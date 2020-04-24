package project.part;

import java.awt.Color;
import java.util.*;

public class games {

	private Grille life = new Grille();
	private static Scanner sc = new Scanner(System.in);
	
	public games() {
		
		int line = 0;
		int column = 0;
		System.out.println("Choose the start configuration.");
		do {
			System.out.println("Type the line of the new black cell");
			line = sc.nextInt();
			while(line<0 || line>= life.getLine())
			{
				System.out.println("This line is not included in this grille.\nRetry.");
				line = sc.nextInt();
			}
			System.out.println("Type the column of the new black cell");
			column = sc.nextInt();
			while(column<0 || column>= life.getLine())
			{
				System.out.println("This column is not included in this grille.\nRetry.");
				column = sc.nextInt();
			}
			while(life.getCurrentCell(line, column).equals(Color.black))
			{
				System.out.println("cell already black, retry please");
				System.out.println("Type the line of the new black cell");
				line = sc.nextInt();
				while(line<0 || line>= life.getLine())
				{
					System.out.println("This line is not included in this grille.\nRetry.");
					line = sc.nextInt();
				}
				System.out.println("Type the column of the new black cell");
				column = sc.nextInt();
				while(column<0 || column>= life.getLine())
				{
					System.out.println("This column is not included in this grille.\nRetry.");
					column = sc.nextInt();
				}
			}
			life.setCurrentCell(Color.black, line, column);
			life.setUpdatingCell(Color.black, line, column);
			System.out.println("Type 1 to add a new black cell on the start configuration or 0 to quit.");
			line = sc.nextInt();
			while(line != 1 && line != 0)
			{
				System.out.println("Retry.\nType 1 to add a new black cell on the start configuration or 0 to quit.");
				line = sc.nextInt();
			}
		}while(line == 1);
	}
	
	public void lapAfterLap()
	{
		int lap = 0;
		int NbrNeighbour = 0;
		char next;
		life.printGrille();
		System.out.println("We are on lap "+lap+".");
		System.out.println("Type on 1 to go to the next lap, 0 to quit the simulation");
		next = (char)sc.nextInt();
		while(next != 0 && next != 1)
		{
			System.out.println("Retry.\nType on 1 to go to the next lap, 0 to quit the simulation");
			next = (char)sc.nextInt();
		}
		while(next == 1) {
			for (int i = 1; i<(life.getLine()-1); i++)
			{
				for (int j = 1; (j<life.getColumn()-1); j++)
				{
					if(life.getCurrentCell(i,j).equals(Color.black))
					{
						NbrNeighbour = life.getValue((i-1),(j-1)) + life.getValue((i-1),(j)) + life.getValue((i-1),(j+1)) +
								life.getValue((i),(j-1)) + life.getValue((i),(j+1)) + life.getValue((i+1),(j-1) )+
								life.getValue((i+1),(j)) + life.getValue((i+1),(j+1));
						
						if(NbrNeighbour != 2 && NbrNeighbour != 3)
						{
							life.setUpdatingCell(Color.white, i, j);
						}
					}
					else
					{
						NbrNeighbour = life.getValue((i-1),(j-1)) + life.getValue((i-1),(j)) + life.getValue((i-1),(j+1)) +
								life.getValue((i),(j-1)) + life.getValue((i),(j+1)) + life.getValue((i+1),(j-1) )+
								life.getValue((i+1),(j)) + life.getValue((i+1),(j+1));
						
						if(NbrNeighbour == 3)
						{
							life.setUpdatingCell(Color.black, i, j);
						}
					}
				}
			}
			for(int i = 1; i<life.getLine()-1 ; i++)
			{
				if(life.getCurrentCell(i,0).equals(Color.black))
				{
					NbrNeighbour = life.getValue((i-1),(0)) + life.getValue((i-1),(1)) + life.getValue((i),(1)) +
							life.getValue((i+1),(0)) + life.getValue((i+1),(1));
					
					if(NbrNeighbour != 2 && NbrNeighbour != 3)
					{
						life.setUpdatingCell(Color.white, i, 0);
					}
				}
				else
				{
					NbrNeighbour = life.getValue((i-1),(0)) + life.getValue((i-1),(1)) + life.getValue((i),(1)) +
							life.getValue((i+1),(0)) + life.getValue((i+1),(1));
					
					
					if(NbrNeighbour == 3)
					{
						life.setUpdatingCell(Color.black, i, 0);
					}
				}
				if(life.getCurrentCell(i,(life.getColumn()-1)).equals(Color.black))
				{
					NbrNeighbour = life.getValue((i-1),(life.getColumn()-1)) + life.getValue((i-1),(life.getColumn()-2))
					+ life.getValue((i),(life.getColumn()-2)) + life.getValue((i+1),(life.getColumn()-2))
					+ life.getValue((i+1),(life.getColumn()-1));
					
					if(NbrNeighbour != 2 && NbrNeighbour != 3)
					{
						life.setUpdatingCell(Color.white, i, life.getColumn()-1);
					}
				}
				else
				{
					NbrNeighbour = life.getValue((i-1),(life.getColumn()-1)) + life.getValue((i-1),(life.getColumn()-2))
					+ life.getValue((i),(life.getColumn()-2)) + life.getValue((i+1),(life.getColumn()-2))
					+ life.getValue((i+1),(life.getColumn()-1));
	
					if(NbrNeighbour == 3)
					{
						life.setUpdatingCell(Color.black, i, life.getColumn()-1);
					}
				}
			}
			for (int j = 1; j<life.getColumn()-1; j++)
			{
				if(life.getCurrentCell(0,j).equals(Color.black))
				{
					NbrNeighbour = life.getValue((0),(j-1)) + life.getValue((1),(j-1)) + life.getValue((1),(j)) +
							life.getValue((1),(j+1)) + life.getValue((0),(j+1));
					
					if(NbrNeighbour != 2 && NbrNeighbour != 3)
					{
						life.setUpdatingCell(Color.white, j, 0);
					}
				}
				else
				{
					NbrNeighbour = life.getValue((0),(j-1)) + life.getValue((1),(j-1)) + life.getValue((1),(j)) +
							life.getValue((1),(j+1)) + life.getValue((0),(j+1));
					
					
					if(NbrNeighbour == 3)
					{
						life.setUpdatingCell(Color.black, j, 0);
					}
				}
				if(life.getCurrentCell(life.getLine()-1,(j)).equals(Color.black))
				{
					NbrNeighbour = life.getValue((life.getLine()-1),(j-1)) + life.getValue((life.getLine()-2),(j-1))
					+ life.getValue((life.getLine()-2),(j)) + life.getValue((life.getLine()-2),(j+1))
					+ life.getValue((life.getLine()-1),(j+1));
					
					if(NbrNeighbour != 2 && NbrNeighbour != 3)
					{
						life.setUpdatingCell(Color.white, j, 0);
					}
				}
				else
				{
					NbrNeighbour = life.getValue((life.getLine()-1),(j-1)) + life.getValue((life.getLine()-2),(j-1))
					+ life.getValue((life.getLine()-2),(j)) + life.getValue((life.getLine()-2),(j+1))
					+ life.getValue((life.getLine()-1),(j+1));
	
					if(NbrNeighbour == 3)
					{
						life.setUpdatingCell(Color.black, j, 0);
					}
				}
			}
			if(life.getCurrentCell(0,0).equals(Color.black))
			{
				NbrNeighbour = life.getValue(1,0) + life.getValue(1,1) + life.getValue(0,1);
				if(NbrNeighbour != 2 && NbrNeighbour != 3)
				{
					life.setUpdatingCell(Color.white, 0, 0);
				}
			}
			else
			{
				NbrNeighbour = life.getValue(1,0) + life.getValue(1,1) + life.getValue(0,1);
				if(NbrNeighbour == 3)
				{
					life.setUpdatingCell(Color.black, 0, 0);
				}
			}
			if(life.getCurrentCell(0,life.getColumn()-1).equals(Color.black))
			{
				NbrNeighbour = life.getValue(0,life.getColumn()-2) + life.getValue(1,life.getColumn()-2)
				+ life.getValue(1,life.getColumn()-1);
				if(NbrNeighbour != 2 && NbrNeighbour != 3)
				{
					life.setUpdatingCell(Color.white, 0, life.getColumn()-1);
				}
			}
			else
			{
				NbrNeighbour = life.getValue(0,life.getColumn()-2) + life.getValue(1,life.getColumn()-2)
				+ life.getValue(1,life.getColumn()-1);
				if(NbrNeighbour == 3)
				{
					life.setUpdatingCell(Color.black, 0, life.getColumn()-1);
				}
			}
			if(life.getCurrentCell(life.getLine()-1,0).equals(Color.black))
			{
				NbrNeighbour = life.getValue(life.getLine()-2,0) + life.getValue(life.getLine()-2,0)
				+ life.getValue(life.getLine()-1,1);
				if(NbrNeighbour != 2 && NbrNeighbour != 3)
				{
					life.setUpdatingCell(Color.white, life.getLine()-1, 0);
				}
			}
			else
			{
				NbrNeighbour = life.getValue(life.getLine()-2,0) + life.getValue(life.getLine()-2,0)
				+ life.getValue(life.getLine()-1,1);
				if(NbrNeighbour == 3)
				{
					life.setUpdatingCell(Color.black, life.getLine()-1, 0);
				}
			}
			if(life.getCurrentCell(life.getLine()-1,life.getColumn()-1).equals(Color.black))
			{
				NbrNeighbour = life.getValue(life.getLine()-2,life.getColumn()-1) + life.getValue(life.getLine()-2,life.getColumn()-2)
				+ life.getValue(life.getLine()-1,life.getColumn()-2);
				if(NbrNeighbour != 2 && NbrNeighbour != 3)
				{
					life.setUpdatingCell(Color.white, life.getLine()-1, life.getColumn()-1);
				}
			}
			else
			{
				NbrNeighbour = life.getValue(life.getLine()-2,life.getColumn()-1) + life.getValue(life.getLine()-2,life.getColumn()-2)
				+ life.getValue(life.getLine()-1,life.getColumn()-2);
				if(NbrNeighbour == 3)
				{
					life.setUpdatingCell(Color.black, life.getLine()-1, life.getColumn()-1);
				}
			}
			for(int i = 0; i<life.getLine();i++)
			{
				for(int j = 0; j<life.getColumn();j++)
				{
					life.setCurrentCell(life.getUpdatingCell(i,j),i,j);
				}
			}
			life.printGrille();
			lap++;
			System.out.println("We are on lap "+lap+".");
			System.out.println("Type on 1 to go to the next lap, 0 to quit the simulation");
			next = (char)sc.nextInt();
			while(next != 0 && next != 1)
			{
				System.out.println("Retry.\nType on 1 to go to the next lap, 0 to quit the simulation");
				next = (char)sc.nextInt();
			}
		}
		sc.close();
		System.out.println("end of the simulation");
	}
	
}
