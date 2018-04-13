package es.fpdual.eadmin.eadmin.repositorio;

import java.io.File;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;

public interface RepositorioDocumento {

	public abstract void altaDocumento (Documento documento);
	
	public abstract void modificarDocumento(Documento documento, Documento documentoNuevo);
	
	public abstract void eliminarDocumento (Integer codigo);
	
	public abstract Documento obtenerDocumentoPorCodigo(Integer codigo);

	public abstract List<Documento> obtenerTodosLosDocumentos();
	
	public void GuardarDocumentoEnArchivo();
/*	
	public void altaDocumentoConArchivo(Documento documento);
	
	public void modificarDocumentoConArchivo(Documento documento, Documento documentoNuevo);
	
	public void eliminarDocumentoConArchivo(Integer codigo);
*/
	void ExportaExcel(Documento documento, String mode);

	void ExportaExcelEnUnSoloDocumento(Documento documento, String opcion);

	
}
