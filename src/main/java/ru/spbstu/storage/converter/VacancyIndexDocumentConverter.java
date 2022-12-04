package ru.spbstu.storage.converter;

import java.net.URL;
import ru.spbstu.search.entity.entry.enties.profile.ProfField;
import ru.spbstu.search.entity.entry.enties.vacancy.Vacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Salary;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.Schedule;
import ru.spbstu.storage.model.ScheduleIndexDocument;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import ru.spbstu.search.entity.entry.enties.vacancy.extra.Skill;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.Address;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroLine;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroStation;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.address.MetroStationProvider;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.area.Area;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.EmployerInVacancy;
import ru.spbstu.search.entity.entry.enties.vacancy.extra.employer.LogoUrls;
import ru.spbstu.storage.model.AddressIndexDocument;
import ru.spbstu.storage.model.AreaIndexDocument;
import ru.spbstu.storage.model.EmployerIndexDocument;
import ru.spbstu.storage.model.LogoUrlsIndexDocument;
import ru.spbstu.storage.model.MetroLineIndexDocument;
import ru.spbstu.storage.model.SalaryIndexDocument;
import ru.spbstu.storage.model.StationIndexDocument;
import ru.spbstu.storage.model.VacancyIndexDocument;

@Component
@Slf4j
public class VacancyIndexDocumentConverter {

    private final MetroStationProvider metroStationProvider;

    @Autowired
    public VacancyIndexDocumentConverter(MetroStationProvider metroStationProvider) {
        this.metroStationProvider = metroStationProvider;
    }

    public List<VacancyIndexDocument> converter(Vacancy vacancy) {
        String name = vacancy.getName();
        VacancyNameParser vacancyNameParser = new VacancyNameParser(name);
        String specializationSf = vacancyNameParser.getSpecialization();
        if ((specializationSf == null || specializationSf.isEmpty()) && vacancy.getProfessionalRoles() != null) {
            ProfField profField = vacancy.getProfessionalRoles().get(0);
            specializationSf = vacancyNameParser.getSpecialization(profField.getName());
        }
        List<String> levelList = vacancyNameParser.getLevel();
        List<VacancyIndexDocument> vacancyIndexDocuments = new ArrayList<>();
        int counter = 0;
        for (String level : levelList) {
            vacancyIndexDocuments.add(processLevel(vacancyNameParser, specializationSf, vacancy, level, counter++));
        }
        return vacancyIndexDocuments;
    }

    private VacancyIndexDocument processLevel(@NotNull VacancyNameParser vacancyNameParser,
                                              @NotNull String specializationSf,
                                              @NotNull Vacancy vacancy,
                                              @NotNull String levelSf, int counter) {
        if (specializationSf.isBlank() && vacancy.getKeySkills() != null) {
            specializationSf = vacancyNameParser.getSpecialization(vacancy.getKeySkills().stream().map(Skill::getName).collect(Collectors.toList()));
        }
        String fieldSf = vacancyNameParser.getField();
        if (fieldSf.isBlank() && vacancy.getKeySkills() != null) {
            fieldSf = vacancyNameParser.getField(vacancy.getKeySkills().stream().map(Skill::getName).collect(Collectors.toList()));
        }
        String subdomainSf = vacancyNameParser.getSubDomain();
        if (subdomainSf.isBlank() && vacancy.getKeySkills() != null) {
            subdomainSf = vacancyNameParser.getSubDomain(vacancy.getKeySkills().stream().map(Skill::getName).collect(Collectors.toList()));
        }
        if (specializationSf.isBlank() && !fieldSf.isBlank()) {
            if (fieldSf.equals("Backend") || fieldSf.equals("Frontend")) {
                specializationSf = "Software engineer";
            }
            if (fieldSf.equals("Management")) {
                specializationSf = "Product/Project manager";
            }
        }
        String languageSf = vacancyNameParser.getLanguage();
        if (languageSf.isBlank() && vacancy.getKeySkills() != null) {
            languageSf = vacancyNameParser.getLanguage(vacancy.getKeySkills().stream().map(Skill::getName).collect(Collectors.toList()));
        }

        List<String> tech = new ArrayList<>();
        if (vacancy.getKeySkills() != null) {
            tech = vacancyNameParser.getTech(vacancy.getKeySkills().stream().map(Skill::getName).collect(Collectors.toList()));
        }

        // validate this
        specializationSf = !specializationSf.isBlank() ? specializationSf : "_";
        fieldSf = !fieldSf.isBlank() ? fieldSf : "_";
        subdomainSf = !subdomainSf.isBlank() ? subdomainSf : "_";
        levelSf = !levelSf.isBlank() ? levelSf : "_";
        languageSf = !languageSf.isBlank() ? languageSf : "_";
        if (tech.isEmpty()) {
            tech.add("_");
        }

        Area area = vacancy.getArea();
        AreaIndexDocument areaIndexDocument = new AreaIndexDocument(
                Long.parseLong(area.getId()),
                area.getName()
        );
        Salary rurSalary = vacancy.getSalary();
        SalaryIndexDocument salaryIndexDocument = new SalaryIndexDocument(
                rurSalary.getFrom(),
                rurSalary.getTo(),
                rurSalary.getCurrency(),
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
        GeoPoint geoPoint = metroStation.getLng() == null || metroStation.getLat() == null
                ? null
                : new GeoPoint(metroStation.getLat(), metroStation.getLng());
        StationIndexDocument stationIndexDocument = new StationIndexDocument(
                metroStation.getId(),
                metroLineIndexDocument,
                metroStation.getOrder(),
                geoPoint
        );
        geoPoint = address.getLng() == null || address.getLat() == null
                ? null
                : new GeoPoint(address.getLat(), address.getLng());
        AddressIndexDocument addressIndexDocument = new AddressIndexDocument(
                address.getCity(),
                address.getStreet(),
                address.getBuilding(),
                stationIndexDocument,
                geoPoint,
                address.getRaw()
        );
        EmployerInVacancy employer = vacancy.getEmployer();
        LogoUrls logoUrls = employer.getLogoUrls();
        LogoUrlsIndexDocument logoUrlsIndexDocument = logoUrls == null ? null : new LogoUrlsIndexDocument(
                logoUrls.getLogo90() != null ? logoUrls.getLogo90().toString() : "",
                logoUrls.getLogo240() != null ? logoUrls.getLogo240().toString() : "",
                logoUrls.getOriginal() != null ? logoUrls.getOriginal().toString() : ""
        );
        URL employerURl = employer.getUrl();
        String url = employerURl != null ? employerURl.getPath() : "";
        EmployerIndexDocument employerIndexDocument = new EmployerIndexDocument(
                employer.getId(),
                employer.getName(),
                url,
                logoUrlsIndexDocument,
                employer.getTrusted()
        );
        Schedule schedule = vacancy.getSchedule();
        ScheduleIndexDocument scheduleIndexDocument = new ScheduleIndexDocument(schedule.getId(), schedule.getName());

        return new VacancyIndexDocument(
                vacancy.getId() + "_" + counter,
                vacancy.getName(),
                vacancy.getUrl() == null ? " " : vacancy.getUrl().toString(),
                vacancy.getDescription(),
                areaIndexDocument,
                salaryIndexDocument,
                addressIndexDocument,
                employerIndexDocument,
                vacancy.getCreatedAt(),
                vacancy.getPublishedAt(),
                scheduleIndexDocument,
                specializationSf,
                fieldSf,
                subdomainSf,
                levelSf,
                languageSf,
            tech
        );
    }
//
//    public Salary toRur(Salary salary) {
//        Salary salaryRur = new Salary();
//        String currency = salary.getCurrency();
//
//        Integer from = salary.getFrom();
////        Integer fromRur = (from != null) ? (int) (from / currency.getRate()) : null;
//        salaryRur.setFrom(from);
//
//        Integer to = salary.getTo();
////        Integer toRur = (to != null) ? (int) (to / currency.getRate()) : null;
//        salaryRur.setTo(to);
//
//        salaryRur.setCurrency(constants.getCurrency().RUR.toString());
//
//        return salaryRur;
//    }

}
