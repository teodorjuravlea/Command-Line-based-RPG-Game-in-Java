public class CharacterFactory {
    public static Character factory(String charClass, String name, int currXP, int currLevel) {
        if(charClass.equals("Warrior")) {
            return new Warrior(name, currXP, currLevel, charClass);
        }
        if(charClass.equals("Mage")) {
            return new Mage(name, currXP, currLevel, charClass);
        }
        if(charClass.equals("Rogue")) {
            return new Rogue(name, currXP, currLevel, charClass);
        }
        return null;
    }
}
