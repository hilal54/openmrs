package testngpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Singlewebdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class login extends Singlewebdriver {

    @Test(dataProvider = "loginData")
    public void Logintest(String usernametext,String passwordtext,String locationtext){

        WebDriver driver= Singlewebdriver.getDriver();
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(usernametext);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(passwordtext);


        List<WebElement> locationlist = driver.findElements(By.cssSelector("ul[id='sessionLocation']>li"));
        for (WebElement location : locationlist) {
            if (locationtext.equals(location))
                location.click();
        }
        WebElement login = driver.findElement(By.id("loginButton"));
        login.click();


        WebElement successmessage = driver.findElement(By.xpath("//div[@id='content']//..//h4"));
        // WebElement errormessage = driver.findElement(By.xpath(??????????????????????);


        Assert.assertTrue(successmessage.getText().toLowerCase().contains("Logged in as Super User (admin) at Pharmacy."));

        //  Assert.assertTrue(errormessage.getText().toLowerCase().contains("invalid"));?????
    }

    @DataProvider
    public Object[][] loginData() {
        Object[][] data = {{"Hilal", "1234", "Inpatient Ward"},
                {"Admin", " ", "Outpatient Clinic"},
                {" ", "Admin123 ", "Outpatient Clinic"},
                {"@mary", "Ab&@.%ghnmk,.", "Pharmacy"},
                {"Kilic", "5678", " "},
                {"Admin", "Admin123", " "},
                {"Admin", "Admin123", "Pharmacy"}
        };
        return data;
    }

}


