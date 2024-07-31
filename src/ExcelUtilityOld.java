import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.util.Map;

public class ExcelUtilityOld {

    /**
     * Adds a footer to the given sheet with specified left and right strings.
     *
     * @param sheet       The sheet where the footer will be added.
     * @param leftString  The string to set on the left side of the footer.
     * @param rightString The string to set on the right side of the footer.
     */
    public static void addFooter(Sheet sheet, String leftString, String rightString) {
        Footer footer = sheet.getFooter();
        footer.setLeft(leftString);
        footer.setRight(rightString);
    }

    /**
     * Applies a hex color to a cell.
     *
     * @param cell     the cell to which the color will be applied
     * @param hexColor the hex color code (e.g., "#FF5733")
     */
    public static void applyColor(Cell cell, String hexColor) {
        if (hexColor != null) {
            Workbook workbook = cell.getSheet().getWorkbook();

            // Ensure the color is a valid hex color
            if (!hexColor.startsWith("#") || hexColor.length() != 7) {
                throw new IllegalArgumentException("Invalid hex color: " + hexColor);
            }

            // Convert hex to RGB
            int red = Integer.parseInt(hexColor.substring(1, 3), 16);
            int green = Integer.parseInt(hexColor.substring(3, 5), 16);
            int blue = Integer.parseInt(hexColor.substring(5, 7), 16);

            // Create a new XSSFColor object
            XSSFColor xssfColor = new XSSFColor(new java.awt.Color(red, green, blue));

            // Create a new cell style
            XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
            cellStyle.setFillForegroundColor(xssfColor);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Apply the cell style to the cell
            cell.setCellStyle(cellStyle);
        }

    }

    /**
     * Writes a string value to a specified cell in the sheet.
     *
     * @param sheet    The sheet where the cell is located.
     * @param rowIndex The row index (zero-based) where the cell is located.
     * @param colIndex The column index (zero-based) where the cell is located.
     * @param value    The string value to write to the cell.
     */
    public static Cell writeToCell(Sheet sheet, int rowIndex, int colIndex, String value) {
        // Retrieve the row, create it if it does not exist
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        // Retrieve the cell, create it if it does not exist
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }

        // Set the value of the cell
        cell.setCellValue(value);

        return cell;
    }


    public static Cell mergeCells(Sheet sheet, int startRow, int endRow, int startCol, int endCol, String cellValue) {


        if (startRow == endRow && startCol == endCol) {

            return writeToCell(sheet, startRow, startCol, cellValue);

        }
        // Use writeToCell to handle row and cell creation and set the value
        Cell cell = writeToCell(sheet, startRow, startCol, cellValue);

        // Create and apply cell style to center the text
        Workbook wb = sheet.getWorkbook();

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Merge the cells
        CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
        sheet.addMergedRegion(region);
        cell.setCellStyle(cellStyle);

        return cell;
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param sheet  the sheet
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     * @param valign the vertical alignment for the cell.
     */
    private static void createCellWithAlign(Sheet sheet, int rowIndex, int columnIndex, HorizontalAlignment halign, VerticalAlignment valign, String value) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        Workbook wb = sheet.getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    /**
     * Applies a hex color to a cell.
     *
     * @param cell     the cell to which the color will be applied
     * @param hexColor the hex color code (e.g., "#FF5733")
     */


    public static void applyLeftBorderColor(Cell cell, String hexColor) {
        if (hexColor != null && hexColor.matches("^#[0-9A-Fa-f]{6}$")) {
            // Convert hex to RGB
            int red = Integer.parseInt(hexColor.substring(1, 3), 16);
            int green = Integer.parseInt(hexColor.substring(3, 5), 16);
            int blue = Integer.parseInt(hexColor.substring(5, 7), 16);

            // Create a new XSSFColor object
            XSSFColor xssfColor = new XSSFColor(new java.awt.Color(red, green, blue));

            // Create a new cell style
            Workbook workbook = cell.getSheet().getWorkbook();
            XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
            cellStyle.setBorderLeft(BorderStyle.THICK);
            cellStyle.setLeftBorderColor(xssfColor);

            // Apply the cell style to the cell
            cell.setCellStyle(cellStyle);
        }
        // If hexColor is invalid, do nothing
    }

    public static void applyTableBorderStyleOnCells(Sheet sheet, int startRow, int endRow, int startCol, int endCol, BorderStyle borderStyle) {
        for (int rowNum = startRow; rowNum <= endRow; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);

            for (int colNum = startCol; colNum <= endCol; colNum++) {
                Cell cell = row.getCell(colNum);
                if (cell == null) cell = row.createCell(colNum);

                CellStyle style = sheet.getWorkbook().createCellStyle();
                style.setBorderTop(borderStyle);
                style.setBorderBottom(borderStyle);
                style.setBorderLeft(borderStyle);
                style.setBorderRight(borderStyle);

                cell.setCellStyle(style);
            }
        }
    }

    public static void autoSize(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        for (int colNum = 0; colNum < lastRowNum; colNum++)
            sheet.autoSizeColumn(colNum);
    }


    public static void applyTableBorderStyleOnMerge(Sheet sheet, int startRow, int endRow, int startCol, int endCol, String hexColor) {
        // Create a new cell style
        Workbook workbook = sheet.getWorkbook();
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();


        XSSFColor blackColor = new XSSFColor(java.awt.Color.BLACK);
        XSSFColor leftBorderColor = blackColor;
        BorderStyle leftBorderStyle = BorderStyle.THIN;


        if (hexColor != null && hexColor.matches("^#[0-9A-Fa-f]{6}$")) {
            // Convert hex to RGB
            int red = Integer.parseInt(hexColor.substring(1, 3), 16);
            int green = Integer.parseInt(hexColor.substring(3, 5), 16);
            int blue = Integer.parseInt(hexColor.substring(5, 7), 16);

            // Create a new XSSFColor object for the custom border color
            leftBorderColor = new XSSFColor(new java.awt.Color(red, green, blue));
            leftBorderStyle = BorderStyle.THICK;
        }

        // Apply border style
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(blackColor);

        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(blackColor);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(blackColor);

        cellStyle.setBorderLeft(leftBorderStyle);
        cellStyle.setLeftBorderColor(leftBorderColor);



        // Center text alignment
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Apply the cell style to each cell in the range
        for (int rowNum = startRow; rowNum <= endRow; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);

            for (int colNum = startCol; colNum <= endCol; colNum++) {
                Cell cell = row.getCell(colNum);
                if (cell == null) cell = row.createCell(colNum);
                cell.setCellStyle(cellStyle);
            }
        }
    }


    public static void applyTableBorderStyle(Cell cell, String hexColor) {
        Workbook workbook = cell.getSheet().getWorkbook();
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();

        // Default colors and border styles
        XSSFColor blackColor = new XSSFColor(java.awt.Color.BLACK);
        XSSFColor leftBorderColor = blackColor;
        BorderStyle leftBorderStyle = BorderStyle.THIN;

        // If a valid hex color is provided, use it for the left border
        if (hexColor != null && hexColor.matches("^#[0-9A-Fa-f]{6}$")) {
            int red = Integer.parseInt(hexColor.substring(1, 3), 16);
            int green = Integer.parseInt(hexColor.substring(3, 5), 16);
            int blue = Integer.parseInt(hexColor.substring(5, 7), 16);

            // Create a new XSSFColor object for the custom border color
            leftBorderColor = new XSSFColor(new java.awt.Color(red, green, blue));
            leftBorderStyle = BorderStyle.THICK;
        }

        // Apply border styles
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(blackColor);

        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(blackColor);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(blackColor);

        cellStyle.setBorderLeft(leftBorderStyle);
        cellStyle.setLeftBorderColor(leftBorderColor);

        // Center text alignment
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Apply the style to the cell
        cell.setCellStyle(cellStyle);
    }


    public static void applyTableBorderStyle2(Cell cell, String hexColor) {
        if (hexColor != null && hexColor.matches("^#[0-9A-Fa-f]{6}$")) {
            // Convert hex to RGB
            int red = Integer.parseInt(hexColor.substring(1, 3), 16);
            int green = Integer.parseInt(hexColor.substring(3, 5), 16);
            int blue = Integer.parseInt(hexColor.substring(5, 7), 16);

            // Create a new XSSFColor object
            XSSFColor xssfColor = new XSSFColor(new java.awt.Color(red, green, blue));

            // Create a new cell style
            Workbook workbook = cell.getSheet().getWorkbook();
            XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();

            // Set thick left border with the specified color
            cellStyle.setBorderLeft(BorderStyle.THICK);
            cellStyle.setLeftBorderColor(xssfColor);

            // Set thin black borders for top, right, and bottom
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));

            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));

            // Apply the cell style to the cell
            cell.setCellStyle(cellStyle);
        }
        // If hexColor is invalid, do nothing
    }



    /**
     * Writes a key-value pair to the specified sheet starting from the given row and column.
     *
     * @param sheet       The sheet where the data will be written.
     * @param startRow    The starting row index (zero-based) where the key-value pairs will be inserted.
     * @param startColumn The starting column index (zero-based) where the key-value pairs will be inserted.
     * @param data        A map containing key-value pairs to be written to the sheet.
     */
    public static void insertKeyValuePairs(Sheet sheet, int startRow, int startColumn, Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Use writeToCell method to insert key and value
            writeToCell(sheet, startRow, startColumn, key);
            writeToCell(sheet, startRow, startColumn + 1, value);

            // Move to the next row for the next pair
            startRow++;
        }
    }


}




