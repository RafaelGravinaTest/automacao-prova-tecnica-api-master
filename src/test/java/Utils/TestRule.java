package Utils;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

public class TestRule extends TestWatcher {

    private static Report report;

    public TestRule() {
        super();
    }

    public static Report getReport() {
        return report;
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);

        new File("target").mkdir();
        new File("target/relatorios").mkdir();
        report = new Report(description);
        Report.create();

    }

    @Before
    public void beforeCenario(Scenario scenario) {
        Report.startTest("Cenario: " + scenario.getName());
    }

    @After
    public void afterCenario() {
        Report.close();
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
    }
}