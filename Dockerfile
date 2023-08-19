FROM amazoncorretto:20.0.2

COPY build/libs/MicroserviceArchitecture-0.0.1-SNAPSHOT.jar ./app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
