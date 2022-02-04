package files;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FilesHomeworkTest {

    @Test
    void downloadTest() {
        File downloadedFile = Selenide.$(cssSelector: "#raw-url").download();
    }
}
