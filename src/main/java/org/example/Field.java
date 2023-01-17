package org.example;

public class Field {
    private boolean isBomb, isCovered, isFlag;
    private String type;

    /**
     * Sets the values for each variable when creating an object
     */
    public Field (){
        isBomb = false;
        isCovered = true;
        isFlag = false;
        type = "X";
    }

    /**
     * Sets the boolean value of the Field object
     * @param covered will be True if it is covered. Otherwise, it will be false
     */
    public void setCovered(boolean covered) {
        isCovered = covered;
    }
    /**
     * Sets the boolean value of the Field object
     * @param bomb will be True if it is a bomb. Otherwise, it will be false
     */
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }
    /**
     * Sets the boolean value of the Field object
     * @return will be True if it is covered. Otherwise, it will be False
     */
    public boolean getIsCovered() {
        return isCovered;
    }
    /**
     * Sets the boolean value of the Field object
     * @return will be True if it is a bomb. Otherwise, it will be False
     */
    public boolean getIsBomb() {
        return isBomb;
    }
    /**
     * Gets the String value of the Field object to be represented in the game board
     * @return the String value of the Field object
     */
    public String getType() {
        return type;
    }
    /**
     * Sets the String value of the Field object to be represented in the game board
     * @param type will on of these options: X: covered, space(" ") = uncovered, Q = bomb
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Gets the boolean value of the Field object
     * @return will be True if it is a flag. Otherwise, it will be False
     */
    public boolean getIsFlag() {
        return isFlag;
    }
    /**
     * Sets the boolean value of the Field object
     * @param flag will be True if it is a flag. Otherwise, it will be False
     */
    public void setFlag(boolean flag) {
        isFlag = flag;
    }
}
