package Exceldata;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Excel {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\sireesha_narakuchi\\OneDrive - EPAM\\Pictures\\exceldata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheesnum = workbook.getNumberOfSheets();

        for (int i = 0; i < sheesnum; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();

                Row firstrow = rows.next();

                Iterator<Cell> ce = firstrow.cellIterator();
                int k = 0;
                int column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
                        column = k;
                    }

                    k++;
                }

                while (rows.hasNext())
                {
                   Row r= rows.next();

                if (   r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase"))
                {

                  Iterator<Cell> c=r.cellIterator();

                  while (c.hasNext())
                  {
                     System.out.println(c.next().getStringCellValue());
                  }
                }
                }
            }

        }
    }
}