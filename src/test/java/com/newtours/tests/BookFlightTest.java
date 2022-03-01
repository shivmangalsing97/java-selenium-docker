package com.newtours.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.newTours.pages.FindFlightPage;
import com.newTours.pages.FlightConfermationPage;
import com.newTours.pages.FlightsDetailsPage;
import com.newTours.pages.RegisterationPage;
import com.newTours.pages.RegistrationConfirmationPage;

public class BookFlightTest extends BaseTest {

	private String noOfPassengers ;
	private String expectedPrice ;
	 
	
	@BeforeTest
	@Parameters({"noOfPassengers","expectedPrice"})
	public void setupPrameters(String noOfPassengers, String  expectedPrice ) {
		this.noOfPassengers = noOfPassengers;
		this.expectedPrice = expectedPrice ;

	}
	
	
	@Test
	public void registrationPageTest() {
		RegisterationPage registrationPage = new RegisterationPage(driver);
		
		registrationPage.goTo();
		registrationPage.enterUserDetails("selenium", "docker");
		registrationPage.enterUserCredentials("selenium", "docker");
		registrationPage.submit();
	}
	
	@Test(dependsOnMethods = "registrationPageTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
		registrationConfirmationPage.goToFlightsDetailsPage();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightsDetailsTest() {
		FlightsDetailsPage flightsDetailsPage = new FlightsDetailsPage(driver);
		flightsDetailsPage.selectPassengers(noOfPassengers);
		flightsDetailsPage.goToFindFlightsPage();
	}

	
	
	@Test(dependsOnMethods = "flightsDetailsTest")
	public void findFlightTest() {
		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.goToFlightConfirmationPage();
	}
	
	@Test(dependsOnMethods = "findFlightTest")
	public void FlightConfermationTest() {
		FlightConfermationPage flightConfermationPage = new FlightConfermationPage(driver);
		//flightConfermationPage.printConfirmation();
		String actualPrice = flightConfermationPage.getPrice() ;
		Assert.assertEquals(actualPrice, expectedPrice);
	}
	
	
}
