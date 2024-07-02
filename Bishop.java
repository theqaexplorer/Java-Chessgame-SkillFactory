public class Bishop extends ChessPiece {

    // Constructor
    public Bishop(String color) {
        super(color); // Call the parent constructor
    }

    // Return the color of the bishop
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     Check if the bishop can move to the provided position
     - Ensure the provided target position is within board boundaries
     - Ensure the target position is not the same as the current position
     - Bishops can only move diagonally
     - If the bishop can move from (line, column) to (toLine, toColumn) according to these rules, the method returns true, else false.
    */

    // Check if the bishop can move to the provided position
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Ensure the provided position and target position are within board boundaries
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column) &&
                chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {

            // Ensure the target position is not the same as the current position
            if (line != toLine || column != toColumn) {
                // Get the piece at the target position
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

                // Check if the target position is empty or contains an opponent's piece
                if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {

                    // Check if the move is diagonal (same absolute difference between line and toLine, column and toColumn)
                    if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
                        // Check if there are no pieces blocking the diagonal path
                        int deltaLine = (toLine - line) > 0 ? 1 : -1; // Determine the direction of movement along the rows
                        int deltaColumn = (toColumn - column) > 0 ? 1 : -1; // Determine the direction of movement along the columns

                        int checkLine = line + deltaLine; // Start checking from the next cell in the diagonal path
                        int checkColumn = column + deltaColumn;

                        while (checkLine != toLine && checkColumn != toColumn) {
                            if (chessBoard.board[checkLine][checkColumn] != null) {
                                // There is a piece blocking the diagonal path
                                return false;
                            }
                            checkLine += deltaLine;
                            checkColumn += deltaColumn;
                        }

                        // If no pieces block the diagonal path, the move is valid
                        return true;
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
        return "B"; // Symbol representing the bishop piece
    }
}
