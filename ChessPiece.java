public abstract class ChessPiece {
    String color;
    boolean check;

    // Constructor
    public ChessPiece(String color) {
        this.color = color;
        this.check = true; // set to true by default
    }

    public abstract String getColor(); //return the color of the piece

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn); //check if the piece can move to provided position on the board

    public abstract String getSymbol(); //return represantation of the piece
}