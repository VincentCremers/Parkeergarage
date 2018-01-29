package nl.hanze.t12.life.controller;

import java.awt.event.*;
import javax.swing.*;
import nl.hanze.t12.life.logic.*;

public class RunController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = -8776795932665582315L;
	private JButton start;
	private JButton stop;
	
	public RunController(LifeLogic life) {
		super(life);
		
		setSize(500, 50);
		start=new JButton("Start");
		start.addActionListener(this);
		stop=new JButton("Stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(start);
		add(stop);
		start.setBounds(140, 10, 70, 30);
		stop.setBounds(230, 10, 70, 30);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==start) {
			life.start();
		}
		
		if (e.getSource()==stop) {
			life.stop();
		}
}
}
