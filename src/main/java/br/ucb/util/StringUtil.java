package br.ucb.util;

public class StringUtil {

	public static boolean isNotNullIsNotEmpty(String valor){
		if(valor != null && !valor.isEmpty()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	} 
	
}
