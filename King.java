public class King extends ChessPiece {

    // Constructor to initialize the king with a color
    public King(String color) {
        super(color); // Call the parent constructor to set the color
    }

    // Return the color of the king
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     * Check if the king can move to the provided position
     * - Ensure the provided target position is within board boundaries
     * - Ensure the target position is not the same as the current position
     * - Kings can move one square in any direction (horizontally, vertically, or diagonally)
     * - If the king can move from (line, column) to (toLine, toColumn) according to these rules, the method returns true, else false.
     */

    // Check if the king can move to the provided position
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Ensure the provided position and target position are within board boundaries
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column) &&
                chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {

            // Check that the target position is different from the current position
            if (line != toLine || column != toColumn) {
                // Get the piece at the target position
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

                // Check if the target position is empty or contains an opponent's piece
                if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {

                    // Define the possible moves for the king (1 square in any direction)
                    int[][] possibleMoves = {
                            // Moving 1 square up
                            {line - 1, column},

                            // Moving 1 square down
                            {line + 1, column},

                            // Moving 1 square to the left
                            {line, column - 1},

                            // Moving 1 square to the right
                            {line, column + 1},

                            // Moving 1 square diagonally up-left
                            {line - 1, column - 1},

                            // Moving 1 square diagonally up-right
                            {line - 1, column + 1},

                            // Moving 1 square diagonally down-left
                            {line + 1, column - 1},

                            // Moving 1 square diagonally down-right
                            {line + 1, column + 1}
                    };

                    // Check if the target position is one of the possible moves
                    for (int[] move : possibleMoves) {
                        if (move[0] == toLine && move[1] == toColumn) {
                            // If the target position matches one of the possible moves, return true
                            return true;
                        }
                    }
                }
            }
        }
        // Return false if any of the conditions are not met
        return false;
    }


    // Return the symbol representing the king
    @Override
    public String getSymbol() {
        return "K"; // Symbol representing the king piece
    }

    /*
     * Check if the position of the king is under attack by any opponent's piece
     * - Check all directions (horizontally, vertically, and diagonally) for opponent's pieces
     * - If the king's position is under attack, return true, otherwise false.
     */

    // Check if the king's position is under attack by any opponent's piece
    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        // Iterate through all positions on the board
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                // Get the piece at the current position
                ChessPiece piece = board.board[l][c];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    // If the piece is an opponent's piece, check if it can move to the king's position
                    if (piece.canMoveToPosition(board, l, c, line, column)) {
                        return true; // King is under attack
                    }
                }
            }
        }
        return false; // King is not under attack
    }
}
