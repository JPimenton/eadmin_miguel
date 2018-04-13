package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;

public class ElementoBaseAdministracionElectronicaTest extends AbstractoModeloBeanTest<ElementoBaseAdministracionElectronica> {
	
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
		
	
	@Override
	public void before() {
		this.entityA1 = new ElementoBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO);
		this.entityA2 = new ElementoBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO);
		this.entityB = new ElementoBaseAdministracionElectronicaFake(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION, FECHA_ULTIMA_MODIFICACION, false);

		
	}
	
	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(CODIGO_DOCUMENTO, this.entityA1.getCodigo());
		assertEquals(NOMBRE_DOCUMENTO, this.entityA1.getNombre());
		assertEquals(FECHA_CREACION, this.entityA1.getFechaCreacion());
		assertEquals(FECHA_ULTIMA_MODIFICACION, this.entityA1.getFechaUltimaActualizacion());
		assertEquals(DOCUMENTO_PUBLICO, this.entityA1.getPublico());
		
	}
	
}
