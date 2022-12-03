package ru.spbstu.storage.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.checkerframework.common.subtyping.qual.Bottom;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class LoadVacanciesTask implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(LoadVacanciesTask.class);

    private final SearchComponent searchComponent;
    private final VacancyRepository vacancyRepository;
    private final VacancyIndexDocumentConverter converter;
    private final String dateFrom;
    private final String dateTo;

    public LoadVacanciesTask(@NotNull SearchComponent searchComponent,
                             @NotNull VacancyRepository vacancyRepository,
                             @Bottom VacancyIndexDocumentConverter converter,
                             @NotNull String dateFrom,
                             @NotNull String dateTo){
        this.searchComponent = searchComponent;
        this.vacancyRepository = vacancyRepository;
        this.converter = converter;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }


    @Override
    public Boolean call() {
        try {
                VacancyPage vacancyPage = searchComponent.search(new SearchParameterBox()
                    .addParameter(new DateFrom(dateFrom))
                    .addParameter(new DateTo(dateTo)));
                if (vacancyPage == null || CollectionUtils.isEmpty(vacancyPage.getItems())) {
                    logger.info("Vacancy page is null. No more results?");
                    return true;
                }
                List<String> ids = vacancyPage.getItems().stream().map(Vacancy::getId).collect(Collectors.toList());
                for (String id : ids) {
                    Vacancy vacancy = searchComponent.search(id);
                    indexPageResults(vacancy);
                }
//                indexPageResults(vacancyPage);
        } catch (SearchException e) {
            logger.error("Failed to load vacancy page with next params: \n" +
                    "dateFrom=[{}], dateTo=[{}], specialisationId=[{}], \n",
                dateFrom, dateTo);
            return false;
        }
        return true;
    }

    public void indexPageResults(@NotNull Vacancy vacancy) {
        try {
            List<VacancyIndexDocument> vacancyIndexDocuments = converter.converter(vacancy);
            log.debug("Load vacancy [{}]", vacancy.getId());
            vacancyRepository.saveAll(vacancyIndexDocuments);
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
            '}';
    }
}
