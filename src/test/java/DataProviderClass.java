
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author piyusharora
 */
public class DataProviderClass {

    @DataProvider(name = "data", parallel = true)
    public Object[][] dataprovide() throws InterruptedException, IOException {
        ReadExcelFile ref = new ReadExcelFile();
        String arr[][] = ref.readExcel();
        return arr;
    }

    @Test(dataProvider = "data")
    public void test(String email, String subject, String body) throws InterruptedException {
        String user_dir=System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", user_dir+"\\src\\test\\resource\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("http://gmail.com/");
        driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("tesqait@gmail.com");
        driver.findElement(By.xpath("//*[@id='identifierNext']//content[@class='CwaK9']//span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("seleniumtesting");
        driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[text()='COMPOSE']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//table[@class='GS' or @tabindex='-1']//textarea[@aria-label='To']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys(subject);
        driver.findElement(By.xpath("//div[@contenteditable='true' and @class='Am Al editable LW-avf']")).sendKeys(body);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Send']")).click();
        driver.findElement(By.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Sign out']")).click();
        Thread.sleep(1500);
        driver.close();

    }

}
