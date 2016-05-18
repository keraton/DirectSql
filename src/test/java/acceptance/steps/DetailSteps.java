package acceptance.steps;

import acceptance.page.DetailPage;
import acceptance.page.QueriesPage;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

public class DetailSteps {

    private QueriesPage queriesPage;
    private DetailPage detailSteps;

    public void clickOn(List<String> columns_name, String field, String value) {
        queriesPage.clickOn(columns_name, field, value);
    }

    public void verifyThatUserRedirectedToDetailPage() {
        detailSteps.shouldBeDisplayed();
    }

    public void verifyPageContains(Map<String, String> stringStringMap) {
        Map<String, String> fields = detailSteps.getFields();
        Assertions.assertThat(fields).contains(stringStringMap.entrySet().toArray(new Map.Entry[]{}));
    }
}
