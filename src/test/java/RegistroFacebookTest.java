import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/****************************************/
//Historia de Usuario: Como usuario nuevo quiero registrar mis datos
//
//Prueba de Aceptacion: Verificar que se muestren alertas para los campos obligatorios
//
//1. Ingresar a la pagina de DZone
//2. Hacer en el link Join
//3. Presionar el boton Join
//
//Resultado Esperado: Se deben mostrar mensajes de alerta para los campos obligatorios que no fueron llenados
/****************************************/


public class RegistroFacebookTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        
    	 String path = "/C/Users/Mirko Alarcon/Downloads/chromedriver-win64/chromedriver";
         
         System.setProperty("webdriver.chrome.driver", path);
         
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
        
    }
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    @Test
    public void verificarMensajeDeCampoVacioEnRegistro(){
        
        /********** Preparacion de la prueba **********/
    	
    	//1. Ingresar a la pagina de Facebook
        String dzoneUrl = "https://www.facebook.com/?locale=es_LA";
        driver.get(dzoneUrl);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        /*********** Logica de la prueba***********/
        //3. Presionar el boton Join

        WebElement joinButton = driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']"));
        joinButton.click();

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert***************/

        // Llenar los campos del formulario
        WebElement nombreInput = driver.findElement(By.name("firstname"));
        nombreInput.sendKeys("");

        WebElement apellidoInput = driver.findElement(By.name("lastname"));
        apellidoInput.sendKeys("Alarcon");

        WebElement emailInput = driver.findElement(By.name("reg_email__"));
        emailInput.sendKeys("alarcon@gmail.com");

        WebElement confirmarEmailInput = driver.findElement(By.name("reg_email_confirmation__"));
        confirmarEmailInput.sendKeys("alarcon@gmail.com");

        WebElement passwordInput = driver.findElement(By.name("reg_passwd__"));
        passwordInput.sendKeys("123456789");

        // Seleccionar la fecha de nacimiento
        WebElement dayDropdown = driver.findElement(By.name("birthday_day"));
        dayDropdown.sendKeys("13");

        WebElement monthDropdown = driver.findElement(By.name("birthday_month"));
        monthDropdown.sendKeys("nov");

        WebElement yearDropdown = driver.findElement(By.name("birthday_year"));
        yearDropdown.sendKeys("1990");

        // Seleccionar el género
        WebElement generoInput = driver.findElement(By.name("sex"));
        generoInput.click(); // Puedes ajustar esto según tu género

        // Hacer clic en el botón de registro
        WebElement registrarseButton = driver.findElement(By.name("websubmit"));
        registrarseButton.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement nombreErrorMessage = driver.findElement(By.xpath("//div[contains(@class, '_5633') and contains(text(), '¿Cómo te llamas?')]"));
        String attribute = nombreErrorMessage.getText();
        System.out.println("Valor del attribute::: " + attribute);

        Assert.assertEquals("¿Cómo te llamas?", attribute);

    }
    
}
