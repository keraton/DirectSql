package acceptance.steps;

import acceptance.page.QueriesPage;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterSteps {

    private QueriesPage queriesPage;

    public void filter(String field, String value) {
        queriesPage.filter(field, value);
    }

    public void verify_table_contains_only_value(String value, List<String> columns_name) {
        assertThat(queriesPage.getRows(columns_name)
                        .stream()
                        .map(Map::values)
                        .allMatch(values -> values.contains(value))).isTrue();
    }

    public void verify_address_bar_contains(List<String> values) {
        String currentUrl = queriesPage.getCurrentUrl();
        assertThat(currentUrl).contains(values);
    }

    public void open_filtered_url(String query, String field, String value) {
        queriesPage.openAt("http://localhost:8080/#/queries?selectedQuery=" + query
                            + "&queryFilter="+ field
                            + "%20%3D%20'" + value
                            + "'");
    }
}
