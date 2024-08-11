public class Utils {
    public static int rollDice(int sidesNum){
        return (int) (Math.random() * sidesNum) + 1;
    }

    public static int calculateDamage(int attack, int defense, int mod){
        int damageBase = rollDice(10);
        return (damageBase * (attack - defense)) + mod;
    }
}
