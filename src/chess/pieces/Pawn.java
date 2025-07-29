package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	private boolean testEnPassant(String direction, Color pawnColor) {
		int EnPassantRow = (pawnColor == Color.WHITE) ? 3 : 4;
		
		Position opponentPos;
		if (direction.equals("left")) {
			opponentPos = new Position(position.getRow(), position.getColumn() - 1);
		}
		else {
			opponentPos = new Position(position.getRow(), position.getColumn() + 1);
		}
		return position.getRow() == EnPassantRow && getBoard().positionExists(opponentPos) && isThereOpponentPiece(opponentPos) && getBoard().piece(opponentPos) == chessMatch.getEnPassantVulnerable();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		if (getColor() == Color.WHITE) {
			
			// verificando acima da peça
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				
				// verificando se pode andar duas casas se a peça não se movimentou nenhuma vez.
				// Obs: é necessário ter a casa anterior válida para movimentação, por isso eu inseri 
				// essa condição no if da primeira casa.
				p.setValues(position.getRow() - 2, position.getColumn());
				if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			// verificando a diagonal superior esquerda da peça ou se é possível fazer o Special Move - En Passant
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if ((getBoard().positionExists(p) && isThereOpponentPiece(p)) || testEnPassant("left", getColor())) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// verificando a diagonal superior direita da peça ou se é possível fazer o Special Move - En Passant
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if ((getBoard().positionExists(p) && isThereOpponentPiece(p)) || testEnPassant("right", getColor())) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		else {
			// verificando abaixo da peça
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				
				// verificando se pode andar duas casas se a peça não se movimentou nenhuma vez
				// Obs: é necessário ter a casa anterior válida para movimentação, por isso eu inseri 
				// essa condição no if da primeira casa.
				p.setValues(position.getRow() + 2, position.getColumn());
				if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			// verificando a diagonal inferior esquerda da peça ou se é possível fazer o Special Move - En Passant
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if ((getBoard().positionExists(p) && isThereOpponentPiece(p)) || testEnPassant("left", getColor())) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// verificando a diagonal inferior direita da peça ou se é possível fazer o Special Move - En Passant
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if ((getBoard().positionExists(p) && isThereOpponentPiece(p)) || testEnPassant("right", getColor())) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}
}
