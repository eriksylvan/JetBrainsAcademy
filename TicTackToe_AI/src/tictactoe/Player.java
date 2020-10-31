package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    static Scanner scanner = new Scanner(System.in);
    protected char tile;
    protected char opponentsTile;


    public Player(char tile) {
        this.tile = tile;
        if (tile == 'X')
            this.opponentsTile = 'O';
        else
            this.opponentsTile = 'X';

    }


    void playTurn(Board board) {
    }

    ;
}


class CumputerEasy extends Player {

    public CumputerEasy(char tile) {
        super(tile);
        System.out.println("Creating computer easy");
    }

    public void playTurn(Board board) {
        System.out.println("Making move level \"easy\":");
        Pos pos = board.pickRandomFreePosition();
        board.setTile(pos.getX(), pos.getY(), tile);
    }
}


class CumputerMedium extends Player {

    public CumputerMedium(char tile) {
        super(tile);
    }
}


class CumputerHard extends Player {

    public CumputerHard(char tile) {
        super(tile);
    }

    public void playTurn(Board board) {
        Pos bestPos;
        bestPos = MinMax.findBestMove(board, this.tile, this.opponentsTile);
        board.setTile(bestPos.getX(), bestPos.getY(), this.tile);
    }
}


class HumanUser extends Player {

    public HumanUser(char tile) {
        super(tile);
    }

    public void playTurn(Board board) {
        Boolean retry = true;
        do {
            System.out.println("Enter the coordinates:");
            try {
                int inpX = scanner.nextInt();
                int inpY = scanner.nextInt();
                if (inpX < 1 || inpX > 3 || inpY < 1 || inpY > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (board.getCellContent(inpX, inpY) == '_') {
                        board.setTile(inpX, inpY, tile);
                        retry = false;
                    } else
                        System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (
                    InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
        while (retry);
    }
}