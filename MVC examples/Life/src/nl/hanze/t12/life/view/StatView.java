package nl.hanze.t12.life.view;

import java.awt.*;

import nl.hanze.t12.life.logic.LifeLogic;

public class StatView extends AbstractView {
	private static final long serialVersionUID = -7891669840482084995L;

	public StatView(LifeLogic life) {
		super(life);
	//	setSize(200,200);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		int openSpots = life.getNumberOfOpenSpots();
		int totaalSpots = life.getNumberOfPlaces() * life.getNumberOfRows();
		int degree = openSpots;
		//int notOpenSpots = life.getNumberOfPlaces() * life.getNumberOfRows() - openSpots;
	
		g.setColor(Color.LIGHT_GRAY);
		g.fillArc(10, 10, 180, 180, 0, 360);
		g.setColor(Color.BLUE);
		g.fillArc(10, 10, 180, 180, 0, degree);		
	}
}
