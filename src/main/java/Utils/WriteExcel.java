package Utils;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

import Utils.RunConfig;

public class WriteExcel {

	public void writeData() {

		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Emp Info");

		Object empData[][]= {
				{"EmpID","Name","Job"},
				{001,"Boobal","ASE"},
				{002,"Alagesan","AE"},
				{003,"BooAla","SE"}};


		int t_row=empData.length;
		int t_column=empData[0].length;

		for(int r=0;r<t_row;r++){
			XSSFRow row =sheet.createRow(r);
			for(int c=0;c<t_column;c++) {
				XSSFCell cell=row.createCell(c);
				Object value= empData[r][c];
				if(value instanceof String)
					cell.setCellValue((String)value);
				if(value instanceof Integer)
					cell.setCellValue((Integer)value);
				if(value instanceof Boolean)
					cell.setCellValue((Boolean)value);

			}
		}

		String path=".//DataFiles//CreatedFile.xlsx";
		try {
			FileOutputStream outputStream =new FileOutputStream(path);

			workbook.write(outputStream);
			System.out.println("Written data successfully");
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error occured!");
			e.printStackTrace();
		}
	}

	public void writeDataUsingForEach() {

		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Emp Info");

		Object empData[][]= {
				{"EmpID","Name","Job"},
				{001,"Boobal","ASE"},
				{002,"Alagesan","AE"},
				{003,"BooAla","SE"}};


		int rowCount=0;
		for(Object emp[]:empData){
			XSSFRow row =sheet.createRow(rowCount++);
			int columnCount=0;
			for(Object value:emp) {
				XSSFCell cell=row.createCell(columnCount++);
				if(value instanceof String)
					cell.setCellValue((String)value);
				if(value instanceof Integer)
					cell.setCellValue((Integer)value);
				if(value instanceof Boolean)
					cell.setCellValue((Boolean)value);

			}
		}

		String path=".//DataFiles//CreatedFile.xlsx";
		try {
			FileOutputStream outputStream =new FileOutputStream(path);

			workbook.write(outputStream);
			System.out.println("Written data successfully");
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error occured!");
			e.printStackTrace();
		}
	}
	public void writeFormulaValue() {
		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("ValueSheet");

		XSSFRow row= sheet.createRow(0);
		row.createCell(0).setCellValue(2);
		row.createCell(1).setCellValue(3);
		row.createCell(2).setCellValue(4);
		row.createCell(3).setCellFormula("A1*B1*C1");
		

		String path=".//DataFiles//FormulaDataCalc.xlsx";
		try {
			FileOutputStream outputStream =new FileOutputStream(path);

			workbook.write(outputStream);
			System.out.println("Written data successfully");
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error occured!");
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		WriteExcel writeExcel = new WriteExcel();
		//writeExcel.writeDataUsingForEach();
		writeExcel.writeFormulaValue();
		
	}
}
