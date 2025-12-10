package com.yagiz.atmapi.repository;

import com.yagiz.atmapi.model.Hesap;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class HesapRepository {

    
    private Map<String, Hesap> hesaplar = new HashMap<>();

    
    @PostConstruct
    public void initData() {
        hesaplar.put("11112222", new Hesap("11112222", "1234", 1500.75));
        hesaplar.put("99998888", new Hesap("99998888", "9876", 450.00));
        hesaplar.put("12345678", new Hesap("12345678", "0000", 100.00));
    }

    
    public Optional<Hesap> findByHesapNo(String hesapNo) {
        return Optional.ofNullable(hesaplar.get(hesapNo));
    }

    
    public Hesap save(Hesap hesap) {
        hesaplar.put(hesap.getHesapNo(), hesap);
        return hesap;
    }
}