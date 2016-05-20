package acceptance.tests;

import acceptance.steps.SelectQuerySteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WhenSelectQuery {

    @Steps
    private SelectQuerySteps selectQuerySteps;

    @When("^user select (.*) query$")
    public void user_select_a_query(String query) throws Throwable {
        selectQuerySteps.select_query(query);
    }

    @Then("^the (.*) query is selected$")
    public void the_salary_query_is_selected(String query) throws Throwable {
        selectQuerySteps.is_selected(query);
    }

    @When("^user access the query (.*) page$")
    public void user_access_the_query_salary_page(String query) throws Throwable {
        selectQuerySteps.select_query_from_url(query);
    }
}
