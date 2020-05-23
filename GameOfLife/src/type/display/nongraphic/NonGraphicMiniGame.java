// Antoine Planche LP24 Project ConwayGameOfLife
// This class is useful in the case of the non-graphic mini games

package type.display.nongraphic;

import java.awt.Color;
import world.grid.MiniGameGrid;
import java.io.*;
import java.util.*;


public class NonGraphicMiniGame { 

	//Attributes
	
	private MiniGameGrid life;
	private Scanner sc = new Scanner(System.in);
	private ResourceBundle RESOURCEBUNDLE;
	private String language ;
	private static FileWriter writer;
	private int goalLap;
	private byte difficulty; // byte to avoid taking up too much memory
	
	// Constructor
	
	public NonGraphicMiniGame(ResourceBundle RESOURCEBUNDLE,String language) 
	{
		
		int line = 0;
		int column = 0;
		this.language = new String(language); // useful to print the grid in the correct language (cf class Grille line 190)
	    	this.RESOURCEBUNDLE = RESOURCEBUNDLE;
		System.out.println(RESOURCEBUNDLE.getString("keyeleven"));
		System.out.println(RESOURCEBUNDLE.getString("keytwo"));
		line = sc.nextInt();
		
		while ( line <= 1 ) // The line must be greater than 1 to make my algorithm work ( with the corner cell and edge cell )
		{
			
			System.out.println(RESOURCEBUNDLE.getString("keyeighteen"));
			System.out.println(RESOURCEBUNDLE.getString("keytwo"));
			line = sc.nextInt();
			
		}
		
		System.out.println(RESOURCEBUNDLE.getString("keythree"));
		column = sc.nextInt();
		
		while ( column <= 1 )  // same for column
		{
			
			System.out.println(RESOURCEBUNDLE.getString("keynineteen"));
			System.out.println(RESOURCEBUNDLE.getString("keythree"));
			column = sc.nextInt();
			
		}
		
		life = new MiniGameGrid(line ,column); // Create the grid
			
		do {
			
			System.out.println(RESOURCEBUNDLE.getString("keytwelve"));
			line = sc.nextInt();
			
			while ( line <=0 || line > life.getLine() ) // I check that the line is contained in the grille
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyfive"));
				line = sc.nextInt();
				
			}
			
			System.out.println(RESOURCEBUNDLE.getString("keythirteen"));
			column = sc.nextInt();
			
			while ( column <=0 || column > life.getColumn() ) // same for column
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyseven"));
				column = sc.nextInt();
				
			}
			
			while ( life.getGoalCell(line-1, column-1).equals(Color.black) ) // I check if the cell is already black if it's the case i repeat the above procedure
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyeight"));
				System.out.println(RESOURCEBUNDLE.getString("keytwelve"));
				line = sc.nextInt();
				
				while ( line <=0 || line > life.getLine() ) //verification loop
				{
					
					System.out.println(RESOURCEBUNDLE.getString("keyfive"));
					line = sc.nextInt();
					
				}
				
				System.out.println(RESOURCEBUNDLE.getString("keythirteen"));
				column = sc.nextInt();
				
				while ( column <= 0 || column > life.getColumn() ) //verification loop
				{
					
					System.out.println(RESOURCEBUNDLE.getString("keyseven"));
					column = sc.nextInt();
					
				}
				
			}
			
			// I initialize the cell with the index (x-1,y-1) so that the use of the 
			// grid is transparent at user level (no index 0)
			
			life.setGoalCell(Color.black, line-1, column-1); 
			System.out.println(RESOURCEBUNDLE.getString("keyfourteen"));
			line = sc.nextInt();
			
			while ( line != 1 && line != 0 ) // I use the variable line to add a new cell and to avoid to state a new variable 
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyfifteen"));
				line = sc.nextInt();
				
			}
			
		}while ( line == 1 );
		
		System.out.println(RESOURCEBUNDLE.getString("keysixteen"));
		goalLap = sc.nextInt();
		
		if ( goalLap <= 0 ) // the maximum lap to reach the configuration must be greater than 0
		{
			
			System.out.println(RESOURCEBUNDLE.getString("keyseventeen"));
			System.out.println(RESOURCEBUNDLE.getString("keysixteen"));
			goalLap = sc.nextInt();
			
		}
			
		System.out.println(RESOURCEBUNDLE.getString("keyone"));
			
		// Now it's the part where the player chooses it's start configuration
		// It's much the same as the algorithm above
		
		do {
			
			System.out.println(RESOURCEBUNDLE.getString("keyfour"));
			line = sc.nextInt();
			
			while ( line <= 0 || line > life.getLine() ) //verification loop
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyfive"));
				line = sc.nextInt();
				
			}
			
			System.out.println(RESOURCEBUNDLE.getString("keysix"));
			column = sc.nextInt();
			
			while ( column <= 0 || column > life.getColumn() ) //verification loop
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyseven"));
				column = sc.nextInt();
				
			}
			
			while ( life.getCurrentCell(line-1, column-1).equals(Color.black) )
			{
				
				System.out.println(RESOURCEBUNDLE.getString("keyeight"));
				System.out.println(RESOURCEBUNDLE.getString("keyfour"));
				line = sc.nextInt();
				
				while ( line <= 0 || line > life.getLine() ) //verification loop
				{
					
					System.out.println(RESOURCEBUNDLE.getString("keyfive"));
					line = sc.nextInt();
					
				}
				
				System.out.println(RESOURCEBUNDLE.getString("keysix"));
				column = sc.nextInt();
				
				while ( column <= 0 || column > life.getColumn() ) //verification loop
				{
					
					System.out.println(RESOURCEBUNDLE.getString("keyseven"));
					column = sc.nextInt();
					
				}
				
			}
			
			life.setCurrentCell(Color.black, line-1, column-1);
			life.setUpdatingCell(Color.black, line-1, column-1);
			System.out.println(RESOURCEBUNDLE.getString("keynine"));
			line = sc.nextInt();
			
			while ( line != 1 && line != 0 ) //verification loop
			
			{
			
				System.out.println(RESOURCEBUNDLE.getString("keyten"));
				line = sc.nextInt();
				
			}
			
		}while ( line == 1 );
		
		System.out.println(RESOURCEBUNDLE.getString("keytwentynine"));
		difficulty =(byte)sc.nextInt();
		
		while ( difficulty != 1 && difficulty != 2 ) // I verify the value of difficulty
		{
			
			System.out.println(RESOURCEBUNDLE.getString("keythirty"));
			System.out.println(RESOURCEBUNDLE.getString("keytwentynine"));
			difficulty =(byte)sc.nextInt();
			
		}	
		
	}
	
	
	
	
	public void lapAfterLapNonGraphic() throws java.io.IOException, Exception
	{
		
		int lap = 1;
		int NbrNeighbour = 0;
		byte next = 0; // byte to avoid taking up too much memory
			
		life.printGrille(language);
		System.out.println(RESOURCEBUNDLE.getString("keytwenty") + lap + ".");
		System.out.println(RESOURCEBUNDLE.getString("keythirtyseven") + life.numberOfCurrentBlackCell() + " " + RESOURCEBUNDLE.getString("keythirtyeight"));
		System.out.println(RESOURCEBUNDLE.getString("keytwentyone"));
		next = (byte)sc.nextInt();
			
		while ( next != 0 && next != 1 ) // I verify if next equal 0 or 1 
		{
				
			System.out.println(RESOURCEBUNDLE.getString("keytwentytwo"));
			next = (byte)sc.nextInt();
				
		}
			
		// I allow the player to manually go from one lap to another so that 
		// he can modify a cell during simulation
			
		while ( next == 1 )  
		{
		
			// At the beginning, I treat the center cells 
			// ( those that are not on the edges or on the corners
			
			for ( int i = 1; i<(life.getLine()-1); i++ )
			{
				
				for ( int j = 1; (j<life.getColumn()-1); j++ )
				{
					
					// I sweep the center of the grid
					
					/*I check if the cell is black or white then I calculate the number of 
					 black neighbors with the function getCurrentValue (see class Cell line 96 for more details)
					 and I apply the appropriate treatment (the cell becomes black or white).
					 Note that we could first calculate the number of black neighbors and 
					 then test if the cell is white or black.
					 It comes down to the same level of complexity  */
					
					if ( life.getCurrentCell(i,j).equals(Color.black) ) 
					{
						
						NbrNeighbour = life.getValue((i-1),(j-1)) + life.getValue((i-1),(j)) + life.getValue((i-1),(j+1)) +
								life.getValue((i),(j-1)) + life.getValue((i),(j+1)) + life.getValue((i+1),(j-1) )+
								life.getValue((i+1),(j)) + life.getValue((i+1),(j+1));
						
						if( NbrNeighbour != 2 && NbrNeighbour != 3 ) // if a black cell isn't surrounded by 2 or 3 black cell, it becomes white.
						{												
							
							life.setUpdatingCell(Color.white, i, j);
						
						}
					
					}
					
					else // then the cell is white
					{
						
						NbrNeighbour = life.getValue((i-1),(j-1)) + life.getValue((i-1),(j)) + life.getValue((i-1),(j+1)) +
								life.getValue((i),(j-1)) + life.getValue((i),(j+1)) + life.getValue((i+1),(j-1) )+
								life.getValue((i+1),(j)) + life.getValue((i+1),(j+1));
						
						if( NbrNeighbour == 3 ) // If a white cell is surrounded by 3 black cell, it becomes black
						{
							
							life.setUpdatingCell(Color.black, i, j);
							
						}
						
					}
					
				}
				
			}
			
			// We move to the grid edge cell, We apply much the same treatment to these cells
			// Only changes the fact that they only have 5 neighboring cells
			// We sweep the grid in four times, first the right column then the left column
			// then the top line and finally the bottom line
			
			for ( int i = 1; i<life.getLine()-1 ; i++ )
			{
				
				if( life.getCurrentCell(i,0).equals(Color.black) )
				{
					
					NbrNeighbour = life.getValue((i-1),(0)) + life.getValue((i-1),(1)) + life.getValue((i),(1)) +
							life.getValue((i+1),(0)) + life.getValue((i+1),(1));
					
					if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
					{
						
						life.setUpdatingCell(Color.white, i, 0);
						
					}
					
				}
				
				else
				{
					
					NbrNeighbour = life.getValue((i-1),(0)) + life.getValue((i-1),(1)) + life.getValue((i),(1)) +
							life.getValue((i+1),(0)) + life.getValue((i+1),(1));
					
					if ( NbrNeighbour == 3 )
					{
						
						life.setUpdatingCell(Color.black, i, 0);
						
					}
				}
				
				if ( life.getCurrentCell(i,(life.getColumn()-1)).equals(Color.black) )
					
				{
					
					NbrNeighbour = life.getValue((i-1),(life.getColumn()-1)) + life.getValue((i-1),(life.getColumn()-2))
					+ life.getValue((i),(life.getColumn()-2)) + life.getValue((i+1),(life.getColumn()-2))
					+ life.getValue((i+1),(life.getColumn()-1));
					
					if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
					{
						
						life.setUpdatingCell(Color.white, i, life.getColumn()-1);
						
					}
					
				}
				else
				{
					
					NbrNeighbour = life.getValue((i-1),(life.getColumn()-1)) + life.getValue((i-1),(life.getColumn()-2))
					+ life.getValue((i),(life.getColumn()-2)) + life.getValue((i+1),(life.getColumn()-2))
					+ life.getValue((i+1),(life.getColumn()-1));
	
					if ( NbrNeighbour == 3 )
					{
						
						life.setUpdatingCell(Color.black, i, life.getColumn()-1);
						
					}
					
				}
				
			}
			
			for ( int j = 1; j<life.getColumn()-1; j++ )
			{
				
				if ( life.getCurrentCell(0,j).equals(Color.black) )	
				{
					NbrNeighbour = life.getValue((0),(j-1)) + life.getValue((1),(j-1)) + life.getValue((1),(j)) +
							life.getValue((1),(j+1)) + life.getValue((0),(j+1));
					
					if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
					{
						
						life.setUpdatingCell(Color.white, 0, j);
						
					}
					
				}
				
				else
				{
					
					NbrNeighbour = life.getValue((0),(j-1)) + life.getValue((1),(j-1)) + life.getValue((1),(j)) +
							life.getValue((1),(j+1)) + life.getValue((0),(j+1));
					
					if ( NbrNeighbour == 3 )
					{
						
						life.setUpdatingCell(Color.black, 0, j);
						
					}
					
				}
				
				if ( life.getCurrentCell(life.getLine()-1,(j)).equals(Color.black) )
				{
					
					NbrNeighbour = life.getValue((life.getLine()-1),(j-1)) + life.getValue((life.getLine()-2),(j-1))
					+ life.getValue((life.getLine()-2),(j)) + life.getValue((life.getLine()-2),(j+1))
					+ life.getValue((life.getLine()-1),(j+1));
					
					if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
					{
						
						life.setUpdatingCell(Color.white, life.getLine()-1, j);
						
					}
					
				}
				
				else
				{
					
					NbrNeighbour = life.getValue((life.getLine()-1),(j-1)) + life.getValue((life.getLine()-2),(j-1))
					+ life.getValue((life.getLine()-2),(j)) + life.getValue((life.getLine()-2),(j+1))
					+ life.getValue((life.getLine()-1),(j+1));
	
					if ( NbrNeighbour == 3 )
					{
						
						life.setUpdatingCell(Color.black, life.getLine()-1, j);
						
					}
					
				}
				
			}
			
			if ( life.getCurrentCell(0,0).equals(Color.black) )
			{
				
				NbrNeighbour = life.getValue(1,0) + life.getValue(1,1) + life.getValue(0,1);
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )	
				{
					
					life.setUpdatingCell(Color.white, 0, 0);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = life.getValue(1,0) + life.getValue(1,1) + life.getValue(0,1);
				
				if ( NbrNeighbour == 3 )
				{
					
					life.setUpdatingCell(Color.black, 0, 0);
					
				}
				
			}
			
			if ( life.getCurrentCell(0,life.getColumn()-1).equals(Color.black) )
			{
				
				NbrNeighbour = life.getValue(0,life.getColumn()-2) + life.getValue(1,life.getColumn()-2)
				+ life.getValue(1,life.getColumn()-1);
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				{
					
					life.setUpdatingCell(Color.white, 0, life.getColumn()-1);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = life.getValue(0,life.getColumn()-2) + life.getValue(1,life.getColumn()-2)
				+ life.getValue(1,life.getColumn()-1);
				
				if ( NbrNeighbour == 3 )
				{
					
					life.setUpdatingCell(Color.black, 0, life.getColumn()-1);
					
				}
				
			}
			
			if ( life.getCurrentCell(life.getLine()-1,0).equals(Color.black) )
			{
				
				NbrNeighbour = life.getValue(life.getLine()-2,0) + life.getValue(life.getLine()-2,0)
				+ life.getValue(life.getLine()-1,1);
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				{
					
					life.setUpdatingCell(Color.white, life.getLine()-1, 0);
					
				}
				
			}
			
			else
			{
				
				NbrNeighbour = life.getValue(life.getLine()-2,0) + life.getValue(life.getLine()-2,0)
				+ life.getValue(life.getLine()-1,1);
				
				if ( NbrNeighbour == 3 )
				{
					
					life.setUpdatingCell(Color.black, life.getLine()-1, 0);
					
				}
				
			}
			
			if ( life.getCurrentCell(life.getLine()-1,life.getColumn()-1).equals(Color.black) )
			{
				
				NbrNeighbour = life.getValue(life.getLine()-2,life.getColumn()-1) + life.getValue(life.getLine()-2,life.getColumn()-2)
				+ life.getValue(life.getLine()-1,life.getColumn()-2);
				
				if ( NbrNeighbour != 2 && NbrNeighbour != 3 )
				
				{
					
					life.setUpdatingCell(Color.white, life.getLine()-1, life.getColumn()-1);
				
				}
				
			}
			
			else
			{
				
				NbrNeighbour = life.getValue(life.getLine()-2,life.getColumn()-1) + life.getValue(life.getLine()-2,life.getColumn()-2)
				+ life.getValue(life.getLine()-1,life.getColumn()-2);
				
				if ( NbrNeighbour == 3 )
				{
					
					life.setUpdatingCell(Color.black, life.getLine()-1, life.getColumn()-1);
					
				}
				
			}
			
			for ( int i = 0; i<life.getLine();i++ )
			{
				
				for ( int j = 0; j<life.getColumn();j++ )
				{
					
					life.setCurrentCell(life.getUpdatingCell(i,j),i,j); // I sweep the grid to update the current cell
					
				}
				
			}
			
			life.printGrille(language);
				
			if ( difficulty == 1 ) // then the user can change the color of a current cell
			{
			
				System.out.println(RESOURCEBUNDLE.getString("keythirtyone"));
				next = (byte)sc.nextInt(); // The player has the choice if he wants to change cell(s) or stay in the same configuration
						
				while ( next != 0 && next != 1) //verification loop
				{
							
					System.out.println(RESOURCEBUNDLE.getString("keythirty"));
					next = (byte)sc.nextInt();
							
				}
		
				if ( next == 1 )
				{
							
					int line = 0;
					int column = 0;
					System.out.println(RESOURCEBUNDLE.getString("keythirtytwo"));
					line = sc.nextInt();
							
					while ( line <= 0 || line > life.getLine() ) //verification loop
					{
								
						System.out.println(RESOURCEBUNDLE.getString("keyfive"));
						line = sc.nextInt();
								
					}
							
					System.out.println(RESOURCEBUNDLE.getString("keythirtythree"));
					column = sc.nextInt();
							
					while ( column <= 0 || column > life.getColumn() ) // verification loop
					{
								
						System.out.println(RESOURCEBUNDLE.getString("keyseven"));
						column = sc.nextInt();
								
					}
							
					if ( life.getCurrentCell(line, column).equals(Color.black) ) // if the cell is black then it will become white and vice versa
					{
								
						life.setCurrentCell(Color.white, line-1, column-1);	 // I initialize the cell with the index (x-1,y-1) so that the use of the 
						life.setUpdatingCell(Color.white, line-1, column-1); // grid is transparent at user level (no index 0)
								
								
					}
							
					else
					{
								
						life.setCurrentCell(Color.black, line-1, column-1); // Same
						life.setUpdatingCell(Color.black, line-1, column-1);
								
					}
							
				}
						
			}
				
			lap++; 
			
			if ( lap > goalLap ) // then we lose
			{
					
				System.out.println(RESOURCEBUNDLE.getString("keytwentythree"));
				writer = new FileWriter("resources\\information\\score.txt",true);
				writer.write(RESOURCEBUNDLE.getString("keytwentyseven"));
				writer.close();
				System.exit(0);
					
			}
				
			else
			{
					
				int counter = 0; //allow to calculate the number of time where "currentCell" have the same value that "goalCell"
					
				for ( int i = 0; i<life.getLine();i++ )
				{
						
					for ( int j = 0; j<life.getColumn();j++ )
					{
							
						if ( life.getCurrentCell(i, j).equals(life.getGoalCell(i, j)) )
						{
								
							counter++;
								
						}
							
					}
						
				}
					
				if ( counter == (life.getLine()*life.getColumn()) ) // if the condition is true then it's the victory !!
				{													// no verification in the first lap, so we canot win in the first lap
						
					System.out.println(RESOURCEBUNDLE.getString("keytwentyfour") + " " + lap + RESOURCEBUNDLE.getString("keytwentyfive"));
					writer = new FileWriter("resources\\information\\score.txt",true);
					writer.write(RESOURCEBUNDLE.getString("keytwentysix") + " " + life.numberOfCurrentBlackCell()*50 + " points.\n"); // We mark the score in the file
					writer.close();
					System.exit(0);
						
				}
					
			}
				
			System.out.println(RESOURCEBUNDLE.getString("keytwenty") + lap + ".");
			System.out.println(RESOURCEBUNDLE.getString("keythirtyseven") + life.numberOfCurrentBlackCell() + " " + RESOURCEBUNDLE.getString("keythirtyeight"));
			System.out.println(RESOURCEBUNDLE.getString("keytwentyone")); // We ask the user if he wants to go to the next lap
			next = (byte)sc.nextInt();
				
			while ( next != 0 && next != 1 )  // verification loop
			{
					
				System.out.println(RESOURCEBUNDLE.getString("keytwentytwo"));
				next = (byte)sc.nextInt();
					
			}
			
		}
		
		sc.close();
		System.out.println(RESOURCEBUNDLE.getString("keytwentyeight"));
		
	}
	
	
}
