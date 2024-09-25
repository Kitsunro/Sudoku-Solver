package Rules;

import Sudoku.Sudoku;

import java.util.HashSet;
import java.util.Set;

//Check row, col, square for same number

public class DR1 extends DeductibleRule{
     public DR1(Sudoku S){
         super(S);
     }

     public boolean check(int x,int y,int sup){
         if(Sudoku.getInstance().getValue(x,y)==0) {
             Set<Integer> temp = new HashSet<Integer>(getTempSudoku().getColumnNumber(x));
             temp.addAll(getTempSudoku().getRowNumber(y));
             temp.addAll(getTempSudoku().getSquareNumber(x / 3, y / 3));
             System.out.println(temp);
             return !(temp.contains(sup)) && temp.size() == 8;
         }
         System.out.println("At position x="+x+" y="+y+" already a number");
         return false;
     }
}
