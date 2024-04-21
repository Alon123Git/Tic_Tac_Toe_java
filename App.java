import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in); // scanner for get input
    static char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8','9'}}; // 2D chars for thr board
    static char O = 'O', X = 'X'; // 2 chars - 'O', 'X'
    static int i = 0, j = 0, scoreO = 0, scoreX = 0, choice = 0, newGame = 0, whoBegin = 0; // indexes, scores, choice for position the char, more game, who start the game
    static boolean isTurn = false, end = false, isBegin = false, gameStarted = true; // woh is turn is, is the game end, is the game began, is game started 

    /**
     * 31 lines
     * main method
     * @param - string array
     * @return void
     */
    public static void main(String[] args) {
        while (true) {
            if (gameStarted) {
                isBegin = isStart(board, i, j, isTurn);
                if (isBegin) {
                    isTurn = true;
                }
                gameStarted = false;
            }
            choosePos(board, choice, isTurn, O, X, i, j); // call method for the player that have the turn to choose position
            printBoard(board, i, j); // Print the board after each move
            end = isGameOver(board, O, X, i, j); // Check for game over after each move
            if (end) {
                if (OWin(board, O) == 1) {
                    System.out.println("O won");
                    scoreO++;
                } else if (XWin(board, X) == 2) {
                    System.out.println("X won");
                    scoreX++;
                } else {
                    System.out.println("the game is tie");
                }
                System.out.println("the score is:\n0 - " + scoreO + " \nX - " + scoreX);
                if (moreGame(newGame) == 2)
                    break;
                resetBoard(board); // Reset the board for a new game
                gameStarted = true;
            }
            isTurn = !isTurn; // Change turn after each player is move
        }
        System.out.println("game over");
    }
    
    // public static void main(String[] args) {
    //     while (true) {
    //         if (gameStarted) {
    //             isBegin = isStart(board, i, j, isTurn);
    //             if (isBegin) {
    //                 isTurn = true;
    //             }
    //             gameStarted = false; 
    //         }
    //         choosePos(board, choice, isTurn, O, X, i, j); // call method to choose position for the player that have the turn
    //         isTurn = !isTurn; // change turn
    //         //printBoard(board, i, j);
    //         end = isGameOver(board, O, X, i, j); // store true if game over, else false in a boolean variable
    //         if (end) {
    //             if (OWin(board, O) == 1) { // if O win 
    //                 System.out.println("O won");
    //                 scoreO++; // increase by 1 score for O
    //             } else if (XWin(board, X) == 2) { // if X win
    //                 System.out.println("X won");
    //                 scoreX++; // increase by 1 score for X
    //             }
    //             System.out.println("the score is:\n0 - " + scoreO + " \nX - " + scoreX);
    //         if (moreGame(newGame) == 2) // if method moreGame return 2
    //             break; 
    //             resetBoard(board); // call method to print the board
    //             gameStarted = true;
    //         }
            
    //     }
    //     System.out.println("game over");
    // }

    /**
     * 12 lines
     * @param board - 2D array of chars that represent the board
     * @param i - index
     * @param j - index
     * @param isTurn - represent who turn is
     * @return true if game started, else false
     */
    private static boolean isStart(char[][] board, int i, int j, boolean isTurn) {
        System.out.println("O start - 1\nX start - 2");
        whoBegin = scanner.nextInt();
        while (whoBegin < 1 || whoBegin > 2) {
            System.out.println("enter a valid answer");
            System.out.println("O start - 1\nX start - 2");
            whoBegin = scanner.nextInt();
        }
        printBoard(board, i, j); // call method to print the board
        if (whoBegin == 1)
            return true;
        return false;
    }

    /**
     * 8 lines
     * @param board - 2D array of chars that represent the board
     * @param O - char that represent O
     * @return = 1 if O win, else 0
     */
    private static int OWin(char[][] board, char O) {
        if (board[0][0] == O && board[0][1] == O &&board[0][2] == O ||
        board[1][0] == O && board[1][1] == O && board[1][2] == O ||
        board[2][0] == O && board[2][1] == O && board[2][2] == O ||
        board[0][0] == O && board[1][1] == O && board[2][2] == O ||
        board[0][2] == O && board[1][1] == O && board[2][0] == O) {
            return 1;
        }
        return 0;
    }

    /**
     * 8 lines
     * @param board - 2D array of chars that represent the board
     * @param X - char that represent X
     * @return - 1 if X win, else 0
     */
    private static int XWin(char[][] board, char X) {
        if (board[0][0] == X && board[0][1] == X &&board[0][2] == X ||
        board[1][0] == X && board[1][1] == X && board[1][2] == X ||
        board[2][0] == X && board[2][1] == X && board[2][2] == X ||
        board[0][0] == X && board[1][1] == X && board[2][2] == X ||
        board[0][2] == X && board[1][1] == X && board[2][0] == X) {
            return 2;
        }
        return 0;
    }

    /**
     * 9 lines
     * @param board - 2D array of chars that represent the board
     * @param O - char that represent O
     * @param X - char that represent X
     * @param i - index
     * @param j - index
     * @return - true if the game is tie, else false
     */
    private static boolean isTie(char[][] board, char O, char X, int i, int j) {
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (board[i][j] != O && board[i][j] != X) 
                    return false;
            }
        }
        System.out.println("the game is tie");
        return true;
    }

    /**
     * 4 lines
     * @param board - 2D array of chars that represent the board
     * @param O - char that represent O
     * @param X - char that represent X
     * @param i - index
     * @param j - index
     * @return - true if game over, else false
     */
    private static boolean isGameOver(char[][] board, char O, char X, int i, int j) {
        if (OWin(board, O) == 1 || XWin(board, X) == 2 || isTie(board, O, X, i, j))
            return true;
        return false;
    }

    /**
     * 34 lines
     * @param board - 2D array of chars that represent the board
     * @param choice
     * @param isTurn
     * @param O - char that represent O
     * @param X - char that represent X
     * @param i - index
     * @param j - index
     * @return - void
     */
    private static void choosePos(char[][] board, int choice, boolean isTurn, char O, char X, int i, int j) {
        System.out.print("enter pos ");
        choice = scanner.nextInt();
        while (choice < 1 || choice > 9 || isPositionTaken(board, choice)) {
            System.out.println("Invalid position or position taken\nenter position\n");
            printBoard(board, i, j); // call method to print the board     
            choice = scanner.nextInt();
        }
        // while (choice < 1 || choice > 9) {
        //     System.out.println("enter a valid pos\nenter position");
        //     choice = scanner.nextInt();
        // }
        
        if (isTurn) {
            if (choice == 1) board[0][0] = O;
            if (choice == 2) board[0][1] = O;
            if (choice == 3) board[0][2] = O;
            if (choice == 4) board[1][0] = O;
            if (choice == 5) board[1][1] = O;
            if (choice == 6) board[1][2] = O;
            if (choice == 7) board[2][0] = O;
            if (choice == 8) board[2][1] = O;
            if (choice == 9) board[2][2] = O;
        } else  {
            if (choice == 1) board[0][0] = X;
            if (choice == 2) board[0][1] = X;
            if (choice == 3) board[0][2] = X;
            if (choice == 4) board[1][0] = X;
            if (choice == 5) board[1][1] = X;
            if (choice == 6) board[1][2] = X;
            if (choice == 7) board[2][0] = X;
            if (choice == 8) board[2][1] = X;
            if (choice == 9) board[2][2] = X;
        }
        printBoard(board, i, j); // call method to print the board
    }

    // Helper method to check if a position is taken
    private static boolean isPositionTaken(char[][] board, int choice) {
        int row = (choice - 1) / 3;
        int col = (choice - 1) % 3;
        return (board[row][col] == 'O' || board[row][col] == 'X');
    }

    /**
     * 8 lines
     * @param board - 2D array of chars that represent the board
     * @param i - index
     * @param j - index
     * @return - void
     */
    private static void printBoard(char[][] board, int i, int j) {
            for (i = 0; i < board.length; i++) {
                for (j = 0; j < 3; j++) {
                    System.out.print("\t" + board[i][j]);
            } 
            System.out.println("\n");
        }
    }

    // 10 lines
    /**
     * 10 lines
     * @param board - 2D array of chars that represent the board
     * @return - void
     */
    private static void resetBoard(char[][] board) {
            board[0][0] = '1';
            board[0][1] = '2';
            board[0][2] = '3';
            board[1][0] = '4';
            board[1][1] = '5';
            board[1][2] = '6';
            board[2][0] = '7';
            board[2][1] = '8';
            board[2][2] = '9';
    }

    /**
     * 13 lines
     * @param newGame - represent if will be more game
     * @return - 1 if will be more game, else 2
     */
    private static int moreGame(int newGame) {
            System.out.println("Another game?\nyes - 1\nno - 2");
            newGame = scanner.nextInt();
            while (newGame < 1 || newGame > 2) {
                System.out.println("enter a valid answer");
                System.out.println("Another game?\nyes - 1\nno - 2");
                newGame = scanner.nextInt();
            }
            if (newGame == 1) {
            resetBoard(board); // call method for send the board to starting point
                return 1;
            }
        return 2;
    }
}