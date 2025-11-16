# --- Aşama 1: Build Aşaması ---
# Projeyi derlemek için Java 17 ve Maven içeren bir "builder" imajı kullanıyoruz.
FROM maven:3.9-eclipse-temurin-17 AS builder

# Container içinde /app adında bir çalışma dizini oluştur
WORKDIR /app

# Önce pom.xml'i kopyala (Docker katman önbelleğinden yararlanmak için)
COPY pom.xml .
# Bağımlılıkları indir
RUN mvn dependency:go-offline

# Geri kalan tüm kaynak kodunu kopyala
COPY src ./src

# Projeyi paketle (testleri atlayarak)
# Bu komut 'target/atmapi-0.0.1-SNAPSHOT.jar' dosyasını oluşturacak
RUN mvn clean package -DskipTests

# --- Aşama 2: Çalıştırma Aşaması ---
# Projeyi çalıştırmak için çok daha küçük bir Java 17 JRE imajı kullanıyoruz.
FROM eclipse-temurin:17-jre-jammy

# Container içinde /app adında bir çalışma dizini oluştur
WORKDIR /app

# 1. Aşamada (builder) oluşturduğumuz .jar dosyasını bu imaja kopyala
COPY --from=builder /app/target/atmapi-0.0.1-SNAPSHOT.jar app.jar

# Uygulamanın 8080 portunda çalışacağını belirt
EXPOSE 8080

# Container çalıştığında .jar dosyasını çalıştıracak olan komut
ENTRYPOINT ["java", "-jar", "app.jar"]