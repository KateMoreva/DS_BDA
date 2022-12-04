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
public class SalaryIndexDocument {

    @Field(type = FieldType.Integer)
    private Integer from;
    @Field(type = FieldType.Integer)
    private Integer to;
    @Field(type = FieldType.Text)
    private String currency;
    @Field(type = FieldType.Boolean)
    private Boolean gross;

}
