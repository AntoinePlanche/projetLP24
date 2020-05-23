// Antoine Planche LP24 Project ConwayGameOfLife

package world.cell;

import java.awt.Color;


public class SimulationCell {
	
	//Attributes
	
	private Color updating;
	private Color current ; 
	
	/* two color, updating for the updating tour, current for the current tour.
	Indeed during the execution of the program the cells already updated will influence those not yet updated.
	That is why we need two colors one to save results and one to apply the algorithm   */
	
	//Methods
	
	//Constructor
	
	public SimulationCell() 
	{
		
		this.current = Color.white;
		this.updating = Color.white;
		
	}
	
	
	// Getter & Setter
	
	public Color getCurrentCell()
	{
		
		return this.current;
		
	}
	
	
	
	
	public Color getUpdatingCell() 
	{
		
		return this.updating;
		
	}
	
	
	
	
	public void setCurrentCell(Color c) 
	{
		
		if ( c.equals(Color.white) )
		{
			
			this.current = Color.white;
			
		}
		
		else
		{
			
			this.current = Color.black;
			
		}
		
	}
	
	
	
	
	public void setUpdatingCell(Color c) 
	{
		
		if ( c.equals(Color.white) )
		{
			
			this.updating = Color.white;
			
		}
		
		else
		{
			
			this.updating = Color.black;
			
		}
		
	}
	
	
	
	
	public int getCurrentValue() 
	{
		
		if ( this.current.equals(Color.white) ) //we assign a value to each color to facilitate the algorithm
		{
			return 0;
			
		}
		
		else
		{
			
			return 1;
			
		}
		
	}
	
	
	
	
	public String printCell(String language) 
	{
		
		if ( language.equals("en") )
		{
			
			if ( this.getCurrentCell().equals(Color.black) )
			{
				
				return "black";
				
			}
			
			else
			{
				
				return "white";
				
			}
			
		}
		
		else
		{
			
			if ( this.getCurrentCell().equals(Color.black) )
			{
				
				return "noire";
				
			}
			
			else 
			{
				
				return "blanche";
				
			}
			
		}
		
	}
	
	
}