package ru.spbstu.storage.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;
import ru.spbstu.storage.controller.FetchTaskRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class VacancyService implements Lifecycle {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors() / 2;

    private static final Logger logger = LoggerFactory.getLogger(VacancyService.class);

    private ExecutorService executorService;
    private volatile boolean running;

    public void submit(@NotNull FetchTaskRequest request) {
        if (!running) {
            logger.warn("Component already stopped on request=[{}]", request);
            return;
        }
        VacancyLoadTaskStatusChecker statusChecker = new VacancyLoadTaskStatusChecker();
        splitFetchTaskRequest(request).forEach(req -> schedule(req, statusChecker));
    }

    @NotNull
    private List<FetchTaskRequest> splitFetchTaskRequest(@NotNull FetchTaskRequest request) {
        int fromPage = request.getFromPage();
        int toPage = request.getToPage();
        int pagesToProcess = toPage - fromPage;
        int pagesPerOneProcess = pagesToProcess / POOL_SIZE;
        int pagesPerOneProcessMod = pagesToProcess % POOL_SIZE;
        List<FetchTaskRequest> fetchTaskRequests = new ArrayList<>();
        int currentStartPage = 0;
        for (int i = 0; i < POOL_SIZE; ++i) {
            int realPagesPerOneProcess = pagesPerOneProcess + (pagesPerOneProcessMod != 0 ? 1 : 0);
            pagesPerOneProcess--;
            fetchTaskRequests.add(new FetchTaskRequest(
                    request.getDateFrom(),
                    request.getDateTo(),
                    request.getSpecialisationId(),
                    request.getLimitPerPage(),
                    currentStartPage,
                    currentStartPage + realPagesPerOneProcess
            ));
            currentStartPage = realPagesPerOneProcess;
        }
        return fetchTaskRequests;
    }

    private void schedule(@NotNull FetchTaskRequest request,
                          @NotNull VacancyLoadTaskStatusChecker statusChecker) {
          LoadVacanciesTask loadVacanciesTask = new LoadVacanciesTask(
                  request.getDateFrom(),
                  request.getDateTo(),
                  request.getSpecialisationId(),
                  request.getLimitPerPage(),
                  request.getFromPage(),
                  request.getToPage()
          );
          Future<?> future = executorService.submit(loadVacanciesTask);
          statusChecker.acceptNewFutureTask(future);
    }

    @Override
    public void start() {
        logger.info("start");
        running = true;
        executorService = Executors.newFixedThreadPool(POOL_SIZE);
    }

    @Override
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

    @Override
    public boolean isRunning() {
        return false;
    }
}
