package Qtrip.QtripProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod (Method m) throws IOException{
        switch(m.getName())
        {
            case "TestCase01" :
                Object[][] testCase1 = readCompleteExcel(System.getProperty("user.dir") + "/src/test/resources/DatasetsforQTrip.xlsx", "TestCase01");
                return testCase1;    
            case "TestCase02" :
                Object[][] testCase2 = readCompleteExcel(System.getProperty("user.dir") + "/src/test/resources/DatasetsforQTrip.xlsx", "TestCase02");
                return testCase2;
            case "TestCase03" :
                Object[][] testCase3 = readCompleteExcel(System.getProperty("user.dir") + "/src/test/resources/DatasetsforQTrip.xlsx", "TestCase03");
                return testCase3;
            case "TestCase04" :
                Object[][] testCase4 = readCompleteExcel(System.getProperty("user.dir") + "/src/test/resources/DatasetsforQTrip.xlsx", "TestCase04");
                return testCase4;
       }
        return null;
    
    }
    public Object[][] readCompleteExcel(String filePath,String sheetName) throws IOException
    {
        Object[][] data = null;
        try{
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int columnCount = row.getLastCellNum();
            XSSFCell cell;
            data = new Object[rowCount-1][columnCount-1];
            for(int i=1;i<rowCount;i++)
            {
                for(int j=1;j<columnCount;j++)
                {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);
                    switch(cell.getCellType())
                    {
                        case STRING :
                            data[i-1][j-1] = cell.getStringCellValue();
                            break;
                        case NUMERIC :
                            if(sheet.getSheetName().equals("TestCase01"))
                                data[i-1][j-1] = String.valueOf(cell.getNumericCellValue());
                            else
                                data[i-1][j-1] = cell.getNumericCellValue();
                            break;
                        case BOOLEAN :
                            data[i-1][j-1] = cell.getBooleanCellValue();
                            break;
                        default : 
                            data[i-1][j-1] = null;
                            break;
                    }
    
                }
            }
            
    
        }
        catch(Exception e)
        {
            System.out.println("Invalid Exception");
        }
        return data;
    }
}