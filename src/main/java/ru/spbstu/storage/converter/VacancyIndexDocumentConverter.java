package ru.spbstu.storage.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spbstu.search.SearchException;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.ConstantsProvider;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Currency;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.*;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.Area;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerInVacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.LogoUrls;
import ru.spbstu.storage.model.AddressIndexDocument;
import ru.spbstu.storage.model.AreaIndexDocument;
import ru.spbstu.storage.model.EmployerIndexDocument;
import ru.spbstu.storage.model.LogoUrlsIndexDocument;
import ru.spbstu.storage.model.MetroLineIndexDocument;
import ru.spbstu.storage.model.SalaryIndexDocument;
import ru.spbstu.storage.model.ScheduleIndexDocument;
import ru.spbstu.storage.model.StationIndexDocument;
import ru.spbstu.storage.model.VacancyIndexDocument;

@Component
@Slf4j
public class VacancyIndexDocumentConverter {

    private final MetroStationProvider metroStationProvider;
    private final ConstantsProvider constants;

    @Autowired
    public VacancyIndexDocumentConverter(MetroStationProvider metroStationProvider,
                                         ConstantsProvider constants) {
        this.metroStationProvider = metroStationProvider;
        this.constants = constants;
    }

    public VacancyIndexDocument converter(Vacancy vacancy) {
        Area area = vacancy.getArea();
        AreaIndexDocument areaIndexDocument = new AreaIndexDocument(
                Long.parseLong(area.getId()),
                area.getName()
        );
        Salary rurSalary = toRur(vacancy.getSalary());
        SalaryIndexDocument salaryIndexDocument = new SalaryIndexDocument(
                rurSalary.getFrom(),
                rurSalary.getTo(),
                rurSalary.getCurrency().getName(),
                rurSalary.getGross()
        );
        Address address = vacancy.getAddress();
        Address.Station station = address.getStation();
        MetroStation metroStation = (station != null) ? metroStationProvider.getStationById(station.getStationId()) : MetroStation.NULL_METRO_STATION;
        MetroLine line = metroStation.getLine();
        MetroLineIndexDocument metroLineIndexDocument = line == null ? null : new MetroLineIndexDocument(
                line.getId(),
                line.getName(),
                line.getCity().getName(),
                line.getHexColor()
        );
        StationIndexDocument stationIndexDocument = new StationIndexDocument(
                metroStation.getId(),
                metroLineIndexDocument,
                metroStation.getOrder(),
                metroStation.getLat(),
                metroStation.getLng()
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
        LogoUrlsIndexDocument logoUrlsIndexDocument = logoUrls == null ? null : new LogoUrlsIndexDocument(
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
                vacancy.getCreatedAt(),
                scheduleIndexDocument
        );
    }

    public Salary toRur(Salary salary) {
        Salary salaryRur = new Salary();
        Currency currency = salary.getCurrency();

        Integer from = salary.getFrom();
        Integer fromRur = (from != null) ? (int) (from / currency.getRate()) : null;
        salaryRur.setFrom(fromRur);

        Integer to = salary.getTo();
        Integer toRur = (to != null) ? (int) (to / currency.getRate()) : null;
        salaryRur.setTo(toRur);

        salaryRur.setCurrency(constants.getCurrency().RUR);

        return salaryRur;
    }

}