package com.lu.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
  
public class Crypt {  
  
    public static void main(String[] args) throws Exception {  
  
        //DesEncrpt();
    	RASEncrpt();
    }

	private static void RASEncrpt()
	{
		// TODO Auto-generated method stub
		
	}

	private static void DesEncrpt() throws FileNotFoundException, IOException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		SecureRandom sr = new SecureRandom();  
        FileInputStream fi = new FileInputStream(new File("d:/key.txt"));  
        byte rawKeyData[] = new byte[fi.available()];  
        fi.read(rawKeyData);  
        fi.close();  
        DESKeySpec dks = new DESKeySpec(rawKeyData);  
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);  
        Cipher cipher = Cipher.getInstance("DES");  
        cipher.init(Cipher.ENCRYPT_MODE, key, sr); 
        String helloPath = WebConsts.encFilePath + "HelloWorld.class";
        final File f = new File(helloPath);
//        InputStream fi2 =  HelloWorld.class.getClassLoader().getResourceAsStream("HelloWorld.class");
        FileInputStream fi2 = new FileInputStream(f/*new File("d:/HelloWorld.class")*/);  
        byte data[] = new byte[fi2.available()];  
        fi2.read(data);  
        fi2.close();  
        byte encryptedData[] = cipher.doFinal(data);  ;
        FileOutputStream fo = new FileOutputStream(new File(helloPath));  
        fo.write(encryptedData);  
        fo.close();
	}  
}  
