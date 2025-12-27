package com.yagiz.atmapi.model;

import jakarta.persistence.*; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "hesaplar") 
public class Hesap {

    @Id
    @Column(name = "hesap_no")
    private String hesapNo; 

    private String pin;
    
    private double bakiye;
}