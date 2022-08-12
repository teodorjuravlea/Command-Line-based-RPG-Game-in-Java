public class Cell {
    int x, y;
    enum cellType{
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }
    cellType enumType;
    CellElement element;
    boolean visited;

    public Cell(char type, int x, int y) {
        visited = false;
        this.x = x;
        this.y = y;
        if (type == 'N') {
            element = new Empty();
            enumType = cellType.EMPTY;
        }
        if (type == 'E') {
            element = new Enemy();
            enumType = cellType.ENEMY;
        }
        if(type == 'S') {
            element = new Shop();
            enumType = cellType.SHOP;
        }
        if(type == 'F') {
            element = new Finish();
            enumType = cellType.FINISH;
        }
    }
}
