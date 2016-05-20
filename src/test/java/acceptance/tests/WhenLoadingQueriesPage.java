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

public class WhenLoadingQueriesPage {

    @Steps
    private LoadingSteps loadingSteps;

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

    @Then("^default first query is selected$")
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


}
