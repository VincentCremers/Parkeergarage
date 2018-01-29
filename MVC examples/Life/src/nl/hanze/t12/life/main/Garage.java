package nl.hanze.t12.life.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import nl.hanze.t12.life.controller.AbstractController;
import nl.hanze.t12.life.controller.RunController;
import nl.hanze.t12.life.logic.LifeLogic;
import nl.hanze.t12.life.view.AbstractView;
import nl.hanze.t12.life.view.FieldView;
import nl.hanze.t12.life.view.StatView;

public class Garage {
	private JFrame screen;
	private AbstractView fieldView;
	private AbstractView statView;
	private LifeLogic lifelogic;
	//private AbstractController initController;
	private AbstractController runController;
	
	public Garage() {
		lifelogic=new LifeLogic();
		//initController=new InitController(lifelogic);
		runController=new RunController(lifelogic);
		fieldView=new FieldView(lifelogic);
		statView=new StatView(lifelogic);
		
		screen=new JFrame("Parkeergarage");
		screen.setSize(1500, 500);
		screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(fieldView);
		screen.getContentPane().add(statView);
		screen.getContentPane().add(runController);
		//screen.getContentPane().add(initController);
		fieldView.setBounds(10, 10, 900, 700);		
		statView.setBounds(1000, 100, 200, 200);
		runController.setBounds(900, 400, 450, 50);
		//initController.setBounds(440, 10, 90, 130);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		lifelogic.doStep();
	}
}
