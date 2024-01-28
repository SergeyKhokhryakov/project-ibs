package ru.ostrovcy.ibs;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@TM4J("HOMEWORK")
@JiraIssues({@JiraIssue("HOMEWORK-1088")})
@Layer("web")
@Owner("hso")
@Feature("Issues")
public class ManualTest {
  @Test
  @Manual
  @DisplayName("Проверка отправки формы заявки на вакансию файлом")
  public void sendFormByFileTest(){
    step("Открыть страницу \"Вакансии\"");
    step("Скроллинг страницы до заголовка \"Не нашли, что искали?\"", () -> {
      step("В поле \"Добавить резюме\" нажать радиокнопку \"файлом\"");
      step("В поле \"Загрузить файл резюме\" выбрать файл из каталога");
      step("В поле \"Имя\" ввести имя");
      step("В поле \"Фамилия\" ввести фамилию");
      step("В поле \"Email\" ввести email");
      step("В поле \"Телефон\" ввести номер телефона");
      step("В поле \"Сообщение\" ввести текст");
      step("Установить чекбокс на согласие на обработку персональных данных");
      step("Нажать кнопку Отправить");
    });
    step("Проверить результат регистрации запроса");
  }
}
