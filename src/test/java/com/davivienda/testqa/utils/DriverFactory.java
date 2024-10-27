package com.davivienda.testqa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    // Mantén la variable estática sin inicializar
    private static WebDriver driver;

    public static WebDriver getDriver() {
        // Solo inicializa el driver si es nulo
        if (driver == null) {
            // Establece la propiedad del sistema con la ruta correcta
            System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");

            // Asigna la instancia de ChromeDriver a la variable estática
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Limpia la referencia
        }
    }
}
