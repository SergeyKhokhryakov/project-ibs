package ru.ostrovcy.ibs.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

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
  private String locatorMore = ".pics-slide__more";
  private String locatorFilterCount = ".jobs-filter-count";

  // Локаторы фильтра
  private String locatorResetButton = ".jobs-filter-header .js-filter-clear";
  private String locatorWithExperience = "input[type=\"checkbox\"][name=\"jobsFilter_299_3824175964\"]";
  private String locatorAtOfficeFormatJob = "input[type=\"checkbox\"][name=\"jobsFilter_209_2216188470\"]";
  private String locatorOnlineFormatJob = "input[type=\"checkbox\"][name=\"jobsFilter_209_2418033469\"]";
  private String locatorHeader = "div[@class=\"jobs-filter-block-header\"]";
  private String locatorDevelopmentDirection = "input[type=\"checkbox\"][name=\"jobsFilter_210_3258323063\"]";
  private String locatorBusinessAndSystemAnalyticDirection = "input[type=\"checkbox\"][name=\"jobsFilter_210_3039895777\"]";
  private String locatorSAPDirection = "input[type=\"checkbox\"][name=\"jobsFilter_210_1016400945\"]";
  private String locator1CDirection = "input[type=\"checkbox\"][name=\"jobsFilter_210_2778488203\"]";
  private String locatorTestingDirection = "input[type=\"checkbox\"][name=\"jobsFilter_210_1267874983\"]";
  private String locatorTechnologyJava = "input[type=\"checkbox\"][name=\"jobsFilter_237_1586743293\"]";
  private String locatorTechnologySelenium = "input[type=\"checkbox\"][name=\"jobsFilter_237_2649019226\"]";
  private String locatorShowAll = "div[@class=\"jobs-filter-block-content\"]/a";
  private String locatorCheckBox = "span";
  private String locatorTitleListJob = "#list h2";

  // Локаторы тегов
  private String locatorActiveTag = "div[contains(@class,'tags--filter')]/a[contains(@class,'is-active')]";
  private String locatorTags = ".tags--filter";
  private String locatorAtOfficeTag = ".tags--filter a[data-jobs-tags-link='office']";
  private String locatorOnlineTag = ".tags--filter a[data-jobs-tags-link='online']";
  private String locatorDevelopmentTag = ".tags--filter a[data-jobs-tags-link='razrabotka']";
  private String locatorBusinessAndSystemAnalyticTag = ".tags--filter a[data-jobs-tags-link='biznes-i-sistemnaya-analitika']";
  private String locatorTestingTag = ".tags--filter a[data-jobs-tags-link='testirovanie']";
  private String locatorSAPTag = ".tags--filter a[data-jobs-tags-link='sap-konsultanty']";
  private String locator1CTag = ".tags--filter a[data-jobs-tags-link='1s-konsultanty-razrabotchiki']";

  // Заголовки
  private String main = "Главная";
  private String career = "Карьера";
  private String title = "Вакансии";
  private String filterDirection = "Направление";
  private String filterTechnology = "Технологии";
  private String onLineTag = "Удаленно";
  private String testingTag = "Тестирование";
  private String businessAndSystemAnalyticTag = "Бизнес- и системная аналитика";
  private String sapTag = "SAP";
  private String oneSTag = "1С";
  private String atOfficeTag = "В офисе";
  private String developmentTag = "Разработка";
  private String expectedResult = "3 вакансии";

  // Состояния
  private String prevResultFilter;
  private int filterCount;

@Step("Открыть страницу \"Вакансии\"")
  public VacanciesPage directlyOpenPage() {
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
//    $(locatorCookies).click();
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
      setCheckBox(locatorTestingDirection);
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

  @Step("Вернуться на главную страницу")
  public void goToMainPage() {
    $x(locatorGoToBreadCrumbs).scrollIntoView(true);
    $(byTagAndText(locatorGoTo, main)).pressEnter();
  }

  @Step("Поочередно установить каждый тег и проверить результат")
  public VacanciesPage setUpTagsAndVerifyResults() {

    filterCount = 0;
    step("Установить тег \"" + atOfficeTag +"\"", () -> {
      setUpTag(locatorAtOfficeTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(atOfficeTag, locatorAtOfficeFormatJob);
    });

    step("Установить тег \"" + onLineTag +"\"", () -> {
      setUpTag(locatorOnlineTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(onLineTag, locatorOnlineFormatJob);
    });


    step("Установить тег \"" + developmentTag + "\"", () -> {
      setUpTag(locatorDevelopmentTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(developmentTag, locatorDevelopmentDirection);
    });


    step("Установить тег \"" + businessAndSystemAnalyticTag + "\"", () -> {
      setUpTag(locatorBusinessAndSystemAnalyticTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(businessAndSystemAnalyticTag, locatorBusinessAndSystemAnalyticDirection);
    });


    step("Установить тег \"" + testingTag + "\"", () -> {
      setUpTag(locatorTestingTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(testingTag, locatorTestingDirection);
    });


    step("Установить тег \"" + sapTag + "\"", () -> {
      setUpTag(locatorSAPTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(sapTag, locatorSAPDirection);
    });


    step("Установить тег \"" + oneSTag + "\"" , () -> {
      setUpTag(locator1CTag);
    });
    step("Проверить - тег активный, наименование тега в верхнем регистре, цвет текста/фона, счетчик фильтра, в наличии кнопка \"Сбросить\"", () -> {
      complexVerify(oneSTag, locator1CDirection);
    });

    return this;
  }

  private void complexVerify(String atOfficeTag, String locatorAtOfficeFormatJob) {
    verifyTag(atOfficeTag);
    verifyFilterCheckbox(locatorAtOfficeFormatJob);
    filterCount++;
    verifyFilterCount();
    verifyResetButton();
}

  public VacanciesPage setUpTag(String locator) {
    $(locator).pressEnter();
    return this;
  }

  public VacanciesPage verifyFilterCheckbox(String locator) {
    // развернуть блок  "Направление"
    if (!(locator.equals(locatorAtOfficeFormatJob) || locator.equals(locatorOnlineFormatJob))) {
      $(byTagAndText(locatorHeader, filterDirection)).click();
    }
    $(locator).scrollIntoView(false);
    $(locator).shouldBe(checked);
    return this;
  }

  public VacanciesPage verifyTag(String name) {
    // существует, в верхнем регистре
    $(byTagAndText(locatorActiveTag, name))
            .shouldBe(exist)
            .shouldHave(textCaseSensitive(name.toUpperCase()));
    // цвет текста - белый, на черном фоне
    $(byTagAndText(locatorActiveTag, name))
            .shouldHave(cssValue("color", "rgba(255, 255, 255, 1)")
                    , cssValue("background-color", "rgba(0, 0, 0, 1)"));
    return this;
  }

  public VacanciesPage verifyFilterCount() {
    $(locatorMore).scrollIntoView(true);
    $(locatorFilterCount).shouldHave(text(String.valueOf(filterCount)));
    return this;
  }

  public VacanciesPage verifyResetButton(){
  $(locatorResetButton).shouldBe(exist);
  return this;
  }


}
