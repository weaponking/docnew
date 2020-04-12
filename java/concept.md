~~~
多态：覆盖 重载
类加载：双父委托
死锁：

//	在Java中，对象的生命周期包括以下几个阶段
//	1.      创建阶段(Created)
//	在创建阶段系统通过下面的几个步骤来完成对象的创建过程
//    为对象分配存储空间
//    开始构造对象
//    从超类到子类对static成员进行初始化
//    超类成员变量按顺序初始化，递归调用超类的构造方法
//    子类成员变量按顺序初始化，子类构造方法调用
//    一旦对象被创建，并被分派给某些变量赋值，这个对象的状态就切换到了应用阶段
//	2.      应用阶段(In Use)
//	对象至少被一个强引用持有着。
//	3.      不可见阶段(Invisible)
//	当一个对象处于不可见阶段时，说明程序本身不再持有该对象的任何强引用，虽然该这些引用仍然是存在着的。
//	简单说就是程序的执行已经超出了该对象的作用域了。
//	4.      不可达阶段(Unreachable)
//	对象处于不可达阶段是指该对象不再被任何强引用所持有。
//	5.      收集阶段(Collected)
//	finalize()
//	6.      终结阶段(Finalized)
//	等待垃圾回收器对该对象空间进行回收
//	7.      对象空间重分配阶段(De-allocated)
//	垃圾回收器对该对象的所占用的内存空间进行回收或者再分配了，则该对象彻底消失了

//	垃圾检测、回收算法
//	1.标记-清除
//	2.复制(eden区2个survivor)
//	3.标记-整理
//	4.分代收集算法
//	年轻代：是所有新对象产生的地方。年轻代被分为3个部分——Enden区和两个Survivor区（From和to）
//	年老代：在年轻代中经历了N次回收后仍然没有被清除的对象
//	持久代：用于存放静态文件，比如java类、方法等
~~~