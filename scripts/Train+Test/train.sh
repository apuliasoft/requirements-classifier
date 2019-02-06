#!/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Usage: train.sh Enterprise-name path-to-sequential-files testNumber"
    exit 1
fi

#Train the model
$MAHOUT_HOME/bin/mahout trainnb -i /$1/tfidf-vectors -li /$1/labelindex -o /$1/model -ow -c

