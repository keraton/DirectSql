package acceptance.steps;

import acceptance.page.QueriesPage;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class LimitRowSteps {

    private QueriesPage queriesPage;

    public void limit_row_to(int limit) {
        queriesPage.limitRow(limit);
    }

    public void verify_table_size(int size) {
        assertThat(queriesPage.getRows().size()).isEqualTo(size);
    }
}
