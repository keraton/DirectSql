package acceptance;

import acceptance.hooks.AcceptanceHook;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.sql.SQLException;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(tags = "@Ready")
public class Acceptance {

    @BeforeClass
    public static void init() throws SQLException {
        AcceptanceHook.init();
    }

    @AfterClass
    public static void end() {
        AcceptanceHook.end();
    }

}
