package org.example;

import java.util.Random;

import static org.example.Main.bombLocations;

/**
 * Responsible for all logic in the game
 */
public class GameManager {


    private static final int SIZE = Main.SIZE;
    private static final int BOMBS = Main.BOMBS;
    /*
    private Field[][] fieldArray;
    */

    Random random = new Random(System.currentTimeMillis());

    /*
    public void setFieldArray(Field[][] fieldArray){
        this.fieldArray = fieldArray;
    }
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


    private boolean validMove(int y, int x){
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }


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

    public void fieldOutput(Field[][] fieldArray) {
        System.out.println("Total bombs: "+ BOMBS +" flags placed: "+countFlags(fieldArray));
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

    public Field[][] uncover(int y, int x, Field[][] fieldArray) {

        if(validMove(y,x) && fieldArray[y][x].getStatus() == Field.FieldStatus.COVERED){
            fieldArray[y][x].setStatus(Field.FieldStatus.UNCOVERED);
            if(getBombsAround(y,x) != 0)
                fieldArray[y][x].setBombsAround(getBombsAround(y, x));
            else
                revealNearbyField(y,x, fieldArray);

        } else if (validMove(y,x) && fieldArray[y][x].getStatus() == Field.FieldStatus.UNCOVERED) {
            System.out.println("\nField is already uncovered\n");
        }
        return  fieldArray;
    }


    /*
    public Field getFieldByCoordinates(int y, int x){
        return fieldArray[y][x];
    }
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

    public boolean checkWin(Field[][] fieldArray) {
        boolean win = true;
        for (int i = 0; i< SIZE; i++){
            for (int j = 0; j< SIZE; j++){
                if((fieldArray[i][j].getStatus() == Field.FieldStatus.COVERED ||
                        fieldArray[i][j].getStatus() == Field.FieldStatus.BOMB) && countFlags(fieldArray) != BOMBS){
                    win = false;
                    break;
                }
            }
        }
        return win;
    }

    public void showBombs(Field[][] fieldArray){
        System.out.println("These would have been the bombs:");
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

