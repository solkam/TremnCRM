package br.com.tremn.crm.model.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Utilitários para se trabalhar com datas
 * @author Solkam
 * @since 01 FEV 2015
 */
public class DateUtil {
	
	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
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
	
	
	public static boolean isAValidDate(int year, int month, int day) {
		try {
			GregorianCalendar calendarAux = new GregorianCalendar(year, month-1, day);
			if (day == calendarAux.get(Calendar.DAY_OF_MONTH)) {
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			return false;
		}
	}
	
	
	public static Date buildDate(int year, int month, int day) {
		if (isAValidDate(year, month, day)) {
			return new GregorianCalendar(year, month-1, day).getTime();
		} else {
			return null; 
		}
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
