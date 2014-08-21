package com.lu.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * server open session -- client open session server receive msg -- client
 * receive msg client close session -- server close session
 * 
 * 1400556794687server open 1400556818615server received aa.jpg size is20480
 * 1400556844446server session close
 * 
 * 1400556802136client open client send finished and wait success
 * 1400556831969thr result is success success! 1400556835206client session close
 * 
 * @author luhp
 * 
 */
public class MinaServer
{
	private static final int PORT = 8080;

	public static void main(String[] args)
	{
		/*
		 * IoAcceptor acceptor = new NioSocketAcceptor();
		 * acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		 * acceptor.getFilterChain().addLast("codec", new
		 * ProtocolCodecFilter(new
		 * TextLineCodecFactory(Charset.forName("UTF-8")))); acceptor.bind(new
		 * InetSocketAddress(PORT));
		 */
		
		
		  
		//服务端的实例  
		 NioSocketAcceptor accept=new NioSocketAcceptor();  
		 //添加filter，codec为序列化方式。这里为对象序列化方式，即表示传递的是对象。  
		 accept.getFilterChain().addLast("codec",  
		 new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));  
		//添加filter，日志信息  
		 accept.getFilterChain().addLast("logging", new LoggingFilter());  
		 //设置服务端的handler  
		 accept.setHandler(new FileUploadHandler());  
		 //绑定ip  
		try
		{
			accept.bind(new InetSocketAddress(PORT));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		          
		        System.out.println("upload  server started.");    
	}
}