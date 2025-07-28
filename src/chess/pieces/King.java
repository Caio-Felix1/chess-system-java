package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != this.getColor();
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
		
		// verificando a esquerda da peça
		p.setValues(this.position.getRow(), this.position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
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
		}
		
		// verificando a diagonal superior direita da peça
		p.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
		if (getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || this.isThereOpponentPiece(p))) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

}
