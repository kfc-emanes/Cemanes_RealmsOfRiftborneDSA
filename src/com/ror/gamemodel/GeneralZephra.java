package com.ror.gamemodel;

public class GeneralZephra extends Entity {
    public GeneralZephra() {
        super("General Zephra", 180, 180, 42, 10); // storm magic, rides a thunderbird
    }

    @Override
    public void attack(Entity target) {
        int dmg = 28 + (int)(Math.random() * 8);  
        // 28-42 inclusive

        System.out.println(getName() + " casts a spell on you for " + dmg + " damage!");
        target.takeDamage(dmg);
    }
}
