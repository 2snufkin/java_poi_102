import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MainTest {
    @Test
    public void testGenerateAndDownloadExcelWithNoBoite() throws IOException {
        // Create instance of your main class
        PlanContainer main = new PlanContainer();
        List<Container> containers = DataClass.createContainersWithNoBoite();

        // Call the method to generate Excel
        ByteArrayOutputStream outputStream = main.generateExcelWithNoBoites(containers);

        // Verify the content written to ByteArrayOutputStream
        byte[] content = outputStream.toByteArray();

        // Validate the content of the Excel file
        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(content))) {

            // Save the workbook to a file
            String filePath = "no_boite_multi_containers_position.xlsx"; // Replace with your desired file path
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }




    @Test
    public void testGenerateAndDownloadExcelWithNoBoiteOneContainer() throws IOException {
        // Create instance of your main class
        PlanContainer main = new PlanContainer();
        List<Container> Container = DataClass.createContainersWithNoBoite();
        // Using streams to filter the list
        // Using streams to filter the list
        // Using streams to keep only the first item
        List<Container> filteredList = Container.stream()
                .limit(1) // Keep only the first item
                .collect(Collectors.toList());
        // Call the method to generate Excel
        ByteArrayOutputStream outputStream = main.generateExcelWithNoBoites(filteredList);

        // Verify the content written to ByteArrayOutputStream
        byte[] content = outputStream.toByteArray();

        // Validate the content of the Excel file
        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(content))) {

            // Save the workbook to a file
            String filePath = "no_boite_one_container.xlsx"; // Replace with your desired file path
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }



    @Test
    public void testGenerateWithBoitesAndDownloadExcel() throws IOException {
        // Create instance of your main class
        PlanContainer main = new PlanContainer();
        List<Container> containers = DataClass.createContainersWithBoites();
        // Call the method to generate Excel
        ByteArrayOutputStream outputStream = main.generateExcelWithBoites(containers);

        // Verify the content written to ByteArrayOutputStream
        byte[] content = outputStream.toByteArray();

        // Validate the content of the Excel file
        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(content))) {

            // Save the workbook to a file
            String filePath = "with_boites_multi_container.xlsx"; // Replace with your desired file path
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }

    @Test
    public void testGenerateWithBoitesAndDownloadExcelOneContainer() throws IOException {
        // Create instance of your main class
        PlanContainer main = new PlanContainer();
        List<Container> containers = DataClass.createContainersWithBoites();

        List<Container> filteredList = containers.stream()
                .limit(1) // Keep only the first item
                .collect(Collectors.toList());
        // Call the method to generate Excel
        ByteArrayOutputStream outputStream = main.generateExcelWithBoites(filteredList);

        // Verify the content written to ByteArrayOutputStream
        byte[] content = outputStream.toByteArray();

        // Validate the content of the Excel file
        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(content))) {

            // Save the workbook to a file
            String filePath = "with_boites_one_container.xlsx"; // Replace with your desired file path
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }
}
