package utils;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    private final String filePath;
    private final Workbook workbook;
    private final Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        fis.close();
    }

    // ✅ Get total row count (includes last row)
    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public String getCellData(int row, int col) {
        Row r = sheet.getRow(row);
        if (r == null) return "";
        Cell c = r.getCell(col);
        if (c == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(c); // safe for all data types
    }

    // ✅ Writes result + sets background color
    public void setCellData(int rowNum, int colNum, String value, boolean match) throws IOException {
        Row row = sheet.getRow(rowNum);
        if (row == null)
            row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null)
            cell = row.createCell(colNum);

        cell.setCellValue(value);

        // ✅ Style settings
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        // Always black text
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // ✅ Background colors
        if (match) {
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex()); // green background
        } else {
            style.setFillForegroundColor(IndexedColors.ROSE.getIndex()); // soft red background
        }
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
    }

    public void saveAndClose() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
