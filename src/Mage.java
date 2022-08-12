public class Mage extends Character{
    public Mage(String name, int currXP, int currLevel, String charClass) {
        super(name, currXP, currLevel, 2);
        charisma = 5 * currLevel;
        dexterity = 2 * currLevel;
        strength = currLevel;
        this.charClass = charClass;
    }

    @Override
    void receiveDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < strength + dexterity){
            currHP -= valueDamage / 2;
        }
        else{
            currHP -= valueDamage;
        }
    }

    @Override
    int getDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < charisma){
            return 2 * (valueDamage + currLevel * valueDamage / 5);
        }
        else{
            return valueDamage + currLevel * valueDamage / 5;
        }
    }
}