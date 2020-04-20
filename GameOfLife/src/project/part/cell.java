package project.part;

import java.awt.Color;

public class cell {
	
	//Attributes
	
	private Color c;
	
	//Methods
	
	//Constructor
	public cell() {
		this.c = Color.white;
	}
	
	
	
	// Getter & Setter
	
	public Color getCell() {
		return this.c;
	}
	
	public void setCell(Color c) {
		if(c.equals(Color.white))
		{
			this.c = Color.white;
		}
		else
		{
			this.c = Color.black;
		}
	}
	
	
	
	
}
