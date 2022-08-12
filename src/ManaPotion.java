public class ManaPotion implements Potion{
    private int price;
    private int regenValue;
    private int weight;

    public ManaPotion(int price, int regenValue, int weight){
        this.price = price;
        this.regenValue = regenValue;
        this.weight = weight;
    }

    @Override
    public char getPotionType() {
        return 'M';
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public int getRegenValue() {
        return regenValue;
    }
    @Override
    public int getWeight() {
        return weight;
    }
}
