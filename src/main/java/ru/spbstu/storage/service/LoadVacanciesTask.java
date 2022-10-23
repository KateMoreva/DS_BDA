package ru.spbstu.storage.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.search.Page;
import ru.spbstu.search.PerPage;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.VacancyPageSearch;
import ru.spbstu.search.entity.entry.enties.profile.Specialization;
import ru.spbstu.search.entity.entry.enties.vacancy.VacancyPage;
import ru.spbstu.search.param.DateFrom;
import ru.spbstu.search.param.DateTo;

public class LoadVacanciesTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(LoadVacanciesTask.class);

    private final String dateFrom;
    private final String dateTo;
    private final String specialisationId;
    private final int limitPerPage;
    private final int fromPage;
    private final int toPage;

    public LoadVacanciesTask(@NotNull String dateFrom,
                             @NotNull String dateTo,
                             @NotNull String specialisationId,
                             int limitPerPage,
                             int fromPage,
                             int toPage) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.specialisationId = specialisationId;
        this.limitPerPage = limitPerPage;
        this.fromPage = fromPage;
        this.toPage = toPage;
    }


    @Override
    public void run() {
        try {
            for (int page = fromPage; page < toPage; ++page) {
                VacancyPage vacancyPage = new VacancyPageSearch()
                        .addParameter(new PerPage(limitPerPage))
                        .addParameter(new Page(page))
                        .addParameter(new Specialization())
                        .addParameter(new DateFrom(dateFrom))
                        .addParameter(new DateTo(dateTo))
                        .search();
                indexPageResults(vacancyPage);
            }
        } catch (SearchException e) {
            logger.error("Failed to load vacancy page with next params: \n" +
                            "dateFrom=[{}], dateTo=[{}], specialisationId=[{}], \n" +
                            "limitPerPage=[{}], fromPage=[{}], toPage=[{}]",
                    dateFrom, dateTo, specialisationId, limitPerPage, fromPage, toPage);
        }
    }

    public void indexPageResults(@NotNull VacancyPage vacancyPage) {

    }

}
