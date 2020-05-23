// Antoine Planche LP24 Project ConwayGameOfLife

package world.grid;

import java.awt.Color;
import world.cell.SimulationCell;


public class SimulationGrid {

	//Attributes
	
	private int line;
	private int column;
	private SimulationCell grid[][];
	
	//Methods
	
	//Constructors
	
	public SimulationGrid() 
	{
		
		this.line = 1000;
		this.column = 1000;
		grid = new SimulationCell[line][column];
		
 		for ( int i = 0;i<this.line; i++ )
		{
			
 			for ( int j = 0; j<this.column; j++ )
			{
				
 				grid[i][j] = new SimulationCell(); // dynamically initiate the grid
 				
			}
 			
		}
 		
	}
	
	
	
	
	public SimulationGrid( int a, int b ) 
	{
		
		this.line = a;
		this.column = b;
		grid = new SimulationCell[line][column];
		
 		for ( int i = 0;i<this.line; i++ )
		{
			
 			for ( int j = 0; j<this.column; j++ )
			{
				
 				grid[i][j] = new SimulationCell(); // dynamically initiate the grid
 				
			}
 			
		}
 		
	}
	
	
	//getters & setters
	
	public int getLine() 
	{
	
		return this.line;
		
	}
	
	
	
	
	public int getColumn() 
	{
		
		return this.column;
		
	}
	
	
	
	
	public Color getCurrentCell(int a, int b) 
	{
		
		return this.grid[a][b].getCurrentCell();
		
	}
	
	
	
	
	public Color getUpdatingCell(int a,int b) 
	{
		
		return this.grid[a][b].getUpdatingCell();
		
	}
	
	
	
	
	public void setUpdatingCell(Color c, int a, int b) 
	{
		
		this.grid[a][b].setUpdatingCell(c);
		
	}
	
	
	
	
	public void setCurrentCell(Color c, int a, int b) 
	{
		
		this.grid[a][b].setCurrentCell(c);
		
	}
	
	
	
	
	public int getValue(int a, int b) 
	{
		
		return this.grid[a][b].getCurrentValue();
		
	}
	
	
	
	
	public int numberOfCurrentBlackCell() //function to calculate the number of black cell ( important information for the score). 
	{ 
		
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
	
	
	
	
	public void printGrille(String language) 
	{
		
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
	



	public  SimulationGrid lapAfterLapGraphicSimulation() 
	{
		
		int NbrNeighbour = 0;
		
		
		// At the beginning, I treat the center cells 
		// ( those that are not on the edges or on the corners )
		
		for ( int i = 1; i<(this.getLine()-1); i++ )
		{
			
			for ( int j = 1; (j<this.getColumn()-1); j++ )
			{
				
				// I sweep the center of the grid
				
				/*I check if the cell is black or white then I calculate the number of 
				 black neighbors with the function getCurrentValue (see class cell line 92 for more details)
				 and I apply the appropriate treatment (the cell becomes black or white).
				 Note that we could first calculate the number of black neighbors and 
				 then test if the cell is white or black.
				 It comes down to the same level of complexity  */
				
				if ( this.getCurrentCell(i,j).equals(Color.black) ) 
				{
					
					NbrNeighbour = this.getValue((i-1),(j-1)) + this.getValue((i-1),(j)) + this.getValue((i-1),(j+1)) +
							this.getValue((i),(j-1)) + this.getValue((i),(j+1)) + this.getValue((i+1),(j-1) )+
							this.getValue((i+1),(j)) + this.getValue((i+1),(j+1));
					
					if( NbrNeighbour != 2 && NbrNeighbour != 3 ) // if a black cell isn't surrounded by 2 or 3 black cell, it becomes white.
					{												
						
						this.setUpdatingCell(Color.white, i, j);
					
					}
				
				}
				
				else // then the cell is white
				{
					
					NbrNeighbour = this.getValue((i-1),(j-1)) + this.getValue((i-1),(j)) + this.getValue((i-1),(j+1)) +
							this.getValue((i),(j-1)) + this.getValue((i),(j+1)) + this.getValue((i+1),(j-1) )+
							this.getValue((i+1),(j)) + this.getValue((i+1),(j+1));
					
					if( NbrNeighbour == 3 ) // If a white cell is surrounded by 3 black cell, it becomes black
					{
						
						this.setUpdatingCell(Color.black, i, j);
						
					}
					
				}
				
			}
			
		}
		
		// We move to the grid edge cell, We apply much the same treatment to these cells
		// Only changes the fact that they only have 5 neighboring cells
		// We sweep the grid in four times, first the right column then the left column
		// then the top line and finally the bottom line
		
		for ( int i = 1; i<this.getLine()-1 ; i++ )
		{
			
			if ( this.getCurrentCell(i,0).equals(Color.black) )
			{
				
				NbrNeighbour = this.getValue((i-1),(0)) + this.getValue((i-1),(1)) + this.getValue((i),(1)) +
						this.getValue((i+1),(0)) + this.getValue((i+1),(1));
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				{
					
					this.setUpdatingCell(Color.white, i, 0);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = this.getValue((i-1),(0)) + this.getValue((i-1),(1)) + this.getValue((i),(1)) +
						this.getValue((i+1),(0)) + this.getValue((i+1),(1));
				
				if ( NbrNeighbour == 3 )
				{
					
					this.setUpdatingCell(Color.black, i, 0);
					
				}
				
			}
			
			if ( this.getCurrentCell(i,(this.getColumn()-1)).equals(Color.black) )
				
			{
				
				NbrNeighbour = this.getValue((i-1),(this.getColumn()-1)) + this.getValue((i-1),(this.getColumn()-2))
				+ this.getValue((i),(this.getColumn()-2)) + this.getValue((i+1),(this.getColumn()-2))
				+ this.getValue((i+1),(this.getColumn()-1));
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				{
					
					this.setUpdatingCell(Color.white, i, this.getColumn()-1);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = this.getValue((i-1),(this.getColumn()-1)) + this.getValue((i-1),(this.getColumn()-2))
				+ this.getValue((i),(this.getColumn()-2)) + this.getValue((i+1),(this.getColumn()-2))
				+ this.getValue((i+1),(this.getColumn()-1));
	
				if ( NbrNeighbour == 3 )
				{
					
					this.setUpdatingCell(Color.black, i, this.getColumn()-1);
					
				}
				
			}
			
		}
		
		for ( int j = 1; j<this.getColumn()-1; j++ )
		{
			
			if ( this.getCurrentCell(0,j).equals(Color.black) )	
			{
				NbrNeighbour = this.getValue((0),(j-1)) + this.getValue((1),(j-1)) + this.getValue((1),(j)) +
						this.getValue((1),(j+1)) + this.getValue((0),(j+1));
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				{
					
					this.setUpdatingCell(Color.white, 0, j);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = this.getValue((0),(j-1)) + this.getValue((1),(j-1)) + this.getValue((1),(j)) +
						this.getValue((1),(j+1)) + this.getValue((0),(j+1));
				
				if ( NbrNeighbour == 3 )
				{
					
					this.setUpdatingCell(Color.black, 0, j);
					
				}
				
			}
			
			if ( this.getCurrentCell(this.getLine()-1,(j)).equals(Color.black) )
			{
				
				NbrNeighbour = this.getValue((this.getLine()-1),(j-1)) + this.getValue((this.getLine()-2),(j-1))
				+ this.getValue((this.getLine()-2),(j)) + this.getValue((this.getLine()-2),(j+1))
				+ this.getValue((this.getLine()-1),(j+1));
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				{
					
					this.setUpdatingCell(Color.white, this.getLine()-1, j);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = this.getValue((this.getLine()-1),(j-1)) + this.getValue((this.getLine()-2),(j-1))
				+ this.getValue((this.getLine()-2),(j)) + this.getValue((this.getLine()-2),(j+1))
				+ this.getValue((this.getLine()-1),(j+1));
	
				if ( NbrNeighbour == 3 )
				{
					
					this.setUpdatingCell(Color.black, this.getLine()-1, j);
					
				}
				
			}
			
		}
		
		if ( this.getCurrentCell(0,0).equals(Color.black) )
		{
			
			NbrNeighbour = this.getValue(1,0) + this.getValue(1,1) + this.getValue(0,1);
			
			if ( NbrNeighbour != 2 && NbrNeighbour != 3 )	
			{
				
				this.setUpdatingCell(Color.white, 0, 0);
				
			}
			
		}
		
		else
		{
			
			NbrNeighbour = this.getValue(1,0) + this.getValue(1,1) + this.getValue(0,1);
			
			if ( NbrNeighbour == 3 )
			{
				
				this.setUpdatingCell(Color.black, 0, 0);
				
			}
			
		}
		
		if ( this.getCurrentCell(0,this.getColumn()-1).equals(Color.black) )
		{
			
			NbrNeighbour = this.getValue(0,this.getColumn()-2) + this.getValue(1,this.getColumn()-2)
			+ this.getValue(1,this.getColumn()-1);
			
			if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
			{
				
				this.setUpdatingCell(Color.white, 0, this.getColumn()-1);
				
			}
			
		}
		
		else
		{
			
			NbrNeighbour = this.getValue(0,this.getColumn()-2) + this.getValue(1,this.getColumn()-2)
			+ this.getValue(1,this.getColumn()-1);
			
			if ( NbrNeighbour == 3 )
			{
				
				this.setUpdatingCell(Color.black, 0, this.getColumn()-1);
				
			}
			
		}
		
		if ( this.getCurrentCell(this.getLine()-1,0).equals(Color.black) )
		{
			
			NbrNeighbour = this.getValue(this.getLine()-2,0) + this.getValue(this.getLine()-2,0)
			+ this.getValue(this.getLine()-1,1);
			
			if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
			{
				
				this.setUpdatingCell(Color.white, this.getLine()-1, 0);
				
			}
			
		}
		
		else
		{
			
			NbrNeighbour = this.getValue(this.getLine()-2,0) + this.getValue(this.getLine()-2,0)
			+ this.getValue(this.getLine()-1,1);
			
			if ( NbrNeighbour == 3 )
			{
				
				this.setUpdatingCell(Color.black, this.getLine()-1, 0);
				
			}
			
		}
		
		if ( this.getCurrentCell(this.getLine()-1,this.getColumn()-1).equals(Color.black) )
		{
			
			NbrNeighbour = this.getValue(this.getLine()-2,this.getColumn()-1) + this.getValue(this.getLine()-2,this.getColumn()-2)
			+ this.getValue(this.getLine()-1,this.getColumn()-2);
			
			if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
			
			{
				
				this.setUpdatingCell(Color.white, this.getLine()-1, this.getColumn()-1);
			
			}
			
		}
		
		else
		{
			
			NbrNeighbour = this.getValue(this.getLine()-2,this.getColumn()-1) + this.getValue(this.getLine()-2,this.getColumn()-2)
			+ this.getValue(this.getLine()-1,this.getColumn()-2);
			
			if ( NbrNeighbour == 3 )
			{
				
				this.setUpdatingCell(Color.black, this.getLine()-1, this.getColumn()-1);
				
			}
			
		}
		
		for ( int i = 0; i<this.getLine();i++ )
		{
			
			for ( int j = 0; j<this.getColumn();j++ )
			{
				
				this.setCurrentCell(this.getUpdatingCell(i,j),i,j); // I sweep the grid to update the current cell
				
			}
			
		}
		
		return this;
	
	}
	
	
}
