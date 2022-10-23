package ru.spbstu.storage.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.spbstu.storage.service.VacancyService;


@RestController
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @RequestMapping(value = "/submitFetchTask", method = RequestMethod.POST)
    public void submit(@RequestBody @NotNull FetchTaskRequest fetchTaskRequest) {
        vacancyService.submit(
                fetchTaskRequest.getDateTo(),
                fetchTaskRequest.getDateFrom(),
                fetchTaskRequest.getSpecialisationId()
        );
    }

}
