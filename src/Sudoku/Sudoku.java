package Sudoku;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {
    private static Sudoku sudoku;
    private List<Integer> grid;
    private List<Integer> unwritable;

    private Sudoku(){
        unwritable = new ArrayList<Integer>(81);
        grid = new ArrayList<Integer>(81);
    }

    public static Sudoku getInstance(){
        if(sudoku == null){
            sudoku = new Sudoku();
        }
        return sudoku;
    }

    public List<Integer> getGrid(){
        return grid;
    }

    public void setGrid(List<Integer> ngrid){
        this.grid = ngrid;
        this.unwritable = ngrid.stream().map(x-> x!=-1 ? 1:0).toList();
    }

    public void setValue(int x,int y,int value){

        grid.set(x+y*9,value);
    }

    public int getValue(int x,int y){
        return grid.get(x+9*y);
    }

    public Set<Integer> getSquareNumber(int x,int y){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3; j++){
                set.add(grid.get((x*3)+(y*27)+j+i*9));
            }
        }
        set.remove(0);
        return set;
    }

    public Set<Integer> getRowNumber(int r){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 9; i++){
            set.add(grid.get(i+r*9));
        }
        set.remove(0);
        return set;
    }

    public Set<Integer> getColumnNumber(int c){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 9; i++){
            set.add(grid.get(c+i*9));
        }
        set.remove(0);
        return set;
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
        for(int i = 0; i < 81; i++) {
            if(i%9==0 && i!=0){
                if(i%27==0){
                    System.out.print("\n*  -   -   - + -   -   - + -   -   -");
                }
                System.out.print("\n"+(i/9)+"  "+grid.get(i)+ "   ");
            } else{
                if((i+1)%3==0 && !((i+1)%9==0)){
                    System.out.print(grid.get(i) + " | ");
                }else {
                    if(i==0){
                        System.out.print("0  "+grid.get(i)+"   ");
                    }else {
                        System.out.print(grid.get(i) + "   ");
                    }
                }
            }
        }
        System.out.println("\nY");
    }
}
