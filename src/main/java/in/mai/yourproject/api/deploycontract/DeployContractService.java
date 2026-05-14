package in.mai.yourproject.api.deploycontract;

import in.mai.yourproject.api.common.ApiEndPoints;
import in.mai.yourproject.api.common.ApiUtil;
import in.mai.yourproject.api.common.ITestContextKeys;
import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DeployContractService {
    Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public DeployContractResponse deployContract(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployEmptyABI(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20",config.chainId, constructorArgs,"","");

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }


    public DeployContractResponse deployContractWithCustomRPC(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", config.chainId, constructorArgs,"https://custom-rpc.example.com",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    // Negative Test Cases
    public DeployContractResponse deployContractWithInvalidBytecode(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest("0xinvalid_bytecode","ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithInvalidChainId(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", 999999, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithInvalidTokenStandard(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"INVALID_TOKEN", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithEmptyBytecode(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest("","ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithNullBytecode(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(null,"ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    // Edge Cases
    public DeployContractResponse deployContractWithLargeConstructorArgs(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        // Add a very large string argument
        StringBuilder largeString = new StringBuilder();
        for(int i = 0; i < 10000; i++) {
            largeString.append("a");
        }
        constructorArgs.add(largeString.toString());
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithSpecialCharacters(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("!@#$%^&*()_+-=[]{}|;':\",./<>?");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithUnicodeCharacters(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("🚀🌟💎🔥");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", config.chainId, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithNegativeChainId(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", -1, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }

    public DeployContractResponse deployContractWithZeroChainId(ITestContext context) {
        String url = config.kwala_url + ApiEndPoints.DEPLOY_CONTRACT;

        List<String> constructorArgs = new ArrayList<>();
        constructorArgs.add("abc");
        constructorArgs.add("2");

        DeployContractRequest deployContractRequest = new DeployContractRequest(config.getBytecode(),"ERC20", 0, constructorArgs,"",config.getAbiEncoded());

        Response response = given().log().all()
                .auth().preemptive().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(deployContractRequest)
                .post(url);

        // Attach request and response to Allure report
        ApiUtil.attachRequestResponseToAllure(url, "POST", ApiUtil.objectToJson(deployContractRequest), response);

        DeployContractResponse deployContractResponse = response.as(DeployContractResponse.class, ObjectMapperType.GSON);
        ApiUtil.updateResponse(response, deployContractResponse);
        return  deployContractResponse;
    }
}