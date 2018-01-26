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

	
	
	
	
	
	
	
	
	
	public void updateView() {
        // Create a new car park image if the size has changed.
		
		Car[][][] cars=life.getState();
		
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
		Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < 4; floor++) {
            for(int row = 0; row < 8; row++) {
                for(int place = 0; place < 20; place++) {
                	Location location = new Location(floor, row, place);
                	Car car = cars[floor][row][place];
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }
	
	
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }

	 public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
	/*	g.setColor(Color.BLUE);

		Car[][][] cars=life.getState();
		
		if (cars==null) return;
		
		int margin=100-cars.length*2;
		for(int i=0;i<cars.length;i++)
			for(int j=0;j<Location.getNumberOfFloors;j++)
				for(int k=0;k<cars[j].length;k++)
					if (!(cars[i][j][k]==null)) {
						g.fillRect(margin+10*i, margin+10*j, k, 3);
						}
									
				

	}
	//g.fillRect(margin+4*i, margin+4*j, 3, 3)
	 */  
}
}

