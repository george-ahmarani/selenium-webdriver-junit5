package com.xyzcorp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

public class FindPlanetsTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testRelativeLocators() {
        driver.get(
                "https://en.wikipedia.org/wiki/Planet");

        // First Method
        driver.findElement(By.cssSelector("a[href*='/wiki/Solar_System']")).click();
        WebElement earth_diameter = driver.findElement(By.xpath("//*[@id='mw-content-text']/div[1]/table[3]/tbody/tr[4]/td[1]"));
        System.out.println("The diameter of Earth is : " + earth_diameter.getText() + " km");

        // Second Method
        //driver.findElement(By.cssSelector("a[href*='/wiki/Earth']")).click();
        //WebElement earth_diameter1 = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[22]/text()[1]"));
        //System.out.println("The diameter of Earth is : " + earth_diameter1.getText());
    }
}
