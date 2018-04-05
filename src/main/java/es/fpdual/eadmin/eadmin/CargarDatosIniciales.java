package es.fpdual.eadmin.eadmin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Component
public class CargarDatosIniciales implements ApplicationRunner {

	private final RepositorioDocumento repositorioDocumento;

	private static final Date fecha = new Date();

	@Autowired
	public CargarDatosIniciales(RepositorioDocumento repositorioDocumento) {
		this.repositorioDocumento = repositorioDocumento;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Documento doc1 = new Documento(1, "documento1", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc2 = new Documento(2, "documento2", fecha, fecha, false, EstadoDocumento.ACTIVO);
		Documento doc3 = new Documento(3, "documento3", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc4 = new Documento(4, "documento4", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc5 = new Documento(5, "documento5", fecha, fecha, true, EstadoDocumento.ACTIVO);
		
		Documento doc2Modificado = new Documento(2, "documento2", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc4Modificado = new Documento(4, "documento4", fecha, fecha, false, EstadoDocumento.ACTIVO);
		
		this.repositorioDocumento.altaDocumentoConArchivo(doc1);
		this.repositorioDocumento.altaDocumentoConArchivo(doc2);
		this.repositorioDocumento.altaDocumentoConArchivo(doc3);
		this.repositorioDocumento.altaDocumentoConArchivo(doc4);
		this.repositorioDocumento.altaDocumentoConArchivo(doc5);
		this.repositorioDocumento.GuardarDocumentoEnArchivo();
		this.repositorioDocumento.modificarDocumentoConArchivo(doc2,doc2Modificado);
		this.repositorioDocumento.modificarDocumentoConArchivo(doc4,doc4Modificado);
		this.repositorioDocumento.GuardarDocumentoEnArchivo();
		this.repositorioDocumento.eliminarDocumentoConArchivo(doc1.getCodigo());
		this.repositorioDocumento.eliminarDocumentoConArchivo(doc3.getCodigo());
		this.repositorioDocumento.eliminarDocumentoConArchivo(doc5.getCodigo());
		this.repositorioDocumento.GuardarDocumentoEnArchivo();

	}

}
