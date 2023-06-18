## Run

1. Setup database:

        docker-compose -f .docker/docker-compose.yaml up -d 

3. Build everything:

       ./gradlew :microservice:user-management:build

4. Start application:

       ./gradlew :microservice:user-management:bootRun