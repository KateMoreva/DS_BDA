package ru.spbstu.storage.service;

import java.util.concurrent.Callable;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.spbstu.search.SearchComponent;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.storage.converter.VacancyIndexDocumentConverter;
import ru.spbstu.storage.model.VacancyIndexDocument;
import ru.spbstu.storage.repository.VacancyRepository;

public class LoadVacancyByIdTask implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(LoadVacancyByIdTask.class);

    private final SearchComponent searchComponent;
    private final VacancyRepository vacancyRepository;
    private final VacancyIndexDocumentConverter converter;
    private final String vacancyId;


    public LoadVacancyByIdTask(@NotNull SearchComponent searchComponent,
                               @NotNull VacancyRepository vacancyRepository,
                               @NotNull VacancyIndexDocumentConverter converter,
                               @NotNull String vacancyId) {
        this.searchComponent = searchComponent;
        this.vacancyRepository = vacancyRepository;
        this.converter = converter;
        this.vacancyId = vacancyId;
    }

    @Override
    public Boolean call() {
        try {
            Vacancy vacancy = searchComponent.search(vacancyId);
            if (vacancy == null) {
                logger.info("Vacancy is null");
                return true;
            }
            indexPageResults(vacancy);
        } catch (SearchException e) {
            logger.error("Failed to load vacancy page with next params: \n" +
                "vacancyId=[{}]", vacancyId);
            return false;
        }
        return true;
    }

    public void indexPageResults(@NotNull Vacancy vacancy) {
        try {
            VacancyIndexDocument indexDocument = converter.converter(vacancy);
            vacancyRepository.save(indexDocument);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "LoadVacancyByIdTask{" +
            "searchComponent=" + searchComponent +
            ", vacancyRepository=" + vacancyRepository +
            ", converter=" + converter +
            ", vacancyId='" + vacancyId +
            '}';
    }

}