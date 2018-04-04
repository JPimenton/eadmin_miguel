package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;
import java.util.List;

public class Expediente extends ElementoBaseAdministracionElectronica{
	
	private final Date fechaArchivado;
	private final EstadoExpediente estado;
	private final List<Documento> documentos;

	public Expediente(Integer codigo, String nombre, Date fechaCreacion, Date fechaUltimaActualizacion, Date fechaArchivado, Boolean publico,
			EstadoExpediente estado, List<Documento> documentos) {
		super(codigo, nombre, fechaCreacion, fechaUltimaActualizacion, publico);
		this.fechaArchivado = fechaArchivado;
		this.estado = estado;
		this.documentos = documentos;
	}

		public Date getFechaArchivado() {
		return fechaArchivado;
	}
	public EstadoExpediente getEstado() {
		return estado;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	@Override
	public int hashCode() {
		return codigo.hashCode() + nombre.hashCode() + fechaCreacion.hashCode() + 
				fechaArchivado.hashCode() + fechaUltimaActualizacion.hashCode() + 
				publico.hashCode() + estado.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Expediente) {
			final Expediente expediente = (Expediente) obj;
			return expediente.getCodigo().equals(this.codigo) 
						&& expediente.getNombre().equals(this.nombre)
						&& expediente.getFechaCreacion().equals(this.fechaCreacion)
						&& expediente.getFechaUltimaActualizacion().equals(this.fechaUltimaActualizacion)
						&& expediente.getFechaArchivado().equals(this.fechaArchivado)
						&& expediente.getPublico().equals(this.publico) 
						&& expediente.getEstado().equals(this.estado);
		}

		return false;

	}
}
