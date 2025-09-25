package practiceproblems.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultithreadingClass {

	private volatile static int a=0;
	private static final int MAX=10;
	
	public static void main(String[] args) {
		MultithreadingClass obj=new MultithreadingClass();
		Thread t1=new Thread(
			() -> obj.printOdd1()
		);
		Thread t2=new Thread(
			() -> obj.printEven1()
		);
		t1.start();
		t2.start();
	}
	
	synchronized void printOdd() {
		while(a<=MAX) {
			if(a%2!=0) {
				System.out.println("Odd: "+ a);
				a++;
				notify();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	synchronized void printEven() {
		while(a<=MAX) {
			if(a%2==0) {
				System.out.println("Even: "+ a);
				a++;
				notify();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	void printOdd1() {
		while(a<MAX) {
			lock.lock();
			if(a%2!=0) {
				System.out.println("Odd: "+a);
				a++;
				condition.signal();
			} else {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
	}
	void printEven1() {
		while(a<MAX) {
			lock.lock();
			if(a%2==0) {
				System.out.println("Even: "+a);
				a++;
				condition.signal();
			} else {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
	}
}
