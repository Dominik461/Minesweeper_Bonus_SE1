package org.example;

public class Field {
    private FieldStatus status;
    private int bombsAround;
    /**
     * Sets the values for each variable when creating an object
     */
    public Field (){
        status = FieldStatus.COVERED;
        bombsAround = 0;
    }

    public enum FieldStatus {
        FLAG("F"),
        COVERED("X"),
        UNCOVERED(" "),
        BOMB("X");

        private final String status;

        FieldStatus(final String status) {
            this.status = status;
        }

        public String toString() { return status;}
    }

    public FieldStatus getStatus() {
        return status;
    }

    public void setStatus(FieldStatus status) {
        this.status = status;
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
}
