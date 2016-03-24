package com.pccw.system.utils;

import java.security.MessageDigest;

public class SHAUtil {
	public static String shaEncode(String inStr){
        MessageDigest sha = null;
        StringBuffer hexValue = new StringBuffer();
        try {
        	
            sha = MessageDigest.getInstance("SHA");
            byte[] byteArray = inStr.getBytes("UTF-8");
        	byte[] md5Bytes = sha.digest(byteArray);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) { 
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        return hexValue.toString();
    }

}
