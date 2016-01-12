package com.wen.content;

import java.io.UnsupportedEncodingException;

import kevin.zhang.NLPIR;

public class NlpirHandler {
	
	public static void main(String[] args){
		
		NLPIR nlpir = new NLPIR();
		try {
			if (!NLPIR.NLPIR_Init("./file/".getBytes("utf-8"), 1)) {
				System.out.println("NLPIR fail to initial...");
			}
			byte [] resBytes = nlpir.NLPIR_GetFileKeyWords("taobaoEvent2.txt".getBytes("utf-8"), 50, true);
			String resultStr  = new String(resBytes,"utf-8");
			System.out.println(resultStr);
			NLPIR.NLPIR_Exit();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
