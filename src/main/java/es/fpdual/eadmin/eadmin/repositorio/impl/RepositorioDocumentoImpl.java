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

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.EadminApplication;
import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.builder.DocumentoBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class RepositorioDocumentoImpl implements RepositorioDocumento {

	private DocumentoMapper mapper;
	
	//private List<Documento> documentos = new ArrayList<>();
	private static final Logger Logger = LoggerFactory.getLogger(RepositorioDocumentoImpl.class);
	FileWriter file;
	PrintWriter pw;
	int rowNumAlta = 0;
	int rowNumMod = 0;
	int rowNumEliminar = 0;
	int rowNumLista = 0;
	int rowNum;
	int rowNumAlta2 = 0;
	int rowNumMod2 = 0;
	int rowNumEliminar2 = 0;
	int rowNumLista2 = 0;
	int rowNum2;

	@Autowired
	public RepositorioDocumentoImpl (DocumentoMapper mapper) {
		this.mapper = mapper;
					
	}
	
	@Override
	public void altaDocumento(Documento documento) {
		/*Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		if (documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}

		documentos.add(documento);
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
		//this.ExportaExcel(documento, "Alta.xls");
		//this.ExportaExcelEnUnSoloDocumento(documento, "Alta");
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
	*/
		
		int posicion = this.mapper.obtenerMaximo();
		Documento documentonuevo = new DocumentoBuilder().clonar(documento).conCodigo(posicion).construir();
		this.mapper.insertarDocumento(documentonuevo);
		
		
	}

	@Override
	public void modificarDocumento(Documento documento, Documento documentoNuevo) {
		/*Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
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
		//this.ExportaExcel(documentoNuevo, "Modificar.xls");
		//this.ExportaExcelEnUnSoloDocumento(documentoNuevo, "Modificar");
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
	*/
		int resultado = mapper.modificarDocumento(documento);
		if (resultado==0) {
			throw new IllegalArgumentException ("No se encuentra el archivo");
		}
		
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		/*Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
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
			//this.ExportaExcel(documentoAEliminar, "Eliminar.xls");
			//this.ExportaExcelEnUnSoloDocumento(documentoAEliminar, "Eliminar");
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
		}*/
		this.mapper.borrarDocumento(codigo);
	}

	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		/*Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
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
		return null;*/
		return this.mapper.consultarDocumento(codigo);
	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		/*Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		for (Documento doc : documentos) {
			Logger.info("************************");
			Logger.info(this.getDatos(doc));
			Logger.info("************************");
		}
		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return this.documentos;*/
		return this.mapper.consultarTodosDocumento();
	}

	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {

		return documento.getCodigo().equals(codigo);
	}

	//public List<Documento> getDocumentos() {
	//	return documentos;
	//}

	public String getDatos(Documento documento) {
		return "Documento con código" + documento.getCodigo() + " Nombre:  " + documento.getNombre() + " FechaCracion: "
				+ documento.getFechaCreacion() + " FechaUltimaActualizacion: " + documento.getFechaUltimaActualizacion()
				+ " Publico: " + documento.getPublico() + ".";
	}

	@Override
	public void GuardarDocumentoEnArchivo() {
		/*Logger.info("Entrando en metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());

		for (Documento documento : documentos) {

			try {
				file = new FileWriter("documentos.txt", true);
				pw = new PrintWriter(file);
				pw.println(documento.getDatos());
				pw.close();
				//this.ExportaExcel(documento, "ListaDocumentos.xls");
				//this.ExportaExcelEnUnSoloDocumento(documento, "Lista");
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

		Logger.info("Saliendo de metodo " + Thread.currentThread().getStackTrace()[1].getMethodName());*/
	}

	@Override
	public void ExportaExcel(Documento documento, String opcion) {

		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;

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

		XSSFSheet sheett = null;
		File archivoExcel = new File("Excel.xlsx");
		XSSFWorkbook workbookk = new XSSFWorkbook();
		
		CellStyle style = workbookk.createCellStyle();//Create style
	    Font font = workbookk.createFont();//Create font
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
	    style.setFont(font);
	    style.setFillPattern(XSSFCellStyle.FINE_DOTS );
	    style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		

		if (!archivoExcel.exists()) {
			
			sheett = workbookk.createSheet("Alta");
			Row row = sheett.createRow(0);
			Cell cell1 = row.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue("Codigo");
			Cell cell2 = row.createCell(1);
			cell2.setCellStyle(style);
			cell2.setCellValue("Nombre");
			Cell cell3 = row.createCell(2);
			cell3.setCellStyle(style);
			cell3.setCellValue("FechaCreacion");
			Cell cell4 = row.createCell(3);
			cell4.setCellStyle(style);
			cell4.setCellValue("FechaUltimaActualizacion");
			Cell cell5 = row.createCell(4);
			cell5.setCellStyle(style);
			cell5.setCellValue("Publico");
			Cell cell6 = row.createCell(5);
			cell6.setCellStyle(style);
			cell6.setCellValue("Estado");
			
			sheett.autoSizeColumn(0);
			sheett.autoSizeColumn(1);
			sheett.autoSizeColumn(2);
			sheett.autoSizeColumn(3);
			sheett.autoSizeColumn(4);
			sheett.autoSizeColumn(5);

			sheett = workbookk.createSheet("Modificar");
			row = sheett.createRow(0);
			cell1 = row.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue("Codigo");
			cell2 = row.createCell(1);
			cell2.setCellStyle(style);
			cell2.setCellValue("Nombre");
			cell3 = row.createCell(2);
			cell3.setCellStyle(style);
			cell3.setCellValue("FechaCreacion");
			cell4 = row.createCell(3);
			cell4.setCellStyle(style);
			cell4.setCellValue("FechaUltimaActualizacion");
			cell5 = row.createCell(4);
			cell5.setCellStyle(style);
			cell5.setCellValue("Publico");
			cell6 = row.createCell(5);
			cell6.setCellStyle(style);
			cell6.setCellValue("Estado");
			
			sheett.autoSizeColumn(0);
			sheett.autoSizeColumn(1);
			sheett.autoSizeColumn(2);
			sheett.autoSizeColumn(3);
			sheett.autoSizeColumn(4);
			sheett.autoSizeColumn(5);

			sheett = workbookk.createSheet("Eliminar");
			row = sheett.createRow(0);
			row = sheett.createRow(0);
			cell1 = row.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue("Codigo");
			cell2 = row.createCell(1);
			cell2.setCellStyle(style);
			cell2.setCellValue("Nombre");
			cell3 = row.createCell(2);
			cell3.setCellStyle(style);
			cell3.setCellValue("FechaCreacion");
			cell4 = row.createCell(3);
			cell4.setCellStyle(style);
			cell4.setCellValue("FechaUltimaActualizacion");
			cell5 = row.createCell(4);
			cell5.setCellStyle(style);
			cell5.setCellValue("Publico");
			cell6 = row.createCell(5);
			cell6.setCellStyle(style);
			cell6.setCellValue("Estado");
			
			sheett.autoSizeColumn(0);
			sheett.autoSizeColumn(1);
			sheett.autoSizeColumn(2);
			sheett.autoSizeColumn(3);
			sheett.autoSizeColumn(4);
			sheett.autoSizeColumn(5);

			sheett = workbookk.createSheet("Lista");
			row = sheett.createRow(0);
			cell1 = row.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue("Codigo");
			cell2 = row.createCell(1);
			cell2.setCellStyle(style);
			cell2.setCellValue("Nombre");
			cell3 = row.createCell(2);
			cell3.setCellStyle(style);
			cell3.setCellValue("FechaCreacion");
			cell4 = row.createCell(3);
			cell4.setCellStyle(style);
			cell4.setCellValue("FechaUltimaActualizacion");
			cell5 = row.createCell(4);
			cell5.setCellStyle(style);
			cell5.setCellValue("Publico");
			cell6 = row.createCell(5);
			cell6.setCellStyle(style);
			cell6.setCellValue("Estado");
			
			sheett.autoSizeColumn(0);
			sheett.autoSizeColumn(1);
			sheett.autoSizeColumn(2);
			sheett.autoSizeColumn(3);
			sheett.autoSizeColumn(4);
			sheett.autoSizeColumn(5);
			
///////////////////////////////////////////////////////
			try {
				FileOutputStream outputStream = new FileOutputStream(archivoExcel);
				workbookk.write(outputStream);
				outputStream.close();
				workbookk.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Hecho");
		}
		try {
			FileInputStream inputStream = new FileInputStream(new File("Excel.xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheet(opcion);

			Object[] bookData = { documento.getCodigo(), documento.getNombre(), documento.getFechaCreacion()+"",
					documento.getFechaUltimaActualizacion()+"", documento.getPublico()+"", documento.getEstado()+"" };

			int rowCount = sheet.getLastRowNum();

			Row row = sheet.createRow(++rowCount);
			
			CellStyle style2 = workbook.createCellStyle();
		    Font font2 = workbook.createFont();
		    style2.setFont(font);
		    style2.setFillPattern(XSSFCellStyle.FINE_DOTS );
		    style2.setFillBackgroundColor(IndexedColors.LAVENDER.getIndex());
		    style2.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
		    
		    
		    
			int columnCount = 0;

			Cell cell = row.createCell(columnCount);
			cell.setCellValue(rowCount);
			
			for (Object field : bookData) {
				cell = row.createCell(columnCount++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
					if (rowCount%2==0)cell.setCellStyle(style2);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
					if (rowCount%2==0)cell.setCellStyle(style2);
				}
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
			}

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream("Excel.xlsx");
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

		} catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
			ex.printStackTrace();
		}

	}

}
