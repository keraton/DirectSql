package acceptance.steps;

import acceptance.page.QueriesPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectQuerySteps {

    private QueriesPage queriesPage;

    public void select_query(String salary) {
        queriesPage.clickQuery(salary);
    }

    public void is_selected(String query) {
        assertThat(query).isEqualToIgnoringCase(queriesPage.getSelectedQuery());
    }

    public void select_query_from_url(String query) {
        queriesPage.openAt("http://localhost:8080/#/queries?selectedQuery=" + query);
    }
}
