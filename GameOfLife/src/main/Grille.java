// Antoine Planche LP24 Project ConwayGameOfLife

/* Note : this class is not really useful, we could have manipulated the cells 
 * directly from a double array declared in the Game class but I personally find
 * that this layer of abstraction makes the Grid clearer. This class also allows 
 * to implement small methods that are more consistent with being in the Grid object 
 * than in the Games object ( for instance printGrille ( line 127 ) 
 * or numberOfCurrentBlackCell() ( line 102 ) */

package main;

import java.awt.Color;

public class Grille {

	
	//Attributes
	private int line;
	private int column;
	private Cell grid[][];
	
	//Methods
	
	//Constructors
	public Grille() {
		
		this.line = 1000;
		this.column = 1000;
		grid = new Cell[line][column];
		
 		for ( int i = 0;i<this.line; i++ )
		{
			
 			for ( int j = 0; j<this.column; j++ )
			{
				
 				grid[i][j] = new Cell(); // dynamically initiate the grid
 				
			}
 			
		}
 		
	}
	
	public Grille( int a, int b ) {
		
		this.line = a;
		this.column = b;
		grid = new Cell[line][column];
		
 		for ( int i = 0;i<this.line; i++ )
		{
			
 			for ( int j = 0; j<this.column; j++ )
			{
				
 				grid[i][j] = new Cell(); // dynamically initiate the grid
 				
			}
 			
		}
 		
	}
	
	//getters & setters
	public int getLine() {
		return this.line;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public Color getCurrentCell(int a, int b) {
		return this.grid[a][b].getCurrentCell();
	}
	
	public Color getUpdatingCell(int a,int b) {
		return this.grid[a][b].getUpdatingCell();
	}
	
	public Color getGoalCell(int a,int b) {
		return this.grid[a][b].getGoalCell();
	}
	
	public void setUpdatingCell(Color c, int a, int b) {
		this.grid[a][b].setUpdatingCell(c);
	}
	
	public void setCurrentCell(Color c, int a, int b) {
		this.grid[a][b].setCurrentCell(c);
	}
	
	public void setGoalCell(Color c,int a,int b) {
		this.grid[a][b].setGoalCell(c);
	}
	
	public int getValue(int a, int b) {
		return this.grid[a][b].getCurrentValue();
	}
	
	public int numberOfCurrentBlackCell() { //function to calculate the number of black cell ( important information for the score). 
		
		int counter = 0;
		
		for ( int i = 0; i<this.line; i++ )
		{
			
			for ( int j = 0; j<this.column; j++ )
			{
				
				if ( grid[i][j].getCurrentCell().equals(Color.black) )
				{
					
					counter++;
					
				}
				
			}
			
		}
		
		return counter;
		
	}
	
	public void printGrille(String language) {
		
		if (language.equals("en"))
		{
			
			for ( int i = 0; i<this.line; i++ )
			{
				
				for ( int j = 0; j<this.column; j++ )
				{
					
					System.out.println("the cell["+(i+1)+"]["+(j+1)+"] is "+this.grid[i][j].printCell(language));
					
				}
				
			}
			
		}
		
		else
		{
			
			for ( int i = 0; i<this.line; i++ )
			{
				
				for (int j = 0; j<this.column; j++)
				{
					
					System.out.println("la cellule["+(i+1)+"]["+(j+1)+"] est "+this.grid[i][j].printCell(language));
					
				}
				
			}
			
		}
		
	}
	
}
