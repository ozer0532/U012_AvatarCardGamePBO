// PowerUp.java

package com.avatarduel.card;
import com.avatarduel.sprite.CardSprite;
import com.avatarduel.gameManager.*;
import com.avatarduel.player.*;
import com.avatarduel.model.*;

public class PowerUp extends Card {
    // CONSTRUCTOR
    public PowerUp(String name, Element elmt, String desc, CardSprite sprite, int pn){
        super(name, elmt, desc, sprite, pn);
    }

    public void OnCardPlayed(GameManager gm, int idx){
        PlayerArena temp=gm.getCurrentPlayer().getPlayerArena();
        temp.addSkillCard(idx,this);
        gm.getCurrentPlayer().setPlayerArena(temp);
    }

    public boolean CanBePlayed(PlayerStats ps){
        // return true kalo power mencukupi
        if (ps.getRemainingPower(this.Element) >= this.powerNeeded){
            //ps.usePower(super.getElmt(), 1); why?
            return true;
        }
        else {
            return false;
        }
    }
}