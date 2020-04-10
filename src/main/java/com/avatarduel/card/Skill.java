// Aura.java

package com.avatarduel.card;

import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public abstract class Skill extends Card {
    // Constructor
    public Skill(String name, Element elmt, String desc, CardSprite sprite, int pn){
        super(name, elmt, desc, sprite, pn);
    }
    // Abstract Methods
    public abstract void OnCardPlayed(GameManager gm, int idx, boolean isPlayedonEnemy);
    public abstract boolean CanBePlayed(PlayerStats ps);
}