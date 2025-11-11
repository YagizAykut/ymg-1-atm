package com.yagiz.atmapi.dto;
import lombok.Data;
@Data
public class ParaCekRequest {
    private String hesapNo;
    private String pin;
    private double miktar;
}