public abstract class Spell {
    private int spellDamage;
    private int spellMana;

    public int getSpellDamage() {
        return spellDamage;
    }
    public int getSpellMana() {
        return spellMana;
    }

    protected void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }
    protected void setSpellMana(int spellMana) {
        this.spellMana = spellMana;
    }
}
