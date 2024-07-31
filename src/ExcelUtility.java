import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.util.Map;

public class ExcelUtility {

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
     * Writes a string value to a specified cell in the sheet.
     *
     * @param sheet    The sheet where the cell is located.
     * @param rowIndex The row index (zero-based) where the cell is located.
     * @param colIndex The column index (zero-based) where the cell is located.
     * @param value    The string value to write to the cell.
     * @return The cell where the value was written.
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

    /**
     * Merges cells in a rectangular region and sets the cell value and style.
     *
     * @param sheet     The sheet where cells will be merged.
     * @param startRow  The starting row index (zero-based) for the merge.
     * @param endRow    The ending row index (zero-based) for the merge.
     * @param startCol  The starting column index (zero-based) for the merge.
     * @param endCol    The ending column index (zero-based) for the merge.
     * @param cellValue The value to set in the merged cell.
     * @return The cell that was merged and styled.
     */
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
     * Creates a cell with specified alignment and value.
     *
     * @param sheet       The sheet where the cell will be created.
     * @param rowIndex    The row index (zero-based) where the cell will be created.
     * @param columnIndex The column index (zero-based) where the cell will be created.
     * @param halign      The horizontal alignment for the cell.
     * @param valign      The vertical alignment for the cell.
     * @param value       The value to set in the cell.
     */
    private static void createCellWithAlign(Sheet sheet, int rowIndex, int columnIndex, HorizontalAlignment halign, VerticalAlignment valign, String value) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        Workbook wb = sheet.getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    /**
     * Applies a hex color to the left border of a cell.
     *
     * @param cell     The cell to which the color will be applied.
     * @param hexColor The hex color code (e.g., "#FF5733").
     * @throws IllegalArgumentException if the hexColor is not in the format "#RRGGBB".
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
    }


    /**
     * Auto-sizes all columns in the sheet.
     *
     * @param sheet The sheet where the columns will be auto-sized.
     */
    public static void autoSize(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        for (int colNum = 0; colNum < lastRowNum; colNum++)
            sheet.autoSizeColumn(colNum);
    }

    /**
     * Applies border styles to a range of cells with optional custom left border color.
     *
     * @param sheet      The sheet where the border styles will be applied.
     * @param startRow   The starting row index (zero-based) for the border.
     * @param endRow     The ending row index (zero-based) for the border.
     * @param startCol   The starting column index (zero-based) for the border.
     * @param endCol     The ending column index (zero-based) for the border.
     * @param hexColor   The hex color code for the left border (e.g., "#FF5733").
     */
    public static void applyTableBorderStyleOnMerge(Sheet sheet, int startRow, int endRow, int startCol, int endCol, String hexColor) {
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
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            for (int colNum = startCol; colNum <= endCol; colNum++) {
                Cell cell = row.getCell(colNum);
                if (cell == null) {
                    cell = row.createCell(colNum);
                }
                cell.setCellStyle(cellStyle);
            }
        }
    }

    /**
     * Applies border styles to a single cell with optional custom left border color.
     *
     * @param cell     The cell to which the border styles will be applied.
     * @param hexColor The hex color code for the left border (e.g., "#FF5733").
     * @throws IllegalArgumentException if the hexColor is not in the format "#RRGGBB".
     */
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

    /**
     * Writes key-value pairs to the specified sheet starting from the given row and column.
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
