import Rules.DR1;
import Rules.DR2;
import Rules.DR3;
import Sudoku.Sudoku;

import javax.tools.Tool;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Sudoku.getInstance().setGrid(ToolBox.fileToIntList("medium.txt"));
        System.out.println("Here's the sudoku, let's solve it :");
        Sudoku.getInstance().show();
        DR1 rule1 = new DR1(Sudoku.getInstance());
        DR2 rule2 = new DR2(Sudoku.getInstance());
        DR3 rule3 = new DR3(Sudoku.getInstance());
        //ToolBox.applyRules(rule1,rule2,rule3);
        System.out.println(rule2.getNOPP(6,7,8));
    }
}