package java.ru.spbstu.storage.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.ru.spbstu.storage.model.VacancyIndexDocument;

public interface VacancyRepository extends ElasticsearchRepository<VacancyIndexDocument, Long> {

}
