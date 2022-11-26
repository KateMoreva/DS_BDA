package ru.spbstu.storage.service;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.collections.CollectionUtils;
import org.checkerframework.common.subtyping.qual.Bottom;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.spbstu.search.Page;
import ru.spbstu.search.PerPage;
import ru.spbstu.search.SearchComponent;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.SearchParameterBox;
import ru.spbstu.search.entity.entry.enties.profile.Specialization;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyPage;
import ru.spbstu.search.param.DateFrom;
import ru.spbstu.search.param.DateTo;
import ru.spbstu.storage.converter.VacancyIndexDocumentConverter;
import ru.spbstu.storage.model.VacancyIndexDocument;
import ru.spbstu.storage.repository.VacancyRepository;

public class LoadVacanciesTask implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(LoadVacanciesTask.class);

    private final SearchComponent searchComponent;
    private final VacancyRepository vacancyRepository;
    private final VacancyIndexDocumentConverter converter;
    private final String dateFrom;
    private final String dateTo;
    private final String specialisationId;
    private final int limitPerPage;
    private final int fromPage;
    private final int toPage;

    public LoadVacanciesTask(@NotNull SearchComponent searchComponent,
                             @NotNull VacancyRepository vacancyRepository,
                             @Bottom VacancyIndexDocumentConverter converter,
                             @NotNull String dateFrom,
                             @NotNull String dateTo,
                             @NotNull String specialisationId,
                             int limitPerPage,
                             int fromPage,
                             int toPage) {
        this.searchComponent = searchComponent;
        this.vacancyRepository = vacancyRepository;
        this.converter = converter;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.specialisationId = specialisationId;
        this.limitPerPage = limitPerPage;
        this.fromPage = fromPage;
        this.toPage = toPage;
    }


    @Override
    public Boolean call() {
        try {
            for (int page = fromPage; page < toPage; ++page) {
                VacancyPage vacancyPage = searchComponent.search(new SearchParameterBox()
                    .addParameter(new PerPage(limitPerPage))
                    .addParameter(new Page(page))
                    .addParameter(new Specialization(specialisationId))
                    .addParameter(new DateFrom(dateFrom))
                    .addParameter(new DateTo(dateTo)));
                if (vacancyPage == null || CollectionUtils.isEmpty(vacancyPage.getItems())) {
                    logger.info("Vacancy page is null. No more results?");
                    return true;
                }
                indexPageResults(vacancyPage);
            }
        } catch (SearchException e) {
            logger.error("Failed to load vacancy page with next params: \n" +
                    "dateFrom=[{}], dateTo=[{}], specialisationId=[{}], \n" +
                    "limitPerPage=[{}], fromPage=[{}], toPage=[{}]",
                dateFrom, dateTo, specialisationId, limitPerPage, fromPage, toPage);
            return false;
        }
        return true;
    }

    public void indexPageResults(@NotNull VacancyPage vacancyPage) {
        List<Vacancy> vacancyList = vacancyPage.getItems();
        try {
            for (Vacancy vacancy : vacancyList) {
                VacancyIndexDocument indexDocument = converter.converter(vacancy);
                vacancyRepository.save(indexDocument);
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "LoadVacanciesTask{" +
            "searchComponent=" + searchComponent +
            ", vacancyRepository=" + vacancyRepository +
            ", converter=" + converter +
            ", dateFrom='" + dateFrom + '\'' +
            ", dateTo='" + dateTo + '\'' +
            ", specialisationId='" + specialisationId + '\'' +
            ", limitPerPage=" + limitPerPage +
            ", fromPage=" + fromPage +
            ", toPage=" + toPage +
            '}';
    }
}
