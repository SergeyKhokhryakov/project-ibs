package ru.ostrovcy.ibs.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.ostrovcy.ibs.config.WebProvider;
import ru.ostrovcy.ibs.helpers.Attach;
import ru.ostrovcy.ibs.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;


public class TestBaseRemote {

  protected MainPage mainPage = MainPage.getInstance();

  @BeforeAll
  static void init() {
    new WebProvider();
    Selenide.open("/");
  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener(" Allure", new AllureSelenide());
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
  }
}
