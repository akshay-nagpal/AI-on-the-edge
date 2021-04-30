FROM tomcat

ADD ./target/AI-on-the-edge-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/AI.war

EXPOSE 8080
EXPOSE 5672

RUN apt-get update && cd -- && apt-get install nfs-client -y && mkdir -p mnt/nfs_share