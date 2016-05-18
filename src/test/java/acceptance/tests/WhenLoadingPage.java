package acceptance.tests;

import acceptance.session.TableData;
import acceptance.steps.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.Arrays;
import java.util.List;

public class WhenLoadingPage {

    @Steps
    private LoadingSteps loadingSteps;

    @Steps
    private SelectQuerySteps selectQuerySteps;

    @Steps
    private FilterSteps filterSteps;

    @Steps
    private LimitRowSteps limitRowSteps;

    @Steps
    private InfoSteps infoSteps;

    @Steps
    private DetailSteps detailSteps;

    @Steps
    private TableData tableData;

    @When("^user access the queries page$")
    public void user_access_the_queries_page() throws Throwable {
        loadingSteps.user_access_queries_page();
    }

    @Then("^load queries: (.*)$")
    public void load_queries_books_customers(List<String> queries) throws Throwable {
        loadingSteps.load_queries(queries);
    }

    @Then("^the first query is selected$")
    public void the_first_query_is_selected() throws Throwable {
        loadingSteps.the_first_query_is_selected();
    }

    @Then("^the table contains data$")
    public void the_table_contains_data() throws Throwable {
        loadingSteps.the_table_contains_data();
    }

    @Given("^the table contains columns$")
    public void the_table_contains_columns(DataTable columns) throws Throwable {
        tableData.setColumnsNames(columns.asList(String.class));
    }

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

    @When("^user limit row to (\\d+)$")
    public void user_limit_row_to(int limit) throws Throwable {
        limitRowSteps.limit_row_to(limit);
    }

    @Then("^the table size is (\\d+)$")
    public void the_table_size_is(int size) throws Throwable {
        limitRowSteps.verify_table_size(size);
    }

    @When("^user access the query (.*) page with filter (.*) is equal to (.*)")
    public void user_access_the_query_page_with_filter_last_name_is_equal_to_Ford(String query,
                                                                                  String field,
                                                                                  String value) throws Throwable {
        filterSteps.open_filtered_url(query, field, value);
    }

    @When("^user click at info link$")
    public void user_click_at_info_link() throws Throwable {
        infoSteps.clickAtInfoLink();
    }

    @Then("^user redirected to info page$")
    public void user_redirected_to_info_page() throws Throwable {
        infoSteps.verifyThatUserRedirectedToInfoPage();
    }

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
