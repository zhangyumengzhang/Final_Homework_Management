FROM java:8

VOLUME /tmp

ADD demo-0.0.1-SNAPSHOT.jar demo.jar

RUN bash -c "touch /demo.jar"

EXPOSE 8080

ENTRYPOINT ["java","-jar","demo.jar"]
