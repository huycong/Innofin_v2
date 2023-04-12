package com.innofin.api.helper;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    public static Object[][] getTableArray(String FilePath, String SheetName, int totalCols) throws Exception {
        Log.info("ExcelUtil-->getTableArray");
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int startRow = 1;
            int startCol = 0;
            int ci,cj;
            int totalRows = ExcelWSheet.getLastRowNum();
            Log.info("totalRows : " + totalRows);
            // you can write a function as well to get Column count
            tabArray = new String[totalRows][totalCols];
            ci=0;
            for (int i = startRow;i <= totalRows; i++, ci++) {
                cj=0;
                for (int j=startCol;j < totalCols;j++, cj++){
                    tabArray[ci][cj]=getCellData(i,j);
                    Log.info(tabArray[ci][cj]);
                }
            }
        }
        catch (FileNotFoundException e){
            Log.error("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            Log.error("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return(tabArray);
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        Log.info("ExcelUtil-->getCellData");
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            CellType dataType = Cell.getCellType();
            if  (dataType.toString() == "3") {
                return "";
            }else {
                String CellData = Cell.getStringCellValue();
                return CellData;
            }
        }catch (Exception e){
                Log.error(e.getMessage());
                throw (e);
            }

        }

    }
