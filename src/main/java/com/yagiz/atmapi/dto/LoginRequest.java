package com.yagiz.atmapi.dto;
import lombok.Data;

@Data
public class LoginRequest {
    private String hesapNo;
    private String pin;
}