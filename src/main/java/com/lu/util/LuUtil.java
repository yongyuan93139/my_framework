package com.lu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import com.lu.consts.WebConsts;

public class LuUtil
{
	public static byte[] getDecrptClass(String classname){
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
}
