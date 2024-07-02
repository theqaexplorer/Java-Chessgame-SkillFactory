public class Queen extends ChessPiece {

    // Constructor to initialize the queen with a color
    public Queen(String color) {
        super(color); // Call the parent constructor to set the color
    }

    // Return the color of the queen
    @Override
    public String getColor() {
        return this.color;
    }

    /*
     * Check if the queen can move to the provided position
     * - Ensure the provided target position is within board boundaries
     * - Ensure the target position is not the same as the current position
     * - Queens can move in straight lines (horizontally or vertically) and diagonally
     * - If the queen can move from (line, column) to (toLine, toColumn) according to these rules, the method returns true, else false.
     */

    // Check if the queen can move to the provided position
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


    // Return the symbol representing the queen
    @Override
    public String getSymbol() {
        return "Q"; // Symbol representing the queen piece
    }
}