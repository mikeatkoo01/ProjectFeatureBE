package com.LBG.legacy.selenium;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;



@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:shopping-schema.sql",
		"classpath:shopping-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class FeaturesTest {

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
	void testFeatureLogin() throws InterruptedException{
		this.driver.get("http://localhost:" + this.port); 
		WebElement clickUserName = this.driver.findElement(By.cssSelector("#username"));
		clickUserName.sendKeys("Username");
		WebElement clickPassword = this.driver.findElement(By.cssSelector("#password"));
		clickPassword.sendKeys("Password");
		WebElement clickLogin = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > section > form > button"));
		clickLogin.click();
		
		
	}

	
	
	
	}

