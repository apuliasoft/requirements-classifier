#!/bin/bash

/usr/sbin/sshd

/tmp/classifier/hadoop/sbin/start-all.sh
/tmp/classifier/hadoop/bin/hdfs dfsadmin -safemode leave
/tmp/classifier/hadoop/sbin/start-dfs.sh
/tmp/classifier/hadoop/bin/hdfs dfsadmin -safemode leave

java -jar /tmp/classifier/target/mahout-0.0.1-SNAPSHOT.jar