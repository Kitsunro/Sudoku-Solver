import Sudoku.Sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class ToolBox {
    private ToolBox() {}

    public static List<Integer> fileToIntList(String input) {
        String content;
        try {
            content = new String(Files.readAllBytes(Path.of(input)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Stream.of(content.replace("\r\n",",").split(",")).map(Integer::parseInt).toList();
    }
}
