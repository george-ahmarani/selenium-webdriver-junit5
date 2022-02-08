package com.xyzcorp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class CGI_lab {
    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }


    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(20).toMillis());

        driver.quit();
    }

    @Test
    void testAcceptingCookies () throws IOException {
        driver.get(
                "https://www.cgi.com/en/");

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/locators.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        driver.findElement(By.xpath(properties.getProperty("accept-cookies")));
        //driver.findElement(By.xpath("//button[@class= \"agree-button eu-cookie-compliance-default-button\"]")).click();
    }

    @Test
    void testCookiesInPlace () throws IOException {

        driver.get(
                "https://www.cgi.com/en/");

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/locators.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        // Creating interaction to the website
        WebDriver.Options options = driver.manage();

        // Creating cookies
        Cookie newCookie1 = new Cookie("_ga", "GA1.2.1728110768.1644346796",".cgi.com", "/", new java.util.Date(2024, 10, 11, 0, 0, 0));
        Cookie newCookie2 = new Cookie("cookie-agreed", "2", "www.cgi.com", "/", new java.util.Date(2024, 10, 11, 0, 0, 0));
        Cookie newCookie3 = new Cookie("_fbp", "fb.1.1644346795888.1106495566", ".cgi.com", "/", new java.util.Date(2024, 10, 11, 0, 0, 0));
        Cookie newCookie4 = new Cookie("cookie-agreed-categories", "%5B%22strictly-necessary%22%2C%22functional%22%2C%22statistics%22%2C%22marketing%22%5D", "www.cgi.com", "/", new java.util.Date(2024, 10, 11, 0, 0, 0));
        Cookie newCookie5 = new Cookie("_gid", "GA1.2.1689622865.1644346796", ".cgi.com", "/", new java.util.Date(2024, 10, 11, 0, 0, 0));
        Cookie newCookie6 = new Cookie("RT", "z=1&dm=www.cgi.com&si=2ca24813-faa7-4e50-8026-ff4b767b113e&ss=kzegyjrz&sl=f&tt=hbf&bcn=%2F%2F173bf10a.akstat.io%2F&obo=4&rl=1", ".cgi.com", "/", new java.util.Date(2024, 10, 11, 0, 0, 0));

        // Adding cookies to the website
        addingCookies(options, newCookie1, newCookie2, newCookie3, newCookie4, newCookie5, newCookie6);


        // Getting cookies values
        String readValue1 = getValue(options, newCookie1);
        String readValue2 = getValue(options, newCookie2);
        String readValue3 = getValue(options, newCookie3);
        String readValue4 = getValue(options, newCookie4);
        String readValue5 = getValue(options, newCookie5);
        String readValue6 = getValue(options, newCookie6);

        // Check if cookies are added correctly
        assertThat(newCookie1.getValue()).isEqualTo(readValue1);
        assertThat(newCookie2.getValue()).isEqualTo(readValue2);
        assertThat(newCookie3.getValue()).isEqualTo(readValue3);
        assertThat(newCookie4.getValue()).isEqualTo(readValue4);
        assertThat(newCookie5.getValue()).isEqualTo(readValue5);
        assertThat(newCookie6.getValue()).isEqualTo(readValue6);
        assertThat(options.getCookieNamed("AKA_A2").getValue()).isEqualTo("A");
        assertThat(options.getCookieNamed("cookie-agreed-version").getValue()).isEqualTo("1.0.0");

        driver.navigate().refresh();
    }

    private String getValue(WebDriver.Options options, Cookie newCookie1) {
        return options.getCookieNamed(newCookie1.getName())
                .getValue();
    }

    private void addingCookies(WebDriver.Options options, Cookie newCookie1, Cookie newCookie2, Cookie newCookie3, Cookie newCookie4, Cookie newCookie5, Cookie newCookie6) {
        options.addCookie(newCookie1);
        options.addCookie(newCookie2);
        options.addCookie(newCookie3);
        options.addCookie(newCookie4);
        options.addCookie(newCookie5);
        options.addCookie(newCookie6);
    }
}
