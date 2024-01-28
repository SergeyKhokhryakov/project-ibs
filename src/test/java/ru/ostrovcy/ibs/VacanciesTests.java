package ru.ostrovcy.ibs;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.ostrovcy.ibs.base.TestBaseRemote;

@TM4J("HOMEWORK")
@JiraIssues({@JiraIssue("HOMEWORK-1088")})
@Layer("web")
@Owner("hso")
@Feature("Issues")
public class VacanciesTests extends TestBaseRemote {

  @Test
//  @TM4J("HOMEWORK")
  @Story("Поиск вакансий")
//  @JiraIssues({@JiraIssue("HOMEWORK-1070")})
  @Description("Поиск вакансии по фильтру: Опыт(С опытом), Формат работы(Удаленно), Направление(Тестирование), Город(), Технологии(Java/Selenium)")
  @Severity(SeverityLevel.CRITICAL)
  @Tags({@Tag("vacancies"), @Tag("positive")})
  public void vacanciesTest() {
    mainPage.openNavigationPage()
            .choiceCareerRubric()
            .openVacanciesPage()
            .verifyTitle()
            .searchVacanciesByFilter()
            .verifySearchResult()
            .goToMainPage();
  }

  @Test
  @Story("Установка фильтра по тегам")
  @Description("Проверка функционирования тегов фильтра")
  @Severity(SeverityLevel.NORMAL)
  @Tags({@Tag("tags"), @Tag("positive")})
  public void tagsFilterTest() {
    mainPage.getVacanciesPage()
            .directlyOpenPage();
  }
}
