package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
			final ElementoBaseAdministracionElectronica param = (ElementoBaseAdministracionElectronica)obj;
			final EqualsBuilder equalsBuilder = new EqualsBuilder();
			
			equalsBuilder.append(this.codigo, param.codigo);
			equalsBuilder.append(this.nombre, param.nombre);
			equalsBuilder.append(this.fechaCreacion, param.fechaCreacion);
			equalsBuilder.append(this.fechaUltimaActualizacion, param.fechaUltimaActualizacion);
			equalsBuilder.append(this.publico, param.publico);
			
			return equalsBuilder.isEquals();
		} 
		return false;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		hashCodeBuilder.append(codigo);
		hashCodeBuilder.append(nombre);
		hashCodeBuilder.append(fechaCreacion);
		hashCodeBuilder.append(fechaUltimaActualizacion);
		hashCodeBuilder.append(publico);
		return hashCodeBuilder.toHashCode();
	}
	
}
