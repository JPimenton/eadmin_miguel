package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.ElementoBaseAdministracionElectronica;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Repository
public class RepositorioExpedienteImpl implements RepositorioExpediente {

	private List<Expediente> expedientes = new ArrayList<>();
	
	@Override
	public void altaExpediente(Expediente expediente) {
		if (expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente ya existe");
		}
		
		expedientes.add(expediente);
		
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente) {
		if (!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente a modificar no existe");
		}
		
		expedientes.set(expedientes.indexOf(expediente), expediente);
		
		return expediente;
	}

	@Override
	public void eliminarExpediente(Integer codigoExpediente) {
		
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (expedienteEncontrado!=null) {
			expedientes.remove(expedienteEncontrado);
		}
		
	}

	
	@Override
	public Expediente asociarDocumentoAlExpediente(Integer codigoExpediente, Documento documento) {
		
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (Objects.isNull(expedienteEncontrado)) {
			return null;
		}
		
		if (!existeDocumentoEnElExpediente(expedienteEncontrado, documento.getCodigo())) {
			expedienteEncontrado.getDocumentos().add(documento);
		}
		
		return expedienteEncontrado;
		
	}


	@Override
	public Expediente desasociarDocumentoDelExpediente(Integer codigoExpediente, Integer codigoDocumento) {
		
		final Expediente expedienteEncontrado = this.obtenerExpedientePorCodigo(codigoExpediente);
		
		if (Objects.isNull(expedienteEncontrado)) {
			return null;
		}
		
		if (existeDocumentoEnElExpediente(expedienteEncontrado, codigoDocumento)) {
			expedienteEncontrado.getDocumentos().remove(obtenerDocumentoDelExpedientePorCodigo(expedienteEncontrado, codigoDocumento));
		}
		
		return expedienteEncontrado;
	}


	@Override
	public Expediente obtenerExpedientePorCodigo(Integer codigoExpediente) {
		
		Optional<Expediente> expedienteEncontrado = 
				expedientes.stream().
					filter(e -> tieneIgualCodigo(e, codigoExpediente)).
					findFirst();
		
		if (expedienteEncontrado.isPresent()) {
			return expedienteEncontrado.get();
		}
		
		return null;
	}
	
	protected boolean tieneIgualCodigo(ElementoBaseAdministracionElectronica elemento, Integer codigo) {
		return elemento.getCodigo().equals(codigo);
	}

	protected boolean existeDocumentoEnElExpediente(Expediente expediente, Integer codigo) {
		return expediente.getDocumentos().stream().anyMatch(d -> tieneIgualCodigo(d, codigo));
	}
	
	protected Documento obtenerDocumentoDelExpedientePorCodigo(Expediente expediente, Integer codigoDocumento) {
		Optional<Documento> documentoEncontrado = 
				expediente.getDocumentos().stream().
					filter(d -> tieneIgualCodigo(d, codigoDocumento)).
					findFirst();
		
		if (documentoEncontrado.isPresent()) {
			return documentoEncontrado.get();
		}
		
		return null;
	}

}
