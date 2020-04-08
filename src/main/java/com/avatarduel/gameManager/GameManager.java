// GameManager.java
package com.avatarduel.gameManager;

import com.avatarduel.player.*;
import com.avatarduel.sprite.*;
import javafx.scene.canvas.GraphicsContext;
import com.avatarduel.card.Card;
import com.avatarduel.gameState.*;
import java.util.List;
import javafx.scene.input.MouseEvent;

public class GameManager {
    private GameState gameState;
    private Player currentPlayer;
    private Player oppositePlayer;
    private List<IMouseClickSub> mouseClickSubs;
    private List<IMouseMoveSub> mouseMoveSubs;
    private GameDrawer gameDrawer;
    private GraphicsContext graphicsContext;

    public GameManager(GraphicsContext gc){
        // Init gameDrawer
        graphicsContext = gc;
        gameDrawer = new GameDrawer();
        
        // Init CurrentPlayer dan oppositePlayer
        currentPlayer = new Player(false);
        oppositePlayer = new Player(true);

        // Generate kartu dan masukin ke player yang bersangkutan, dan simpan ke drawListnya GameDrawer
        // Init exit button dan masukin spritenya ke drawlistnya gamedrawer
        // Masukin 7 kartu ke tangan kedua player
        // Init state dengan draw phase
    }

    public void gameLoop(double durationSinceLastFrame){
        // Ini dipanggil oleh Avatar Duel pada Animation Timer pada handle()
        // Reference: (1) - Cari bagian animation (bisa dipake buat yang bukan animasi juga)
        this.gameDrawer.drawGame(this, durationSinceLastFrame);
    }


    public void sendMouseClickEvent(MouseEvent event){
        // Kirim event OnMouseClick ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse click
        for (int i = 0; i < mouseClickSubs.size(); i++){
            mouseClickSubs.get(i).OnMouseClick(event);
        }
    }

    public void sendMouseMoveEvent(MouseEvent event){
        // Kirim event OnMouseMove ke subs dari MouseClickSubs saat klik
        // Ini dipanggil oleh Avatar Duel pada setOnMouseClicked pada handle()
        // Reference: (1) - Cari bagian mouse move
        for (int i = 0; i < mouseMoveSubs.size(); i++){
            mouseMoveSubs.get(i).OnMouseMove(event);
        }
    }

    public Card getClickedCardFromHand(int X, int Y){
        // Cek kartu apa yang di klik dari tangan player saat itu
        // Return null kalo gak ada kartu yang di klik
        // Caranya loop untuk setiap kartu di hand kalo Sprite.OverlapPoint
        for (Card kartu : this.currentPlayer.getPlayerHands()){
            if (kartu.getSprite().isPointOverlap(X, Y)){
                return kartu;
            }
        }
        return null;
    }

    public ArenaClickInfo getArenaClickInfo(int X, int Y){
        // Cek posisi klik dari mouse, apakah ada di belah atas atau bawah, dan ada di kolom ke berapa
        // Cek apakah ada kartu di posisi tersebut
        // Return ArenaClickInfo
        for (int i = 0; i < 8; i++){
            Card kartu = this.currentPlayer.getPlayerArena().getCharCard()[i];
            if (kartu != null){
                if (kartu.getSprite().isPointOverlap(X, Y)){
                    return new ArenaClickInfo(kartu, true, this.currentPlayer.getIsTopPlayer(), i, true, false);
                }
            }
        }
        for (int i = 0; i < 8; i++){
            Card kartu = this.currentPlayer.getPlayerArena().getSkills()[i];
            if (kartu != null){
                if (kartu.getSprite().isPointOverlap(X, Y)){
                    return new ArenaClickInfo(kartu, false, this.currentPlayer.getIsTopPlayer(), i, false, true);
                }
            }
        }
        return null;
    }

    // Setter
    public void setGameState(GameState gs){
        this.gameState = gs;
    }

    public void switchPlayer(){
        Player temp = this.currentPlayer;
        this.currentPlayer = this.oppositePlayer;
        this.oppositePlayer = temp;
    }

    public void setCurrentPlayer(Player P){
        this.currentPlayer = P;
    }

    public void setOppositePlayer(Player P){
        this.oppositePlayer = P;
    }

    public void setGameDrawer(GameDrawer gd){
        this.gameDrawer = gd;
    }

    public void setMouseClickSubs(List<IMouseClickSub> mcSubs){
        this.mouseClickSubs = mcSubs;
    }

    public void setMouseMoveSubs(List<IMouseMoveSub> mmSubs){
        this.mouseMoveSubs = mmSubs;
    }

    public void setGraphicsContext(GraphicsContext gc){
        this.graphicsContext = gc;
    }

    // Mendaftarkan IMouseClickSub agar dikirim event nanti
    public void RegisterMouseClick(IMouseClickSub click){
        mouseClickSubs.add(click);
    }

    // Mendaftarkan IMouseClickSub agar dikirim event nanti
    public void RegisterMouseMove(IMouseMoveSub move){
        mouseMoveSubs.add(move);
    }
    
    // Getter
    public GameState getGameState(){
        return this.gameState;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public Player getOppositePlayer(){
        return this.oppositePlayer;
    }

    public List<IMouseClickSub> getMouseClickSubs(){
        return this.mouseClickSubs;
    }

    public List<IMouseMoveSub> getMouseMoveSubs(){
        return this.mouseMoveSubs;
    }

    public GameDrawer getGameDrawer(){
        return this.gameDrawer;
    }

    public GraphicsContext getGraphicsContext(){
        return this.graphicsContext;
    }

}