package org.example;

import java.util.Scanner;

/**
 * Receives user input and interacts with the user
 */
public class Main {
    /**
     * Your application's main entry point.
     *
     * @param args Yet unused
     */
    final static int SIZE = 10, BOMBS = 15;
    public static int[][] bombLocations = new int[2][BOMBS];

    public static void main( String[] args ) {
        GameManager manager = new GameManager();


        String testInput;
        int x,y;
        Field [][] fieldArray = new Field[SIZE][SIZE];
        boolean gameover = false, firstMove = true;
        //manager.setSize(SIZE);
        //manager.setFieldArray(fieldArray);
        // manager.setBombs(BOMBS);
        do {
            x=-1;
            y=-1;

            manager.fieldOutput(fieldArray);
            System.out.println("\n\n1: Uncover field");
            System.out.println("2: Set flag");
            Scanner scan = new Scanner(System.in);
            String action = scan.next();
            try {
                if(firstMove && action.equals("2")) {
                    System.out.println("Please uncover a field first.");
                    continue;
                }
                do{
                    testInput=" ";
                    System.out.println("");
                    scan.nextLine();
                    System.out.println("Please type in the y coordinate of your field");
                    try{
                        y = scan.nextInt();
                    }catch (Exception e){
                        System.out.println("No number entered");
                        testInput = "";
                    }

                    if((y<0 || y> SIZE)&&!testInput.equals(""))
                        System.out.println("Number entered was not inside playing area");

                }while (y<0 || y> SIZE);

                do{
                    testInput=" ";
                    System.out.println("");
                    scan.nextLine();
                    System.out.println("Please type in the x coordinate of your field");
                    try{
                        x = scan.nextInt();
                    }catch (Exception e){
                        System.out.println("No number entered");
                        testInput = "";
                    }

                    if((x<0 || x> SIZE)&&!testInput.equals(""))
                        System.out.println("Number entered was not inside playing area");

                }while (x<0 || x> SIZE);

                if(firstMove)
                    fieldArray = manager.fillArray(y,x,fieldArray);

                switch (action) {
                    case "1" -> {

                        fieldArray = manager.uncover(y, x, fieldArray);
                        if (fieldArray[y][x].getStatus() == Field.FieldStatus.BOMB) {
                            gameover = true;
                            System.out.println("idiot");
                            manager.showBombs(fieldArray);
                        }
                    }
                    case "2" -> {
                        fieldArray = manager.placeOrRemoveFlag(y,x, fieldArray, bombLocations);
                    }
                    default -> System.out.println("Please choose from the options above");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(manager.checkWin(fieldArray)){
                manager.fieldOutput(fieldArray);
                System.out.println("\nYou won");
                break;
            }

            firstMove= false;
        }while(!gameover);
 }

}
