package acceptance.tests;

import acceptance.steps.InfoSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WhenInfo {

    @Steps
    private InfoSteps infoSteps;

    @When("^user click at info link$")
    public void user_click_at_info_link() throws Throwable {
        infoSteps.clickAtInfoLink();
    }

    @Then("^user redirected to info page$")
    public void user_redirected_to_info_page() throws Throwable {
        infoSteps.verifyThatUserRedirectedToInfoPage();
    }

}
