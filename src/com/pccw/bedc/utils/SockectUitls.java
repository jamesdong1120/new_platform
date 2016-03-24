package com.pccw.bedc.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/*******************************************
 * <p>Title: Socket工具类</p>
 * <p>Description: </p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月15日 下午2:41:58
 ******************************************/
public class SockectUitls {
	private static String characterEncoding = "UTF-8";
	private static String revCharacterEncoding = "UTF-8";
	/**
	 * 通过socket发送信息
	 * @param socketIp
	 * @param socketPort
	 * @param sendMsg
	 * @param timeout
	 * @return
	 */
	public static String send(String socketIp,Integer socketPort,String sendMsg,Integer timeout){
		Socket socket=null;
		try {
			socket=new Socket(socketIp,socketPort);
			socket.setSoTimeout(timeout);
			

			PrintWriter out = new PrintWriter( new OutputStreamWriter( socket.getOutputStream() , characterEncoding ) , true );			
			InputStream in = socket.getInputStream() ;
			
			out.println(sendMsg);
			byte buf[] = new byte[10240];
			ByteArrayOutputStream content = new ByteArrayOutputStream();
			int len;
			while ((len = in.read(buf)) > 0) {
				content.write(buf, 0, len);
				break;
			}

			byte[] byteArray = content.toByteArray();
			String recmsg = new String(byteArray, revCharacterEncoding);
			in.close();
			out.close();
			return recmsg;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送报文失败 错误在com.pccw.bedc.utils.SockectUitls中");
		}finally{
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
