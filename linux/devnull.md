#不用任何输出，只用知道命令运行是否正常
cat 1.txt
输出 cat: 1.txt: No such file or directory

cat 1.txt &>/dev/null or cat 1.txt>/dev/null >/dev/null
echo $? 
输出 1
0为命令正常执行，1-255为有出错
