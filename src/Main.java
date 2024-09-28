import Rules.DR1;
import Rules.DR2;
import Rules.DR3;
import Sudoku.Sudoku;

import javax.tools.Tool;
import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Sudoku.getInstance().setGrid(ToolBox.fileToIntList("hard.txt"));
        System.out.println("Here's the sudoku, let's solve it :");
        Sudoku.getInstance().show();
        DR1 rule1 = new DR1(Sudoku.getInstance());
        DR2 rule2 = new DR2(Sudoku.getInstance());
        DR3 rule3 = new DR3(Sudoku.getInstance());
        ToolBox.applyRules(rule1, rule2, rule3);
        System.out.println(rule3.otherPosibility(8, 8, 5));
    }
}