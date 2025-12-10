package com.yagiz.atmapi.service;

import com.yagiz.atmapi.dto.LoginRequest;
import com.yagiz.atmapi.exception.GecersizPinException;
import com.yagiz.atmapi.exception.HesapBulunamadiException;
import com.yagiz.atmapi.model.Hesap;
import com.yagiz.atmapi.repository.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private HesapRepository hesapRepository;

    // Tokenları hafızada tutuyoruz: Token -> HesapNo
    private Map<String, String> activeTokens = new HashMap<>();

    public String login(LoginRequest request) {
        // 1. Hesabı bul
        Hesap hesap = hesapRepository.findByHesapNo(request.getHesapNo())
                .orElseThrow(() -> new HesapBulunamadiException("Hesap bulunamadı"));

        // 2. PIN Kontrolü
        if (!hesap.getPin().equals(request.getPin())) {
            throw new GecersizPinException("Hatalı PIN");
        }

        // 3. Token Üret (UUID basit bir token mantığıdır)
        String token = UUID.randomUUID().toString();
        
        // 4. Token'ı kaydet
        activeTokens.put(token, hesap.getHesapNo());

        return token;
    }

    public boolean validateToken(String token) {
        return activeTokens.containsKey(token);
    }
}