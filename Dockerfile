FROM ubuntu:latest
LABEL authors="gabriel"

WORKDIR /app

COPY pom.xml .

COPY src ./src


EXPOSE 8080

CMD ["java", "-jar", "app.jar"]


ENTRYPOINT ["top", "-b"]