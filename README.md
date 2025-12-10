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