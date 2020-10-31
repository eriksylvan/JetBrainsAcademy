package tictactoe;

public class MinMax {


    static int evaluate(Board board, char maximizersTile, char miniminimizersTile ) {
        char b = board.checkWinner();
        int e = 0;
        if (b == maximizersTile)
            e = 10; // assuming X is maximizers
        else if (b == miniminimizersTile)
            e = -10; // assuming Y is opponent

//        if (maximizersTile == 'Y')
//            e = (e * -1); // wrong asumption, correct the evaluation value
        return e;
    }

    static int minimax(Board board, int depth, Boolean isMax, char maximizersTile, char minimizersTile) {

        int score = evaluate(board, maximizersTile, minimizersTile);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score - depth;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score + depth;

        // If there are no more moves and
        // no winner then it is a tie
        if (board.isMovesLeft() == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    // Check if cell is empty
                    if (board.getCellContent(i, j) == '_') {

                        // Make the move
                        board.setTile(i, j, maximizersTile);

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board, depth + 1, !isMax, maximizersTile, minimizersTile));

                        // Undo the move
                        board.setTile(i, j, '_');
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    // Check if cell is empty
                    if (board.getCellContent(i, j) == '_') {
                        // Make the move
                        board.setTile(i, j, minimizersTile);

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board, depth + 1, !isMax, maximizersTile, minimizersTile));

                        // Undo the move
                        board.setTile(i, j, '_');
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
// position for the player
    static Pos findBestMove(Board board, char playerTile, char oponentsTile) {
        int bestVal = -1000;
        int bestX = -1;
        int bestY = -1;


        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                // Check if cell is empty
                if (board.getCellContent(i, j) == '_') {
                    // Make the move
                    board.setTile(i,j, playerTile);

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false, playerTile, oponentsTile);

                    // Undo the move
                    board.setTile(i,j, '_');

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestX = i;
                        bestY = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return new Pos(bestX,bestY);
    }

}
