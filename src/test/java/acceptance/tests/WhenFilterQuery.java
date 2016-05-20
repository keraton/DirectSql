package acceptance.tests;

import acceptance.session.TableData;
import acceptance.steps.FilterSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;

public class WhenFilterQuery {

    @Steps
    private FilterSteps filterSteps;

    @Steps
    private TableData tableData;

    @When("^user filter with (.*) is equal to (.*)$")
    public void user_filter_with_field_is_equal_to_value(String field, String value) throws Throwable {
        filterSteps.filter(field, value);
    }

    @Then("^the table contains only (.*)$")
    public void the_table_contains_only_value(String value) throws Throwable {
        List<String> columns_name = tableData.getColumnsNames();
        filterSteps.verify_table_contains_only_value(value, columns_name);
    }

    @Then("^the address bar contains: (.*)$")
    public void the_address_bar_contains_filter_last_name_and_Ford(List<String> values) throws Throwable {
        filterSteps.verify_address_bar_contains(values);
    }

    @When("^user access the query (.*) page with filter (.*) is equal to (.*)")
    public void user_access_the_query_page_with_filter_last_name_is_equal_to_Ford(String query,
                                                                                  String field,
                                                                                  String value) throws Throwable {
        filterSteps.open_filtered_url(query, field, value);
    }

}
