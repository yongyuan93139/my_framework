package com.lu.mina;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class FileUploadHandler extends IoHandlerAdapter
{

	private BufferedOutputStream out;

	private int count;

	private String fileName = "classpath:out.jpg";

	private static final Log log = LogFactory.getLog(FileUploadHandler.class);

	public void sessionOpened(IoSession session) throws Exception
	{
		System.out.println(System.currentTimeMillis() + "server open");
		count = 0;
		//新建一个文件输入对象BufferedOutputStream，随便定义新文件的位置  
		out = new BufferedOutputStream(new FileOutputStream("out.jpg"));
	}

	public void exceptionCaught(IoSession session, Throwable cause) throws Exception
	{
		System.out.println(System.currentTimeMillis() + "exception");
		session.close(true);
		super.exceptionCaught(session, cause);
	}

	public void messageReceived(IoSession session, Object message)
	{
		System.out.println(System.currentTimeMillis() + "server received");

		try
		{
			if (message instanceof FileUploadRequest)
			{
				//FileUploadRequest 为传递过程中使用的DO。  
				FileUploadRequest request = (FileUploadRequest) message;
				System.out.println(request.getFilename());
				out.write(request.getFileContent());
				count += request.getFileContent().length;

			}
			else if (message instanceof String)
			{
				if (((String) message).equals("finish"))
				{
					System.out.println("size is" + count);
					//这里是进行文件传输后，要进行flush和close否则传递的文件不完整。  
					out.flush();
					//out.close();
					//回执客户端信息，上传文件成功  
					session.write("success");
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void sessionClosed(IoSession session) throws Exception
	{
		System.out.println(System.currentTimeMillis() + "server session close");
		out.close();
	}
}