package in.mai.yourproject.common;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * TestNG listener that automatically generates and opens Allure report after all tests complete
 */
public class AllureReportListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        // Nothing to do at suite start
    }

    @Override
    public void onFinish(ISuite suite) {
        // Automatically generate and open Allure report after all tests complete
        openAllureReport();
    }

    /**
     * Generates and opens Allure report automatically after test execution
     */
    private void openAllureReport() {
        try {
            System.out.println("\n==========================================");
            System.out.println("Generating Allure report...");
            System.out.println("==========================================\n");
            
            // Get the project directory
            String projectDir = System.getProperty("user.dir");
            String allureResultsPath = projectDir + File.separator + "target" + File.separator + "allure-results";
            String allureReportPath = projectDir + File.separator + "target" + File.separator + "allure-report";
            
            // Clean previous report directory
            System.out.println("Cleaning previous Allure report...");
            File reportDir = new File(allureReportPath);
            if (reportDir.exists()) {
                deleteDirectory(reportDir);
                System.out.println("✓ Previous report cleaned.");
            }
            
            // Check if allure-results directory exists
            File resultsDir = new File(allureResultsPath);
            if (!resultsDir.exists() || resultsDir.listFiles() == null || resultsDir.listFiles().length == 0) {
                System.out.println("No Allure results found. Skipping report generation.");
                return;
            }
            
            // Generate Allure report
            ProcessBuilder generateProcess = new ProcessBuilder(
                    "allure", "generate", allureResultsPath, 
                    "--clean", "-o", allureReportPath
            );
            generateProcess.directory(new File(projectDir));
            Process generate = generateProcess.start();
            
            // Wait for report generation to complete
            boolean generated = generate.waitFor(30, TimeUnit.SECONDS);
            if (!generated) {
                System.out.println("Allure report generation timed out.");
                generate.destroyForcibly();
                return;
            }
            
            int exitCode = generate.exitValue();
            if (exitCode != 0) {
                // Read error output
                try (BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(generate.getErrorStream()))) {
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                        System.err.println(errorLine);
                    }
                }
                System.out.println("Failed to generate Allure report. Exit code: " + exitCode);
                return;
            }
            
            System.out.println("✓ Allure report generated successfully!");
            System.out.println("Report location: " + allureReportPath + File.separator + "index.html\n");
            
            // Start Allure server in background and open browser
            System.out.println("Starting Allure server...");
            ProcessBuilder serveProcess = new ProcessBuilder(
                    "allure", "serve", allureResultsPath
            );
            serveProcess.directory(new File(projectDir));
            Process serve = serveProcess.start();
            
            // Wait a bit for server to start
            Thread.sleep(3000);
            
            // Try to get the port from the server output or use default
            String url = "http://localhost:";
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(serve.getInputStream()))) {
                String line;
                int lineCount = 0;
                while ((line = reader.readLine()) != null && lineCount < 10) {
                    // Allure serve typically outputs the URL
                    if (line.contains("http://")) {
                        int start = line.indexOf("http://");
                        int end = line.length();
                        // Find end of URL (space, newline, or end of string)
                        for (int i = start; i < line.length(); i++) {
                            if (line.charAt(i) == ' ' || line.charAt(i) == '\n') {
                                end = i;
                                break;
                            }
                        }
                        url = line.substring(start, end).trim();
                        break;
                    }
                    lineCount++;
                }
            }
            
            // If we couldn't find the URL, try common ports
            if (url.equals("http://localhost:")) {
                // Try to find the port using lsof or use common default
                url = "http://localhost:5050"; // Common default port
            }
            
            // Open browser (works on macOS, Linux, and Windows)
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder openProcess;
            
            if (os.contains("mac")) {
                openProcess = new ProcessBuilder("open", url);
            } else if (os.contains("win")) {
                openProcess = new ProcessBuilder("cmd", "/c", "start", url);
            } else {
                openProcess = new ProcessBuilder("xdg-open", url);
            }
            
            openProcess.start();
            System.out.println("✓ Allure report opened in browser!");
            System.out.println("URL: " + url);
            System.out.println("\nNote: The Allure server is running in the background.");
            System.out.println("Close it manually if needed (Ctrl+C in terminal or kill the process).\n");
            
        } catch (Exception e) {
            System.err.println("\n⚠ Failed to open Allure report automatically: " + e.getMessage());
            e.printStackTrace();
            System.out.println("\nYou can manually generate and open the report using:");
            System.out.println("  allure generate target/allure-results --clean -o target/allure-report");
            System.out.println("  allure serve target/allure-results");
        }
    }

    /**
     * Recursively delete a directory and all its contents
     */
    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}

