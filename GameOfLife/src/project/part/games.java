package project.part;

import java.awt.Color;
import java.util.*;

public class games {

	private Grille life = new Grille();
	
	public games() {
		for(int j = 499;j<502;j++)
		{
			life.setCurrentCell(Color.black,500,j);
			life.setUpdatingCell(Color.black,500,j);
		}
	}
	
	public void lapAfterLap()
	{
		int lap = 0;
		int NbrNeighbour = 0;
		life.printGrille();
		System.out.println("We are on lap "+lap+".");
		Scanner sc = new Scanner(System.in);
		System.out.println("Type on \"n\" to go to the next lap");
		char next = sc.nextLine().charAt(0);
		while(next == 'n') {
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
			System.out.println("Type on \"n\" to go to the next lap");
			next = sc.nextLine().charAt(0);
		}
		sc.close();
	}
	
}
