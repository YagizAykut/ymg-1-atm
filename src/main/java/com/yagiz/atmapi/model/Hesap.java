package com.yagiz.atmapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode'u otomatik oluşturur
@NoArgsConstructor // Boş constructor
@AllArgsConstructor // Dolu constructor
public class Hesap {
    private String hesapNo;
    private String pin;
    private double bakiye;
}