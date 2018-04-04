package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ElementoBaseAdministracionElectronicaTest {
	
	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHA_ULTIMA_MODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO_DOCUMENTO = 1;
	
	class ElementoBaseAdministracionElectronicaFake extends ElementoBaseAdministracionElectronica {

		public ElementoBaseAdministracionElectronicaFake(Integer codigo, String nombre, Date fechaCreacion,
				Date fechaUltimaModificacion, Boolean publico) {
			super(codigo, nombre, fechaCreacion, fechaUltimaModificacion, publico);
			
		}
		
	}
	
	private ElementoBaseAdministracionElectronica elementoBaseAdministracionElectronica;
	
	@Before
	public void inicializarCadaTest() {
		elementoBaseAdministracionElectronica = 
			new ElementoBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO);
	}
	
	@Test
	public void deberiaComprobarGetters() {
		
		
		assertEquals(Integer.valueOf(CODIGO_DOCUMENTO), elementoBaseAdministracionElectronica.getCodigo());
		assertEquals(NOMBRE_DOCUMENTO, elementoBaseAdministracionElectronica.getNombre());
		assertEquals(FECHA_CREACION, elementoBaseAdministracionElectronica.getFechaCreacion());
		assertEquals(DOCUMENTO_PUBLICO, elementoBaseAdministracionElectronica.getPublico());
	}
	
	@Test
	public void deberiaDevolverTrueSiTienenIgualCodigo() {
		
		final Documento elementoBaseAdministracionElectronica2 = new Documento(CODIGO_DOCUMENTO, null, null, null, null, null);
		
		final Boolean resultado = elementoBaseAdministracionElectronica2.equals(elementoBaseAdministracionElectronica);
		
		assertTrue(resultado);
	}

	@Test
	public void deberiaDevolverFalseSiNoTienenIgualCodigo() {
		
		final Documento elementoBaseAdministracionElectronica2 = new Documento(5, null, null, null, null, null);
		
		final Boolean resultado = elementoBaseAdministracionElectronica2.equals(elementoBaseAdministracionElectronica);
		
		assertFalse(resultado);
	}
	
	@Test
	public void deberiaDevolverFalseSiNoEsUnDocumento() {
		
		final Boolean resultado = elementoBaseAdministracionElectronica.equals(new Date());
		
		assertFalse(resultado);
	}
		
	@Test
	public void deberiaDevolverHasCodeDelCodigo() {
		
		final int resultado = elementoBaseAdministracionElectronica.hashCode();
		
		assertEquals(CODIGO_DOCUMENTO.hashCode(), resultado);
	}
}
