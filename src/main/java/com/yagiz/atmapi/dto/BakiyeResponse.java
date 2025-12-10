package com.yagiz.atmapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor 
public class BakiyeResponse {
    private String hesapNo;
    private double guncelBakiye;
}