~~~
wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add -
vi /etc/apt/sources.list
deb https://pkg.jenkins.io/debian binary/
sudo apt-get update
sudo apt-get install jenkins

sudo systemctl start jenkins
sudo systemctl restart jenkins
systemctl status jenkins.service

1.jenkins[9782]: ERROR: No Java executable found in current PATH: /bin:/usr/bin:/sbin:/usr/sbin,Failed to start LSB: Start Jenkins at boot time
cat /etc/services | grep 8080
sudo vi /etc/default/jenkins
sudo vi /etc/init.d/jenkins

ln -s /opt/jdk1.8.0_201/bin /usr/bin/java
sudo update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_201/bin/java 1
sudo update-alternatives --config java

password:
/var/lib/jenkins/secrets/initialAdminPassword

2.Permission denied
sudo vi /etc/default/jenkins
JENKINS_USER=root
JENKINS_GROUP=root
~~~
