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
    int B;
    boolean GAP;
    
    public Box(){
        A = new org.edisoncor.gui.button.ButtonIcon();
        B = -1;
        GAP = true;
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
    
}
