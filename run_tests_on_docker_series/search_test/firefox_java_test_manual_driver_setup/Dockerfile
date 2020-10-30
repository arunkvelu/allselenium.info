FROM maven:3-openjdk-8 as builder

WORKDIR /tmp
COPY search-docker-test search-docker-test/

WORKDIR /tmp/search-docker-test
RUN mvn clean package

FROM openjdk:8-jdk-alpine

RUN apk update && \
    apk add firefox-esr

WORKDIR /testspace
COPY --from=builder /tmp/search-docker-test/search_title_testsuite.xml .
COPY --from=builder /tmp/search-docker-test/target/search-docker-test-0.0.1-runnable.jar search-docker-test-0.0.1.jar

RUN wget -q "https://repo1.maven.org/maven2/org/testng/testng/6.7/testng-6.7.jar" -O /testspace/testng-6.7.jar && \
    wget -q "https://github.com/mozilla/geckodriver/releases/download/v0.27.0/geckodriver-v0.27.0-linux64.tar.gz" -O /tmp/geckodriver.tgz && \
    tar zxf /tmp/geckodriver.tgz -C /usr/bin/ && \
    rm /tmp/geckodriver.tgz

CMD ["java", "-Dwebdriver.gecko.driver=/usr/bin/geckodriver", "-cp", "/testspace/*", "org.testng.TestNG", "/testspace/search_title_testsuite.xml"]
