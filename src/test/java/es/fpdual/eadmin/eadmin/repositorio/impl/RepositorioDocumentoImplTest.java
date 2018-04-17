package es.fpdual.eadmin.eadmin.repositorio.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
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
	private DocumentoMapper mapper;
	private final Documento documento =
			new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION,FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);
	
	@Before
	public void inicializarEnCadaTest() {
		this.mapper=mock(DocumentoMapper.class);
		
		this.repositorioDocumento = new RepositorioDocumentoImpl(mapper);
		
	}
	
	@Test
	public void deberiaAlmacenarUnDocumento() {
		
		this.repositorioDocumento.altaDocumento(documento);
		
		//assertTrue(repositorioDocumento.getDocumentos().contains(documento));
		verify(this.mapper).insertarDocumento(this.documento);

		
	}

	/*@Test (expected = IllegalArgumentException.class)
	public void deberiaLanzarExceptionSiIntentamosAlmacenarUnDocumentoQueYaExiste() {
		
		//this.repositorioDocumento.getDocumentos().add(documento);
		this.repositorioDocumento.obtenerTodosLosDocumentos().add(documento);
		this.repositorioDocumento.altaDocumento(documento);
	}*/
	
	@Test
	public void deberiaModificarUnDocumento() {
		
		//final Documento documento2 = new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION,FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.APROBADO);
		
		//this.repositorioDocumento.getDocumentos().add(documento);
		when(this.mapper.modificarDocumento(documento)).thenReturn(1);
		
		this.repositorioDocumento.modificarDocumento(documento,documento);
		
		verify(this.mapper).modificarDocumento(documento);
		
		//assertSame(documento2, this.repositorioDocumento.getDocumentos().get(0));
		
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deberiaLanzarExcepcionSiIntentamosAlmacenarUnDocumentoQueNoExiste() {
		
		this.repositorioDocumento.modificarDocumento(documento,documento);
		
	}
	
	/*
	@Test 
	public void deberiaEliminarUnDocumento() {
		
		//this.repositorioDocumento.getDocumentos().add(documento);
		this.repositorioDocumento.obtenerTodosLosDocumentos().add(documento);
		this.repositorioDocumento.eliminarDocumento(documento.getCodigo());
		
		//assertTrue(this.repositorioDocumento.getDocumentos().isEmpty());
		assertTrue(this.repositorioDocumento.obtenerTodosLosDocumentos().isEmpty());

	}
	
	@Test
	public void deberiaNoEliminarDocumentoSiNoExiste() {
		
		
		this.repositorioDocumento.eliminarDocumento(documento.getCodigo());
		
		//assertTrue(this.repositorioDocumento.getDocumentos().isEmpty());
		assertTrue(this.repositorioDocumento.obtenerTodosLosDocumentos().isEmpty());
	}
	*/
	@Test
	public void deberiaObtenerDocumentoPorCodigo() {
		when(mapper.consultarDocumento(this.documento.getCodigo())).thenReturn(this.documento);
		//this.repositorioDocumento.getDocumentos().add(documento);
		//this.repositorioDocumento.obtenerTodosLosDocumentos().add(documento);
		
		final Documento resultado = this.repositorioDocumento.obtenerDocumentoPorCodigo(CODIGO_DOCUMENTO);
		
		assertSame(resultado, this.documento);
		
	}
	/*
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
	*/
	
	@Test
	public void deberiaObtenerTodosLosDocumentos() throws Exception {
		final List<Documento> todosLosDocumentos = Arrays.asList(documento);
		
		when(mapper.consultarTodosDocumento()).thenReturn(todosLosDocumentos);
		
		List<Documento> resultado = this.repositorioDocumento.obtenerTodosLosDocumentos();
		
		assertThat(resultado, hasSize(1));
		assertThat(resultado, hasItem(this.documento));
	}
	
	@Test
	public void deberiaDevolver1SiLaBDEstaVacia() {
		int posicion = this.mapper.obtenerMaximo();
		
		assertThat(posicion, is(1));
		assertThat(mapper.consultarTodosDocumento(), hasSize(0));
	}
	
	@Test
	public void deberiaDevolver2SiLaBDTieneUnRegisro() {
		mapper.insertarDocumento(documento);
		
		int posicion = this.mapper.obtenerMaximo();
		
		assertThat(posicion, is(2));
		assertThat(mapper.consultarTodosDocumento(), hasSize(1));
	}
	
}
		
		
		
		
		
		
		