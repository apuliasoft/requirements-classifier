#!/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Usage: train.sh Enterprise-name path-to-sequential-files testNumber"
    exit 1
fi


$HADOOP_HOME/bin/hadoop fs -put ./tmpFiles/$1/test$3/testSet /$1/testSet

$MAHOUT_HOME/bin/mahout testnb -i /$1/testSet -l /$1/labelindex -m /$1/model -ow -o /$1/results


#Delete all hadoop files:
$HADOOP_HOME/bin/hadoop fs -rm -r /$1


