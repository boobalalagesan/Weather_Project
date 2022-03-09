package Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class CellColour {


	public void AddColour() {

		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("DataSheet");

		XSSFRow row=sheet.createRow(0);

		XSSFCellStyle style1=workbook.createCellStyle();
		style1.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style1.setFillPattern(FillPatternType.BIG_SPOTS);

		XSSFCell cell =row.createCell(0);

		cell.setCellValue("Name");
		cell.setCellStyle(style1);

		XSSFCellStyle style2=workbook.createCellStyle();
		style2.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFCell cell2 =row.createCell(1);

		cell2.setCellValue("ID");
		cell2.setCellStyle(style2);

		XSSFRow row1=sheet.createRow(1);
		row1.createCell(0).setCellValue("Boobal");
		
		row1.createCell(1).setCellValue("16BME3023");

		try {
			FileOutputStream outputStream =new FileOutputStream(".//DataFiles//ColouredExcel.xlsx");

			workbook.write(outputStream);
			System.out.println("Written data successfully");
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error occured!");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws FileNotFoundException {

		CellColour cellColour=new CellColour();
		cellColour.AddColour();
	}
}


