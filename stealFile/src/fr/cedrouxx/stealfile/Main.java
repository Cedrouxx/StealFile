package fr.cedrouxx.stealfile;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main implements ActionListener{

	public static void main(String[] args) {
		new Main();
	}
	
	public static final String TITLE = "Steal File";
	
	public static final String AUTHOR = "Cedroux";
	
	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 320;
	
	private final String DEFAULT_FOLDER = "C:\\";
	
	private JFrame frame;
	private JPanel panel;
	
	private JTextField editText;
	
	private JCheckBox pngFile;
	private JCheckBox jpgFile;
	private JCheckBox txtFile;
	
	private JLabel info;
	private JButton button;
	
	private Execute execute;
	
	private JCheckBox autoScroll;
	private JScrollPane scroll;
	private JTextArea console;

	public Main() {
		
		execute = new Execute(this);
		
		frame = new JFrame();
		panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JPanel apanel;
		
		// chemin
		apanel = newApanel(BoxLayout.LINE_AXIS);
		JLabel cheminText = new JLabel("Starting path : ");
		editText = new JTextField();
		apanel.setMaximumSize(new Dimension(FRAME_WIDTH-100, FRAME_HEIGHT));
		editText.setText(DEFAULT_FOLDER);
		
		apanel.add(cheminText);
		apanel.add(editText);
		
		panel.add(apanel);
		
		// select file type
		apanel = newApanel(BoxLayout.LINE_AXIS);
		
		pngFile = new JCheckBox(".png");
		jpgFile = new JCheckBox(".jpg");
		txtFile = new JCheckBox(".txt");
		
		apanel.add(pngFile);
		apanel.add(jpgFile);
		apanel.add(txtFile);
		
		panel.add(apanel);
		
		//button
		apanel = newApanel(BoxLayout.LINE_AXIS);
		button = new JButton("start");
		buttonOn();
		
		
		info = new JLabel();
		
		apanel.add(button);
		apanel.add(info);
		
		panel.add(apanel);
		
		//autoScroll
		apanel = newApanel(BoxLayout.LINE_AXIS);
		autoScroll = new JCheckBox("auto scroll");
		autoScroll.setSelected(true);
		
		apanel.add(autoScroll);
		
		panel.add(apanel);
		
		//console
		apanel = newApanel(BoxLayout.LINE_AXIS);
		console = new JTextArea();
		console.setEditable(false);
		
		scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setAutoscrolls(false);
		
		apanel.add(scroll);
		
		panel.add(apanel);
		
		apanel = newApanel(BoxLayout.LINE_AXIS);
		apanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JLabel author = new JLabel("by " + AUTHOR);
		
		apanel.add(author);
		
		panel.add(apanel);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(TITLE);
		frame.setContentPane(panel);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public JPanel newApanel(int boxAxis) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, boxAxis));
		return panel;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTextField getEditText() {
		return editText;
	}
	
	public JCheckBox getPngFile() {
		return pngFile;
	}
	public JCheckBox getJpgFile() {
		return jpgFile;
	}
	public JCheckBox getTxtFile() {
		return txtFile;
	}
	
	public JLabel getInfo() {
		return info;
	}
	
	public JButton buttonOn() {
		button.addActionListener(this);
		return button;
	}
	
	public JButton buttonOff() {
		button.removeActionListener(this);
		return button;
	}
	
	public void addConsole(String text) {
		console.append(text + "\n");
		if(autoScroll.isSelected())
			console.setCaretPosition(console.getText().length());
		
	}
	
	public void addConsoleError(List<String> list) {
		addConsole("============= Errors =============");

		for(String str : list) {
			addConsole(str);
		}
		addConsole("================================");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		buttonOff();
		new Thread(execute, "start").start();
	}
}
