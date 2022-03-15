/*
 * Author: 	Ranga Palakonda
 * 			021 217 6042
 * 			ranga.palakonda@gmail.com
 * 
 * Date:	15/03/2022
 * 
 * Round 2 automation script
 * 
*/




package skyProj;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MyClass {

	//returns a random int between min max parameters
	public static int randomNum(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}



	//Holds the execution of automation script for the desired number of milliseconds
	public static void holdExecution(int milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			System.out.println("Error holding execution");
		}

	}


	private static void Scenario1(String baseUrl) {
		// Initialize
		WebDriver driver = new ChromeDriver();


		driver.get(baseUrl);
		driver.manage().deleteAllCookies();

		// Click Browse button
		WebElement browseButton = driver.findElement(By.id("SiteHeader_SiteTabs_browseDropDownLink"));
		browseButton.click();

		// Get size of list and select random item form list to click
		List<WebElement> browseList = driver.findElements(By.xpath("//*[@id=\"BrowseDropDown\"]/div/div[2]/div/div[1]/ul/li"));
		System.out.println(browseList.size());

		// listItemNum is a randomly selected number which is then used as an index to
		// select a random item from the list
		// listSide is a randomly selected number which is then used as an index to
		// randomly select from the left or right list
		int listItemNum = randomNum(1, browseList.size());
		int listSide = randomNum(1, 2);
		System.out.println("listItemNum " + listItemNum);
		System.out.println("listSide " + listSide);


		// Check that the browse button window is opened and click the random list item
		if (driver.findElement(By.cssSelector("#BrowseDropDown > div > div.modal-inner > div > div:nth-child(" + listSide + ") > ul > li:nth-child(" + listItemNum + ")")).isDisplayed()) {
			driver.findElement(By.cssSelector("#BrowseDropDown > div > div.modal-inner > div > div:nth-child(" + listSide + ") > ul > li:nth-child(" + listItemNum + ")")).click();
			System.out.println("Clicked \""+driver.findElement(By.cssSelector("#BrowseDropDown > div > div.modal-inner > div > div:nth-child(" + listSide + ") > ul > li:nth-child(" + listItemNum + ")")).getText() + "\"");
		}

		// close Chrome
		holdExecution(5000);
		driver.close();
	}
	

	private static void Scenario2(String baseUrl) {
		//Initialize
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();


		//Click register button
		WebElement registerButton = driver.findElement(By.xpath("//*[@id=\"NotLoggedInDiv\"]/a[1]"));

		if(registerButton.isDisplayed()) {
			registerButton.click();
		} else {
			System.out.println("Register button not found!");
		}

		//All the inputs for the form. 
		String email = "ranga.palakonda@gmail.com";
		String password = "secret123";
		String userName = "Ranga987";
		String firstName = "Ranga";
		String lastName = "Palakonda";
		String gender = "Male";
		String areaCode = "021";
		String phoneNumber = "2176042";
		String dobDate = "28";
		String dobMonth = "June";
		String dobYear = "1996";
		String country = "New Zealand";
		String address = "7 Multose Drive";
		String district = "Auckland - Manukau";
		String suburb = "Flat Bush";
		String city = "Auckland";
		String postCode = "2019";


		//Check each second if the page is loaded. Max 5 checks
		int i =0;
		do {
			holdExecution(1000);
			i++;
		}while (!driver.findElement(By.id("LoginDetails_EmailAddressWidgetCell")).isDisplayed() && i<5);


		
		if (driver.findElement(By.id("LoginDetails_EmailAddressTextBox")).isDisplayed()) {

			//Enter email, password, confirm password, username, first name and last name
			driver.findElement(By.id("LoginDetails_EmailAddressTextBox")).sendKeys(email);
			driver.findElement(By.id("LoginDetails_PasswordTextBox")).sendKeys(password);
			driver.findElement(By.id("LoginDetails_ConfirmPasswordTextBox")).sendKeys(password);
			driver.findElement(By.id("LoginDetails_UserNameTextBox")).sendKeys(userName);
			driver.findElement(By.id("ContactDetails_FirstNameTextBox")).sendKeys(firstName);
			driver.findElement(By.id("ContactDetails_LastNameTextBox")).sendKeys(lastName);

			//Select gender
			if (gender == "Male") {
				driver.findElement(By.xpath("//*[@id=\"ContactDetails_GenderWidgetCell\"]/span[1]/span/label")).click();

			} else if (gender == "Female") {
				driver.findElement(By.xpath("//*[@id=\"ContactDetails_GenderWidgetCell\"]/span[2]/span/label")).click();

			} else if (gender == "Diverse") {
				driver.findElement(By.xpath("//*[@id=\"ContactDetails_GenderWidgetCell\"]/span[3]/span/label")).click();

			} else {
				System.out.println("Gender not selected");
			}

			//Selects day if value matching input is found in the day dropdown list
			Select dobDay = new Select(driver.findElement(By.id("ContactDetails_DobDay")));
			List<WebElement> dobDayOptions = dobDay.getOptions();

			for (i = 0; i < dobDayOptions.size(); i++) {
				if (dobDayOptions.get(i).getText().equals(dobDate)) {
					dobDay.selectByIndex(i);
					break;
				}
			}


			//Selects month if value matching input is found in the month dropdown list
			Select dobMonthDD = new Select(driver.findElement(By.id("ContactDetails_DobMonth")));
			List<WebElement> dobMonthOptions = dobMonthDD.getOptions();

			for (i = 0; i < dobMonthOptions.size(); i++) {
				if (dobMonthOptions.get(i).getText().equals(dobMonth)) {
					dobMonthDD.selectByIndex(i);
					break;
				}
			}

			//Enter year
			driver.findElement(By.id("ContactDetails_DobYear")).sendKeys(dobYear);



			//Selects phone area code if value matching input is found in the area code dropdown list
			Select phoneDropdown = new Select(driver.findElement(By.id("ContactDetails_ContactPhoneAreaCodeDropDown")));
			List<WebElement> phoneOptions = phoneDropdown.getOptions();

			for (i = 0; i < phoneOptions.size(); i++) {
				if (phoneOptions.get(i).getText().equals(areaCode)) {
					phoneDropdown.selectByValue(areaCode);
					break;
				}
			}

			//Enter phone number
			driver.findElement(By.id("ContactDetails_ContactPhoneTextBox")).sendKeys(phoneNumber);


			//Select country radio button
			if (country.equals("New Zealand")) {
				driver.findElement(By.xpath("//*[@id=\"ContactDetails_StreetAddress_CountryWidgetCell\"]/span[1]/span/label")).click();

				
				//Enters address in field and waits for the auto fill search to appear and selects the option if it matches the address
				driver.findElement(By.xpath("//*[@id=\"ContactDetails_StreetAddress_AddressAutocompleteCell\"]/div/div/div/div/div/input")).sendKeys(address);

				holdExecution(3000);

				if (driver.findElement(By.xpath("//*[@id=\"ContactDetails_StreetAddress_AddressAutocompleteCell\"]/div/div/div/div/div/div/div[3]/ul/li/span[1]")).getText().contains(address)){
					driver.findElement(By.xpath("//*[@id=\"ContactDetails_StreetAddress_AddressAutocompleteCell\"]/div/div/div/div/div/div/div[3]/ul/li/span[1]")).click();

				} else {

					System.out.println("Could not find matching address");
				}


				//Selects the district from the district dropdown 
				Select districtDD = new Select(driver.findElement(By.id("ContactDetails_ClosestSuburbDropDown")));
				List<WebElement> districtOptions = districtDD.getOptions();

				for (i = 0; i < districtOptions.size(); i++) {
					if (districtOptions.get(i).getText().equals(district)) {
						districtDD.selectByIndex(i);
						break;
					}
				}

			} else if (country.equals("Australia")) {
				driver.findElement(By.xpath("//*[@id=\"ContactDetails_StreetAddress_CountryWidgetCell\"]/span[2]/span/label")).click();
				//Enters the address in the address fields. When Australia is selected, there is no autofill search available.
				driver.findElement(By.id("ContactDetails_StreetAddress_Address1TextBox")).sendKeys(address);

				driver.findElement(By.id("ContactDetails_StreetAddress_SuburbTextBox")).sendKeys(suburb);
				driver.findElement(By.id("ContactDetails_StreetAddress_CityTextBox")).sendKeys(city);
				driver.findElement(By.id("ContactDetails_StreetAddress_PostcodeTextBox")).sendKeys(postCode);

				Select districtDD = new Select(driver.findElement(By.id("ContactDetails_ClosestSuburbDropDown")));
				districtDD.selectByValue("Australia");

			} else {
				System.out.println("Country not found");
			}



			//Agree to T&Cs and submit form
			driver.findElement(By.id("TnCCheckbox")).click();
			driver.findElement(By.id("SubmitButton")).click();

		}



		holdExecution(5000);
		driver.close();

	}
	
	

	private static void Scenario3(String baseUrl) {
		// Initialize
		WebDriver driver = new ChromeDriver();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		holdExecution(3000);
		
		// Select random list and listing numbers
		int randListingNum = 0;
		int randListNum = randomNum(1,4);
		String selectedList = "";
		
		if (randListNum==1) {
			selectedList = "OneDollarReserveStripe";
			randListingNum = randomNum(1, 50);
		} else if (randListNum==2) {
			selectedList = "FeaturedJobsStripe_loaded";
			randListingNum = randomNum(1, 7);
		} else if (randListNum==3) {
			selectedList = "ClosingSoonStripe_loaded";
			randListingNum = randomNum(1, 50);
		} else if (randListNum==4) {
			selectedList = "TradeMeDealsStripe_loaded";
			randListingNum = randomNum(1, 4);
		}

		//Scroll down page and wait to load all listing on the page
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		holdExecution(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		holdExecution(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
		holdExecution(5000);
		
		// Go to the selected list and click next until the randomly selected listing is visiable on screen
		if (randListingNum >5) {
			driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
			holdExecution(3000);
			if (randListingNum>10) {
				driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
				holdExecution(3000);
				if (randListingNum>15) {
					driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
					holdExecution(3000);
					if (randListingNum>20) {
						driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
						holdExecution(3000);
						if (randListingNum>25) {
							driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
							holdExecution(3000);
							if (randListingNum>30) {
								driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
								holdExecution(3000);
								if (randListingNum>35) {
									driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
									holdExecution(3000);
									if (randListingNum>40) {
										driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
										holdExecution(3000);
										if (randListingNum>45) {
											driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[3]/span")).click();
											holdExecution(3000);
											
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		
		
		//Click randomly selected listing
		driver.findElement(By.xpath("//*[@id=\""+selectedList+"\"]/div/div[2]/div/div[2]/ul/li/div/a[" +randListingNum+ "]")).click();
		
		

		holdExecution(5000);
		driver.close();

	}


	public static void main(String[] args) throws InterruptedException {


		String baseUrl = "https://www.tmsandbox.co.nz/";

		//Web Automation
		//Scenario 1
		Scenario1(baseUrl);


		//Scenario 2
		Scenario2(baseUrl);


		//Scenario 3
		Scenario3(baseUrl);


	}






}