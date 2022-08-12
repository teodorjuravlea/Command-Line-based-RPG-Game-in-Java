public class Warrior extends Character{
    public Warrior(String name, int currXP, int currLevel, String charClass) {
        super(name, currXP, currLevel, 4);
        strength = 5 * currLevel;
        charisma = 2 * currLevel;
        dexterity = currLevel;
        this.charClass = charClass;
    }
    @Override
    void receiveDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < charisma + dexterity){
            currHP -= valueDamage / 2;
        }
        else{
            currHP -= valueDamage;
        }
    }

    @Override
    int getDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < strength){
            return 2 * (valueDamage + currLevel * valueDamage / 5);
        }
        else{
            return valueDamage + currLevel * valueDamage / 5;
        }
    }
}