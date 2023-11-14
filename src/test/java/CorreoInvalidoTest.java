import java.util.concurrent.TimeUnit;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.*;

public class CorreoInvalidoTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception {
        String path = "/C/Users/Mirko Alarcon/Downloads/chromedriver-win64/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    @Test
    public void paginaPrincipalFacebook(){
        
        /**************Preparacion de la prueba***********/
    	
    	//1. Ingresar a la pagina de Facebook
        String googleUrl = "https://www.facebook.com/?locale=es_LA";
        driver.get(googleUrl);

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /**************Logica de la prueba***************/
        // 2. En el campo de texto, escribir un criterio de busqueda
        /*Capturar el campo de busqueda*/
        WebElement campoCorreo = driver.findElement(By.name("email"));
        /*Escribir el correo a ingresar*/
        campoCorreo.sendKeys("aleatorioMZ$$%@123UCB.us.bo");

        //3. Presionar la tecla Enter
        campoCorreo.submit();

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert***************/

        WebElement iconAlert = driver.findElement(By.xpath("//*[@id=\"email_container\"]/div[1]/img"));
        junit.framework.Assert.assertEquals(true, iconAlert.isDisplayed());

        System.out.println("Se muestra el icono? "+iconAlert.isDisplayed());

        //Validar el mensaje del correo
        WebElement 	emailErrorMessage = driver.findElement(By.xpath("//*[@id=\"email_container\"]/div[2]/a"));
        String attribute = emailErrorMessage.getText();
        System.out.println("Valor del attribute::: "+attribute);

        Assert.assertEquals("Encuentra tu cuenta e inicia sesión.", attribute);
    }
    
}







