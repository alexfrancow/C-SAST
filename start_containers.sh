#!/bin/bash

if [ "$1" == "start" ]; then

    for i in `seq 1010 1015`;
    do
        cp Dockerfile.template Dockerfile
        echo $i
        sed -i -e "s/????/$i/g" Dockerfile
        docker build -t "$i:dockerfile" . >> /dev/null
        image_id=$(docker images | grep $i | awk '{print $3}')
        docker run -d -v codewarrior:/apps -p $i:$i $image_id >> /dev/null
        rm Dockerfile
    done
    docker ps

elif [ "$1" == "stop" ]; then
    docker ps | awk '{print $1}' | while read line; do
        echo "Stopping $line"
        docker kill $line
    done

else
   echo "Unknown parameter"

fi
