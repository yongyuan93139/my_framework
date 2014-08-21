package com.lu.encrpt;

import java.io.File;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
import org.apache.commons.io.FileUtils;

//RSATest类即为测试类
public class RSATest
{
	//主函数 
	public static void main(String[] args)
	{
		try
		{
//			RSATest encrypt = new RSATest();
//			String encryptText = "encryptText";//输入的明文   
//			KeyPair keyPair = encrypt.generateKey();//调用函数生成密钥对，函数见下   
//			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//			byte[] e = encrypt.encrypt(publicKey, encryptText.getBytes());
//			//调用自己编写的encrypt函数实现加密，   
//			byte[] de = encrypt.decrypt(privateKey, e);
//			//调用自己编写的decrypt函数实现解密，   
//			String deStr = new String(de);
//			System.out.println("解密" + deStr);
//
//			System.out.println(toHexString(e)); //输出结果，采用ASSIC码形式  
//			System.out.println(toHexString(de));
			
			String text = "我是好人";
			
			String publicKeyStr = FileUtils.readFileToString(new File("d:/rsa_public_key.pem"));
			String privateKeyStr = FileUtils.readFileToString(new File("d:/rsa_private_key.pem"));
			
			PrivateKey privateKey = RSAUtil.getPrivateKeyFromString(privateKeyStr);
			PublicKey pubkey = RSAUtil.getPublicKeyFromString(publicKeyStr);

			String enStr = RSAUtil.encrypt(text, pubkey);
			String pt2 = RSAUtil.decrypt(enStr, privateKey);
			System.out.println("plaText=" + text);
			System.out.println("cipText=" + enStr);
			System.out.println("plaText2=" + pt2);
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// generateKey密钥对生成函数的实现
	public KeyPair generateKey() throws NoSuchAlgorithmException
	{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); //返回生成指定算法的 public/private 密钥对的 KeyPairGenerator 对象。  
		keyPairGen.initialize(1024, new SecureRandom());
		//使用给定的随机源（和默认的参数集合）初始化确定密钥大小的密钥对生成器。
		KeyPair keyPair = keyPairGen.generateKeyPair();//生成一个密钥对   
		return keyPair;
	}

	// encrypt加密函数  
	protected byte[] encrypt(RSAPublicKey publicKey, byte[] data)
	{
		if (publicKey != null)
		{
			try
			{
				Cipher cipher = Cipher.getInstance("RSA");
				//返回实现指定转换的 Cipher 对象。   
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				//用密钥初始化此 Cipher,第一个参数表示加密   
				return cipher.doFinal(data);
				//按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此 Cipher 的初始化方式）。
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	//  decrypt解密函数
	protected byte[] decrypt(RSAPrivateKey privateKey, byte[] raw)
	{
		if (privateKey != null)
		{
			try
			{
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey); //第一个参数表示解密  
				return cipher.doFinal(raw);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	// toHexString将字节数组变为ASSIC码表示的字符串
	public static String toHexString(byte[] b)
	{
		StringBuilder sb = new StringBuilder(b.length * 2);
		//构造一个不带任何字符的字符串生成器，其初始容量由 capacity 参数指定。   
		for (int i = 0; i < b.length; i++)
		{
			sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
			// append 方法始终将这些字符添加到生成器的末端；而 insert 方法则在指定的点添加字符。
			//调用HEXCHAR方法  
			sb.append(HEXCHAR[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	//构建一个HEXCHAR数组  
	private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
