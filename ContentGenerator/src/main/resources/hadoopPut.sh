#!/bin/sh

# check if HADOOP_HOME is set in env
if [ -z "${HADOOP_HOME}" ];then
        echo "HADOOP_HOME is not set! hence cannot run"
        exit 3
fi


# check if HIVE_HOME is set in env
if [ -z "${HIVE_HOME}" ];then
        echo "HADOOP_HOME is not set! hence cannot run"
        exit 3
fi


#DATAFile path 
if [ $# -lt 1 ] || [ $# -gt 1 ]; then
        echo "data file path is required"
        echo "Usage `basename $0` <file path is required> "
        exit 2
fi


DATA_FILE=$1
DATA_DIRECTORY=`dirname $DATA_FILE` 
if [ -e $DATA_FILE ]
then
	hadoop fs -mkdir -p $DATA_DIRECTORY
	hadoop fs -put $DATA_FILE $DATA_DIRECTORY 
	exit $?
else
    exit 1;
fi

