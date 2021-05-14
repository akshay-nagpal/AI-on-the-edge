FROM tomcat

ADD ./target/AI-on-the-edge-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/AI.war

EXPOSE 8080
EXPOSE 5672

RUN apt-get update && cd -- && apt-get install nfs-client -y && mkdir -p /./mnt/nfs_share && apt-get install python3 -y && apt install python3-pip -y && pip3 install pika && pip3 install apscheduler && pip3 install mysql-connector-python && apt-get install rabbitmq-server -y && wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | apt-key add - && apt-get install apt-transport-https -y && echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | tee -a /etc/apt/sources.list.d/elastic-7.x.list && apt-get update -y && apt-get install logstash -y

