package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.example.Main.BOMBS;
import static org.example.Main.bombLocations;

public class GameManagerTest {
    GameManager manager = new GameManager();
    final int SIZE = Main.SIZE;
    Field[][] testArray = new Field[SIZE][SIZE];
    Field[][] emptyArray =  new Field[SIZE][SIZE];
    public void createArray(){
        for(int i = 0; i< SIZE; i++){
            for (int j = 0; j< SIZE; j++){
                testArray[i][j] = new Field();
            }
        }
    }

    @Test
    public void testFillArray(){
        Field[][] fieldArray = manager.fillArray(0,0, emptyArray);
        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                Assert.assertNotNull(fieldArray[j][i]);
            }
        }

        //will be overwritten
        Field[][] fieldArray2 = manager.fillArray(0,0, testArray);
        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                Assert.assertNotNull(fieldArray2[j][i]);
            }
        }

    }

    @Test
    public void TestPlaceOrRemoveFlag(){
        createArray();
        //covered -> Flag -> covered
        testArray = manager.placeOrRemoveFlag(1,1, testArray, bombLocations);
        testArray = manager.placeOrRemoveFlag(3,5, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.FLAG, testArray[1][1].getStatus());
        Assert.assertSame(Field.FieldStatus.FLAG, testArray[3][5].getStatus());
        testArray = manager.placeOrRemoveFlag(1,1, testArray, bombLocations);
        testArray = manager.placeOrRemoveFlag(3,5, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.COVERED, testArray[1][1].getStatus());
        Assert.assertSame(Field.FieldStatus.COVERED, testArray[3][5].getStatus());

        //uncovered -> uncovered (can't place flag on uncovered)
        testArray[4][1].setStatus(Field.FieldStatus.UNCOVERED);
        testArray[5][1].setStatus(Field.FieldStatus.UNCOVERED);
        testArray = manager.placeOrRemoveFlag(4,1, testArray, bombLocations);
        testArray = manager.placeOrRemoveFlag(5,1, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.UNCOVERED, testArray[4][1].getStatus());
        Assert.assertSame(Field.FieldStatus.UNCOVERED, testArray[5][1].getStatus());

        //Bomb -> Flag -> Bomb
        bombLocations[0][2] = 2;
        bombLocations[1][2] = 2;
        testArray[2][2].setStatus(Field.FieldStatus.BOMB);
        testArray = manager.placeOrRemoveFlag(2,2, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.FLAG, testArray[2][2].getStatus());
        testArray = manager.placeOrRemoveFlag(2,2, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.BOMB, testArray[2][2].getStatus());
        bombLocations[0][3] = 3;
        bombLocations[1][3] = 4;
        testArray[3][4].setStatus(Field.FieldStatus.BOMB);
        testArray = manager.placeOrRemoveFlag(3,4, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.FLAG, testArray[3][4].getStatus());
        testArray = manager.placeOrRemoveFlag(3,4, testArray, bombLocations);
        Assert.assertSame(Field.FieldStatus.BOMB, testArray[3][4].getStatus());

    }

    @Test
    public void testUncover(){
        createArray();
        testArray[1][5].setStatus(Field.FieldStatus.FLAG);
        testArray = manager.uncover(1,5, testArray);
        Assert.assertEquals(Field.FieldStatus.FLAG, testArray[1][5].getStatus());

        testArray = manager.uncover(4,5, testArray);
        Assert.assertEquals(Field.FieldStatus.UNCOVERED, testArray[4][5].getStatus());

        testArray = manager.uncover(4,5, testArray);
        Assert.assertEquals(Field.FieldStatus.UNCOVERED, testArray[4][5].getStatus());
        //if a bomb is uncovered the method will not be called
    }

    @Test
    public void testCheckWin(){
        createArray();
        int flag = 0;
        createArray();
        Assert.assertFalse(manager.checkWin(testArray));

        for(int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
               if(flag<15) {
                   testArray[i][j].setStatus(Field.FieldStatus.FLAG);
                   flag++;
               } else{
                   testArray[i][j].setStatus(Field.FieldStatus.UNCOVERED);
               }
            }
        }

        Assert.assertTrue(manager.checkWin(testArray));
    }

}
