package Utils;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.*;
import Utils.RunConfig;

public class ReadExcel {

	public void readData() {
		try {
			FileInputStream inputstream= new FileInputStream(RunConfig.DATA_PATH);
			XSSFWorkbook workbook =new XSSFWorkbook(inputstream);	
			//XSSFSheet sheet = workbook.getSheet("District Sheet");
			XSSFSheet sheet = workbook.getSheetAt(0);
			int l_row_num= sheet.getLastRowNum();
			int l_column_Num= sheet.getRow(1).getLastCellNum();
			for (int r = 0; r <= l_row_num; r++) {
				XSSFRow row= sheet.getRow(r);
				for(int c=0;c<l_column_Num;c++) {
					XSSFCell cell = row.getCell(c);

					switch (cell.getCellType()) {
					case STRING:
						System.out.println(cell.getStringCellValue());
						break;
					case NUMERIC:
						System.out.println(cell.getNumericCellValue());
						break;
					case BOOLEAN:
						System.out.println(cell.getBooleanCellValue());
						break;
					case FORMULA:
						System.out.println(cell.getNumericCellValue());
						break;
					default:
						break;
					}
					//System.out.println(cell.toString());
				}
				System.out.println("...........");
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void iteratorData() {
		try {
			FileInputStream inputstream= new FileInputStream(".//DataFiles//ProtectedData.xlsx");
			//XSSFWorkbook workbook =new XSSFWorkbook(inputstream);	
			XSSFWorkbook workbook= (XSSFWorkbook) WorkbookFactory.create(inputstream, "12345");//Read data from password protected file
			//XSSFSheet sheet = workbook.getSheet("District Sheet");
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> iterator= sheet.rowIterator();

			while(iterator.hasNext()){

				XSSFRow	row= (XSSFRow) iterator.next();
				Iterator<Cell> cellIterator=row.cellIterator();

				while(cellIterator.hasNext()) {
					XSSFCell cell=(XSSFCell) cellIterator.next();
					switch (cell.getCellType()) {
					case STRING:
						System.out.println(cell.getStringCellValue());
						break;
					case NUMERIC:
						System.out.println(cell.getNumericCellValue());
						break;
					case BOOLEAN:
						System.out.println(cell.getBooleanCellValue());
						break;
					default:
						break;
					}
				}
				System.out.println("...........");
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String args[]) {
		ReadExcel readExcel=new ReadExcel();
		readExcel.readData();
		//readExcel.iteratorData();

	}

}
