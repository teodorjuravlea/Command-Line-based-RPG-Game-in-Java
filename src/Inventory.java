import java.util.ArrayList;

public class Inventory {
    ArrayList<Potion> potionList;
    private int maxWeight;
    int coins;

    public Inventory(int coins, int maxWeight) {
        potionList = new ArrayList<Potion>();
        this.coins = coins;
        this.maxWeight = maxWeight;
    }

    void addPotion(Potion potion) {
        potionList.add(potion);
    }
    void removePotion(Potion potion) {
        potionList.remove(potion);
    }
    int unusedWeight() {
        int weight = 0;
        for(int i = 0; i < potionList.size(); ++i){
            weight += potionList.get(i).getWeight();
        }
        weight = maxWeight - weight;
        return weight;
    }
}
