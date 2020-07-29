package ar.com.avaco.educacion.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import ar.com.avaco.educacion.domain.entities.Aula;

public class SolapaUtils {

	/**
	 * Compara dos aulas si su dia y hora se solapan
	 * 
	 * @param aula1 Aula 1
	 * @param aula2 Aula 2
	 * @param duracionClase en Minutos
	 * @return
	 */
	public static boolean seSolapanAulas(Aula aula1, Aula aula2, int duracionClase) {
		return seSolapan(aula1.getDia(), aula2.getDia(), aula1.getHora(), aula2.getHora(), duracionClase);
	}
	
	public static boolean seSolapan(Date dia1, Date dia2, Integer hora1, Integer hora2, int duracionClase) {
	
		if (dia1 == null|| dia2 == null|| hora1 == null|| hora2 == null)
			return false;
		
		//Si es el mismo dia		
		if (dia1.toString().equals(dia2.toString())) {
			//Si la hora1 esta entre 
			LocalTime aula1LT = LocalTime.parse(hora1.toString());
			LocalTime aula2LT = LocalTime.parse(hora2.toString());
			long minutos=Math.abs(ChronoUnit.MINUTES.between(aula1LT, aula2LT));
			if (minutos < duracionClase)
				return true;
		}
		
		return false;		
	}
	
}
