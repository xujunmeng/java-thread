package Callable和Future和FutureTask.test1;

import java.util.concurrent.FutureTask;

/**
@author junmeng.xu
@date  2016年5月19日下午3:21:52
 */
public class CallableTest {

	public static void main(String[] args) {
		CallableThread callableThread = new CallableThread();
		FutureTask<Integer> futureTask = new FutureTask<>(callableThread);
		for(int i = 0 ; i < 100 ; i++){
			System.out.println(Thread.currentThread().getName() + " 变量的值为 :  " + i);
			if( i == 20){
				new Thread(futureTask).start();
			}
		}
		try {
			System.out.println("返回值为 : " + futureTask.get());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
