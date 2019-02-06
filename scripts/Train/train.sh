#!/bin/bash

#To train a CompanyModel we need the .tsv file that will be the 2nd parameter of the script

if [ "$#" -ne 2 ]; then
    echo "Usage: train.sh Enterprise-name path-to-sequential-file"
    exit 1
fi
#4. Train

echo "Training mahout and creating the CompanyModel"

$MAHOUT_HOME/bin/mahout trainnb -i /$1/tfidf-vectors -li /$1/labelindex -o /$1/model -ow -c

#Get the generated files in order to store them.
#We will store it in a folder with the name of the document
#If the folder doesn't exist, create it.

if [ ! -d ./data/$1 ]; then
    mkdir -p ./data/$1

fi

echo "Getting all files"
$HADOOP_HOME/bin/hadoop fs -get /$1/labelindex ./data/$1/labelindex
$HADOOP_HOME/bin/hadoop fs -get /$1/model ./data/$1/
$HADOOP_HOME/bin/hadoop fs -get /$1/dictionary.file-0 ./data/$1/dictionary.file-0
$HADOOP_HOME/bin/hadoop fs -getmerge /$1/df-count ./data/$1/df-count

sleep 30s

#Delete all hadoop files after downloading them
$HADOOP_HOME/bin/hadoop fs -rm -r /$1