package com.davivienda.testqa.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.davivienda.testqa.LoginPage;
import com.davivienda.testqa.utils.DriverFactory;
import com.davivienda.testqa.utils.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    public static void setUpReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Reporte de Pruebas de Login");
        htmlReporter.config().setReportName("Pruebas de Login");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://accounts.saucelabs.com/am/XUI/#login/");
        loginPage = new LoginPage(driver);
        test = extent.createTest("Validar Login - Prueba Individual");
    }

    @Test
    public void testLogin() {
        try {
            loginPage.login("usuarioEjemplo", "contraseñaEjemplo");

            // Aserción para verificar que el login fue exitoso, por ejemplo, validando URL o algún elemento.
            String expectedUrl = "https://accounts.saucelabs.com/am/XUI/#login/";
            String actualUrl = driver.getCurrentUrl();
            assertEquals(expectedUrl, actualUrl, "La URL después del login no coincide.");

            test.log(Status.PASS, "El login se realizó correctamente y se verificó la URL.");
        } catch (Exception e) {
            test.log(Status.FAIL, "La prueba falló: " + e.getMessage());
            Assertions.fail("La prueba de login falló.", e);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void tearDownReport() {
        extent.flush();
    }
}


