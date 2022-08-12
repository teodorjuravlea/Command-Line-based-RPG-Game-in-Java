import java.util.ArrayList;

public class Shop implements CellElement {
    private ArrayList<Potion> potionList;

    public Shop() {
        potionList = new ArrayList<Potion>();
        potionList.add(new HealthPotion(10, 30, 1));
        potionList.add(new ManaPotion(10, 30, 1));
        int i;
        for(i = 0; i < (int) (Math.random() * 2 + 2); ++i){
            if(Math.random() > 0.5){
                potionList.add(new HealthPotion(10, 30, 1));
            }
            else{
                potionList.add(new ManaPotion(10, 30, 1));
            }
        }
    }

    public Potion selectPotion(int index) {
        Potion potion = potionList.get(index);
        potionList.remove(index);
        return potion;
    }

    public void addPotion(int index, Potion potion) {
        potionList.add(index, potion);
    }

    public void listPotions() {
        int i;
        for(i = 0; i < potionList.size(); ++i) {
            if(potionList.get(i) instanceof HealthPotion) {
                System.out.println("    " + (i + 1) + ") Health Potion - " + potionList.get(i).getPrice() + " coins");
            }
            else {
                System.out.println("    " + (i + 1) + ") Mana Potion - " + potionList.get(i).getPrice() + " coins");
            }
        }
    }

    public int getPotionNumber() {
        return potionList.size();
    }

    @Override
    public char getElementType() {
        return 'S';
    }
}
