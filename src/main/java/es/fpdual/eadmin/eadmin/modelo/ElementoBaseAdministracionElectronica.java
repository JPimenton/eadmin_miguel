package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public abstract  class ElementoBaseAdministracionElectronica {
	
	protected final Integer codigo;
	protected final String nombre;
	protected final Date fechaCreacion;
	protected final Date fechaUltimaActualizacion;
	protected final Boolean publico;
	
	public ElementoBaseAdministracionElectronica(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Boolean publico) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.publico = publico;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}


	public Boolean getPublico() {
		return publico;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof ElementoBaseAdministracionElectronica) {
			return codigo.equals(((ElementoBaseAdministracionElectronica) obj).getCodigo());
		} 
		return false;
	}
	
	@Override
	public int hashCode() {
		return codigo.hashCode();
	}
	
}
