package org.example;

public class Field {
    public FieldStatus getStatus() {
        return status;
    }

    public void setStatus(FieldStatus status) {
        this.status = status;
    }

    /*private boolean isBomb, isCovered, isFlag;
        private String type;*/
    private FieldStatus status;
    private int bombsAround;
    /**
     * Sets the values for each variable when creating an object
     */
    public Field (){
        /*isBomb = false;
        isCovered = true;
        isFlag = false;
        type = "X";*/
        status = FieldStatus.COVERED;
        bombsAround = 0;
    }

    public enum FieldStatus {
        FLAG("F"),
        COVERED("X"),
        UNCOVERED(" "),
        BOMB("X");

        private String status;

        FieldStatus(final String status) {
            this.status = status;
        }

        public String toString() { return status;}
    }

    /**
     * Sets the boolean value of the Field object
     * @param covered will be True if it is covered. Otherwise, it will be false
     */
    public void setCovered(boolean covered) {
        status = FieldStatus.COVERED;
        if(!covered) setStatus(FieldStatus.UNCOVERED);
    }
    /**
     * Sets the boolean value of the Field object
     * @param bomb will be True if it is a bomb. Otherwise, it will be false
     */
    public void setBomb(boolean bomb) {
        if(bomb) status = FieldStatus.BOMB;
        else status = FieldStatus.COVERED;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    /**
     * Sets the boolean value of the Field object
     * @return will be True if it is covered. Otherwise, it will be False
     */
    /*public boolean getIsCovered() {
        return isCovered;
    }*/
    /**
     * Sets the boolean value of the Field object
     * @return will be True if it is a bomb. Otherwise, it will be False
     */
    /*public boolean getIsBomb() {
        return isBomb;
    }*/
    /**
     * Gets the String value of the Field object to be represented in the game board
     * @return the String value of the Field object
     */
    /*public String getType() {
        return type;
    }*/
    /**
     * Sets the String value of the Field object to be represented in the game board
     * @param type will on of these options: X: covered, space(" ") = uncovered, Q = bomb
     */
    /*public void setType(String type) {
        this.type = type;
    }*/
    /**
     * Gets the boolean value of the Field object
     * @return will be True if it is a flag. Otherwise, it will be False
     */
    /*public boolean getIsFlag() {
        return isFlag;
    }*/
    /**
     * Sets the boolean value of the Field object
     * @param flag will be True if it is a flag. Otherwise, it will be False
     */
    /*public void setFlag(boolean flag) {
        isFlag = flag;
    }*/
}
