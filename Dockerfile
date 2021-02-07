FROM adoptopenjdk:11-jre-openj9

RUN apt-get update && apt-get upgrade -y
ENV DEBIAN_FRONTEND="noninteractive"
RUN apt-get -y install tzdata
RUN ln -fs /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN dpkg-reconfigure --frontend noninteractive tzdata

ENV TZ America/Sao_Paulo

RUN sed -i -e 's/# pt_BR.UTF-8 UTF-8/pt_BR.UTF-8 UTF-8/' /etc/locale.gen && \
    locale-gen

ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR.UTF-8
ENV LC_ALL pt_BR.UTF-8

EXPOSE 8080

RUN adduser --system --no-create-home --disabled-login -q java
RUN mkdir /opt/app && mkdir /opt/app/banco
RUN chown -R java. /opt/app

COPY target/scrum-deck-backend-1.0.0.jar /opt/app

VOLUME /opt/app/banco

ENV SPRING_DATASOURCE_URL="jdbc:h2:/opt/app/banco/scrum-deck-db;DB_CLOSE_ON_EXIT=FALSE"
ENV SPRING_H2_CONSOLE_ENABLED="false"
ENV JAVA_TOOL_OPTIONS="-server -XX:+UseContainerSupport -Xms8m -XX:MaxRAMPercentage=80 -Xtune:virtualized -XX:+UseNUMA -XX:+UseCompressedOops -XX:+IdleTuningCompactOnIdle -XX:+IdleTuningGcOnIdle -XX:+UseCompressedStrings -XX:+OptimizeStringConcat -XX:+UseFastAccessorMethods -XX:+UseStringDeduplication -XX:+UseStringCache -XX:+UseCompressedClassPointers -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -XX:+UseZGC -XX:+ZProactive -XX:+ParallelRefProcEnabled -Djava.security.egd=file:/dev/./urandom"

USER java

WORKDIR /opt/app

ENTRYPOINT ["java", "-jar", "/opt/app/scrum-deck-backend-1.0.0.jar"]

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
            CMD curl --silent --fail --request GET http://localhost:8080/actuator/health