package ru.ostrovcy.ibs;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.ostrovcy.ibs.base.TestBaseRemote;

import static io.qameta.allure.Allure.step;

@TM4J("HOMEWORK")
@JiraIssues({@JiraIssue("HOMEWORK-1088")})
@Layer("web")
@Owner("hso")
@Feature("Issues")
public class NavigationTests extends TestBaseRemote {

  @Test
//  @TM4J("HOMEWORK")
  @Story("Навигация по сайту https://ibs.ru/")
//  @JiraIssues({@JiraIssue("HOMEWORK-1070")})
  @Description("Проверка перечня рубрик в разделе Меню (Burger) на главной странице")
  @Severity(SeverityLevel.CRITICAL)
  @Tags({@Tag("menu"), @Tag("positive"), @Tag("smoke")})
  public void navigationPageTest(){
    step("Открыть главную страницу", () -> {
      mainPage.openNavigationPage()
              .verifyRubrics()
              .closePage();
    });
  }

  @Test
//  @TM4J("HOMEWORK")
  @Story("Навигация по сайту https://ibs.ru/")
//  @JiraIssues({@JiraIssue("HOMEWORK-1070")})
  @Description("В каждой рубрике проверить перечень подрубрик, а также их ссылки (внутренняя/внешняя)")
  @Severity(SeverityLevel.CRITICAL)
  @Tags({@Tag("menu"), @Tag("service"), @Tag("positive"), @Tag("smoke")})
  public void subRubricsTest(){
    mainPage.openNavigationPage()
            .verifySubRubrics()
            .closePage();
  }

}
