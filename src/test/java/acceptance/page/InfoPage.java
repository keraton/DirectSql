package acceptance.page;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

@DefaultUrl("http://localhost:8080/#/info")
public class InfoPage extends PageObject {

    public InfoPage(WebDriver webDriver) {
        super(webDriver);
    }

}
