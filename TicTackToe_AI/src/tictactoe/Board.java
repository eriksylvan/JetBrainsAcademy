package tictactoe;

import static tictactoe.Main.rand;

final class Pos {
    private final int x;
    private final int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Board {
    static String board;

    public Board() {
        board = "_________";
    }

    public Board(String setBoard) {
        if (setBoard.length() == 9)
            board = setBoard;
        else {
            System.out.println("Incorrect board");
            board = "_________";
        }
    }

    public static void drawBoard() {
        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if ((i) % 3 == 0) {
                System.out.print("|");
            }
            System.out.print(" ");
            if (board.charAt(i) != '_')
                System.out.print(board.charAt(i));
            else
                System.out.print(' ');
            if ((i + 1) % 3 == 0) {
                System.out.println(" |");
            }
        }
        System.out.println("---------");
    }

    public static char getCellContent(int x, int y) {
        char cellContent = board.charAt(3 * (3 - y) + x - 1);
        return cellContent;
    }

    public static void setTile(int x, int y, char tile) {
        StringBuilder str = new StringBuilder(board);
        str.setCharAt((3 * (3 - y) + x - 1), tile);
        board = str.toString();
    }


    public Pos pickRandomFreePosition() {
        int randX, randY;
        //TODO: check if free position exists
        do {
            randX = rand.nextInt(3) + 1;
            randY = rand.nextInt(3) + 1;
        }
        while (getCellContent(randX, randY) != '_');

        return new Pos(randX, randY);
    }

    public Boolean checkWinner() {
        Boolean gameFinished;
        if (board.substring(0, 3).equals("XXX") ||
                board.substring(3, 6).equals("XXX") ||
                board.substring(6).equals("XXX") ||
                (board.charAt(0) == 'X' && board.charAt(3) == 'X' && board.charAt(6) == 'X') ||
                (board.charAt(1) == 'X' && board.charAt(4) == 'X' && board.charAt(7) == 'X') ||
                (board.charAt(2) == 'X' && board.charAt(5) == 'X' && board.charAt(8) == 'X') ||
                (board.charAt(0) == 'X' && board.charAt(4) == 'X' && board.charAt(8) == 'X') ||
                (board.charAt(2) == 'X' && board.charAt(4) == 'X' && board.charAt(6) == 'X')) {
            System.out.println("X wins");
            gameFinished = true;
        } else if (board.substring(0, 3).equals("OOO") ||
                board.substring(3, 4).equals("OOO") ||
                board.substring(6).equals("OOO") ||
                (board.charAt(0) == 'O' && board.charAt(3) == 'O' && board.charAt(6) == 'O') ||
                (board.charAt(1) == 'O' && board.charAt(4) == 'O' && board.charAt(7) == 'O') ||
                (board.charAt(2) == 'O' && board.charAt(5) == 'O' && board.charAt(8) == 'O') ||
                (board.charAt(0) == 'O' && board.charAt(4) == 'O' && board.charAt(8) == 'O') ||
                (board.charAt(2) == 'O' && board.charAt(4) == 'O' && board.charAt(6) == 'O')) {
            System.out.println("O wins");
            gameFinished = true;
        } else if (!board.contains("_")) {
            System.out.println("Draw");
            gameFinished = true;
        } else {
            //System.out.println("Game not finished");
            gameFinished = false;
        }
        return gameFinished;
    }
}


