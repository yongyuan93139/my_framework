package com.lu.classloader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.lu.util.LuUtil;

public class MyClassLoader extends ClassLoader
{
	public static ConcurrentHashMap<String, Class<?>> classes = new ConcurrentHashMap<String, Class<?>>();

	public static MyClassLoader instance = new MyClassLoader();
	public static Lock lock = new ReentrantLock();

	//构造自定义Classloader, 并指定父Classloader  
	public MyClassLoader()
	{
		super(Thread.currentThread().getContextClassLoader());
	}

	public Class<?> load(String name, byte[] data, boolean resolve)
	{
		Class<?> klass = defineClass(name, data, 0, data.length);
		if (resolve)
			resolveClass(klass);
		classes.put(name, klass);
		return klass;
	}

	public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
	{
		Object value = classes.get(name); // 检查缓存  
		if (value != null)
		{
			Class<?> klass = (Class<?>) value;
			if (resolve)
				resolveClass(klass);
			return klass;

		}
		else
		{ // 缓存中不存在  
			byte[] data = LuUtil.getDecrptClass("");/*
													 * read(findClassFile(name));
													 */// 读取类文件  
			if (data == null)
				return super.loadClass(name, resolve); // 交由父classloader去load类文件  
			else
			{
				try
				{
					lock.lock();
					Object cc = classes.get(name); // 检查缓存  
					if (cc != null)
					{
						return (Class<?>) cc;
					}
					else
						return instance.load(name, data, resolve); // 自己load类文件  
				}
				finally
				{
					lock.unlock();
				}
			}
		}
	}

}