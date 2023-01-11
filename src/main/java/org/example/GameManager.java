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

