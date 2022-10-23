package ru.spbstu.loader;

import ru.spbstu.loader.cache.ICache;
import lombok.extern.slf4j.Slf4j;
import ru.spbstu.search.SearchException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ContentLoader implements IContentLoader {
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, List<String>> params = new HashMap<>();
    private final ICache storage;

    ContentLoader(ICache storage) {
        this.storage = storage;
    }

    @Override
    public String loadContent(String url) throws SearchException {
        assert (url != null);
        try {
            String urlWithParams = appendParameters(url);
            String cachedContent = storage.search(urlWithParams);
            if (cachedContent != null && !cachedContent.isEmpty()) {
                return cachedContent;
            } else {
                String content = loadUrlContent(urlWithParams);
                storage.save(urlWithParams, content);
                return content;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new SearchException(e);
        }
    }

    @Override
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public void addParam(String key, String value) {
        if (!params.containsKey(key)) {
            params.put(key, new ArrayList<>(List.of(value)));
        } else {
            List<String> values = params.get(key);
            values.add(value);
        }
    }

    private String loadUrlContent(String url) throws IOException {
        URL hhUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) hhUrl.openConnection();
        connection.setRequestMethod("GET");
        addHeader("User-Agent", "");
        addHeader("Accept", "application/json");
        setHeaders(connection);
        connection.connect();
        String content = readInputStreamToString(connection);
        connection.disconnect();
        return content;
    }

    private String appendParameters(String url) {
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

    private void setHeaders(HttpURLConnection connection) {
        for (String key : headers.keySet()) {
            connection.setRequestProperty(key, headers.get(key));
        }
    }

    private String readInputStreamToString(HttpURLConnection conn)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line != null) {
                builder.append(line);
            } else {
                break;
            }
        }
        return builder.toString();
    }
}
