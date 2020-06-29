package ar.com.avaco.educacion.domain.entities.cliente;

/**
 * Determina el tipo de cliente. Un inversor es quien invierte su dinero en un
 * producto financiero. El solicitante quien pide dinero a traves de un producto
 * financiero. El tipo mixto es quien tiene inversiones y prestamos tomados.
 * 
 * @author beto
 *
 */
public enum TipoPerfil {

	INVERSOR("Inversor"), 
	SOLICITANTE("Solicitante"), 
	MIXTO("Mixto");

	private String label;

	TipoPerfil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
