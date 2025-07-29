package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		// verificando as casas acima da peça
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas da diagonal superior esquerda da peça
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);;
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas à esquerda da peça
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas da diagonal inferior esquerda da peça
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);;
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas abaixo da peça
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);;
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas da diagonal inferior direita da peça
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);;
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas à direita da peça
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// verificando as casas da diagonal superior direita da peça
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);;
		}
		if (getBoard().positionExists(p) && this.isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}
}
