package es.fpdual.eadmin.eadmin.repositorio.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.EadminApplication;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private List<Documento> documentos = new ArrayList<>();
	private static final Logger Logger = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);
	FileWriter file;
	PrintWriter pw;
	int rowNumAlta = 0;
	int rowNumMod = 0;
	int rowNumEliminar = 0;
	int rowNumLista = 0;
	int rowNum;

	@Override
	public void altaDocumento(Documento documento) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}

		documentos.add(documento);
		this.ExportaExcel(documento, "Alta.xls");
		Logger.info(documento.toString() + " se ha creado correctamente");
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void altaDocumentoConArchivo(Documento documento) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}

		documentos.add(documento);
		this.ExportaExcel(documento, "Alta.xls");
		this.ExportaExcelEnUnSoloDocumento(documento, "Alta");		
		try {
			file = new FileWriter("AltaDocumentos.txt", true);
			pw = new PrintWriter(file);
			pw.println(documento.getDatos());
			pw.println("********************");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.info(documento.toString() + " se ha creado correctamente");
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void modificarDocumento(Documento documento, Documento documentoNuevo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento a modificar no existe");
		}

		documentos.set(documentos.indexOf(documento), documentoNuevo);
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void modificarDocumentoConArchivo(Documento documento, Documento documentoNuevo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		if (!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento a modificar no existe");
		}

		documentos.set(documentos.indexOf(documento), documentoNuevo);
		this.ExportaExcel(documentoNuevo, "Modificar.xls");
		this.ExportaExcelEnUnSoloDocumento(documentoNuevo, "Modificar");
		try {
			file = new FileWriter("ModificarDocumentos.txt", true);
			pw = new PrintWriter(file);
			pw.println(documentoNuevo.getDatos());
			pw.println("********************");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		final Documento documentoAEliminar = this.obtenerDocumentoPorCodigo(codigo);
		Logger.info("Eliminando " + documentoAEliminar.toString());

		if (Objects.nonNull(documentoAEliminar)) {
			documentos.remove(documentoAEliminar);
		}

		Logger.info(documentoAEliminar.toString() + " se ha eliminado");
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Override
	public void eliminarDocumentoConArchivo(Integer codigo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		final Documento documentoAEliminar = this.obtenerDocumentoPorCodigo(codigo);

		if (Objects.nonNull(documentoAEliminar)) {
			Logger.info("Eliminando " + documentoAEliminar.toString());
			documentos.remove(documentoAEliminar);
			this.ExportaExcel(documentoAEliminar, "Eliminar.xls");
			this.ExportaExcelEnUnSoloDocumento(documentoAEliminar, "Eliminar");
			try {
				file = new FileWriter("EliminarDocumentos.txt", true);
				pw = new PrintWriter(file);
				pw.println(documentoAEliminar.getDatos());
				pw.println("********************");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Logger.info(documentoAEliminar.toString() + " se ha eliminado");
			Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		Logger.info("Buscando documento con codigo " + codigo);
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> tieneIgualCodigo(d, codigo))
				.findFirst();

		if (documentoEncontrado.isPresent()) {
			Logger.info("Documento con codigo " + codigo + " encontrado. ");
			documentoEncontrado.get().getDatos();
			Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return documentoEncontrado.get();
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return null;
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		for (Documento doc : documentos) {
			Logger.info("************************");
			Logger.info(this.getDatos(doc));
			Logger.info("************************");
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return this.documentos;
	}

	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {

		return documento.getCodigo().equals(codigo);
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public String getDatos(Documento documento) {
		return "Documento con código" + documento.getCodigo() + " Nombre:  " + documento.getNombre() + " FechaCracion: "
				+ documento.getFechaCreacion() + " FechaUltimaActualizacion: " + documento.getFechaUltimaActualizacion()
				+ " Publico: " + documento.getPublico() + ".";
	}

	@Override
	public void GuardarDocumentoEnArchivo() {
		Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		for (Documento documento : documentos) {

			try {
				file = new FileWriter("documentos.txt", true);
				pw = new PrintWriter(file);
				pw.println(documento.getDatos());
				pw.close();
				this.ExportaExcel(documento, "ListaDocumentos.xls");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		try {
			file = new FileWriter("documentos.txt", true);
			pw = new PrintWriter(file);
			pw.println("**********************");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	

	@Override
	public void ExportaExcel(Documento documento, String opcion) {

		XSSFWorkbook workbook = null;
		XSSFSheet sheet=null;

		File archivoExcel = new File(opcion);

		if (archivoExcel.exists()) {

			FileInputStream excelFile = null;
			try {
				excelFile = new FileInputStream(archivoExcel);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				workbook = new XSSFWorkbook(excelFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = workbook.getSheetAt(0);

		} else {
			
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Alumnos");
			Row row = sheet.createRow(0);

			Cell cell1 = row.createCell(0);
			cell1.setCellValue("Codigo");
			Cell cell2 = row.createCell(1);
			cell2.setCellValue("Nombre");
			Cell cell3 = row.createCell(2);
			cell3.setCellValue("FechaCreacion");
			Cell cell4 = row.createCell(3);
			cell4.setCellValue("FechaUltimaActualizacion");
			Cell cell5 = row.createCell(4);
			cell5.setCellValue("Publico");
			Cell cell6 = row.createCell(5);
			cell6.setCellValue("Estado");
		}
		
		switch (opcion) {
		case "Alta.xls":
			rowNumAlta++;
			rowNum = rowNumAlta;
			break;
		case "Eliminar.xls":
			rowNumEliminar++;
			rowNum = rowNumEliminar;
			break;
		case "Modificar.xls":
			rowNumMod++;
			rowNum = rowNumMod;
			break;
		case "ListaDocumentos.xls":
			rowNumLista++;
			rowNum = rowNumLista;
			break;
		}

			Row row = sheet.createRow(rowNum);

			Cell cell1 = row.createCell(0);
			cell1.setCellValue(documento.getCodigo());
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(documento.getNombre());
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(documento.getFechaCreacion() + "");
			Cell cell4 = row.createCell(3);
			cell4.setCellValue(documento.getFechaUltimaActualizacion() + "");
			Cell cell5 = row.createCell(4);
			cell5.setCellValue((boolean) documento.getPublico());
			Cell cell6 = row.createCell(5);
			cell6.setCellValue(documento.getEstado() + "");

		try {
			FileOutputStream outputStream = new FileOutputStream(opcion);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Hecho");
	}
	
	@Override
	public void ExportaExcelEnUnSoloDocumento(Documento documento, String opcion) {
		int num = 0;
		
		XSSFSheet sheet=null;

		File archivoExcel = new File("Excel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		if (!archivoExcel.exists()) {
		sheet = workbook.createSheet("Alta");
		Row row = sheet.createRow(0);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue("Codigo");
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("Nombre");
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("FechaCreacion");
		Cell cell4 = row.createCell(3);
		cell4.setCellValue("FechaUltimaActualizacion");
		Cell cell5 = row.createCell(4);
		cell5.setCellValue("Publico");
		Cell cell6 = row.createCell(5);
		cell6.setCellValue("Estado");
		
		sheet = workbook.createSheet("Modificar");
		row = sheet.createRow(0);
		cell1 = row.createCell(0);
		cell1.setCellValue("Codigo");
		cell2 = row.createCell(1);
		cell2.setCellValue("Nombre");
		cell3 = row.createCell(2);
		cell3.setCellValue("FechaCreacion");
		cell4 = row.createCell(3);
		cell4.setCellValue("FechaUltimaActualizacion");
		cell5 = row.createCell(4);
		cell5.setCellValue("Publico");
		cell6 = row.createCell(5);
		
		sheet = workbook.createSheet("Eliminar");
		row = sheet.createRow(0);
		cell1 = row.createCell(0);
		cell1.setCellValue("Codigo");
		cell2 = row.createCell(1);
		cell2.setCellValue("Nombre");
		cell3 = row.createCell(2);
		cell3.setCellValue("FechaCreacion");
		cell4 = row.createCell(3);
		cell4.setCellValue("FechaUltimaActualizacion");
		cell5 = row.createCell(4);
		cell5.setCellValue("Publico");
		cell6 = row.createCell(5);
		
		sheet = workbook.createSheet("Todos los documentos");
		row = sheet.createRow(0);
		cell1 = row.createCell(0);
		cell1.setCellValue("Codigo");
		cell2 = row.createCell(1);
		cell2.setCellValue("Nombre");
		cell3 = row.createCell(2);
		cell3.setCellValue("FechaCreacion");
		cell4 = row.createCell(3);
		cell4.setCellValue("FechaUltimaActualizacion");
		cell5 = row.createCell(4);
		cell5.setCellValue("Publico");
		cell6 = row.createCell(5);
		}
// HAsta aqui está bien hecho
		switch (opcion) {
			case "Alta":
				num=0;
				break;
			case "Eliminar":
				num=2;
				break;
			case "Modificar":
				num=1;
				break;
			case "Lista":
				num=3;
				break;
			}
			

		
		switch (opcion) {
		case "Alta":
			rowNumAlta++;
			rowNum = rowNumAlta;
			break;
		case "Eliminar":
			rowNumEliminar++;
			rowNum = rowNumEliminar;
			break;
		case "Modificar":
			rowNumMod++;
			rowNum = rowNumMod;
			break;
		case "Lista":
			rowNumLista++;
			rowNum = rowNumLista;
			break;
		}
		System.out.println("TPM");
		sheet = workbook.getSheetAt(num);
		System.out.println("TPM");
		Row row = sheet.createRow(rowNum);
		System.out.println("TPM");
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(documento.getCodigo());
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(documento.getNombre());
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(documento.getFechaCreacion() + "");
			Cell cell4 = row.createCell(3);
			cell4.setCellValue(documento.getFechaUltimaActualizacion() + "");
			Cell cell5 = row.createCell(4);
			cell5.setCellValue((boolean) documento.getPublico());
			Cell cell6 = row.createCell(5);
			cell6.setCellValue(documento.getEstado() + "");

		try {
			FileOutputStream outputStream = new FileOutputStream(archivoExcel);
			workbook.write(outputStream);
			outputStream.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Hecho");
	}
	
}
