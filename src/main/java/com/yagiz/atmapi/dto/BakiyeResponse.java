package com.yagiz.atmapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor // Constructor'ı oluşturur
public class BakiyeResponse {
    private String hesapNo;
    private double guncelBakiye;
}