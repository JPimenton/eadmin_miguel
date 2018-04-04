package es.fpdual.eadmin.eadmin.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;


public class ControladorEadminTest {

	private ControladorEadmin controlador;
	
	private final ServicioDocumento servicio = mock(ServicioDocumento.class);
	private final Documento documento = mock(Documento.class);
			
	@Before
	public void inicializarCadaTest() {
		this.controlador = new ControladorEadmin(servicio);
	}
	
	@Test
	public void DeberiaDevolverTodosLosDocumentos() {
		final List<Documento> documentos = new ArrayList<>();
		when(this.servicio.obtenerTodosLosDocumentos()).thenReturn(documentos);
		
		List<Documento> resultado= this.controlador.getTodosDocumentos().getBody();
		
		assertSame(documentos,resultado);
	}
	
	@Test
	public void DeberiaDevolverDocumentosPorCodigo() {
		
		when(this.servicio.obtenerDocumentoPorCodigo(1)).thenReturn(documento);
		
		Documento resultado= this.controlador.getDocumento(1).getBody();
		
		assertSame(documento,resultado);
	}
	
	@Test
	public void DeberiaEliminarPorCodigo() {
		ResponseEntity<?> resultado = this.controlador.eliminarDocumento(1);
		verify(servicio).eliminarDocumento(1);
		assertSame(HttpStatus.OK,resultado.getStatusCode());
		
	}
}
