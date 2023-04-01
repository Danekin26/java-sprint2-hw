import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadDataFromFiles {

    public String readFileContentsOrNull(String path){   // Считывание данных с файлов
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e){
            System.out.println("Невозможно прочитать файл с отчётом. Запрашиваемые данные отсутствуют в директории.");
            return null;
        }
    }
}
