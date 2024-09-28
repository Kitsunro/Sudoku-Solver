package Rules;

import Sudoku.Sudoku;

import java.util.Set;

public class DR2 extends DeductibleRule{
    public DR2(Sudoku S){
        super(S);
    }

    //((casex*3)+(casey*27)+x1+y1*9)
    //NOPP number of possible places
    public int getNOPP(int x,int y, int who){
        int NOPP = 0;
        int casex = x/3;
        int casey = y/3;
        for(int x1=0;x1<3;x1++){
            for(int y1=0;y1<3;y1++){
                if(Sudoku.getInstance().getValue(casex*3+x1,casey*3+y1)==0){
                    Set<Integer> temp = Sudoku.getInstance().getNoPossibleNumber(casex*3+x1,casey*3+y1);
                    //System.out.println(temp + " coord  x="+(casex*3+x1)+" y="+(casey*3+y1));
                    NOPP += (temp.contains(who)?0:1);
                }
            }
        }
        return NOPP;
    }

    public void apply(Sudoku S,int x, int y,int sup) {
        if(S.isWritable(x,y) && (!S.getNoPossibleNumber(x,y).contains(sup)) && getNOPP(x,y,sup)==1){
            System.out.println("2. x="+x+" y="+y+" put "+sup);
            S.setValue(x,y,sup);
            S.modified = true;
        }
    }
}
