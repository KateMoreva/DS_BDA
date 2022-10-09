package loader;

import loader.cache.FileCache;
import loader.cache.ICache;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public final class ContentLoaderFactory {
    public static final String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");
    private static final ICache LONG_TERM_STORAGE;

    static {
        final File cacheDir = new File(JAVA_IO_TMPDIR, "hh_api_cache");

        int sevenDays = 7 * 24 * 60;
        File longTermDir = new File(cacheDir, "long_term");
        LONG_TERM_STORAGE = new FileCache(longTermDir, sevenDays);
        log.debug("Storage cache initialized ({} min.): {}", sevenDays, longTermDir.getAbsolutePath());
    }

    private ContentLoaderFactory() {
    }

    public static IContentLoader newInstanceLongTermCache() {
        return new ContentLoader(LONG_TERM_STORAGE);
    }
}
