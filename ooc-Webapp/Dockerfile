FROM maven:3-jdk-8

WORKDIR /app

ADD . /app

RUN mvn package

EXPOSE 8082


CMD ["sh", "target/bin/webapp"]