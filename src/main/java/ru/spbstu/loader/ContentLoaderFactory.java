package java.ru.spbstu.loader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ContentLoaderFactory {

    private ContentLoaderFactory() {
    }

    public static IContentLoader newInstanceLongTermCache() {
        return new ContentLoader();
    }
}
