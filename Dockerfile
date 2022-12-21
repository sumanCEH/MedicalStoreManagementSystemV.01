FROM openjdk:17
EXPOSE 9001
ADD target/MedicalStoreManagementSystem.jar MedicalStoreManagementSystem.jar
ENTRYPOINT ["java","-jar","/MedicalStoreManagementSystem.jar"]