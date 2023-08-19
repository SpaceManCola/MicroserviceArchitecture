FROM amazoncorretto:20.0.2

COPY build/libs/* ./app/micro-arch.jar

ENTRYPOINT ["java","-jar","/app/micro-arch.jar"]
