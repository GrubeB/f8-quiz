## Prerequisite

1. user-management
2. quiz-management

## Run

1. Setup database:

        docker-compose -f .docker/docker-compose.yaml up -d 

3. Build everything:

       ./gradlew :microservice:quiz-management:build

4. Start application:

       ./gradlew :microservice:quiz-management:bootRun