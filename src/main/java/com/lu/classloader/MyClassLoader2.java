package com.lu.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import com.lu.util.LuUtil;

public class MyClassLoader2 extends ClassLoader {  
    private String fileName;  
  
    public MyClassLoader2(String fileName) {  
        this.fileName = fileName;  
    }  
    
    public static void main(String[] args)
	{
    	MyClassLoader2 loader = new MyClassLoader2("com.lu.classloader.HelloWorld");
    	try
		{
			loader.findClass(loader.fileName);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
  
    @SuppressWarnings("rawtypes")
	protected Class<?> findClass(String className) throws ClassNotFoundException {  
//        Class clazz = this.findLoadedClass(className);  
//        if (null == clazz) {  
//            try {  
//                String classFile = getClassFile(className);  
//                FileInputStream fis = new FileInputStream(classFile);  
//                FileChannel fileC = fis.getChannel();  
//                ByteArrayOutputStream baos = new  
//                        ByteArrayOutputStream();  
//                WritableByteChannel outC = Channels.newChannel(baos);  
//                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
//                while (true) {  
//                    int i = fileC.read(buffer);  
//                    if (i == 0 || i == -1) {  
//                        break;  
//                    }  
//                    buffer.flip();  
//                    outC.write(buffer);  
//                    buffer.clear();  
//                }  
//                fis.close();  
//                byte[] bytes = baos.toByteArray();  
//  
//                clazz = defineClass(className, bytes, 0, bytes.length);  
//            } catch (FileNotFoundException e) {  
//                e.printStackTrace();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  

//        }  
    	byte[] bytes = LuUtil.getDecrptClass("");
        Class clazz = defineClass(className, bytes, 0, bytes.length);  
        Method mainMethod;
		try
		{
			Object obj = clazz.newInstance();
			mainMethod = clazz.getMethod("sayHello");
			mainMethod.invoke(obj, null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
       
        return clazz;  
    }  
    
    @SuppressWarnings("unused")
	private byte[] loadClassBytes(String className) throws  
            ClassNotFoundException {  
        try {  
            String classFile = getClassFile(className);  
            FileInputStream fis = new FileInputStream(classFile);  
            FileChannel fileC = fis.getChannel();  
            ByteArrayOutputStream baos = new  
                    ByteArrayOutputStream();  
            WritableByteChannel outC = Channels.newChannel(baos);  
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
            while (true) {  
                int i = fileC.read(buffer);  
                if (i == 0 || i == -1) {  
                    break;  
                }  
                buffer.flip();  
                outC.write(buffer);  
                buffer.clear();  
            }  
            fis.close();  
            return baos.toByteArray();  
        } catch (IOException fnfe) {  
            throw new ClassNotFoundException(className);  
        }  
    }  
    private String getClassFile(String name) {  
        StringBuffer sb = new StringBuffer(fileName);  
        name = name.replace('.', File.separatorChar) + ".class";  
        sb.append(File.separator + name);  
        return sb.toString();  
    }  
}  