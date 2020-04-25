package project.part;

import java.awt.Color;

public class Grille {

	
	//Attributes
	private int line;
	private int column;
	private cell grille[][];
	
	//Methods
	
	//Constructors
	public Grille() {
		this.line = 1000;
		this.column = 1000;
		grille = new cell[line][column];
 		for (int i = 0;i<this.line; i++)
		{
			for(int j = 0; j<this.column; j++)
			{
				grille[i][j] = new cell();
			}
		}
	}
	
	public Grille(int a, int b) {
		this.line = b;
		this.column = a;
		grille = new cell[line][column];
 		for (int i = 0;i<this.line; i++)
		{
			for(int j = 0; j<this.column; j++)
			{
				grille[i][j] = new cell();
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
		return this.grille[a][b].getCurrentCell();
	}
	
	public Color getUpdatingCell(int a,int b) {
		return this.grille[a][b].getUpdatingCell();
	}
	
	public void setLine(int a) {
		this.line = a;
	}
	
	public void setColumn(int b) {
		this.line = b;
	}
	
	public void setUpdatingCell(Color c, int a, int b) {
		this.grille[a][b].setUpdatingCell(c);
	}
	
	public void setCurrentCell(Color c, int a, int b) {
		this.grille[a][b].setCurrentCell(c);
	}
	
	public int getValue(int a, int b) {
		return this.grille[a][b].getCurrentValue();
	}
	
	public void printGrille() {
		for(int i = 0; i<this.line; i++)
		{
			for (int j = 0; j<this.column; j++)
			{
				System.out.println("the cell["+(i+1)+"]["+(j+1)+"] is "+this.grille[i][j].printCell());
			}
		}
	}
}
