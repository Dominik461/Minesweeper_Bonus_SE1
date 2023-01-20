package org.example;

import java.util.Scanner;

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
        boolean gameover = false;
        manager.setSize(size);
        manager.setFieldArray(fieldArray);
        manager.setBombs(bombs);

        do {
            x=-1;
            y=-1;

            manager.fieldOutput();
            System.out.println("\n\n1: Uncover field");
            System.out.println("2: Set flag");
            Scanner scan = new Scanner(System.in);
            String action = scan.next();
            try {
                switch (action) {
                    case "1" -> {
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
                        manager.uncover(y, x);
                        if (manager.getFieldByCoordinates(y, x).getStatus() == Field.FieldStatus.BOMB) {
                            gameover = true;
                            System.out.println("idiot");
                        }
                    }
                    case "2" -> {
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
                        manager.placeOrRemoveFlag(y,x);
                    }
                    default -> System.out.println("Please choose from the options above");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(manager.checkWin()){
                manager.fieldOutput();
                System.out.println("\nYou won");
                break;
            }
        }while(!gameover);
 }
}
