# Проект по автоматизации тестирования сайта IBS
<p align="center">
  <a href="https://ibs.ru/">
  <img src="media/screen/logo.png" alt="IBS" width="150">
  </a>
</p>

##	Содержание

- [Проект по автоматизации тестирования сайта IBS](#проект-по-автоматизации-тестирования-сайта-itpelag)
  - [Содержание](#содержание)
  - [Используемый стек технологий](#Используемый-стек-технологий)
  - [Реализованные проверки](#реализованные-проверки)
  - [Запуск тестов из терминала](#запуск-тестов-из-терминала)
    - [Локальный запуск тестов](#локальный-запуск-тестов)
    - [Удаленный запуск тестов](#удаленный-запуск-тестов)
    - [Параметры запуска](#параметры-запуска)
  - [Запуск тестов в Jenkins](#запуск-тестов-в-jenkins)
      - [Главная страница Jenkins](#главная-страница-jenkins)
      - [Настройка параметров](#настройка-параметров)
      - [Значок Allure Report](#значок-allure-report)
  - [Отчет о результатах тестирования в Allure Report](#отчет-о-результатах-тестирования-в-allure-report)
      - [Главная страница Allure Report](#главная-страница-allure-report)
      - [Тесты](#тесты)
      - [Графики](#графики)
  - [Интеграция с Allure TestOps](#интеграция-с-allure-testops)
      - [Ход выполнения теста](#ход-выполнения-теста)
      - [Тест-кейсы](#тест-кейсы)
      - [Дашборды](#дашборды)
  - [Интеграция с Jira](#интеграция-с-jira)
      - [Задача в Jira](#задача-в-jira)
  - [Уведомления в Telegram с использованием бота](#уведомления-в-telegram-с-использованием-бота)
      - [Оповещение о результатах сборки](#оповещение-о-результатах-сборки)
  - [Пример запуска теста в Selenoid](#пример-запуска-теста-в-selenoid)

## Используемый стек технологий

<p  align="center">

<img width="5%" title="GitHub" src="media/icons/GitHub.svg">
<img width="5%" title="IntelliJ IDEA" src="media/icons/Idea.svg">
<img width="5%" title="Java" src="media/icons/Java.svg">
<img width="5%" title="Selenide" src="media/icons/Selenide.svg">
<img width="5%" title="Gradle" src="media/icons/Gradle.svg">
<img width="5%" title="Junit5" src="media/icons/Junit5.svg">
<img width="5%" title="Allure Report" src="media/icons/Allure.svg">
<img width="5%" title="Jenkins" src="media/icons/Jenkins.svg">
<img width="5%" title="Selenoid" src="media/icons/Selenoid.svg">
<img width="5%" title="Allure Testops" src="media/icons/Allure_TO.svg">
<img width="5%" title="Jira" src="media/icons/Jira.svg">
<img width="5%" title="Telegram" src="media/icons/Telegram.svg">
</p>

## Реализованные проверки

- [x] *Проверка рубрик в навигации сайта*
- [x] *Проверка подрубрик в навигации сайта*
- [x] *Проверка функционирования тегов фильтра поиска вакансий*
- [x] *Проверка поиска вакансий по заданным критериям*

## Запуск тестов из терминала

### Локальный запуск тестов

```bash
gradle clean test
```

### Удаленный запуск тестов

```bash
gradle clean ${TASK}

-DisRemote=${ISREMOTE} 
-Denv=${ENV}
-Dauth=${AUTH}
```

### Параметры запуска
<code>TASK</code> – выбор тестов для запуска (_варианты: <code>test</code>, <code>positive</code>, <code>smoke</code>, <code>menu</code>, <code>vacancies</code>, <code>tags</code>_).

<code>isRemote</code> – удаленный или локальный запуск (_по умолчанию - <code>удаленный</code>_).

<code>env</code> – окружение (_по умолчанию - <code>удаленное: браузер chrome, версия 99.0, размер окна браузера 1920x1080</code>_).

<code>auth</code> – авторизация для удаленного запуска (_по умолчанию - <code>выполнена</code>_).

## Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/021-SergeyAQA-IBS/)

#### Главная страница Jenkins

<p align="center">
  <img src="media/screen/jenkins_mainpage.png" alt="Jenkins" width="800">
</p>

Для запуска сборки необходимо указать значения параметров и нажать кнопку <code><strong>*Собрать*</strong></code>.

#### Настройка параметров

<p align="center">
  <img src="media/screen/jenkins_param.png" alt="jenkins_param" width="800">
</p>

Результаты сборки можно посмотреть в Allure отчёте, кликнув на значок <code><strong>*Allure Report*</strong></code>.

#### Значок Allure Report

<p align="center">
  <img src="media/screen/jenkins_allure.png" alt="jenkins_allure" width="800">
</p>

## Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/021-SergeyAQA-IBS/allure/)

#### Главная страница Allure Report

<p align="center">
  <img src="media/screen/allure_main.png" alt="allure_main" width="800">
</p>

#### Тесты

<p align="center">
  <img src="media/screen/allure_tests.png" alt="allure_tests" width="800">
</p>

#### Графики

<p align="center">
  <img src="media/screen/allure_graphs.png" alt="allure_graphs" width="800">
</p>

## Интеграция с [Allure Testops](https://allure.autotests.cloud/project/4006/launches)

В <code><strong>*Allure Testops*</strong></code> есть возможность наблюдать за выполнением тестов в реальном времени.

#### Ход выполнения теста

<p align="center">
  <img src="media/screen/testops_launches.png" alt="testops_launches" width="800">
</p>

#### Тест-кейсы

<p align="center">
  <img src="media/screen/testops_tests.png" alt="testops_tests" width="800">
</p>

#### Дашборды

<p align="center">
  <img src="media/screen/allure_dashboards.png" alt="dashboards" width="800">
</p>

## Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-1088)

#### Задача в Jira

<p align="center">
  <img src="media/screen/jira.png" alt="jira" width="800">
</p>

## Уведомления в Telegram с использованием бота

#### Оповещение о результатах сборки

<p align="center">
  <img src="media/screen/telegram.png" alt="telegram" width="400">
</p>

## Пример запуска теста в Selenoid

К каждому тесту в отчете прилагается видео.

На данном видео выполняется:

- Проверка подрубрик в навигации сайта:

<p align="center">
  <img title="Selenoid Video" src="media/gif/video_subrubrics.gif">
</p>

- Проверка функционирования тегов фильтра поиска вакансий:

<p align="center">
  <img title="Selenoid Video" src="media/gif/video_tags.gif">
</p>

- Проверка поиска вакансий по заданным критериям:

<p align="center">
  <img title="Selenoid Video" src="media/gif/video_vacancies.gif">
</p>