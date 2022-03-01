package com.newTours.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightConfermationPage {
	
	private WebDriver driver ;
	private WebDriverWait wait ;
	
	@FindBy(xpath = "//font[contains(text(), 'Confirmation')]")
	private WebElement flightConfirmationHeader ;
	
	@FindBy(xpath = "//font[contains(text(), 'USD')]")
	private List<WebElement> prices ;
	
	@FindBy(linkText = "SIGN-OFF")
	private WebElement signOffLink ;
	
	
	public FlightConfermationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}
	
	public String getPrice() {
		this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationHeader));
//		System.out.println(this.flightConfirmationHeader);
//		System.out.println(this.prices.get(1).getText());
		String price = this.prices.get(1).getText();
		this.signOffLink.click();
		return price;
	}

}
