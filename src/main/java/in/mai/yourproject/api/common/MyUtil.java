package in.mai.yourproject.api.common;

import in.mai.yourproject.config.Config;
import in.mai.yourproject.config.ConfigManager;

import java.security.SecureRandom;
import java.util.Objects;

public class MyUtil {
    static Config config = ConfigManager.CONFIG_MANAGER.getConfig();

    public static void printLog(Object response) {
        if (!Objects.equals(ConfigManager.CONFIG_MANAGER.getConfig().getEnvironment(), "stage")) {
            System.out.println(response);
        }
    }

    public static String getRandomHexString(int length) {
        String hexChars = "0123456789abcdef";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom(); // More secure and less predictable than Random

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(hexChars.length());
            sb.append(hexChars.charAt(index));
        }

        return sb.toString();
    }



}
