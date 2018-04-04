package es.fpdual.eadmin.eadmin.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.servicio.ServicioExpediente;

@Service
public class ServicioExpedienteImpl implements ServicioExpediente{

	private final RepositorioExpediente repositorioExpediente;
	
	@Autowired
	public ServicioExpedienteImpl (RepositorioExpediente repositorioExpediente) {
		this.repositorioExpediente = repositorioExpediente;
	}
	
	
	@Override
	public Expediente altaExpediente(Expediente expediente) {
		return this.altaExpediente(expediente);
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente) {
		return this.repositorioExpediente.modificarExpediente(expediente);
		
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {
		this.repositorioExpediente.eliminarExpediente(codigoExpediente);
		
	}

	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		return this.repositorioExpediente.asociarDocumentoAlExpediente(codigoExpediente, documento);
		
	}

	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		return this.repositorioExpediente.desasociarDocumentoDelExpediente(codigoExpediente, codigoDocumento);
		
	}

}
