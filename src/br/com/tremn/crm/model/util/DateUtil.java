package br.com.tremn.crm.model.util;

import java.util.Calendar;
import java.util.Date;


/**
 * Utilitários para trabalhar com datas
 * @author Solkam
 * @since 01 FEV 2015
 */
public class DateUtil {
	
	/**
	 * Calcula a idade a partir da data de nascimento
	 * @param birthday
	 * @return idade
	 */
	public static Integer calculateAge(Date birthDate) {
		throwIfNull(birthDate);
		
		Calendar birthCalendar = Calendar.getInstance();
		birthCalendar.setTime( birthDate );
		
		Calendar today = Calendar.getInstance();
		
		int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		return age;
	}
	
	
	
	/**
	 * Lança exception se parametro vier null
	 * @param param
	 */
	private static void throwIfNull(Object param) {
		if (param==null) {
			throw new IllegalArgumentException("Parametro null passado para metodo em CalendarUtil");
		}
	}

}
