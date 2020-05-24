// Antoine Planche LP24 Project ConwayGameOfLife
// This class is useful in the case of the graphical simulation

package type.display.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import world.grid.SimulationGrid;


public class GraphicalSimulation extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private ResourceBundle RESOURCEBUNDLE;
	private int lap = 0; // counter of laps
	private float NbrSecondBetweenLap = 0; // to set the speed of the simulation
	private JLabel numberOfLap; // print the current lap
	private JLabel numberOfBlackCell; // print the current number of back cell
	private JButton[][] cell; // matrix of cell to contain the grid
	private JButton stopSimulation; // allow to stop the mini game
	private JButton startSimulation; // allow to start simulation
	private JButton pausePlay; // allow to put the simulation in pause
	private JPanel grid = new JPanel(); // for the grid
	private JPanel mainInformationPanel = new JPanel(); // to contain configurationPane and simulationPane with OverLay layout
	private JPanel configurationPane = new JPanel(); // for the beginning (choice of the configuration)
	private JPanel simulationPane = new JPanel(); // for the simulation
	private SimulationGrid life; // The object to apply the algorithm
	private ThreadSimulation simulation;




	public GraphicalSimulation(ResourceBundle RESOURCEBUNDLE)
	{

		this.RESOURCEBUNDLE = RESOURCEBUNDLE; // to affect the language

		// configuration of the frame and the panels

		this.setTitle("Conway");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		grid.setBackground(Color.cyan);
		grid.setPreferredSize(new Dimension(1000, 800));
		this.add(grid, "South");

		configurationPane.setBackground(Color.green);
		configurationPane.setPreferredSize(new Dimension(1000, 200));

		simulationPane.setBackground(Color.green);
		simulationPane.setPreferredSize(new Dimension(1000, 200));

		mainInformationPanel.setBackground(Color.gray);
		mainInformationPanel.setPreferredSize(new Dimension(1000, 200));
		mainInformationPanel.setLayout(new OverlayLayout(mainInformationPanel)); // set the Layout of the mainInformation Panel
		mainInformationPanel.add(simulationPane);
		mainInformationPanel.add(configurationPane);

		this.add(mainInformationPanel, "North");

	}




	public void startSimulation(int line, int column)
	{

		cell = new JButton[line][column];
		this.life = new SimulationGrid(line, column);

		for ( int i = 0; i < line; i++ )
		{

			for ( int j = 0; j < column; j++ )
			{

				cell[i][j] = new JButton();
				grid.add(cell[i][j]);
				cell[i][j].addActionListener(this); // I create the grid of cell button

			}

		}

		// I instantiate the object Thread simulation

		simulation = new ThreadSimulation();

		// add and configure the buttons and the label of the simulationPane

		numberOfBlackCell = new JLabel();
		numberOfLap = new JLabel();
		pausePlay = new JButton("Pause");
		pausePlay.addActionListener(this);
		stopSimulation = new JButton(RESOURCEBUNDLE.getString("keygraphicone"));
		stopSimulation.addActionListener(this);
		simulationPane.setLayout(new FlowLayout());
		simulationPane.add(stopSimulation);
		simulationPane.add(pausePlay);
		simulationPane.add(numberOfLap, FlowLayout.LEFT);
		simulationPane.add(numberOfBlackCell, FlowLayout.RIGHT);
		simulationPane.setVisible(false);

		// add and configure the buttons of the configurationPane

		startSimulation = new JButton(RESOURCEBUNDLE.getString("keygraphictwo"));
		startSimulation.addActionListener(this);
		configurationPane.setLayout(new FlowLayout());
		configurationPane.add(startSimulation);
		configurationPane.setVisible(true);

		// I configure the grid panel to host the matrix of button

		grid.setLayout(new GridLayout(line, column));
		grid.setVisible(true);

		// I configure the property of the frame

		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setAlwaysOnTop(false);
		this.setVisible(true);

		// allow to explain to the user how the grid works

		JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicthree"), "Configuration",
				JOptionPane.INFORMATION_MESSAGE);

	}




	public void actionPerformed(ActionEvent e)
	{

		if ( e.getSource() == startSimulation )
		{

			// The user chooses the speed of the simulation

			NbrSecondBetweenLap = 1000
					* Float.parseFloat(JOptionPane.showInputDialog(this, RESOURCEBUNDLE.getString("keygraphicnine"),
							RESOURCEBUNDLE.getString("keygraphicten"), JOptionPane.QUESTION_MESSAGE));

			for ( int i = 0; i < this.life.getLine(); i++ )
			{

				for ( int j = 0; j < this.life.getColumn(); j++ )
				{

					cell[i][j].setEnabled(false); // I disable all the cells buttons

				}

			}

			configurationPane.setVisible(false);
			simulationPane.setVisible(true); // I switch between the two JPanel
			this.mainInformationPanel.repaint();
			this.repaint();

			// Start the thread

			simulation.start();

		}

		else if ( e.getSource() == stopSimulation )
		{

			simulation.stopThread();
			JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicfour"),
					RESOURCEBUNDLE.getString("keygraphicfive"), JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);

		}

		else if ( e.getSource() == pausePlay ) // I distinguish the case where we must pause and the case where we must resume on the same button
		{

			if ( pausePlay.getText().equals("Pause") )
			{

				pausePlay.setText("Play");
				simulation.pause();

			}

			else
			{

				pausePlay.setText("Pause");
				simulation.resumeThread();

			}

		}

		else // so it's the grid button, I update the current color and the updating color (cf class SimulationCell)
		{

			for ( int i = 0; i < this.life.getLine(); i++ )
			{

				for ( int j = 0; j < this.life.getColumn(); j++ )
				{

					if ( e.getSource() == cell[i][j] )
					{

						if ( this.life.getCurrentCell(i, j).equals(Color.white) )
						{

							this.life.setCurrentCell(Color.black, i, j);
							this.life.setUpdatingCell(Color.black, i, j);

						}

						else
						{

							this.life.setCurrentCell(Color.white, i, j);
							this.life.setUpdatingCell(Color.white, i, j);

						}

						this.displayCurrentColor(this.life);

					}

				}

			}

		}

	}




	public void displayCurrentColor(SimulationGrid g)
	{

		for ( int i = 0; i < g.getLine(); i++ )
		{

			for ( int j = 0; j < g.getColumn(); j++ )
			{

				cell[i][j].setBackground(g.getCurrentCell(i, j));

			}

		}

	}




	public class ThreadSimulation extends Thread // nested class
	{


		private boolean continuer = true; // to allow the user to pause the thread
		private boolean finish = false;




		void pause()
		{

			continuer = false;

		}




		synchronized void resumeThread()
		{

			continuer = true;
			notify();

		}




		synchronized void stopThread()
		{

			finish = true;
			notify();

		}




		public void run()
		{

			displayCurrentColor(life);
			numberOfBlackCell.setText(RESOURCEBUNDLE.getString("keygraphicseven") + life.numberOfCurrentBlackCell());
			numberOfLap.setText(RESOURCEBUNDLE.getString("keygraphiceight") + lap);

			try
			{

				Thread.sleep((long) NbrSecondBetweenLap); // allow the user to see the result (just a little bit)

			} catch ( InterruptedException ie )
			{

				// Activation of the interrupt flag

				Thread.currentThread().interrupt();

			}

			while ( !isInterrupted() ) // As long as the thread is not interrupted ...
			{

				try
				{

					synchronized ( this )
					{

						while ( !continuer && !finish )
							wait(); // To put the thread in pause

					}

				}

				catch ( InterruptedException exc )
				{

				}

				if ( finish )
					break; // to stop the thread

				// // the grid displays the new lap I update the lap counter and the JLabels

				displayCurrentColor(life.lapAfterLapGraphicSimulation());
				lap++;
				numberOfBlackCell
						.setText(RESOURCEBUNDLE.getString("keygraphicseven") + life.numberOfCurrentBlackCell());
				numberOfLap.setText(RESOURCEBUNDLE.getString("keygraphiceight") + lap);
				grid.repaint();
				simulationPane.repaint();
				repaint();

				try
				{

					Thread.sleep((long) NbrSecondBetweenLap); // allow the user to see the result (just a little bit)

				} catch ( InterruptedException ie )
				{

					// Activation of the interrupt flag

					Thread.currentThread().interrupt();

				}

			}

		}


	}


}