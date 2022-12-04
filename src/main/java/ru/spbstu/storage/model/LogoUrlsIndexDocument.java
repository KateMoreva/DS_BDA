package java.ru.spbstu.storage.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogoUrlsIndexDocument {

    @Field(type = FieldType.Text)
    private String logo90;
    @Field(type = FieldType.Text)
    private String logo240;
    @Field(type = FieldType.Text)
    private String original;

}
