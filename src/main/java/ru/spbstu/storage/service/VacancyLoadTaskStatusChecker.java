package ru.spbstu.storage.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VacancyLoadTaskStatusChecker {


    private static final int CHECKER_INITIAL_DELAY = 100;
    private static final int CHECKER_PERIOD = 100;
    private static final TimeUnit CHECKER_TIMEUNIT = TimeUnit.MILLISECONDS;
    private static final int CHECKER_SERVICE_POOL_SIZE = 1;

    private static final Logger logger = LoggerFactory.getLogger(VacancyLoadTaskStatusChecker.class);

    private final ScheduledExecutorService checkerService = Executors.newScheduledThreadPool(CHECKER_SERVICE_POOL_SIZE);

    private final Queue<Future<Boolean>> futuresQueue = new LinkedBlockingQueue<>();
    private boolean shouldNoAcceptNewTask;

    public void start() {
        checkerService.scheduleAtFixedRate(
                this::checkStatus,
                CHECKER_INITIAL_DELAY,
                CHECKER_PERIOD,
                CHECKER_TIMEUNIT
        );
    }

    public void stop() {
        try {
            checkerService.shutdownNow();
        } catch (RuntimeException ex) {
            logger.warn("Failed to correctly stop vacancy load status checker executor service", ex);
        }
    }

    /**
     * // TODO: probably here we need some arguments to write them to the log when smth went wrong
     */
    private void checkStatus() {
        try {
            Future<Boolean> future = futuresQueue.peek();
            if (future == null) {
                logger.info("There is no more task in the futures queue, skip this iteration");
                return;
            }
            if (future.isCancelled()) {
                logger.warn("Future task from queue is canceled. Probably there is a bug.");
            }
            if (!future.isDone()) {
                logger.info("Future task still not done. Status will be rechecked in the next iteration.");
                return;
            }
            this.shouldNoAcceptNewTask = future.get();
            if (shouldNoAcceptNewTask) {
                stop();
            }
        } catch (RuntimeException ex) {
            logger.warn("Smth went wrong while checking vacancy load task status", ex);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            logger.error("Failed to stop", e);
            Thread.currentThread().interrupt();
        }
    }

    public void acceptNewFutureTask(@NotNull Future<Boolean> newFuture) {
        if (newFuture.isCancelled()) {
            logger.warn("Accepted by status checker future is already canceled");
            return;
        }
        if (!newFuture.isDone()) {
            futuresQueue.add(newFuture);
        }
    }


}
