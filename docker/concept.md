~~~
Linux Namespaces

隔离用户空间,共用内核

容器级虚拟化

Linux Container(lxc) -> docker -> rep -> image

工具:

machine swarm compose

mesos marathon

kubernetes(k8s)

bootfs (系统引导 bootloader kernel)--> rootfs (内核只读)
~~~

名词|Desc
-|-
UTS|主机名域名
Mount|挂载
IPC|信号量 消息队列
PID|进程树
User|root
Network|网络设备
Control Group(cgroup) |资源分配(CPU MEM)
docker daemon|守护进程
