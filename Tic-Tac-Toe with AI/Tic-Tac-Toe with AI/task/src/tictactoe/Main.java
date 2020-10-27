package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static String board = "";
    static char userTile = ' ';

    public static void main(String[] args) {
        System.out.println("Enter cells:");
        String inp = scanner.next();
        board = inp;
        decideUserTine();
        boolean exit = false;

        drawBoard();
        while (!exit) {
            System.out.println("Enter the coordinates:");
            try {

                int inpX = scanner.nextInt();
                int inpY = scanner.nextInt();
                if (inpX < 1 || inpX > 3 || inpY < 1 || inpY > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (getCellContent(inpX, inpY) == '_') {
                        setTile(inpX, inpY, userTile);
                        exit = true;
                    } else
                        System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
        drawBoard();
        checkState();
    }

    private static void decideUserTine() {
        int x = 0, o = 0;
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == 'X') {
                x++;
            } else if (board.charAt(i) == 'O') {
                o++;
            }

        }
        if (x == o) {
            userTile = 'X';
        } else {
            userTile = 'O';
        }
        // System.out.println("User is " + userTile);
    }

    private static void drawBoard() {
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

    private static char getCellContent(int x, int y) {
        char cellContent = board.charAt(3 * (3 - y) + x - 1);
        return cellContent;
    }

    private static void setTile(int x, int y, char tile) {
        if (getCellContent(x, y) == '_') {
            StringBuilder str = new StringBuilder(board);
            str.setCharAt((3 * (3 - y) + x - 1), tile);
            board = str.toString();
        } else
            System.out.println("This cell is occupied! Choose another one!");
    }

    private static void checkState(){
        if (board.substring(0,3).equals("XXX") ||
                board.substring(3,6).equals("XXX") ||
                board.substring(6).equals("XXX") ||
                (board.charAt(0) == 'X' && board.charAt(3) =='X' && board.charAt(6) == 'X') ||
                (board.charAt(1) == 'X' && board.charAt(4) =='X' && board.charAt(7) == 'X') ||
                (board.charAt(2) == 'X' && board.charAt(5) =='X' && board.charAt(8) == 'X') ||
                (board.charAt(0) == 'X' && board.charAt(4) =='X' && board.charAt(8) == 'X') ||
                (board.charAt(2) == 'X' && board.charAt(4) =='X' && board.charAt(6) == 'X')){
            System.out.println("X wins");
        }
        else if (board.substring(0,3).equals("OOO") ||
                    board.substring(3,4).equals("OOO") ||
                    board.substring(6).equals("OOO") ||
                    (board.charAt(0) == 'O' && board.charAt(3) =='O' && board.charAt(6) == 'O') ||
                    (board.charAt(1) == 'O' && board.charAt(4) =='O' && board.charAt(7) == 'O') ||
                    (board.charAt(2) == 'O' && board.charAt(5) =='O' && board.charAt(8) == 'O') ||
                    (board.charAt(0) == 'O' && board.charAt(4) =='O' && board.charAt(8) == 'O') ||
                    (board.charAt(2) == 'O' && board.charAt(4) =='O' && board.charAt(6) == 'O')){
                System.out.println("O wins");
            }
        else if (!board.contains("_")) {
            System.out.println("Draw");
        }
        else{
            System.out.println("Game not finished");
        }
    }
}
