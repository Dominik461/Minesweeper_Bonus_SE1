package org.example;

import java.util.Random;

public class GameManager {
    private int size, bombs;
    private Field[][] fieldArray;
    Random random = new Random(System.currentTimeMillis());

    /**
     * This will set the size for later use as the reference of the board size
     * @param size is the size of the game board area
     */
    public void setSize(int size){
        this.size = size;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    /**
     * Sets the array for each field of the playing board
     * @param fieldArray array with fields to use as the playing area
     */
    public void setFieldArray(Field[][] fieldArray){
        this.fieldArray = fieldArray;
    }

    /**
     * Fills the array with Field objects that are created with the standard constructor
     * after that calls the setMines method to place mines
     * @param bomb how many bomb there should be in each game
     */
    //Gets an empty array of Fields with the size of the board and how many bomb the game has
    public void fillArray(int bomb){
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
            //Adds for each space in the array a Field object
                fieldArray[x][y] = new Field();
            }
        }
        //Calls the setMines method to place the bomb before it is being returned for use
        setMines(bomb);
    }

    /**
     * Sets as many bombs as the int value of bombs. The do while loop is used to be sure that there a no duplicates
     * @param bomb how many bomb there should be in each game
     */
    //Gets an array filled with Field objects with the size of the board and how many bomb the game has
    private void setMines(int bomb) {
        //Coordinates to be used to for the placement of a mine
        int x, y;
        for (int i = 0; i <= bomb; i++) {
            //Will loop until it finds a Field object that has not yet a mine
            do {
                //Sets a random value that is between 0 and the size of the board -1
                x = random.nextInt(size);
                y = random.nextInt(size);
                //Checks if the Field object is already mined
                if(!fieldArray[x][y].getIsBomb())
                    fieldArray[x][y].setBomb(true);
            } while(!fieldArray[x][y].getIsBomb());
        }
    }

    /**
     * Checks if a given move is with in the game board area
     * @param x is the x coordinate of the entered field
     * @param y is the y coordinate of the entered field
     * @return will be True if it is in the game board area. Otherwise, it will return false.
     */
    //Checks if the move is in the board
    private boolean validMove(int x, int y){
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /**
     * If the field is still covered a "flag" will be placed on the field. If there is already a "flag" it will be removed.
     * @param x coordinate for the fieldArray
     * @param y coordinate for the fieldArray
     * @return the String of the Field object from the selected Field of the fieldArray
     */
    /*
    Sets a Flag if the field is still covered
    If the field already has a flag it will be removed
     */
    public void placeOrRemoveFlag(int x, int y){
        if(validMove(x,y)){
            if(fieldArray[x][y].getIsCovered() && fieldArray[x][y].getType().equals("X"))
                fieldArray[x][y].setType("F");
            else if(fieldArray[x][y].getIsCovered() && fieldArray[x][y].getType().equals("F"))
                fieldArray[x][y].setType("X");
        }
        System.out.println("The x and/or y coordinate was not within the game board!");
    }

    /**
     *Prints out the game board with each Field from the fieldArray
     */
    public void fieldOutput() {
        System.out.println("    0  1  2  3  4  5  6  7  8  9");
        System.out.print("---------------------------------");

        for (int i = 0; i<size; i++){

            System.out.print("\n" + i + " |");
            for (int j = 0; j<size; j++){
                System.out.print(" " + fieldArray[i][j].getType() + " ");
            }
        }
        System.out.println();
    }

    /**
     * Uncovers the selected field on the game board if it is still covered
     * @param x  coordinate for the fieldArray
     * @param y coordinate for the fieldArray
     */
    /*
    Checks if the chosen field is covered, exists, is not a bomb and not a flag
    then uncovers the field
     */
    public void uncover(int x, int y) {
        Field field = getFieldByCoordinates(x,y);
        if(validMove(x,y) && field.getIsCovered() &&  !field.getIsBomb() && !field.getIsFlag()){
            fieldArray[x][y].setCovered(false);
        }
        //TODO change string X to " "
    }

    /**
     * Gets the selected Field object from the fieldArray
     * @param x coordinate for the fieldArray
     * @param y coordinate for the fieldArray
     * @return the selected Field object
     */
    /*
    Returns the field at the given coordinates
     */
    public Field getFieldByCoordinates(int x, int y){
        return fieldArray[x][y];
    }

    /**
     * Counts how many bombs there are around the selected Field object
     * @param x coordinate for the fieldArray
     * @param y coordinate for the fieldArray
     * @return an int value of how many bombs there are
     */
    /*
    Checks for mines around the selected field
    TODO implement
     */
    private int getMinesAround(int x, int y){
        int counter = 0;

        if(validMove(x-1, y-1)){
            if(fieldArray[x-1][y-1].getIsBomb())
                counter++;
        }

        if(validMove(x, y-1)){
            if(fieldArray[x][y-1].getIsBomb())
                counter++;
        }


        if(validMove(x+1, y-1)){
            if(fieldArray[x+1][y-1].getIsBomb())
                counter++;
        }

        if(validMove(x-1, y)){
            if(fieldArray[x-1][y].getIsBomb())
                counter++;
        }

        if(validMove(x+1, y)){
            if(fieldArray[x+1][y].getIsBomb())
                counter++;
        }

        if(validMove(x-1, y+1)){
            if(fieldArray[x-1][y+1].getIsBomb())
                counter++;
        }

        if(validMove(x, y+1)){
            if(fieldArray[x][y+1].getIsBomb())
                counter++;
        }

        if(validMove(x+1, y+1)){
            if(fieldArray[x+1][y+1].getIsBomb())
                counter++;
        }

        return counter;
    }

}

