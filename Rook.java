public class Rook extends ChessPiece {

    // Constructor to initialize the rook with a color
    public Rook(String color) {
        super(color); // Call the parent constructor to set the color
    }

    // Return the color of the rook
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     Check if the rook can move to the provided position
     - Ensure the provided target position is within board boundaries
     - Ensure the target position is not the same as the current position
     - Rooks can only move in straight lines (horizontally or vertically)
     - If the rook can move from (line, column) to (toLine, toColumn) according to these rules, the method returns true, else false.
    */

    // Check if the rook can move to the provided position
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

                    // Check if the move is along a straight line (either same line or same column)
                    if (line == toLine || column == toColumn) {

                        // Check if there are no pieces blocking the straight path
                        if (line == toLine) { // Moving horizontally
                            int minColumn = Math.min(column, toColumn);
                            int maxColumn = Math.max(column, toColumn);
                            for (int c = minColumn + 1; c < maxColumn; c++) {
                                if (chessBoard.board[line][c] != null) {
                                    // There is a piece blocking the path
                                    return false;
                                }
                            }
                        } else { // Moving vertically
                            int minLine = Math.min(line, toLine);
                            int maxLine = Math.max(line, toLine);
                            for (int l = minLine + 1; l < maxLine; l++) {
                                if (chessBoard.board[l][column] != null) {
                                    // There is a piece blocking the path
                                    return false;
                                }
                            }
                        }

                        // If no pieces block the straight path, the move is valid
                        return true;
                    }
                }
            }
        }
        // Return false if any of the conditions are not met
        return false;
    }


    // Return the symbol representing the rook
    @Override
    public String getSymbol() {
        return "R"; // Symbol representing the rook piece
    }
}
