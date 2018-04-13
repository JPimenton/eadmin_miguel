package es.fpdual.eadmin.eadmin.mapper;
import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.util.Utilidades;

@Transactional("eadminTransactionManager")
public abstract class BaseDocumentoMapperTest {

	@Autowired
	private DocumentoMapper mapper;

	private static final Date FECHA_CREACION = Utilidades.asDate(LocalDate.of(2015, 1, 1));
	private static final Date FECHA_ULTIMA_MODIFICACION = Utilidades.asDate(LocalDate.of(2015, 1, 1));
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO_DOCUMENTO = 1;
	
	private final Documento documento =new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION,FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.ACTIVO);

	
	
	@Test
	public void deberiaInsertarUnDocumento() throws Exception {
		int num = mapper.insertarDocumento(documento);
		assertEquals(1,num); 
	}
	
	@Test
	public void deberiaBorrarUnDocumento() throws Exception {
		mapper.insertarDocumento(documento);
		int num = mapper.borrarDocumento(1);
		assertEquals(1,num); 
	}
	
	@Test
	public void deberiaModificarUnDocumento() throws Exception {
		mapper.insertarDocumento(documento);
		Documento documentoModificado = 
		
				new Documento(CODIGO_DOCUMENTO, NOMBRE_DOCUMENTO, FECHA_CREACION,
				FECHA_ULTIMA_MODIFICACION, DOCUMENTO_PUBLICO, EstadoDocumento.APROBADO);
		
		int num = mapper.modificarDocumento(documentoModificado);
		
		assertEquals(1,num); 
		
		
		Documento documentoActualizado = mapper.consultarDocumento(1);
		assertThat(documentoModificado, is(documentoActualizado));
	}
	
	@Test
	public void deberiaConsultarUnDocumento() throws Exception {
		mapper.insertarDocumento(documento);
		Documento resultado = mapper.consultarDocumento(1);
		
		assertEquals(resultado,documento); 
	}
	
}
