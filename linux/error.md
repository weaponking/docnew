
1.E: Could not get lock /var/lib/dpkg/lock - open (11: Resource temporarily unavailable)
  E: Unable to lock the administration directory (/var/lib/dpkg), is another process using it?

ps -A | grep apt
kill -9 pid

or

sudo rm /var/lib/dpkg/lock
sudo dpkg --configure -a
sudo apt-get update


System Program problem detected
vi /etc/default/apport enable =0

ubuntu18.04 ibus 拼音
删除 .cache/ibus
