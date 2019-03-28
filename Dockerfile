FROM maven:3.6.0-ibmjava-8-alpine

RUN apk update
RUN apk add --no-cache openssh

COPY . /tmp/classifier/

WORKDIR /tmp/classifier/

RUN ssh-keygen -A

# create ssh certificate for user
# RUN cat /dev/zero | ssh-keygen -q -N ""
RUN cat /dev/zero | ssh-keygen -t rsa -P ""
RUN cat $HOME/.ssh/id_rsa.pub >> $HOME/.ssh/authorized_keys

# RUN /usr/sbin/sshd

RUN cp ./docker/environment.txt ./config/environment.txt
RUN cp ./docker/.bashrc ~/.bashrc
RUN source ~/.bashrc
RUN cp ./docker/hadoop-env.sh ./hadoop/etc/hadoop/hadoop-env.sh
RUN cp ./docker/hdfs-site.xml ./hadoop/etc/hadoop/hdfs-site.xml
RUN cp ./docker/init.sh ./init.sh
RUN mkdir -p /tmp/classifier/hadoop/hadoop_store/hdfs/datanode
RUN mkdir -p /tmp/classifier/hadoop/hadoop_data/hdfs/namenode
RUN /tmp/classifier/hadoop/bin/hdfs namenode -format
# RUN /tmp/classifier/hadoop/sbin/start-all.sh
# RUN /tmp/classifier/hadoop/bin/hdfs dfsadmin -safemode leave
# RUN /tmp/classifier/hadoop/sbin/start-dfs.sh
# RUN /tmp/classifier/hadoop/bin/hdfs dfsadmin -safemode leave
RUN wget http://central.maven.org/maven2/org/apache/mahout/mahout-examples/0.12.2/mahout-examples-0.12.2-job.jar -O ./mahout/mahout-examples-0.12.2-job.jar

RUN mvn clean install package -DskipTests=true

CMD ["./init.sh"]

