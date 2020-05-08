// Antoine Planche LP24 Projet ConwayGameOfLife

package main;

import java.awt.Color;

public class Cell {
	
	//Attributes
	
	private Color updating;
	private Color current ; 
	private Color goal;
	
	/* three color, updating for the updating tour, current for the current tour and purpose to compare with the purpose configuration.
	Indeed during the execution of the program the cells already updated will influence those not yet updated.
	That is why we need two colors one to save results and one to apply the algorithm   */
	
	//Methods
	
	//Constructor
	public Cell() {
		this.current = Color.white;
		this.updating = Color.white;
		this.goal = Color.white;
	}
	
	
	
	// Getter & Setter
	
	public Color getCurrentCell() {
		return this.current;
	}
	
	public Color getUpdatingCell() {
		return this.updating;
	}
	
	public Color getGoalCell() {
		return this.goal;
	}
	
	public void setGoalCell(Color c) {
		if ( c.equals(Color.white) )
		{
			
			this.goal = Color.white;
			
		}
		
		else
		{
			
			this.goal = Color.black;
			
		}
		
	}
	
	public void setCurrentCell(Color c) {
		if ( c.equals(Color.white) )
		{
			
			this.current = Color.white;
			
		}
		
		else
		{
			
			this.current = Color.black;
			
		}
		
	}
	
	public void setUpdatingCell(Color c) {
		if ( c.equals(Color.white) )
		{
			
			this.updating = Color.white;
			
		}
		
		else
		{
			
			this.updating = Color.black;
			
		}
		
	}
	
	
	public int getCurrentValue() {
		if ( this.current.equals(Color.white) ) //we assign a value to each color to facilitate the algorithm
		{
			return 0;
			
		}
		
		else
		{
			
			return 1;
			
		}
		
	}
	
	public String printCell(String language) {
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
