package java.ru.spbstu.search.entity.entry.enties.profile;

import lombok.Getter;
import lombok.Setter;

public class Specialization extends ProfField {
    public static final Specialization NULL_SPECIALIZATION = new Specialization(null);
    @Getter
    @Setter
    private int profarea_id;
    @Getter
    @Setter
    private String profarea_name;
    public Specialization(String id) {
        this.setId(id);
    }

}
