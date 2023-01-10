package org.example;

public class Field {
    boolean isMine, isCovered = true;

    public void setCovered(boolean covered) {
        isCovered = covered;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public boolean isMine() {
        return isMine;
    }

}
