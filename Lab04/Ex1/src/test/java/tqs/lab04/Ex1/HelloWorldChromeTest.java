package tqs.lab04.Ex1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldChromeTest {
    private WebDriver webDriver;

    @BeforeAll
    static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup(){
        webDriver = new ChromeDriver();
    }

    @Test
    void whenTest_thenTest(){
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";

        webDriver.get(sutUrl);

        String title = webDriver.getTitle();


        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @AfterEach
    void teardown(){
        webDriver.quit();
    }



}
