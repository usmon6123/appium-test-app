package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ReadExcels {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Usmon Ataboyev\\Documents\\ob havo\\OneDrive\\Рабочий стол\\Project\\appium_test.xlsx";

        // Excel dosyasını oku
        FileInputStream excelFile = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        // Belirtilen sayfayı al
        XSSFSheet sheet = workbook.getSheet("Лист1");

        int index = 1;
        for (; true; ) {

            if (sheet.getRow(index) != null) {
                XSSFRow row = sheet.getRow(index);
                if (row.getCell(0) != null && row.getCell(1) != null && (row.getCell(2) == null|| Objects.equals(row.getCell(2).getStringCellValue(), ""))) {

                    var title = row.getCell(0).getStringCellValue();
                    var sms = row.getCell(1).getNumericCellValue();
                    close(excelFile, workbook);

                    System.out.println(index + " : burada note qayd ediliyor");


                    writeExcel(12, "success");

                    //excelni qayta o'qishni boshladi
                    excelFile = new FileInputStream(filePath);
                    workbook = new XSSFWorkbook(excelFile);
                    sheet = workbook.getSheet("Лист1");
                }
//                else writeExcel(index, "error");
                index++;
            }

            Thread.sleep(500);
        }
    }

    private static void writeExcel(int rowNumber, String message) throws IOException {
        String filePath = "C:\\Users\\Usmon Ataboyev\\Documents\\ob havo\\OneDrive\\Рабочий стол\\Project\\appium_test.xlsx";

        // Excel dosyasını oku
        FileInputStream excelFile = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet("Лист1");
        // Veriyi eklemek istediğiniz satır ve sütunu seçin (örneğin, satır 0 ve sütun 1)
        int rowIndex = rowNumber;
        int columnIndex = 2;

        // Belirtilen satırı al veya oluştur
        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        // Belirtilen sütunu al veya oluştur
        XSSFCell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }

        // Hücreye değeri ekleyin
        cell.setCellValue(message);

        // Değişiklikleri kaydedin
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


    public static void close(FileInputStream excelFile, XSSFWorkbook workbook) {
        try {
            excelFile.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//
////qatorga kiryapdi
//        for (int rowNumber = 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++) {
//        if (sheet.getRow(rowNumber) != null) {
//        XSSFRow row = sheet.getRow(rowNumber);
//        for (int cellNumber = 0; cellNumber < row.getPhysicalNumberOfCells(); cellNumber++) {
//        if (row.getCell(cellNumber) != null) {
//        XSSFCell cell = row.getCell(cellNumber);
//        switch (cell.getCellType().getCode()) {
//        case 1:
//        System.out.print(cell.getStringCellValue() + "\t");
//        break;
//        case 0:
//        System.out.print(cell.getNumericCellValue() + "\t");
//        break;
//        }
//        }
//        }
//        System.out.println();
//        }
//
//        }
//
