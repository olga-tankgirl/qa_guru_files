package files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeworkTest {

    @Test
    void Homework() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/zip.zip");

        ZipEntry zipEntry = zipFile.getEntry("pdf.pdf");
        try (InputStream pdfStream = zipFile.getInputStream(zipEntry)) {
            PDF parsed = new PDF(pdfStream);
            assertThat(parsed.text).contains("PDF-file");
        }

        zipEntry = zipFile.getEntry("xlsx.xlsx");
        try (InputStream xlsStream = zipFile.getInputStream(zipEntry)) {
            XLS parsed = new XLS(xlsStream);
            assertThat(parsed.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue())
                    .isEqualTo("XLSX-file");
        }

        zipEntry = zipFile.getEntry("csv.csv");
        try (InputStream csvStream = zipFile.getInputStream(zipEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(csvStream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(2)
                    .contains(
                            new String[]{"CSV-file", "UNO"},
                            new String[]{"DUO", "TRES"});
        }
    }


}
