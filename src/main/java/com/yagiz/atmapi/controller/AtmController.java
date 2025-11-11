package com.yagiz.atmapi.controller;

import com.yagiz.atmapi.dto.BakiyeResponse;
import com.yagiz.atmapi.dto.ParaCekRequest;
import com.yagiz.atmapi.dto.ParaYatirRequest;
import com.yagiz.atmapi.service.AtmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm") // Bütün endpoint'ler /api/atm ile başlasın
@Tag(name = "ATM API Servisi", description = "Para Çekme, Yatırma ve Bakiye İşlemleri")
public class AtmController {

    @Autowired
    private AtmService atmService;

    @Operation(summary = "Bakiye Sorgulama", description = "Hesap numarasına göre güncel bakiye bilgisini döndürür.")
    @GetMapping("/bakiye/{hesapNo}")
    public ResponseEntity<BakiyeResponse> bakiyeSorgula(@PathVariable String hesapNo) {
        return ResponseEntity.ok(atmService.getBakiye(hesapNo));
    }

    @Operation(summary = "Para Çekme", description = "Hesap no, PIN ve miktar ile para çekme işlemi yapar.")
    @PostMapping("/para-cek")
    public ResponseEntity<BakiyeResponse> paraCek(@RequestBody ParaCekRequest request) {
        return ResponseEntity.ok(atmService.paraCek(request));
    }

    @Operation(summary = "Para Yatırma", description = "Hesap no ve miktar ile para yatırma işlemi yapar.")
    @PostMapping("/para-yatir")
    public ResponseEntity<BakiyeResponse> paraYatir(@RequestBody ParaYatirRequest request) {
        return ResponseEntity.ok(atmService.paraYatir(request));
    }
}