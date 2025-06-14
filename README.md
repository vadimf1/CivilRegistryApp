# Civil Registry Application

## Описание проекта
Проект представляет собой автоматизированное тестирование веб-приложения для работы с гражданским реестром. Приложение позволяет регистрировать различные гражданские акты (рождение, брак, смерть) и управлять заявками на регистрацию.

## Технологии и инструменты
- Java 17
- Gradle 8.5
- JUnit 5.12.2
- Selenide 7.7.3
- REST Assured 5.4.0
- Allure Reports 2.28.1
- Cucumber 7.20.1
- PostgreSQL 42.7.3
- Lombok 1.18.36
- SLF4J 2.0.17
- Logback 1.5.16
- Jackson 2.19.0
- JavaFaker 1.0.2

## Структура проекта
```
src/
├── main/
│   ├── java/
│   │   └── eu/senla/regoffice/
│   │       ├── api/           # API клиенты и спецификации
│   │       ├── constants/     # Константы проекта
│   │       ├── db/           # Работа с базой данных
│   │       ├── factory/      # Фабрики для создания объектов
│   │       ├── models/       # Модели данных
│   │       ├── ui/           # UI компоненты
│   │       │   ├── config/   # Конфигурация UI тестов
│   │       │   └── pages/    # Page Objects
│   │       └── utils/        # Утилитные классы
│   └── resources/
└── test/
    ├── java/
    │   └── eu/senla/regoffice/
    │       ├── api/         # API тесты
    │       └── ui/          # UI тесты
    └── resources/
        ├── features/        # Cucumber feature файлы
```

## Требования для запуска
- JDK 17 или выше
- Gradle 8.5 или выше
- Браузер (Chrome, Firefox или другой)
- PostgreSQL

## Параметры запуска
Проект поддерживает следующие параметры запуска, которые можно передать через командную строку:

```
# Параметры авторизации в приложении (обязательные)
-Dapp.login=app_login
-Dapp.password=app_password

# Параметры подключения к базе данных (обязательные)
-Ddb.login=db_login
-Ddb.password=db_password

# Параметры браузера (обязательные)
-Dbrowser.size=1920x1080
-Dbrowser.type=chrome
```

## Альтернативный способ передачи параметров
Параметры можно также задать в файле gradle.properties, который должен находиться в корне проекта
Пример содержимого файла:

```
systemProp.app.login=app_login
systemProp.app.password=app_password
systemProp.db.login=db_login
systemProp.db.password=db_password
systemProp.browser.size=1920x1080
systemProp.browser.type=chrome
```

## Запуск тестов
Для запуска всех тестов:
```bash
./gradlew test \
  -Dapp.login=app_login \
  -Dapp.password=app_password \
  -Ddb.login=db_login \
  -Ddb.password=db_password \
  -Dbrowser.size=1920x1080 \
  -Dbrowser.type=chrome
```

Для запуска UI тестов:
```bash
./gradlew testUi \
  -Dapp.login=app_login \
  -Dapp.password=app_password \
  -Ddb.login=db_login \
  -Ddb.password=db_password \
  -Dbrowser.size=1920x1080 \
  -Dbrowser.type=chrome
```

Для запуска API тестов:
```bash
./gradlew testApi \
  -Dapp.login=app_login \
  -Dapp.password=app_password \
  -Ddb.login=db_login \
  -Ddb.password=db_password \
  -Dbrowser.size=1920x1080 \
  -Dbrowser.type=chrome
```

## Генерация отчета Allure
После выполнения тестов для генерации отчета выполните:
```bash
./gradlew allureReport
./gradlew allureServe
```

## Основной функционал
1. Регистрация гражданских актов:
   - Регистрация рождения
   - Регистрация брака
   - Регистрация смерти

2. Управление заявками:
   - Создание заявок
   - Просмотр статуса заявок
   - Одобрение/отклонение заявок (для администратора)

3. Управление пользователями:
   - Регистрация пользователей
   - Регистрация администраторов
   - Авторизация в системе

## Особенности реализации
- Page Object Pattern для UI тестов
- REST Assured для API тестов
- Cucumber для BDD тестирования
- Allure для генерации отчетов
- Логирование с использованием SLF4J и Logback
- Генерация тестовых данных с помощью JavaFaker
- Работа с базой данных PostgreSQL
