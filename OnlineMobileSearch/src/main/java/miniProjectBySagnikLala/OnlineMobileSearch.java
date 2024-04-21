package miniProjectBySagnikLala;

//importing the Selenium libraries
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//importing the annotation library of TestNG
import org.testng.annotations.*;


  
public class OnlineMobileSearch { 
	//Author :: Sagnik Lala
	
	WebDriver driver = null;
	String browser = "";
	
	    	
    @Parameters("browserName")
	@BeforeTest
    public void setup(String browserName) { //Setting up the driver properly
    	
    	System.out.println("Currently, Test cases are being tested in "+browserName+"\n");
    	
    	if(browserName.equalsIgnoreCase("chrome")) {
    		
    		System.setProperty("webdriver.chrome.com", "user.dir/chrome.exe");
    		driver = new ChromeDriver();
    		browser=browserName;
    		
    	}
    	
    	else if(browserName.equalsIgnoreCase("firefox")) {
    		
    		System.setProperty("webdriver.firefox.com", "user.dir/geckodriver.exe");
    		driver = new FirefoxDriver();
    		browser=browserName;
    		
    	}
    	
    	else if(browserName.equalsIgnoreCase("edge")) {
    		
    		System.setProperty("webdriver.edge.com", "user.dir/edgedriver.exe");
    		driver = new EdgeDriver();
    		browser=browserName;
    		
    	}
    	
    }
    
    @Test(priority=1)
    public void displayString()throws InterruptedException{ //Test Case 1
    	
    	
    	driver.get("https://www.amazon.in");
    	driver.manage().window().maximize(); 
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobile smartphones under 30000");
        
        //driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS); This function is now deprecated in Selenium 4
        Thread.sleep(3000);
        
        driver.findElement(By.id("nav-search-submit-button")).click();
        
        String displayText = driver.findElement(By.className("sg-col-inner")).getText();
             
        System.out.println("");
        if ((displayText.replaceAll("[0-9]","").equals("- of over , results for \"mobile smartphones under \"")) || (displayText.replaceAll("[0-9]","").equals("- of , results for \"mobile smartphones under \""))) {
            System.out.println("TEST CASE 1 for "+browser+"\nValidate the availability and format of the text that shows the number of search results"+"\nExpected Result: [Numeric]-[Numeric] of over [Numeric] results for \"mobile smartphones under 30000\"\nActual Result:"+displayText+"\nPASS\n\n");
        } else {
            System.out.println("TEST CASE 1 for "+browser+"\nValidate the availability and format of the text that shows the number of search results"+"\nExpected Result: [Numeric]-[Numeric] of over [Numeric] results for \"mobile smartphones under 30000\"\nActual Result:"+displayText+"\nFAIL\n\n");
        }
        
    }
    
    @Test(priority=2)
    public void sortingOptions()throws InterruptedException { //Test Case 2
    	
    	
        driver.findElement(By.id("a-autoid-0-announce")).click();
    
        if(driver.findElement(By.className("a-popover-inner")).getText().split("\n").length == 4)
        	System.out.print("\n\nTEST CASE 2 for "+browser+"\nValidate the number of sorting options in the drop down listbox"+"\nExpected Result: 4\nActual Result: "+driver.findElement(By.className("a-popover-inner")).getText().split("\n").length+"\nPASS\n\n");
        else
        	System.out.print("\n\nTEST CASE 2 for "+browser+"\nValidate the number of sorting options in the drop down listbox"+"\nExpected Result: 4\nActual Result: "+driver.findElement(By.className("a-popover-inner")).getText().split("\n").length+"\nFAIL\n\n");
    
        Thread.sleep(8000);
        driver.findElement(By.cssSelector("#s-result-sort-select_4")).click();
    	
    }
    
    @Test(priority=3)
    public void newestArrivals()throws InterruptedException { //Test Case 3
    	
        
        String newSortOrder=driver.findElement(By.cssSelector(".a-dropdown-prompt")).getText();
        
        if(newSortOrder.equals("Newest Arrivals"))
        	System.out.print("\n\nTEST CASE 3 for "+browser+"\nValidate the availability and format of \"NEWEST ARRIVALS\""+"\nExpected Result: Newest Arrivals\nActual Results: "+newSortOrder+"\nPASS\n\n");
        else
        	System.out.print("\n\nTEST CASE 3 for "+browser+"\nValidate the availability and format of \"NEWEST ARRIVALS\""+"\nExpected Result: Newest Arrivals\nActual Results: "+newSortOrder+"\nFAIL\n\n");
        
        
        Thread.sleep(10000);
    	
    }
    
    @AfterTest
    public void teardown() { //Wrapping up the test suite
    	
    	System.out.println("\n\nTests on "+browser+" completed Successfully");
    	
    	driver.close(); //Browser window is closed and the code is terminated
    	
    }
}