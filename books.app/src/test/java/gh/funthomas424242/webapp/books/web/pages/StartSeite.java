package gh.funthomas424242.webapp.books.web.pages;

import org.openqa.selenium.WebDriver;

public class StartSeite extends SeleniumPage {

	public StartSeite(final WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageUrl() {
		return "http://127.0.0.1:8080/";
	}

}