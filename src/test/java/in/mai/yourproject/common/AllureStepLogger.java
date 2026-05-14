package in.mai.yourproject.common;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.util.UUID;

/*
* Helper class to log into allure reporting.
* It can be used in place of @Step to avoid parameter logging into report for sensitive information
* */
public class AllureStepLogger {

    public static String startStep(String title) {
        return startStep(title, "");
    }

    public static String startStep(String title, String description) {
        String uuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName(title);
        if (!description.isEmpty()) {
            stepResult.setDescription(description);
        }
        Allure.getLifecycle().startStep(uuid, stepResult);
        return uuid;
    }

    public static void updateStatus(String uuid, Status status) {
        Allure.getLifecycle().updateStep(uuid, stepResult -> stepResult.setStatus(status));
    }

    public static void stopStep(String uuid) {
        Allure.getLifecycle().stopStep(uuid);
    }

    public static void failStep(String errorMessage) {
        String uuid = UUID.randomUUID().toString();
        StepResult resultFail = new StepResult().setName(errorMessage).setStatus(Status.FAILED);
        Allure.getLifecycle().startStep(uuid, resultFail);
        Allure.getLifecycle().stopStep(uuid);
    }


    public static void passStep(String message) {
        String uuid = UUID.randomUUID().toString();
        StepResult resultPass = new StepResult().setName(message).setStatus(Status.PASSED);
        Allure.getLifecycle().startStep(uuid, resultPass);
        Allure.getLifecycle().stopStep(uuid);
    }
}
