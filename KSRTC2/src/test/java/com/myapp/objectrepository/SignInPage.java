package com.myapp.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.sjm.utilities.GenericLibraries.*;

public class SignInPage {

	
	WebDriver driver ;
	
	public SignInPage (WebDriver driver) {
		this.driver=driver;
	}
	
	
	@FindBy(linkText="Sign In")
	public WebElement signInBtnOnHomePage;
	
	@FindBy(id="userName")
	public WebElement usrNameTextBox;
	@FindBy(id="password")
	public WebElement passwordTextBox;
	
	@FindBy(css="input[id='submitBtn']")
	public WebElement loginBtn;
	
	@FindBy(id="errorMsg")
	public WebElement incorrectLabel;

    public boolean loginAsInValidUser(String userNme, String password) {
    	
	clickElement(signInBtnOnHomePage, driver);
	
	setValue(passwordTextBox, password, driver);
	
	clickElement(loginBtn, driver);
	 
	return isDisplayed(incorrectLabel);

}
}