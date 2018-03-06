package cn.itcast.thread;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * ThreadLocal对象的用法
 * @author cxf
 *
 */
public class Demo1 {
	@Test
	public void fun1() {
		final ThreadLocal<String> tl = new ThreadLocal<String>();
//		tl.set("hello");//存
		
		
		new Thread() {
			public void run() {
				tl.set("内部类存");
//				System.out.println("内容类：" + tl.get());
			}
		}.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tl.get());//取
	}
	
	@Test
	public void fun2() {
		final Map<Thread, String> map = new HashMap<Thread, String>();
		map.put(Thread.currentThread(), "hello");
		System.out.println(map.get(Thread.currentThread()));
		
		new Thread() {
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.out.println(map.get(Thread.currentThread()));
			}
		}.start();
	}
}

/**
 * 它的内部是一个Map
 * @author cxf
 *
 * @param <T>
 */
class TL<T> {
	private Map<Thread, T> map = new HashMap<Thread, T>();
	
	public void set(T data) {
		// 使用当前线程做key
		map.put(Thread.currentThread(), data);
	}
	
	public T get() {
		return map.get(Thread.currentThread());
	}
	
	public void remove() {
		map.remove(Thread.currentThread());
	}
}

/**
 * ThreadLocal通常用在一个类的成员上
 * 多个线程访问它时，每个线程都有自己的副本，互不干扰！
 * Spring中把Connection放到了ThreadLocal中！
 * 明天我们会再次修改JdbcUtils类！，给它添加一个ThreadLocal<Connection>
 * @author cxf
 *
 */
class User {
	private ThreadLocal<String> usernameTl = new ThreadLocal<String>();
}
