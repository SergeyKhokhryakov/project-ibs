package ru.ostrovcy.ibs.pages;


import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

/**
 * Главная страница приложения имеет
 * меню навигации по категориям
 * Каждая категория - перечень подрубрик (самостоятельные страницы, переход на которые
 * осуществляется по внутреннним/внешним ссылкам)
 *
 * Методы:
 * - openMainPage()
 * - openNavigationPage()
 */


public final class MainPage {
  private static volatile MainPage instance;
  //locators
  private String locatorMenu = ".header-burger";
  private String locatorHome = "a.header-logo";



  @Getter
  private NavigationPage navigationPage = new NavigationPage();
  @Getter
  private VacanciesPage vacanciesPage = new VacanciesPage();


  private MainPage() {
  }

  public static MainPage getInstance() {
    MainPage result = instance;
    if (result != null) {
      return result;
    }
    synchronized (MainPage.class) {
      if (instance == null) {
        instance = new MainPage();
      }
      return instance;
    }
  }

  @Step("Открыть меню")
  public NavigationPage openNavigationPage(){
    open(locatorMenu);
    return navigationPage;
  }

  private void open(String locator) {
    $(locator).click();
  }

}

