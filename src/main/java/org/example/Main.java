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

    public static void main( String[] args ) {
        GameManager manager = new GameManager();

        final int size = 10, bombs = 15;
        String testInput;
        int x,y;
        Field [][] fieldArray = new Field[size][size];
        boolean gameover = false, firstMove = true;
        manager.setSize(size);
        //manager.setFieldArray(fieldArray);
        manager.setBombs(bombs);
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

                    if((y<0 || y>size)&&!testInput.equals(""))
                        System.out.println("Number entered was not inside playing area");

                }while (y<0 || y>size);

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

                    if((x<0 || x>size)&&!testInput.equals(""))
                        System.out.println("Number entered was not inside playing area");

                }while (x<0 || x>size);

                if(firstMove)
                    fieldArray = manager.fillArray(y,x,fieldArray);

                switch (action) {
                    case "1" -> {

                        fieldArray = manager.uncover(y, x, fieldArray);
                        if (fieldArray[y][x].getStatus() == Field.FieldStatus.BOMB) {
                            gameover = true;
                            System.out.println("idiot");
                        }
                    }
                    case "2" -> {

                        fieldArray = manager.placeOrRemoveFlag(y,x, fieldArray);
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
