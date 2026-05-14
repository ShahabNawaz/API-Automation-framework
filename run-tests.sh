
#!/bin/bash

# Allure Test Runner Script
# This script runs tests and generates Allure reports

echo "=== Allure Test Runner ==="

# Function to clean previous results
clean_results() {
    echo "Cleaning previous Allure results..."
    rm -rf target/allure-results
    rm -rf target/allure-report
    rm -rf allure-results
    rm -rf allure-report
}

# Function to run tests
run_tests() {
    local test_suite=$1
    echo "Running tests with suite: $test_suite"
    
    if [ "$test_suite" = "all" ]; then
        mvn clean test -DsuiteXmlFile=testng.xml
    else
        mvn clean test -Dtest=$test_suite
    fi
}

# Function to generate Allure report
generate_report() {
    echo "Generating Allure report..."
    
    # Check if allure-results directory exists
    if [ -d "target/allure-results" ]; then
        allure generate target/allure-results --clean -o target/allure-report
        echo "Allure report generated successfully!"
        echo "Report location: target/allure-report/index.html"
    elif [ -d "allure-results" ]; then
        allure generate allure-results --clean -o allure-report
        echo "Allure report generated successfully!"
        echo "Report location: allure-report/index.html"
    else
        echo "No Allure results found. Please run tests first."
        exit 1
    fi
}

# Function to open Allure report
open_report() {
    local report_path=""
    
    if [ -f "target/allure-report/index.html" ]; then
        report_path="target/allure-report/index.html"
    elif [ -f "allure-report/index.html" ]; then
        report_path="allure-report/index.html"
    else
        echo "No Allure report found. Please generate report first."
        exit 1
    fi
    
    echo "Opening Allure report..."
    open "$report_path"
}

# Function to auto-open report after tests
auto_open_report() {
    echo "Auto-opening Allure report after test completion..."
    sleep 2  # Give a moment for report generation
    open_report
}

# Function to show usage
show_usage() {
    echo "Usage: $0 [OPTIONS] [TEST_SUITE]"
    echo ""
    echo "OPTIONS:"
    echo "  -c, --clean     Clean previous results before running tests"
    echo "  -r, --report    Generate Allure report after running tests"
    echo "  -o, --open      Open Allure report in browser"
    echo "  -h, --help      Show this help message"
    echo ""
    echo "TEST_SUITE:"
    echo "  all             Run all tests using testng.xml (default)"
    echo "  GetPrice        Run only GetPrice test class"
    echo "  DeployContract  Run only DeployContract test class"
    echo "  <class_name>    Run specific test class"
    echo ""
    echo "EXAMPLES:"
    echo "  $0                    # Run all tests and generate report"
    echo "  $0 -c -r              # Clean, run all tests, generate report"
    echo "  $0 -c -r -o           # Clean, run all tests, generate and open report"
    echo "  $0 GetPrice           # Run only GetPrice tests"
    echo "  $0 -r GetPrice        # Run GetPrice tests and generate report"
}

# Parse command line arguments
CLEAN=false
GENERATE_REPORT=false
OPEN_REPORT=false
TEST_SUITE="all"

while [[ $# -gt 0 ]]; do
    case $1 in
        -c|--clean)
            CLEAN=true
            shift
            ;;
        -r|--report)
            GENERATE_REPORT=true
            shift
            ;;
        -o|--open)
            OPEN_REPORT=true
            shift
            ;;
        -h|--help)
            show_usage
            exit 0
            ;;
        -*)
            echo "Unknown option: $1"
            show_usage
            exit 1
            ;;
        *)
            TEST_SUITE="$1"
            shift
            ;;
    esac
done

# Main execution
echo "Test Suite: $TEST_SUITE"
echo "Clean: $CLEAN"
echo "Generate Report: $GENERATE_REPORT"
echo "Open Report: $OPEN_REPORT"
echo ""

# Clean if requested
if [ "$CLEAN" = true ]; then
    clean_results
fi

# Run tests
run_tests "$TEST_SUITE"

# Generate report if requested or if no specific action was requested
if [ "$GENERATE_REPORT" = true ] || ([ "$CLEAN" = false ] && [ "$OPEN_REPORT" = false ]); then
    generate_report
fi

# Open report if requested
if [ "$OPEN_REPORT" = true ]; then
    open_report
fi

echo "=== Test execution completed ==="

