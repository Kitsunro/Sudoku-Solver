package Sudoku;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Sudoku {
    private static Sudoku sudoku;
    private List<Integer> grid;
    private final int[] unwritable;
    public boolean modified  = false;

    private Sudoku(){
        unwritable = new int[81];
        grid = new ArrayList<>(81);
    }

    public static Sudoku getInstance(){
        if(sudoku == null){
            sudoku = new Sudoku();
        }
        return sudoku;
    }

    public void setGrid(List<Integer> ngrid){
        this.grid = ngrid;
        for(int i = 0; i < 81; i++){
            this.unwritable[i] = ngrid.get(i)==-1 ? -1 : 1;
        }
    }

    public void setValue(int x,int y,int value){
        this.grid.set(x+y*9,value);
        this.unwritable[x+y*9] = 1;
    }

    public boolean isFinished(){
        return !grid.contains(-1);
    }

    public boolean isWritable(int x,int y){
        return this.unwritable[x+y*9]==-1;
    }

    public int getValue(int x,int y){
        return grid.get(x+9*y);
    }

    public boolean possible(int x,int y){
        Set<Integer> temp = new HashSet<>();
        int sup = getValue(x,y);
        for(int k = 0; k < 3; k++){
            for(int i = 0; i < 9; i++){
                switch (k){
                    case 0:
                        temp.add((i!=x)?getValue(i,y):0);
                        break;
                    case 1:
                        temp.add(i!=y?getValue(x,i):0);
                        break;
                    case 2:
                        temp.add(i!=x?getValue((x/3)*3+i%3,(y/3)*3+i/3):0);
                        break;
                }
            }
        }
        //System.out.println(temp);
        return !temp.contains(sup);
    }

    public Set<Integer> getNoPossibleNumber(int x, int y){
        Set<Integer> noPossibleNumber = new HashSet<>(Sudoku.getInstance().getColumnNumber(x));
        noPossibleNumber.addAll(Sudoku.getInstance().getRowNumber(y));
        noPossibleNumber.addAll(Sudoku.getInstance().getSquareNumber(x/3,y/3));
        return noPossibleNumber;
    }

    public Set<Integer> getSquareNumber(int x,int y){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3; j++){
                set.add(grid.get((x*3)+(y*27)+j+i*9));
            }
        }
        set.remove(-1);
        return set;
    }

    public Set<Integer> getRowNumber(int r){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 9; i++){
            set.add(grid.get(i+r*9));
        }
        set.remove(-1);
        return set;
    }

    public Set<Integer> getColumnNumber(int c){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 9; i++){
            set.add(grid.get(c+i*9));
        }
        set.remove(-1);
        return set;
    }

    public int Minus(int x){
        return x==-1?0:x;
    }

    public void show(){
        if(grid.size()!=81){
            System.out.println(grid.size());
            System.out.println(grid);
            return;
        }
        System.out.print("   ");
        for(int i = 0; i < 9; i++){
            System.out.print(i+"   ");
        }
        System.out.println("X");
        System.out.println("  ___________________________________");
        for(int i = 0; i < 81; i++) {
            if(i%9==0 && i!=0){
                if(i%27==0){
                    System.out.print("\n*| -   -   - + -   -   - + -   -   -");
                }
                System.out.print("\n"+(i/9)+"| "+Minus(grid.get(i))+ "   ");
            } else{
                if((i+1)%3==0 && !((i+1)%9==0)){
                    System.out.print(Minus(grid.get(i)) + " | ");
                }else {
                    if(i==0){
                        System.out.print("0| "+Minus(grid.get(i))+"   ");
                    }else {
                        System.out.print(Minus(grid.get(i)) + "   ");
                    }
                }
            }
        }
        System.out.println("\nY");
    }
}
