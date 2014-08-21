package com.lu.classloader;

import java.lang.reflect.Method;
public class Decrypt {  
  

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {  
  
//        SecureRandom sr = new SecureRandom();  
//        FileInputStream fi = new FileInputStream(new File("d:/key.txt"));  
//        byte rawKeyData[] = new byte[fi.available()];  
//        fi.read(rawKeyData);  
//        fi.close();  
//        DESKeySpec dks = new DESKeySpec(rawKeyData);  
//        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);  
//        Cipher cipher = Cipher.getInstance("DES");  
//        cipher.init(Cipher.DECRYPT_MODE, key, sr);  
//        FileInputStream fi2 = new FileInputStream(new File(WebConsts.encFilePath + "HelloWorld.class"));  
//        byte encryptedData[] = new byte[fi2.available()];  
//        fi2.read(encryptedData);  
//        fi2.close();  
//        byte decryptedData[] = cipher.doFinal(encryptedData);  
        
//        FileOutputStream out = new FileOutputStream(WebConsts.encFilePath + "HelloWorld.class");
//        out.write(decryptedData);
//        out.flush();
//        out.close();
        
//        MyClassLoader2 mc = new MyClassLoader2(WebConsts.classpath);  
//        Class cl = mc.findClass("");
//        
//        //Class cl =  MyClassLoader.instance.load("HelloWorld", decryptedData, false);
//        Method mainMethod = cl.getMethod("sayHello");  
//        mainMethod.invoke(null, null);  
    	
    	try { 
            // 每次都创建出一个新的类加载器
            ClassLoader3 cl = new ClassLoader3(new String[]{"com.lu.classloader.HelloWorld"}); 
            Class cls = cl.loadClass("com.lu.classloader.HelloWorld",false); 
            Object foo = cls.newInstance(); 

            Method m = foo.getClass().getMethod("sayHello", new Class[]{}); 
            Object ret = m.invoke(foo, new Object[]{}); 
            System.out.println(ret);
        }  catch(Exception ex) { 
            ex.printStackTrace(); 
        } 
    }  
}  
