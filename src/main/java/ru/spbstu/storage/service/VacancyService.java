package ru.spbstu.storage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.spbstu.search.SearchComponent;
import ru.spbstu.storage.controller.FetchByIdTaskRequest;
import ru.spbstu.storage.controller.FetchIdsRangeTaskRequest;
import ru.spbstu.storage.controller.FetchTaskRequest;
import ru.spbstu.storage.converter.VacancyIndexDocumentConverter;
import ru.spbstu.storage.repository.VacancyRepository;

@Service
public class VacancyService {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors() / 2;

    private static final Logger logger = LoggerFactory.getLogger(VacancyService.class);

    private final SearchComponent searchComponent;
    private final VacancyRepository vacancyRepository;
    private final VacancyIndexDocumentConverter converter;
    private ExecutorService executorService;
    private volatile boolean running;

    @Autowired
    public VacancyService(SearchComponent searchComponent,
                          VacancyRepository vacancyRepository,
                          VacancyIndexDocumentConverter converter) {
        this.searchComponent = searchComponent;
        this.vacancyRepository = vacancyRepository;
        this.converter = converter;
    }

    @PostConstruct
    public void start() {
        logger.info("start");
        running = true;
        executorService = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public void submit(@NotNull FetchTaskRequest request) {
        if (!running) {
            logger.warn("Component already stopped on request=[{}]", request);
            return;
        }
        VacancyLoadTaskStatusChecker statusChecker = new VacancyLoadTaskStatusChecker();
        statusChecker.start();
        splitFetchTaskRequest(request).forEach(req -> schedule(req, statusChecker));
    }

    public void submit(@NotNull FetchByIdTaskRequest request) {
        if (!running) {
            logger.warn("Component already stopped on request=[{}]", request);
            return;
        }
        VacancyLoadTaskStatusChecker statusChecker = new VacancyLoadTaskStatusChecker();
        statusChecker.start();
        schedule(request, statusChecker);
    }

    public void submit(@NotNull FetchIdsRangeTaskRequest request) {
        if (!running) {
            logger.warn("Component already stopped on request=[{}]", request);
            return;
        }
        VacancyLoadTaskStatusChecker statusChecker = new VacancyLoadTaskStatusChecker();
        statusChecker.start();
        try {
            long from = Long.parseLong(request.getVacancyIdFrom());
            long to = Long.parseLong(request.getVacancyIdTo());
            for (long i = from; i <= to; i++) {
                schedule(new FetchByIdTaskRequest(String.valueOf(i)), statusChecker);
            }
        } catch (NumberFormatException e) {
            logger.warn("Rubbish range request [{}]", request);
        }
    }

    @NotNull
    private List<FetchTaskRequest> splitFetchTaskRequest(@NotNull FetchTaskRequest request) {
        int fromPage = request.getFromPage();
        int toPage = request.getToPage();
        int pagesToProcess = toPage - fromPage + 1;// 2 - 0 + 1 = 3

        int pagesPerOneProcess = pagesToProcess / POOL_SIZE; // 0

        if (pagesPerOneProcess == 0) {
            return Collections.singletonList(request);
        }

        int pagesPerOneProcessMod = pagesToProcess % POOL_SIZE; // 3
        List<FetchTaskRequest> fetchTaskRequests = new ArrayList<>();
        int currentStartPage = 0;
        for (int i = 0; i < POOL_SIZE; ++i) {
            int realPagesPerOneProcess = pagesPerOneProcess + (pagesPerOneProcessMod != 0 ? 1 : 0); // 26 26 26 25
            pagesPerOneProcessMod = pagesPerOneProcessMod > 0 ? pagesPerOneProcessMod - 1 : pagesPerOneProcessMod; // 2 1 0
            int finalPage = currentStartPage + realPagesPerOneProcess >= pagesToProcess
                ? currentStartPage + realPagesPerOneProcess : currentStartPage + realPagesPerOneProcess - 1;
            fetchTaskRequests.add(new FetchTaskRequest(
                request.getDateFrom(),
                request.getDateTo(),
                request.getSpecialisationId(),
                request.getLimitPerPage(),
                currentStartPage, // 0 26 52 78
                finalPage // 25 51 77 103
            ));
            currentStartPage += realPagesPerOneProcess; // 26 52 78
            if (currentStartPage >= toPage) {
                break;
            }
        }
        return fetchTaskRequests;
    }

    private void schedule(@NotNull FetchTaskRequest request,
                          @NotNull VacancyLoadTaskStatusChecker statusChecker) {
        LoadVacanciesTask loadVacanciesTask = new LoadVacanciesTask(
            searchComponent,
            vacancyRepository,
            converter,
            request.getDateFrom(),
            request.getDateTo(),
            request.getSpecialisationId(),
            request.getLimitPerPage(),
            request.getFromPage(),
            request.getToPage()
        );
//        logger.info("LoadVacanciesTask [{}]", loadVacanciesTask);
        Future<Boolean> future = executorService.submit(loadVacanciesTask);
        statusChecker.acceptNewFutureTask(request, future);
    }

    private void schedule(@NotNull FetchByIdTaskRequest request,
                          @NotNull VacancyLoadTaskStatusChecker statusChecker) {
        LoadVacancyByIdTask loadVacanciesTask = new LoadVacancyByIdTask(
            searchComponent,
            vacancyRepository,
            converter,
            request.getVacancyId()
        );
//        logger.info("LoadVacanciesTask [{}]", loadVacanciesTask);
        Future<Boolean> future = executorService.submit(loadVacanciesTask);
        statusChecker.acceptNewFutureTask(request, future);
    }

    @PreDestroy
    public void stop() {
        logger.info("stop");
        running = false;
        executorService.shutdown();
        try {
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Failed to stop", e);
            Thread.currentThread().interrupt();
        }
    }

}
