package ru.spbstu.loader;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.jetbrains.annotations.NotNull;
import ru.spbstu.search.SearchException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ContentLoader implements IContentLoader {

    private static final int CLIENT_POOL_SIZE = Runtime.getRuntime().availableProcessors() / 2;
    private static final int CLIENT_TIMEOUT = 10000;
    private final HttpClient client;

    ContentLoader() {
        final ExecutorService clientES = Executors.newFixedThreadPool(
                CLIENT_POOL_SIZE,
                new ThreadFactoryBuilder()
                        .setNameFormat("async-client-%d")
                        .setUncaughtExceptionHandler((t, e) -> log.error("Error when processing request in: {}", t, e))
                        .build()
        );
        this.client = java.net.http.HttpClient.newBuilder()
                .executor(clientES)
                .connectTimeout(Duration.ofMillis(CLIENT_TIMEOUT))
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .build();
    }

    public static HttpRequest buildRequest(@NotNull String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.ofMillis(CLIENT_TIMEOUT))
                    .header("Accept", "application/json")
                    .header("User-Agent", "")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            log.error("Cannot construct URI for [{}]", url);
            throw new IllegalArgumentException("Failed to create URI", e);
        }
    }

    @Override
    public String loadContent(@NotNull String url,
                              @NotNull Map<String, List<String>> params) throws SearchException {
        try {
            String urlWithParams = appendParameters(url, params);
            HttpRequest httpRequest = buildRequest(urlWithParams);
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.error("Invalid response [{}]", response);
                throw new RuntimeException("smth went wrong");
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
            throw new SearchException(e);
        }
    }

    @NotNull
    private String appendParameters(@NotNull String url,
                                    @NotNull Map<String, List<String>> params) {
        if (MapUtils.isEmpty(params)) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        for (String key : params.keySet()) {
            for (String value : params.get(key)) {
                if (builder.indexOf("?") == -1) {
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key);
                builder.append("=");
                builder.append(value);
            }
        }
        return builder.toString();
    }

}
