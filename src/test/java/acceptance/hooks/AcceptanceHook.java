package acceptance.hooks;

import com.github.keraton.DirectSqlApplication;
import org.flywaydb.core.Flyway;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Acceptance test hook
 *
 */
public class AcceptanceHook {

    private static Server server;

    /**
     * Run all the resources for the test
     * @throws SQLException
     */
    public static void init() throws SQLException {
        runSpringBoot();
        setWebdriver();
        startDatabase();
        initData();
    }

    private static void runSpringBoot() {
        DirectSqlApplication.main(new String[]{});
    }

    private static void setWebdriver() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
    }

    private static void startDatabase() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers").start();
    }

    private static void initData() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:tcp://localhost/~/acceptance_test", "sa", null);
        flyway.clean();
        flyway.migrate();
    }

    /**
     * Gracefully end the resources
     */
    public static void end() {
        server.stop();
    }

}
