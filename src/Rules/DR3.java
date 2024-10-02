package Rules;

import Sudoku.Sudoku;

public class DR3 extends DeductibleRule{
    public DR3(Sudoku S){
        super(S);
    }

    public boolean otherPosibility(int x, int y, int sup){
        int noofplace = 0;
        for(int i = 0; i<9 ; i++){
            if(!(Sudoku.getInstance().getValue(x,i)==0 && Sudoku.getInstance().getNoPossibleNumber(x,i).contains(sup))){
                noofplace++;
            }
        }
        if(noofplace==1){
            return true;
        }
        noofplace = 0;
        for(int i = 0; i<9 ; i++){
            if(Sudoku.getInstance().getValue(i,y)==0 && !Sudoku.getInstance().getNoPossibleNumber(i,y).contains(sup)){
                noofplace++;
            }
        }
        return noofplace == 1;
    }

    public void apply(Sudoku S, int x, int y,int sup) {
        if(S.isWritable(x,y) && (!S.getNoPossibleNumber(x,y).contains(sup)) && otherPosibility(x,y,sup)){
            System.out.println("3. x="+x+" y="+y+" put "+sup);
            S.setValue(x,y,sup);
            S.modified = true;
        }
    }
}
