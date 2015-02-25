package gh.funthomas424242.webapp.books;

import gh.funthomas424242.webapp.books.Application;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(tags = { "@modul" }, plugin = { "pretty",
		"html:target/test-report" }, features = { "src/test/resources/stories" }
		)
@SpringApplicationConfiguration(classes = { Application.class })
public class BuchverwaltungTest {
}