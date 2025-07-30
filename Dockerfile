FROM amazoncorretto:21-alpine

ARG APP_VERSION=''
ENV TZ=''

RUN apk add --no-cache tzdata \
    && ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

WORKDIR /app
COPY ./build/libs/surge-converter-${APP_VERSION}.jar app.jar
EXPOSE 8080

ENTRYPOINT ["/bin/sh", "-c", "java -jar app.jar"]