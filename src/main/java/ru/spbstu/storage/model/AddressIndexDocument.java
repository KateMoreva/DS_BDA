package ru.spbstu.storage.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressIndexDocument {

    private String city;
    private String street;
    private String building;
    private StationIndexDocument station;
    private Double lat;
    private Double lng;
    private String raw;

}
