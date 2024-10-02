import Rules.DR1;
import Rules.DR2;
import Rules.DR3;
import Sudoku.Sudoku;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Main <sudoku.txt>");
        }
        Sudoku.getInstance().setGrid(ToolBox.fileToIntList(args[0]));
        System.out.println("Here's the sudoku, let's solve it :");
        Sudoku.getInstance().show();
        DR1 rule1 = new DR1(Sudoku.getInstance());
        DR2 rule2 = new DR2(Sudoku.getInstance());
        DR3 rule3 = new DR3(Sudoku.getInstance());
        ToolBox.applyRules(rule1, rule2, rule3);
    }
}