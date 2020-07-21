package ar.com.avaco.educacion.domain.entities;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public enum Dia {

	LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
	
	
	String output = DayOfWeek.MONDAY.getDisplayName( TextStyle.FULL , Locale.forLanguageTag("es_AR")) ;  // Or Locale.US, etc.
	
}
