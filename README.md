# ATM API Projesi

Bu proje, Spring Boot ve Java kullanılarak geliştirilmiş bir REST API servisidir.
Para çekme, yatırma ve bakiye sorgulama işlemlerini simüle eder.



1. Projeyi derlemek ve paketlemek için:
   ```bash
   mvn clean package
2.Projeyi çalıştırmak için:
   java -jar target/atmapi-0.0.1-SNAPSHOT.jar

   Swagger Dokümantasyonu 
Proje başarıyla çalıştıktan sonra (Tomcat started on port(s): 8080 mesajı görüldüğünde), interaktif Swagger dokümantasyonuna erişmek için tarayıcınızdan aşağıdaki adresi açmalıyız:
http://localhost:8080/swagger-ui.html

Docker ile Çalıştırma

Bu proje, Docker ve Docker Compose ile container olarak çalıştırılabilir.

1. Docker İmajı Oluşturma:
Projenin ana dizininde (Dockerfile'ın olduğu yerde) şu komutu çalıştırın:

docker build -t atmapi:latest .


2. Docker Compose ile Başlatma :
Projeyi docker-compose.yml dosyasını kullanarak başlatmak en kolay yoldur. Bu, imajı otomatik olarak inşa eder ve çalıştırır.

docker-compose up -d


Uygulama http://localhost:8080/swagger-ui.html adresinde yayın yapmaya başlayacaktır.

Servisi durdurmak için:

docker-compose down


Swagger Dokümantasyonu 

Proje başarıyla çalıştıktan sonra (Tomcat started on port(s): 8080 mesajı görüldüğünde),
interaktif Swagger dokümantasyonuna erişmek için tarayıcınızdan aşağıdaki adresi açın:

http://localhost:8080/swagger-ui.html


10 Aralık Ödevi

frontend için birinci portu açıp localhost:80 portunu kullanıyoruz
Hesap No: 11112222
PIN: 1234
backend testi için http://localhost:8080/swagger-ui/index.html adresini kullanıyoruz
login kısmına geliyoruz 
{
  "hesapNo": "11112222",
  "pin": "1234"
}

authorizelıyoruz 
value kısmına loginden aldığımız tokenı giriyoruz

sonrasında bakiyeden 11112222 hesap nosuyla giriyoruz.


MERMAIDJS AKIS DIYAGRAMI KODU (PARA CEKME)
sequenceDiagram
    participant Kullanici
    participant Frontend
    participant TokenFilter
    participant AtmController
    participant AtmService
    participant Veritabani

    Note over Kullanici, Veritabani: 1. Giriş İşlemi
    Kullanici->>Frontend: Hesap No ve PIN girer
    Frontend->>AtmController: POST /login (HesapNo, PIN)
    AtmController->>Veritabani: Hesap Doğrula
    Veritabani-->>AtmController: Hesap Bilgisi
    AtmController-->>Frontend: 200 OK + Bearer Token (UUID)

    Note over Kullanici, Veritabani: 2. Para Çekme İşlemi
    Kullanici->>Frontend: Para Çek (Miktar: 100)
    Frontend->>TokenFilter: POST /para-cek (Header: Bearer Token)
    TokenFilter->>TokenFilter: Token Geçerli mi?
    TokenFilter->>AtmController: İsteği İlet
    AtmController->>AtmService: Bakiye Kontrol ve Düşme
    AtmService->>Veritabani: UPDATE Hesap SET Bakiye...
    Veritabani-->>AtmService: İşlem Tamam
    AtmService-->>Frontend: Yeni Bakiye Dön
    Frontend-->>Kullanici: "İşlem Başarılı" Mesajı

    AI DESTEKLI GUVENLIK VE IYILESTIRME RAPORU

    Proje yapay zeka tarafından analiz edilmiş ve aşağıdaki 5 güvenlik/iyileştirme önerisi sunulmuştur:

Şifreleme (Hashing): Mevcut sistemde PIN kodları veritabanında açık metin (plain-text) olarak saklanmaktadır. Güvenlik için BCrypt veya Argon2 ile hashlenmelidir.

HTTPS Kullanımı: Token ve kimlik bilgilerinin ağ üzerinde çalınmasını önlemek için iletişim SSL/TLS (HTTPS) üzerinden yapılmalıdır.

Token Süresi (Expiration): Şu an kullanılan UUID tokenlar sonsuza kadar geçerli kalmaktadır. Bunun yerine belirli bir süresi olan (örn: 15 dk) JWT (JSON Web Token) standardına geçilmelidir.

Rate Limiting: /login endpointine yapılabilecek deneme sayısı sınırlandırılmalıdır (Örn: Dakikada 5 deneme). Bu, Brute-Force saldırılarını engeller.

Environment Variables: Veritabanı şifreleri kod içinde veya application.properties dosyasında açıkça yazılmamalı, Docker .env dosyası üzerinden gizli değişken olarak alınmalıdır.

# ATM API Projesi

Bu proje, Spring Boot, Java ve PostgreSQL kullanılarak geliştirilmiş, Dockerize edilmiş güvenli bir REST API servisidir.
JWT (Token) tabanlı kimlik doğrulama sistemi içerir.

 Kurulum ve Çalıştırma (Docker ile)

Proje 'docker-compose' ile tek komutla ayağa kalkacak şekilde tasarlanmıştır.

1. Terminali açın ve proje dizinine gelin.
2. Şu komutu çalıştırın:
   ```bash
   docker-compose up -d --build

   Konteynerler ayağa kalktıktan sonra:

Frontend Arayüzü: http://localhost:80

Swagger Dokümantasyonu: http://localhost:8080/swagger-ui.html

Backend API: http://localhost:8080/api/atm

 Test Hesap Bilgileri
Sistemi test etmek için veritabanında hazır gelen şu hesabı kullanabilirsiniz:

Hesap No: 11112222

PIN: 1234

Bakiye: 1500.75 TL