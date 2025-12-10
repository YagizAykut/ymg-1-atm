package com.yagiz.atmapi.service;

import com.yagiz.atmapi.dto.BakiyeResponse;
import com.yagiz.atmapi.dto.ParaCekRequest;
import com.yagiz.atmapi.dto.ParaYatirRequest;
import com.yagiz.atmapi.exception.GecersizPinException;
import com.yagiz.atmapi.exception.HesapBulunamadiException;
import com.yagiz.atmapi.exception.YetersizBakiyeException;
import com.yagiz.atmapi.model.Hesap;
import com.yagiz.atmapi.repository.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    @Autowired
    private HesapRepository hesapRepository;

    public BakiyeResponse getBakiye(String hesapNo) {
        Hesap hesap = hesapRepository.findByHesapNo(hesapNo)
                .orElseThrow(() -> new HesapBulunamadiException("Hesap bulunamadı: " + hesapNo));
        
        return new BakiyeResponse(hesap.getHesapNo(), hesap.getBakiye());
    }

    public BakiyeResponse paraCek(ParaCekRequest request) {
        Hesap hesap = hesapRepository.findByHesapNo(request.getHesapNo())
                .orElseThrow(() -> new HesapBulunamadiException("Hesap bulunamadı: " + request.getHesapNo()));

        // 1. PIN Kontrolü
        if (!hesap.getPin().equals(request.getPin())) {
            throw new GecersizPinException("Geçersiz PIN.");
        }

        // 2. Bakiye Kontrolü
        if (hesap.getBakiye() < request.getMiktar()) {
            throw new YetersizBakiyeException("Yetersiz bakiye. Mevcut bakiye: " + hesap.getBakiye());
        }
        
        if(request.getMiktar() <= 0){
             throw new IllegalArgumentException("Çekilecek miktar pozitif olmalıdır.");
        }

        // 3. İşlem
        hesap.setBakiye(hesap.getBakiye() - request.getMiktar());
        hesapRepository.save(hesap);

        return new BakiyeResponse(hesap.getHesapNo(), hesap.getBakiye());
    }

    public BakiyeResponse paraYatir(ParaYatirRequest request) {
        Hesap hesap = hesapRepository.findByHesapNo(request.getHesapNo())
                .orElseThrow(() -> new HesapBulunamadiException("Hesap bulunamadı: " + request.getHesapNo()));

        if(request.getMiktar() <= 0){
             throw new IllegalArgumentException("Yatırılacak miktar pozitif olmalıdır.");
        }
        
        // İşlem
        hesap.setBakiye(hesap.getBakiye() + request.getMiktar());
        hesapRepository.save(hesap);

        return new BakiyeResponse(hesap.getHesapNo(), hesap.getBakiye());
    }
}