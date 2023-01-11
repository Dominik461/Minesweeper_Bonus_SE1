package org.example;

import java.util.Random;

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

    public String setFlag(Field field){
        if(field.getIsCovered())
            field.setType("F");
        return field.getType();
    }
}

