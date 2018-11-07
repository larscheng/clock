FROM 47.99.123.83:5000/openjdk:8-jre
WORKDIR /usr/src/
COPY target/test-0.0.1-SNAPSHOT.jar /usr/src/
CMD ["java", "-Duser.timezone=GMT+08", "-jar", "test-0.0.1-SNAPSHOT.jar"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV LANG C.UTF-8
