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
		this.repositorioDocumento =repositorioDocumento;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.repositorioDocumento.altaDocumento(new Documento(1, "documento1", fecha, fecha, true, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.altaDocumento(new Documento(2, "documento2", fecha, fecha, false, EstadoDocumento.ACTIVO ));
		this.repositorioDocumento.altaDocumento(new Documento(3, "documento3", fecha, fecha, true, EstadoDocumento.ACTIVO ));
		
	}
	
	

}
