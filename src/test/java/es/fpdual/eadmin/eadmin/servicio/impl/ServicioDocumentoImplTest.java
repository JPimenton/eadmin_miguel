package es.fpdual.eadmin.eadmin.servicio.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;


public class ServicioDocumentoImplTest {
	
	private ServicioDocumentoImpl servicioDocumento;
	
	private static final Documento DOCUMENTO = mock(Documento.class);
	
	private final RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);
	
	@Before
	public void inicializarEnCadaTest() {
		
		this.servicioDocumento = spy(new ServicioDocumentoImpl(repositorioDocumento));
	}
	
	@Test
	public void deberiaAlmacenarUnDocumento() {
		
		final Documento documentoModificado = mock(Documento.class);
		
		doReturn(documentoModificado).when(this.servicioDocumento).obtenerDocumentoConFechaCreacionCorrecta(DOCUMENTO);
		
		this.servicioDocumento.altaDocumento(DOCUMENTO);
		
		verify(this.repositorioDocumento).altaDocumento(documentoModificado);
		
	
	}
	
	@Test
	public void deberiaModificarDocumento() {
		
		final Documento documentoModificado = mock(Documento.class);
		
		doReturn(documentoModificado).when(this.servicioDocumento).obtenerDocumentoConFechaUltimaActualizacionCorrecta(DOCUMENTO);
		
		this.servicioDocumento.modificarDocumento(DOCUMENTO);
		
		verify(this.repositorioDocumento).modificarDocumento(documentoModificado);
		
	}
	
	@Test
	public void deberiaEliminarDocumento() {
		
		when(DOCUMENTO.getCodigo()).thenReturn(1);
		
		this.servicioDocumento.eliminarDocumento(DOCUMENTO.getCodigo());
		
		verify(this.repositorioDocumento).eliminarDocumento(1);
	}
	


}
