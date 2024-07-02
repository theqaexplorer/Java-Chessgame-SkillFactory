public class Horse extends ChessPiece {

    // Constructor
    public Horse(String color) {
        super(color); // Call parent constructor
    }

    // Return horse color
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     Check if the horse can move to provided position
     Check provided target position is within board boundaries
     Check provided target position is not same as the current position
     Horse can move only as "L" shape:
     2 squares up and 1 square left,            2 squares up and 1 square right,
     2 squared down and 1 square left,          2 squares up and 1 square right,
     1 square up and 2 squares to the left,     1 square up and 2 squares to the right,
     1 square down and 2 squares to the left,   1 square down and 2 squares to the right

     If Horse can move from position (line, column) to position (toLine, toColumn)
     following the rules, method returns true, else false
    */

    // Check if the horse can move to the provided position
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Ensure the provided position and target position are within board boundaries
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column) &&
                chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {

            // Check that the target position is not the same as the current position
            if (line != toLine || column != toColumn) {
                // Get the piece at the target position
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

                // Check if the target position is either empty or occupied by an opponent's piece
                if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {

                    // Define the possible moves for the horse in an "L" shape
                    int[][] possibleMoves = {
                            // Moving 2 squares up and 1 square to the left
                            {line - 2, column - 1},

                            // Moving 2 squares up and 1 square to the right
                            {line - 2, column + 1},

                            // Moving 2 squares down and 1 square to the left
                            {line + 2, column - 1},

                            // Moving 2 squares down and 1 square to the right
                            {line + 2, column + 1},

                            // Moving 1 square up and 2 squares to the left
                            {line - 1, column - 2},

                            // Moving 1 square up and 2 squares to the right
                            {line - 1, column + 2},

                            // Moving 1 square down and 2 squares to the left
                            {line + 1, column - 2},

                            // Moving 1 square down and 2 squares to the right
                            {line + 1, column + 2}
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


    // Implementation of getSymbol method
    @Override
    public String getSymbol() {
        return "H"; // Symbol representing the horse piece
    }
}
