// Antoine Planche LP24 Project ConwayGameOfLife
// This class is useful in the case of the non-graphic mini games

package type.display.nongraphic;

import java.awt.Color;
import world.grid.MiniGameGrid;
import java.util.*;


public class NonGraphicSimulation { 

	//Attributes
	
	private MiniGameGrid life;
	private Scanner sc = new Scanner(System.in);
	private ResourceBundle RESOURCEBUNDLE;
	private String language ;
	
	// Constructor
	
	public NonGraphicSimulation(ResourceBundle RESOURCEBUNDLE,String language) 
	{
		
		int line = 0;
		int column = 0;
		this.language = new String(language); // useful to print the grid in the correct language (cf class SimulationGrid line 165)
	    	this.RESOURCEBUNDLE = RESOURCEBUNDLE;
		
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

		System.out.println(RESOURCEBUNDLE.getString("keythirtysix"));
			
		// Now it's the part where the user chooses it's start configuration
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
		
	}
	
	
	
	
	public void lapAfterLapNonGraphic() throws java.io.IOException, Exception
	{
			
		System.out.println(RESOURCEBUNDLE.getString("keythirtyfive"));
		
		try {
			
			Thread.sleep(3000); // allow the user to see the result
			
		} catch (InterruptedException ie) {
			
			//nothing
			
		}
		
		// Start the thread
		
		ThreadSimulation simulation = new ThreadSimulation();
		simulation.start(); 
          
		// Wait for manual interruption 
		
		System.in.read();
		simulation.interrupt(); 

		// Wait for the thread to finish

		simulation.join(); 
		
		sc.close();
		System.out.println(RESOURCEBUNDLE.getString("keytwentyeight"));
		
	}
	
	
	
	
	/* This below class allow us to activate a second execution thread to interrupt
	 the simulation by a keyboard input  */
	
	public class ThreadSimulation extends Thread //nested class
    	{
		
		private int lap = 1;
		private int NbrNeighbour = 0;
    	
		public void run() 
    		{
    		
			life.printGrille(language);
			System.out.println(RESOURCEBUNDLE.getString("keytwenty") + lap + ".");
			System.out.println(RESOURCEBUNDLE.getString("keythirtyseven") + life.numberOfCurrentBlackCell() + " " + RESOURCEBUNDLE.getString("keythirtyeight"));

			try {

				Thread.sleep(3000); // allow the user to see the result

			} catch (InterruptedException ie) {

				//Activation of the interrupt flag

				Thread.currentThread().interrupt();

			}

			while ( !isInterrupted() )  // As long as the thread is not interrupted ...
			{

				// At the beginning, I treat the center cells 
				// ( those that are not on the edges or on the corners )

				for ( int i = 1; i<(life.getLine()-1); i++ )
				{

					for ( int j = 1; (j<life.getColumn()-1); j++ )
					{

						// I sweep the center of the grid

						/*I check if the cell is black or white then I calculate the number of 
						 black neighbors with the function getCurrentValue (see class cell line 92 for more details)
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

					if ( life.getCurrentCell(i,0).equals(Color.black) )
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
				lap++;
				System.out.println(RESOURCEBUNDLE.getString("keytwenty") + lap + ".");
				System.out.println(RESOURCEBUNDLE.getString("keythirtyseven") + life.numberOfCurrentBlackCell() + " " + RESOURCEBUNDLE.getString("keythirtyeight"));

				try {

					Thread.sleep(3000); // allow the user to see the result

				} catch (InterruptedException ie) {

					//Activation of the interrupt flag

					Thread.currentThread().interrupt();

				}

			}

		}
    	
    }
	
	
}
