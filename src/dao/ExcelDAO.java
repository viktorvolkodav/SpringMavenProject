package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component("excelgDAO")
public class ExcelDAO {

	public Map<String, Map<String, Double>> searchBankInfoInExcel(String fileName, int mfo) throws IOException {
		Map<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();
		FileInputStream fis = new FileInputStream(new File(fileName));
		HSSFWorkbook workbook = new HSSFWorkbook(fis);

		for (int i = 0; i < 4; i++) {
			HSSFSheet spreadsheet = workbook.getSheetAt(i);
			map.put(workbook.getSheetName(i), searchBankInfoInExcelSheet(spreadsheet, mfo));
		}

		workbook.close();
		fis.close();
		return map;
	}

	private Map<String, Double> searchBankInfoInExcelSheet(HSSFSheet spreadsheet, int mfo) throws IOException {

		List<String> stringList = new ArrayList<String>();
		List<Double> doubleList = new ArrayList<Double>();

		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {

			HSSFRow row = (HSSFRow) rowIterator.next();
			Cell cell = row.getCell(3, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					if ((int) cell.getNumericCellValue() == mfo) {

						Row row1 = cell.getRow();
						Iterator<Cell> cellIterator = row1.cellIterator();
						while (cellIterator.hasNext()) {
							Cell cell1 = cellIterator.next();
							switch (cell1.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								doubleList.add(cell1.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								doubleList.add(null);
								break;

							}
						}

					}
			}

		}

		HSSFRow row = spreadsheet.getRow(4);
		for (Cell cell2 : row) {
			switch (cell2.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				stringList.add(cell2.getStringCellValue());
				break;

			}
		}

		Map<String, Double> map = new LinkedHashMap<String, Double>();
		for (int i = 5; i < stringList.size(); i++) {
			map.put(stringList.get(i), doubleList.get(i));
		}

		return map;
	}

}
