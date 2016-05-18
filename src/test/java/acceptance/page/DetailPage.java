package acceptance.page;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DefaultUrl("http://localhost:8080/#/detail")
public class DetailPage extends PageObject {
    public Map<String, String> getFields() {
        List<WebElementFacade> details = findAll(".css-id-details");
        return details
                .stream()
                .map(webElementFacade -> webElementFacade.getWrappedElement())
                .collect(Collectors.toMap(elem -> elem.findElement(By.className("panel-title")).getText(),
                                          elem -> elem.findElement(By.className("panel-body")).getText()));
    }
}
