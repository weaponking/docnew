建立软连接
sudo ln -s /home/weapon/develop/apache-maven-3.5.3/bin/mvn /usr/bin/mvn

mvn.sh
#!/bin/bash
export JAVA_HOME=/usr/lib/jdk/jdk1.8.0_171
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=.:${JAVA_HOME}/bin:$PATH
cd /home/weapon/commonwork/frame-base
mvn clean install -e
cd /home/weapon/commonwork/parent-pom/frame-core
mvn clean install -e
cd /home/weapon/commonwork/parent-pom/commons-util
mvn clean install -e
