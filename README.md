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
- Docker

## Структура проекта
```
src/
├── main/
│   ├── java/
│   │   └── org/example/
│   │       ├── api/          # API клиенты и спецификации
│   │       ├── db/           # Работа с базой данных
│   │       ├── models/       # Модели данных
│   │       ├── ui/           # UI компоненты
│   │       │   └── config/   # Конфигурация UI тестов
│   │       │   └── pages/    # Page Objects
│   │       │            
│   │       └── utils/        # Утилитные классы
│   └── resources/
└── test/
    ├── java/
    │   └── org/example/
    │       ├── api/         # API тесты
    │       └── ui/          # UI тесты
    └── resources/
```

## Требования для запуска
- Docker
- Bash shell

## Параметры окружения Docker

Проект требует следующие переменные окружения, которые необходимо передать в контейнер при запуске:

```
# Название задачи Gradle
# GRADLE_TASK может принимать значения:
# test – для запуска всех типов тестов
# testUi – для запуска UI тестов
# testApi – для запуска API тестов
GRADLE_TASK=testUi

# Параметры подключения к базе данных
DB_LOGIN=db_login
DB_PASSWORD=db_password

# Параметры авторизации в приложении
APP_LOGIN=app_login
APP_PASSWORD=app_password

# Параметры браузера
# BROWSER_TYPE может принимать значения:
# chrome, firefox, opera
BROWSER_TYPE=chrome
BROWSER_SIZE=1920x1080
```

## Запуск тестов
1. Сборка Docker образа:
```bash
docker build -t civil-registry-app .
```

2. Запуск тестов через скрипт run-tests.sh:
```bash
GRADLE_TASK=testUi \
DB_LOGIN=db_login \
DB_PASSWORD=db_password \
APP_LOGIN=app_login \
APP_PASSWORD=app_password \
BROWSER_TYPE=chrome \
BROWSER_SIZE=1920x1080 \
/bin/bash run-tests.sh
```

## Результаты выполнения тестов
1. Видео-записи тестов сохраняются в директории
`report/video`, расположенной в корне проекта.
2. Результаты Allure-тестов сохраняются в
`report/allure-results`, также в корне проекта.

## Генерация отчета Allure
После выполнения тестов для генерации отчета выполните:
```bash
allure serve ./report/allure-results
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
- Запуск тестов в Docker контейнере
