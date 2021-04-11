FROM openjdk:8

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

ENV ENDPOINT_SEND_MAIL=https://localhost/endpoint-send-mail
ENV ENDPOINT_SEND_SMS=https://localhost/endpoint-send-sms
ENV ENDPOINT_SEND_PUSH=https://localhost/endpoint-send-push
ENV ENDPOINT_SEND_WHATSAPP=https://localhost/endpoint-send-whatsapp