import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        final String CHOOSE_BARREL_MSG = "Choose a barrel: barrel1 (one), barrel2 "
                + "(two), or both (both), or skip turn (skip)? ";
        final String BISCUIT_AMOUNT_MSG = "How many biscuits are you taking? ";
        final String INTEGER_ERROR_MSG = "Please input an integer: ";
        final String BISCUIT_AMOUNT_ERROR = "Sorry, that's not a legal number "
                + "of biscuits for that/those barrel(s)";
        final String BISCUITS_LEFT_1 = "Biscuits Left - Barrel 1: ";
        final String BISCUITS_LEFT_2 = "Biscuits Left - Barrel 2: ";
        final String PLAYER_TURN = "Player Turn: ";
        final String SKIP_NOTIFICATION = "Sorry you've used your skip.";
        final String WINNER_MESSAGE = "Winner is player ";

        final int BARREL_ONE_STARTING_NUMBER = 6;
        final int BARREL_TWO_STARTING_NUMBER = 8;

        final int PLAYER_ONE = 1;
        final int PLAYER_TWO = 2;

        int barrelOne = BARREL_ONE_STARTING_NUMBER;
        int barrelTwo = BARREL_TWO_STARTING_NUMBER;
        int round = 1;

        boolean playerOneSkip = false;
        boolean playerTwoSkip = false;
        boolean run = true;

        // Main game loop:
        while (run) {

            boolean playerTwoTurn = false;
            boolean playerOneTurn = false;

            boolean barrelChoiceRun = true;

            String barrelChoice = "";
            String biscuitAmountString;
            int biscuitAmountInt;

            System.out.println(BISCUITS_LEFT_1 + barrelOne);
            System.out.println(BISCUITS_LEFT_2 + barrelTwo);

            // Player turn displayed at top of each round:
            if (round % 2 == 0) {
                System.out.println(PLAYER_TURN + PLAYER_TWO);
                playerTwoTurn = true;
            } else {
                System.out.println(PLAYER_TURN + PLAYER_ONE);
                playerOneTurn = true;
            }

            // Barrel choice validation (Prints twice for some reason, need to fix):
            while (barrelChoiceRun) {

                System.out.print(CHOOSE_BARREL_MSG);
                barrelChoice = inputScanner.nextLine();

                if (barrelChoice.equals("one") || barrelChoice.equals("two")
                        || barrelChoice.equals("both")){
                    boolean intValidation = true;
                    do {
                        System.out.print(BISCUIT_AMOUNT_MSG);
                        while (!inputScanner.hasNextInt()) {
                            String input = inputScanner.nextLine();
                            System.out.print(INTEGER_ERROR_MSG);
                        }
                        biscuitAmountString = inputScanner.nextLine();
                        biscuitAmountInt=Integer.parseInt(biscuitAmountString);
                        intValidation = false;

                    } while (intValidation);

                    if (barrelChoice.equals("one") && biscuitAmountInt
                            <= barrelOne && biscuitAmountInt > 0) {
                        barrelOne = barrelOne - biscuitAmountInt;
                        barrelChoiceRun = false;

                    } else if (barrelChoice.equals("two") && biscuitAmountInt
                            <= barrelTwo && biscuitAmountInt > 0) {
                        barrelTwo = barrelTwo - biscuitAmountInt;
                        barrelChoiceRun = false;

                    } else if (barrelChoice.equals("both") && biscuitAmountInt
                            <= barrelTwo && biscuitAmountInt<=barrelOne
                            && biscuitAmountInt>0) {
                        barrelOne = barrelOne - biscuitAmountInt;
                        barrelTwo = barrelTwo - biscuitAmountInt;
                        barrelChoiceRun = false;

                    } else {
                        System.out.println(BISCUIT_AMOUNT_ERROR);
                    }

                } else if (barrelChoice.equals("skip")) {
                    if (playerOneTurn) {
                        if (!playerOneSkip) {
                            playerOneSkip = true;
                            barrelChoiceRun = false;
                        } else {
                            System.out.println(SKIP_NOTIFICATION);
                        }

                    } else if (playerTwoTurn) {
                        if (!playerTwoSkip) {
                            playerTwoSkip = true;
                            barrelChoiceRun = false;
                        } else {
                            System.out.println(SKIP_NOTIFICATION);
                        }
                    }
                }
            }

            if (barrelOne == 0 && barrelTwo == 0) {

                System.out.println(BISCUITS_LEFT_1 + barrelOne);
                System.out.println(BISCUITS_LEFT_2 + barrelTwo);

                if (round % 2 == 0) {
                    System.out.println(WINNER_MESSAGE + PLAYER_TWO);
                } else {
                    System.out.println(WINNER_MESSAGE + PLAYER_ONE);
                }
                run = false;
            }

            round++;

        }

    }
}


