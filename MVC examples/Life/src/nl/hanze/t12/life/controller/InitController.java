package nl.hanze.t12.life.controller;

import java.awt.event.*;
import javax.swing.*;

import nl.hanze.t12.life.logic.*;

public class InitController extends AbstractController implements ActionListener {
	private static final long serialVersionUID = 8084081366423909672L;
	private JTextField size;
	
	
	public InitController(LifeLogic life) {
		super(life);
		setSize(90, 130);
		size=new JTextField();	
		this.setLayout(null);
		add(size);
		size.setBounds(10, 10, 70, 30);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int sizeField=parseSize();
			life.setSize(sizeField);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private int parseSize() throws NumberFormatException {
		return Integer.parseInt(size.getText());
	}
}
