package Utils;


import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class TestRule extends TestWatcher {


    public TestRule() {
        super();
    }


    @Override
    protected void starting(Description description) {
        super.starting(description);
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
    }
}