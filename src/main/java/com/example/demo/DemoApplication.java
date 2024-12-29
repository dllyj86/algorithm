package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

	public static volatile int value = 0;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		for (int i = 0; i < 10; i++) {
			new Thread(new Task(i)).start();
		}
	}

	public static class Task implements Runnable {

		private final int threadId;

		public Task(int threadId) {

			this.threadId = threadId;
		}

		public void run() {
			for (int i = 0; i < 10; i++) {
				int count = threadLocal.get();
				threadLocal.set(++count);
//				System.out.println("Thread " + this.threadId + " - Count : " + threadLocal.get());


				value++;
				System.out.println(threadId + ":" + value);
			}

		}
	}

}
