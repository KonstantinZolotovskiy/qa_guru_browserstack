package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import utils.DateUtil;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class TestBase {

    DateUtil dateUtil = new DateUtil();

    public static String env = System.getProperty("env");

    @BeforeAll
    public static void setDriver() {
        switch (env) {
            case "emulator":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "ios":
            case "android":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + env);
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = Selenide.sessionId().toString();
        Attach.pageSource();
        closeWebDriver();

        if (env.equals("android") || env.equals("ios")) {
            Attach.addVideo(sessionId);
        }
    }
}