package lesson_14;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;

public class ExcelReader {
    public static void main(String[] args) {
        try (InputStream inputStream = ExcelReader.class.getClassLoader().getResourceAsStream("Homework_14.xlsx")) {
            if (inputStream == null) {
                System.err.println("File not found!");
                return;
            }

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print("Unknown cell type\t");
                    }
                }
                System.out.println();
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
