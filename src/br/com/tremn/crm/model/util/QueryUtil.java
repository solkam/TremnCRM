package br.com.tremn.crm.model.util;

import java.util.List;

/**
 * Utilitarios para Query usando Criteria
 * @author Solkam
 * @since 02 FEV 2015
 */
public class QueryUtil {

	private static final String LIKE_SIMBOL = "%";
	
	
/*
 * Criteria JPA 2	
 ****************/
	/**
	 * Retorna se uma string está em branco
	 * testando se está null e se tem espaços 
	 * em branco.
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return str!=null && !str.trim().isEmpty();
	}
	
	/**
	 * Verifica se um inteiro é positivo ou zero.
	 * @param num
	 * @return
	 */
	public static boolean isNotNegative(Integer num) {
		return num!=null && num>0;
	}
	
	/**
	 * Verifica se um objeto é null
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return obj!=null;
	}
	
	/**
	 * Verifica se lista está preenchida
	 * @param lista
	 * @return
	 */
	public static boolean isNotEmpty(List<?> lista) {
		return lista!=null && !lista.isEmpty();
	}
	
	
/*
 * Expressões com LIKE	
 *********************/
	private static String nvl(String text) {
		return text==null ? "" : text;
	}
	
	/**
	 * Retorna a expressão de like com modo de comparação INICIAL
	 * @param value
	 * @return expressão like
	 */
	public static String toLikeMatchModeSTART(String value) {
		return nvl(value) + LIKE_SIMBOL;
	}
	
	/**
	 * Retorna a expressão de like com modelo de comparação INICIAL e FINAL
	 * @param value
	 * @return expressao like
	 */
	public static String toLikeMatchModeANY(String value) {
		return LIKE_SIMBOL + nvl(value) + LIKE_SIMBOL;
	}
	
	
}
