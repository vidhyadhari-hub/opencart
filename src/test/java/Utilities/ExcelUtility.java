package Utilities;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtility {
	    String path;

	    public ExcelUtility(String path) {
	        this.path = path;
	    }

	   

		// Get Row Count
	    public int getRowCount(String sheetName) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);

	        int rowCount = sheet.getLastRowNum();

	        wb.close();
	        fi.close();

	        return rowCount;
	    }

	    // Get Cell Count
	    public int getCellCount(String sheetName, int rownum) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);
	        Row row = sheet.getRow(rownum);

	        int cellCount = row.getLastCellNum();

	        wb.close();
	        fi.close();

	        return cellCount;
	    }

	    // Get Cell Data
	    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);
	        Row row = sheet.getRow(rownum);
	        Cell cell = row.getCell(colnum);

	        DataFormatter formatter = new DataFormatter();

	        String data;

	        try {
	            data = formatter.formatCellValue(cell);
	        } catch (Exception e) {
	            data = "";
	        }

	        wb.close();
	        fi.close();

	        return data;
	    }

	    // Set Cell Data
	    public void setCellData(String sheetName, int rownum, int colnum, String data)
	            throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);

	        if (sheet == null)
	            sheet = wb.createSheet(sheetName);

	        Row row = sheet.getRow(rownum);

	        if (row == null)
	            row = sheet.createRow(rownum);

	        Cell cell = row.getCell(colnum);

	        if (cell == null)
	            cell = row.createCell(colnum);

	        cell.setCellValue(data);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // Create Sheet
	    public void createSheet(String sheetName) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        wb.createSheet(sheetName);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // Delete Sheet
	    public void deleteSheet(String sheetName) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        int index = wb.getSheetIndex(sheetName);

	        if (index != -1)
	            wb.removeSheetAt(index);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // Fill Green Color
	    public void fillGreenColor(String sheetName, int rownum, int colnum)
	            throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);
	        Row row = sheet.getRow(rownum);
	        Cell cell = row.getCell(colnum);

	        CellStyle style = wb.createCellStyle();

	        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	        cell.setCellStyle(style);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // Fill Red Color
	    public void fillRedColor(String sheetName, int rownum, int colnum)
	            throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);
	        Row row = sheet.getRow(rownum);
	        Cell cell = row.getCell(colnum);

	        CellStyle style = wb.createCellStyle();

	        style.setFillForegroundColor(IndexedColors.RED.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	        cell.setCellStyle(style);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // Create Row
	    public void createRow(String sheetName, int rownum) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);

	        sheet.createRow(rownum);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // Delete Row
	    public void deleteRow(String sheetName, int rownum) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        XSSFWorkbook wb = new XSSFWorkbook(fi);

	        XSSFSheet sheet = wb.getSheet(sheetName);

	        Row row = sheet.getRow(rownum);

	        if (row != null)
	            sheet.removeRow(row);

	        FileOutputStream fo = new FileOutputStream(path);

	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }
	}


