package project.part;

import java.awt.Color;
import java.util.*;

public class Games {

	private Grille life;
	private Scanner sc = new Scanner(System.in);
	private String BUNDLENAME= new String("project.part.string");
	private ResourceBundle RESOURCEBUNDLE;
	private String language ;
	private int goalLap;
	
	public Games() {
		
		int line = 0;
		int column = 0;
		System.out.println("Choose the language of this game.\nType en for english or fr for french.");
		language = sc.nextLine();
		while(!language.equals("en") && !language.equals("fr"))
		{
			System.out.println("Choose the language of this game.\nType en for english or fr for french.");
			language = sc.nextLine();
		}
		RESOURCEBUNDLE = ResourceBundle.getBundle(BUNDLENAME,new Locale(language));
		System.out.println(RESOURCEBUNDLE.getString("keyone"));
		System.out.println(RESOURCEBUNDLE.getString("keytwo"));
		line = sc.nextInt();
		while (line <= 0)
		{
			System.out.println(RESOURCEBUNDLE.getString("keyeighteen"));
			System.out.println(RESOURCEBUNDLE.getString("keytwo"));
			line = sc.nextInt();
		}
		System.out.println(RESOURCEBUNDLE.getString("keythree"));
		column = sc.nextInt();
		while (column <= 0)
		{
			System.out.println(RESOURCEBUNDLE.getString("keynineteen"));
			System.out.println(RESOURCEBUNDLE.getString("keythree"));
			column = sc.nextInt();
		}
		life = new Grille(line ,column);
		do {
			System.out.println(RESOURCEBUNDLE.getString("keyfour"));
			line = sc.nextInt();
			while(line <= 0 || line > life.getLine())
			{
				System.out.println(RESOURCEBUNDLE.getString("keyfive"));
				line = sc.nextInt();
			}
			System.out.println(RESOURCEBUNDLE.getString("keysix"));
			column = sc.nextInt();
			while(column <= 0 || column > life.getColumn())
			{
				System.out.println(RESOURCEBUNDLE.getString("keyseven"));
				column = sc.nextInt();
			}
			while(life.getCurrentCell(line-1, column-1).equals(Color.black))
			{
				System.out.println(RESOURCEBUNDLE.getString("keyeight"));
				System.out.println(RESOURCEBUNDLE.getString("keyfour"));
				line = sc.nextInt();
				while(line <= 0 || line > life.getLine())
				{
					System.out.println(RESOURCEBUNDLE.getString("keyfive"));
					line = sc.nextInt();
				}
				System.out.println(RESOURCEBUNDLE.getString("keysix"));
				column = sc.nextInt();
				while(column <= 0 || column > life.getColumn())
				{
					System.out.println(RESOURCEBUNDLE.getString("keyseven"));
					column = sc.nextInt();
				}
			}
			life.setCurrentCell(Color.black, line-1, column-1);
			life.setUpdatingCell(Color.black, line-1, column-1);
			System.out.println(RESOURCEBUNDLE.getString("keynine"));
			line = sc.nextInt();
			while(line != 1 && line != 0)
			{
				System.out.println(RESOURCEBUNDLE.getString("keyten"));
				line = sc.nextInt();
			}
		}while(line == 1);
		System.out.println(RESOURCEBUNDLE.getString("keyeleven"));
		do {
			System.out.println(RESOURCEBUNDLE.getString("keytwelve"));
			line = sc.nextInt();
			while(line <=0 || line > life.getLine())
			{
				System.out.println(RESOURCEBUNDLE.getString("keyfive"));
				line = sc.nextInt();
			}
			System.out.println(RESOURCEBUNDLE.getString("keythirteen"));
			column = sc.nextInt();
			while(column <=0 || column > life.getColumn())
			{
				System.out.println(RESOURCEBUNDLE.getString("keyseven"));
				column = sc.nextInt();
			}
			while(life.getGoalCell(line-1, column-1).equals(Color.black))
			{
				System.out.println(RESOURCEBUNDLE.getString("keyeight"));
				System.out.println(RESOURCEBUNDLE.getString("keytwelve"));
				line = sc.nextInt();
				while(line <=0 || line > life.getLine())
				{
					System.out.println(RESOURCEBUNDLE.getString("keyfive"));
					line = sc.nextInt();
				}
				System.out.println(RESOURCEBUNDLE.getString("keythirteen"));
				column = sc.nextInt();
				while(column <= 0 || column > life.getColumn())
				{
					System.out.println(RESOURCEBUNDLE.getString("keyseven"));
					column = sc.nextInt();
				}
			}
			life.setGoalCell(Color.black, line-1, column-1);
			System.out.println(RESOURCEBUNDLE.getString("keyfourteen"));
			line = sc.nextInt();
			while(line != 1 && line != 0)
			{
				System.out.println(RESOURCEBUNDLE.getString("keyfifteen"));
				line = sc.nextInt();
			}
		}while(line == 1);
		System.out.println(RESOURCEBUNDLE.getString("keysixteen"));
		goalLap = sc.nextInt();
		if(goalLap <= 0)
		{
			System.out.println(RESOURCEBUNDLE.getString("keyseventeen"));
			System.out.println(RESOURCEBUNDLE.getString("keysixteen"));
			goalLap = sc.nextInt();
		}
	}
	
	public void lapAfterLap()
	{
		int lap = 0;
		int NbrNeighbour = 0;
		char next;
		life.printGrille(language);
		System.out.println(RESOURCEBUNDLE.getString("keytwenty") + lap + ".");
		System.out.println(RESOURCEBUNDLE.getString("keytwentyone"));
		next = (char)sc.nextInt();
		while(next != 0 && next != 1)
		{
			System.out.println(RESOURCEBUNDLE.getString("keytwentytwo"));
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
			life.printGrille(language);
			lap++;
			if(lap > goalLap) {
				System.out.println(RESOURCEBUNDLE.getString("keytwentythree"));
				System.exit(0);
			}
			else
			{
				int counter = 0;
				for(int i = 0; i<life.getLine();i++)
				{
					for(int j = 0; j<life.getColumn();j++)
					{
						if(life.getCurrentCell(i, j).equals(life.getGoalCell(i, j)))
						{
							counter++;
						}
					}
				}
				if( counter == (life.getLine()*life.getColumn()) )
				{
					System.out.println(RESOURCEBUNDLE.getString("keytwentyfour") + lap + RESOURCEBUNDLE.getString("keytwentyfive"));
					System.exit(0);
				}
			}
			System.out.println(RESOURCEBUNDLE.getString("keytwenty")+lap+".");
			System.out.println(RESOURCEBUNDLE.getString("keytwentyone"));
			next = (char)sc.nextInt();
			while(next != 0 && next != 1)
			{
				System.out.println(RESOURCEBUNDLE.getString("keytwentytwo"));
				next = (char)sc.nextInt();
			}
		}
		sc.close();
		System.out.println(RESOURCEBUNDLE.getString("keytwentysix"));
	}
	
}
