/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoeAI;

/**
 *
 * @author fabian
 */
public class Box {
    org.edisoncor.gui.button.ButtonIcon A;
    int index;
    int B = 0;
    boolean GAP = true;
    
    public Box(){
        this.A = new org.edisoncor.gui.button.ButtonIcon();
        
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    public boolean isGAP() {
        return GAP;
    }

    public void setGAP(boolean GAP) {
        this.GAP = GAP;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    public void setIndex(int index){
        this.index = index;
    }
}
