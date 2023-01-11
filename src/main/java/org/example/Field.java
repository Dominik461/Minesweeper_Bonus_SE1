package org.example;

public class Field {
    private boolean isBomb, isCovered, isFlag;
    private String type;

    public Field (){
        isBomb = false;
        isCovered = true;
        isFlag = false;
        type = "X";
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
    }
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean getIsCovered() {
        return isCovered;
    }

    public boolean getIsMine() {
        return isBomb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
