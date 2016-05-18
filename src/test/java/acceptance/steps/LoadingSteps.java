package acceptance.steps;

import acceptance.page.QueriesPage;

import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadingSteps {

    private QueriesPage queriesPage;

    public void user_access_queries_page() {
        queriesPage.open();
    }

    public void load_queries(List<String> queries) {
        assertThat(queriesPage.getListOfQueries()).containsAll(queries);
    }

    public void the_first_query_is_selected() {
        String firstQuery = new String(queriesPage.getListOfQueries().get(0).getBytes(Charset.defaultCharset()));
        assertThat(firstQuery).isEqualToIgnoringCase(queriesPage.getSelectedQuery());
    }

    public void the_table_contains_data() {
        assertThat(queriesPage.getRows().size()).isGreaterThan(0);
    }
}
