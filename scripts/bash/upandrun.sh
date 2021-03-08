#!/bin/bash
clear
rm  -rf EksiDebeFetcher/
echo "Move to EksiDebeFetcher repo parent directory"
cd /opt/eksi
echo "Current current directory"
pwd
echo "Git clone from repo"
git clone https://github.com/dauut/EksiDebeFetcher.git
echo "Change dir to EksiDebeFetcher"
cd EksiDebeFetcher
echo "Compile the project"
mvn clean install -DskipTests=true
echo "Project is compiled"
echo "Move output file"
mv /opt/eksi/EksiDebeFetcher/target/EksiDebeFetcher-0.0.1-SNAPSHOT.jar /opt/eksi/
echo "EksiDebeFetcher.jar is moved to project root directory"
echo "Make jar executable"
sudo chmod 500 /opt/eksi/EksiDebeFetcher-0.0.1-SNAPSHOT.jar
echo "restarting service"
sudo systemctl restart eksi.service