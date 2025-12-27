package com.yagiz.atmapi.model;

import jakarta.persistence.*; // Bu import önemli
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Veritabanı tablosu olduğunu belirtir
@Table(name = "hesaplar") // Tablo adı 'hesaplar' olsun
public class Hesap {

    @Id
    @Column(name = "hesap_no")
    private String hesapNo; // TC/Hesap No ID olarak kullanıyoruz

    private String pin;
    
    private double bakiye;
}