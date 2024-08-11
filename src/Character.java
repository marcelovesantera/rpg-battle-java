public class Character {
    String name;
    int lifePoints;
    Weapon weapon;
    Shield shield;
    boolean isAlive;

    public Character(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.isAlive = true;
    }

    public Weapon setWeapon(Weapon weapon){
        return this.weapon = weapon;
    }

    public Shield setShield(Shield shield){
        return this.shield = shield;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public int updateLifePoints(int damage){
        return lifePoints -= damage;
    }

    public boolean checkIfAlive(int lifePoints){
        if(lifePoints <= 0){
            isAlive = false;
        }

        return isAlive;
    }

    public void attackEnemy(Character target){
        if(target.isAlive){
            int attack = Utils.rollDice(10) + this.weapon.attackMod;
            int defense = Utils.rollDice(10) + target.weapon.defenseMod;

            if(attack > defense){
                int damage = Utils.calculateDamage(attack, defense, this.weapon.damageMod);

                int enemyLifePoints = target.updateLifePoints(damage);
                boolean isStillAlive = target.checkIfAlive(enemyLifePoints);

                if(!isStillAlive){
                    target.setIsAlive(false);
                    System.out.println(this.name + " hits " + target.name + "! " + target.name + " is dead!");
                }else{
                    System.out.println(this.name + " hits " + target.name + "! " + target.name + " still have " + target.lifePoints + " life points!");
                }
            }else{
                System.out.println(this.name + " misses attack!");
            }
        }else{
            System.out.println(target.name + " is already dead!");
        }
    }
}