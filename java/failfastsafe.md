java.util包下面的所有的集合类都是快速失败的
java.util.concurrent包下面的所有的类都是安全失败的

快速失败: 迭代器在遍历时直接访问集合中的内容 会抛出ConcurrentModificationException异常
安全失败: 迭代器遍历的是开始遍历那一刻拿到的集合拷贝 迭代器并不能访问到修改后的内容

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastTest {

	private static List<String> list = new ArrayList<>();
//	private static List<String> list = new CopyOnWriteArrayList<>();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				int index = 0;
				while(index<10) {
					list.add(Integer.toString(index));
					print(index);
					index++;
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				int index = 10;
				while(index<20) {
					list.add(Integer.toString(index));
					print(index);
					index++;
				}
			}
		}).start();

		list.forEach(System.out::println);
	}

	public static void print(int index) {
		System.out.println("----------------"+index);
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next()+",");
		}
		System.out.println();
		System.out.println(index+"----------------");
	}
}

调用 next() 和 remove()时，都会执行 checkForComodification()。若 “modCount 不等于 expectedModCount”，则抛出ConcurrentModificationException异常
final void checkForComodification() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
}
add()、remove()，clear()，只要涉及到修改集合中的元素个数时，都会改变modCount的值
