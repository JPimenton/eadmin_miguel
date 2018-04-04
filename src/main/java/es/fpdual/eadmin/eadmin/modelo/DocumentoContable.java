package es.fpdual.eadmin.eadmin.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class DocumentoContable extends Documento {
	
	private final BigDecimal importe;
	private final String nifInteresado;
	
	public DocumentoContable(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Boolean publico,
			EstadoDocumento estado, BigDecimal importe, String nifInteresado) {
		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion, publico, estado);
		this.importe = importe;
		this.nifInteresado = nifInteresado;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public String getNifInteresado() {
		return nifInteresado;
	}

}
