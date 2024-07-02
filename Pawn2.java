public class Pawn2 extends ChessPiece {

    // Constructor
    public Pawn2(String color) {
        super(color); // Call parent constructor
    }

    // Return pawn color
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     Check if the pawn can move to the provided position
     The provided target position must be within board boundaries
     The provided target position must not be the same as the current position

     Pawn can move forward only:
     - White pawns (color == "White") move from lower rows to higher rows (increasing line number)
     - Black pawns (color == "Black") move from higher rows to lower rows (decreasing line number)

     - On the first move, a pawn can move forward two squares if:
       - White pawn is at line 1 and moves to line 3
       - Black pawn is at line 6 and moves to line 4

     - Pawn can only move one square forward otherwise:
       - White pawn moves from (line, column) to (line + 1, column)
       - Black pawn moves from (line, column) to (line - 1, column)

     - Pawns can only attack diagonally forward by one square
       - White pawn attacks to (line + 1, column - 1) or (line + 1, column + 1)
       - Black pawn attacks to (line - 1, column - 1) or (line - 1, column + 1)

     If the pawn can move from position (line, column) to position (toLine, toColumn)
     following these rules, the method returns true, else false.
    */

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Ensure the provided position is within board boundaries
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column) &&
                chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {

            // Check that the target position is not the same as the current position
            if (line != toLine || column != toColumn) {
                // Determine movement direction and initial line based on pawn color
                int direction = this.color.equals("White") ? 1 : -1; // White moves up, Black moves down
                int startLine = this.color.equals("White") ? 1 : 6; // Initial line for white and black pawns

                // Check if the move is a standard single step forward
                if (column == toColumn) {
                    // Moving one square forward
                    if (line + direction == toLine) {
                        return chessBoard.board[toLine][toColumn] == null;
                    }

                    // Moving two squares forward from the initial position
                    if (line == startLine && line + 2 * direction == toLine) {
                        return chessBoard.board[toLine][toColumn] == null &&
                                chessBoard.board[line + direction][column] == null;
                    }
                } else if (Math.abs(column - toColumn) == 1 && line + direction == toLine) {
                    // Moving one square diagonally forward (attacking)
                    ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
                    return targetPiece != null && !targetPiece.getColor().equals(this.color);
                }
            }
        }
        // Return false if any conditions are not met
        return false;
    }

    // Implementation of getSymbol method
    @Override
    public String getSymbol() {
        return "P"; // Symbol representing the pawn piece
    }
}
