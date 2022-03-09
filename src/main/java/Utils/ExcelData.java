package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;

public class ExcelData {
	public String path;
	public FileInputStream fis = null;
	FileInputStream inputstream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	public ExcelData(String path){
		this.path=path;
	}

	public String[][] getData(String SheetName) throws IOException {
		int index=0;
		inputstream = new FileInputStream(path);
		workbook = new XSSFWorkbook(inputstream);
		//XSSFSheet sheet = workbook.getSheet("District Sheet");
		sheet = workbook.getSheet(SheetName);
		int l_row_num = sheet.getLastRowNum();
		int l_column_Num = sheet.getRow(1).getLastCellNum();

		int dataObjectArraySize = getValidRows(path,SheetName);
		String[][] dataObjectArray = new String[dataObjectArraySize][l_column_Num];

		DataFormatter dataFormatter=new DataFormatter();
		for (int r = 0; r <= l_row_num; r++) {
			row = sheet.getRow(r);

			String runMode=row.getCell(0).toString();

			if(runMode.trim().equalsIgnoreCase("Y")) {
				//System.out.println("Current Row = "+r);
				//System.out.println("Column = "+l_column_Num);
				//System.out.println("Row = "+l_row_num);
				//System.out.println(runMode);

				for (int c = 0; c < l_column_Num; c++) {
					cell = row.getCell(c);
					dataObjectArray[index][c]= dataFormatter.formatCellValue(cell);
					//System.out.println("current Index value , column = "+index+","+c);

				}
				index++;
			}

		}

		return dataObjectArray;

	}


	public int getValidRows(String path, String SheetName) throws IOException {
		inputstream = new FileInputStream(path);
		workbook = new XSSFWorkbook(inputstream);
		sheet = workbook.getSheet(SheetName);
		int rows =sheet.getLastRowNum();
		int count=0;
		for(int row=0;row<=rows;row++) {
			if(sheet.getRow(row).getCell(0).toString().equalsIgnoreCase("Y")) {
				count++;
			}
		}
		//fis.close();
		//System.out.println(count);
		return count;
	}

	public String getCellData(String Sheet,int RowCount, int ColumnCount ) throws IOException {
		inputstream=new FileInputStream(path);
		workbook=new XSSFWorkbook();
		sheet=workbook.getSheet(Sheet);
		row=sheet.getRow(RowCount);
		cell=row.getCell(ColumnCount);
		DataFormatter dataFormatter=new DataFormatter();
		String CellValue=dataFormatter.formatCellValue(cell);
		workbook.close();
		inputstream.close();
		return CellValue;
	}
	public String getEncryptedCellData(String Sheet,int RowCount, int ColumnCount ) throws IOException {
		inputstream=new FileInputStream(path);
		workbook= (XSSFWorkbook) WorkbookFactory.create(inputstream, "123qwe");
		sheet=workbook.getSheet(Sheet);
		row=sheet.getRow(RowCount);
		cell=row.getCell(ColumnCount);
		DataFormatter dataFormatter=new DataFormatter();
		String CellValue=dataFormatter.formatCellValue(cell);
		workbook.close();
		inputstream.close();
		return CellValue;
	}


	public static void main(String args[]) throws  IOException {
		ExcelData excelData = new ExcelData(RunConfig.DATA_PATH);
		excelData.getData("RunSheet");

	}
}
