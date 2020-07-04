package ar.com.avaco.educacion.domain.entities.aulaVirtual;

import java.io.Serializable;

public class Clase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5078607598626738123L;

	protected String idClase;
	
	protected String url;

	public String getIdClase() {
		return idClase;
	}

	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
