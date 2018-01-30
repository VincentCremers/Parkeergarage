package nl.hanze.t12.life.view;

import java.awt.*;
import nl.hanze.t12.life.logic.LifeLogic;

public class LegendaView extends AbstractView{
	private static final long serialVersionUID = 3597041755244154804L;
		public LegendaView(LifeLogic life) {
			super(life);
		}
		
	    public void paintComponent(Graphics graphics) {
		    	graphics.setColor(Color.blue);
				graphics.fillRect(10, 10, 10, 10);
				graphics.setColor(Color.LIGHT_GRAY);
				graphics.fillRect(10, 30, 10, 10);
	        }
	     }

