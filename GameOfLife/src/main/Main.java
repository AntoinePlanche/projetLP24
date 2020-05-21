// Antoine Planche LP24 Project ConwayGameOfLife

package main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import type.display.graphic.GraphicalSimulationDisplayer;
import type.display.nongraphic.NonGraphicGame;

public class Main {
	
	
	private static ResourceBundle RESOURCEBUNDLE;
	private static String language;
	private final static String BUNDLENAME= new String("information.string");
	private static byte typeOfGraphique;
	
	public static void main(String[] args) throws IOException, Exception
	{
		
		String[] langue = {"français","english"};
		int rang = JOptionPane.showOptionDialog(null, "Choose the language of this game.", "Choice of the language", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, langue, langue[1]);
		if (rang == -1 || rang == 1)
		{
			language = "en";
			
		} 
		else 
		{
			language = "fr";
		}
	    RESOURCEBUNDLE = ResourceBundle.getBundle(BUNDLENAME,new Locale(language));
		
		 String[] graphique = {"console","interface graphique"};
		 typeOfGraphique = (byte)JOptionPane.showOptionDialog(null, "Quel type de graphique voulez-vous", "Choice of the graphique type.", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, graphique, langue[0]);
			
		 if(typeOfGraphique == 0)
		 {
			 NonGraphicGame one = new NonGraphicGame(RESOURCEBUNDLE);	
			 one.lapAfterLapNonGraphic();
		 }
		 else
		 {
			 	String[] type = {"simulation","mini-jeux"};
				rang = JOptionPane.showOptionDialog(null, "Quel est le type de jeux", "type de jeux", 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, type, type[1]);
				if (rang == -1 || rang == 0)
				{
					
					int line = Integer.parseInt(JOptionPane.showInputDialog(null,"combien y a t-il de ligne ?", "nombre de ligne", JOptionPane.QUESTION_MESSAGE));
					
					while ( line <= 1 ) // The line must be greater than 1 to make my algorithm work ( with the corner cell and edge cell )
					{
						
						JOptionPane.showMessageDialog(null, "le nombre de ligne doit être supérieur à 1","Error", JOptionPane.WARNING_MESSAGE);
						line = Integer.parseInt(JOptionPane.showInputDialog(null,"combien y a t-il de ligne ?", "nombre de ligne", JOptionPane.QUESTION_MESSAGE));
						
					}
					
					int column = Integer.parseInt(JOptionPane.showInputDialog(null,"combien y a t'il de colonne ?", "Nombre de colonne", JOptionPane.QUESTION_MESSAGE));
					
					while ( column <= 1 )  // same for column
					{
						
						JOptionPane.showMessageDialog(null, "le nombre de colonne doit être supérieur à 1","Warning", JOptionPane.WARNING_MESSAGE);
						column = Integer.parseInt(JOptionPane.showInputDialog(null,"combien y a t'il de colonne ?", "Nombre de colonne", JOptionPane.QUESTION_MESSAGE));
						
					}
					GraphicalSimulationDisplayer gui = new GraphicalSimulationDisplayer(RESOURCEBUNDLE);
					gui.startSimulation(line,column);
				} 
				else 
				{
					
					JOptionPane.showMessageDialog(null, "master game choisissez la configuration de départ", "Game-Master", JOptionPane.INFORMATION_MESSAGE);
					
				}
		 }
			 
		
	}
	
}
