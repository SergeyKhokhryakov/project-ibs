package ru.ostrovcy.ibs.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class VacanciesPage {
  // Локаторы
  private String locatorSelfPage = "/career/jobs";
  private String locatorCookies = ".cookies-right";
  private String locatorGoTo = "div[@class=\"breadcrumbs\"]/a";
  private String locatorGoToBreadCrumbs = "//" + locatorGoTo;
  private String locatorTitlePage = ".top h1";
  private String locatorWithExperience = "input[type=\"checkbox\"][name=\"jobsFilter_299_3824175964\"]";
  private String locatorOnlineFormatJob = "input[type=\"checkbox\"][name=\"jobsFilter_209_2418033469\"]";
  private String locatorHeader = "div[@class=\"jobs-filter-block-header\"]";
  private String locatorDirectionTesting = "input[type=\"checkbox\"][name=\"jobsFilter_210_1267874983\"]";
  private String locatorTechnologyJava = "input[type=\"checkbox\"][name=\"jobsFilter_237_1586743293\"]";
  private String locatorTechnologySelenium = "input[type=\"checkbox\"][name=\"jobsFilter_237_2649019226\"]";
  private String locatorShowAll = "div[@class=\"jobs-filter-block-content\"]/a";
  private String locatorCheckBox = "span";
  private String locatorTitleListJob = "#list h2";
  private String locatorActiveTag = "div[contains(@class,'tags--filter')]/a[contains(@class,'is-active')]";
  private String locatorTags = ".tags--filter";
  private String locatorMore = ".pics-slide__more";
  private String locatorFilterCount = ".jobs-filter-count";

  // Заголовки
  private String main = "Главная";
  private String career = "Карьера";
  private String title = "Вакансии";
  private String filterDirection = "Направление";
  private String filterTechnology = "Технологии";
  private String onLineTag = "Удаленно";
  private String testingTag = "Тестирование";
  private String expectedResult = "3 вакансии";

  // Состояния
  private String prevResultFilter;
  private int filterCount;


  public VacanciesPage directlyOpenPage(){
    Selenide.open(locatorSelfPage);
    return this;
  }

  @Step("Проверить заголовок страницы")
  public VacanciesPage verifyTitle() {
    $(locatorTitlePage).shouldHave(text(title));
    return this;
  }

  @Step("Поиск вакансий по заданному критерию: Опыт(С опытом), Формат работы(Удаленно), Направление(Тестирование), Технологии(Java/Selenium)")
  public VacanciesPage searchVacanciesByFilter() {
    filterCount = 0;
    // Принять условия
    $(locatorCookies).click();
    step("Установить фильтр", () -> {
      setUpFilter();
    });

    return this;
  }

  private void setUpFilter() {
    // Опыт
//    prevResultFilter = $(locatorTitleListJob).text();
    step("Опыт - С опытом", () -> {
      setCheckBox(locatorWithExperience);
    });
    filterCount++;
//    $(locatorTitleListJob).shouldNotHave(text(prevResultFilter));

    // Формат работы
    step("Формат работы - Удаленно", () -> {
      setCheckBox(locatorOnlineFormatJob);
    });
    filterCount++;

    // Направление
    step("Направление - Тестирование", () -> {
      $(byTagAndText(locatorHeader, filterDirection)).click();
      setCheckBox(locatorDirectionTesting);
    });
    filterCount++;

    // Город

    // Технологии
    step("Технологии - Java", () -> {
      $(byTagAndText(locatorHeader, filterTechnology)).click();
      $(byTagAndText(locatorHeader, filterTechnology)).parent().$x(locatorShowAll).scrollIntoView(false).click();
      setCheckBox(locatorTechnologyJava);
    });
    filterCount++;

    step("Технологии - Selenium", () -> {
      setCheckBox(locatorTechnologySelenium);
    });
    filterCount++;
  }

  private void setCheckBox(String locator) {
    $(locator).scrollIntoView(false);
    $(locator).parent().$(locatorCheckBox).click();
    $(locator).shouldBe(checked);
  }

  public VacanciesPage verifySearchResult() {
    step("Проверка счетчика фильтра, активных тегов, результата поиска", () -> {
      $(locatorMore).scrollIntoView(true);
      $(locatorFilterCount).shouldHave(text(String.valueOf(filterCount)));
      $(byTagAndText(locatorActiveTag, onLineTag)).shouldBe(exist);
      $(byTagAndText(locatorActiveTag, testingTag)).shouldBe(exist);
      $(locatorTitleListJob).shouldHave(text(expectedResult));
    });

    return this;
  }
  public void goToMainPage(){
    $x(locatorGoToBreadCrumbs).scrollIntoView(true);
    $(byTagAndText(locatorGoTo, main)).pressEnter();
  }


}
