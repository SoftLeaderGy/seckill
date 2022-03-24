package com.yang.seckilldemo.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @Description: MD5工具类
 * @Author: Guo.Yang
 * @Date: 2022/03/22/16:35
 */
@Component
public class MD5Util {

    // 加密的用的言， 加密密钥
    private static final String salt = "1a2b3c4d";

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * 第一次加密（页面输入后到服务端的加密）
     * @param inputPass
     * @return
     */
    public static String inputPassToFromPass(String inputPass){
        String str = "" + salt.charAt(0)+ salt.charAt(2)+ inputPass+ salt.charAt(5)+ salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二次加密 （服务端到存入数据库的密码加密）
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBpass(String formPass,String salt){
        String str = "" + salt.charAt(0)+salt.charAt(2)+ formPass + salt.charAt(5)+ salt.charAt(4);
        return md5(str);
    }

    /**
     * 这个是直接从输入的密码  直接经过两次加密直接得到存入数据库的密码
     * @param inputpass
     * @param salt
     * @return
     */
    public static String inputPassToDBPass(String inputpass , String salt){
        String formPass = inputPassToFromPass(inputpass);
        String dbPass = formPassToDBpass(formPass,salt);
        return dbPass;
    }


    public static void main(String[] args) {
        // d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(inputPassToFromPass("123456"));

        // b7797cce01b4b131b433b6acf4add449
        System.out.println(formPassToDBpass("d3b1294a61a07da9b49b6e22b2cbd7f9", "1a2b3c4d"));

        // b7797cce01b4b131b433b6acf4add449
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
