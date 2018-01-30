package nl.hanze.t12.life.view;

import java.awt.*;


import nl.hanze.t12.life.logic.Location;
import nl.hanze.t12.life.logic.Car;
import nl.hanze.t12.life.logic.LifeLogic;
	
	
public class FieldView extends AbstractView {
	private static final long serialVersionUID = -8200251211832614969L;
	private Dimension size; 
    private Image carParkImage;

	public FieldView(LifeLogic life) {
		super(life);
        size = new Dimension(0,0);
	}
	
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }
	
    public void paintComponent(Graphics g) {
//        if (carParkImage == null) {
//            return;
//        }
    	System.out.println("painting");

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }
     
     public void updateView() {
         if (!size.equals(getSize())) {
             size = getSize();
             carParkImage = createImage(size.width, size.height);
         }
         Graphics graphics = carParkImage.getGraphics();
         for(int row = 0; row < life.getNumberOfRows(); row++) {
         	System.out.println("painting line "+row);
             for(int place = 0; place < life.getNumberOfPlaces(); place++) {
            	Color color;
            	Location location = new Location(row,place);
            	Car nextCar = life.getCarAt(location);
             	if(nextCar!=null) {
             		color = nextCar.getColor();
             		drawPlace(graphics, location, color);
             	}
             	else {
             		color = Color.white;
             		drawPlace(graphics, location, color);
             	}
             }
         }
         
         repaint();
     }
    
     
     private void drawPlace(Graphics graphics, Location location, Color color) {
         graphics.setColor(color);
         graphics.fillRect(
                  (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                 60 + location.getPlace() * 10,
                 20 - 1,
                 10 - 1); // TODO use dynamic size or constants
     }

     //	 private void drawPlace(Graphics graphics, Location location, Color color) {
//            graphics.setColor(color);
//            graphics.fillRect(
//                    260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
//                    60 + location.getPlace() * 10,
//                    20 - 1,
//                    10 - 1); // TODO use dynamic size or constants
//        }
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
 