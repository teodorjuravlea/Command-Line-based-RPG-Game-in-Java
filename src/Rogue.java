public class Rogue extends Character{
    public Rogue(String name, int currXP, int currLevel, String charClass) {
        super(name, currXP, currLevel, 3);
        dexterity = 5 * currLevel;
        strength = 2 * currLevel;
        charisma = currLevel;
        this.charClass = charClass;
    }

    @Override
    void receiveDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < charisma + strength){
            currHP -= valueDamage / 2;
        }
        else{
            currHP -= valueDamage;
        }
    }

    @Override
    int getDamage(int valueDamage) {
        int rollValue = (int) (Math.random() * 100);

        if(rollValue < dexterity){
            return 2 * (valueDamage + currLevel * valueDamage / 5);
        }
        else{
            return valueDamage + currLevel * valueDamage / 5;
        }
    }
}