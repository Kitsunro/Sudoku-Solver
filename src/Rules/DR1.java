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
         if(Sudoku.getInstance().isWritable(x, y)) {
             Set<Integer> temp = Sudoku.getInstance().getNoPossibleNumber(x, y);
             //System.out.println(temp);
             return !(temp.contains(sup)) && temp.size() == 8;
         }
         //System.out.println("At position x="+x+" y="+y+" already a number");
         return false;
     }

     public boolean apply(Sudoku F,int x,int y,int sup){
        if(check(x,y,sup)){
            F.setValue(x,y,sup);
            F.modified = true;
            return true;
        }
        return false;
    }

}
