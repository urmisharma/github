package com.stephengware.java.games.chess.bot;
import java.util.Iterator;
import com.stephengware.java.games.chess.bot.Bot;
import com.stephengware.java.games.chess.state.Bishop;
import com.stephengware.java.games.chess.state.Knight;
import com.stephengware.java.games.chess.state.Pawn;
import com.stephengware.java.games.chess.state.Piece;
import com.stephengware.java.games.chess.state.Player;
import com.stephengware.java.games.chess.state.Queen;
import com.stephengware.java.games.chess.state.Rook;
import com.stephengware.java.games.chess.state.State;
/**
 * A chess bot which selects its next move at random.
 * 
 * @author Urmila Sharma
 */
public class MyBot extends Bot {
	/**
	 * Constructs a new chess bot named "My Chess Bot" and whose random  number
	 * generator (see {@link java.util.Random}) begins with a seed of 0.
	 */
	public MyBot() {
		super("usharma");
	}
	@Override
	protected State chooseMove(State root) {
		int depth = 4;
		Node selectedNode = new Node();
		
		selectedNode = minimax(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depth );
		if (selectedNode.getState().previous.equals(root))
			{return selectedNode.getState();}
		else{
			while (!selectedNode.getState().previous.equals(root)){
				selectedNode.setState(selectedNode.getState().previous);
			}
		}
		return selectedNode.getState();
	}
	/**
	 * 
	 * @param state
	 * @return Double
	 * Mini-Max Algorithm
	 * 
	 */
	protected Node minimax(State root, double alpha, double beta, int depth)
	{
		Node bestNode = new Node();
		Node node = new Node();
		Node returned = new Node();
		if (root.player.equals(Player.WHITE)){
			if (root.over || depth <= 0) {
				
				node.setState(root);
				node.setUtility(evaluate(node.getState()));
				return node;
			}
			double max = Double.NEGATIVE_INFINITY;
			Iterator<State> itr = root.next().iterator();
	
			while (itr.hasNext() && !root.searchLimitReached()) {
	
				Node nextState = new Node();
				nextState.setState(itr.next());
				returned = minimax(nextState.getState(), alpha, beta, depth - 1);
				if (returned.getUtility() > max) {
	
					bestNode = returned;
				}
				max = Math.max(max, returned.getUtility());
	
				if (max >= beta)
					return bestNode;
				alpha = Math.max(alpha, max);
			}
			
			if (bestNode.getState() == null) {
				bestNode.setState(root);
				bestNode.setUtility(0.0);
	
			}}
		
		if (root.player.equals(Player.BLACK)){
			if (root.over || depth <= 0) {
				node.setState(root);
				node.setUtility(evaluate(node.getState()));
				return node;
			}

			double min = Double.POSITIVE_INFINITY;

			Iterator<State> itr = root.next().iterator();

			while (itr.hasNext() && !root.searchLimitReached()) {

				Node nextState = new Node();
				nextState.setState(itr.next());
				returned = minimax(nextState.getState(), alpha, beta, depth - 1);

				if (returned.getUtility() < min) {

					bestNode = returned;

				}
				min = Math.min(min, returned.getUtility());
				if (min <= alpha)
					return bestNode;
				beta = Math.min(beta, min);
			}

			if (bestNode.getState() == null) {
				bestNode.setState(root);
				bestNode.setUtility(0.0);
				} 
			}
				return bestNode;
	}
	/**
	 * 
	 * @param root
	 * @return returnUtility
	 * 
	 */

	public static double evaluate(State root) {
		double WhiteUtility = 0.0;
		double BlackUtility = 0.0;
		double returnUtility = 0.0;
		
		if (root.check)
	      {
			if (root.player.equals(Player.WHITE)){
	        	return -10000;
	        }
	        else if (root.player.equals(Player.BLACK)){
	        	return 10000;
	        }
	        
	      }
		for(Piece piece : root.board){
			
			int row = piece.rank;
			int col = piece.file;
			int oppKingRow = root.board.getKing(root.player.other()).rank;
			int oppKingCol = root.board.getKing(root.player.other()).file;
			double manHatDist = Math.abs(oppKingRow-row)+Math.abs(oppKingCol-col);
			double closeness = 1/manHatDist;
			
			if (piece.player.equals(Player.WHITE)){
				if(root.player == piece.player){
					BlackUtility +=0.2;
				}
				if (piece instanceof Queen){
					WhiteUtility += 8.8; 
					WhiteUtility +=closeness;
					if(root.player == piece.player){
						WhiteUtility +=0.2;
					}
					}
				if (piece instanceof Bishop){
					WhiteUtility +=3.5+((piece.rank)*0.02);
					WhiteUtility +=closeness;
					}
				if (piece instanceof Knight){
					WhiteUtility +=3.2+((piece.rank)*0.03);
					WhiteUtility +=closeness;
					}
					if (piece instanceof Rook){
						WhiteUtility +=5.1;
						WhiteUtility +=closeness;
					}
					if (piece instanceof Pawn){
						WhiteUtility +=1.1+((piece.rank)*0.07);
						WhiteUtility +=closeness;
					}
			}
			else
			{
				if (piece.player.equals(Player.BLACK)){
					if(root.player == piece.player){
						BlackUtility +=0.2;
					}
					if (piece instanceof Queen){
						BlackUtility += 8.8; 
						BlackUtility +=closeness;
						}
						if (piece instanceof Bishop){
							BlackUtility +=3.5+((10.0-piece.rank)*0.02);
							BlackUtility +=closeness;
							
						}
						if (piece instanceof Knight){
							BlackUtility +=3.2+((7.0-piece.rank)*0.03);
							BlackUtility +=closeness;
						}
						if (piece instanceof Rook){
							BlackUtility +=5.1+((9.0-piece.rank)*0.03);
							BlackUtility +=closeness;
						}
						if (piece instanceof Pawn){
							BlackUtility +=1.1+((6.0-piece.rank)*0.07);
							BlackUtility +=closeness;
						}
		}
			}
		}
		if(root.over){
			
			returnUtility = BlackUtility-WhiteUtility;
			
		}else{
			
			returnUtility = WhiteUtility-BlackUtility;
			}
			return returnUtility;
		}
}
	
 class Node
{
	 private State state;
	 private Double utility;
	 int depth;
	 
	 public State getState(){
		 return state;
	 }
	 public void setState(State state){
		 this.state = state;
	 }
	 public double getUtility(){
		 return this.utility;
	 }
	 public void setUtility(double utility){
		 this.utility = utility;
	 }
}