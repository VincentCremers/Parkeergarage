package nl.hanze.t12.life.view;

import java.awt.*;


import nl.hanze.t12.life.logic.Location;
import nl.hanze.t12.life.logic.LifeLogic;
import nl.hanze.t12.life.logic.Car;
 
	
	
public class FieldView extends AbstractView {
	private static final long serialVersionUID = -8200251211832614969L;
	private Dimension size; 
    private Image carParkImage;

	public FieldView(LifeLogic life) {
		super(life);
		setSize(200,200);
	}
	
	
	 public void paintComponent(Graphics g) {
     // Create a new car park image if the size has changed.
     if (!size.equals(getSize())) {
         size = getSize();
         carParkImage = createImage(size.width, size.height);
     }
	Graphics graphics = carParkImage.getGraphics();
         for(int row = 0; row < life.getNumberOfRows(); row++) {
             for(int place = 0; place < life.getNumberOfPlaces(); place++) {
             	if(life.getCarAtLocation(row, place)==true) {
             		Color color = life.cars[row][place] != null ? Color.white : life.cars[row][place].getColor();
             	}
             	Car car = life.cars[row][place];
                Location location = new Location(row, place);
                drawPlace(graphics, location, color);
             }
         } 
         repaint();
     }
    
     
         
	 private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

		 
		 
		 
		 
		 
		 
		 
		 
		 
		/*
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLUE);
			Car[][] cars= life.getState();
			System.out.println("in de draw component");
			if (cars==null) return;
			
			int margin=100-cars.length*2;
			for(int i=0;i<cars.length;i++){
				for(int j=0;j<cars.length;j++) {
					if(cars[i][j].getHasToPay()== true) {
						g.setColor(Color.RED);
						g.fillRect(margin+10*i, margin+10*j, 3, 3);
					}
					else {
					g.fillRect(margin+10*i, margin+10*j, 3, 3);
					}
	 			}
			}
		}
		*/
							
				
	
		//g.fillRect(margin+4*i, margin+4*j, 3, 3)
 