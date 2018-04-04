package es.fpdual.eadmin.eadmin.repositorio;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {
	
	void altaExpediente(Expediente expediente);

	Expediente modificarExpediente(Expediente expediente);

	void eliminarExpediente(Integer codigoExpediente);
	
	Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento);
	
	Expediente desasociarDocumentoDelExpediente (Integer codigoExpediente, Integer codigoDocumento);
	
	Expediente obtenerExpedientePorCodigo(Integer codigoExpediente);
}
