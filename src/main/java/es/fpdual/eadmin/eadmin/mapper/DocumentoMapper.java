package es.fpdual.eadmin.eadmin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import es.fpdual.eadmin.eadmin.modelo.*;

public interface DocumentoMapper {

	int insertarDocumento(@Param("documento") Documento documento);

	int borrarDocumento(@Param("codigo") Integer cod);
	
	int modificarDocumento(@Param("documento") Documento documento);
	
	Documento consultarDocumento(@Param("codigo") Integer num);
	List<Documento> consultarTodosDocumento();
	Integer obtenerMaximo();
}
