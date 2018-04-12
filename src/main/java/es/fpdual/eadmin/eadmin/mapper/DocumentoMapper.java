package es.fpdual.eadmin.eadmin.mapper;

import org.apache.ibatis.annotations.Param;

import es.fpdual.eadmin.eadmin.modelo.*;

public interface DocumentoMapper {

	int insertarDocumento(@Param("documento") Documento documento);

	int borrarDocumento(@Param("documento") Integer cod);
	
	int modificarDocumento(@Param("documento") Documento documento);
	
	Documento consultarDocumento(@Param("numero") Integer num);
	//es/pfdual/eadmin/eadmin/mapper
}
