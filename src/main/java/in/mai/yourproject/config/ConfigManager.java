package in.mai.yourproject.config;

import com.google.gson.Gson;
import io.restassured.internal.util.IOUtils;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;

enum Env {
    prod,
    beta,
    stage;

    static Env getEnv(String s) {
        if (prod.name().equalsIgnoreCase(s)) {
            return prod;
        } else if (beta.name().equalsIgnoreCase(s)) {
            return beta;
        } else if (stage.name().equalsIgnoreCase(s)) {
            return stage;
        }
        return stage;
    }
}

public class ConfigManager {

    public static final ConfigManager CONFIG_MANAGER = new ConfigManager();
    private Config config;

    private ConfigManager() {
        String resourceName = "config/" + Env.getEnv(System.getenv("BUILD_ENV")) + ".json";
        InputStream resourceAsStream = ConfigManager.class.getClassLoader().getResourceAsStream(resourceName);

        Assert.assertNotNull(resourceAsStream, "No config found for the env " + resourceName);
        try {
            String configJson = new String(IOUtils.toByteArray(resourceAsStream));
            Gson gson = new Gson();
            this.config = gson.fromJson(configJson, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Config getConfig() {
        return config;
    }
}
