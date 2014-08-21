package com.lu.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.io.FileUtils;
import com.lu.consts.WebConsts;
import com.lu.encrpt.RSAUtil;

public class SysInitServlet extends HttpServlet
{
	private static final long serialVersionUID = -6271365901574689502L;

	public void init() throws ServletException {
		try
		{
			DesEncrptClass();
			//RSAEncrptClass(); RSA 只支持最多117byte加密
		}
		catch (Exception e)
		{
			throw new ServletException("sys init failure..." + e.getMessage());
		}
    }

	private void RSAEncrptClass() throws Exception
	{
		String className = "com.lu.classloader.HelloWorld";
		File classFile = new File(WebConsts.classpath + className.replace(".", "/") + ".class");
		byte[] plainCls = FileUtils.readFileToByteArray(classFile);
		
		String publicKeyStr = FileUtils.readFileToString(new File("d:/rsa_public_key.pem"));
		PublicKey pubkey = RSAUtil.getPublicKeyFromString(publicKeyStr);

		byte[] encrptCls = RSAUtil.encrypt(plainCls, pubkey);
		FileUtils.writeByteArrayToFile(classFile, encrptCls);
		
	}

	private void DesEncrptClass() throws Exception
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
