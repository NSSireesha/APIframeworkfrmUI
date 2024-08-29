package Exceldata;

import jdk.nashorn.internal.ir.WhileNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ICell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Excelrun {


    public static void main(String args[]) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\sireesha_narakuchi\\OneDrive - EPAM\\Pictures\\exceldata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        Integer sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {


            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> row = sheet.iterator();
                Row firstrow = row.next();

                Iterator<Cell> cell = firstrow.cellIterator();
                int k = 0;
                int column = 0;
                while (cell.hasNext()) {
                    Cell cellvalue = cell.next();

                    if (cellvalue.getStringCellValue().equalsIgnoreCase("Test case")) {
                      column=k;

                    }
                    k++;


                }
                System.out.println(column);

                while(row.hasNext())
                {
                    Row r= row.next();
                    //System.out.println(r.getCell(column).getStringCellValue());
                   if ( r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase"))
                   {

                       Iterator<Cell> cell1=r.cellIterator();
                       while(cell1.hasNext())
                       {
                           System.out.println(cell1.next().getStringCellValue());

                       }
                       //System.out.println(cellvalue);
                   }
                }

            }




        }
    }}


