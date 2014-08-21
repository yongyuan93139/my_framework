package com.lu.encrpt;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.crypto.Cipher;
import org.apache.commons.net.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSAUtil
{
    public static void main(String[] args) throws Exception{
		String text = "中国人";
		RSAUtil.init();
		KeyPair kp = RSAUtil.generateKey();
		PublicKey pubkey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		String pubkeyStr = RSAUtil.getKeyAsString(pubkey);
		String privateKeyStr = RSAUtil.getKeyAsString(privateKey);

//		privateKeyStr = "-----BEGIN RSA PRIVATE KEY-----MIICXAIBAAKBgQCmJFvYWGh0kVkPusr1iOXoqOtttnTiNOT+ENdxM4eof+rC01MnHgG8QbzYtgrCJrTUI0vPPMqkdCnHQqE8D4LiZWN36KWgTvzPmUEH7dLVoc5EN3EvcgCv9LUQCiesniu+/0QtUnqkQNR3nQJBi+ePJKqcq0mtwXwKtKRCs/+YHQIDAQABAoGAZCDbfXpikYYbf8BamznDHcHYCUoCDWZFb90kimO86U8WZA5KOfP6pcOUlaK4vyPmrUcMZDxGAHDF0QIV/AV5IhHSpxwXAGL1+PQt53bM4EvY7lWlp2xHps07RhgUeU1wWYszw2CIrO2qIJ8xHsg0kKRX7lx03EEWLL2NIZlCQ50CQQDQCathmhJnoJnLaz4UyUOJpvraLM+q3DPwjSYr0hs4AkhUBHDn78SeqfkLsQWATdOFiIDy77HdEACEH5rcfH8DAkEAzHIDp4Xyra3QGGSNaonaHLVfebiRT1t6vyJFxHgoQ11yAVJE9CGrVP2HxPG11lBWWuy1hhKyHRfxgn2YpzTSXwJAcFmYfYhYgjBgflP3XgBngfJ0rNjgefSb9EA2Kho46uGIsB8J3qhFNi6zOdYrE2R0ZDwY75n1I16d+LycxJBTgwJBAMQkImNnO/LqjXnJhm8PACWI0Kd7rlU/Q5z56YtbZ+1xSj4ASs0ZPEHW1pnY94Y8Hw0uidyuNRl6biauDnVkVOsCQGgR2juhhiilB2AC2UN3uJ6GjIVctpPokJD/12QEp0VZEmgYb51qvXTwDpoAxxxYA6mrFhSKlb4YpRg/pFGBOgc=-----END RSA PRIVATE KEY-----";
//		pubkeyStr = "-----BEGIN PUBLIC KEY-----MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmJFvYWGh0kVkPusr1iOXoqOtttnTiNOT+ENdxM4eof+rC01MnHgG8QbzYtgrCJrTUI0vPPMqkdCnHQqE8D4LiZWN36KWgTvzPmUEH7dLVoc5EN3EvcgCv9LUQCiesniu+/0QtUnqkQNR3nQJBi+ePJKqcq0mtwXwKtKRCs/+YHQIDAQAB-----END PUBLIC KEY-----";

		System.out.println("privateKeyStr = \n" + privateKeyStr );
		System.out.println("pubkeyStr = \n" + pubkeyStr );
		
		privateKey = getPrivateKeyFromString(privateKeyStr);
		pubkey = getPublicKeyFromString(pubkeyStr);

		String enStr = RSAUtil.encrypt(text, pubkey);
		String pt2 = RSAUtil.decrypt(enStr, privateKey);
		System.out.println("plaText=" + text);
		System.out.println("cipText=" + enStr);
		System.out.println("plaText2=" + pt2);
    }

	@SuppressWarnings("unused")
	private static void extractDb() throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, Exception
	{
		RSAUtil.init();
		KeyPair kp = RSAUtil.generateKey();
        PublicKey pu=kp.getPublic();
        PrivateKey pr=kp.getPrivate();
		String pu2 = RSAUtil.getKeyAsString(pu);
		String pr2 = RSAUtil.getKeyAsString(pr);
        
		//将密钥保存到数据库中

		String ui = "as";
		String db = "as";

		//动态导入数据库的驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取数据连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, ui, ui);
		//编写SQL语句
		String sql = "INSERT INTO rsa(userId,pubKey,priKey) VALUES ('" + ui + "','" + pu2 + "','" + pr2 + "')";
		//执行SQL语句
		Statement stat = conn.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		conn.close();
		System.out.println("Save Key Success!");

		//将密钥从数据库中取出来

		//动态导入数据库的驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取数据连接
		Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, ui, ui);
		//编写SQL语句
		String sql2 = "select priKey,pubKey from " + db + ".rsa where userId='" + ui + "'";
		//执行SQL语句
		Statement stat2 = conn2.createStatement();
		ResultSet rs2 = stat2.executeQuery(sql2);

		//移动到下一条记录
		boolean b = rs2.next();
		//检查数据是否存在
		if (b == false)
		{
			System.out.println("Error!");
		}
		else
		{

			//取得as的私钥，公钥
			String asPriKey = rs2.getString("priKey");
			String asPubKey = rs2.getString("pubKey");

			System.out.println("asPriKey=" + asPriKey);
			System.out.println("asPubKey=" + asPubKey);

			//明文    
			String pt = "12345";
			//公钥加密，私钥解密
			String ct = RSAUtil.encrypt(pt, pu);
			String pt2 = RSAUtil.decrypt(ct, pr);
			System.out.println("plaText=" + pt);
			System.out.println("cipText=" + ct);
			System.out.println("plaText2=" + pt2);

			//私钥加密，公钥解密
			String ctt = RSAUtil.encrypt(pt, pr);
			String ptt2 = RSAUtil.decrypt(ctt, pu);
			System.out.println("cipTextt=" + ctt);
			System.out.println("plaTextt2=" + ptt2);
		}
        
		rs2.close();
		stat2.close();
		conn2.close();
	}
    
    public static final String ALGORITHM="RSA";
    public static void init(){
        
        Security.addProvider(new BouncyCastleProvider());
        
    }
    public static KeyPair generateKey() throws NoSuchAlgorithmException{

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        return key;
    }
    public String subSecEncrypt(String pText,Key key) throws Exception{
        
        //分段加密，注意：分段加密的密文长度都是174字节
        int pTextLen=pText.length();
        int m=pTextLen/117;
        int n=pTextLen-m*117;
        String cText=""; //密文
        //对117整数倍的子串加密，拼接
        for(int i=0;i<m;i++){
            String pTextSub=pText.substring(i*117, (i+1)*117); //获取子串
            cText=cText+encrypt(pTextSub, key); //分段加密，拼接
        }
        //对剩下的子串加密，拼接
        String pTextSub2=pText.substring(pTextLen-n, pTextLen);
        cText=cText+encrypt(pTextSub2, key);
        return cText;
    }
    public static String subSecDecrypt(String cText,Key key) throws Exception{
        
        //分段解密，注意：分段加解密的密文长度都是174字节
        int cTextLen=cText.length();
        int m=cTextLen/174;
        //int n=cTextLen-m*174;
        String pText=""; //明文
        for(int i=0;i<m;i++){
            String cTextSub=cText.substring(i*174, (i+1)*174);
            pText=pText+decrypt(cTextSub, key);
        }
        return pText;
    }
    public static byte[] encrypt(byte[] text, Key key) throws Exception
    {
        byte[] cipherText=null;
        Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipherText=cipher.doFinal(text);
        return cipherText;
    }
    public static String encrypt(String text, Key key) throws Exception
    {
        String encryptedText;
        byte[] cipherText=encrypt(text.getBytes("UTF8"),key);
        encryptedText=encodeBASE64(cipherText);
        return encryptedText;
    }
    public static byte[] decrypt(byte[] text, Key key) throws Exception
    {
        byte[] decryptedText=null;
        Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        decryptedText=cipher.doFinal(text);
        return decryptedText;
    }
    public static String decrypt(String text, Key key) throws Exception
    {
        String result;
        byte[] dectyptedText=decrypt(decodeBASE64(text),key);
        result=new String(dectyptedText,"UTF8");
        return result;
    }
    //将String类型转换为PrivateKey类型
    public static PrivateKey getPrivateKeyFromString(String key) throws Exception
    {
        KeyFactory keyFactory=KeyFactory.getInstance(ALGORITHM);

		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        PrivateKey privateKey=keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }
    //将String类型转换为PublicKey类型
    public static PublicKey getPublicKeyFromString(String key) throws Exception
    {
        KeyFactory keyFactory=KeyFactory.getInstance(ALGORITHM);
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        PublicKey publicKey=keyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }
    //将Key类型转换为String类型
    public static String getKeyAsString(Key key)
    {
        byte[] keyBytes = key.getEncoded();
		return Base64.encodeBase64String(keyBytes);
    }
    public static String encodeBASE64(byte[] bytes)
    {
		return Base64.encodeBase64String(bytes);
    }
    public static byte[] decodeBASE64(String text) throws IOException
    {
		return Base64.decodeBase64(text);
    }
}