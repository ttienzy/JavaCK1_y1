package View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class SignIn extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JLabel lbImage,lbUserName,lbPassword;
	private JPanel header,body;
	private JTextField tfUserName;
	private JPasswordField tfPassword;
	private JCheckBox open;
	private JButton btLogin,btCacel;
	private String Accout="tien",PassWord="111";
	
	public SignIn()
	{
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		try {
			Image imgTi = ImageIO.read(SignIn.class.getResource("java.jpg"));
			setIconImage(imgTi);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		header = new JPanel();
		header.setBounds(0, 0, 800, 80);
		header.setBackground(new Color(0, 0, 0, 30));
		header.setLayout(new FlowLayout());
		
		body = new JPanel();
		body.setBackground(new Color(0, 0, 0, 30));
		body.setBounds(200, 112, 400, 200);
		body.setLayout(new BorderLayout(50,50));
		
		JPanel pNorth = new JPanel();
		pNorth.setBackground(new Color(0, 0, 0, 0));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		
		JPanel pUserName = new JPanel();
		pUserName.setBackground(new Color(0, 0, 0, 0));
		pUserName.setLayout(new BoxLayout(pUserName, BoxLayout.X_AXIS));
		lbUserName = new JLabel("User :");
		lbUserName.setBackground(new Color(0, 0, 0, 0));
		lbUserName.setForeground(Color.YELLOW);
		lbUserName.setFont(new Font("Times New Roman", Font.BOLD, 30));
		tfUserName = new JTextField();
		tfUserName.setForeground(Color.YELLOW);
		tfUserName.setHorizontalAlignment((int) TextField.LEFT_ALIGNMENT);
		tfUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tfUserName.setBackground(new Color(80,90,100));
		pUserName.add(lbUserName);
		pUserName.add(tfUserName);
		
		JPanel pPassword = new JPanel();
		pPassword.setBackground(new Color(0, 0, 0, 0));
	    pPassword.setLayout(new BoxLayout(pPassword, BoxLayout.X_AXIS));
	    lbPassword = new JLabel("Pass :");
	    lbPassword.setBackground(new Color(0, 0, 0, 0));
	    lbPassword.setForeground(Color.YELLOW);
	    lbPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
	    tfPassword = new JPasswordField();
	    tfPassword.setHorizontalAlignment((int) JPasswordField.CENTER_ALIGNMENT);
	    tfPassword.setForeground(Color.YELLOW);
	    tfPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    tfPassword.setBackground(new Color(70,80,90));	    
	    
	    JPanel pCheckPass = new JPanel();
	    pCheckPass.setBackground(new Color(0, 0, 0, 0));
	    pCheckPass.setLayout(new BoxLayout(pCheckPass, BoxLayout.X_AXIS));
	    open = new JCheckBox("Openpass");
	    open.setBackground(new Color(50, 60, 70));
	    open.setForeground(Color.YELLOW);
	    open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(open.isSelected())
				{
					tfPassword.setEchoChar('\u0000');
				}
				else
				{
					tfPassword.setEchoChar('*');
				}
	
			}
		});
	    
	    pPassword.add(lbPassword);
	    pPassword.add(tfPassword);
	    pCheckPass.add(open);
	    
	    pNorth.add(pUserName);
	    pNorth.add(pPassword);
	    pNorth.add(pCheckPass);
	    body.add(pNorth,BorderLayout.NORTH);
	    
	    JPanel pLogin = new JPanel();
	    pLogin.setBackground(new Color(0, 0, 0, 30));
	    pLogin.setLayout(new FlowLayout());
	    
	    btCacel = new JButton("CANCEL");
	    btCacel.setBackground(new Color(50,60,70));
	    btCacel.setForeground(Color.YELLOW);
	    btCacel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    btCacel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				dispose();
			}
		});
	    

	    btLogin = new JButton("SIGN IN");
	    btLogin.setBackground(new Color(50,60,70));
	    btLogin.setForeground(Color.YELLOW);
	    btLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btLogin.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				//char[] pass = tfPassword.getPassword();
				
			
				if(tfUserName.getText().equalsIgnoreCase(Accout)&&tfPassword.getText().equalsIgnoreCase(PassWord)) {
					JOptionPane.showInternalConfirmDialog(null, "SUCCESS","SIGN IN",JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION,
							new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("tick.png"))));
					FrameFastFoodManagement.main(null);
					dispose();
				}
				else
				{
					JOptionPane.showConfirmDialog(null, "Account or password is incorrect", "ERROR", JOptionPane.CANCEL_OPTION);
				}
				
				
				
			}
		});
	    pLogin.add(btLogin);
	    pLogin.add(btCacel);
	    
	    //lbPassword.setPreferredSize(lbUserName.getPreferredSize());
	    tfUserName.setPreferredSize(tfPassword.getPreferredScrollableViewportSize());
	    
		body.add(pLogin,BorderLayout.CENTER);
		
		
		
		JLabel Title = new JLabel("LOGIN");
		Title.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		Title.setForeground(Color.RED);
		header.add(Title);
		
		ImageIcon imgicon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("form.jpg")));
		Image i = imgicon.getImage();
		Image tmp = i.getScaledInstance(800, 500, Image.SCALE_SMOOTH);		
		imgicon = new ImageIcon(tmp);
		
		lbImage = new JLabel("", imgicon, JLabel.CENTER);		
		lbImage.setBounds(0,0,800, 500);
		
		lbImage.add(header);
		lbImage.add(body);
		
		add(lbImage);
		
	}
	
	
	

	public static void main(String[] args) {
		new SignIn().setVisible(true);
	}

}
