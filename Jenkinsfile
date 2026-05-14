pipeline {
    agent any

    environment {
        AWS_REGION  = "ap-south-1"
        EKS_CLUSTER = "kalp-analytics-stg"
		ECR_URL	 = "408153089286.dkr.ecr.ap-south-1.amazonaws.com/kwala-api-automation"
        DOCKER_IMAGE = "${ECR_URL}:cronjob-${BUILD_NUMBER}"
    }

    stages {

        stage('Docker Build') {
            steps {
                echo "Building Docker image"
                sh "docker build -t '${DOCKER_IMAGE}' . --no-cache"
            }
        }

        stage('Push Image to ECR') {
            steps {
                echo "Logging into ECR and pushing image"
                sh '''
                  aws ecr get-login --no-include-email --region ${AWS_REGION} | sh
                  docker push ${DOCKER_IMAGE}
                '''
            }
        }

        stage('Deploy to EKS') {
            steps {
                echo "Deploying CronJob to EKS"
                sh '''
                  aws eks --region ${AWS_REGION} update-kubeconfig --name ${EKS_CLUSTER}

                  sed -i "s/<VERSION>/${BUILD_NUMBER}/g" kwala-api-automation-cronjob.yaml

                  kubectl apply -f kwala-api-automation-cronjob.yaml
                '''
            }
        }
    }

    post {
        success {
            echo "Docker image built, pushed to ECR, and deployed successfully."
        }
        failure {
            echo "Pipeline failed. Please check the logs."
        }
    }
}