// Antoine Planche LP24 Project ConwayGameOfLife

package world.cell;

import java.awt.Color;


public class MiniGameCell extends SimulationCell {
		
	//Attributes

	private Color goal;
	
	/* three color, updating for the updating tour, current for the current tour and purpose to compare with the purpose configuration.
	Indeed during the execution of the program the cells already updated will influence those not yet updated.
	That is why we need two colors one to save results and one to apply the algorithm   */
	
	//Methods
	
	//Constructor
	
	public MiniGameCell() 
	{
		
		super();
		this.goal = Color.white;
		
	}
	
	
	// Getter & Setter
	
	public Color getGoalCell() 
	{
		
		return this.goal;
		
	}
	
	
	
	
	public void setGoalCell(Color c) 
	{
		
		if ( c.equals(Color.white) )
		{
			
			this.goal = Color.white;
			
		}
		
		else
		{
			
			this.goal = Color.black;
			
		}
		
	}
	
		
}
