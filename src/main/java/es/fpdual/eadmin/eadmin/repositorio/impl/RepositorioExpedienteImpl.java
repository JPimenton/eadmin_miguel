package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.ElementoBaseAdministracionElectronica;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Repository
public class RepositorioExpedienteImpl implements RepositorioExpediente {

	private List<Expediente> expedientes = new ArrayList<>();
	private static final Logger Logger = LoggerFactory.getLogger(RepositorioExpedienteImpl.class);
	FileWriter file;
	PrintWriter pw;
	
	@Override
	public void altaExpediente(Expediente expediente) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		if (expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente ya existe");
		}
		
		expedientes.add(expediente);
		Logger.info(expediente.toString() + " se ha creado correctamente");
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	
	}
	
	@Override
	public void altaExpedienteConArchivo(Expediente expediente) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		if (expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente ya existe");
		}
		
		expedientes.add(expediente);
		try {
			file = new FileWriter("AltaExpedientes.txt", true);
			pw = new PrintWriter(file);
			pw.println(expediente.getDatos());
			pw.println("********************");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.info(expediente.toString() + " se ha creado correctamente");
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente, Expediente expedienteNuevo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente a modificar no existe");
		}
		
		expedientes.set(expedientes.indexOf(expediente), expedienteNuevo);
		
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return expedienteNuevo;
	}
	
	@Override
	public Expediente modificarExpedienteConArchivo(Expediente expediente, Expediente expedienteNuevo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente a modificar no existe");
		}
		
		expedientes.set(expedientes.indexOf(expediente), expedienteNuevo);
		try {
			file = new FileWriter("ModificarExpedientes.txt", true);
			pw = new PrintWriter(file);
			pw.println(expedienteNuevo.getDatos());
			pw.println("********************");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return expedienteNuevo;
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (expedienteEncontrado!=null) {
			Logger.info("Eliminando " + expedienteEncontrado.toString());
			expedientes.remove(expedienteEncontrado);
			Logger.info(expedienteEncontrado.toString() + " se ha eliminado");
			Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
	}

	@Override
	public void eliminarExpedienteConArchivo(Integer codigoExpediente) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (expedienteEncontrado!=null) {
			Logger.info("Eliminando " + expedienteEncontrado.toString());
			try {
				file = new FileWriter("EliminarExpedientes.txt", true);
				pw = new PrintWriter(file);
				pw.println(expedienteEncontrado.getDatos());
				pw.println("********************");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			expedientes.remove(expedienteEncontrado);
			Logger.info(expedienteEncontrado.toString() + " se ha eliminado");
			Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
		
	}
	
	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (Objects.isNull(expedienteEncontrado)) {
			return null;
		}
		
		if (!existeDocumentoEnElExpediente(expedienteEncontrado, documento.getCodigo())) {
			expedienteEncontrado.getDocumentos().add(documento);
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return expedienteEncontrado;
		
	}


	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (Objects.isNull(expedienteEncontrado)) {
			return null;
		}
		
		if (existeDocumentoEnElExpediente(expedienteEncontrado, codigoDocumento)) {
			expedienteEncontrado.getDocumentos().remove(obtenerDocumentoDelExpedientePorCodigo(expedienteEncontrado, codigoDocumento));
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return expedienteEncontrado;
	}


	@Override
	public Expediente obtenerExpedientePorCodigo(Integer codigoExpediente) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		Optional<Expediente> expedienteEncontrado = 
				expedientes.stream().
					filter(e -> tieneIgualCodigo(e, codigoExpediente)).
					findFirst();
		
		if (expedienteEncontrado.isPresent()) {
			Logger.info("Expediente con codigo " + codigoExpediente + " encontrado. ");
			Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return expedienteEncontrado.get();
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return null;
	}
	
	protected boolean tieneIgualCodigo(ElementoBaseAdministracionElectronica elemento, Integer codigo) {
		return elemento.getCodigo().equals(codigo);
	}

	protected boolean existeDocumentoEnElExpediente(Expediente expediente, Integer codigo) {
		return expediente.getDocumentos().stream().anyMatch(d -> tieneIgualCodigo(d, codigo));
	}
	
	protected Documento obtenerDocumentoDelExpedientePorCodigo(Expediente expediente, Integer codigoDocumento) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Optional<Documento> documentoEncontrado = 
				expediente.getDocumentos().stream().
					filter(d -> tieneIgualCodigo(d, codigoDocumento)).
					findFirst();
		
		if (documentoEncontrado.isPresent()) {
			return documentoEncontrado.get();
		}
		
		return null;
	}
	@Override
	public void GuardarExpedientesEnArchivo() {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		for (Expediente expediente : expedientes) {

			try {
				file = new FileWriter("expediente.txt", true);
				pw = new PrintWriter(file);
				pw.println(expediente.getDatos());
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			file = new FileWriter("expediente.txt", true);
			pw = new PrintWriter(file);
			pw.println("**********************");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

}
