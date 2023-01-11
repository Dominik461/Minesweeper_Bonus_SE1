package org.example;

public class Field {
    private boolean isMine , isCovered;
    private String type;

    public Field (){
        isMine = false;
        isCovered = true;
        type = "X";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
