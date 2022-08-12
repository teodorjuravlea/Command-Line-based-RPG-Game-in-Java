import java.util.ArrayList;

public abstract class Character extends Entity {
    String name, charClass;
    int xCoord, yCoord;
    Inventory charInventory;
    int currXP, currLevel;
    int strength, dexterity, charisma;

    public Character(String name, int currXP, int currLevel, int maxWeight) {
        this.name = name;
        this.xCoord = 0;
        this.yCoord = 0;
        this.currXP = currXP;
        this.currLevel = currLevel;
        charInventory = new Inventory(20, maxWeight);

        spellList = new ArrayList<Spell>();
        int i;
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

    public void buyPotion(int index, Shop currShop) {
        Potion potion = currShop.selectPotion(index);

        if(this.charInventory.unusedWeight() < potion.getWeight()){
            System.out.println();
            System.out.println("Can't carry any more!");
            currShop.addPotion(index, potion);
            return;
        }
        if(this.charInventory.coins < potion.getPrice()){
            System.out.println();
            System.out.println("Not enough money!");
            currShop.addPotion(index, potion);
            return;
        }

        this.charInventory.coins -= potion.getPrice();
        this.charInventory.addPotion(potion);
    }

    public void usePotion(int index) {
        Potion potion = charInventory.potionList.get(index);

        if(potion instanceof HealthPotion) {
            regenHP(potion.getRegenValue());
        }
        if(potion instanceof ManaPotion) {
            regenMana(potion.getRegenValue());
        }

        charInventory.removePotion(potion);
    }
}
