package acceptance.steps;

import acceptance.page.InfoPage;
import acceptance.page.QueriesPage;

public class InfoSteps {

    private InfoPage infoPage;
    private QueriesPage queriesPage;


    public void clickAtInfoLink() {
        queriesPage.clickAtInfoLink();
    }

    public void verifyThatUserRedirectedToInfoPage() {
        infoPage.shouldBeDisplayed();
    }
}
