public class HealthPotion implements Potion{
    private int price;
    private int regenValue;
    private int weight;

    public HealthPotion(int price, int regenValue, int weight){
        this.price = price;
        this.regenValue = regenValue;
        this.weight = weight;
    }

    @Override
    public char getPotionType() {
        return 'H';
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
