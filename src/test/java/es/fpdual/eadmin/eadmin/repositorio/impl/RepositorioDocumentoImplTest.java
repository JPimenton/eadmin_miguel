package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.impl.RepositorioDocumentoImpl;
import org.junit.Assert.*;

public class RepositorioDocumentoImplTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO_DOCUMENTO = 1;

	private RepositorioDocumentoImpl repositorioDocumento;
	
	private final Documento documento =
			
			new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION,FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	
	@Before
	public void inicializarEnCadaTest() {
		this.repositorioDocumento = new RepositorioDocumentoImpl();
		
		
	}
	
	@Test
	public void deberiaAlmacenarUnDocumento() {
		
		this.repositorioDocumento.altaDocumento(documento);
		
		assertTrue(repositorioDocumento.getDocumentos().contains(documento));
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deberiaLanzarExceptionSiIntentamosAlmacenarUnDocumentoQueYaExiste() {
		
		this.repositorioDocumento.getDocumentos().add(documento);
		
		this.repositorioDocumento.altaDocumento(documento);
	}
	
	@Test
	public void deberiaModificarUnDocumento() {
		
		final Documento documento2 = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION,FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.APROBADO);
		
		this.repositorioDocumento.getDocumentos().add(documento);
		
		this.repositorioDocumento.modificarDocumento(documento2);
		
		assertSame(documento2, this.repositorioDocumento.getDocumentos().get(0));
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deberiaLanzarExcepcionSiIntentamosAlmacenarUnDocumentoQueNoExiste() {
		
		this.repositorioDocumento.modificarDocumento(documento);
		
	}
	
	
	@Test 
	public void deberiaEliminarUnDocumento() {
		
		this.repositorioDocumento.getDocumentos().add(documento);
		
		this.repositorioDocumento.eliminarDocumento(documento.getCodigo());
		
		assertTrue(this.repositorioDocumento.getDocumentos().isEmpty());
	}
	
	@Test
	public void deberiaNoEliminarDocumentoSiNoExiste() {
		
		
		this.repositorioDocumento.eliminarDocumento(documento.getCodigo());
		
		assertTrue(this.repositorioDocumento.getDocumentos().isEmpty());
	}
	
	@Test
	public void deberiaObtenerDocumentoPorCodigo() {
		
		this.repositorioDocumento.getDocumentos().add(documento);
		
		final Documento resultado = this.repositorioDocumento.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);
		
		assertSame(resultado, documento);
		
	}
	
	@Test
	public void deberiaDevolverNuloAlObtenerDocumentoPorCodigoSiNoExisteElDocumento() {
	
		final Documento resultado = this.repositorioDocumento.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);
		
		assertNull(resultado);
	}
	
	@Test
	public void deberiaDevolverTrueSiTienenIgualCodigo() {
		
		assertTrue(this.repositorioDocumento.tieneIgualCodigo(documento, CODIGO_DOCUMENTO));
		
	}
	
	@Test
	public void deberiaDevolverFalseSiNoTienenIgualCodigo() {
		
		assertFalse(this.repositorioDocumento.tieneIgualCodigo(documento, 99));
		
	}
	
}
