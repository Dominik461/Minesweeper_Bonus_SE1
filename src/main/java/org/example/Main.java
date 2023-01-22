package org.example;

import java.util.Scanner;

/**
 * Receives user input and interacts with the user
 */
public class Main {
    /**
     * Size of the game board.
     */
    final static int SIZE = 10;
    /**
     * How many bombs there are in a game.
     */
    final static int BOMBS = 15;
    /**
     * 2D array of ints where the locations of each bomb is stored.
     * <p>
     * <code>bombLocations[0][a]</code> equals the y coordinate &#38;
     * <code>bombLocations[1][a]</code> equals the x coordinate
     * y &#38; x are stored at the same location in the example at »a«
     * </p>
     */
    public static int[][] bombLocations = new int[2][BOMBS];

    /**
     * Here the main game loop happens.
     * <p>
     *     The player is first asked if he wants to uncover a field or place a flag. If it is the first turn the player can only uncover a field.
     * </p>
     * <p>
     *     After the player chose a mode they need to enter first the y coordinate of the field he wants to act on and the x coordinate after that.
     * </p>
     * <p>
     *     Then we check if the player chose a valid option for the mode. If not a massage will tell him that only »1« and »2« are valid options.
     * </p>
     * <p>
     *     If they chose to uncover a field, the uncover method of the GameManager is called. If the player hit a bomb, they will get shown all bombs that have not been flagged and a information will show up to tell them the game is over.
     * </p>
     * <p>
     *     If they chose to place a flag, the placeOrRemoveFlag method of the GameManager is called.
     * </p>
     * <p>
     *     At the end of each loop we check with the checkWin method of the GamaManager if the player has won.
     * </p>
     * @param args Yet unused
     */
    public static void main( String[] args ) {
        GameManager manager = new GameManager();

        String testInput;
        int x,y;
        Field [][] fieldArray = new Field[SIZE][SIZE];
        boolean gameover = false, firstMove = true;

        System.out.println("""
                ███╗   ███╗██╗███╗   ██╗███████╗███████╗██╗    ██╗███████╗███████╗██████╗ ███████╗██████╗\s
                ████╗ ████║██║████╗  ██║██╔════╝██╔════╝██║    ██║██╔════╝██╔════╝██╔══██╗██╔════╝██╔══██╗
                ██╔████╔██║██║██╔██╗ ██║█████╗  ███████╗██║ █╗ ██║█████╗  █████╗  ██████╔╝█████╗  ██████╔╝
                ██║╚██╔╝██║██║██║╚██╗██║██╔══╝  ╚════██║██║███╗██║██╔══╝  ██╔══╝  ██╔═══╝ ██╔══╝  ██╔══██╗
                ██║ ╚═╝ ██║██║██║ ╚████║███████╗███████║╚███╔███╔╝███████╗███████╗██║     ███████╗██║  ██║
                ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚══════╝╚══════╝ ╚══╝╚══╝ ╚══════╝╚══════╝╚═╝     ╚══════╝╚═╝  ╚═╝
                                                                                                         \s
                """);
        do {
            x=-1;
            y=-1;

            manager.fieldOutput(fieldArray);
            System.out.println("\n\nPlease choose a mode: ");
            System.out.println("1: Uncover field");
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
                            manager.showBombs(fieldArray);
                            System.out.println("\nGame Over, idiot");
                        }
                    }
                    case "2" ->
                        fieldArray = manager.placeOrRemoveFlag(y,x, fieldArray, bombLocations);

                    default -> System.out.println("Please only enter >1< or >2< for a mode.");
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
