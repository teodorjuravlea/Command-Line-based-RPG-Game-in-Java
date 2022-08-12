import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    int tableHeight, tableWidth;
    Character currentChar;
    int currentX, currentY;
    private static Grid gameGrid;

    private Grid(int tableHeight, int tableWidth, Character currentChar) {
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.currentChar = currentChar;
    }

    public static Grid getHardcodedMap(Character currentChar) {
        if(gameGrid != null){
            return gameGrid;
        }
        gameGrid = new Grid(5, 5, currentChar);
        int i;
        for(i = 0; i < 5; ++i){
            gameGrid.add(new ArrayList<>());
        }
        gameGrid.get(0).add(new Cell('N', 0, 0));
        gameGrid.get(0).add(new Cell('N', 0, 1));
        gameGrid.get(0).add(new Cell('N', 0, 2));
        gameGrid.get(0).add(new Cell('S', 0, 3));
        gameGrid.get(0).add(new Cell('N', 0, 4));

        gameGrid.get(1).add(new Cell('N', 0, 0));
        gameGrid.get(1).add(new Cell('N', 0, 1));
        gameGrid.get(1).add(new Cell('N', 0, 2));
        gameGrid.get(1).add(new Cell('S', 0, 3));
        gameGrid.get(1).add(new Cell('N', 0, 4));

        gameGrid.get(2).add(new Cell('S', 0, 0));
        gameGrid.get(2).add(new Cell('N', 0, 1));
        gameGrid.get(2).add(new Cell('N', 0, 2));
        gameGrid.get(2).add(new Cell('N', 0, 3));
        gameGrid.get(2).add(new Cell('N', 0, 4));

        gameGrid.get(3).add(new Cell('N', 0, 0));
        gameGrid.get(3).add(new Cell('N', 0, 1));
        gameGrid.get(3).add(new Cell('N', 0, 2));
        gameGrid.get(3).add(new Cell('N', 0, 3));
        gameGrid.get(3).add(new Cell('E', 0, 4));

        gameGrid.get(4).add(new Cell('N', 0, 0));
        gameGrid.get(4).add(new Cell('N', 0, 1));
        gameGrid.get(4).add(new Cell('N', 0, 2));
        gameGrid.get(4).add(new Cell('S', 0, 3));
        gameGrid.get(4).add(new Cell('F', 0, 4));

        gameGrid.currentX = 0;
        gameGrid.currentY = 0;
        gameGrid.get(0).get(0).visited = true;

        return gameGrid;
    }

    void goWest(){
        if(currentY == 0){
            System.out.println("Can't go there!");
            return;
        }
        --currentY;
        --currentChar.yCoord;
        gameGrid.get(currentX).get(currentY).visited = true;
    }
    void goEast(){
        if(currentY == tableHeight - 1){
            System.out.println("Can't go there!");
            return;
        }
        ++currentY;
        ++currentChar.yCoord;
        gameGrid.get(currentX).get(currentY).visited = true;
    }
    void goSouth(){
        if(currentX == tableWidth - 1){
            System.out.println("Can't go there!");
            return;
        }
        ++currentX;
        ++currentChar.xCoord;
        gameGrid.get(currentX).get(currentY).visited = true;
    }
    void goNorth(){
        if(currentX == 0){
            System.out.println("Can't go there!");
            return;
        }
        --currentX;
        --currentChar.xCoord;
        gameGrid.get(currentX).get(currentY).visited = true;
    }
    void displayMap(boolean revealed) {
        int i, j;
        for(i = 0; i < this.tableHeight; ++i) {
            for(j = 0; j < this.tableWidth; ++j) {
                if(i == currentX && j == currentY) {
                    System.out.print("P" + " ");
                }
                else if(revealed || this.get(i).get(j).visited) {
                    System.out.print(this.get(i).get(j).element.getElementType() + " ");
                }
                else {
                    System.out.print("?" + " ");
                }
            }
            System.out.println();
        }
    }
}
