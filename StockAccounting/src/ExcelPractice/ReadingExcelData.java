package ExcelPractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import CommonFunctionLibrary.CreateSuplier;




public class ReadingExcelData
{
	
public static void main(String[] args) throws Exception
{
	CreateSuplier app=new CreateSuplier();
	app.appLaunch("http://webapp.qedge.com");
	app.appLogin("admin", "master");
		// TODO Auto-generated method stub
	File srcFile=new File("C:\\Users\\swathi.r\\Swathi_Automation\\Files\\SuplierData.xlsx");
	FileInputStream fis=new FileInputStream(srcFile);
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	XSSFSheet sheet=wb.getSheet("Suplier");
	int rowCount=sheet.getLastRowNum();
	//String celldata="";
	for (int i = 1; i <=rowCount; i++) 
	{
//		if(sheet.getRow(i).getCell(0).getCellType()==Cell.CELL_TYPE_NUMERIC)
//	{
//		int data=(int)sheet.getRow(i).getCell(0).getNumericCellValue();
//		celldata=String.valueOf(data);
//		
//	}
//	else
//	{
//		celldata=sheet.getRow(i).getCell(0).getStringCellValue();
//	//String address=sheet.getRow(i).getCell(1).getStringCellValue();
//	//System.out.println(suplierName +" "+address);
//	}
		
		String sname=sheet.getRow(i).getCell(0).getStringCellValue();
		String address=sheet.getRow(i).getCell(1).getStringCellValue();
		String city=sheet.getRow(i).getCell(2).getStringCellValue();
		String country=sheet.getRow(i).getCell(3).getStringCellValue();
		String conPerson=sheet.getRow(i).getCell(4).getStringCellValue();
		int pNum=(int) sheet.getRow(i).getCell(5).getNumericCellValue();
		String pNumber=String.valueOf(pNum);
		String email=sheet.getRow(i).getCell(6).getStringCellValue();
		int mNum=(int) sheet.getRow(i).getCell(7).getNumericCellValue();
		String mNumber=String.valueOf(mNum);
		String note=sheet.getRow(i).getCell(8).getStringCellValue();
		String results=app.createSuplier(sname,address,city,country,conPerson,pNumber,email,mNumber,note);
		sheet.getRow(i).createCell(9).setCellValue(results);
		FileOutputStream fos=new FileOutputStream(srcFile);
		wb.write(fos);
		
}
	fis.close();
	wb.close();
}
}
