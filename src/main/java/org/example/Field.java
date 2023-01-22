package org.example;

/**
 * Is used to create the game board and for logic calls in the game manager to check for a number of things
 */
public class Field {
    /**
     * object from FieldStatus to know in which state the current field is
     */
    private FieldStatus status;
    /**
     * number of bombs that are around the field
     */
    private int bombsAround;

    //used only for testing
    public Field(FieldStatus status) {
        this.status = status;
    }

    /**
     * Sets the standard values for each variable when creating an object
     */
    public Field (){
        status = FieldStatus.COVERED;
        bombsAround = 0;
    }

    /**
     * The only valid states a field object can be
     */
    public enum FieldStatus {
        /**
         * Used when a flag is placed on a field
         */
        FLAG("F"),
        /**
         * Used when the field is still not uncovered
         */
        COVERED("X"),
        /**
         * Used when the field was uncovered
         */
        UNCOVERED(" "),
        /**
         * Used when a field is a bomb
         */
        BOMB("X");
        /**
         * String of the set status
         */
        private final String status;

        /**
         * constructor used when a new instance of FieldStatus is created that sets the status
         * @param status The status that will be set
         */
        FieldStatus(final String status) {
            this.status = status;
        }

        /**
         * Returns the status as a String
         * @return status as String
         */
        public String toString() { return status;}
    }

    /**
     * Gets the current status from the field object
     * @return current status
     */
    public FieldStatus getStatus() {
        return status;
    }
    /**
     * Sets the  status of the field object
     * @param status value from the enum FieldStatus
     */
    public void setStatus(FieldStatus status) {
        this.status = status;
    }
    /**
     * Sets bombsAround to the number that the parameter has
     * @param bombsAround number of bombs around the field
     */
    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    /**
     * Gets the int value of how many bombs are around the field
     * @return number of bombs around the field
     */
    public int getBombsAround() {
        return bombsAround;
    }
}
