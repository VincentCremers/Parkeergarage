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
         Color color;
         Graphics graphics = carParkImage.getGraphics();
         for(int row = 0; row < life.getNumberOfRows(); row++) {
             for(int place = 0; place < life.getNumberOfPlaces(); place++) {
            	Location location = new Location(row,place);
            	Car nextCar = life.getCarAt(location);
             	if(nextCar != null) {
             		color = nextCar == null ? Color.white : nextCar.getColor();
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
}


/*
for(int teller = life.getCarsRemoved(); teller < 0; teller--) {
	 System.out.println("witting "+teller);
	 color = Color.white;
	 Location location =life.getFirstFreeLocation();
	 drawPlace(graphics, location, color);
	 life.resetCarsRemoved();
}*/