package es.fpdual.eadmin.eadmin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Component
public class CargarDatosIniciales implements ApplicationRunner {

	private final RepositorioDocumento repositorioDocumento;
	private final RepositorioExpediente repositorioExpediente;

	private static final Date fecha = new Date();

	@Autowired
	public CargarDatosIniciales(RepositorioDocumento repositorioDocumento, RepositorioExpediente repositorioExpediente) {
		this.repositorioDocumento = repositorioDocumento;
		this.repositorioExpediente = repositorioExpediente;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//List<Documento> documentos = new ArrayList<>();
		System.out.println("0");
		Documento doc1 = new Documento(1, "documento1", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc2 = new Documento(2, "documento2", fecha, fecha, false, EstadoDocumento.ACTIVO);
		Documento doc3 = new Documento(3, "documento3", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc4 = new Documento(4, "documento4", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc5 = new Documento(5, "documento5", fecha, fecha, true, EstadoDocumento.ACTIVO);
		
		Documento doc2Modificado = new Documento(2, "documento2", fecha, fecha, true, EstadoDocumento.ACTIVO);
		Documento doc4Modificado = new Documento(4, "documento4", fecha, fecha, false, EstadoDocumento.ACTIVO);

		/*Expediente exp1 = new Expediente(1, "expediente1", fecha, fecha, fecha, true, EstadoExpediente.INICIADO,documentos);
		Expediente exp2 = new Expediente(2, "expediente2", fecha, fecha, fecha, false, EstadoExpediente.INICIADO,documentos);
		Expediente exp3 = new Expediente(3, "expediente3", fecha, fecha, fecha, true, EstadoExpediente.INICIADO,documentos);
		Expediente exp4 = new Expediente(4, "expediente4", fecha, fecha, fecha, true, EstadoExpediente.INICIADO,documentos);
		Expediente exp5 = new Expediente(5, "expediente5", fecha, fecha, fecha, true, EstadoExpediente.INICIADO,documentos);
		
		Expediente exp2Modificado = new Expediente(2, "expediente2", fecha, fecha, fecha, true, EstadoExpediente.INICIADO,documentos);
		Expediente exp4Modificado = new Expediente(4, "expediente4", fecha, fecha, fecha, false, EstadoExpediente.INICIADO,documentos);*/
		
		this.repositorioDocumento.altaDocumento(doc1);
		this.repositorioDocumento.altaDocumento(doc2);
		this.repositorioDocumento.altaDocumento(doc3);
		this.repositorioDocumento.altaDocumento(doc4);
		this.repositorioDocumento.altaDocumento(doc5);
		//this.repositorioDocumento.GuardarDocumentoEnArchivo();
		System.out.println("1");
		this.repositorioDocumento.modificarDocumento(doc2Modificado,doc2Modificado);
		this.repositorioDocumento.modificarDocumento(doc4Modificado,doc4Modificado);
		//this.repositorioDocumento.GuardarDocumentoEnArchivo();
		System.out.println("2");
		this.repositorioDocumento.eliminarDocumento(doc1.getCodigo());
		this.repositorioDocumento.eliminarDocumento(doc3.getCodigo());
		this.repositorioDocumento.eliminarDocumento(doc5.getCodigo());
		System.out.println("3");
		//this.repositorioDocumento.GuardarDocumentoEnArchivo();
/*
		this.repositorioExpediente.altaExpedienteConArchivo(exp1);
		this.repositorioExpediente.altaExpedienteConArchivo(exp2);
		this.repositorioExpediente.altaExpedienteConArchivo(exp3);
		this.repositorioExpediente.altaExpedienteConArchivo(exp4);
		this.repositorioExpediente.altaExpedienteConArchivo(exp5);
		this.repositorioExpediente.GuardarExpedientesEnArchivo();
		this.repositorioExpediente.modificarExpedienteConArchivo(exp2,exp2Modificado);
		this.repositorioExpediente.modificarExpedienteConArchivo(exp4,exp4Modificado);
		this.repositorioExpediente.GuardarExpedientesEnArchivo();
		this.repositorioExpediente.eliminarExpedienteConArchivo(exp1.getCodigo());
		this.repositorioExpediente.eliminarExpedienteConArchivo(exp3.getCodigo());
		this.repositorioExpediente.eliminarExpedienteConArchivo(exp5.getCodigo());
		this.repositorioExpediente.GuardarExpedientesEnArchivo();*/
	}

}
