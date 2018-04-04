package es.fpdual.eadmin.eadmin.modelo.builder;

import java.math.BigDecimal;

import es.fpdual.eadmin.eadmin.modelo.DocumentoContable;

public class DocumentoContableBuilder extends DocumentoBuilder {
	
	private BigDecimal importe;
	private String nifInteresado;
	
	public DocumentoContable construir() {
		return new DocumentoContable(this.codigo, this.nombre, 
				this.fechaCreacion, this.fechaUltimaActualizacion, this.publico, this.estado, 
				this.importe, this.nifInteresado);
	}
	
	public DocumentoContableBuilder conImporte(BigDecimal importe) {
		this.importe = importe;
		return this;
	}

	public DocumentoContableBuilder conNifInteresado(String nifInteresado) {
		this.nifInteresado = nifInteresado;
		return this;
	}
	
	public DocumentoContableBuilder clonar(DocumentoContable documentoContable) {
		
		super.clonar(documentoContable);
		this.importe = documentoContable.getImporte();
		this.nifInteresado = documentoContable.getNifInteresado();
		return this;
		
	}
}
