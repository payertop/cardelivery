package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }


    @Test
    public void shouldValidValue() {
        Configuration.timeout = 15000;
        $("[placeholder='Город']").setValue("Москва");
        $(".menu-item__control").click();
//        $(".calendar-input__native-control").setValue("18.03.2023");
        $("[name='name']").setValue("Олег Иванов");
        $("[name='phone']").setValue("+79424242477");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
    }

    private void selectPlusSevenDaysDate() {
        LocalDate today = LocalDate.now();

        LocalDate deliveryDate = LocalDate.now().plusDays(7);

        if (deliveryDate.getMonthValue() != today.getMonthValue()) {
            $("[type='button']").click();
            $("[data-step='1']").click();
        }

        if (deliveryDate.getMonthValue() == today.getMonthValue()) {
            $("[type='button']").click();
        }
    }

    @Test
    public void shouldSuccessfullyWithManualFilling() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        String meetingDate = LocalDate.now().plusDays(3).format(formatter);
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(meetingDate);
        $("[name='name']").setValue("Олег Иванов");
        $("[name='phone']").setValue("+79424242477");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    public void shouldSuccessfullyWithAutocomplete() {

        String meetingDateDay = String.valueOf(LocalDate.now().plusDays(7).getDayOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
        String meetingDate = LocalDate.now().plusDays(7).format(formatter);

        $("[placeholder='Город']").setValue("Мо");
        $(byText("Москва")).click();
        selectPlusSevenDaysDate();
        $$(".calendar__day").find(text(meetingDateDay)).click();
        $("[name='name']").setValue("Олег Иванов");
        $("[name='phone']").setValue("+79424242477");
        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }
}