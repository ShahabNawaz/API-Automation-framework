# API Automation Framework with Allure Reporting

This project is a comprehensive API automation framework built with TestNG and enhanced with Allure reporting for detailed test execution insights.

## Features

- **TestNG Framework**: Robust test execution with parallel processing support
- **Allure Reporting**: Beautiful and detailed test reports with attachments
- **REST Assured**: Powerful API testing capabilities
- **Maven Integration**: Easy dependency management and build process
- **Retry Mechanism**: Automatic test retry on failures
- **Soft Assertions**: Comprehensive validation without stopping test execution

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Allure Command Line Tool

### Installing Allure

**On macOS (using Homebrew):**
```bash
brew install allure
```

**On Windows (using Scoop):**
```bash
scoop install allure
```

**On Linux:**
```bash
# Add allure repository
sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure
```

## Project Structure

```
src/
├── main/java/in/mai/yourproject/
│   ├── api/                    # API service classes
│   │   ├── common/            # Common utilities and base classes
│   │   ├── deploycontract/    # Contract deployment API
│   │   ├── deployestimate/    # Deployment estimation API
│   │   ├── getprice/          # Price retrieval API
│   │   ├── getsupportedtoken/ # Supported tokens API
│   │   ├── sendtransactions/  # Transaction sending API
│   │   └── transactionestimate/ # Transaction estimation API
│   └── config/                # Configuration management
└── test/java/in/mai/yourproject/
    ├── common/                # Test utilities and base classes
    └── testsuite/             # Test classes
        ├── DeployContract.java
        ├── DeployEstimate.java
        ├── GetPrice.java
        ├── GetSupportedTokens.java
        ├── SendTransaction.java
        └── TransactionEstimate.java
```

## Running Tests

### Method 1: Using the Convenience Scripts

**For macOS/Linux:**
```bash
# Run all tests and generate Allure report
./run-tests.sh

# Run all tests, clean previous results, and generate report
./run-tests.sh -c -r

# Run all tests, generate report, and open in browser
./run-tests.sh -c -r -o

# Run only GetPrice tests
./run-tests.sh GetPrice

# Run GetPrice tests and generate report
./run-tests.sh -r GetPrice

# Show help
./run-tests.sh -h
```

**For Windows:**
```cmd
# Run all tests and generate Allure report
run-tests.bat

# Run all tests, clean previous results, and generate report
run-tests.bat -c -r

# Run all tests, generate report, and open in browser
run-tests.bat -c -r -o

# Run only GetPrice tests
run-tests.bat GetPrice

# Run GetPrice tests and generate report
run-tests.bat -r GetPrice

# Show help
run-tests.bat -h
```

### Method 2: Using Maven Commands

**Run all tests using testng.xml:**
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

**Run specific test class:**
```bash
mvn clean test -Dtest=GetPrice
```

**Run specific test method:**
```bash
mvn clean test -Dtest=GetPrice#getPriceWithValidContractAddress
```

### Method 3: Using TestNG XML

**Run using testng.xml:**
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

## Generating Allure Reports

### Automatic Report Generation

Reports are automatically generated when using the convenience scripts or when tests complete successfully.

### Manual Report Generation

**Generate report from existing results:**
```bash
# If results are in target/allure-results
allure generate target/allure-results --clean -o target/allure-report

# If results are in allure-results
allure generate allure-results --clean -o allure-report
```

**Open existing report:**
```bash
# If report is in target/allure-report
allure open target/allure-report

# If report is in allure-report
allure open allure-report
```

**Serve report on local server:**
```bash
# If results are in target/allure-results
allure serve target/allure-results

# If results are in allure-results
allure serve allure-results
```

## Allure Report Features

The Allure reports provide:

- **Test Execution Overview**: Summary of passed, failed, and skipped tests
- **Detailed Test Results**: Step-by-step execution details
- **Attachments**: Screenshots, logs, and other test artifacts
- **Timeline View**: Test execution timeline
- **Categories**: Automatic categorization of failures
- **Trends**: Historical test execution trends
- **Environment Information**: Test environment details

## Configuration

### Allure Properties

The `allure.properties` file contains configuration for:
- Results and report directories
- Attachment settings
- File type filters
- Encoding settings

### Test Configuration

Update `src/main/resources/config/stage.json` to configure:
- API endpoints
- Test data
- Timeout settings
- Assertion parameters

## Test Annotations

Your tests use various Allure annotations for better reporting:

```java
@Description("Get price for valid contract address - Positive Test")
@Test(priority = 1, retryAnalyzer = MyRetryAnalyzer.class)
public void getPriceWithValidContractAddress(ITestContext context) {
    // Test implementation
}
```

## Adding New Tests

1. Create a new test class in `src/test/java/in/mai/yourproject/testsuite/`
2. Extend `MyAssert` for common assertion methods
3. Add `@Description` annotations for better reporting
4. Use `@Test` with appropriate priority and retry analyzer
5. Add the class to `testng.xml` if running all tests

## Troubleshooting

### Common Issues

**Allure command not found:**
- Ensure Allure is properly installed and in your PATH
- Try reinstalling Allure using the appropriate package manager

**No Allure results generated:**
- Check that tests are running successfully
- Verify that `allure.results.directory` is set correctly in `pom.xml`
- Ensure AspectJ weaver is properly configured

**Report not opening:**
- Check if the report directory exists
- Verify file permissions
- Try using `allure serve` instead of opening the HTML file directly

### Logs and Debugging

- Check Maven logs for compilation errors
- Review TestNG output for test execution issues
- Examine Allure results directory for generated artifacts

## Contributing

1. Follow the existing code structure and naming conventions
2. Add appropriate Allure annotations to new tests
3. Include comprehensive test descriptions
4. Update this README for any new features or changes

## Support

For issues related to:
- **TestNG**: Check the [TestNG documentation](https://testng.org/doc/)
- **Allure**: Visit the [Allure documentation](https://docs.qameta.io/allure/)
- **REST Assured**: Refer to the [REST Assured documentation](https://rest-assured.io/)# kwala-api-automation
