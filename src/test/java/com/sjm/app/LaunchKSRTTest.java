package com.sjm.app;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

        public class LaunchKSRTTest {	
        	WebDriver driver;
        	Alert alt;
        		
            @BeforeMethod  //launching  chrome and KSRTC Application
     	    void testCase1() {
            	 WebDriverManager.chromedriver().setup();
          		driver=new ChromeDriver();
          		
          		driver.get("https://ksrtc.in/oprs-web/");
          		driver.manage().window().maximize();
          		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);   
            }
           
            
            @BeforeMethod  //Clicking PNR Enquiry and validating 
            void testCase2() {
            	
            	driver.findElement(By.xpath("//a[text()='PNR Enquiry']")).click();
            	WebElement actual1=  driver.findElement(By.xpath("//h4[text()='PNR Enquiry']"));
            	Assert.assertTrue(actual1.isDisplayed());
        		driver.findElement(By.xpath("//a[@id='home-tab']")).click();
            	
            }
     
        @Test(priority = 1) //valid Ph.number valid PNR number
        void testCase3() {
        	
    		driver.findElement(By.xpath("//input[@id='pnrPrefixWithTktNo']")).sendKeys("J88282497");
    		driver.findElement(By.xpath("//input[@name='mobileNo']")).sendKeys("9731427693");
    		
    		JavascriptExecutor js= (JavascriptExecutor) driver;
    		js.executeScript("document.getElementById('searchBtn').click()");
    		
    		WebElement actual2=driver.findElement(By.xpath("//td[@class='tktHeaderColor']"));
    	    Assert.assertTrue(actual2.isDisplayed());
    		driver.navigate().back(); 	 
        }
        
        @Test(priority = 2)//valid Ph.number invalid PNR No.
        void testCase4() {
        	
			driver.findElement(By.xpath("//input[@id='pnrPrefixWithTktNo']")).sendKeys("J889497");
			driver.findElement(By.xpath("//input[@name='mobileNo']")).sendKeys("9731427693");
			
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('searchBtn').click()");
			
			WebElement errorMsg1=driver.findElement(By.xpath("//div[@id='errorMsg']"));
			Assert.assertTrue(errorMsg1.isDisplayed());
			
			//WebElement actual=driver.findElement(By.xpath("//td[@class='tktHeaderColor']"));
		    //Assert.assertFalse(actual.isDisplayed());
			//driver.navigate().back();	  
         }
        
        @Test(priority = 3)//invalid Ph.number valid PNR No.
        void testCase5() {
        	
			driver.findElement(By.xpath("//input[@id='pnrPrefixWithTktNo']")).sendKeys("J88282497");
			driver.findElement(By.xpath("//input[@name='mobileNo']")).sendKeys("9731347679");
			
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('searchBtn').click()");
			
			WebElement errorMsg2=driver.findElement(By.xpath("//div[@id='errorMsg']"));
			Assert.assertTrue(errorMsg2.isDisplayed());
			
			//WebElement actual=driver.findElement(By.xpath("//td[@class='tktHeaderColor']"));
		    //Assert.assertFalse(actual.isDisplayed());
			//driver.navigate().back();	  
         }
        
        @Test(priority = 4)// entering alphabets in Ph.number text field invalid PNR No.
        void testCase6() {
			
			driver.findElement(By.xpath("//input[@id='pnrPrefixWithTktNo']")).sendKeys("J88282497");
			driver.findElement(By.xpath("//input[@name='mobileNo']")).sendKeys("werghjkfg2");
			
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('searchBtn').click()");
			
			alt=driver.switchTo().alert();
			String alertMag=alt.getText();
			System.out.println(alertMag);
			alt.accept();
	   }
        @Test(priority = 5) //valid PNR and Ph.number less than 10
        void testCase7() {
    		
    		driver.findElement(By.xpath("//input[@id='pnrPrefixWithTktNo']")).sendKeys("J88282497");
    		driver.findElement(By.xpath("//input[@name='mobileNo']")).sendKeys("97317693");
    		
    		JavascriptExecutor js= (JavascriptExecutor) driver;
    		js.executeScript("document.getElementById('searchBtn').click()");
    		
    		alt=driver.switchTo().alert();
			String alertMag=alt.getText();
			System.out.println(alertMag);
			alt.accept();
        }
        
        @Test(priority = 6) //no PNR and no Ph.number  
        void testCase8() {
    		
       		JavascriptExecutor js= (JavascriptExecutor) driver;
    		js.executeScript("document.getElementById('searchBtn').click()");
    		
    		alt=driver.switchTo().alert();
			String alertMag=alt.getText();
			System.out.println(alertMag);
			alt.accept();
        }
        
        @Test(priority = 7) //valid PNR and Ph.number field with combination of alphabet numbers and symbols
        void testCase9() {
        	
    		driver.findElement(By.xpath("//input[@id='pnrPrefixWithTktNo']")).sendKeys("J88282497");
    		driver.findElement(By.xpath("//input[@name='mobileNo']")).sendKeys("@#76%HGF*(");
    		
    		JavascriptExecutor js= (JavascriptExecutor) driver;
    		js.executeScript("document.getElementById('searchBtn').click()");
    		
    		alt=driver.switchTo().alert();
			String alertMag=alt.getText();
			System.out.println(alertMag);
			alt.accept();
        }
        
        @AfterMethod
        void testCase10() {
        	
        	driver.close();
        	
        }
     
}
