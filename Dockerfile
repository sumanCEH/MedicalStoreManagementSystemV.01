FROM openjdk:8
EXPOSE 8080
ADD target/MedicalStoreManagementSystem.jar MedicalStoreManagementSystem.jar
ENTRYPOINT ["java","-jar","/MedicalStoreManagementSystem.jar"]