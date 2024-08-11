public class Weapon {
    String weaponType;
    int conditionPoints;
    int attackMod;
    int defenseMod;
    int damageMod;

    public Weapon() {}

    public Weapon(String weaponType, int conditionPoints, int attackMod, int defenseMod, int damageMod) {
        this.weaponType = weaponType;
        this.conditionPoints = conditionPoints;
        this.attackMod = attackMod;
        this.defenseMod = defenseMod;
        this.damageMod = damageMod;
    }

    public String setWeaponType(String weaponType) {
        return this.weaponType = weaponType;
    }

    public int setConditionPoints(int conditionPoints) {
        return this.conditionPoints = conditionPoints;
    }

    public int setAttackMod(int attackMod) {
        return this.attackMod = attackMod;
    }

    public int setDefenseMod(int defenseMod) {
        return this.defenseMod = defenseMod;
    }

    public int setDamageMod(int damageMod) {
        return this.damageMod = damageMod;
    }
}