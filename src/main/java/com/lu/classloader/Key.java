package com.lu.classloader;

import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
  
class Key {  
  
    private String keyName;  
  
    public Key() {  
  
    }  
  
    public Key(String keyName) {  
        this.keyName = keyName;  
    }  
  
    public void createKey(String keyName) throws Exception {  
  
        SecureRandom sr = new SecureRandom();  
        KeyGenerator kg = KeyGenerator.getInstance("DES");  
        kg.init(sr);  
        SecretKey key = kg.generateKey();  
        System.out.println(key.toString());  
        byte rawKeyData[] = key.getEncoded();  
        FileOutputStream fo = new FileOutputStream(new File(keyName));  
        fo.write(rawKeyData);  
        fo.close();  
    }  
  
    public static void main(String args[]) {  
        try {  
            new Key("").createKey("d:/key.txt");  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
}  