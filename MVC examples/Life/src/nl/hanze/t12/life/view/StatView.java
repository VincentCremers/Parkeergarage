package nl.hanze.t12.life.view;

import java.awt.*;

import nl.hanze.t12.life.logic.LifeLogic;

public class StatView extends AbstractView {
	private static final long serialVersionUID = -7891669840482084995L;

	public StatView(LifeLogic life) {
		super(life);
	}

	public void paintComponent(Graphics grap) {
		grap.setColor(Color.WHITE);
		grap.fillRect(0, 0, 200, 200);
		int openSpots = life.getNumberOfOpenSpots();
		//int totaalSpots = life.getNumberOfPlaces() * life.getNumberOfRows();
		int degree = openSpots;
		//int notOpenSpots = life.getNumberOfPlaces() * life.getNumberOfRows() - openSpots;
	
		grap.setColor(Color.blue);
		grap.fillArc(10, 10, 180, 180, 0, 360);
		grap.setColor(Color.LIGHT_GRAY);
		grap.fillArc(10, 10, 180, 180, 90, degree);
	}
}
