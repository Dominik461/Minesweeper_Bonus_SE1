package org.example;

import java.util.Random;

public class GameManager {
    private int size, bombs;
    private Field[][] fieldArray;
    private int[][] bombLocations;
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
        bombLocations = new int[2][bombs];
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
     * @param y y coordinate of the first uncover move
     * @param x x coordinate of the first uncover move
     */
    //Gets an empty array of Fields with the size of the board and how many bomb the game has
    public void fillArray(int y, int x){
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
            //Adds for each space in the array a Field object
                fieldArray[j][i] = new Field();
            }
        }
        //Calls the setMines method to place the bomb before it is being returned for use
        setBomb(y,x);
    }

    /**
     * Sets as many bombs as the int value of bombs. The do while loop is used to be sure that there a no duplicates
     * @param fieldY y coordinate of the first uncover move
     * @param fieldX X coordinate of the first uncover move
     */
    //Gets an array filled with Field objects with the size of the board and how many bomb the game has
    private void setBomb(int fieldY, int fieldX) {
        //Coordinates to be used to for the placement of a mine
        int x, y;
        boolean setBomb;
        for (int i = 0; i < 5; i++) {
            bombLocations[0][i]=-1;
            bombLocations[1][i]=-1;
        }
        for (int i = 0; i < bombs; i++) {
            //Will loop until it finds a Field object that has not yet a mine
            do {
                setBomb = true;
                //Sets a random value that is between 0 and the size of the board-1
                x = random.nextInt(size);
                y = random.nextInt(size);
                if(
                        (x == fieldX-1 && y == fieldY-1) ||
                        (x == fieldX-1 && y == fieldY) ||
                        (x == fieldX-1 && y == fieldY+1) ||
                        (x == fieldX && y == fieldY-1) ||
                        (x == fieldX && y == fieldY) ||
                        (x == fieldX && y == fieldY+1) ||
                        (x == fieldX+1 && y == fieldY-1) ||
                        (x == fieldX+1 && y == fieldY) ||
                        (x == fieldX+1 && y == fieldY+1)
                )
                    continue;

                //Checks if the Field object is already mined
                for (int j = 0; j < 5; j++) {
                    if(bombLocations[0][j] == y && bombLocations[1][j] == x){
                        setBomb = false;
                        break;
                    }
                }

                if(setBomb){
                    bombLocations[0][i]=y;
                    bombLocations[1][i]=x;
                    fieldArray[y][x].setStatus(Field.FieldStatus.BOMB);
                }
            } while(!setBomb);
        }
    }

    /**
     * Checks if a given move is with in the game board area
     * @param x is the x coordinate of the entered field
     * @param y is the y coordinate of the entered field
     * @return will be True if it is in the game board area. Otherwise, it will return false.
     */
    //Checks if the move is in the board
    private boolean validMove(int y, int x){
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /**
     * If the field is still covered a "flag" will be placed on the field. If there is already a "flag" it will be removed.
     * @param x coordinate for the fieldArray
     * @param y coordinate for the fieldArray
     */
    /*
    Sets a Flag if the field is still covered
    If the field already has a flag it will be removed
     */
    public void placeOrRemoveFlag(int y, int x){
        boolean wasBomb=false;
        if(fieldArray[y][x].getStatus() == Field.FieldStatus.COVERED || fieldArray[y][x].getStatus() == Field.FieldStatus.BOMB)
            fieldArray[y][x].setStatus(Field.FieldStatus.FLAG);
        else if(fieldArray[y][x].getStatus() == Field.FieldStatus.FLAG) {
            for (int i = 0; i < bombs; i++) {
                if (bombLocations[0][i] == y && bombLocations[1][i] == x) {
                    fieldArray[y][x].setStatus(Field.FieldStatus.BOMB);
                    wasBomb = true;
                    break;
                }
            }
            if (!wasBomb)
                fieldArray[y][x].setStatus(Field.FieldStatus.COVERED);
        }
    }

    /**
     *Prints out the game board with each Field from the fieldArray
     */
    public void fieldOutput() {
        System.out.println("Total bombs: "+ bombs+" flags placed: "+countFlags());
        System.out.println("    0  1  2  3  4  5  6  7  8  9");
        System.out.print("---------------------------------");

        for (int y = 0; y<size; y++){

            System.out.print("\n" + y + " |");
            for (int x = 0; x<size; x++){
                if(fieldArray[y][x] == null){
                    System.out.print(" X ");
                }else{
                    if(fieldArray[y][x].getBombsAround() == 0)
                        System.out.print(" " + fieldArray[y][x].getStatus().toString() + " ");
                    else
                        System.out.print(" " + fieldArray[y][x].getBombsAround() + " ");
                }
            }
        }
        System.out.println();
    }

    /**
     * Uncovers the selected field on the game board if it is still covered
     * @param x coordinate for the fieldArray
     * @param y coordinate for the fieldArray
     */
    /*
    Checks if the chosen field is covered, exists, is not a bomb and not a flag
    then uncovers the field
     */
    public void uncover(int y, int x) {
        if(fieldArray[y][x] == null){
            fillArray(y,x);
        }
        Field field = getFieldByCoordinates(y,x);
        if(validMove(y,x) && field.getStatus() == Field.FieldStatus.COVERED){
            fieldArray[y][x].setStatus(Field.FieldStatus.UNCOVERED);
            if(getMinesAround(y,x) != 0)
                fieldArray[y][x].setBombsAround(getMinesAround(y, x));
            else
                revealNearbyField(y,x);

        }
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
    public Field getFieldByCoordinates(int y, int x){
        return fieldArray[y][x];
    }

    /**
     * Counts how many bombs there are around the selected Field object
     * @param y coordinate for the fieldArray
     * @param x coordinate for the fieldArray
     * @return an int value of how many bombs there are
     */
    /*
    Checks for mines around the selected field
     */
    private int getMinesAround(int y, int x){
        int counter = 0;

        if(validMove(y-1, x-1)){
            if(fieldArray[y-1][x-1].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y, x-1)){
            if(fieldArray[y][x-1].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y+1, x-1)){
            if(fieldArray[y+1][x-1].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y-1, x)){
            if(fieldArray[y-1][x].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y+1, x)){
            if(fieldArray[y+1][x].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y-1, x+1)){
            if(fieldArray[y-1][x+1].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y, x+1)){
            if(fieldArray[y][x+1].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        if(validMove(y+1, x+1)){
            if(fieldArray[y+1][x+1].getStatus() == Field.FieldStatus.BOMB)
                counter++;
        }

        return counter;
    }

    private int countFlags(){
        int counter = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if(fieldArray[y][x] == null)
                    break;
                if(fieldArray[y][x].getStatus() == Field.FieldStatus.FLAG)
                    counter++;
            }
        }
        return counter;
    }

    private void revealNearbyField(int y, int x){
        int adjacentX, adjacentY;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                adjacentY = y;
                adjacentX = x;
                if (i == 0 && j == 0){
                    continue;
                }
                adjacentX +=j;
                adjacentY +=i;
                if(validMove(adjacentY, adjacentX)){
                    if(fieldArray[adjacentY][adjacentX].getStatus() == Field.FieldStatus.COVERED){
                        fieldArray[adjacentY][adjacentX].setStatus(Field.FieldStatus.UNCOVERED);
                        if(getMinesAround(adjacentY, adjacentX) == 0)
                            fieldArray[adjacentY][adjacentX].setStatus(Field.FieldStatus.UNCOVERED);
                        else
                            fieldArray[adjacentY][adjacentX].setBombsAround(getMinesAround(adjacentY, adjacentX));

                        if(fieldArray[adjacentY][adjacentX].getBombsAround() == 0)
                            revealNearbyField(adjacentY, adjacentX);
                    }
                }
            }
        }
    }

    public boolean checkWin() {
        boolean win = true;
        for (int i = 0; i<size; i++){
            for (int j= 0; j<size; j++){
                if((fieldArray[i][j].getStatus() == Field.FieldStatus.COVERED ||
                        fieldArray[i][j].getStatus() == Field.FieldStatus.BOMB) && countFlags() == bombs){
                    win = false;
                    break;
                }
            }
        }
        return win;
    }
}

