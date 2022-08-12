import java.util.ArrayList;

public abstract class Entity {
    ArrayList<Spell> spellList;
    int currHP, maxHP;
    int currMana, maxMana;
    boolean fireResistance, iceResistance, earthResistance;

    void regenHP(int valueHP) {
        currHP += valueHP;
        if(currHP > maxHP) {
            currHP = maxHP;
        }
    }
    void regenMana(int valueMana) {
        currMana += valueMana;
        if(currMana > maxMana) {
            currMana = maxMana;
        }
    }
    void useSpell(int index, Entity target) {
        Spell usedSpell = spellList.get(index);
        if(currMana < usedSpell.getSpellMana()) {
            return;
        }
        currMana -= usedSpell.getSpellMana();
        if((usedSpell instanceof Ice && target.iceResistance)
                || (usedSpell instanceof Fire && target.fireResistance)
                || (usedSpell instanceof Earth && target.earthResistance)){
            System.out.println("Enemy is immune!");
            return;
        }
        target.receiveDamage(this.getDamage(usedSpell.getSpellDamage()));
    }

    abstract void receiveDamage(int valueDamage);
    abstract int getDamage(int valueDamage);
}
