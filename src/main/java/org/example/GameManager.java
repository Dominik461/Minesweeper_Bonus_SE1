package org.example;

import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private int size;
    private Field[][] fieldArray;
    Random random = new Random(System.currentTimeMillis());
    public void setSize(int size){
        this.size = size;
    }
    public void setFieldArray(Field[][] fieldArray){
        this.fieldArray = fieldArray;
    }
    //Gets an empty array of Fields with the size of the board and how many mines the game has
        public Field[][] fillArray(Field[][] fields, int size, int mines){
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                //Adds for each space in the array a Field object
                fields[x][y] = new Field();
            }
        }
        //Calls the setMines method to place the mines before it is being returned for use
        return setMines(fields, size, mines);
    }

    //Gets an array filled with Field objects with the size of the board and how many mines the game has
    private Field[][] setMines(Field[][] fields, int size, int mines) {
        //Coordinates to be used to for the placement of a mine
        int x, y;
        for (int i = 0; i <= mines; i++) {
            //Will loop until it finds a Field object that has not yet a mine
            do {
                //Sets a random value that is between 0 and the size of the board -1
                x = random.nextInt(size);
                y = random.nextInt(size);
                //Checks if the Field object is already mined
                if(!fields[x][y].getIsMine())
                    fields[x][y].setMine(true);
            } while(!fields[x][y].getIsMine());
        }
        return fields;
    }

    //Checks if the move is in the board
    private boolean validMove(int x, int y, int size){
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /*
    Sets a Flag if the field is still covered
    If the field already has a flag it will be removed
     */
    public String setFlag(Field[][] fields, int x, int y){
        if(fields[x][y].getIsCovered() && fields[x][y].getType().equals("X"))
            fields[x][y].setType("F");
        else if(fields[x][y].getIsCovered() && fields[x][y].getType().equals("F"))
            fields[x][y].setType("X");
        return fields[x][y].getType();
    }

    public void fieldOutput(Field[][] fieldArray, int size) {
        System.out.println("0  1  2  3  4  5  6  7  8  9");
        System.out.print("---------------------------------");

        for (int i = 0; i<size; i++){

            System.out.print("\n" + i + " |");
            for (int j = 0; j<size; j++){
                System.out.print(" " + fieldArray[i][j].getType() + " ");
            }
        }
    }


    /*Lets the player choose their action
    public void getUserAction() {
        System.out.println("1: Uncover field");
        System.out.println("2: Set flag");
        try {
            Scanner scan = new Scanner(System.in);
            int action = scan.nextInt();

            switch (action){
                case 1: uncover();
                case 2: ;
                default:
                    System.out.println("Please choose from the options above");
                    getUserAction();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //Checks if the chosen field
    private void uncover() {

    }

    /*
    Returns the field at the given coordinates
     */
    public Field getFieldByCoordinates(int x, int y){
        return fieldArray[x][y];
    }


    /*
    Checks for mines around the selected field
    TODO implement
     */
    private int getMinesAround(Field[][] fields, int x, int y){
        int counter = 0;

        if(validMove(x-1, y-1, size)){
            if(fields[x-1][y-1].getIsMine())
                counter++;
        }

        if(validMove(x, y-1, size)){
            if(fields[x][y-1].getIsMine())
                counter++;
        }


        if(validMove(x+1, y-1, size)){
            if(fields[x+1][y-1].getIsMine())
                counter++;
        }

        if(validMove(x-1, y, size)){
            if(fields[x-1][y].getIsMine())
                counter++;
        }

        if(validMove(x+1, y, size)){
            if(fields[x+1][y].getIsMine())
                counter++;
        }

        if(validMove(x-1, y+1, size)){
            if(fields[x-1][y+1].getIsMine())
                counter++;
        }

        if(validMove(x, y+1, size)){
            if(fields[x][y+1].getIsMine())
                counter++;
        }

        if(validMove(x+1, y+1, size)){
            if(fields[x+1][y+1].getIsMine())
                counter++;
        }

        return counter;
    }
}

