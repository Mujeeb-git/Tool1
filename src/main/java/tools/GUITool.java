package tools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class GUITool {

	private JFrame frame;
	private JTextField textField;
	FileActions fileActionsObj;
	JTextPane textPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITool window = new GUITool();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUITool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fileActionsObj = new FileActions();
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textField.setText(fileActionsObj.chooseFile());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBrowse.setBounds(310, 55, 99, 38);
		frame.getContentPane().add(btnBrowse);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String fPath=textField.getText();
					//fileActionsObj.convertFile();
					textPane.setText("Successfully generated .dat file at: "+fileActionsObj.convertFile(fPath));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(162, 132, 138, 44);
		frame.getContentPane().add(btnNewButton);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 56, 290, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setForeground(Color.BLUE);
		textPane.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		textPane.setBounds(0, 201, 434, 29);
		frame.getContentPane().add(textPane);
	}
}
