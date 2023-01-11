package org.example;

public class Field {
    private boolean isMine , isCovered;

    public Field (){
        isMine = false;
        isCovered = true;
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean getIsCovered() {
        return isCovered;
    }

    public boolean getIsMine() {
        return isMine;
    }

}
