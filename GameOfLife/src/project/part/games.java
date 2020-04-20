package project.part;

import java.awt.Color;

public class games {

	private Grille life = new Grille();
	
	public void startConfiguration() {
		for(int i = 498;i<503;i++)
		{
			life.setCell(Color.cyan,500,i);
		}
	}
	
	public void lapAfterLap()
	{
		int lap = 0;
		while(true) {
			for (int i = 0; i<life.getLine(); i++)
			{
				for (int j = 0; j<life.getColumn(); j++)
				{
					
				}
			}
			lap++;
			System.out.println(lap);
		}
		
		
	}
	
}
