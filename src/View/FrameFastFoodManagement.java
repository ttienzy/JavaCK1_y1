package View;


import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import Controller.CustomerManager;
import Controller.FoodItemsManager;
import Controller.OrderManager;
import Controller.ShipperManager;
import Controller.SummaryManager;
import Model.Customers;
import Model.FoodItems;
import Model.Order;
import Model.Shippers;


public class FrameFastFoodManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	//Table
	private DefaultTableModel dtmCu,dtmFoo,dtmOrd,dtmShi;
	private JTable tbCu,tbFoo,tbOrd,tbShi;
	private JScrollPane scCu,scFoo,scOrd,scShi;
	
	//Panel
	private JTabbedPane jtbPanel;
	private JPanel pWest,pCus,pShi,pOrd,pFoo,pSummary;
	
	//Button
	private JButton btCus,btFoo,btOrd,btShi,btSum,btLogout;
	
	//TextFiel
	private JTextField tfIDfood,tfIDCus,tfIDOrdr,tfShipper;
	
	
	//Object management
	private CustomerManager cusMNG = new CustomerManager();
	private FoodItemsManager fooMNG = new FoodItemsManager();
	private OrderManager ordMNG = new OrderManager();
	private ShipperManager shiMNG = new ShipperManager();
	private SummaryManager sumMNG = new SummaryManager();
	
	//Regular Expressions 
	String nameRegex = "^[\\p{L}]+([\\p{Zs}\\p{L}]+)*$";  // Define a regular expressions pattern for a valid name
	String numericRegex = "\\d+";       //Define a regular expressions pattern for a string contain only digits
	String date ="^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
	Pattern patternName = Pattern.compile(nameRegex);  //Compile the regular expressions pattern
	Pattern patternNumber = Pattern.compile(numericRegex);
	Pattern patternDate = Pattern.compile(date); 
	
	public FrameFastFoodManagement()
	{
		super("FrameFastFoodManagement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(0,0,1200,800);
    	ImageIcon imgTitle =new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("java.jpg")));
    	setIconImage(imgTitle.getImage());
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setBackground(Color.BLUE);
    	setLayout(null);
    	
    	PanelCenter();
    	PanelWest();
    	
    	pcustomer();
    	pfooditems();
    	porders();
    	pshippers();
    	psummary();
	}
	public void PanelCenter()
	{
		jtbPanel = new JTabbedPane();
		jtbPanel.setBounds(280,10,900,750);
		
		pCus = new JPanel();
		pCus.setBackground(new Color(20,40,60,80));
		pCus.setLayout(null);
		
		pFoo = new JPanel();
		pFoo.setBackground(new Color(30,150,70,90));
		pFoo.setLayout(null);
		
		pOrd = new JPanel();
		pOrd.setBackground(new Color(180,180,180));
		pOrd.setLayout(null);
		
		pShi = new JPanel();
		pShi.setBackground(new Color(230,120,120));
		pShi.setLayout(null);
		
		pSummary = new JPanel();
		pSummary.setBackground(new Color(100,199,100));
		pSummary.setLayout(null);
		
		jtbPanel.addTab("Customer", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("customer.jpg"))), pCus);
		jtbPanel.addTab("FoodItems", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("food.png"))), pFoo);
		jtbPanel.addTab("Order", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("order.png"))), pOrd);
		jtbPanel.addTab("Shipper", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("Graphicloads-100-Flat-2-Bus.256.jpg"))), pShi);
		jtbPanel.addTab("Summary", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("resultTab.png"))), pSummary);

		add(jtbPanel);
	}
	
	public void PanelWest()
	{
		pWest = new JPanel();
		pWest.setBounds(10, 10,260, 750);
		pWest.setLayout(null);
		pWest.setBorder(new TitledBorder(null,"Management",(int)LEFT_ALIGNMENT,(int)TOP_ALIGNMENT, new Font("Times New Roman", Font.PLAIN,20),Color.MAGENTA));
		pWest.setBackground(new Color(130,40,30,40));
		
		JLabel titlewest = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("west.png"))));
		titlewest.setBounds(20, 20, 220, 200);
		pWest.add(titlewest);
		
		btCus = new JButton("Customer Registration", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("customer.jpg"))));
		btCus.setBounds(20, 230, 220, 50);
		btCus.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		pWest.add(btCus);
		btCus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtbPanel.setSelectedComponent(pCus);
				
			}
		});
		
		btFoo = new JButton("Add FoodItem", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("food.png"))));
		btFoo.setBounds(20, 290, 220, 50);
		btFoo.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		pWest.add(btFoo);
		btFoo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtbPanel.setSelectedComponent(pFoo);
			}
		});
		
		btOrd = new JButton("Customer Orders", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("order.png"))));
		btOrd.setBounds(20, 350, 220, 50);
		btOrd.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		pWest.add(btOrd);
		btOrd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtbPanel.setSelectedComponent(pOrd);
				
			}
		});
		
		btShi = new JButton("Deliver", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("Graphicloads-100-Flat-2-Bus.256.jpg"))));
		btShi.setBounds(20, 410, 220, 50);
		pWest.add(btShi);
		btShi.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		btShi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtbPanel.setSelectedComponent(pShi);
				
			}
		});
		
		btSum = new JButton("Summary", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("sum.png"))));
		btSum.setBounds(20, 470, 220, 50);
		pWest.add(btSum);
		btSum.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		btSum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtbPanel.setSelectedComponent(pSummary);
				
			}
		});
		
		
		btLogout = new JButton("LOG OUT", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("reset.png"))));
		btLogout.setBounds(20, 680, 220, 50);
		btLogout.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.GRAY, Color.lightGray));
		pWest.add(btLogout);
        btLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SignIn.main(null);
				dispose();
				
			}
		});
		
		add(pWest);
	}
	
	public void pcustomer()
	{
		String[] col = {"IDCustomer","Full Name","Phone","Address"};
		String[][] row = null;
		dtmCu = new DefaultTableModel(row, col);
		tbCu = new JTable(dtmCu);
		//tbCu.setBounds(300, 20, 850, 600);
		scCu = new JScrollPane(tbCu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scCu.setBounds(30, 20, 850, 400);
		pCus.add(scCu);
		
		pContentCus();
		
	}
	public void pfooditems()
	{
		String[] col = {"IDFood","Name","Price","Description"};
		String[][] row = null;
		dtmFoo = new DefaultTableModel(row, col);
		tbFoo = new JTable(dtmFoo);
		scFoo = new JScrollPane(tbFoo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scFoo.setBounds(30, 20, 850, 400);
		pFoo.add(scFoo);
		
		pContentFood();
	}
	public void porders()
	{
		String[] col = {"IDOrder","NameCus","NameFood","Number","TotalPrice","DateOrder","Status"};
		String[][] row = null;
		dtmOrd = new DefaultTableModel(row, col);
		tbOrd = new JTable(dtmOrd);
		scOrd = new JScrollPane(tbOrd, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scOrd.setBounds(30, 20, 850, 400);
		pOrd.add(scOrd);
		
		pContentOrder();
	}
	public void pshippers()
	{
		String[] col = {"IDShipper","NameShipper","IDOrder","PhoneShipper","DeleverStatus"};
		String[][] row = null;
		dtmShi = new DefaultTableModel(row, col);
		tbShi = new JTable(dtmShi);
		scShi = new JScrollPane(tbShi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scShi.setBounds(30, 20, 850, 400);
		pShi.add(scShi);
		
		pContentShipper();
	}
	public void psummary()
	{
		pContentSum();
	}
	
	void pContentCus() {
		JLabel IDcus = new JLabel("IDCustomer :");
		IDcus.setBounds(30,450,100,40);
		IDcus.setForeground(Color.blue);
		pCus.add(IDcus);
		
		JLabel NameCus = new JLabel("NameCustomer :");
		NameCus.setBounds(30,500,100,40);
		NameCus.setForeground(Color.blue);
		pCus.add(NameCus);
		
		JLabel Phone = new JLabel("PhoneCustomer :");
		Phone.setBounds(30,550,120,40);
		Phone.setForeground(Color.blue);
		pCus.add(Phone);
		
		JLabel Address = new JLabel("AddressCustomer :");
		Address.setBounds(30,600,120,40);
		Address.setForeground(Color.blue);
		pCus.add(Address);
		
		tfIDCus = new JTextField();
		tfIDCus.setBounds(160,450,150,40);
		tfIDCus.setBackground(new Color(90,100,110));
		tfIDCus.setForeground(Color.yellow);
		tfIDCus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pCus.add(tfIDCus);
		
		JTextField tfNameCus = new JTextField();
		tfNameCus.setBounds(160,500,200,40);
		tfNameCus.setBackground(new Color(90,100,110));
		tfNameCus.setForeground(Color.yellow);
		tfNameCus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pCus.add(tfNameCus);
		
		JTextField tfPhoneCus = new JTextField();
		tfPhoneCus.setBounds(160,550,150,40);
		tfPhoneCus.setBackground(new Color(90,100,110));
		tfPhoneCus.setForeground(Color.yellow);
		tfPhoneCus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pCus.add(tfPhoneCus);
		
		JTextField tfAddresscUS = new JTextField();
		tfAddresscUS.setBounds(160,600,200,40);
		tfAddresscUS.setBackground(new Color(90,100,110));
		tfAddresscUS.setForeground(Color.yellow);
		tfAddresscUS.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pCus.add(tfAddresscUS);
		
		JButton btAdd = new JButton("ADD", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("addCus.png"))));
		btAdd.setBounds(400,450,90,40);
		pCus.add(btAdd);
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * stream:List convert into a stream
				 * anyMatch:search element satisfy the condition?true:false
				 */		
				if(cusMNG.getID().stream().anyMatch(x -> x.contains(tfIDCus.getText())) || tfIDCus.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"ID is exits or empty!");
					tfIDCus.requestFocus();
					return;
				}
				if(tfNameCus.getText().equals("")|| !patternName.matcher(tfNameCus.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"Name no valid!");
					tfNameCus.requestFocus();
					return;
				}
				if(tfPhoneCus.getText().equals("")|| !patternNumber.matcher(tfPhoneCus.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"Phone no valid!");
					tfPhoneCus.requestFocus();
					return;
				}
				if(tfAddresscUS.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"address no valid!");
					tfAddresscUS.requestFocus();
					return;
				}
				cusMNG.Add(new Customers(tfIDCus.getText(),tfNameCus.getText(),tfPhoneCus.getText(),tfAddresscUS.getText()));
				dtmCu.addRow(new String[] {tfIDCus.getText(),tfNameCus.getText(),tfPhoneCus.getText(),tfAddresscUS.getText()});
			}	
		});
		
		JButton btUpdate = new JButton("UPDATE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("update.png"))));
		btUpdate.setBounds(400,500,120,40);
		pCus.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cusMNG.Update(new Customers(tfIDCus.getText(),tfNameCus.getText(),tfPhoneCus.getText(),tfAddresscUS.getText()));
				for(int i=0;i<tbCu.getRowCount();++i)
				{
					for(int j =0;j<tbCu.getColumnCount();++j)
					{
						if(tbCu.getValueAt(i, j).equals(tfIDCus.getText()))
						{
							dtmCu.removeRow(i);
							break;
						}
					}
				}
				dtmCu.addRow(new String[] {tfIDCus.getText(),tfNameCus.getText(),tfPhoneCus.getText(),tfAddresscUS.getText()});
				
			}
		});
		
		JButton btDelete = new JButton("DELETE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("delete.png"))));
		btDelete.setBounds(400,550,120,40);
		pCus.add(btDelete);
		btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				cusMNG.Dalete(new Customers(tfIDCus.getText(),tfNameCus.getText(),tfPhoneCus.getText(),tfAddresscUS.getText()));
				dtmCu.removeRow(tbCu.getSelectedRow());
				tfIDCus.setText("");
				tfNameCus.setText("");
				tfPhoneCus.setText("");
				tfAddresscUS.setText("");
			}
		});
		
		JButton btShow = new JButton("IMFOMATION CUS", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("data.png"))));
		btShow.setBounds(400,600,200,40);
		pCus.add(btShow);
		btShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmCu.setRowCount(0);
				cusMNG.ShowInfomation();
				cusMNG.getCustomerManagement().forEach(o->
					dtmCu.addRow(new String[] {
							o.getIDCus(),
							o.getNameCus(),
							o.getPhoneCus(),
							o.getAddressCus()
					}));
			}
		});
		
		JButton btSearch = new JButton("SEARCH", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("searchh.png"))));
		btSearch.setBounds(530,450,120,40);
		pCus.add(btSearch);
		btSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfIDCus.setText("");
				tfNameCus.setText("");
				tfPhoneCus.setText("");
				tfAddresscUS.setText("");
				String rollno = JOptionPane.showInputDialog("Search by ID :");
				dtmCu.setRowCount(0);
				if(rollno!=null)
				{
					if(cusMNG.Search(rollno)==null||rollno.equals(""))
					{
						JOptionPane.showMessageDialog(null, "ID isn't exits");
					}
					else
					{
						Vector<Customers> tmp =  cusMNG.Search(rollno);
						tmp.forEach(x->
						            dtmCu.addRow(new String[] {
						             x.getIDCus(),x.getNameCus(),x.getPhoneCus(),x.getAddressCus()
						            }));
						
						if(dtmCu.getRowCount()==0)
						{
							JOptionPane.showMessageDialog(null,"IDCustomer isn't exits");
						}
					}
				}else {}
				
			}
		});
		tbCu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int n = tbCu.getSelectedRow();
				tfIDCus.setText((String) tbCu.getValueAt(n,0));
				tfNameCus.setText((String) tbCu.getValueAt(n,1));
				tfPhoneCus.setText((String) tbCu.getValueAt(n,2));
				tfAddresscUS.setText((String) tbCu.getValueAt(n,3));
			}
		});
		
	}
	void pContentFood() {
		JLabel IDFood = new JLabel("IDFood :");
		IDFood.setBounds(30,450,100,40);
		IDFood.setForeground(Color.blue);
		pFoo.add(IDFood);
		
		JLabel NameFood = new JLabel("NameFood :");
		NameFood.setBounds(30,500,100,40);
		NameFood.setForeground(Color.blue);
		pFoo.add(NameFood);
		
		JLabel Price = new JLabel("Price :");
		Price.setBounds(30,550,120,40);
		Price.setForeground(Color.blue);
		pFoo.add(Price);
		
		JLabel Decription = new JLabel("Decription :");
		Decription.setBounds(30,600,120,40);
		Decription.setForeground(Color.blue);
		pFoo.add(Decription);
			
		tfIDfood = new JTextField();
		tfIDfood.setBounds(160,450,150,40);
		tfIDfood.setBackground(new Color(90,100,110));
		tfIDfood.setForeground(Color.yellow);
		tfIDfood.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pFoo.add(tfIDfood);
		
		JTextField tfNamefoo = new JTextField();
		tfNamefoo.setBounds(160,500,200,40);
		tfNamefoo.setBackground(new Color(90,100,110));
		tfNamefoo.setForeground(Color.yellow);
		tfNamefoo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pFoo.add(tfNamefoo);
		
		JTextField tfPriceFoo = new JTextField();
		tfPriceFoo.setBounds(160,550,150,40);
		tfPriceFoo.setBackground(new Color(90,100,110));
		tfPriceFoo.setForeground(Color.yellow);
		tfPriceFoo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pFoo.add(tfPriceFoo);
		
		JTextField tfDescription = new JTextField();
		tfDescription.setBounds(160,600,200,40);
		tfDescription.setBackground(new Color(90,100,110));
		tfDescription.setForeground(Color.yellow);
		tfDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pFoo.add(tfDescription);
		
		JButton btAdd = new JButton("ADD", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("addFoo.png"))));
		btAdd.setBounds(400,450,90,40);
		pFoo.add(btAdd);
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		
				if(fooMNG.getID().stream().anyMatch(x -> x.contains(tfIDfood.getText())) || tfIDfood.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"ID is exits or empty!");
					tfIDfood.requestFocus();
					return;
				}
				if(tfNamefoo.getText().equals("")|| !patternName.matcher(tfNamefoo.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"NameFood no valid!");
					tfNamefoo.requestFocus();
					return;
				}
				if(tfPriceFoo.getText().equals("")|| !patternNumber.matcher(tfPriceFoo.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"Price no valid!");
					tfPriceFoo.requestFocus();
					return;
				}

				fooMNG.Add(new FoodItems(tfIDfood.getText(),tfNamefoo.getText(),Float.valueOf(tfPriceFoo.getText()),tfDescription.getText()));
				dtmFoo.addRow(new String[] {tfIDfood.getText(),tfNamefoo.getText(),tfPriceFoo.getText(),tfDescription.getText()});
			}
		});
		
		JButton btUpdate = new JButton("UPDATE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("update.png"))));
		btUpdate.setBounds(400,500,120,40);
		pFoo.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fooMNG.Update(new FoodItems(tfIDfood.getText(),tfNamefoo.getText(),Float.valueOf(tfPriceFoo.getText()),tfDescription.getText()));
				for(int i=0;i<tbFoo.getRowCount();++i)
				{
					for(int j =0;j<tbFoo.getColumnCount();++j)
					{
						if(tbCu.getValueAt(i, j).equals(tfIDfood.getText()))
						{
							dtmFoo.removeRow(i);
							break;
						}
					}
				}
				dtmFoo.addRow(new String[] {tfIDfood.getText(),tfNamefoo.getText(),tfPriceFoo.getText(),tfDescription.getText()});
			}
		});
		
		JButton btDelete = new JButton("DELETE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("delete.png"))));
		btDelete.setBounds(400,550,120,40);
		pFoo.add(btDelete);
		btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fooMNG.Dalete(new FoodItems(tfIDfood.getText(),tfNamefoo.getText(),Float.valueOf(tfPriceFoo.getText()),tfDescription.getText()));
				dtmFoo.removeRow(tbFoo.getSelectedRow());
				tfIDfood.setText("");
				tfNamefoo.setText("");
				tfPriceFoo.setText("");
				tfDescription.setText("");
			}
		});
		
		JButton btShow = new JButton("IMFOMATION FOOD", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("data.png"))));
		btShow.setBounds(400,600,200,40);
		pFoo.add(btShow);
		btShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmFoo.setRowCount(0);
				fooMNG.ShowInfomation();
				fooMNG.getFooditemsManagement().forEach(
						o->dtmFoo.addRow(new String[] {
								o.getIDFood(),
								o.getNameFood(),
								String.valueOf(o.getPriceFood()),
								o.getDescription()
						}));
			}
		});
		
		JButton btSearch = new JButton("SEARCH", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("searchh.png"))));
		btSearch.setBounds(530,450,120,40);
		pFoo.add(btSearch);
		btSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfIDfood.setText("");
				tfNamefoo.setText("");
				tfPriceFoo.setText("");
				tfDescription.setText("");
				String rollno = JOptionPane.showInputDialog("Search by IDFood :");
				if(rollno!=null) {
					dtmFoo.setRowCount(0);
					if(fooMNG.Search(rollno)==null||rollno.equals(""))
					{
						JOptionPane.showMessageDialog(null,"IDFood isn't exits");
					}
					else
					{
						Vector<FoodItems> tmp = fooMNG.Search(rollno);
						tmp.forEach(y->
						            dtmFoo.addRow(new String[] {
						             y.getIDFood(),y.getNameFood(),String.valueOf(y.getPriceFood()),y.getDescription()	
						            }));
						
						if(dtmFoo.getRowCount()==0)
						{
							JOptionPane.showMessageDialog(null,"IDFood isn't exits");
						}
					}
				}else {}
				
			}
		});
		tbFoo.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int n = tbFoo.getSelectedRow();
				tfIDfood.setText((String) tbFoo.getValueAt(n,0));
				tfNamefoo.setText((String) tbFoo.getValueAt(n,1));
				tfPriceFoo.setText((String) tbFoo.getValueAt(n,2));
				tfDescription.setText((String) tbFoo.getValueAt(n,3));
			}
		});
	}
	void pContentOrder()
	{
		JLabel IDOrder = new JLabel("IDOrder :");
		IDOrder.setBounds(30,450,100,40);
		IDOrder.setForeground(Color.blue);
		pOrd.add(IDOrder);
		
		JLabel Namecus = new JLabel("NameCus :");
		Namecus.setBounds(30,500,100,40);
		Namecus.setForeground(Color.blue);
		pOrd.add(Namecus);
		
		JLabel NameFood = new JLabel("NameFood :");
		NameFood.setBounds(30,550,120,40);
		NameFood.setForeground(Color.blue);
		pOrd.add(NameFood);
		
		JLabel Number = new JLabel("Number :");
		Number.setBounds(30,600,120,40);
		Number.setForeground(Color.blue);
		pOrd.add(Number);
		
		JLabel TotalPrice = new JLabel("TotalPrice :");
		TotalPrice.setBounds(390,450,70,40);
		TotalPrice.setForeground(Color.blue);
		pOrd.add(TotalPrice);
		
		JLabel DateOrder = new JLabel("DateOrder :");
		DateOrder.setBounds(390,500,70,40);
		DateOrder.setForeground(Color.blue);
		pOrd.add(DateOrder);
		
		JLabel Status = new JLabel("Status :");
		Status.setBounds(390,550,90,40);
		Status.setForeground(Color.blue);
		pOrd.add(Status);
		
		
		tfIDOrdr = new JTextField();
		tfIDOrdr.setBounds(120,450,150,40);
		tfIDOrdr.setBackground(new Color(90,100,110));
		tfIDOrdr.setForeground(Color.yellow);
		tfIDOrdr.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pOrd.add(tfIDOrdr);
		
		JComboBox<String> cbNameCus = new JComboBox<String>(cusMNG.getName());
		cbNameCus.setBounds(120,500,250,40);
		cbNameCus.setBackground(new Color(90,100,110));
		cbNameCus.setForeground(Color.yellow);
		cbNameCus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pOrd.add(cbNameCus);
		
		
		JComboBox<String> cbNameFood = new JComboBox<String>(fooMNG.getName());
		cbNameFood.setBounds(120,550,250,40);
		cbNameFood.setBackground(new Color(90,100,110));
		cbNameFood.setForeground(Color.yellow);
		cbNameFood.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pOrd.add(cbNameFood);
		
		JTextField tfNumber = new JTextField();
		tfNumber.setBounds(120,600,100,40);
		tfNumber.setBackground(new Color(90,100,110));
		tfNumber.setForeground(Color.yellow);
		tfNumber.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pOrd.add(tfNumber);
		
		
		JTextField tfTotalPrice = new JTextField();
		tfTotalPrice.setBounds(480,450,100,40);
		tfTotalPrice.setBackground(new Color(90,100,110));
		tfTotalPrice.setForeground(Color.yellow);
		tfTotalPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pOrd.add(tfTotalPrice);
		tfNumber.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(tfNumber.getText().equals(""))
				{
					tfTotalPrice.setText("0");
				}
				else
				{
					try
					{
						int check = Integer.valueOf(tfNumber.getText());
						String tmp = fooMNG.getPriceTotal((String)cbNameFood.getSelectedItem(), check);
						tfTotalPrice.setText(tmp);
					}catch(NumberFormatException z)
					{
						tfTotalPrice.setText("ERROR : Number is Digit");
						tfNumber.requestFocus();
						return;
					}
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(tfNumber.getText().equals(""))
				{
					tfTotalPrice.setText("0");
				}
				else
				{
					try
					{
						int check = Integer.valueOf(tfNumber.getText());
						String tmp = fooMNG.getPriceTotal((String)cbNameFood.getSelectedItem(), check);
						tfTotalPrice.setText(tmp);
					}catch(NumberFormatException z)
					{
						tfTotalPrice.setText("ERROR : Number isDigit");
						tfNumber.requestFocus();
						return;
					}
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(tfNumber.getText().equals(""))
				{
					tfTotalPrice.setText("0");
				}
				else
				{
					try
					{
						int check = Integer.valueOf(tfNumber.getText());
						String tmp = fooMNG.getPriceTotal((String)cbNameFood.getSelectedItem(), check);
						tfTotalPrice.setText(tmp);
					}catch(NumberFormatException z)
					{
						tfTotalPrice.setText("ERROR : Number isDigit");
						tfNumber.requestFocus();
						return;
					}
				}
			}
		});
		cbNameFood.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {				
				//get value from cbNameFood
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					try
					{
						int check = Integer.valueOf(tfNumber.getText());
						String tmp = fooMNG.getPriceTotal((String)cbNameFood.getSelectedItem(), check);
						tfTotalPrice.setText(tmp);
					}catch(NumberFormatException z)
					{
						tfTotalPrice.setText("ERROR : Number isDigit");
						tfNumber.requestFocus();
						return;
					}
				}
				
			}
		});
		
		
		JTextField tfDate = new JTextField();
		tfDate.setBounds(480,500,200,40);
		tfDate.setBackground(new Color(90,100,110));
		tfDate.setForeground(Color.yellow);
		tfDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tfDate.setToolTipText("yyyy-MM-dd HH:mm:ss");
		pOrd.add(tfDate);
		
		JTextField tfStatus = new JTextField();
		tfStatus.setBounds(480,550,200,40);
		tfStatus.setBackground(new Color(90,100,110));
		tfStatus.setForeground(Color.yellow);
		tfStatus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pOrd.add(tfStatus);	
		
		
		
		JButton btAdd = new JButton("ADD Order", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("tick-mark.png"))));
		btAdd.setBounds(30,650,120,40);
		pOrd.add(btAdd);
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ordMNG.getID().stream().anyMatch(x -> x.contains(tfIDOrdr.getText())) || tfIDOrdr.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"ID is exits or empty!");
					tfIDOrdr.requestFocus();
					return;
				}
				if(tfNumber.getText().equals("")|| Integer.valueOf(tfNumber.getText())>500)
				{
					JOptionPane.showMessageDialog(null,"Number no valid!");
					tfNumber.requestFocus();
					return;
				}
				if(tfDate.getText().equals("")|| !patternDate.matcher(tfDate.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"DateTime no valid!");
					tfDate.requestFocus();
					return;
				}
				ordMNG.Add(new Order(tfIDOrdr.getText(),
						             cusMNG.getID().get(cbNameCus.getSelectedIndex()),
						             fooMNG.getID().get(cbNameFood.getSelectedIndex()),
						             Integer.valueOf(tfNumber.getText()),
						             Integer.valueOf(tfTotalPrice.getText()),
						             tfDate.getText(),
						             tfStatus.getText()));
					
				dtmOrd.addRow(new Object[] {tfIDOrdr.getText(),
						             cusMNG.getName().get(cbNameCus.getSelectedIndex()),
						             fooMNG.getName().get(cbNameFood.getSelectedIndex()),
						             Integer.valueOf(tfNumber.getText()),
						             Integer.valueOf(tfTotalPrice.getText()),
						             tfDate.getText(),
						             tfStatus.getText()});	
			}
		});
		
		JButton btUpdate = new JButton("UPDATE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("update.png"))));
		btUpdate.setBounds(160,650,120,40);
		pOrd.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				ordMNG.Update(new Order(tfIDOrdr.getText(),
			             cusMNG.getID().get(cbNameCus.getSelectedIndex()),
			             fooMNG.getID().get(cbNameFood.getSelectedIndex()),
			             Integer.valueOf(tfNumber.getText()),
			             Integer.valueOf(tfTotalPrice.getText()),
			             tfDate.getText(),
			             tfStatus.getText()));
				for(int i=0;i<tbOrd.getRowCount();++i)
				{
					for(int j =0;j<tbOrd.getColumnCount();++j)
					{
						if(tbCu.getValueAt(i, j).equals(tfIDOrdr.getText()))
						{
							dtmOrd.removeRow(i);
							break;
						}
					}
				}
				dtmOrd.addRow(new Object[] {tfIDOrdr.getText(),
			             cusMNG.getID().get(cbNameCus.getSelectedIndex()),
			             fooMNG.getID().get(cbNameFood.getSelectedIndex()),
			             Integer.valueOf(tfNumber.getText()),
			             Integer.valueOf(tfTotalPrice.getText()),
			             tfDate.getText(),
			             tfStatus.getText()});	
			}
		});
		
		JButton btDelete = new JButton("DELETE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("delete.png"))));
		btDelete.setBounds(290,650,120,40);
		pOrd.add(btDelete);
		btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ordMNG.Dalete(new Order(tfIDOrdr.getText(),
			             cusMNG.getID().get(cbNameCus.getSelectedIndex()),
			             fooMNG.getID().get(cbNameFood.getSelectedIndex()),
			             Integer.valueOf(tfNumber.getText()),
			             Integer.valueOf(tfTotalPrice.getText()),
			             tfDate.getText(),
			             tfStatus.getText()));
				dtmShi.removeRow(tbShi.getSelectedRow());
				tfIDOrdr.setText("");
				tfNumber.setText("");
				tfDate.setText("");
				tfTotalPrice.setText("");
				tfStatus.setText("");
			}
		});
		
		JButton btShow = new JButton("IMFOMATION ORDER", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("data.png"))));
		btShow.setBounds(420,650,200,40);
		pOrd.add(btShow);
		btShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmOrd.setRowCount(0);
				ordMNG.ShowInfomation();
				ordMNG.getOrderManagement().forEach(
						o->dtmOrd.addRow(new String[] {
								o.getIDord(),
								o.getNameCust(),
								o.getNameFood(),
								String.valueOf(o.getNumber()),
								String.valueOf(o.getTotal()),
								o.getDateOrder(),
								o.getStatu()
						}));
			}
		});
		
		JButton btSearch = new JButton("SEARCH", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("searchh.png"))));
		btSearch.setBounds(630,650,120,40);
		pOrd.add(btSearch);
		btSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfIDOrdr.setText("");
				tfNumber.setText("");
				tfDate.setText("");
				tfTotalPrice.setText("");
				tfStatus.setText("");
				String rollno = JOptionPane.showInputDialog(null,"Search by IDOrder :");
				dtmOrd.setRowCount(0);
				if(rollno!=null)
				{
					if(ordMNG.Search(rollno)==null||rollno.equals(""))
					{
						JOptionPane.showMessageDialog(null,"IDOrder isn't exits");
					}
					else
					{
						Vector<Order> tmp = ordMNG.Search(rollno);
						tmp.forEach(o->
						            dtmOrd.addRow(new String[] {
						             o.getIDord(),o.getNameCust(),o.getNameFood(),String.valueOf(o.getNumber()),	
						             String.valueOf(o.getTotal()),o.getDateOrder(),o.getStatu()
						            }));
						
						if(dtmOrd.getRowCount()==0)
						{
							JOptionPane.showMessageDialog(null,"IDOrder isn't exits");
						}
					}
				}
				else {}
			}
		});
		tbOrd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int n = tbOrd.getSelectedRow();
				tfIDOrdr.setText((String) tbOrd.getValueAt(n,0));
				cbNameCus.setSelectedItem((String) tbOrd.getValueAt(n, 1));
				cbNameFood.setSelectedItem((String) tbOrd.getValueAt(n, 2));
				tfNumber.setText((String) tbOrd.getValueAt(n,3));
				tfTotalPrice.setText((String) tbOrd.getValueAt(n,4));
				tfDate.setText((String) tbOrd.getValueAt(n,5));
				tfStatus.setText((String) tbOrd.getValueAt(n,6));
			}
		});
	}
	void pContentShipper()
	{
		JLabel IDShipper = new JLabel("IDShipper :");
		IDShipper.setBounds(30,450,100,40);
		IDShipper.setForeground(Color.blue);
		pShi.add(IDShipper);
		
		JLabel NameShipper = new JLabel("NameShipper :");
		NameShipper.setBounds(30,500,100,40);
		NameShipper.setForeground(Color.blue);
		pShi.add(NameShipper);
		
		JLabel lbIDOrder = new JLabel("IDOrder :");
		lbIDOrder.setBounds(30,550,120,40);
		lbIDOrder.setForeground(Color.blue);
		pShi.add(lbIDOrder);
		
		JLabel PhoneShipper = new JLabel("PhoneShipper :");
		PhoneShipper.setBounds(400,450,150,40);
		PhoneShipper.setForeground(Color.blue);
		pShi.add(PhoneShipper);
		
		JLabel DeliverStatus = new JLabel("DeliverStatus :");
		DeliverStatus.setBounds(400,500,150,40);
		DeliverStatus.setForeground(Color.blue);
		pShi.add(DeliverStatus);
		
		tfShipper = new JTextField();
		tfShipper.setBounds(160,450,180,40);
		tfShipper.setBackground(new Color(90,100,110));
		tfShipper.setForeground(Color.yellow);
		tfShipper.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pShi.add(tfShipper);
		
		JTextField tfPhone = new JTextField();
		tfPhone.setBounds(540,450,180,40);
		tfPhone.setBackground(new Color(90,100,110));
		tfPhone.setForeground(Color.yellow);
		tfPhone.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pShi.add(tfPhone);
		
		JTextField tfNameShi = new JTextField();
		tfNameShi.setBounds(160,500,200,40);
		tfNameShi.setBackground(new Color(90,100,110));
		tfNameShi.setForeground(Color.yellow);
		tfNameShi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pShi.add(tfNameShi);
		
		JComboBox<String> cbIDOrder = new JComboBox<String>(ordMNG.getID());
		cbIDOrder.setBounds(160,550,150,40);
		cbIDOrder.setBackground(new Color(90,100,110));
		cbIDOrder.setForeground(Color.yellow);
		cbIDOrder.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pShi.add(cbIDOrder);
		
		JTextField tfStatus = new JTextField();
		tfStatus.setBounds(540,500,200,40);
		tfStatus.setBackground(new Color(90,100,110));
		tfStatus.setForeground(Color.yellow);
		tfStatus.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pShi.add(tfStatus);
		
		JButton btAdd = new JButton("ADD", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("tick-mark.png"))));
		btAdd.setBounds(30,600,120,40);
		pShi.add(btAdd);
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				if(shiMNG.getID().stream().anyMatch(x -> x.contains(tfIDfood.getText())) || tfShipper.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"ID is exits or empty!");
					tfShipper.requestFocus();
					return;
				}
				if(tfNameShi.getText().equals("")|| !patternName.matcher(tfNameShi.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"NameFood no valid!");
					tfNameShi.requestFocus();
					return;
				}
				if(tfPhone.getText().equals("")|| !patternNumber.matcher(tfPhone.getText()).matches())
				{
					JOptionPane.showMessageDialog(null,"Price no valid!");
					tfPhone.requestFocus();
					return;
				}

				shiMNG.Add(new Shippers(tfShipper.getText(),tfNameShi.getText(),(String)cbIDOrder.getSelectedItem(),tfPhone.getText(),tfStatus.getText()));
				dtmShi.addRow(new String[] {tfShipper.getText(),tfNameShi.getText(),(String)cbIDOrder.getSelectedItem(),tfPhone.getText(),tfStatus.getText()});
			}
		});
		
		JButton btUpdate = new JButton("UPDATE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("update.png"))));
		btUpdate.setBounds(160,600,120,40);
		pShi.add(btUpdate);
		btUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shiMNG.Update(new Shippers(tfShipper.getText(),tfNameShi.getText(),(String)cbIDOrder.getSelectedItem(),tfPhone.getText(),tfStatus.getText()));
				for(int i=0;i<tbShi.getRowCount();++i)
				{
					for(int j =0;j<tbShi.getColumnCount();++j)
					{
						if(tbCu.getValueAt(i, j).equals(tfShipper.getText()))
						{
							dtmShi.removeRow(i);
							break;
						}
					}
				}
				dtmShi.addRow(new String[] {tfShipper.getText(),tfNameShi.getText(),(String)cbIDOrder.getSelectedItem(),tfPhone.getText(),tfStatus.getText()});
			}
		});
		
		JButton btDelete = new JButton("DELETE", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("delete.png"))));
		btDelete.setBounds(290,600,120,40);
		pShi.add(btDelete);
		btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				shiMNG.Dalete(new Shippers(tfShipper.getText(), null));
				dtmShi.removeRow(tbShi.getSelectedRow());
				tfShipper.setText("");
				tfNameShi.setText("");
				cbIDOrder.setActionCommand("11111");
				tfPhone.setText("");
				tfStatus.setText("");
			}
		});
		
		JButton btShow = new JButton("IMFOMATION SHIPPER", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("data.png"))));
		btShow.setBounds(420,600,250,40);
		pShi.add(btShow);
		btShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dtmShi.setRowCount(0);
				shiMNG.ShowInfomation();
				shiMNG.getShipperManagement().forEach(
						sh->dtmShi.addRow(new String[] {
								sh.getIDShi(),
								sh.getNameShi(),
								sh.getIDOr(),
								sh.getPhoneSh(),
								sh.getDiliverStatus()							
						}));
			}
		});
		
		JButton btSearch = new JButton("SEARCH", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("searchh.png"))));
		btSearch.setBounds(680,600,120,40);
		pShi.add(btSearch);
		btSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tfShipper.setText("");
				tfNameShi.setText("");
				tfPhone.setText("");
				tfStatus.setText("");
				String rollno = JOptionPane.showInputDialog("Search by IDShipper :");
				dtmShi.setRowCount(0);
				if(rollno!=null) {
					if(shiMNG.Search(rollno)==null||rollno.equals(""))
					{
						JOptionPane.showMessageDialog(null,"IDShipper isn't exits");
					}
					else
					{
						Vector<Shippers> tmp = shiMNG.Search(rollno);
						tmp.forEach(s->
						            dtmShi.addRow(new String[] {
						             s.getIDShi(),s.getNameShi(),s.getIDOr(),s.getPhoneSh(),s.getDiliverStatus()		
						            }));

						
						if(dtmShi.getRowCount()==0)
						{
							JOptionPane.showMessageDialog(null,"IDShipper isn't exits");
						}
					}
				}else {}
				
			}
		});
		tbShi.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int n = tbShi.getSelectedRow();
				tfShipper.setText((String) tbShi.getValueAt(n,0));
				tfNameShi.setText((String) tbShi.getValueAt(n,1));
				cbIDOrder.setSelectedItem((String) tbShi.getValueAt(n, 2));
				tfPhone.setText((String) tbShi.getValueAt(n,3));
				tfStatus.setText((String) tbShi.getValueAt(n,4));
			}
		});
	}
	void pContentSum() {
		JLabel cusNumber = new JLabel("CUSTOMER NUMBERS",JLabel.CENTER);
		cusNumber.setBounds(30,20,250,100);
		cusNumber.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		cusNumber.setForeground(Color.blue);
		cusNumber.setFont(new Font("Times New Roman", Font.PLAIN,15));
		pSummary.add(cusNumber);
		
		JLabel nc = new JLabel(sumMNG.getNumCus(),JLabel.CENTER);
		nc.setBounds(30,100,250,100);
		nc.setForeground(Color.blue);
		nc.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(nc);
		
		JLabel fooNumber = new JLabel("FOOD NUMBERS",JLabel.CENTER);
		fooNumber.setBounds(320,20,250,100);
		fooNumber.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.GRAY, Color.lightGray));
		fooNumber.setForeground(Color.blue);
		fooNumber.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		pSummary.add(fooNumber);
		
		JLabel nf = new JLabel(sumMNG.getNumFoo(),JLabel.CENTER);
		nf.setBounds(320,100,250,100);
		nf.setForeground(Color.blue);
		nf.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		pSummary.add(nf);
		
		JLabel ordNumber = new JLabel("REVENUE",JLabel.CENTER);
		ordNumber.setBounds(610,20,250,100);
		ordNumber.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.YELLOW, Color.GREEN));
		ordNumber.setForeground(Color.blue);
		ordNumber.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		pSummary.add(ordNumber);
		
		JLabel ns = new JLabel(sumMNG.getRevenue(),JLabel.CENTER);
		ns.setBounds(610,100,250,100);
		ns.setForeground(Color.blue);
		ns.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		pSummary.add(ns);
		
		//---------------------------------------------------//
		
		JLabel orderSum = new JLabel("Total :");
		orderSum.setBounds(30,300,150,50);
		orderSum.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		orderSum.setForeground(Color.blue);
		orderSum.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(orderSum);
		
		JLabel totall = new JLabel(sumMNG.getNumOrder());
		totall.setBounds(180,300,150,50);
		totall.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		totall.setForeground(Color.blue);
		totall.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(totall);
		
		JLabel cacel = new JLabel("Boom :");
		cacel.setBounds(30,350,150,50);
		cacel.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		cacel.setForeground(Color.blue);
		cacel.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(cacel);
		
		JLabel boom = new JLabel(sumMNG.getNumOrderDisContinue());
		boom.setBounds(180,350,150,50);
		boom.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		boom.setForeground(Color.blue);
		boom.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(boom);
		
		JLabel continues = new JLabel("Remain :");
		continues.setBounds(30,400,150,50);
		continues.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		continues.setForeground(Color.blue);
		continues.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(continues);
		
		JLabel continu = new JLabel(sumMNG.getNumOrderContinue());
		continu.setBounds(180,400,150,50);
		continu.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		continu.setForeground(Color.blue);
		continu.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(continu);
		
		//------------------------------------------------//
		
		JButton topFoods = new JButton("Top Food", new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("chart.png"))));
		topFoods.setBounds(400,300,200,150);
		topFoods.setBorder(BorderFactory.createBevelBorder(getComponentCount(), Color.CYAN, Color.GREEN));
		topFoods.setForeground(Color.blue);
		topFoods.setFont(new Font("Times New Roman", Font.PLAIN,30));
		pSummary.add(topFoods);
		topFoods.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TopFoodChart.main(null);
				
			}
		});
		
		
		
	}
	

	public static void main(String[] args) {
		new FrameFastFoodManagement().setVisible(true);
	}
	

}
