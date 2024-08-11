import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== Hello to RPG Battle ==========");
        System.out.println(" ");

        Scanner scanner = new Scanner(System.in);

        System.out.println("What's your name?");
        String heroName = scanner.nextLine();
        Hero hero = createHero(heroName);

        System.out.println(" ");
        ArrayList<Enemy> enemies = createEnemies();
        System.out.println("Okay! So let's start!");

        startBattle(scanner, hero, enemies);

        System.exit(0);
    }

    private static Hero createHero(String heroName){
        Hero hero = new Hero(heroName, 500);

        Scanner scanner = new Scanner(System.in);

        boolean isWeaponAnswerCorrect = false;
        while (!isWeaponAnswerCorrect) {
            System.out.println("Okay! So tell me " + heroName + ", do you battle with a Sword or an Axe? (Type 'Sword' or 'Axe')");
            String weaponName = scanner.nextLine().toLowerCase();

            Weapon heroWeapon = new Weapon();
            if(weaponName.equals("sword")){

                boolean isSwordAnswerCorrect = false;
                while (!isSwordAnswerCorrect) {
                    System.out.println("And is a Short Sword or a Two-handed Sword? (Type 'Short' or 'Two-handed')");
                    String swordType = scanner.nextLine().toLowerCase();

                    if(swordType.equals("short")){
                        heroWeapon.setWeaponType("Short Sword");
                        heroWeapon.setConditionPoints(20);
                        heroWeapon.setAttackMod(2);

                        hero.setWeapon(heroWeapon);
                        System.out.println("Okay, so with a Short Sword you better use a Shield. Take this.");

                        hero.setShield(new Shield(40, 2));
                        System.out.println("*You received a Shield.*");

                        isSwordAnswerCorrect = true;
                    }else if(swordType.equals("two-handed")){
                        heroWeapon.setWeaponType("Two-handed Sword");
                        heroWeapon.setConditionPoints(30);
                        heroWeapon.setAttackMod(1);
                        heroWeapon.setDamageMod(4);

                        hero.setWeapon(heroWeapon);

                        isSwordAnswerCorrect = true;
                    }else{
                        System.out.println("Err...Something went wrong.");
                    }
                }

                isWeaponAnswerCorrect = true;
            }else if(weaponName.equals("axe")){
                boolean isAxeAnswerCorrect = false;
                while (!isAxeAnswerCorrect) {
                    System.out.println("And is a Small Axe or a Two-handed Axe? (Type 'Small' or 'Two-handed')");
                    String axeType = scanner.nextLine().toLowerCase();

                    if(axeType.equals("small")){
                        heroWeapon.setWeaponType("Small Axe");
                        heroWeapon.setConditionPoints(20);
                        heroWeapon.setDamageMod(2);

                        hero.setWeapon(heroWeapon);
                        System.out.println("Okay, so with a Small Axe you better use a Shield. Take this.");

                        hero.setShield(new Shield(40, 2));
                        System.out.println("*You received a Shield.*");

                        isAxeAnswerCorrect = true;
                    }else if(axeType.equals("two-handed")){
                        heroWeapon.setWeaponType("Two-handed Axe");
                        heroWeapon.setConditionPoints(30);
                        heroWeapon.setDamageMod(6);

                        hero.setWeapon(heroWeapon);
                        isAxeAnswerCorrect = true;
                    }else{
                        System.out.println("Err...Something went wrong.");
                    }

                    isWeaponAnswerCorrect = true;
                }
            }else{
                System.out.println("Err...Something went wrong.");
            }
        }

        return hero;
    }

    private static ArrayList<Enemy> createEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        Enemy smallGoblin = new Enemy("Small Goblin", 50);
        smallGoblin.setWeapon(new Weapon("Short Sword", 10, 0, 0, 0));
        enemies.add(smallGoblin);

        Enemy goblin = new Enemy("Goblin", 90);
        goblin.setWeapon(new Weapon("Short Axe", 14, 0, 0, 1));
        enemies.add(goblin);

        Enemy largeOrc = new Enemy("Orc", 200);
        largeOrc.setWeapon(new Weapon("Two-handed Sword", 30, 1, 1, 4));
        enemies.add(largeOrc);

        return enemies;
    }

    private static void startBattle(Scanner scanner, Hero hero, ArrayList<Enemy> enemies) {
        boolean isFighting = true;
        while(isFighting) {
            System.out.println(" ");
            System.out.println("You are fighting against three enemies:");
            int pos = 1;
            for (int i = 0; i < 3; i++) {
                String condition = " (alive)";
                if(!enemies.get(i).isAlive){
                    condition = " (dead)";
                }
                System.out.println("[" + pos + "]" + " - " + enemies.get(i).name + condition);
                pos++;
            }
            System.out.println(" ");
            System.out.println("Who you want to attack? (Type 1, 2 or 3.)");
            int input = scanner.nextInt();

            if(input <= 0 || input > 3){
                System.out.println("You typed a wrong number. You miss your chance to attack!");
            }else{
                int enemyIndex = input -1;
                hero.attackEnemy(enemies.get(enemyIndex));
            }

            int aliveEnemies = 3;
            for (Enemy enemy : enemies){
                if(enemy.isAlive){
                    enemy.attackEnemy(hero);

                    if(!hero.isAlive) isFighting = false;
                }else{
                    aliveEnemies--;

                    if(aliveEnemies == 0) isFighting = false;
                }
            }

            System.out.println("========== End of this turn ==========");
        }

        System.out.println(" ");

        if(hero.isAlive){
            System.out.println("You WIN!");
        }else{
            System.out.println("You are dead!");
        }
    }
}