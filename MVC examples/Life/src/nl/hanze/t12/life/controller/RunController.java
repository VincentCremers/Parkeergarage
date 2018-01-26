package nl.hanze.t12.life.controller;

import java.awt.event.*;
import javax.swing.*;
import nl.hanze.t12.life.exception.*;
import nl.hanze.t12.life.logic.*;

public class RunController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = -8776795932665582315L;
	private JButton mineen;
	private JButton pluseen;
	private JButton start;
	private JButton stop;
	
	public RunController(LifeLogic life) {
		super(life);
		
		setSize(500, 50);
		mineen=new JButton("-1");
		mineen.addActionListener(this);
		pluseen=new JButton("+1");
		pluseen.addActionListener(this);
		start=new JButton("Start");
		start.addActionListener(this);
		stop=new JButton("Stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(mineen);
		add(pluseen);
		add(start);
		add(stop);
		mineen.setBounds(50, 10, 70, 30);
		pluseen.setBounds(140, 10, 70, 30);
		start.setBounds(229, 10, 70, 30);
		stop.setBounds(319, 10, 70, 30);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==mineen) {
			LifeLogic.setNumberOfOpenSpots(LifeLogic.getNumberOfOpenSpots()+1);
		}
		
		if (e.getSource()==pluseen) {
			LifeLogic.setNumberOfOpenSpots(LifeLogic.getNumberOfOpenSpots()-1);
		}
		
		if (e.getSource()==start) {
			LifeLogic.run();
		}
		
		if (e.getSource()==stop) {
			LifeLogic.stopSteps();
		}
	
}
}
