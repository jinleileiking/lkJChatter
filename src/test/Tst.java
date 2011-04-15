package test;

import java.io.UnsupportedEncodingException;

import tools.HexStr2Byte;

public class Tst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "你";
		byte[] as8Bytes;
		try {
			as8Bytes = str.getBytes("utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		System.out.println("utf8") ;
		System.out.println(HexStr2Byte.byte2HexStr( as8Bytes)) ;
		
		try {
			as8Bytes = str.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		System.out.println("GBK") ;
		System.out.println(HexStr2Byte.byte2HexStr( as8Bytes)) ;
		
		try {
			as8Bytes = str.getBytes("UNICODE");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		System.out.println("UNICODE") ;
		System.out.println(HexStr2Byte.byte2HexStr( as8Bytes)) ;
		
//		try {
//			as8Bytes = str.getBytes("utf8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return;
//		}
//		
//		
//		System.out.println(HexStr2Byte.byte2HexStr( as8Bytes)) ;
//        char c = '你';
//        int i = c;
//        System.out.println(c);
//        System.out.println(i);
	}

}
