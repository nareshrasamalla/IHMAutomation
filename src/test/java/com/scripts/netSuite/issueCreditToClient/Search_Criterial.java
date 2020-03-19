package com.scripts.netSuite.issueCreditToClient;

public class Search_Criterial {

	
	 public static void main(String[] args) {
		 String num= "";
		 String alpha ="";
	      String s = "546545454jhfgh6586586678fgkj nlkfnj897897987";
	      alpha = s.replaceAll("[^a-zA-Z]", "");
	      num = s.replaceAll("[^0-9", "");
	      System.out.println(s);
	  }
}
