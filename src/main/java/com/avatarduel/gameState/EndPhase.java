package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.GameManager;

public class EndPhase extends GameState{
    public EndPhase(GameManager gameManager){
        super(gameManager);
    }

    public void StartTurn(){
        // Tuker current player dan opposite player
        GameManager gm = super.getGameManager();
        gm.switchPlayer();
        super.setGameManager(gm);

        // Panggil endturn
        this.EndTurn();
    }

    public void EndTurn(){
        // Pindah ke draw phase
        GameManager gm = super.getGameManager();
        super.getGameManager().setGameState(new DrawPhase(gm));
    }
}