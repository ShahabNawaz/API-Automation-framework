package in.mai.yourproject.common;


import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.qameta.allure.*;
import io.qameta.allure.model.Status;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(NoTrace.class)
public class MySuite {

    public static final String API_GROUP = "API_GROUP";
    public static final String WEB_GROUP = "WEB_GROUP";
    private String environment;
    private int threadSleepInMillis;
    private int pageLoadTimeoutInMillis;
    private SoftAssert assertSuite;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("Staring suite for the " + ConfigManager.CONFIG_MANAGER.getConfig().getEnvironment() + " Environment");
        Config config = ConfigManager.CONFIG_MANAGER.getConfig();
        this.environment = ConfigManager.CONFIG_MANAGER.getConfig().getEnvironment();
        this.threadSleepInMillis = config.getThreadSleepInMillis();
        this.pageLoadTimeoutInMillis = config.getPageLoadTimeoutInMillis();

        assertSuite = new SoftAssert();
        System.out.println("Staring Suite verification on " + environment + " Environment");
    }

//    @Test(retryAnalyzer = MyRetryAnalyzer.class, groups = API_GROUP)
//    void apiTest(ITestContext context) {
//        OAuthResponse oAuth = (OAuthResponse) new OAuthService().getOAuth(context);
//        Assert.assertFalse(oAuth.ok, "Api Error");
//        OAuthResponse myAuth = (OAuthResponse) context.getAttribute("myAuth");
//        System.out.println(myAuth.getAccessToken());
//    }

    @AfterSuite(alwaysRun = true)
    public void setupAfterSuite() {
        System.out.println("Finished Suite verification on " + environment + " Environment");
        assertSuite.assertAll();
    }



    private void waitForSometime() {
        if (threadSleepInMillis > 0) {
            try {
                Thread.sleep(threadSleepInMillis);
            } catch (InterruptedException ignored) {
            }
        }
    }


    private void failStep() {
        Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(Status.FAILED));
        Allure.getLifecycle().stopStep();
    }
}