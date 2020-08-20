package com.wsw.shirolearn.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description md5加密
 * @Author wsw
 * @Date 2020/8/6 15:10
 */
public class MD5Util {

    public static String getMd5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            byte[] digest = md5.digest();
            String result = "";
            for (int i = 0; i < digest.length; i++) {
                /**
                 * result:为8位。如：ffffffe1；.substring方法截取后两位数，如：e1
                 */
                result += Integer.toHexString((0x000000ff & digest[i]) | 0xffffff00).substring(6);
//                System.out.println(digest[i]);
//                System.out.println(0x000000ff & digest[i]);
//                System.out.println((0x000000ff & digest[i]) | 0xffffff00);
//                System.out.println(Integer.toHexString((0x000000ff & digest[i]) | 0xffffff00));
//                System.out.println("====================================================");
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMd5ForBalt(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            byte[] digest = md5.digest();
            StringBuilder sb = new StringBuilder();
            int i;
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                System.out.println("===" + i);
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(0);
                System.out.println("---" + i);
                sb.append(Integer.toHexString(i));//通过Integer.toHexString方法把值变为16进制
            }
//            return sb.toString().substring(0, 6);//从下标0开始，length目的是截取多少长度的值
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getMd5("123456"));
        System.out.println(MD5Util.getMd5ForBalt("123123"));
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(new BigInteger("ffffff00", 16));
        String s = "10101";//1+4+16==21
        System.out.println(Integer.parseInt(s, 10));//结果是21
        System.out.println(new BigInteger(s, 10));

    }
}
