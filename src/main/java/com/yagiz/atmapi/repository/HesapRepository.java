package com.yagiz.atmapi.repository;

import com.yagiz.atmapi.model.Hesap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HesapRepository extends JpaRepository<Hesap, String> {
    // JpaRepository sayesinde save, findAll gibi metodlar hazır gelir.
    // Özel olarak sadece hesap numarasına göre bulma metodunu tanımlıyoruz:
    Optional<Hesap> findByHesapNo(String hesapNo);
}