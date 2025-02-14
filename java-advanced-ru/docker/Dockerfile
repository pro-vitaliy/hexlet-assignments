# 1. Используем базовый образ с JDK 20
FROM eclipse-temurin:20-jdk

# 2. Устанавливаем Gradle
ARG GRADLE_VERSION=8.2

RUN apt-get update && apt-get install -yq make unzip wget \
    && wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip \
    && rm gradle-${GRADLE_VERSION}-bin.zip \
    && mv gradle-${GRADLE_VERSION} /opt/gradle

ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:$GRADLE_HOME/bin

# 3. Создаем рабочую директорию и копируем файлы
WORKDIR /app
COPY app /app

# 4. Собираем приложение
RUN gradle build --no-daemon

# 5. Открываем порт 8080
EXPOSE 8080

# 6. Запускаем приложение
CMD ["java", "-jar", "build/libs/simple-application.jar"]
