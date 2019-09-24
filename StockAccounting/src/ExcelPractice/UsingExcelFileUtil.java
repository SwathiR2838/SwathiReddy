package ExcelPractice;

import java.io.FileOutputStream;

import CommonFunctionLibrary.StockAccountingLibrary;

public class UsingExcelFileUtil {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		ExcelFileUtil futil=new ExcelFileUtil();
		StockAccountingLibrary funlib=new StockAccountingLibrary();
		funlib.appLaunch("http://webapp.qedge.com");
		funlib.appLogin("admin", "master");
		for (int i = 0; i < futil.rowCount("Sheet1"); i++) 
		{
			for (int j = 0; j < futil.colCount("Sheet1", i); j++) 
			{
//				String sname=sheet.getRow(i).getCell(0).getStringCellValue();
//				String address=sheet.getRow(i).getCell(1).getStringCellValue();
//				String city=sheet.getRow(i).getCell(2).getStringCellValue();
//				String country=sheet.getRow(i).getCell(3).getStringCellValue();
//				String conPerson=sheet.getRow(i).getCell(4).getStringCellValue();
//				int pNum=(int) sheet.getRow(i).getCell(5).getNumericCellValue();
//				String pNumber=String.valueOf(pNum);
//				String email=sheet.getRow(i).getCell(6).getStringCellValue();
//				int mNum=(int) sheet.getRow(i).getCell(7).getNumericCellValue();
//				String mNumber=String.valueOf(mNum);
//				String note=sheet.getRow(i).getCell(8).getStringCellValue();
//				String results=app.createSuplier(sname,address,city,country,conPerson,pNumber,email,mNumber,note);
//				sheet.getRow(i).createCell(9).setCellValue(results);
//				FileOutputStream fos=new FileOutputStream(srcFile);
//				wb.write(fos);
				
				String data=futil.getData("Sheet1", i, j);
			System.out.println(data);
				
				
			}
			
		//	funlib.createSuplier(sName, address, city, country, contactPerson, pNumber, email, mNumber, note)
		}
	}

}
