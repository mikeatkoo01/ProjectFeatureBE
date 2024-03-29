package com.LBG.legacy.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.LBG.legacy.SpringLegacyApplication;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = { SpringLegacyApplication.class })
@TestMethodOrder(OrderAnnotation.class)

public class ProjectTest {

	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(1)

	void testLogin() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickUserName = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div > div:nth-child(4) > label:nth-child(2) > input[type=text]"));
		clickUserName.sendKeys("Liam");
		WebElement clickPassword = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div > div:nth-child(4) > label:nth-child(4) > input[type=password]"));
		clickPassword.sendKeys("Password");
		WebElement clickLogin = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div:nth-child(4) > button"));
		clickLogin.click();
		Thread.sleep(500);
		WebElement clickNextQuote = this.driver.findElement(By.cssSelector(
				"#root > div > div > div > div.carousel.slide > a.carousel-control-next > span.carousel-control-next-icon"));
		clickNextQuote.click();

		WebElement someElementAfterLogin = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div.card.border-danger > div"));
		assertTrue(someElementAfterLogin.isDisplayed(), "the element is not displayed after login");

	}

	@Test
	@Order(2)

	void testItem() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement enterItemName = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(2) > input[type=text]"));
		enterItemName.sendKeys("Tissues");
		WebElement enterItemPrice = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(3) > input[type=text]"));
		enterItemPrice.sendKeys("0.99");
		WebElement enterItemQuantity = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form > label:nth-child(4) > input[type=text]"));
		enterItemQuantity.sendKeys("1");
		WebElement createItemButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form > button"));
		createItemButton.click();
		Thread.sleep(500);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert = wait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

		String alertMessage = alert.getText();
		assertEquals("Item created successfully", alertMessage);
		alert.accept();

	}

	@Test
	@Order(3)
	void testDeleteItem() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickInventory = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(2) > a > b"));
		clickInventory.click();
		WebElement deleteItemButton = this.driver.findElement(
				By.cssSelector("#root > div > div > div.container.mt-4 > div > div:nth-child(2) > div > div > button"));
		deleteItemButton.click();

		WebElement someElementAfterDelete = this.driver
				.findElement(By.cssSelector("#root > div > div > div.container.mt-4 > div > div > div > div"));
		assertTrue(someElementAfterDelete.isDisplayed(), "the element is not displayed after delete");

	}

	@Test
	@Order(4)

	void testCart() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(3) > a > b"));
		clickOrders.click();

		WebElement enterCustomer = this.driver.findElement(
				By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(1) > label > input[type=text]"));
		enterCustomer.sendKeys("Mr Squiggles");

		WebElement createCartButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(1) > button"));
		createCartButton.click();
		Thread.sleep(500);

		Alert cartAlert = driver.switchTo().alert();
		String cartAlertMessage = cartAlert.getText();
		assertEquals("Cart created successfully", cartAlertMessage);
		cartAlert.accept();

	}

	@Test
	@Order(5)
	void testEditCustomer() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrders = this.driver.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(3) > a > b"));
		clickOrders.click();
		WebElement editCustomer = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > ul > li:nth-child(3) > button"));
		editCustomer.click();
		Thread.sleep(500);

		Alert alert = driver.switchTo().alert();

		String alertMessage = driver.switchTo().alert().getText();

		System.out.println(alertMessage);
		Thread.sleep(500);
		alert.sendKeys("Maxie");
		Thread.sleep(500);
		alert.accept();

		Thread.sleep(500);

		WebElement editedBuyer = this.driver.findElement(
				By.cssSelector("#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > h5"));
		Assertions.assertEquals("14: Maxie", editedBuyer.getText());

	}

	@Test
	@Order(6)

	void testAddToCart() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);

		WebElement clickOrdersAgain = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(3) > a > b"));
		clickOrdersAgain.click();

		WebElement clickSelectItem = this.driver.findElement(By.cssSelector(
				"#root > div > div > div:nth-child(1) > form:nth-child(2) > label:nth-child(2) > select > option:nth-child(2)"));
		clickSelectItem.click();

		WebElement clickSelectCustomer = this.driver.findElement(By.cssSelector(
				"#root > div > div > div:nth-child(1) > form:nth-child(2) > label:nth-child(3) > select > option:nth-child(2)"));
		clickSelectCustomer.click();

		WebElement addToCartButton = this.driver
				.findElement(By.cssSelector("#root > div > div > div:nth-child(1) > form:nth-child(2) > button"));
		addToCartButton.click();

		Thread.sleep(500);

		Alert addAlert = driver.switchTo().alert();
		String addAlertMessage = addAlert.getText();
		assertEquals("Item added to cart successfully", addAlertMessage);
		addAlert.accept();

	}

	@Test
	@Order(7)

	void testTotalPrice() throws InterruptedException {
		this.driver.get("http://localhost:" + this.port);
		WebElement clickOrdersAswell = this.driver
				.findElement(By.cssSelector("#navbarNav > ul > li:nth-child(3) > a > b"));
		clickOrdersAswell.click();

		WebElement totalButton = this.driver.findElement(By.cssSelector(
				"#root > div > div > div.container.mt-4 > div > div:nth-child(1) > div > div > ul > li:nth-child(4) > button"));
		totalButton.click();

		Thread.sleep(500);

		Alert totalAlert = driver.switchTo().alert();
		String totalAlertMessage = totalAlert.getText();
		assertEquals("Total for Maxie's cart: £26.80", totalAlertMessage);
		totalAlert.accept();

	}

}
