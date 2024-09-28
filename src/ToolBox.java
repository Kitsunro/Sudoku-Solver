import Sudoku.Sudoku;
import Rules.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class ToolBox {
    public static int State = 1;
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

    public static void readInput(){
        System.out.println("Please enter which number to add => {ex 245 : put 5 at x=2 y=5}");
        boolean correct = true;
        while(correct) {
            String input = System.console().readLine();
            if(input.length() == 3){
                String[] inputs = input.split("");
                Sudoku.getInstance().setValue(Integer.parseInt(inputs[0]),Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]));
                Sudoku.getInstance().show();
                break;
            }
            System.out.println("Incorrect format. please try again");
        }
    }

    public static void applyRules(DR1 r1,DR2 r2,DR3 r3) {
        boolean noError = true;
        while (noError) {
            while (State < 4) {
                System.out.println("Using " + State + " rule:");
                do {
                    Sudoku.getInstance().modified = false;
                    for (int i = 0; i < 81; i++) {
                        for (int x = 1; x < 10; x++) {
                            r1.apply(Sudoku.getInstance(), i % 9, i / 9, x);
                            if (State > 1) {
                                r2.apply(Sudoku.getInstance(), i % 9, i / 9, x);
                            }
                            if (State > 2) {
                                r3.apply(Sudoku.getInstance(), i % 9, i / 9, x);
                            }
                        }
                    }
                } while (Sudoku.getInstance().modified);
                Sudoku.getInstance().show();
                if (Sudoku.getInstance().isFinished()) {
                    System.out.println("Finished!");
                    return;
                }
                State += 1;
            }
            System.out.println("All the rules aren't enough, please choose...");
            readInput();
        }
    }
}
