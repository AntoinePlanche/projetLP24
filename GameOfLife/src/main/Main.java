// Antoine Planche LP24 Project ConwayGameOfLife

package main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.JOptionPane;
import type.display.graphic.GraphicalMiniGame;
import type.display.graphic.GraphicalSimulation;
import type.display.nongraphic.NonGraphicMiniGame;
import type.display.nongraphic.NonGraphicSimulation;


public class Main {


	private static ResourceBundle RESOURCEBUNDLE;
	private static String language;
	private final static String BUNDLENAME = new String("information.string");
	private static byte typeOfGraphique;
	private static byte miniGame;




	public static void main(String[] args) throws IOException, Exception
	{

		String[] langue = { "français", "english" };
		int rang = JOptionPane.showOptionDialog(null, "Choose the language of this game.", "Choice of the language",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, langue, langue[1]);

		if ( rang == -1 || rang == 1 )
		{

			language = "en";

		}

		else
		{

			language = "fr";

		}

		RESOURCEBUNDLE = ResourceBundle.getBundle(BUNDLENAME, new Locale(language));

		String[] graphique = { RESOURCEBUNDLE.getString("keyconfigurationone"),
				RESOURCEBUNDLE.getString("keyconfigurationtwo") };
		typeOfGraphique = (byte) JOptionPane.showOptionDialog(null, RESOURCEBUNDLE.getString("keyconfigurationthree"),
				RESOURCEBUNDLE.getString("keyconfigurationfour"), JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, graphique, graphique[0]);

		if ( typeOfGraphique == 0 ) // non graphic application
		{

			Scanner sc = new Scanner(System.in);
			System.out.println(RESOURCEBUNDLE.getString("keythirtyfour"));
			miniGame = (byte) sc.nextInt();

			while ( miniGame != 1 && miniGame != 2 ) // verification loop
			{

				System.out.println(RESOURCEBUNDLE.getString("keythirty"));
				System.out.println(RESOURCEBUNDLE.getString("keythirtyfour"));
				miniGame = (byte) sc.nextInt();

			}

			if ( miniGame == 2 ) // it's the simulation
			{

				NonGraphicSimulation one = new NonGraphicSimulation(RESOURCEBUNDLE, language);
				one.lapAfterLapNonGraphic();
				sc.close();

			}

			else // minigames = 1 so it's the mini game
			{

				NonGraphicMiniGame one = new NonGraphicMiniGame(RESOURCEBUNDLE, language);
				one.lapAfterLapNonGraphic();
				sc.close();

			}

		}

		else
		{

			String[] type = { "simulation", RESOURCEBUNDLE.getString("keyconfigurationfive") };
			miniGame = (byte) JOptionPane.showOptionDialog(null, RESOURCEBUNDLE.getString("keyconfigurationsix"),
					RESOURCEBUNDLE.getString("keyconfigurationseven"), JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, type, type[1]);

			if ( miniGame == -1 || miniGame == 0 ) // graphic simulation
			{

				int line = Integer
						.parseInt(JOptionPane.showInputDialog(null, RESOURCEBUNDLE.getString("keyconfigurationeight"),
								RESOURCEBUNDLE.getString("keyconfigurationnine"), JOptionPane.QUESTION_MESSAGE));

				while ( line <= 1 ) // The line must be greater than 1 to make my algorithm work ( with the corner cell and edge cell )
				{

					JOptionPane.showMessageDialog(null, RESOURCEBUNDLE.getString("keyconfigurationten"),
							RESOURCEBUNDLE.getString("keyconfigurationeleven"), JOptionPane.WARNING_MESSAGE);
					line = Integer.parseInt(
							JOptionPane.showInputDialog(null, RESOURCEBUNDLE.getString("keyconfigurationeight"),
									RESOURCEBUNDLE.getString("keyconfigurationenine"), JOptionPane.QUESTION_MESSAGE));

				}

				int column = Integer
						.parseInt(JOptionPane.showInputDialog(null, RESOURCEBUNDLE.getString("keyconfigurationtwelve"),
								RESOURCEBUNDLE.getString("keyconfigurationthirteen"), JOptionPane.QUESTION_MESSAGE));

				while ( column <= 1 ) // same for column
				{

					JOptionPane.showMessageDialog(null, RESOURCEBUNDLE.getString("keyconfigurationfourteen"),
							RESOURCEBUNDLE.getString("keyconfigurationeleven"), JOptionPane.WARNING_MESSAGE);
					column = Integer.parseInt(JOptionPane.showInputDialog(null,
							RESOURCEBUNDLE.getString("keyconfigurationtwelve"),
							RESOURCEBUNDLE.getString("keyconfigurationthirteen"), JOptionPane.QUESTION_MESSAGE));

				}

				GraphicalSimulation guiSimulation = new GraphicalSimulation(RESOURCEBUNDLE);
				guiSimulation.startSimulation(line, column);

			}

			else // graphic mini game
			{

				JOptionPane.showMessageDialog(null, RESOURCEBUNDLE.getString("keyconfigurationfifteen"),
						RESOURCEBUNDLE.getString("keyconfigurationsixteen"), JOptionPane.INFORMATION_MESSAGE);

				int line = Integer
						.parseInt(JOptionPane.showInputDialog(null, RESOURCEBUNDLE.getString("keyconfigurationeight"),
								RESOURCEBUNDLE.getString("keyconfigurationnine"), JOptionPane.QUESTION_MESSAGE));

				while ( line <= 1 ) // The line must be greater than 1 to make my algorithm work ( with the corner cell and edge cell )
				{

					JOptionPane.showMessageDialog(null, RESOURCEBUNDLE.getString("keyconfigurationten"),
							RESOURCEBUNDLE.getString("keyconfigurationeleven"), JOptionPane.WARNING_MESSAGE);
					line = Integer.parseInt(
							JOptionPane.showInputDialog(null, RESOURCEBUNDLE.getString("keyconfigurationeight"),
									RESOURCEBUNDLE.getString("keyconfigurationenine"), JOptionPane.QUESTION_MESSAGE));

				}

				int column = Integer
						.parseInt(JOptionPane.showInputDialog(null, RESOURCEBUNDLE.getString("keyconfigurationtwelve"),
								RESOURCEBUNDLE.getString("keyconfigurationthirteen"), JOptionPane.QUESTION_MESSAGE));

				while ( column <= 1 ) // same for column
				{

					JOptionPane.showMessageDialog(null, RESOURCEBUNDLE.getString("keyconfigurationfourteen"),
							RESOURCEBUNDLE.getString("keyconfigurationeleven"), JOptionPane.WARNING_MESSAGE);
					column = Integer.parseInt(JOptionPane.showInputDialog(null,
							RESOURCEBUNDLE.getString("keyconfigurationtwelve"),
							RESOURCEBUNDLE.getString("keyconfigurationthirteen"), JOptionPane.QUESTION_MESSAGE));

				}

				GraphicalMiniGame guiMiniGame = new GraphicalMiniGame(RESOURCEBUNDLE);
				guiMiniGame.startSimulation(line, column);

			}

		}

	}


}