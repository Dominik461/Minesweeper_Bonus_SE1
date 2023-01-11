package org.example;

import java.util.Random;
import java.util.Scanner;

public class GameManager {
    Random random = new Random(System.currentTimeMillis());
    public Field[][] fillArray(Field[][] fields, int size, int mines){
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                fields[x][y] = new Field();
            }
        }
        return setMines(fields, size, mines);
    }

    private Field[][] setMines(Field[][] fields, int size, int mines) {
        int x, y;
        for (int i = 0; i <= mines; i++) {
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
                if(!fields[x][y].getIsMine())
                    fields[x][y].setMine(true);
            } while(!fields[x][y].getIsMine());
            fields[x][y].setMine(true);
        }
        return fields;
    }

    //Field system output
    public void fieldOutput(Field[][] fieldArray, int size){

        System.out.println("    0  1  2  3  4  5  6  7  8  9 ");
        System.out.print("---------------------------------");
        for (int j = 0; j<size; j++){
            System.out.print("\n" + j + " |" );
            for (int i = 0; i<size; i++) System.out.print(" " + fieldArray[j][i].getType() + " ");
        }

    }

    public void getUserAction (){
        System.out.println("\n\n\n1: Choose a field to uncover");
        System.out.println("2: Set flag");

        try {
            Scanner scan = new Scanner(System.in);
            int action = scan.nextInt();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

