package acceptance.tests;

import acceptance.steps.LimitRowSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WhenLimitRows {

    @Steps
    private LimitRowSteps limitRowSteps;

    @When("^user limit row to (\\d+)$")
    public void user_limit_row_to(int limit) throws Throwable {
        limitRowSteps.limit_row_to(limit);
    }

    @Then("^the table size is (\\d+)$")
    public void the_table_size_is(int size) throws Throwable {
        limitRowSteps.verify_table_size(size);
    }

}
