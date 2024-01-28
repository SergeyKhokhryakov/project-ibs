package ru.ostrovcy.ibs.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class NavigationPage {

  private String locatorRubrics = ".navigation-sections a";
  private String locatorNavigationListTitle = "div[contains(@class, 'navigation-list')]/div[@class='h2']";
  private String locatorSubRubrics = ".navigation-list.is-active.is-selected a";
  private String locatorSubRubricsXPath = "div[@class='navigation-list js-scrollbar is-active is-selected']/a";
  private String locatorClosePage = ".navigation-close";
  private static int index;

  // страницы подрубрик
  VacanciesPage vacanciesPage = new VacanciesPage();

  // Структура меню
  List<String> rubricsOracle = List.of(
          "Решения и услуги",
          "Отраслевые решения",
          "Проекты",
          "Создано в IBS",
          "Карьера",
          "Медиацентр",
          "О компании");

  private List<Map<String, Boolean>> subRubricsOracle = new ArrayList<>();
//          = List.of(
//          Map.of(
//                  "Управление программами", false,
//                  "Бизнес-консалтинг", true,
//                  "Аналитические решения", true,
//                  "Бизнес-решения", true,
//                  "Разработка", true,
//                  "Тестирование", true,
//                  "Аутсорсинг ИТ-процессов", true,
//                  "Кибербезопасность", false,
//                  "ESG и устойчивое развитие", false
//          ),
//          Map.of(
//                  "Агропромышленность", false,
//                  "Газовая индустрия", false,
//                  "Государственные программы", false,
//                  "Металлургия", false,
//                  "Нефть и химия", false,
//                  "Промышленность и транспорт", false,
//                  "Телеком", false,
//                  "ТНП и ритейл", false,
//                  "Финансовые институты", false,
//                  "Энергетика и ЖКХ", false
//                  ),
//          Map.of(
//                  "Платформа Планета", true,
//                  "IBS НСИ", true,
//                  "IBS EAM", true,
//                  "Комплексная система весогабаритного контроля", false,
//                  "GlobalCareer — IT Рекрутинговое агентство", true,
//                  "ТехноДром — Технологии создания команд", true,
//                  "Реестр российского ПО и свидетельства в Роспатент", false
//          ),
//          Map.of(
//                  "Карьера в IBS", false,
//                  "Вакансии", false,
//                  "Стажировки", true,
//                  "IBS Training Center", true,
//                  "Как стать частью команды IBS", false,
//                  "Полезные материалы для соискателей", false,
//                  "Наша жизнь", false
//          ),
//          Map.of(
//                  "Новости компании", false,
//                  "События", false,
//                  "Материалы для СМИ", false
//          ),
//          Map.of(
//                  "О компании", false,
//                  "Менеджмент", false,
//                  "История IBS", false,
//                  "Признание", false,
//                  "Партнёры", false,
//                  "Раскрытие информации", false,
//                  "Контакты", false
//          )
//  );

  Map<String, Boolean> officesOfIBS = Map.of(
          "Москва", false,
          "Нижний Новгород", false,
          "Омск", false,
          "Пенза", false,
          "Пермь", false,
          "Санкт-Петербург", false,
          "Таганрог", true,
          "Ульяновск", false,
          "Уфа", false
  );

  NavigationPage() {
    Map<String, Boolean> map = new LinkedHashMap<>();
//    "Решения и услуги"
    map.put("Управление программами", false);
    map.put("Бизнес-консалтинг", true);
    map.put("Аналитические решения", true);
    map.put("Бизнес-решения", true);
    map.put("Разработка", true);
    map.put("Тестирование", true);
    map.put("Аутсорсинг ИТ-процессов", true);
    map.put("Кибербезопасность", false);
    map.put("ESG и устойчивое развитие", false);
    subRubricsOracle.add(map);

    map = new LinkedHashMap<>();
//    "Отраслевые решения"
    map.put("Агропромышленность", false);
    map.put("Газовая индустрия", false);
    map.put("Государственные программы", false);
    map.put("Металлургия", false);
    map.put("Нефть и химия", false);
    map.put("Промышленность и транспорт", false);
    map.put("Телеком", false);
    map.put("ТНП и ритейл", false);
    map.put("Финансовые институты", false);
    map.put("Энергетика и ЖКХ", false);
    subRubricsOracle.add(map);

    map = new LinkedHashMap<>();
//    "Проекты"
    subRubricsOracle.add(map);

    map = new LinkedHashMap<>();
//    "Создано в IBS"
    map.put("Платформа Планета", true);
    map.put("IBS НСИ", true);
    map.put("IBS EAM", true);
    map.put("Комплексная система весогабаритного контроля", false);
    map.put("GlobalCareer — IT Рекрутинговое агентство", true);
    map.put("ТехноДром — Технологии создания команд", true);
    map.put("Реестр российского ПО и свидетельства в Роспатент", false);
    subRubricsOracle.add(map);

    map = new LinkedHashMap<>();
//    "Карьера"
    map.put("Карьера в IBS", false);
    map.put("Вакансии", false);
    map.put("Стажировки", true);
    map.put("IBS Training Center", true);
    map.put("Как стать частью команды IBS", false);
    map.put("Полезные материалы для соискателей", false);
    map.put("Наша жизнь", false);
    // IBS offices
    map.put("Москва", false);
    map.put("Нижний Новгород", false);
    map.put("Омск", false);
    map.put("Пенза", false);
    map.put("Пермь", false);
    map.put("Санкт-Петербург", false);
    map.put("Таганрог", true);
    map.put("Ульяновск", false);
    map.put("Уфа", false);
    subRubricsOracle.add(map);

    map = new LinkedHashMap<>();
//    "Медиацентр"
    map.put("Новости компании", false);
    map.put("События", false);
    map.put("Материалы для СМИ", false);
    subRubricsOracle.add(map);

    map = new LinkedHashMap<>();
//    "О компании"
    map.put("О компании", false);
    map.put("Менеджмент", false);
    map.put("История IBS", false);
    map.put("Признание", false);
    map.put("Партнёры", false);
    map.put("Раскрытие информации", false);
    map.put("Контакты", false);
    subRubricsOracle.add(map);
  }

  @Step("Закрыть страницу")
  public void closePage(){
    $(locatorClosePage).click();
  }

  @Step("Проверить перечень рубрик")
  public NavigationPage verifyRubrics() {
    List<String> list = getList();
    assertThat(list).isEqualTo(rubricsOracle);
    return this;
  }

  public NavigationPage verifySubRubrics() {
    List<String> list = getList();
    int interCount = 0;
    for (index = 0; index < rubricsOracle.size(); index++) {
    String name = list.get(index);
      openRubric(name, index == 0 || index != interCount ? name : list.get(index - 1));
      step("Поверить подрубрики", () -> {
        checkSubRubrics();
      });
      if (interCount != index){
        interCount = index;
      }
      interCount++;
    }
    return this;
  }

  private List<String> getList() {
    List<String> list = $$(locatorRubrics).texts();
    list.remove("Написать нам");
    return list;
  }

  @Step("Открыть рубрику")
  public NavigationPage openRubric(String nameRubric, String prev) {
    $(byText(nameRubric)).hover();
    if(prev.length() != 0) {
      if (nameRubric.equals("Проекты")) {
        $(byTagAndText(locatorNavigationListTitle, prev)).parent().should(disappear);
      } else {
        if (prev.equals("Проекты")) {
          $(locatorSubRubrics).shouldBe(visible);
        } else {
          $(byTagAndText(locatorNavigationListTitle, prev)).parent().should(disappear);
          $(locatorSubRubrics).shouldBe(visible);
        }
      }
    } else {
      $(locatorSubRubrics).shouldBe(visible);
    }
    return this;
  }

  public NavigationPage checkSubRubrics() {
    // прочитать эталон подрубрик текущей рубрики
    Map<String, Boolean> subRubricOracle = subRubricsOracle.get(index);
    if (subRubricOracle.size() == $$(locatorSubRubrics).stream().count()) {
      ListIterator<SelenideElement> iterator = $$(locatorSubRubrics).listIterator();
      for (Map.Entry<String, Boolean> entryOracle : subRubricOracle.entrySet()) {
        SelenideElement element = iterator.next();
        step("Проверить наименование подрубрики", () -> {
          element.shouldHave(text(entryOracle.getKey()));
        });
        if (entryOracle.getValue()) {
          step("Проверка - ссылка подрубрики внешняя", () -> {
            element.shouldHave(attributeMatching("href", "^.*[^//ibs.ru].*$"));
          });
        } else {
          step("Проверка - ссылка подрубрики внутренняя", () -> {
            element.shouldHave(attributeMatching("href", "^.*//ibs.ru.*$"));
          });
        }
      }
    }
    return this;
  }

  public NavigationPage choiceCareerRubric(){
    openRubric("Карьера", "");
    return this;
  }

  @Step("Открыть страницу \"Вакансии\"")
  public VacanciesPage openVacanciesPage(){
    $(byTagAndText(locatorSubRubricsXPath, "Вакансии")).click();
    return vacanciesPage;
  }

}
