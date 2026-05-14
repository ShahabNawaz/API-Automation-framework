package in.mai.yourproject.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int maxRetry = 1;

    @Override
    public boolean retry(ITestResult result) {
        return (count++ < maxRetry);
    }
}
