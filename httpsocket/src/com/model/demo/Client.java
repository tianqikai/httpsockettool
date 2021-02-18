package com.model.demo;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
/** * Socket客户端
* 功能说明： * * @author 大智若愚的小懂 * @Date 2016年8月30日 * @version 1.0 */
public class Client {
	/** * 入口 * @param args */
	public static void main(String[] args) {
		SocketClient client = null;
		try {
			client = TestClientFactory.createClient();
			client.send(String.format("Hello,Server!I'm %d.这周末天气如何。", client.client.getLocalPort()));
			client.receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 开启三个客户端，一个线程代表一个客户端 
//		for (int i = 0; i < 3; i++) {
//			new Thread(new Runnable() {
//				@Override public void run() {
//					try {
//						TestClient client = TestClientFactory.createClient();
//						client.send(String.format("Hello,Server!I'm %d.这周末天气如何。", client.client.getLocalPort()));
//						client.receive();
//					}
//					catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			).start();
//		}
	}
	/** * 生产测试客户端的工厂 */
	static class TestClientFactory {
		public static SocketClient createClient() throws Exception {
			return new SocketClient("127.0.0.1", 8899);
		}
	}
}