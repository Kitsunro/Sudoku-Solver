import Rules.DR1;
import Sudoku.Sudoku;

import javax.tools.Tool;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Sudoku.getInstance().setGrid(ToolBox.fileToIntList("test.txt"));
        Sudoku.getInstance().show();
        DR1 rule1 = new DR1(Sudoku.getInstance());
        System.out.println(rule1.check(3,5,6));
    }
}