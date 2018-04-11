package es.fpdual.eadmin.eadmin.mapper;

import org.apache.ibatis.annotations.Param;

import es.fpdual.eadmin.eadmin.modelo.*;

public interface DocumentoMapper {

	int insertarDocumento(@Param("documento") Documento documento);

	//es/pfdual/eadmin/eadmin/mapper
}
