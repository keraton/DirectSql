package acceptance.page;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.matchers.BeanMatchers;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.components.HtmlTable;
import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.stream.Collectors;

@DefaultUrl("http://localhost:8080/#/queries")
public class QueriesPage extends PageObject {

    public QueriesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public List<String> getListOfQueries() {
        List<WebElementFacade> queries = findAll(".css-id-list-of-queries");
        return queries.stream()
                    .map(WebElementFacade::getText)
                    .collect(Collectors.toList());
    }

    public String getSelectedQuery() {
        return find(By.id("selectedQuery")).getTextValue();
    }

    public List<Map<Object, String>> getRows() {
        return getRows(Arrays.asList(""));
    }

    public List<Map<Object, String>> getRows(List<String> columns_name) {
        return  HtmlTable.withColumns(extract(columns_name))
                    .inTable(element(By.id("tableBody")))
                    .getRows();
    }

    private String[] extract(List<String> columns_name) {
        return columns_name.toArray(new String[]{});
    }

    public void clickQuery(String salary) {
        List<WebElementFacade> queries = findAll(".css-id-list-of-queries");
        Optional<WebElementFacade> webElementFacade = queries.stream()
                                                            .filter(w -> w.getText().equals(salary))
                                                            .findFirst();
        clickOn(webElementFacade.get());
    }

    public void filter(String field, String value) {
        find(By.id("queryFilter")).sendKeys(field + " = '" + value + "'");
        find(By.id("queryFilter")).submit();
    }


    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void limitRow(int limit) {
        find(By.id("limit")).sendKeys(limit + "");
        find(By.id("limit")).submit();
    }

    public void clickAtInfoLink() {
        find(By.id("info")).click();
    }

    public void clickOn(List<String> columns_name, String field, String value) {
        HtmlTable.withColumns(extract(columns_name))
                .inTable(element(By.id("tableBody")))
                .getRowElementsWhere(BeanMatchers.the(field, CoreMatchers.equalTo(value)))
                .stream()
                .findFirst()
                .get()
                .findElement(By.className("css-id-row"))
                .click();
    }
}
