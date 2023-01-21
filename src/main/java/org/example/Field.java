package org.example;

public class Field {
    private FieldStatus status;
    private int bombsAround;

    //used only for testing
    public Field(FieldStatus status) {
        this.status = status;
    }

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

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    public int getBombsAround() {
        return bombsAround;
    }
}
