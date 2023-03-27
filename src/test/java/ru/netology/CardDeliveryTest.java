package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;




public class CardDeliveryTest {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSendFormSuccessfulNotification() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Тула");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на "
                + planningDate), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSendForm() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Тула");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSendFormCityWithDash() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Санкт-Петербург");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    //тест буквы "ё"
    @Test
    void shouldSendFormCityWithE() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Орёл");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSendFormCityWithSpace() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Нижний Новгород");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSendFormNameWithDash() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Тула");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Ана-Мария Огбезян");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSendFormSurNameWithDash() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Тула");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Марина Перейра-Родригес");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSendFormDate3Days() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Тула");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Марина Перейра-Родригес");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldSendFormDate4Days() {
        String planningDate = generateDate(4);
        $x("//*[@data-test-id='city']//input").val("Тула");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Марина Перейра-Родригес");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(14));
    }

    @Test
    void shouldNotSendFormCityWrongSpelling() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Санкт Петербург");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='city']//*[@class='input__sub']").shouldHave(exactText("Доставка в "
                + "выбранный город недоступна"));
    }

    @Test
    void shouldNotSendFormCityNotInList() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Белозерск");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='city']//*[@class='input__sub']").shouldHave(exactText("Доставка в "
                + "выбранный город недоступна"));
    }

    @Test
    void shouldNotSendFormDateLessThanThreeDays() {
        String planningDate = generateDate(2);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='date']//*[@class='input__sub']").shouldHave(exactText("Заказ на "
                + "выбранную дату невозможен"));
    }

    @Test
    void shouldNotSendFormDateInPast() {
        String planningDate = generateDate(-365);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='date']//*[@class='input__sub']").shouldHave(exactText("Заказ на "
                + "выбранную дату невозможен"));
    }

    @Test
    void shouldNotSendFormNameInLatin() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("James Bond");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='name']//*[@class='input__sub']").shouldHave(exactText("Имя и Фамилия "
                + "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendFormSymbolsInsteadOfName() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("~!@#$%^&*()_+?...;");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='name']//*[@class='input__sub']").shouldHave(exactText("Имя и Фамилия "
                + "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendFormPhoneStartsWithEight() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("89110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='phone']//*[@class='input__sub']").shouldHave(exactText("Телефон указан "
                + "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendFormPhoneWithSpaces() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+7 911 007 07 07");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='phone']//*[@class='input__sub']").shouldHave(exactText("Телефон указан "
                + "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendFormIfNoAgreement() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='agreement']//*[@class='checkbox__text']").shouldHave(exactText("Я "
                + "соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldNotSendFormIfEmptyCity() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='city']//*[@class='input__sub']").shouldHave(exactText("Поле обязательно "
                + "для заполнения"));
    }

    @Test
    void shouldNotSendFormIfEmptyDate() {
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val("");
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='date']//*[@class='input__sub']").shouldHave(exactText("Неверно "
                + "введена дата"));
    }

    @Test
    void shouldNotSendFormIfEmptyName() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("");
        $x("//*[@data-test-id='phone']//input").val("+79110070707");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='name']//*[@class='input__sub']").shouldHave(exactText("Поле обязательно "
                + "для заполнения"));
    }

    @Test
    void shouldNotSendFormIfEmptyPhone() {
        String planningDate = generateDate(3);
        $x("//*[@data-test-id='city']//input").val("Вологда");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[@data-test-id='date']//input").val(planningDate);
        $x("//*[@data-test-id='name']//input").val("Джеймс Бонд");
        $x("//*[@data-test-id='phone']//input").val("");
        $x("//*[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='phone']//*[@class='input__sub']").shouldHave(exactText("Поле обязательно "
                + "для заполнения"));
    }

    @Test
    void shouldNotSendFormIfWholeFormEmpty() {
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys("BackSpace");
        $x("//*[text()='Забронировать']").click();
        $x("//*[@data-test-id='city']//*[@class='input__sub']").shouldHave(exactText("Поле обязательно "
                + "для заполнения"));
    }
}