package gh.funthomas424242.webapp.books.web;

/*
 * #%L
 * Books.App - Selenium Webdriver Tests
 * %%
 * Copyright (C) 2015 Pivotal Software, Inc.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gh.funthomas424242.webapp.books.Application;
import gh.funthomas424242.webapp.books.WebApplication;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author huluvu424242
 * 
 *         Fixer GUI Test der bei jedem Modultest mitläuft. Soll extrem kurz
 *         gehalten werden und nur grundlegende Dinge sicherstellen. Beispiele:
 *         - Der Server startet - Die Startseite wird ausgeliefert. - Das
 *         JavaScript arbeitet korrekt (ein dyn. Text muss erkannt werden).
 * 
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { WebApplication.class })
// WebConfiguration.class })
// @WebAppConfiguration
@DirtiesContext
@WebIntegrationTest({ "server.port=9000" })
@SeleniumTest(lang = SeleniumTest.LANGUAGE.DEUTSCH, baseUrl = "http://localhost:9000")
// @PropertySource("classpath:messages.properties")
public class ManualGuiIntegrationTest {

	public static final String SERVER_URL = "http://localhost:";

	@Value("${local.server.port}")
	private int serverPort;

	@Value("${link.books}")
	private String LINK_BOOKS;

	@Value("${link.buch.erfassen}")
	private String LINK_BUCH_ERFASSEN;

	@Value("${info.app.name}")
	private String APP_INFO;

	@Inject
	protected WebDriver driver;

	@Inject
	WebApplicationContext contextWebApp;

	// @Before
	// public void setup() throws IOException {
	//
	// final MyWebConnectionHtmlUnitDriver newDriver = new
	// MyWebConnectionHtmlUnitDriver();
	// newDriver.changeLocaleTo(Locale.GERMAN);
	// driver = MockMvcHtmlUnitDriverBuilder.webAppContextSetup(contextWebApp)
	// .configureDriver(newDriver);
	// }

	// @After
	// public void destroy() {
	// if (driver != null) {
	// driver.close();
	// }
	// }

	@Test
	public void startSeiteGeliefertJavaScriptArbeitet() {
		System.out
				.println("INFO_URL++:" + SERVER_URL + serverPort + LINK_BOOKS);
		driver.get(SERVER_URL + serverPort + LINK_BOOKS);
		assertEquals("Books.App", driver.getTitle());
		final WebElement element = driver.findElement(By.id("message"));
		final String messageCode = element.getText();
		// assertEquals("Aktuell keine Bücher im Buchregal.",
		// element.getText());
	}

	@Test
	///@Ignore
	public void homePage() {
		System.out.println("INFO: " + SERVER_URL + serverPort + LINK_BOOKS);
		final TestRestTemplate restTemplate = new TestRestTemplate();
		final String pageContent = restTemplate.getForObject(SERVER_URL
				+ serverPort + LINK_BOOKS, String.class);
		assertTrue(pageContent
				.contains("Kleine Bücherverwaltung - Bücherregal"));
	}

}
