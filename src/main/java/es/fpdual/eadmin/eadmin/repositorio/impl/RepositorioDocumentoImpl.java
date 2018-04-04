package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.EadminApplication;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;


@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private List<Documento> documentos = new ArrayList<>();
	private static final Logger Logger = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);

	
	@Override
	public void altaDocumento(Documento documento) {
		Logger.info("Entrando en metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		
		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		
		documentos.add(documento);
		Logger.info(documento.toString()+" se ha creado correctamente");
		Logger.info("Saliendo de metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void modificarDocumento(Documento documento) {
		Logger.info("Entrando en metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento a modificar no existe");
		}
		
		documentos.set(documentos.indexOf(documento), documento);
		Logger.info("Saliendo de metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		Logger.info("Entrando en metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		final Documento documentoAEliminar =  this.obtenerDocumentoPorCodigo(codigo);
		Logger.info("Eliminando "+documentoAEliminar.toString());
		
		if (Objects.nonNull(documentoAEliminar)) {
			documentos.remove(documentoAEliminar);
			Logger.info(documentoAEliminar.toString()+" se ha eliminado");
			Logger.info("Saliendo de metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
	}
	
	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		Logger.info("Entrando en metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Logger.info("Buscando documento con codigo "+ codigo);
		Optional<Documento> documentoEncontrado = 
				documentos.stream().
					filter(d -> tieneIgualCodigo(d, codigo)).
					findFirst();
		
		if (documentoEncontrado.isPresent()) {
			Logger.info("Documento con codigo "+ codigo + " encontrado. ");
			documentoEncontrado.get().getDatos();
			Logger.info("Saliendo de metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
			return documentoEncontrado.get();
		}
		Logger.info("Saliendo de metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return null;
	}
	
	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		Logger.info("Entrando en metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		for (Documento doc : documentos) {
			Logger.info("************************");
			Logger.info(this.getDatos(doc));
			Logger.info("************************");
		}
		Logger.info("Saliendo de metodo "+ Thread.currentThread().getStackTrace()[1].getMethodName());
		return this.documentos;
	}

	
	protected boolean tieneIgualCodigo(Documento documento, Integer codigo)	  {
		
		return documento.getCodigo().equals(codigo);
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public String getDatos(Documento documento) {
		return "Documento con código" + documento.getCodigo() + 
				" Nombre:  " + documento.getNombre() +
				" FechaCracion: " + documento.getFechaCreacion() +
				" FechaUltimaActualizacion: " + documento.getFechaUltimaActualizacion() + 
				" Publico: " + documento.getPublico()+".";				
	}

}


