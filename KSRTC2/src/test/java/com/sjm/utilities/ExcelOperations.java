package com.sjm.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations

{
	private static XSSFWorkbook wb;
	private static XSSFSheet sh;
	private static DataFormatter formatter = new DataFormatter();
	private static String file = "SignIn.xlsx";
	//private static String sheetName="SignIn";


	public static String getData(String sheetName, int rwNo, int colNo) {
		XSSFCell cell = null;
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;

		try {			
			
			URL resource = ClassLoader.getSystemResource(file);
			
			InputStream stream = resource.openStream();

			wb = new XSSFWorkbook(stream);

			sh = wb.getSheet(sheetName);

			if (null == sh) {
				throw new Exception("null == sh");
			}

			cell = sh.getRow(rwNo).getCell(colNo);

			if (null == cell) {
				throw new Exception("null == cell");
			}

			String rtnVal = cell.getStringCellValue();

			DataFormatter formatter = new DataFormatter();
			
			rtnVal = formatter.formatCellValue(cell);

			return rtnVal.trim();

		} catch (IOException e) {
			return e.toString();
		} catch (Exception e) {
			return e.toString();
		} finally {
			try {
				if (null != wb) {
					wb.close();
				}
			} 
			catch (IOException e) {
				return e.toString();
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

	}

}