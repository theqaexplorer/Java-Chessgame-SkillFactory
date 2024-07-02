public class Pawn extends ChessPiece {

    // Constructor
    public Pawn(String color) {
        super(color); // Call the parent constructor
    }

    // Return the color of the pawn
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     Check if the pawn can move to the provided position
     - Ensure the provided target position is within board boundaries
     - Ensure the target position is not the same as the current position
     - Pawns can only move forward
     - White pawns move from lower to higher lines (up the board)
     - Black pawns move from higher to lower lines (down the board)
     - On their first move, pawns can move two squares forward
     - Subsequent moves can only be one square forward
     - Pawns cannot move sideways or backwards
     - Pawns cannot move to a square occupied by a piece of the same color

     If a pawn can move from (line, column) to (toLine, toColumn) according to these rules, the method returns true, else false.
    */

    // Check if the pawn can move to the provided position
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Ensure the provided position and target position are within board boundaries
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column) &&
                chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {

            // Ensure the target position is not the same as the current position
            if (line != toLine || column != toColumn) {
                // Get the piece at the target position
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

                // Define movement rules for the pawn
                // White pawns move up the board (line increases)
                if (this.color.equals("White")) {
                    // Check if the pawn is moving straight forward (not capturing)
                    if (column == toColumn) {
                        // Check if the pawn moves one square forward
                        if (toLine == line + 1 && targetPiece == null) {
                            return true;
                        }
                        // Check if the pawn moves two squares forward from its initial position
                        if (line == 1 && toLine == line + 2 && targetPiece == null &&
                                chessBoard.board[line + 1][toColumn] == null) {
                            return true;
                        }
                    }
                    // Check if the pawn is capturing an opponent's piece diagonally
                    if (toLine == line + 1 && (toColumn == column + 1 || toColumn == column - 1)) {
                        if (targetPiece != null && !targetPiece.getColor().equals(this.color)) {
                            return true;
                        }
                    }
                }

                // Black pawns move down the board (line decreases)
                if (this.color.equals("Black")) {
                    // Check if the pawn is moving straight forward (not capturing)
                    if (column == toColumn) {
                        // Check if the pawn moves one square forward
                        if (toLine == line - 1 && targetPiece == null) {
                            return true;
                        }
                        // Check if the pawn moves two squares forward from its initial position
                        if (line == 6 && toLine == line - 2 && targetPiece == null &&
                                chessBoard.board[line - 1][toColumn] == null) {
                            return true;
                        }
                    }
                    // Check if the pawn is capturing an opponent's piece diagonally
                    if (toLine == line - 1 && (toColumn == column + 1 || toColumn == column - 1)) {
                        if (targetPiece != null && !targetPiece.getColor().equals(this.color)) {
                            return true;
                        }
                    }
                }
            }
        }
        // Return false if any of the conditions are not met
        return false;
    }

    // Implementation of the getSymbol method
    @Override
    public String getSymbol() {
        return "P"; // Symbol representing the pawn piece
    }
}
