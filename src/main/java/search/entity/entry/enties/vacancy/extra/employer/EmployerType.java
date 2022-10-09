package search.entity.entry.enties.vacancy.extra.employer;

import loader.ContentLoaderFactory;
import loader.IContentLoader;
import loader.UrlConstants;
import search.SearchException;
import search.entity.Entity;
import search.entity.IEntity;
import search.entity.entry.AbstractEntityEntry;
import search.entity.entry.enties.vacancy.extra.ExtraInfoEntityContainer;
import search.entity.entry.enties.vacancy.extra.ExtraInfoParser;

import java.util.Collections;

public class EmployerType extends AbstractEntityEntry {
    public static final EmployerType NULL_EMPLOYER_TYPE = new EmployerType();
    public static IEntity<EmployerType>
            EMPLOYER_TYPES;
    public static final EmployerType
            COMPANY = EMPLOYER_TYPES.getById("company");
    public static final EmployerType
            AGENCY = EMPLOYER_TYPES.getById("agency");
    public static final EmployerType
            PRIVATE_RECRUITER = EMPLOYER_TYPES.getById("private_recruiter");
    private IContentLoader loader = ContentLoaderFactory.newInstanceLongTermCache();

    private EmployerType() {
        try {
            String content = loader.loadContent(UrlConstants.DICTINARIES_URL);
            ExtraInfoParser parse = new ExtraInfoParser();
            ExtraInfoEntityContainer container = parse.parse(content);
            EMPLOYER_TYPES = new Entity<>(container.getEmployerType(), NULL_EMPLOYER_TYPE);
        } catch (SearchException e) {
            e.printStackTrace();
            EMPLOYER_TYPES = new Entity<>(Collections.emptyList(), NULL_EMPLOYER_TYPE);
        }
    }
}
