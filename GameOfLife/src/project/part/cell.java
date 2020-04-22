package project.part;

import java.awt.Color;

public class cell {
	
	//Attributes
	
	private Color updating;
	private Color current ; // two color, updating for the updating tour and current for the current tour
	//Methods
	
	//Constructor
	public cell() {
		this.current = Color.white;
		this.updating = Color.black;
	}
	
	
	
	// Getter & Setter
	
	public Color getCurrentCell() {
		return this.current;
	}
	
	public Color getUpdatingCell() {
		return this.updating;
	}
	
	public void setCurrentCell(Color c) {
		if(c.equals(Color.white))
		{
			this.current = Color.white;
		}
		else
		{
			this.current = Color.black;
		}
	}
	
	public void setUpdatingCell(Color c) {
		if(c.equals(Color.white))
		{
			this.updating = Color.white;
		}
		else
		{
			this.updating = Color.black;
		}
	}
	
	
	public int getCurrentValue() {
		if(this.current.equals(Color.white))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
}
