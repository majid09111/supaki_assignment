FROM openjdk:8

WORKDIR /mohammadmajidmasroor/Documents/supaki_assignment

COPY target/marketplace-0.0.1-SNAPSHOT.jar marketplace.jar

ENTRYPOINT ["java", "-jar", "marketplace.jar"]
