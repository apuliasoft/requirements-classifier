#!/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Usage: train.sh Enterprise-name path-to-sequential-files testNumber"
    exit 1
fi

#Get files to generate testSet.
echo "Downloading frequencies"
$HADOOP_HOME/bin/hadoop fs -getmerge /$1/df-count ./tmpFiles/$1/test$3/df-count

$HADOOP_HOME/bin/hadoop fs -get /$1/dictionary.file-0 ./tmpFiles/$1/test$3/dictionary.file-0
