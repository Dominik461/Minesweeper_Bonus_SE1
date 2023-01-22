package org.example;

import java.util.Random;

import static org.example.Main.bombLocations;

/**
 * Responsible for all logic in the game
 */
public class GameManager {

    /**
     * size of the game board.
     */
    private static final int SIZE = Main.SIZE;
    /**
     * how many bombs should be in a game.
     */
    private static final int BOMBS = Main.BOMBS;

    /**
     * creating an object of Random with a seed to be used later for placing bombs
     */
    Random random = new Random(System.currentTimeMillis());

    /**
     * Fills the array with Field objects that are created with the standard constructor
     * after that calls the setBombsInFieldArray method to place bombs
     * @param y y coordinate of the first uncover move
     * @param x x coordinate of the first uncover move
     * @param fieldArray copy of the current state from the fieldArray
     * @return calls the setBombsInFieldArray method and then returns the array with bombs placed
     */
    public Field[][] fillArray(int y, int x, Field[][] fieldArray){
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
            //Adds for each space in the array a Field object
                fieldArray[j][i] = new Field();
            }
        }
        //Calls the setBombsInFieldArray method to place the bombs before it is used
        return setBombsInFieldArray(y,x, fieldArray);
    }

    /**
     * <p>
     *     Sets as many bombs as the int value of bombs.
     * </p>
     * <p>
     *     In the beginning set a few local variable for the x and y value and a boolean if the bomb is valid to be placed at the location.
     *     Then we fill the bombLocations array with »-1« at each location. So that it is impossible to have duplicate values for a bomb location.
     * </p>
     * <p>
     *     A for loop is used with the length of the bombs variable, so that the number of bombs in the game is accurate.
     *     In the loop we use a do while loop with the condition <code>while(bombLocations[0][i] == -1 &#38;&#38; bombLocations[1][i] == -1)</code> so that there are always the correct number of bombs.
     * </p>
     * <p>
     *     In the beginning of each run setBombs will be set to <code>true</code> and x and y will be set to a random int with <code>random.nextInt(size)</code>.
     *     Then it will be checked if the rolled int value for x &#38; y is around the first uncover move. If it is the case then it will continue to the next loop to re-roll the values.
     * </p>
     * <p>
     *     When the values are not around the first uncover move, they go through a for loop to check if there is already a bomb placed with the same coordinates.
     *     If this is the case setBombs will be set to <code>false</code> and it stops checking the rest of the bombsLocations array.
     * </p>
     * <p>
     *     The bomb will only be set if setBombs is <code>true</code> with placing the x and y coordinates in the bombLocations array and setting the FieldStatus of the Field object at y,x in the fieldArray to BOMB.
     * </p>
     * @param fieldY y coordinate of the first uncover move
     * @param fieldX X coordinate of the first uncover move
     * @param fieldArray copy of the current state from the fieldArray
     * @return fieldArray that now has bombs placed
     */
    private Field[][] setBombsInFieldArray(int fieldY, int fieldX, Field[][] fieldArray) {
        //Coordinates to be used to for the placement of a mine
        int x, y;
        boolean setBomb;
        for (int i = 0; i < BOMBS; i++) {
            bombLocations[0][i]=-1;
            bombLocations[1][i]=-1;
        }
        for (int i = 0; i < BOMBS; i++) {
            //Will loop until it finds a Field object that has not yet a mine
            do {
                setBomb = true;
                //Sets a random value that is between 0 and the size of the board-1
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
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
                for (int j = 0; j < BOMBS; j++) {
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
            } while(bombLocations[0][i] == -1 && bombLocations[1][i] == -1);
        }
        return fieldArray;
    }

    /**
     * Checks if a given move is with in the game board area
     * @param x is the x coordinate of the entered field
     * @param y is the y coordinate of the entered field
     * @return will be True if it is in the game board area. Otherwise, it will return false.
     */
    private boolean validMove(int y, int x){
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /**
     * If the field still is covered or a bomb a "flag" will be placed on the field.
     * If there is already a "flag" it will be removed and the field will be set to covered or if it was a bomb to bomb again.
     * @param x is the x coordinate of the entered field
     * @param y is the y coordinate of the entered field
     * @param fieldArray copy of the current state from the fieldArray
     * @param bombLocations all locations where bombs are
     * @return fieldArray with either a new flag placed or an old flag removed
     */

    public Field[][] placeOrRemoveFlag(int y, int x, Field[][] fieldArray, int[][] bombLocations){
        boolean wasBomb=false;
        if(fieldArray[y][x].getStatus() == Field.FieldStatus.COVERED || fieldArray[y][x].getStatus() == Field.FieldStatus.BOMB)
            fieldArray[y][x].setStatus(Field.FieldStatus.FLAG);
        else if(fieldArray[y][x].getStatus() == Field.FieldStatus.FLAG) {
            for (int i = 0; i < BOMBS; i++) {
                if (bombLocations[0][i] == y && bombLocations[1][i] == x) {
                    fieldArray[y][x].setStatus(Field.FieldStatus.BOMB);
                    wasBomb = true;
                    break;
                }
            }
            if (!wasBomb)
                fieldArray[y][x].setStatus(Field.FieldStatus.COVERED);
        }
        return fieldArray;
    }

    /**
     * Prints out the game board with each Field from the fieldArray
     * @param fieldArray copy of the current state from the fieldArray
     */
    public void fieldOutput(Field[][] fieldArray) {
        System.out.println("\nTotal bombs: "+ BOMBS +" flags placed: "+countFlags(fieldArray));
        System.out.println("    0  1  2  3  4  5  6  7  8  9");
        System.out.print("---------------------------------");

        for (int y = 0; y< SIZE; y++){

            System.out.print("\n" + y + " |");
            for (int x = 0; x< SIZE; x++){
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
     * <p>Uncovers the selected field on the game board if it is still covered.</p>
     * <p>
     *     It will only be uncovered if the x and y coordinates are inside the playing area.
     *     Then it will check if there is 1 or more bombs around the selected field.
     * </p>
     * <p>
     *     If this is the case the number of how many bombs will be saved to the field with <code>fieldArray[y][x].setBombsAround(getBombsAround(y, x))</code>
     *     Otherwise it will call <code>revealNearbyField(y,x)</code>
     * </p>
     * <p>
     *     The player will get a information if they try to uncover a field that has already been uncovered or flagged.
     * </p>
     * @param x is the x coordinate of the entered field
     * @param y is the y coordinate of the entered field
     * @param fieldArray copy of the current state from the fieldArray
     * @return the new state of the fieldArray
     */
    public Field[][] uncover(int y, int x, Field[][] fieldArray) {

        if(validMove(y,x) && fieldArray[y][x].getStatus() == Field.FieldStatus.COVERED){
            fieldArray[y][x].setStatus(Field.FieldStatus.UNCOVERED);
            if(getBombsAround(y,x) != 0)
                fieldArray[y][x].setBombsAround(getBombsAround(y, x));
            else
                revealNearbyField(y,x, fieldArray);

        } else if (validMove(y,x) && fieldArray[y][x].getStatus() == Field.FieldStatus.UNCOVERED) {
            System.out.println("\nField is already uncovered\n");
        } else if (validMove(y,x) && fieldArray[y][x].getStatus() == Field.FieldStatus.FLAG) {
            System.out.println("\nField has a Flag and can't be uncovered\n");
        }
        return  fieldArray;
    }

    /**
     * Counts how many bombs there are around the selected Field object
     * <p>A counter variable is set and will be increased for each bomb that is around the selected field and then returned.</p>
     * <p>To check if there is a bomb nearby the bombLocations array is used. Because the field which is a bomb could be already flagged</p>
     * @param y coordinate for the fieldArray
     * @param x coordinate for the fieldArray
     * @return an int value of how many bombs there are
     */
    private int getBombsAround(int y, int x){
        int counter = 0;

        for (int i = 0; i < BOMBS; i++) {
            if (
                    (bombLocations[0][i] == y-1 && bombLocations[1][i] == x-1 && validMove(y-1, x-1))||
                    (bombLocations[0][i] == y-1 && bombLocations[1][i] == x && validMove(y-1, x))||
                    (bombLocations[0][i] == y-1 && bombLocations[1][i] == x+1 && validMove(y-1, x+1))||
                    (bombLocations[0][i] == y && bombLocations[1][i] == x-1 && validMove(y, x-1))||
                    (bombLocations[0][i] == y && bombLocations[1][i] == x+1 && validMove(y, x+1))||
                    (bombLocations[0][i] == y+1 && bombLocations[1][i] == x-1 && validMove(y+1, x-1))||
                    (bombLocations[0][i] == y+1 && bombLocations[1][i] == x && validMove(y+1, x))||
                    (bombLocations[0][i] == y+1 && bombLocations[1][i] == x+1 && validMove(y+1, x+1))
            )
                counter++;
        }

        return counter;
    }

    /**
     * Goes through the fieldArray and counts how many fields are flagged
     * @param fieldArray copy of the current state from the fieldArray
     * @return counter with the number of flags already placed
     */
    private int countFlags(Field[][] fieldArray){
        int counter = 0;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if(fieldArray[y][x] == null)
                    break;
                if(fieldArray[y][x].getStatus() == Field.FieldStatus.FLAG)
                    counter++;
            }
        }
        return counter;
    }

    /**
     * <p>
     * Will only be called if the uncovered field has 0 bombs around them.
     * Sets two variables (adjacentY, adjacentX) which are used to look at the nearby fields from the one that was uncovered.
     * </p>
     * <p>
     * Then two for loops are used to check each field nearby. Each for loop goes from »-1« to »1«.
     * In the beginning of each cycle we set adjacentY &#38; adjacentX to y &#38; x respectively. If both for loops are at »0« we skip the cycle because this is the field that called the method.
     * </p>
     * <p>
     * After that we add the value of the first for loop to adjacentY and the second to adjacentX.
     * Now we have a coordinate of a field that is around the uncovered one.
     * </p>
     * <p>
     * If adjacentY &#38; adjacentX are valid coordinates then we check if the field is still covered.
     * When this is the case the uncover method will be called with the parameters adjacentY &#38; adjacentX.
     * </p>
     * @param y is the y coordinate of the uncovered field
     * @param x is the x coordinate of the uncovered field
     * @param fieldArray copy of the current state from the fieldArray
     */
    private void revealNearbyField(int y, int x, Field[][] fieldArray){
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
                        uncover(adjacentY,adjacentX, fieldArray);
                    }
                }
            }
        }
    }

    /**
     * <p>
     *     First checks if all bombs have been flagged. If not it will return <code>false</code>.
     * </p>
     * <p>
     *     Then goes through each field object in the fieldArray and checks if it is still covered. When it is the case <code>false</code> will be returned.
     * </p>
     * <p>
     *     If not then <code>true</code> will be returned
     * </p>
     * @param fieldArray copy of the current state from the fieldArray
     * @return <code>true</code> if the player has won and <code>false</code> if the player hasn't won yet
     */
    public boolean checkWin(Field[][] fieldArray) {

        for (int i = 0; i < BOMBS; i++) {
            if(fieldArray[bombLocations[0][i]][bombLocations[1][i]].getStatus() != Field.FieldStatus.FLAG){
                return false;
            }
        }

        for (int i = 0; i< SIZE; i++){
            for (int j = 0; j< SIZE; j++){
                if(fieldArray[i][j].getStatus() == Field.FieldStatus.COVERED){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * <p>Will only be called when the player hit a bomb.</p>
     * <p>Then it will print out a game board where every bomb, that has not been flagged, is shown by a »Q«</p>
     * @param fieldArray copy of the current state from the fieldArray
     */
    public void showBombs(Field[][] fieldArray){
        System.out.println("These would have been the bombs that are not flagged:");
        System.out.println("    0  1  2  3  4  5  6  7  8  9");
        System.out.print("---------------------------------");

        for (int y = 0; y< SIZE; y++){

            System.out.print("\n" + y + " |");
            for (int x = 0; x< SIZE; x++){
                if(fieldArray[y][x].getStatus() == Field.FieldStatus.BOMB){
                    System.out.print(" Q ");
                } else {
                    System.out.print(" " + fieldArray[y][x].getStatus().toString() + " ");
                }

            }
        }
    }
}

