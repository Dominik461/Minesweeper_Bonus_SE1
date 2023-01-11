package org.example;

import java.util.Random;
import java.util.Scanner;

public class GameManager {
    Random random = new Random(System.currentTimeMillis());
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

    private boolean validMove(int x, int y, int size){
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public String setFlag(Field[][] fields, int x, int y){
        if(fields[x][y].getIsCovered())
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

    public void getUserAction() {
        try {
            Scanner scan = new Scanner(System.in);
            int action = scan.nextInt();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

