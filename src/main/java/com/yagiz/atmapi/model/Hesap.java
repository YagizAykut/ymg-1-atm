package com.yagiz.atmapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Hesap {
    private String hesapNo;
    private String pin;
    private double bakiye;
}