package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public enum GameState {
        Init,
        Turn,
        CheckWinner,
        Exit
    }

    static Player PlayerOne;
    static Player PlayerTwo;

    static GameState gameState = GameState.Init;


    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    static int playerTurn = 1; //Start player 1s


    public static void main(String[] args) {
    Boolean exit = false;
    Board board = new Board();

        while (!exit) {
            System.out.println(gameState);
            switch (gameState) {
                case Init:
                    System.out.println("State Init");
                    readStartInput();
                    break;
                case Turn:
                    System.out.println("State Turn");
                    board.drawBoard();
                    if (playerTurn == 1) {
                        PlayerOne.playTurn(board);
                        playerTurn = 2;
                    }
                    else {
                        PlayerTwo.playTurn(board);
                        playerTurn = 1;
                    }

                    gameState = GameState.CheckWinner;
                    break;

                case CheckWinner:
                    System.out.println("State Check winner");
                    if (board.checkWinner()) {
                        board.drawBoard();
                        gameState = GameState.Exit;
                    }
                    else
                        gameState = GameState.Turn;
                    break;

                case Exit:
                    System.out.println("State Exit");
                    exit = true;
                    break;

            }
        }
    }


    protected static void readStartInput() {
        System.out.println("Input command:");
        String[] parameters = scanner.nextLine().split(" ");

        if (parameters.length == 1) {
            System.out.println("exit");
            System.out.println(parameters[0]);
            if (parameters[0].equals("exit")) {
                gameState = GameState.Exit;
                System.out.println(gameState);
                return;
            }
        }
        if (parameters.length == 3) {
            String command = parameters[0];
            String player1 = parameters[1];
            String player2 = parameters[2];
            if (!(command.equals("start") || command.equals("exit"))) {
                System.out.println("Bad parameters! 1");
                gameState = GameState.Init;
                return;
            }
            if (!(player1.equals("easy") || player1.equals("medium") || player1.equals("hard")|| player1.equals("user"))) {
                System.out.println("Bad parameters! 2");
                gameState = GameState.Init;
                return;
            }
            if (!(player2.equals("easy") || player2.equals("medium") || player2.equals("hard") || player2.equals("user"))) {
                System.out.println("Bad parameters! 3");
                gameState = GameState.Init;
                return;
            }
            if (player1.equals("easy")) {
                PlayerOne = new CumputerEasy('X');
            } else if (player1.equals("medium")) {
                PlayerOne = new CumputerMedium('X');
            } else if (player1.equals("hard")) {
                PlayerOne = new CumputerHard('X');
            } else if (player1.equals("user")) {
                PlayerOne = new HumanUser('X');
            } else {
                System.out.println("Cound not create player 1");
                return;
            }
            if (player2.equals("easy")) {
                PlayerTwo = new CumputerEasy('O');
            } else if (player2.equals("medium")) {
                PlayerTwo = new CumputerMedium('O');
            } else if (player2.equals("hard")) {
                PlayerTwo = new CumputerHard('O');
            } else if (player2.equals("user")) {
                PlayerTwo = new HumanUser('O');
            } else {
                System.out.println("Cound not create player 2");
                return;
            }
            gameState = GameState.Turn;
        } else {
            System.out.println("Bad parameters! 4");
            gameState = GameState.Init;
        }



    }


}
