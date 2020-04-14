~~~
sudo apt-get update

sudo dpkg --configure -a

sudo apt --fix-broken install

sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io
~~~
