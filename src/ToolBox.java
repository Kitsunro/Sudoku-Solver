import Sudoku.Sudoku;
import Rules.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.lang.System;

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
        return new ArrayList<>(temp.stream().map(x -> x==0?-1:x).toList());
    }

    public static void readInput(){
        System.out.println("Waiting for user input, follow this format => 245 (will put 5 at x=2 y=4)");
        boolean correct = true;
        while(correct) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter input: ");
            String input = scanner.nextLine();
            if(input.length() == 3){
                List<Integer> inputs = Arrays.stream(input.split("")).map(Integer::parseInt).toList();
                Sudoku.getInstance().setValue(inputs.get(0),inputs.get(1),inputs.get(2));
                if(Sudoku.getInstance().possible(inputs.get(0),inputs.get(1))){
                    Sudoku.getInstance().show();
                    System.out.println("You. put "+inputs.get(2)+" at x="+inputs.get(0)+" y="+inputs.get(1));
                    correct = false;
                }else{
                    System.out.println("Sudoku unsolvable, please restart the program");
                    System.exit(-1);
                }
            }else {
                System.out.println("Incorrect format. please try again");
            }
        }
    }

    public static void applyRules(DR1 r1,DR2 r2,DR3 r3) {
        while (true) {
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
                System.out.println("Finished! this one was of level :"+State);
                return;
            }
            State += (State<4)?1:0;
            if(State>3) {
                System.out.println("All the rules aren't enough, please choose...");
                readInput();
            }
        }
    }
}
