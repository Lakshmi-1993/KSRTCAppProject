package com.myapp.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.myapp.objectrepository.SignInPage;
import com.sjm.utilities.BaseSuiteSetUp;
import com.sjm.utilities.ExcelOperations;

import static com.sjm.utilities.GenericLibraries.*;
import static com.sjm.utilities.ExcelOperations.*;
public class SignInTest extends BaseSuiteSetUp {
	private SignInPage signInPage;
		
	@BeforeMethod

	public void beforeTest() {
		signInPage=PageFactory.initElements(driver, SignInPage.class);

	}
	
	@Test
	public void invalidLoginTest() {
		assertion(signInPage.loginAsInValidUser(getData("SignIn", 1, 0),getData("SignIn", 1, 1)));
	}

}
