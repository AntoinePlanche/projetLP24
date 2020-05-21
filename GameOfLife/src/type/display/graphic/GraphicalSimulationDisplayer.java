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
import abstractgrid.Grille;



public class GraphicalSimulationDisplayer extends JFrame implements ActionListener
{
   
	private static final long serialVersionUID = 1L;
	private ResourceBundle RESOURCEBUNDLE;
	private int lap = 0;
	private JLabel numberOfLap;
	private JLabel numberOfBlackCell;
	private JButton[][] cell;
	private JButton stopSimulation;
	private JButton startSimulation;
	private JButton pausePlay;
    private JPanel grid = new JPanel(); // for the grid
    private JPanel mainInformationPanel = new JPanel(); //to contain configurationPane and simulationPane with OverLay layout
    private JPanel configurationPane = new JPanel(); // for the beginning (choice of the configuration)
    private JPanel simulationPane = new JPanel(); //for the simulation
    private Grille life;
    private ThreadSimulation simulation;
    
    
    public GraphicalSimulationDisplayer(ResourceBundle RESOURCEBUNDLE)
    {
        
    	this.RESOURCEBUNDLE = RESOURCEBUNDLE;
    	
    	this.setTitle("Conway");
        this.setSize(1000, 1000); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        grid.setBackground(Color.cyan);
        grid.setPreferredSize(new Dimension(1000,800));
        this.add(grid,"South");
        
        configurationPane.setBackground(Color.green);
        configurationPane.setPreferredSize(new Dimension(1000,200));
        
        simulationPane.setBackground(Color.green);
        simulationPane.setPreferredSize(new Dimension(1000,200));
        
        mainInformationPanel.setBackground(Color.gray);
        mainInformationPanel.setPreferredSize(new Dimension(1000,200));
        mainInformationPanel.setLayout(new OverlayLayout(mainInformationPanel));
        mainInformationPanel.add(simulationPane);
        mainInformationPanel.add(configurationPane);
        
        this.add(mainInformationPanel,"North");     
        
    }
    
    
    
    
    
    public void startSimulation(int line, int column)
    {
    
        cell = new JButton[line][column];
        this.life = new Grille(line,column);
        
        for (int i=0; i<line; i++)
        {
        	for (int j=0; j<column; j++)
        	{
        		
        		cell[i][j] = new JButton();
        		grid.add(cell[i][j]);
        		cell[i][j].addActionListener(this);
        	}
                
        }
        
        simulation = new ThreadSimulation();
       
        
        numberOfBlackCell = new JLabel();
        numberOfLap = new JLabel();
        pausePlay = new JButton("Pause");
        pausePlay.addActionListener(this);
        stopSimulation = new JButton("STOP");
		stopSimulation.addActionListener(this);
		simulationPane.setLayout(new FlowLayout());
		simulationPane.add(stopSimulation);
		simulationPane.add(pausePlay,FlowLayout.LEFT);
		simulationPane.add(numberOfLap,FlowLayout.LEFT);
		simulationPane.add(numberOfBlackCell,FlowLayout.RIGHT);
		simulationPane.setVisible(false);
		
        startSimulation = new JButton("START");
        startSimulation.addActionListener(this);
        configurationPane.setLayout(new FlowLayout());
        configurationPane.add(startSimulation);
        
        simulationPane.setVisible(false);
        configurationPane.setVisible(true);
        
        grid.setLayout(new GridLayout(this.life.getLine(),this.life.getColumn()));
        grid.setVisible(true);
        
        this.setLocationRelativeTo(null);
	    this.setResizable(true);
	    this.setAlwaysOnTop(true);
        this.setVisible(true);
        
        JOptionPane.showMessageDialog(this, "choisissez la configuration de départ en cliquant sur les cases", "Configuration", JOptionPane.INFORMATION_MESSAGE);
    
    }

	
	
    
    public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == startSimulation)
        {
        	
        		for (int i=0; i<this.life.getLine(); i++)
                {
                	
        			for (int j=0; j<this.life.getColumn(); j++)
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
		
		
		else if(e.getSource() == stopSimulation)
		{  
			
	        simulation.interrupt(); 
	        
	        // Wait for the thread to finish
	        
	        try {
	        	
				simulation.join();
				
			} catch (InterruptedException e1) {
				
				System.exit(1);
				
			} 
	        
			JOptionPane.showMessageDialog(this, "Vous quittez la simulation", "Dommage", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
			
		}
		
		else if (e.getSource() == pausePlay)
		{
			
			if(pausePlay.getText().equals("Pause"))
			{
				
				pausePlay.setText("Play");
				simulation.pause(); 
		       
			}
			
			else
			{
				
				pausePlay.setText("Pause");
				simulation.reprendre();
			
			}
			
		}
		
        else
        {
        	
        	for (int i=0; i<this.life.getLine(); i++)
            {
            	
        		for (int j=0; j<this.life.getColumn(); j++)
            	{
            		
        			if (e.getSource() == cell[i][j])
            		{
            			
        				if( this.life.getCurrentCell(i,j).equals(Color.white))
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
  
	
	
    
    public void  displayCurrentColor(Grille g)
    {
        
        for (int i=0; i<g.getLine(); i++)
        {
        	
        	for(int j=0; j<g.getColumn(); j++)
        	{
        		
        		cell[i][j].setBackground(g.getCurrentCell(i, j)); 
        	
        	}
        
        }   
   
    }
	
	
	public class ThreadSimulation extends Thread //nested class
    {
		
		private boolean continuer = true;
		
		void pause()
	    {
	     
			continuer = false;
	   
	    }

		synchronized void reprendre()
	    {
	        
			continuer = true;
	        notify();
	        
	    }
	  
		public void run() 
    	{
    		
    		
    		try {
    			
    			Thread.sleep(100); // allow the user to see the result
    			
    		} catch (InterruptedException ie) {
    			
    			//Activation of the interrupt flag
    			
    			Thread.currentThread().interrupt();
    			
    		}
    		
    		while ( !isInterrupted())  // As long as the thread is not interrupted ...
    		{
    			
    			try
      		  	{
    				
    				synchronized(this)
      		      	{	   
    					
    					while (!continuer) wait();
      		      	
      		      	}
      		  	
      		  	}	
    	
    			catch (InterruptedException exc) {}

    			displayCurrentColor(life.lapAfterLapGraphicSimulation());
    			lap++;
    			numberOfBlackCell.setText("Nombre de cellule noir : " + life.numberOfCurrentBlackCell());
    			numberOfLap.setText("Tour : " + lap);
    			grid.repaint();
    			simulationPane.repaint();
    	        repaint();
    	        
    	        try {
    				
    				Thread.sleep(100); // allow the user to see the result
    				
    			} catch (InterruptedException ie) {
    				
    				//Activation of the interrupt flag
        			
        			Thread.currentThread().interrupt();
    				
    			}
    	        
    		}
    	
    	}
    
    }

}



    
    