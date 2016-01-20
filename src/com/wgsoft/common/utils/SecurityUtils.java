package com.wgsoft.common.utils;

import java.security.MessageDigest;

public class SecurityUtils {
	/**
	 * @desc:转成md5
	 * @param inStr
	 * @return
	 * @return String
	 * @date： 2015-9-9 上午08:57:56
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	/**
	 * @desc: 加密后解密
	 * @param inStr
	 * @return
	 * @return String
	 * @date： 2015-9-9 上午08:56:44
	 */
	public static String getPwdForDb(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	/**
	 * 
	 * @desc:设置加密后的密码保存到数据库
	 * @param inStr
	 * @return
	 * @return String
	 * @date： 2015-9-9 上午08:56:27
	 */
	public static String setPwdForDb(String inStr) {
		inStr = MD5(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	/**
	 * @desc:解密
	 * @param inStr
	 * @return
	 * @return String
	 * @date： 2015-9-9 上午08:57:38
	 */
	public static String transferPwdFormDb(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		char[] b = s.toCharArray();
		for (int i = 0; i < b.length; i++) {
			b[i] = (char) (b[i] ^ 't');
		}
		String k = new String(b);
		return k;
	}

	/**
	 * @desc:检查输入与数据库密码是否一致
	 * @param inputStr
	 * @param pwd
	 * @return
	 * @return boolean
	 * @date： 2015-9-9 上午08:57:17
	 */
	public static boolean checkLogin(String inputStr, String pwd) {
		return (inputStr.hashCode() == pwd.hashCode());
	}

	// ����������
	public static void main(String args[]) {
		String s = new String("123");

		System.out.println("原始：" + s);
		System.out.println("MD5后：" + MD5(s));
		System.out.println("MD5后再加密：" + getPwdForDb(MD5(s)));
		System.out.println("解密为MD5后的：" + transferPwdFormDb(MD5(s)));
	}

}
