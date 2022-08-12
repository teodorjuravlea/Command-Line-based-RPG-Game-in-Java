import java.util.ArrayList;

public class Enemy extends Entity implements CellElement{

    public Enemy() {
        spellList = new ArrayList<Spell>();
        int i;
        currHP = (int) (Math.random() * 20 + 120);
        currMana = (int) (Math.random() * 60 + 30);
        for(i = 0; i < (int) (Math.random() * 2 + 2); ++i){
            double randomNumber = Math.random();
            if(randomNumber < 0.33){
                spellList.add(new Ice());
            }
            else if(randomNumber < 0.66) {
                spellList.add(new Fire());
            }
            else {
                spellList.add(new Earth());
            }
        }
    }

    @Override
    void receiveDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < 50){
            currHP -= valueDamage / 2;
        }
        else{
            currHP -= valueDamage;
        }
    }

    @Override
    int getDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < 50){
            return 2 * (valueDamage);
        }
        else{
            return valueDamage;
        }
    }

    public char getElementType() {
        return 'E';
    }
}
