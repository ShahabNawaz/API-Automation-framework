# Use Maven with JDK 17
FROM maven:3.9-eclipse-temurin-17

# 1. Install System Dependencies ..
# We keep 'dos2unix' and 'jq' for script processing.
RUN apt-get update && apt-get install -y \
    unzip \
    wget \
    jq \
    dos2unix \
    && rm -rf /var/lib/apt/lists/*

# 2. Install AWS CLI v2
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" \
    && unzip awscliv2.zip \
    && ./aws/install \
    && rm awscliv2.zip

# 3. Install Allure CLI (For Report Generation)
RUN wget https://github.com/allure-framework/allure2/releases/download/2.24.0/allure-2.24.0.tgz \
    && tar -zxvf allure-2.24.0.tgz -C /opt/ \
    && ln -s /opt/allure-2.24.0/bin/allure /usr/bin/allure \
    && rm allure-2.24.0.tgz

# 4. Setup Workspace
WORKDIR /app

# 5. Cache Maven Dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B \
    -Dmaven.wagon.http.retryHandler.count=3 \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.rto=60000

# 6. Copy Source Code & Configs
COPY src ./src
COPY testng.xml .
COPY cron-runner.sh .
COPY valid_workflow_request.json .

# 7. Sanitize Script (Fix Windows/Format Issues)
# This remains CRITICAL to prevent "Exec Format Error"
RUN sed -i '1s/^\xEF\xBB\xBF//' cron-runner.sh
RUN sed -i 's/\r$//' cron-runner.sh
RUN dos2unix cron-runner.sh
RUN chmod +x cron-runner.sh

# 8. Set Container Entrypoint
# We explicitly use /bin/bash to run the script.
# This ensures the container runs the tests immediately and then exits.
ENTRYPOINT ["/bin/bash", "/app/cron-runner.sh"]
