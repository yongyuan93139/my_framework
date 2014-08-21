package com.lu.jxl;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportExample {
	
	public static void main(String[] args) {
		importExl();
	}
	
	private static void importExl(){
		File file = new File("export.xls");
		
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			
			sheet.getCell(0, 1);
			sheet.getCell(1,1);
			
			sheet.getCell(0,2).getContents();
			sheet.getCell(1,2);
			
			System.out.println("******************");
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
