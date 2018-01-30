package nl.hanze.t12.life.main;

import javax.swing.JFrame;
import javax.swing.JLabel;

import nl.hanze.t12.life.controller.AbstractController;
import nl.hanze.t12.life.controller.RunController;
import nl.hanze.t12.life.logic.LifeLogic;
import nl.hanze.t12.life.view.AbstractView;
import nl.hanze.t12.life.view.FieldView;
import nl.hanze.t12.life.view.StatView;
import nl.hanze.t12.life.view.LegendaView;

public class Garage {
	private JFrame screen;
	private AbstractView fieldView;
	private AbstractView statView;
	private AbstractView legendaView;
	private LifeLogic lifelogic;
	private AbstractController runController;
	
	public Garage() {
		lifelogic=new LifeLogic();
		runController=new RunController(lifelogic);
		fieldView=new FieldView(lifelogic);
		statView=new StatView(lifelogic);
		legendaView=new LegendaView(lifelogic);
		JLabel label = new JLabel("Plekken bezet");
		JLabel label1 = new JLabel("Plekken vrij");
		
		screen=new JFrame("Parkeergarage");
		screen.setSize(1500, 500);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(fieldView);
		screen.getContentPane().add(statView);
		screen.getContentPane().add(legendaView);
		screen.getContentPane().add(label);
		screen.getContentPane().add(label1);
		screen.getContentPane().add(runController);
		fieldView.setBounds(10, 10, 900, 700);
		statView.setBounds(1000, 100, 200, 200);
		legendaView.setBounds(1200, 100, 50, 50);
		label.setBounds(1250, 90, 300, 50);
		label1.setBounds(1250, 110, 300, 50);
		
		runController.setBounds(900, 400, 450, 50);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		lifelogic.doStep();
	}
}
