import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class PingOutline extends JFrame {
	String getPort;
	public PingOutline() {
		super("IP Range - Angry IP Scanner");
		Font myfont = new Font("Serlf", Font.BOLD, 16);
		//�޴� ����
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar); 
		
		JMenu scanMenu=new JMenu("Scan");
		JMenu gotoMenu=new JMenu("Go to");
		JMenu cmdMenu=new JMenu("Commands");
		JMenu favoritesMenu=new JMenu("Favorites");
		JMenu toolsMenu=new JMenu("Tools");
		JMenu helpMenu=new JMenu("Help");
		
		menuBar.add(scanMenu);
		menuBar.add(gotoMenu);
		menuBar.add(cmdMenu);
		menuBar.add(favoritesMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);
		//Scan
		JMenuItem loadFromFileAction=new JMenuItem("Load from file...");
		JMenuItem ExportAllAction=new JMenuItem("Export all...");
		JMenuItem exportSelectionAction=new JMenuItem("Export selection...");
		JMenuItem quitAction=new JMenuItem("Quit");
		
		scanMenu.add(loadFromFileAction);
		scanMenu.add(ExportAllAction);
		scanMenu.add(exportSelectionAction);
		scanMenu.addSeparator();
		scanMenu.add(quitAction);
		//Go to
		JMenuItem nextalivehostAction=new JMenuItem("Next alive host");
		JMenuItem nextopenportAction=new JMenuItem("Next open port");
		JMenuItem nextdeadhostAction=new JMenuItem("Next dead host");
		JMenuItem PreviousalivehostAction=new JMenuItem("Previous alive host");
		JMenuItem PreviousopenportAction=new JMenuItem("Previous open port");
		JMenuItem PreviousdeadhostAction=new JMenuItem("Previous dead host");
		JMenuItem FindAction=new JMenuItem("Find...");
		
		gotoMenu.add(nextalivehostAction);
		gotoMenu.add(nextopenportAction);
		gotoMenu.add(nextdeadhostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(PreviousalivehostAction);
		gotoMenu.add(PreviousopenportAction);
		gotoMenu.add(PreviousdeadhostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(FindAction);
		//Commands
		JMenuItem ShowdetailsAction=new JMenuItem("Show details");
		JMenuItem RescanIPAction=new JMenuItem("Rescan IP(s)");
		JMenuItem DeleteIPAction=new JMenuItem("Delete IP(s)");
		JMenuItem CopyIPAction=new JMenuItem("Copy IP");
		JMenuItem CopydetailsAction=new JMenuItem("Copy details");
		JMenuItem OpenAction=new JMenuItem("Open");
		
		cmdMenu.add(ShowdetailsAction);
		cmdMenu.addSeparator();
		cmdMenu.add(RescanIPAction);
		cmdMenu.add(DeleteIPAction);
		cmdMenu.addSeparator();
		cmdMenu.add(CopyIPAction);
		cmdMenu.add(CopydetailsAction);
		cmdMenu.addSeparator();
		cmdMenu.add(OpenAction);
		//Favorites
		JMenuItem AddcurrentAction=new JMenuItem("Add current...");
		JMenuItem ManageFavoriteAction=new JMenuItem("Manage Favorites...");
		
		favoritesMenu.add(AddcurrentAction);
		favoritesMenu.add(ManageFavoriteAction);
		// Tools
		JMenuItem PreferenceAction=new JMenuItem("Preferences...");
		JMenuItem FetchersAction=new JMenuItem("Fetchers...");
		JMenuItem SelectionAction=new JMenuItem("Selection");
		JMenuItem ScanStaticsAction=new JMenuItem("Scan Statistics");
		
		toolsMenu.add(PreferenceAction);
		toolsMenu.add(FetchersAction);
		toolsMenu.addSeparator();
		toolsMenu.add(SelectionAction);
		toolsMenu.add(ScanStaticsAction);
		// Help
		JMenuItem GettingstartedAction=new JMenuItem("Getting Started");
		JMenuItem OfficialwebsiteAction=new JMenuItem("Official Website");
		JMenuItem FAQAction=new JMenuItem("FAQ");
		JMenuItem ReportanissueAction=new JMenuItem("Report an issue");
		JMenuItem PluginsAction=new JMenuItem("Plugins");
		JMenuItem CommandlineusageAction=new JMenuItem("Command-line usage");
		JMenuItem CheckfornewversionAction=new JMenuItem("Check for newer version...");
		JMenuItem AboutAction=new JMenuItem("About");
		
		helpMenu.add(GettingstartedAction);
		helpMenu.addSeparator();
		helpMenu.add(OfficialwebsiteAction);
		helpMenu.add(FAQAction);
		helpMenu.add(ReportanissueAction);
		helpMenu.add(PluginsAction);
		helpMenu.addSeparator();
		helpMenu.add(CommandlineusageAction);
		helpMenu.addSeparator();
		helpMenu.add(CheckfornewversionAction);
		helpMenu.addSeparator();
		helpMenu.add(AboutAction);
	
		quitAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		// �޴� ��
		// ���� �� ����
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		JLabel readyLabel = new JLabel("Ready");
		JLabel displayLabel = new JLabel("Display All");
		JLabel threadLabel = new JLabel("Thread: 0");
		statusPanel.add(readyLabel);
		statusPanel.add(displayLabel);
		statusPanel.add(threadLabel);
		readyLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		displayLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		threadLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		readyLabel.setPreferredSize(new Dimension(300, 20));
		displayLabel.setPreferredSize(new Dimension(150, 20));
		threadLabel.setPreferredSize(new Dimension(150, 20));
		readyLabel.setFont(myfont);
		displayLabel.setFont(myfont);
		threadLabel.setFont(myfont);
		// ���� �� ��
		// ���̺� ����
		String[] titles = new String[] {"IP","Ping","TTL","Host name","Ports[0+]"};
		Object[][] stats =initializeTableData();
		JTable jTable = new JTable(stats,titles);
		JScrollPane sp = new JScrollPane(jTable);
		add(sp, BorderLayout.CENTER);
		// ���̺� ��
		// �� �� ����
		JToolBar toolbar1 = new JToolBar();
		JToolBar toolbar2 = new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));
				
		JLabel rangeStartLabel = new JLabel("IP Range:");
		JLabel rangeEndLabel = new JLabel("to");
		JTextField rangeStartTextField = new JTextField(10);	
		JTextField rangeEndTextField = new JTextField(10);
				
		rangeStartLabel.setFont(myfont);
		rangeStartLabel.setPreferredSize(new Dimension(90, 30));
		rangeEndLabel.setFont(myfont);
		rangeEndLabel.setPreferredSize(new Dimension(20, 30));
				
		JComboBox ComboBox=new JComboBox();
		ComboBox.addItem("IP Range");
		ComboBox.addItem("Random");
		ComboBox.addItem("Text File");
		
		toolbar1.add(rangeStartLabel);
		toolbar1.add(rangeStartTextField);
		toolbar1.add(rangeEndLabel);
		toolbar1.add(rangeEndTextField);
		toolbar1.add(ComboBox);
		
		try {
			Image img = ImageIO.read(new File("icon\\java image.png"));
			toolbar1.add(new JButton(new ImageIcon(img.getScaledInstance(15, 15, Image.SCALE_SMOOTH))));
		
		} catch (IOException e2) {
			e2.printStackTrace();
		}
			
			
		JLabel hostNameLabel = new JLabel("Hostname: ");
		JTextField hostNameTextField = new JTextField(10);	
		JButton b1 = new JButton("IP��");
				
		JComboBox optionComboBox = new JComboBox();
		optionComboBox.addItem("Netmask");
		optionComboBox.addItem("/16");
		optionComboBox.addItem("/24");
		optionComboBox.addItem("/26");
		JButton startButton = new JButton("��Start");
				
		hostNameLabel.setFont(myfont);
		hostNameTextField.setPreferredSize(new Dimension(90, 30));
		b1.setPreferredSize(new Dimension(50, 30));
		optionComboBox.setPreferredSize(new Dimension(90, 30));
		startButton.setPreferredSize(new Dimension(60, 30));
				
		toolbar2.add(hostNameLabel);
		
		toolbar2.add(hostNameTextField);
		toolbar2.add(b1);
		toolbar2.add(optionComboBox);
		toolbar2.add(startButton);
		
		Image img2 = null;
		try {
			img2 = ImageIO.read(new File("icon\\java image2.png"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		toolbar2.add(new JButton(new ImageIcon(img2.getScaledInstance(15, 15, Image.SCALE_SMOOTH))));
		
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1, BorderLayout.NORTH);
		pane.add(toolbar2, BorderLayout.SOUTH);
		
		add(pane, BorderLayout.NORTH);
		// �� �� ��	
		String myIp = null;
		String myHostname = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();
			
			myIp = ia.getHostAddress();
			myHostname = ia.getHostName();
		} catch (Exception e) {

		}
		String fixedIp = myIp.substring(0, myIp.lastIndexOf(".") + 1);
		rangeStartTextField.setText(fixedIp + "1");
		rangeEndTextField.setText(fixedIp + "254");
		hostNameTextField.setText(myHostname);

		// System.out.println(myIp + " " + myHostname);
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pinging[] pg = new Pinging[254];

				for (int i = 0; i <= 253; i++) {
					pg[i] = new Pinging(fixedIp + (i + 1));
					pg[i].start();
				}
				for (int i = 0; i <= 253; i++) {
					Object[] msg = pg[i].getMsg();
					stats[i][0] = msg[0];
					if (msg[1] != null) {
								ExecutorService es = Executors.newFixedThreadPool(20);
								String ip = fixedIp + i;
								int timeout = 100;
								ArrayList<Future<ScanResult>> futures = new ArrayList<>();
								for(int port = 1; port <= 1024; port++) {
									futures.add(portIsOpen(es, ip, port, timeout));
								}
								try {
								es.awaitTermination(200L, TimeUnit.MILLISECONDS);
								int openPorts = 0;
								for(final Future<ScanResult> f: futures) {
									if(f.get().isOpen()) {
										openPorts++;
										getPort = Integer.toString(f.get().getPort());
										break;
									}
								}
								}catch (Exception ee) {
									ee.printStackTrace();
								}
								stats[i][4] = getPort;
							}
							if(msg[1] == null) {
								msg[1] = "[n/a]";
								msg[2] = "[n/s]";
								msg[3] = "[n/s]";
								stats[i][4] = "[n/s]";
							} else if (msg[3] == null) {
								msg[3] = "[n/a]";
							}
							stats[i][0] = msg[0];
							stats[i][1] = msg[1];
							stats[i][2] = msg[2];
							stats[i][3] = msg[3];
						}//ping
						jTable.repaint();
					}
				});
			}
	public static Future<ScanResult> portIsOpen(final ExecutorService es,
			final String ip,final int port,final int timeout){
		return es.submit(new Callable<ScanResult>() {
			@Override
			public ScanResult call() throws Exception {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new ScanResult(port, true);
				} catch (Exception e) {
					return new ScanResult(port,false);
				}
			}
		});
	}
	public Object[][] initializeTableData() {
		Object[][] results = new Object[254][5];
		return results;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PingOutline PO = new PingOutline();
	}
}
