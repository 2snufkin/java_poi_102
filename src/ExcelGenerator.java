
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    public class ExcelGenerator {
        /**
         * public ByteArrayOutputStream generateExcelWithNoBoites() throws IOException {
         *         // Set the name for the sheet and normalize it
         *         String freezerName = "NBT -80 / Congél VI";
         *         String safeSheetName = WorkbookUtil.createSafeSheetName(freezerName).replaceAll("\\s+", " ");
         *
         *         // Create a Map to hold data
         *         Map<String, String> data = createDataMap();
         *
         *         // Create Enceinte
         *         List<Enceinte> enceintes = createContainers();
         *
         *         // Create workbook and sheet
         *         try (XSSFWorkbook workbook = new XSSFWorkbook()) {
         *             Sheet sheet = workbook.createSheet(safeSheetName);
         *
         *             // Add current date to the first cell
         *             Row row = sheet.createRow(0);
         *             Cell cell = row.createCell(0);
         *             cell.setCellValue(getCurrentDate());
         *
         *             // Insert data and containers into the sheet
         *             ExcelUtility.insertKeyValuePairs(sheet, 1, 0, data);
         *             ExcelUtility.insertWithNoBoite(enceintes, sheet, 7, workbook);
         *
         *
         *             addFooter(freezerName, sheet);
         *
         *             // Write workbook to ByteArrayOutputStream
         *             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         *             workbook.write(outputStream);
         *             return outputStream;
         *         }
         *     }
         * @return
         * @throws IOException
         */



     }


