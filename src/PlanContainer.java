import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.WorkbookUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlanContainer {

    //  **************************************  with boites  ***********************************************************


    public ByteArrayOutputStream generateExcelWithBoites(List<Container> containers) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        for (Container container : containers) {


            // Set the name for the sheet and normalize it
            String safeSheetName = WorkbookUtil.createSafeSheetName(container.getName()).replaceAll("\\s+", " ");
            // Create sheet
            Sheet sheet = workbook.createSheet(safeSheetName);

            // Add current date to the first cell
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(getCurrentDate());

            // Insert data and containers into the sheet
            ExcelUtility.insertKeyValuePairs(sheet, 1, 0, createHeaderSection(container));
            writeContainerWithBoitesToSheet(sheet, container, 6);




            ExcelUtility.addFooter(sheet, container.getName(), getCurrentDate());
            ExcelUtility.autoSize(sheet);

        }

        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }


    private void writeContainerWithBoitesToSheet(Sheet sheet, Container container, int rowIndex) {
        int columnIndx = 0;
        int firstLevelEnceinteRow = rowIndex;
        int secondLevelRow = rowIndex + 1; // Use incremented row index directly
        int firstBoiteRow = secondLevelRow + 1;
        if (container.getEnceintes().size() == 0){
            firstBoiteRow = secondLevelRow; // Adjust if no enceintes
        }

        for (Enceinte enceinte : container.getEnceintes()) {
            int endColumn = columnIndx + enceinte.getSubEnceintes().size() - 1;
            endColumn = Math.max(endColumn, 0);
            ExcelUtility.mergeCells(sheet, firstLevelEnceinteRow, firstLevelEnceinteRow, columnIndx, endColumn, createTitle(enceinte.getName(), enceinte.getAlias()));

            ExcelUtility.applyTableBorderStyleOnMerge(sheet, firstLevelEnceinteRow, firstLevelEnceinteRow, columnIndx, endColumn,  enceinte.getColor()) ;

                // Reset column index for each Enceinte
            int currentColumnIndx = columnIndx;

            for (Enceinte subEncients : enceinte.getSubEnceintes()) {
                writeEnciente(sheet, subEncients, secondLevelRow, currentColumnIndx);
                writeBoites(sheet, subEncients.getBoites(), firstBoiteRow, currentColumnIndx);
                currentColumnIndx++; // Increment column index for each subEnceinte
            }

            columnIndx += enceinte.getSubEnceintes().size(); // Move column index for next Enceinte
        }
    }

    private String createTitle(String name, String alias) {
        StringBuilder title = new StringBuilder();
        title.append(name)
                .append(" (")
                .append(alias)
                .append(")");
        return title.toString();
    }


    private void writeBoites(Sheet sheet, List<Boite> boites, int rowIndx, int columnIndx) {
        for (Boite boite : boites) {
            String boiteName = createTitle(boite.getName(), boite.getAlias());
            Cell enceinteCell = ExcelUtility.writeToCell(sheet, rowIndx, columnIndx, boiteName);
            ExcelUtility.applyTableBorderStyle(enceinteCell, boite.getColor());
            rowIndx++;
        }
    }
    public static Map<String, String> createHeaderSection(Container container) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("Nom de congélateur", container.getName());
        data.put("Description", container.getDescription());
        data.put("Etablissement/ Service", "69 : HCL/GHE : H. NEUROLOGIQUE / NEUROBIOTEC");
        return data;
    }
    private void writeEnciente(Sheet sheet, Enceinte subEncient, int rowIndex, int colIndex) {
        String enceinteName = createTitle(subEncient.getName(), subEncient.getAlias());
        Cell enceinteCell = ExcelUtility.writeToCell(sheet, rowIndex, colIndex, enceinteName);
        ExcelUtility.applyTableBorderStyle(enceinteCell, subEncient.getColor());

    }


//  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  tested, dont touch  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public void insertWithNoBoite(List<Enceinte> enceintes, Sheet sheet, int startRow) {
    int currentRow = startRow;
    int enceinteCol = 0;
    int subEnceinteCol = 1;
    int boiteCol = 2; // Column for Boite

    for (Enceinte mainContainer : enceintes) {
        // Insert main container details into the specified cell
        String enceinteName = createTitle(mainContainer.getName(), mainContainer.getAlias());
        Cell enceinteCell = ExcelUtility.writeToCell(sheet, currentRow, enceinteCol, enceinteName);
        ExcelUtility.applyLeftBorderColor(enceinteCell, mainContainer.getColor());

        currentRow++;
        // Insert boites within this main container
        for (Boite boite : mainContainer.getBoites()) {
            String boiteName = boite.getName();
            Cell boiteCell = ExcelUtility.writeToCell(sheet, currentRow, boiteCol, boiteName);
            // Assume a method to get the color for a Boite
            ExcelUtility.applyLeftBorderColor(boiteCell, boite.getColor());
            currentRow++;
        }

        // Insert sub-containers
        for (Enceinte enceinte : mainContainer.getSubEnceintes()) {
            String subEnceinteName = createTitle(enceinte.getName(), enceinte.getAlias());
            Cell subEnceinteCell = ExcelUtility.writeToCell(sheet, currentRow, subEnceinteCol, subEnceinteName);
            ExcelUtility.applyLeftBorderColor(subEnceinteCell, enceinte.getColor());

            currentRow++; // Move to the next row for the next sub-container

            // Insert boites within this sub-container
            for (Boite boite : enceinte.getBoites()) {
                String boiteName = boite.getName();
                Cell boiteCell = ExcelUtility.writeToCell(sheet, currentRow, boiteCol, boiteName);
                // Assume a method to get the color for a Boite
                ExcelUtility.applyLeftBorderColor(boiteCell, boite.getColor());
                currentRow++;
            }

            // Recursively insert sub-sub-containers (if any)
            insertWithNoBoite(enceinte.getSubEnceintes(), sheet, currentRow);
        }
    }
}

    public void insertWithNoBoite2(List<Enceinte> enceintes, Sheet sheet, int startRow) {
    int currentRow = startRow;
    int enceinteCol = 0;
    int subEnceinteCol = 1;

    for (Enceinte mainContainer : enceintes) {
        // Insert main container details into the specified cell
        String enceinteName = createTitle(mainContainer.getName(), mainContainer.getAlias());
        Cell enceinteCell = ExcelUtility.writeToCell(sheet, currentRow, enceinteCol, enceinteName);
        ExcelUtility.applyLeftBorderColor(enceinteCell, mainContainer.getColor());

        currentRow++;
        // Insert sub-containers
        for (Enceinte enceinte : mainContainer.getSubEnceintes()) {
            String subEnceinteName = createTitle(enceinte.getName(), enceinte.getAlias());
            Cell subEnceinteCell = ExcelUtility.writeToCell(sheet, currentRow, subEnceinteCol, subEnceinteName);
            ExcelUtility.applyLeftBorderColor(subEnceinteCell, enceinte.getColor());
            currentRow++; // Move to the next row for the next sub-container

        }
    }
}

    public ByteArrayOutputStream generateExcelWithNoBoites(List<Container> containers) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        for (Container container : containers) {
            // Set the name for the sheet and normalize it
            String freezerName = container.getName();
            String safeSheetName = WorkbookUtil.createSafeSheetName(freezerName).replaceAll("\\s+", " ");

            // Create workbook and sheet
            Sheet sheet = workbook.createSheet(safeSheetName);

            // Add current date to the first cell
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(getCurrentDate());
            List<Enceinte> enceintes = container.getEnceintes();

            // Sort enceintes by position
            sortEnceintesByPosition(enceintes);

            // Insert data and containers into the sheet
            ExcelUtility.insertKeyValuePairs(sheet, 1, 0, createHeaderSection(container));
            insertWithNoBoite(enceintes, sheet, 7);

            ExcelUtility.addFooter(sheet, freezerName, getCurrentDate());
            ExcelUtility.autoSize(sheet);
        }

        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }
    public ByteArrayOutputStream generateExcelWithNoBoites2(List<Container> containers) throws IOException {


        Workbook workbook = new XSSFWorkbook();

        for (Container container : containers) {

            // Set the name for the sheet and normalize it
            String freezerName = container.getName();
            String safeSheetName = WorkbookUtil.createSafeSheetName(freezerName).replaceAll("\\s+", " ");

            // Create workbook and sheet
            Sheet sheet = workbook.createSheet(safeSheetName);

            // Add current date to the first cell
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(getCurrentDate());
            List<Enceinte> enceintes = container.getEnceintes();

            sortEnceintesByPosition(enceintes);


            // Insert data and containers into the sheet
            ExcelUtility.insertKeyValuePairs(sheet, 1, 0, createHeaderSection(container));
            insertWithNoBoite(enceintes, sheet, 7);

            ExcelUtility.addFooter(sheet, freezerName, getCurrentDate());
            ExcelUtility.autoSize(sheet);

        }
        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }


    private void sortEnceintesByPosition(List<Enceinte> enceintes) {
        if (enceintes == null) return;

        Collections.sort(enceintes, Comparator.comparingInt(Enceinte::getPosition));
        for (Enceinte enceinte : enceintes) {
            sortEnceintesByPosition(enceinte.getSubEnceintes());
            sortBoitesByPosition(enceinte.getBoites());
        }
        System.out.println(enceintes);

    }

    private void sortBoitesByPosition(List<Boite> boites) {
        if (boites == null) return;

        Collections.sort(boites, Comparator.comparingInt(Boite::getPosition));
    }

    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.now().format(formatter);
    }


}
