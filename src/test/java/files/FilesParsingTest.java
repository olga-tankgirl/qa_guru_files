package files;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void parseCsvTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files/example.csv")) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(3)
                    .contains(
                            new String[]{"Author", "Book"},
                            new String[]{"Block", "Apteka"},
                            new String[]{"Esenin", "Cherniy Chelovek"}
                    );
        }
    }

    @Test
    void parseXlsTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("src/test/resources/xlsx.xlsx")) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue())
                    .isEqualTo("Abril");
        }
    }

}
