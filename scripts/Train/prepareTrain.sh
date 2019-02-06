#!/bin/bash

#To train a CompanyModel we need the .tsv file that will be the 2nd parameter of the script

if [ "$#" -ne 2 ]; then
    echo "Usage: train.sh Enterprise-name path-to-sequential-file"
    exit 1
fi

if [[ -z "$MAHOUT_WORK_DIR" ]]; then
    WORK_DIR=$HOME/mahout-api
else
    WORK_DIR=$MAHOUT_WORK_DIR
fi

#Create the directory if not created...
mkdir -p ${WORK_DIR}/$1/seqFiles

#echo "Moving sequential file to working directory"

#mv $2 ${WORK_DIR}/$1/seqFiles/

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

#rm -rf ${WORK_DIR}/$1


#3. generate vectors from sequential file

echo "Converting sequence files to vectors"

$MAHOUT_HOME/bin/mahout seq2sparse -i /$1 -o /$1