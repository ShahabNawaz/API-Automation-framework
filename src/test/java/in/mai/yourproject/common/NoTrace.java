package in.mai.yourproject.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/* Add this listener to disable detailed stack trace from the test report*/
public class NoTrace extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        Throwable thrown = tr.getThrowable();
        StackTraceElement[] outTrace = new StackTraceElement[0];
        thrown.setStackTrace(outTrace);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
    }
}
