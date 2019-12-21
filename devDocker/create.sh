#!/bin/bash

cp devDocker/Dockerfile_template devDocker/Dockerfile

docker build -f devDocker/Dockerfile -t dev-eagleeyewing-img .

docker run -t -d --name dev-eagleeyewing -v $(pwd):/home/EagleEyeWing dev-eagleeyewing-img

rm -f devDocker/Dockerfile
