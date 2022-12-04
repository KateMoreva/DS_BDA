package java.ru.spbstu.loader;

import org.jetbrains.annotations.NotNull;
import java.ru.spbstu.search.SearchException;

import java.util.List;
import java.util.Map;

public interface IContentLoader {
    String loadContent(@NotNull String url,
                       @NotNull Map<String, List<String>> params) throws SearchException;

    String loadContent(@NotNull String urlWithParams) throws SearchException;
}
