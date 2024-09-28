import Sudoku.Sudoku;
import Rules.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        List<Integer> temp = Stream.of(content.replace("\r\n",",").split(",")).map(Integer::parseInt).toList();
        return new ArrayList<>(temp);
    }

    public static void applyRules(DR1 r1,DR2 r2,DR3 r3) {
        int state = 1;
        while (state < 4) {
            System.out.println("Applying one more rule");
            do {
                Sudoku.getInstance().modified = false;
                for (int i = 0; i < 81; i++) {
                    for (int x = 1; x < 10; x++) {
                        r1.apply(Sudoku.getInstance(), i % 9, i / 9, x);
                        if(state>1){
                            r2.apply(Sudoku.getInstance(),i%9,i/9,x);
                        }
                        //if(state>2){
                        //    r3.apply(Sudoku.getInstance(),i%9,i/9,x);
                        //}
                    }
                }
            } while (Sudoku.getInstance().modified);
            Sudoku.getInstance().show();
            state += 1;
        }
        System.out.println("All the rules aren't enough, please choose...");
    }
}
