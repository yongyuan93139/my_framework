package com.lu.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import com.lu.consts.WebConsts;

class ClassLoader3 extends ClassLoader { 

    @SuppressWarnings("rawtypes")
	private Hashtable<String,Class> dynaclazns; // 需要由该类加载器直接加载的类名,和class的map
    private HashMap<String, byte[]> encryptClassByteMap;

    public ClassLoader3(String[] clazns) throws IOException { 
        super(null); // 指定父类加载器为 null 
        initEncrptClassMap(clazns);
        loadClassByMe(clazns); 
    } 

    private void initEncrptClassMap(String[] classNames)
	{
    	encryptClassByteMap = new HashMap<String, byte[]>();
    	for(String className : classNames){
    		encryptClassByteMap.put(className, getDecrptClass(className));
    	}
	}

	@SuppressWarnings("rawtypes")
	private void loadClassByMe(String[] clazns) throws IOException { 
		dynaclazns =  new Hashtable<String,Class>(); 
        for (int i = 0; i < clazns.length; i++) { 
            Class clazz = instantiateClass(clazns[i]);/*loadDirectly(clazns[i]); */
            dynaclazns.put(clazns[i], clazz);
        } 
    } 

    /*private Class loadDirectly(String name) throws IOException { 
        Class cls = null; 
        StringBuffer sb = new StringBuffer(basedir); 
        String classname = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + classname); 
        File classF = new File(sb.toString()); 
        cls = instantiateClass(name,new FileInputStream(classF),classF.length()); 
        return cls; 
    }*/   		

//    private Class instantiateClass(String name,InputStream fin,long len) throws IOException{ 
//        byte[] raw = new byte[(int) len]; 
//        fin.read(raw); 
//        fin.close(); 
//        byte[] raw = LuUtil.getDecrptClass(name);
//    	byte[] raw = encryptClassByteMap.get(name);/*getDecrptClass(name);*/
//        return defineClass(name,raw,0,raw.length); 
//    } 
    
    @SuppressWarnings("rawtypes")
	private Class instantiateClass(String name) throws IOException{ 
//      byte[] raw = LuUtil.getDecrptClass(name);
      byte[] raw = encryptClassByteMap.get(name);/*getDecrptClass(name);*/
      return defineClass(name,raw,0,raw.length); 
  } 
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Class loadClass(String name, boolean resolve) 
            throws ClassNotFoundException { 
        Class cls = null; 
        cls = findLoadedClass(name); 
        if(cls == null){
        	cls = this.dynaclazns.get(name);
        }
        if(!this.dynaclazns.contains(name) && cls == null) 
            cls = getSystemClassLoader().loadClass(name); 
        if (cls == null) 
            throw new ClassNotFoundException(name); 
        if (resolve) 
            resolveClass(cls); 
        return cls; 
    } 
	
	public byte[] getDecrptClass(String classname){
		try
		{
			SecureRandom sr = new SecureRandom();  
	        FileInputStream fi = new FileInputStream(new File("d:/key.txt"));  
	        byte rawKeyData[] = new byte[fi.available()];  
	        fi.read(rawKeyData);  
	        fi.close();  
	        DESKeySpec dks = new DESKeySpec(rawKeyData);  
	        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);  
	        Cipher cipher = Cipher.getInstance("DES");  
	        cipher.init(Cipher.DECRYPT_MODE, key, sr);  
	        classname = classname.replace(".", "/");
	        FileInputStream fi2 = new FileInputStream(new File(WebConsts.classpath + classname + ".class"));  
	        byte encryptedData[] = new byte[fi2.available()];  
	        fi2.read(encryptedData);  
	        fi2.close();  
	        byte decryptedData[] = cipher.doFinal(encryptedData);  
	        
	        
	        return decryptedData;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args)
	{
		try
		{
			ClassLoader3 cl = new ClassLoader3(new String[]{"com.lu.classloader.HelloWorld"}); 
	         @SuppressWarnings("rawtypes")
			Class cls = cl.loadClass("com.lu.classloader.HelloWorld",false); 
	         Object foo = cls.newInstance(); 

	         Method m = foo.getClass().getMethod("sayHello", new Class[]{}); 
	         Object ret = m.invoke(foo, new Object[]{}); 
	         System.out.println(ret);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		 
	}

}
