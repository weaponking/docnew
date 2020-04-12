vim /etc/profile

export JAVA_HOME=/usr/lib/jdk/jdk1.8.0_171

export JRE_HOME=${JAVA_HOME}/jre

export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib

export PATH=.:${JAVA_HOME}/bin:$PATH

source /etc/profile
