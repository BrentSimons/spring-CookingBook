FROM eclipse-temurin:17

VOLUME /tmp
# needed for temporary storage by spring boot

COPY target/CookingBook-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
