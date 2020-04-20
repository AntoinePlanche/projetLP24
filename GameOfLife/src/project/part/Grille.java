package project.part;

import java.awt.Color;

public class Grille {

	
	//Attributes
	private int line;
	private int column;
	private cell grille[][] = new cell[line][column];
	
	//Methods
	
	//Constructors
	public Grille() {
		this.line = 1000;
		this.column = 1000;
	}
	
	public Grille(int a, int b) {
		this.line = b;
		this.column = a;
	}
	
	//getters & setters
	public int getLine() {
		return this.line;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public Color getCell(int a, int b) {
		return this.grille[a][b].getCell();
	}
	
	public void setLine(int a) {
		this.line = a;
	}
	
	public void setColumn(int b) {
		this.line = b;
	}
	
	public void setCell(Color c, int a, int b) {
		this.grille[a][b].setCell(c);
	}
}
