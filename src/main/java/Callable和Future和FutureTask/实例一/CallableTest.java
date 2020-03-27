package Callable和Future和FutureTask.实例一;

import java.util.concurrent.FutureTask;

/**
@author junmeng.xu
@date  2016年5月19日下午2:38:12
 */
public class CallableTest {

	public static void main(String[] args) {
		
		//创建Callable对象
		RtnThread rt = new RtnThread();
		//使用FutrueTask来包装Callable对象
		FutureTask<Integer> task = new FutureTask<>(rt);
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName() + " 的循环变量i的值 :  " + i);
			if(i == 10){
				//实质还是以Callable对象来创建，并启动线程
				new Thread(task, "有返回值的线程 : ").start();
			}
			
		}
		try {
			//获取线程返回值
			System.out.println("子线程的返回值 : " + task.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
