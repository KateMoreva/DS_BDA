package java.ru.spbstu.storage.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.ru.spbstu.storage.service.VacancyService;


@RestController
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @RequestMapping(value = "/submitFetchTask", method = RequestMethod.POST)
    public void submit(@RequestBody @NotNull FetchTaskRequest fetchTaskRequest) {
        vacancyService.submit(fetchTaskRequest);
    }

    @RequestMapping(value = "/submitIdTask", method = RequestMethod.POST)
    public void submit(@RequestBody @NotNull FetchByIdTaskRequest fetchByIdTaskRequest) {
        vacancyService.submit(fetchByIdTaskRequest);
    }

    @RequestMapping(value = "/submitIdsTask", method = RequestMethod.POST)
    public void submit(@RequestBody @NotNull FetchIdsRangeTaskRequest fetchIdsRangeTaskRequest) {
        vacancyService.submit(fetchIdsRangeTaskRequest);
    }

    @RequestMapping(value = "/submitIdsListTask", method = RequestMethod.POST)
    public void submit(@RequestBody @NotNull FetchIdsListRequest fetchIdsListRequest) {
        vacancyService.submit(fetchIdsListRequest);
    }

}
