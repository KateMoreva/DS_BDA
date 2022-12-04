package java.ru.spbstu.search.entity.entry.enties.vacancy.extra;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import java.ru.spbstu.search.parser.IParser;

@Slf4j
public class ExtraInfoParser implements IParser<ExtraInfoEntityContainer> {

    private final Gson gson;

    public ExtraInfoParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public ExtraInfoEntityContainer parse(String content) {
        log.info("Start passing external info {}", content);
        return gson.fromJson(content, ExtraInfoEntityContainer.class);
    }
}
