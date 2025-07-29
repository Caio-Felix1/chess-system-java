package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != this.getColor();
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == this.getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		// verificando acima da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando a diagonal superior esquerda da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		// verificando à esquerda da peça
		p.setValues(this.position.getRow(), this.position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
			// verificando se pode andar duas casas à direita (Special Move - Castling Queenside rook)
			Position p1 = new Position(position.getRow(), position.getColumn() - 2);
			Position p2 = new Position(position.getRow(), position.getColumn() - 3);
			if (getMoveCount() == 0 && !chessMatch.getCheck() && !getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2)) {
				Position postT = new Position(position.getRow(), position.getColumn() - 4);
				if (testRookCastling(postT)) {
					mat[p1.getRow()][p1.getColumn()] = true;
				}
				
			}
		}	
		
		// verificando a diagonal inferior esquerda da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando abaixo da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando a diagonal inferior direita da peça
		p.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando a direita da peça
		p.setValues(this.position.getRow(), this.position.getColumn() + 1);
		if (getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || this.isThereOpponentPiece(p))) {
			mat[p.getRow()][p.getColumn()] = true;
			
			// verificando se pode andar duas casas à direita (Special Move - Castling Kingside rook)
			Position p1 = new Position(position.getRow(), position.getColumn() + 2);
			if (getMoveCount() == 0 && !chessMatch.getCheck() && !getBoard().thereIsAPiece(p1)) {
				Position postT = new Position(position.getRow(), position.getColumn() + 3); 
				if (testRookCastling(postT)) {
					mat[p1.getRow()][p1.getColumn()] = true;
				}
			}
		}
		
		// verificando a diagonal superior direita da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || this.isThereOpponentPiece(p))) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

}
