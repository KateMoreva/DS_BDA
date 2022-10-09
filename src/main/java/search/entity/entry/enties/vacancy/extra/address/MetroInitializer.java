package search.entity.entry.enties.vacancy.extra.address;

import loader.ContentLoaderFactory;
import loader.IContentLoader;
import loader.UrlConstants;
import lombok.extern.slf4j.Slf4j;
import search.parser.IParser;
import search.parser.MetroParser;

@Slf4j
final class MetroInitializer {
    private static MetroInitializer Entity;
    private IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();
    private MetroCityEntity metroCities;

    private MetroInitializer() {
        log.debug("MetroInitializer");
        loadMetro();
    }

    public static MetroInitializer getInstance() {
        if (Entity == null) {
            Entity = new MetroInitializer();
        }
        return Entity;
    }

    MetroCityEntity getMetroCities() {
        return metroCities;
    }

    private void loadMetro() {
        try {
            log.debug("Load metro");
            String content = loader.loadContent(UrlConstants.METRO_URL);
            IParser<MetroCityEntity> parse = new MetroParser();
            metroCities = parse.parse(content);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            metroCities = new MetroCityEntity();
        }
    }

}
