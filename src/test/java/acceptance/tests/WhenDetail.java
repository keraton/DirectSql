package acceptance.tests;

import acceptance.session.TableData;
import acceptance.steps.DetailSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class WhenDetail {

    @Steps
    private DetailSteps detailSteps;

    @Steps
    private TableData tableData;

    @When("^user click on (.*) equal to (.*)$")
    public void user_click_on_ID_equal_to(String field, String value) throws Throwable {
        List<String> columns_name = tableData.getColumnsNames();
        detailSteps.clickOn(columns_name, field, value);
    }

    @Then("^user redirected to detailed page$")
    public void user_redirected_to_detailed_page() throws Throwable {
        detailSteps.verifyThatUserRedirectedToDetailPage();
    }

    @Then("^detailed page contains$")
    public void detailed_page_contains(DataTable arg1) throws Throwable {
        detailSteps.verifyPageContains(arg1.asMap(String.class, String.class));
    }

}
