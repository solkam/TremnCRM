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
	 * Retorna se uma string est� em branco
	 * testando se est� null e se tem espa�os 
	 * em branco.
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return str!=null && !str.trim().isEmpty();
	}
	
	/**
	 * Verifica se um inteiro � positivo ou zero.
	 * @param num
	 * @return
	 */
	public static boolean isNotNegative(Integer num) {
		return num!=null && num>0;
	}
	
	/**
	 * Verifica se um objeto � null
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj) {
		return obj!=null;
	}
	
	/**
	 * Verifica se lista est� preenchida
	 * @param lista
	 * @return
	 */
	public static boolean isNotEmpty(List<?> lista) {
		return lista!=null && !lista.isEmpty();
	}
	
	
/*
 * Express�es com LIKE	
 *********************/
	private static String nvl(String text) {
		return text==null ? "" : text;
	}
	
	/**
	 * Retorna a express�o de like com modo de compara��o INICIAL
	 * @param value
	 * @return express�o like
	 */
	public static String toLikeMatchModeSTART(String value) {
		return nvl(value) + LIKE_SIMBOL;
	}
	
	/**
	 * Retorna a express�o de like com modelo de compara��o INICIAL e FINAL
	 * @param value
	 * @return expressao like
	 */
	public static String toLikeMatchModeANY(String value) {
		return LIKE_SIMBOL + nvl(value) + LIKE_SIMBOL;
	}
	
	
}
