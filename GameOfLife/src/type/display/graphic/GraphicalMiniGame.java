// Antoine Planche LP24 Project ConwayGameOfLife
// This class is useful in the case of the graphical simulation

package type.display.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import world.grid.MiniGameGrid;


public class GraphicalMiniGame extends JFrame implements ActionListener {
   
	
	private static final long serialVersionUID = 1L;
	private ResourceBundle RESOURCEBUNDLE;
	private int lap = 0; // counter of laps
	private static FileWriter writer; // allow to write the score in the score file
	private int goalLap; // stock the max lap to reach the "target" configuration
	private byte difficulty; // byte to avoid taking up too much memory
	private JLabel numberOfLap; // print the current lap
	private JLabel numberOfBlackCell; // print the current number of back cell
	private JButton[][] cell; //matrix of cell to contain the grid
	private JButton stopMiniGame; // allow to stop the mini game
	private JButton startMiniGame; // allow to start the mini game
	private JButton startPlayerConfiguration; // allow to go to the choice of the starting simulation of the player
	private JButton nextLap; // allow to go to the next lap
	private JButton changeOneCell; // allow to change one cell each lap during the mini game if the difficulty is easy
    private JPanel grid = new JPanel(); // for the grid
    private JPanel mainInformationPanel = new JPanel(); //to contain configurationPane and simulationPane with OverLay layout
    private JPanel configurationMasterGamePane = new JPanel(); // for the choice of the "target" configuration
    private JPanel configurationPlayerPane = new JPanel(); //for the choice of the player configuration
    private JPanel miniGamePane = new JPanel(); // for the mini game
    private MiniGameGrid life; // The object to apply the algorithm
    
    
    public GraphicalMiniGame(ResourceBundle RESOURCEBUNDLE)
    {
        
    	this.RESOURCEBUNDLE = RESOURCEBUNDLE; // to affect the language
    	
    	// configuration of the frame and the panels
    	
    	this.setTitle("Conway");
        this.setSize(1000, 1000); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        grid.setBackground(Color.cyan);
        grid.setPreferredSize(new Dimension(1000,800));
        this.add(grid,"South");
        
        configurationMasterGamePane.setBackground(Color.green);
        configurationMasterGamePane.setPreferredSize(new Dimension(1000,200));
        
        configurationPlayerPane.setBackground(Color.green);
        configurationPlayerPane.setPreferredSize(new Dimension(1000,200));
        
        miniGamePane.setBackground(Color.green);
        miniGamePane.setPreferredSize(new Dimension(1000,200));
        
        mainInformationPanel.setBackground(Color.gray);
        mainInformationPanel.setPreferredSize(new Dimension(1000,200));
        mainInformationPanel.setLayout(new OverlayLayout(mainInformationPanel)); // set the Layout of the mainInformation Panel
        mainInformationPanel.add(configurationMasterGamePane);
        mainInformationPanel.add(configurationPlayerPane);
        mainInformationPanel.add(miniGamePane);
        
        this.add(mainInformationPanel,"North");     
        
    }
    
    
    
     
    public void startSimulation(int line, int column) throws IOException
    {
    
        cell = new JButton[line][column];
        this.life = new MiniGameGrid(line,column);
        
        for ( int i=0; i<line; i++ )
        {
        	for ( int j=0; j<column; j++ )
        	{
        		
        		cell[i][j] = new JButton();
        		grid.add(cell[i][j]);
        		cell[i][j].addActionListener(this); // I create the grid of cell button
        		
        	}
                
        }
        
        // add and configure the buttons and the label of the miniGamePane
        
        numberOfBlackCell = new JLabel();
        numberOfLap = new JLabel();
        nextLap = new JButton(RESOURCEBUNDLE.getString("keygraphicminigameone"));
        nextLap.addActionListener(this);
        stopMiniGame = new JButton(RESOURCEBUNDLE.getString("keygraphicone"));
        stopMiniGame.addActionListener(this);
        changeOneCell = new JButton(RESOURCEBUNDLE.getString("keygraphicminigamethree"));
        changeOneCell.addActionListener(this);
        miniGamePane.setLayout(new FlowLayout());
        miniGamePane.add(nextLap);
        miniGamePane.add(changeOneCell);
        miniGamePane.add(stopMiniGame);
        miniGamePane.add(numberOfLap,FlowLayout.LEFT);
        miniGamePane.add(numberOfBlackCell,FlowLayout.RIGHT);
        miniGamePane.setVisible(false);
		
        // add and configure the buttons of the configurationMasterGamePane
        
		startPlayerConfiguration = new JButton(RESOURCEBUNDLE.getString("keygraphicminigamefour"));
		startPlayerConfiguration.addActionListener(this);
		configurationMasterGamePane.setLayout(new FlowLayout());
		configurationMasterGamePane.add(startPlayerConfiguration);
		configurationMasterGamePane.setVisible(true);
		
		// add and configure the buttons of the configurationPlayerPane
		
		startMiniGame = new JButton(RESOURCEBUNDLE.getString("keygraphictwo"));
		startMiniGame.addActionListener(this);
		configurationPlayerPane.setLayout(new FlowLayout());
		configurationPlayerPane.add(startMiniGame);
		configurationPlayerPane.setVisible(false);
        
        // I configure the grid panel to host the matrix of button
		
		grid.setLayout(new GridLayout(line,column));
        grid.setVisible(true);
        
        // I configure the property of the frame
        
        this.setLocationRelativeTo(null);
	    this.setResizable(true);
	    this.setAlwaysOnTop(false);
        this.setVisible(true);
        
        // allow to explain to the master game how the grid works
        
        JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicminigamesix"), "Configuration", JOptionPane.INFORMATION_MESSAGE);
        
        // I instantiate writer with the write file
        
        writer = new FileWriter("resources\\information\\score.txt",true);
    
    }

	
	
    
    public void actionPerformed(ActionEvent e)
	{
		
		if ( e.getSource() == startPlayerConfiguration )
        {
			
			// the master game chooses the number of maximum lap to reach his configuration
			
			goalLap = Integer.parseInt(JOptionPane.showInputDialog(this, RESOURCEBUNDLE.getString("keygraphicminigameseven"), RESOURCEBUNDLE.getString("keygraphicminigameeight"), JOptionPane.QUESTION_MESSAGE));
			
			while( goalLap < 1 ) // verification loop
			{
				
				JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicminigamenine"), RESOURCEBUNDLE.getString("keyconfigurationeleven"), JOptionPane.WARNING_MESSAGE);
				goalLap = Integer.parseInt(JOptionPane.showInputDialog(this, RESOURCEBUNDLE.getString("keygraphicminigameseven"), RESOURCEBUNDLE.getString("keygraphicminigameeight"), JOptionPane.QUESTION_MESSAGE));
				 
			}
			
			// allow to explain to the player how to choose his starting configuration
			
			JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicminigameeleven"), "Configuration", JOptionPane.INFORMATION_MESSAGE);
			
			configurationMasterGamePane.setVisible(false); 
			configurationPlayerPane.setVisible(true); // I switch between these two JPanels
			this.displayCurrentColor(life);
			this.grid.repaint();
    		this.mainInformationPanel.repaint();
    		this.repaint();
    		
        }
		
		else if( e.getSource() == stopMiniGame )
		{  
			
			JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicfour"), RESOURCEBUNDLE.getString("keygraphicfive"), JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
			
		}
		
		else if ( e.getSource() == startMiniGame )
		{
			
			// allow to explain to the player what is the difference between the two levels of difficulty
			
			JOptionPane.showMessageDialog(this, RESOURCEBUNDLE.getString("keygraphicminigametwelve"), RESOURCEBUNDLE.getString("keygraphicminigamethirteen"), JOptionPane.INFORMATION_MESSAGE);
			
			// The player choose his level of difficulty
			
			String[] levelOfDifficulty = { RESOURCEBUNDLE.getString("keygraphicminigamefourteen"), RESOURCEBUNDLE.getString("keygraphicminigamefifteen") };
			difficulty = (byte)JOptionPane.showOptionDialog(this, RESOURCEBUNDLE.getString("keygraphicminigamesixteen"), RESOURCEBUNDLE.getString("keygraphicminigamethirteen"), 
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, levelOfDifficulty, levelOfDifficulty[1]);
		
			if( difficulty == 1 ) // If it's the hard difficulty I disable the changeOneCell button
			{
				
				changeOneCell.setVisible(false);
				changeOneCell.setEnabled(false);
				
			}
			
			for ( int i=0; i<this.life.getLine(); i++ )
            {
            	
    			for ( int j=0; j<this.life.getColumn(); j++ )
            	{
            		
    				cell[i][j].setEnabled(false); // I disable all the cells buttons
    				
            	}
                    
            }
			
			// I display the information number of black cell and the current lap
			
			this.numberOfBlackCell.setText(RESOURCEBUNDLE.getString("keygraphicseven") + life.numberOfCurrentBlackCell());
			this.numberOfLap.setText(RESOURCEBUNDLE.getString("keygraphiceight") + lap);
			
			configurationPlayerPane.setVisible(false); 
			miniGamePane.setVisible(true); // I switch between the two JPanel
    		this.mainInformationPanel.repaint();
    		this.repaint();
    		
		}
		
		else if ( e.getSource() == nextLap )
		{
			
			try {
				
				this.displayCurrentColor(life.lapAfterLapGraphicMiniGame(writer, lap, goalLap, RESOURCEBUNDLE)); // the grid displays the new lap
				
			} catch (IOException e1) {
				
				System.exit(1); // error
				
			}
			
			// I update the lap counter and the JLabels
			
			this.lap++;
			this.numberOfBlackCell.setText(RESOURCEBUNDLE.getString("keygraphicseven") + life.numberOfCurrentBlackCell());
			this.numberOfLap.setText(RESOURCEBUNDLE.getString("keygraphiceight") + lap);
			
			if( difficulty == 0 ) // If it's the easy difficulty I allow to change  a new cell each lap
			{
				
				changeOneCell.setEnabled(true); 
				changeOneCell.setVisible(true);
				
			}
			
			this.grid.repaint();
			this.miniGamePane.repaint();
	        repaint();
			
		}
		
		else if ( e.getSource() == changeOneCell )
		{
			
			for (int i=0; i<this.life.getLine(); i++)
            {
            	
    			for (int j=0; j<this.life.getColumn(); j++)
            	{
            		
    				cell[i][j].setEnabled(true); // I disable all the cells buttons to avoid the player can change many cells during one lap
    				
            	}
                    
            }
			
			changeOneCell.setEnabled(false);
			changeOneCell.setVisible(false);
			
			
		}
		
        else // so it's the grid button
        {
        	
        	if( configurationPlayerPane.isVisible() ) // If the configurationPlayerPane is  visible, I update the current color and the updating color (cf class MiniGameCell)
        	{
            	for ( int i=0; i<this.life.getLine(); i++ )
                {
                	
            		for ( int j=0; j<this.life.getColumn(); j++ )
                	{
                		
            			if ( e.getSource() == cell[i][j] )
                		{
                			
            				if( this.life.getCurrentCell(i,j).equals(Color.white) )
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
        	
        	else if ( miniGamePane.isVisible() ) // if miniGamePane is visible, it's the same than above just i enable all the cell to avoid the player change many cell during one lap
        	{
        		
        		for ( int i=0; i<this.life.getLine(); i++ )
                {
                	
            		for ( int j=0; j<this.life.getColumn(); j++ )
                	{
                		
            			if ( e.getSource() == cell[i][j] )
                		{
                			
            				if( this.life.getCurrentCell(i,j).equals(Color.white) )
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
        		
        		for ( int i=0; i<this.life.getLine(); i++ )
                {
                	
        			for ( int j=0; j<this.life.getColumn(); j++)
                	{
                		
        				cell[i][j].setEnabled(false); // I disable all the cells buttons
        				
                	}
                        
                }	
        		
        	}
        	
        	else // so the configuration configurationMasterGamePane who is visible, I just update the color of the goal cell
        	{
        		
        		for ( int i=0; i<this.life.getLine(); i++ )
                {
                	
            		for ( int j=0; j<this.life.getColumn(); j++ )
                	{
                		
            			if ( e.getSource() == cell[i][j ])
                		{
                			
            				if( this.life.getGoalCell(i,j).equals(Color.white) )
                        	{
                				
            					this.life.setGoalCell(Color.black, i, j);
            					
                        	}
            				
                        	else
                        	{
                        		
                        		this.life.setGoalCell(Color.white, i, j);
                        		
                        	}
            				
                        	this.displayGoalColor(this.life);
                		
                		}
                	}
                        
                }
        		
        	}
        	
        }
		
	}
  
	
	
    
    public void  displayCurrentColor( MiniGameGrid g ) // allow to update the grid with the current color
    {
        
        for ( int i=0; i<g.getLine(); i++ )
        {
        	
        	for( int j=0; j<g.getColumn(); j++ )
        	{
        		
        		cell[i][j].setBackground(g.getCurrentCell(i, j)); 
        	
        	}
        
        }   
   
    }
    
    
    
    
    public void  displayGoalColor(MiniGameGrid g) // allow to update the grid with the goal color
    {
        
        for ( int i=0; i<g.getLine(); i++ )
        {
        	
        	for( int j=0; j<g.getColumn(); j++ )
        	{
        		
        		cell[i][j].setBackground(g.getGoalCell(i, j)); 
        	
        	}
        
        }   
   
    }
	
	
}