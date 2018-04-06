package es.fpdual.eadmin.eadmin.repositorio;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {
	
	void altaExpediente(Expediente expediente);

	Expediente modificarExpediente(Expediente expediente, Expediente expedienteNuevo);

	void eliminarExpediente(Integer codigoExpediente);
	
	Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento);
	
	Expediente desasociarDocumentoDelExpediente (Integer codigoExpediente, Integer codigoDocumento);
	
	Expediente obtenerExpedientePorCodigo(Integer codigoExpediente);
	
	public void altaExpedienteConArchivo(Expediente documento);
	
	public Expediente modificarExpedienteConArchivo(Expediente expediente, Expediente expedienteNuevo);
	
	public void eliminarExpedienteConArchivo(Integer codigoExpediente);

	void GuardarExpedientesEnArchivo();
}
