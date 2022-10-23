package ru.spbstu.storage.converter;

import ru.spbstu.search.SearchException;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.Address;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroLine;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroStation;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.Area;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerInVacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.LogoUrls;
import ru.spbstu.storage.model.*;

public class VacancyIndexDocumentConverter {

    public static VacancyIndexDocument converter(Vacancy vacancy) throws SearchException {
        Area area = vacancy.getArea();
        AreaIndexDocument areaIndexDocument = new AreaIndexDocument(
                Long.parseLong(area.getId()),
                area.getName()
        );
        Salary rurSalary = Salary.toRur(vacancy.getSalary());
        SalaryIndexDocument salaryIndexDocument = new SalaryIndexDocument(
                rurSalary.getFrom(),
                rurSalary.getTo(),
                rurSalary.getCurrency().getName(),
                rurSalary.getGross()
        );
        Address address = vacancy.getAddress();
        MetroStation station = address.getStation();
        MetroLine line = station.getLine();
        MetroLineIndexDocument metroLineIndexDocument = new MetroLineIndexDocument(
                line.getId(),
                line.getName(),
                line.getCity().getName(),
                line.getHexColor()
        );
        StationIndexDocument stationIndexDocument = new StationIndexDocument(
                station.getId(),
                metroLineIndexDocument,
                station.getOrder(),
                station.getLat(),
                station.getLng()
        );
        AddressIndexDocument addressIndexDocument = new AddressIndexDocument(
                address.getCity(),
                address.getStreet(),
                address.getBuilding(),
                stationIndexDocument,
                address.getLat(),
                address.getLng(),
                address.getRaw()
        );
        EmployerInVacancy employer = vacancy.getEmployer();
        LogoUrls logoUrls = employer.getLogoUrls();
        LogoUrlsIndexDocument logoUrlsIndexDocument = new LogoUrlsIndexDocument(
                logoUrls.getLogo90().toString(),
                logoUrls.getLogo240().toString(),
                logoUrls.getOriginal().toString()
        );
        EmployerIndexDocument employerIndexDocument = new EmployerIndexDocument(
                employer.getId(),
                employer.getName(),
                employer.getUrl().toString(),
                logoUrlsIndexDocument,
                employer.getTrusted()
        );
        Schedule schedule = vacancy.getSchedule();
        ScheduleIndexDocument scheduleIndexDocument = new ScheduleIndexDocument(schedule.getId(), schedule.getName());
        return new VacancyIndexDocument(
                Long.parseLong(vacancy.getId()),
                vacancy.getName(),
                vacancy.getUrl().toString(),
                vacancy.getDescription(),
                areaIndexDocument,
                salaryIndexDocument,
                addressIndexDocument,
                employerIndexDocument,
                vacancy.getCreatedAt().getTime(),
                scheduleIndexDocument
        );
    }

}
