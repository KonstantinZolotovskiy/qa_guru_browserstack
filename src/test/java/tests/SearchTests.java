package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @DisplayName("Successful search on Android")
    @Tag("android")
    @Test
    void androidSuccessfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @DisplayName("Check date in \"in the news\" block")
    @Tag("android")
    @Test
    void currentDateTest() {
        step("Check date in \"in the news\" block", () -> {
            $(id("org.wikipedia.alpha:id/view_card_header_subtitle")).shouldHave(text(dateUtil.getFormattedDate(new Date())));
        });
    }

    @DisplayName("Check text input")
    @Tag("ios")
    @Test()
    void checkTextInputTest() {
        String text = "qa.guru";

        step("Type text button", () -> {
            $(accessibilityId("Text Button")).click();
        });

        step("Set value in text input and press enter", () -> {
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys(text);
            $(id("Text Input")).pressEnter();
        });

        step("Verify output text", () ->
                $(id("Text Output")).shouldHave(text(text)));
    }
}


