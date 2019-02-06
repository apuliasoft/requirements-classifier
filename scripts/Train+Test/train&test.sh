#!/bin/bash

#To train a CompanyModel we need the .tsv file that will be the 2nd parameter of the script

if [ "$#" -ne 2 ]; then
    echo "Usage: train.sh Enterprise-name path-to-sequential-files"
    exit 1
fi

if [[ -z "$MAHOUT_WORK_DIR" ]]; then
    WORK_DIR=$HOME/mahout-api
else
    WORK_DIR=$MAHOUT_WORK_DIR
fi

####PATH_TO_SEQ_FILE=${WORK_DIR}/$1/seqFiles

#Create all directories needed in HDFS

#hadoop fs -mkdir -p ${WORK_DIR}/$1/seqFiles/
#hadoop fs -mkdir -p ${WORK_DIR}/$1/vectors/
#hadoop fs -mkdir -p ${WORK_DIR}/$1/training/
#hadoop fs -mkdir -p ${WORK_DIR}/$1/test/
#hadoop fs -mkdir -p ${WORK_DIR}/$1/CompanyModel/
#hadoop fs -mkdir -p ${WORK_DIR}/$1/results/

#2. Upload data to HDFS

if [ "$HADOOP_HOME" != "" ] && [ "$MAHOUT_LOCAL" == "" ]; then
    echo "Copying files to HDFS"

    #Crear el Directorio en HDFS:
    #hadoop -fs mkdir -p ${WORK_DIR}/$1/seqFiles
    #Creo que no hace falta, que lo hace solo al hacerle el put y tal...

    $HADOOP_HOME/bin/hadoop fs -put $2 /$1
fi

#3. generate vectors from sequential file

echo "Converting sequence files to vectors"

$MAHOUT_HOME/bin/mahout seq2sparse -i /$1/train -o /$1/train

sleep 1m

$MAHOUT_HOME/bin/mahout seq2sparse -i /$1/test -o /$1/test

sleep 1m

$MAHOUT_HOME/bin/mahout trainnb -i /$1/train/tfidf-vectors -li /$1/train/labelindex -o /$1/train/model -ow -c

#5. Test

echo "Testing trainer"
$MAHOUT_HOME/bin/mahout testnb -i /$1/test/tfidf-vectors -m /$1/train/model -l /$1/train/labelindex -ow -o /$1/results -c

#Delete all hadoop files after downloading them
$HADOOP_HOME/bin/hadoop fs -rm -r /$1

sleep 30s