package GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GUIComponents.ButtonCustom;
import GUIComponents.CustomCellEditorRegoleSingole;
import GiocoDellOca.Dado;
import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Pedina;
import GiocoDellOca.Personalizzazione;
import GiocoDellOca.Regola;
import GiocoDellOca.Scenario;
import GiocoDellOca.TipologiaRegolaEnum;

public class GiocoDellOcaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRegoleSelezionabili;
	private GiocoDellOca giocoDellOca;
	private List<Regola> listaRegoleSingole;
	private Map<String, Set<Regola>> mapRegoleSet;
	private List<Scenario> listScenari;
	private List<Personalizzazione> listPersonalizzazioni;
	private JTable tableRegoleSelezionate;
	private JTable tableScenariSelezionabili;
	private JTable tableScenarioSelezionato;
	private JTable tableRegoleSetSelezionabili;
	private JTable tableRegoleSetSelezionato;
	private JTable tablePedineSelezionabili;
	private JTable tablePedinaSelezionata;
	private JTable tableDadiSelezionabili;
	private JTable tableDadoSelezionato;
	
	private void SwitchToPanel (JLayeredPane layeredPane, JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	private void hideColumn(JTable table, int columnIndex) {
		TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(columnIndex);
        columnModel.removeColumn(column);
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiocoDellOcaGUI frame = new GiocoDellOcaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public GiocoDellOcaGUI() {		
//		this.giocoDellOca = new GiocoDellOca();
//		//Creazione panel
//		this.setTitle("Gioco dell'Oca");
//        ImageIcon icon = new ImageIcon("./src/images/Icon.png");
//        
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        contentPane = new JPanel();
//        
//        this.setIconImage(icon.getImage());
//        this.getContentPane().add(contentPane);
//        this.pack();
//        this.setLocationByPlatform(true);
//        
//        
//		setBounds(100, 100, 533, 341);
//        setLocationRelativeTo(null);
//		
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(new CardLayout(0, 0));
		
		this.giocoDellOca = new GiocoDellOca();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
     // Ottieni le dimensioni dello schermo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Imposta il frame per occupare l'intero schermo utilizzando setBounds
        setBounds(0, 0, screenSize.width, screenSize.height);
//		setBounds(100, 100, 533, 341);
//        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		this.setTitle("Gioco dell'Oca");
		ImageIcon icon = new ImageIcon("./src/images/Icon.png");
		this.setIconImage(icon.getImage());
		
		//Inizio semplificazione con i manager
		
		//MenuPanelManager menuPaneManager = new MenuPanelManager(contentPane);
		JLayeredPane layeredPane;
		JPanel menuPrincipalePanel;
		JLabel lblTitleMenuPrincipale;
		ButtonCustom btnConfiguraNuovaPartitaSP;
		JPanel selezioneTipologiaRegolePanel;
		JLabel lblTitleSelezioneTipologiaRegole;
		ButtonCustom btnTipologiaRegoleSet;
		ButtonCustom btnTipologiaRegoleSingole;
		ButtonCustom btnReturnToMenuFromSelTipReg;
		JPanel selezioneRegoleSingolePanel;
		JLabel lblTitleSelezioneRegoleSingole;
		JScrollPane scrollPaneRegoleSelezionabili;
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_609521392636900");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		menuPrincipalePanel = new JPanel();
		layeredPane.add(menuPrincipalePanel, "name_610369483648900");
		menuPrincipalePanel.setLayout(null);
		
		lblTitleMenuPrincipale = new JLabel("Gioco dell'Oca");
		lblTitleMenuPrincipale.setFont(new Font("Segoe UI", Font.BOLD, 99));
		lblTitleMenuPrincipale.setBounds(420, 10, 672, 112);
		menuPrincipalePanel.add(lblTitleMenuPrincipale);
		
		btnConfiguraNuovaPartitaSP = new ButtonCustom("Nuova partita singleplayer", ButtonCustom.ButtonStyle.PRIMARY);
		
		btnConfiguraNuovaPartitaSP.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		btnConfiguraNuovaPartitaSP.setBounds(564, 220, 384, 92);
		menuPrincipalePanel.add(btnConfiguraNuovaPartitaSP);
		
		selezioneTipologiaRegolePanel = new JPanel();
		layeredPane.add(selezioneTipologiaRegolePanel, "name_610398291392000");
		selezioneTipologiaRegolePanel.setLayout(null);
		
		lblTitleSelezioneTipologiaRegole = new JLabel("Selezione tipologia regole");
		lblTitleSelezioneTipologiaRegole.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezioneTipologiaRegole.setBounds(383, 10, 745, 81);
		selezioneTipologiaRegolePanel.add(lblTitleSelezioneTipologiaRegole);
		
		btnTipologiaRegoleSet = new ButtonCustom("Set di regole predefinite", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 28));
		btnTipologiaRegoleSet.setBounds(831, 344, 365, 128);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSet);
		
		btnTipologiaRegoleSingole = new ButtonCustom("Regole singole", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 28));
		btnTipologiaRegoleSingole.setBounds(316, 344, 326, 128);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSingole);
		
		btnReturnToMenuFromSelTipReg = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelTipReg.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelTipReg.setBounds(0, 0, 178, 74);
		selezioneTipologiaRegolePanel.add(btnReturnToMenuFromSelTipReg);
		
		selezioneRegoleSingolePanel = new JPanel();
		layeredPane.add(selezioneRegoleSingolePanel, "name_166112297214100");
		selezioneRegoleSingolePanel.setLayout(null);
		
		lblTitleSelezioneRegoleSingole = new JLabel("Selezione regole singole");
		lblTitleSelezioneRegoleSingole.setBounds(414, 10, 683, 81);
		lblTitleSelezioneRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 60));
		selezioneRegoleSingolePanel.add(lblTitleSelezioneRegoleSingole);
		
		scrollPaneRegoleSelezionabili = new JScrollPane();
		scrollPaneRegoleSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSelezionabili.setBounds(10, 211, 731, 462);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionabili);
		
        ToolTipManager.sharedInstance().setInitialDelay(200);
        
		//TableRegoleSelezionabili
		//var tableRegoleSelezionabiliManager = new TabellaRegoleSelezionabiliManager(menuPaneManager);
		//tableRegoleSelezionabili = tableRegoleSelezionabiliManager.getTableRegoleSelezionabili();
		
		DefaultTableModel tableRegoleSelezionabiliModel;
		
		tableRegoleSelezionabili = new JTable() {
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String descrizioneRegola = (String) getValueAt(row, 0);

                    return "<html>" + descrizioneRegola + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableRegoleSelezionabili.setRowHeight(40);
		tableRegoleSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
		tableRegoleSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceRegola", "Regole selezionabili"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRegoleSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tableRegoleSelezionabili.getColumnModel().getColumn(1).setResizable(false);
		tableRegoleSelezionabili.getColumnModel().getColumn(1).setPreferredWidth(103);
		//menuPaneManager.getScrollPaneRegoleSelezionabili().setViewportView(tableRegoleSelezionabili);
		scrollPaneRegoleSelezionabili.setViewportView(tableRegoleSelezionabili);
		
		tableRegoleSelezionabiliModel = (DefaultTableModel) tableRegoleSelezionabili.getModel();
		
		hideColumn(tableRegoleSelezionabili, 0);
		
		//TableRegoleSelezionate
		//var tableRegoleSelezionateManager = new TabellaRegoleSelezionateManager(menuPaneManager);
		
		JScrollPane scrollPaneRegoleSelezionate;
		DefaultTableModel tableRegoleSelezionateModel;
		ButtonCustom btnAvanzaToSelezionaScenarioRegSing;
		ButtonCustom btnReturnToMenuFromSelRegSing;
		
		scrollPaneRegoleSelezionate = new JScrollPane();
		scrollPaneRegoleSelezionate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSelezionate.setBounds(771, 211, 731, 462);
		//menuPaneManager.getSelezioneRegoleSingolePanel().add(scrollPaneRegoleSelezionate);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionate);
		
		tableRegoleSelezionate = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String descrizioneRegola = (String) getValueAt(row, 0);

                    return "<html>" + descrizioneRegola + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableRegoleSelezionate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableRegoleSelezionate.setRowHeight(40);
            
		tableRegoleSelezionate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceRegola", "Regola selezionata", "Valore"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRegoleSelezionate.getColumnModel().getColumn(0).setResizable(false);
		tableRegoleSelezionate.getColumnModel().getColumn(1).setResizable(false);
		tableRegoleSelezionate.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableRegoleSelezionate.getColumnModel().getColumn(2).setResizable(false);
		tableRegoleSelezionate.getColumnModel().getColumn(2).setPreferredWidth(50);
		scrollPaneRegoleSelezionate.setViewportView(tableRegoleSelezionate);
		
		tableRegoleSelezionateModel = (DefaultTableModel) tableRegoleSelezionate.getModel();

		//Bottoni
		btnAvanzaToSelezionaScenarioRegSing = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnAvanzaToSelezionaScenarioRegSing.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvanzaToSelezionaScenarioRegSing.setBounds(1150, 702, 352, 52);
		//menuPaneManager.getSelezioneRegoleSingolePanel().add(btnAvanzaToSelezionaScenarioRegSing);
		selezioneRegoleSingolePanel.add(btnAvanzaToSelezionaScenarioRegSing);
				
		btnReturnToMenuFromSelRegSing = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelRegSing.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelRegSing.setBounds(0, 0, 178, 74);
		//menuPaneManager.getSelezioneRegoleSingolePanel().add(btnReturnToMenuFromSelRegSing);
		selezioneRegoleSingolePanel.add(btnReturnToMenuFromSelRegSing);
		
		Map<String, String[]> dropdownValuesTableRegoleSelezionate = new HashMap<>();
		dropdownValuesTableRegoleSelezionate.put("Casella", new String[]{"42", "63","90"});
		dropdownValuesTableRegoleSelezionate.put("Dado", new String[]{"1","2" ,"3"});
		TableColumn statusColumn = tableRegoleSelezionate.getColumnModel().getColumn(2);
        statusColumn.setCellEditor(new CustomCellEditorRegoleSingole(dropdownValuesTableRegoleSelezionate, giocoDellOca));
		
		//tableRegoleSelezionate = tableRegoleSelezionateManager.getTableRegoleSelezionate();
		hideColumn(tableRegoleSelezionate, 0);
		
		//TableScenariSelezionabili
		//var tableScenariSelezionabiliManager = new TabellaScenariSelezionabiliManager(menuPaneManager);
		
		JPanel selezioneScenarioPanel;
		JLabel lblTitleSelezioneScenario;
		JScrollPane scrollPaneScenariSelezionabili;
		DefaultTableModel tableScenariSelezionabiliModel;
		
		selezioneScenarioPanel = new JPanel();
		layeredPane.add(selezioneScenarioPanel, "name_769942002122600");
		selezioneScenarioPanel.setLayout(null);
		
		lblTitleSelezioneScenario = new JLabel("Selezione scenario");
		lblTitleSelezioneScenario.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezioneScenario.setBounds(414, 10, 683, 81);
		selezioneScenarioPanel.add(lblTitleSelezioneScenario);
		
		scrollPaneScenariSelezionabili = new JScrollPane();
		scrollPaneScenariSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneScenariSelezionabili.setBounds(10, 211, 731, 461);
		selezioneScenarioPanel.add(scrollPaneScenariSelezionabili);
		
		tableScenariSelezionabili = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableScenariSelezionabili.setRowHeight(40);
		tableScenariSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableScenariSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceScenario", "Scenario selezionabile"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableScenariSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tableScenariSelezionabili.getColumnModel().getColumn(0).setPreferredWidth(118);
		tableScenariSelezionabili.getColumnModel().getColumn(1).setResizable(false);
		tableScenariSelezionabili.getColumnModel().getColumn(1).setPreferredWidth(78);
		scrollPaneScenariSelezionabili.setViewportView(tableScenariSelezionabili);
		
		tableScenariSelezionabiliModel = (DefaultTableModel) tableScenariSelezionabili.getModel();
		
		hideColumn(tableScenariSelezionabili, 0);
		
		//tableScenariSelezionabili = tableScenariSelezionabiliManager.getTableScenariSelezionabili();
		
		//TableScenarioSelezionato
		//var tableScenarioSelezionatoManager = new TabellaScenarioSelezionatoManager(menuPaneManager, tableScenariSelezionabiliManager);
		
		JScrollPane scrollPaneScenarioSelezionato;
		ButtonCustom btnReturnToSelRegole;
		ButtonCustom btnReturnToMenuFromSelScen;
		ButtonCustom btnAvanzaToSelezionePers;
		DefaultTableModel tableScenarioSelezionatoModel;
		JPanel selezioneRegoleSetPanel;
		JLabel lblTitleSelezioneRegoleSet;
		JScrollPane scrollPaneRegoleSetSelezionabili;
		
		scrollPaneScenarioSelezionato = new JScrollPane();
		scrollPaneScenarioSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneScenarioSelezionato.setBounds(771, 211, 731, 462);
		//tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(scrollPaneScenarioSelezionato);
		selezioneScenarioPanel.add(scrollPaneScenarioSelezionato);
		
		tableScenarioSelezionato = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableScenarioSelezionato.setRowHeight(40);
		tableScenarioSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableScenarioSelezionato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceScenario", "Scenario selezionato"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableScenarioSelezionato.getColumnModel().getColumn(0).setResizable(false);
		tableScenarioSelezionato.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableScenarioSelezionato.getColumnModel().getColumn(1).setResizable(false);
		tableScenarioSelezionato.getColumnModel().getColumn(1).setPreferredWidth(84);
		scrollPaneScenarioSelezionato.setViewportView(tableScenarioSelezionato);
				
		//Bottoni
		btnReturnToSelRegole = new ButtonCustom("Selezione regole", ButtonCustom.ButtonStyle.WHITE);	
		btnReturnToSelRegole.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnReturnToSelRegole.setBounds(10, 702, 352, 52);
		//tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(btnReturnToSelRegole);
		selezioneScenarioPanel.add(btnReturnToSelRegole);
		
		btnReturnToMenuFromSelScen = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelScen.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelScen.setBounds(0, 0, 178, 74);
		//tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(btnReturnToMenuFromSelScen);
		selezioneScenarioPanel.add(btnReturnToMenuFromSelScen);
		
		btnAvanzaToSelezionePers = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.WHITE);
		btnAvanzaToSelezionePers.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvanzaToSelezionePers.setBounds(1032, 702, 470, 52);
		//tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(btnAvanzaToSelezionePers);
		selezioneScenarioPanel.add(btnAvanzaToSelezionePers);
		
		tableScenarioSelezionatoModel = (DefaultTableModel) tableScenarioSelezionato.getModel();
		
		hideColumn(tableScenarioSelezionato, 0);
		
		selezioneRegoleSetPanel = new JPanel();
		//menuPaneManager.getLayeredPane().add(selezioneRegoleSetPanel, "name_790010692461100");
		layeredPane.add(selezioneRegoleSetPanel, "name_790010692461100");
		selezioneRegoleSetPanel.setLayout(null);
		
		lblTitleSelezioneRegoleSet = new JLabel("Selezione set di regole");
		lblTitleSelezioneRegoleSet.setBounds(439, 10, 634, 81);
		lblTitleSelezioneRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 60));
		selezioneRegoleSetPanel.add(lblTitleSelezioneRegoleSet);
		
		scrollPaneRegoleSetSelezionabili = new JScrollPane();
		scrollPaneRegoleSetSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSetSelezionabili.setBounds(10, 211, 731, 462);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionabili);
		
		//tableScenarioSelezionato = tableScenarioSelezionatoManager.getTableScenarioSelezionato();
		
		//TableRegoleSetSelezionabili
		//var tableRegoleSetSelezionabiliManager = new TabellaRegoleSetSelezionabiliManager(tableScenarioSelezionatoManager);
		
		DefaultTableModel tableRegoleSetSelezionabiliModel;
		
		tableRegoleSetSelezionabili = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableRegoleSetSelezionabili.setRowHeight(40);
		tableRegoleSetSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableRegoleSetSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Set di regole selezionabili"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRegoleSetSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tableRegoleSetSelezionabili.getColumnModel().getColumn(0).setPreferredWidth(134);
		//tableScenarioSelezionatoManager.getScrollPaneRegoleSetSelezionabili().setViewportView(tableRegoleSetSelezionabili);
		scrollPaneRegoleSetSelezionabili.setViewportView(tableRegoleSetSelezionabili);
		
		tableRegoleSetSelezionabiliModel = (DefaultTableModel) tableRegoleSetSelezionabili.getModel();
		
		//tableRegoleSetSelezionabili = tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabili();
		
		//TableRegoleSetSelezionato
		//var tableRegoleSetSelezionatoManager = new TabellaRegoleSetSelezionatoManager(tableScenarioSelezionatoManager);
		
		JScrollPane scrollPaneRegoleSetSelezionato;
		DefaultTableModel tableRegoleSetSelezionatoModel;
		ButtonCustom btnAvanzaToSelezionaScenarioRegSet;
		ButtonCustom btnReturnToMenuFromSelRegSet;
		
		scrollPaneRegoleSetSelezionato = new JScrollPane();
		scrollPaneRegoleSetSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSetSelezionato.setBounds(771, 211, 731, 462);
		//tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel().add(scrollPaneRegoleSetSelezionato);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionato);
		
		tableRegoleSetSelezionato = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableRegoleSetSelezionato.setRowHeight(40);
		tableRegoleSetSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableRegoleSetSelezionato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceRegola", "Regola selezionata", "Valore"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRegoleSetSelezionato.getColumnModel().getColumn(0).setResizable(false);
		tableRegoleSetSelezionato.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableRegoleSetSelezionato.getColumnModel().getColumn(1).setResizable(false);
		tableRegoleSetSelezionato.getColumnModel().getColumn(1).setPreferredWidth(122);
		tableRegoleSetSelezionato.getColumnModel().getColumn(2).setResizable(false);
		scrollPaneRegoleSetSelezionato.setViewportView(tableRegoleSetSelezionato);
		
		tableRegoleSetSelezionatoModel = (DefaultTableModel) tableRegoleSetSelezionato.getModel();

		btnAvanzaToSelezionaScenarioRegSet = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnAvanzaToSelezionaScenarioRegSet.setFont(new Font("Tahoma", Font.BOLD, 30));;		
		btnAvanzaToSelezionaScenarioRegSet.setBounds(1150, 702, 352, 52);
		//tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel().add(btnAvanzaToSelezionaScenarioRegSet);
		selezioneRegoleSetPanel.add(btnAvanzaToSelezionaScenarioRegSet);
		
		btnReturnToMenuFromSelRegSet = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelRegSet.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelRegSet.setBounds(0, 0, 178, 74);
		//tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel().add(btnReturnToMenuFromSelRegSet);
		selezioneRegoleSetPanel.add(btnReturnToMenuFromSelRegSet);
		
		//tableRegoleSetSelezionato = tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionato();
		hideColumn(tableRegoleSetSelezionato, 0);
		
		//TipologiaPersonalizzazioni
		//var tipologiaPersonalizzazioniManager = new TipologiaPersonalizzazioniManager(menuPaneManager);
		
		JPanel selezioneTipologiaPersonalizzazionePanel;
		JLabel lblTitleSelezioneTipologiaPersonalizzazione;
		ButtonCustom btnReturnToMenuFromSelPers;
		ButtonCustom btnTipologiaPersDado;
		ButtonCustom btnTipologiaPersPedina;
		ButtonCustom btnReturnToSelScenFromSelPers;
		
		selezioneTipologiaPersonalizzazionePanel = new JPanel();
		//menuPaneManager.getLayeredPane().add(selezioneTipologiaPersonalizzazionePanel, "name_1048915761636599");
		layeredPane.add(selezioneTipologiaPersonalizzazionePanel, "name_1048915761636599");
		selezioneTipologiaPersonalizzazionePanel.setLayout(null);
		
		lblTitleSelezioneTipologiaPersonalizzazione = new JLabel("Selezione personalizzazioni");
		lblTitleSelezioneTipologiaPersonalizzazione.setBounds(367, 10, 777, 81);
		lblTitleSelezioneTipologiaPersonalizzazione.setFont(new Font("Segoe UI", Font.BOLD, 60));
		selezioneTipologiaPersonalizzazionePanel.add(lblTitleSelezioneTipologiaPersonalizzazione);
		
		btnReturnToMenuFromSelPers = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelPers.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelPers.setBounds(0, 0, 178, 74);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToMenuFromSelPers);
		
		btnTipologiaPersDado = new ButtonCustom("Dadi", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaPersDado.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnTipologiaPersDado.setBounds(831, 344, 326, 128);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersDado);
		
		btnTipologiaPersPedina = new ButtonCustom("Pedina", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaPersPedina.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnTipologiaPersPedina.setBounds(316, 344, 326, 128);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersPedina);
		
		btnReturnToSelScenFromSelPers = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnReturnToSelScenFromSelPers.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnReturnToSelScenFromSelPers.setBounds(10, 702, 352, 52);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToSelScenFromSelPers);
		
		//TablePedineSelezionabili
		//var tablePedineSelezionabiliManager = new TabellaPedineSelezionabiliManager(menuPaneManager);	
		
		JPanel selezionePedinaPanel;
		JLabel lblTitleSelezionePedina;
		ButtonCustom btnReturnToMenuFromSelPedina;
		JScrollPane scrollPanePedineSelezionabili;
		DefaultTableModel tablePedineSelezionabiliModel;
		
		selezionePedinaPanel = new JPanel();
		//menuPaneManager.getLayeredPane().add(selezionePedinaPanel, "name_1050308981416700");
		layeredPane.add(selezionePedinaPanel, "name_1050308981416700");
		selezionePedinaPanel.setLayout(null);
		
		lblTitleSelezionePedina = new JLabel("Selezione pedina");
		lblTitleSelezionePedina.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezionePedina.setBounds(518, 10, 476, 81);
		selezionePedinaPanel.add(lblTitleSelezionePedina);
		
		btnReturnToMenuFromSelPedina = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelPedina.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelPedina.setBounds(0, 0, 178, 74);
		selezionePedinaPanel.add(btnReturnToMenuFromSelPedina);
		
		
		scrollPanePedineSelezionabili = new JScrollPane();
		scrollPanePedineSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPanePedineSelezionabili.setBounds(10, 211, 731, 462);
		selezionePedinaPanel.add(scrollPanePedineSelezionabili);
			
		tablePedineSelezionabili = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tablePedineSelezionabili.setRowHeight(40);
		tablePedineSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tablePedineSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodicePedina", "Pedine selezionabili"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePedineSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tablePedineSelezionabili.getColumnModel().getColumn(1).setResizable(false);
		scrollPanePedineSelezionabili.setViewportView(tablePedineSelezionabili);
		
		tablePedineSelezionabiliModel = (DefaultTableModel) tablePedineSelezionabili.getModel();
		
		//tablePedineSelezionabili = tablePedineSelezionabiliManager.getTablePedineSelezionabili();
		hideColumn(tablePedineSelezionabili, 0);
		
		//TablePedinaSelezionata
		//var tablePedineSelezionataManager = new TabellaPedinaSelezionataManager(tablePedineSelezionabiliManager);
		
		JScrollPane scrollPanePedinaSelezionata;
		DefaultTableModel tablePedinaSelezionataModel;
		ButtonCustom btnSelezionePersonalizzazioniFromSelPed;
		ButtonCustom btnAvviaPartitaFromSelPedina;
		
		scrollPanePedinaSelezionata = new JScrollPane();
		scrollPanePedinaSelezionata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPanePedinaSelezionata.setBounds(771, 211, 731, 462);
		//tablePedineSelezionabiliManager.getSelezionePedinaPanel().add(scrollPanePedinaSelezionata);
		selezionePedinaPanel.add(scrollPanePedinaSelezionata);
		
		tablePedinaSelezionata = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tablePedinaSelezionata.setRowHeight(40);
		tablePedinaSelezionata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tablePedinaSelezionata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodicePedina", "Pedina"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablePedinaSelezionata.getColumnModel().getColumn(0).setResizable(false);
		tablePedinaSelezionata.getColumnModel().getColumn(1).setResizable(false);
		scrollPanePedinaSelezionata.setViewportView(tablePedinaSelezionata);
		
		tablePedinaSelezionataModel = (DefaultTableModel) tablePedinaSelezionata.getModel();

		btnSelezionePersonalizzazioniFromSelPed = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.WHITE);
		btnSelezionePersonalizzazioniFromSelPed.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSelezionePersonalizzazioniFromSelPed.setBounds(10, 702, 463, 52);
		//tablePedineSelezionabiliManager.getSelezionePedinaPanel().add(btnSelezionePersonalizzazioniFromSelPed);
		selezionePedinaPanel.add(btnSelezionePersonalizzazioniFromSelPed);
		
		btnAvviaPartitaFromSelPedina = new ButtonCustom("Avvia partita", ButtonCustom.ButtonStyle.PRIMARY);
		btnAvviaPartitaFromSelPedina.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvviaPartitaFromSelPedina.setBounds(1150, 702, 352, 52);
		//tablePedineSelezionabiliManager.getSelezionePedinaPanel().add(btnAvviaPartitaFromSelPedina);
		selezionePedinaPanel.add(btnAvviaPartitaFromSelPedina);
		
		//tablePedinaSelezionata = tablePedineSelezionataManager.getTablePedinaSelezionata();
		hideColumn(tablePedinaSelezionata, 0);

		//TableDadiSelezionabili
		//var tableDadiSelezionabiliManager = new TabellaDadiSelezionabiliManager(menuPaneManager);
		
		JPanel selezioneDadiPanel;
		JLabel lblTitleSelezioneDadi;
		ButtonCustom btnReturnToMenuFromSelDado;
		JScrollPane scrollPaneDadiSelezionabili;
		DefaultTableModel tableDadiSelezionabiliModel;
		
		selezioneDadiPanel = new JPanel();
		//menuPaneManager.getLayeredPane().add(selezioneDadiPanel, "name_1050468066312900");
		layeredPane.add(selezioneDadiPanel, "name_1050468066312900");
		selezioneDadiPanel.setLayout(null);
		
		lblTitleSelezioneDadi = new JLabel("Selezione dadi");
		lblTitleSelezioneDadi.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezioneDadi.setBounds(550, 10, 411, 81);
		selezioneDadiPanel.add(lblTitleSelezioneDadi);
		
		btnReturnToMenuFromSelDado = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelDado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelDado.setBounds(0, 0, 178, 74);
		selezioneDadiPanel.add(btnReturnToMenuFromSelDado);
		
		
		scrollPaneDadiSelezionabili = new JScrollPane();
		scrollPaneDadiSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneDadiSelezionabili.setBounds(10, 211, 731, 462);
		selezioneDadiPanel.add(scrollPaneDadiSelezionabili);
		
		tableDadiSelezionabili = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableDadiSelezionabili.setRowHeight(40);
		tableDadiSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableDadiSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceDado", "Dadi selezionabili"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDadiSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tableDadiSelezionabili.getColumnModel().getColumn(1).setResizable(false);
		scrollPaneDadiSelezionabili.setViewportView(tableDadiSelezionabili);
		
		tableDadiSelezionabiliModel = (DefaultTableModel) tableDadiSelezionabili.getModel();
		
		//tableDadiSelezionabili = tableDadiSelezionabiliManager.getTableDadiSelezionabili();
		hideColumn(tableDadiSelezionabili, 0);

		//TableDadoSelezionato
		//var tableDadoSelezionatoManager = new TabellaDadoSelezionatoManager(tableDadiSelezionabiliManager);
		
		JScrollPane scrollPaneDadoSelezionato;
		DefaultTableModel tableDadoSelezionatoModel;
		ButtonCustom btnSelezionePersonalizzazioniFromSelDadi;
		ButtonCustom btnAvviaPartitaFromSelDado;
		
		scrollPaneDadoSelezionato = new JScrollPane();
		scrollPaneDadoSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneDadoSelezionato.setBounds(771, 211, 731, 462);
		//tableDadiSelezionabiliManager.getSelezioneDadiPanel().add(scrollPaneDadoSelezionato);
		selezioneDadiPanel.add(scrollPaneDadoSelezionato);
		
		tableDadoSelezionato = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String text = (String) getValueAt(row, column);

                    return "<html>" + text + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableDadoSelezionato.setRowHeight(40);
		tableDadoSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableDadoSelezionato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceDado", "Dadi selezionati"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDadoSelezionato.getColumnModel().getColumn(0).setResizable(false);
		tableDadoSelezionato.getColumnModel().getColumn(1).setResizable(false);
		tableDadoSelezionato.getColumnModel().getColumn(1).setPreferredWidth(91);
		scrollPaneDadoSelezionato.setViewportView(tableDadoSelezionato);
		
		tableDadoSelezionatoModel = (DefaultTableModel) tableDadoSelezionato.getModel();

		//Bottoni
		btnSelezionePersonalizzazioniFromSelDadi = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.WHITE);
		btnSelezionePersonalizzazioniFromSelDadi.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSelezionePersonalizzazioniFromSelDadi.setBounds(10, 702, 454, 52);
		//tableDadiSelezionabiliManager.getSelezioneDadiPanel().add(btnSelezionePersonalizzazioniFromSelDadi);
		selezioneDadiPanel.add(btnSelezionePersonalizzazioniFromSelDadi);
				
		btnAvviaPartitaFromSelDado = new ButtonCustom("Avvia partita", ButtonCustom.ButtonStyle.PRIMARY);
		btnAvviaPartitaFromSelDado.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvviaPartitaFromSelDado.setBounds(1150, 702, 352, 52);
		//tableDadiSelezionabiliManager.getSelezioneDadiPanel().add(btnAvviaPartitaFromSelDado);
		selezioneDadiPanel.add(btnAvviaPartitaFromSelDado);
		
		//tableDadoSelezionato = tableDadoSelezionatoManager.getTableDadoSelezionato();
		hideColumn(tableDadoSelezionato, 0);
		
		//Action listeners
        //menuPaneManager.getBtnConfiguraNuovaPartitaSP()
		btnConfiguraNuovaPartitaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.configuraNuovaPartitaSP();
				
				GiocoDellOcaGUI.this.listaRegoleSingole = giocoDellOca.getListaRegoleSingole();
				GiocoDellOcaGUI.this.mapRegoleSet = giocoDellOca.getMapRegoleSet();
				GiocoDellOcaGUI.this.listScenari = giocoDellOca.getListaScenari();
				GiocoDellOcaGUI.this.listPersonalizzazioni = giocoDellOca.getListaPersonalizzazioni();
				
				//tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel() (tutti i model sono dentro i metodi get dei rispettivi manager)
				
				if(tableRegoleSelezionabiliModel.getRowCount() == 0 &&  tableRegoleSelezionateModel.getRowCount() == 0) 
				{
					for(var regola : listaRegoleSingole) {
						tableRegoleSelezionabiliModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione()});
					}
				}
				
				if(tableRegoleSetSelezionabiliModel.getRowCount() == 0 && tableRegoleSetSelezionatoModel.getRowCount() == 0) 
				{
					for (var key : mapRegoleSet.keySet()) {
						tableRegoleSetSelezionabiliModel.addRow(new Object[] {key});
					}
				}
				
				if(tableScenariSelezionabiliModel.getRowCount() == 0 && tableScenarioSelezionatoModel.getRowCount() == 0) 
				{
					for(var scenario : listScenari) {
						tableScenariSelezionabiliModel.addRow(new Object[] {scenario.getCodiceScenario(), scenario.getDescrizione()});
					}
				}
				
				if(tablePedineSelezionabili.getRowCount() == 0 && tablePedinaSelezionata.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Pedina)
							tablePedineSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
					}
				}
				
				if(tableDadiSelezionabiliModel.getRowCount() == 0 && tableDadoSelezionatoModel.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Dado)
							tableDadiSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
					}
				}
									
				//SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getSelezioneTipologiaRegolePanel());
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});
		
		//menuPaneManager.getBtnTipologiaRegoleSingole()
		
		btnTipologiaRegoleSingole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneRegoleSingolePanel);
			}
		});
		
		//tableRegoleSelezionateManager.getBtnAvanzaToSelezionaScenarioRegSing()
		
		btnAvanzaToSelezionaScenarioRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole().clear();
				
				for (int row = 0; row < tableRegoleSelezionateModel.getRowCount(); row++) {
		                var codiceRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 0);
		                var descrizioneRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 1);
		                var proprietaRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 2);
		                
	                	TipologiaRegolaEnum tipologiaRegola = null;
                		
                		for (var elem : listaRegoleSingole) {
                           	  	if (elem.getCodiceRegola().equals(codiceRegola)){
                           	  		tipologiaRegola = elem.getTipologiaRegola(); 
                           	  		break;
                           	  	}
                		}
		                
		                var regola = new Regola(codiceRegola, descrizioneRegola, proprietaRegola, tipologiaRegola);
		                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addRegolaToList(regola);		                
				}
				SwitchToPanel(layeredPane, selezioneScenarioPanel);
			}
		});
		
		//anche gli altri button sono nei vari manager, accessibili dai getter
		
		btnAvanzaToSelezionaScenarioRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole().clear();
				
				for (int row = 0; row < tableRegoleSetSelezionatoModel.getRowCount(); row++) {
		                var codiceRegola = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 0);
		                var descrizioneRegola = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 1);
		                var proprietaRegola = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 2);
		                
	                	TipologiaRegolaEnum tipologiaRegola = null;
		                
		                outerLoop:
	                		for (var entry : mapRegoleSet.entrySet()) {
	                            for (var value :  entry.getValue()) {
	                           	  	if (value.getCodiceRegola().equals(codiceRegola)) {
	                           	  		tipologiaRegola = value.getTipologiaRegola();
	                           	  		break outerLoop;
	                           	  	}
	                          	  }                        	                         
	                		}
		                
		                var regola = new Regola(codiceRegola, descrizioneRegola, proprietaRegola, tipologiaRegola);
		                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addRegolaToList(regola);		                
				}
				
				SwitchToPanel(layeredPane, selezioneScenarioPanel);
			}
		});
		
		btnTipologiaRegoleSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneRegoleSetPanel);
			}
		});
		
		btnReturnToSelRegole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});
		
		btnAvanzaToSelezionePers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(tableScenarioSelezionatoModel.getRowCount() > 0)
				{
	                var codiceScenario = (String) tableScenarioSelezionatoModel.getValueAt(0, 0);
	                var descrizioneScenario = (String) tableScenarioSelezionatoModel.getValueAt(0, 1);
	                String descrizioneCasellaOca = "";
	            	String descrizioneCasellaPonte = "";
	            	String descrizioneCasellaLocanda = "";
	            	String descrizioneCasellaPrigione = "";
	            	String descrizioneCasellaLabirinto = "";
	            	String descrizioneCasellaScheletro = "";
	                
	            	for(var scenario : listScenari) {
							if(scenario.getCodiceScenario().equals(codiceScenario)) {
								descrizioneCasellaOca = scenario.getDescrizioneCasellaOca();
				            	descrizioneCasellaPonte = scenario.getDescrizioneCasellaPonte();
				            	descrizioneCasellaLocanda = scenario.getDescrizioneCasellaLocanda();
				            	descrizioneCasellaPrigione = scenario.getDescrizioneCasellaPrigione();
				            	descrizioneCasellaLabirinto = scenario.getDescrizioneCasellaLabirinto();
				            	descrizioneCasellaScheletro = scenario.getDescrizioneCasellaScheletro();			
				            	break;
							}
							else
								continue;
					}
	            	
	                var scenario = new Scenario(codiceScenario, descrizioneScenario, descrizioneCasellaOca, descrizioneCasellaPonte, descrizioneCasellaLocanda, descrizioneCasellaPrigione,
	                		descrizioneCasellaLabirinto, descrizioneCasellaScheletro);
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().setScenario(scenario);               				
					
					SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
				}
			}
		});
		

		btnReturnToSelScenFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneScenarioPanel);
			}
		});
		

		btnTipologiaPersPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezionePedinaPanel);
			}
		});
		

		btnTipologiaPersDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneDadiPanel);
			}
		});
		

		btnSelezionePersonalizzazioniFromSelPed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
			}
		});
		

		btnSelezionePersonalizzazioniFromSelDadi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
			}
		});
		

		btnAvviaPartitaFromSelPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePedinaSelezionataModel.getRowCount() > 0) {
					var codicePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 0);
	                var descrizionePedina= (String) tablePedinaSelezionataModel.getValueAt(0, 1);
	                String pathPedina = "";
	                
	                for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Pedina) {
							if(personalizzazione.getCodicePersonalizzazione().equals(codicePedina)) {
								pathPedina = personalizzazione.getPath();
								break;
							}
							else
								continue;
						}
					}
	                
	                var pedina = new Pedina(codicePedina, descrizionePedina, pathPedina);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(pedina);      
				}
				
				if(tableDadoSelezionatoModel.getRowCount() > 0) {
					var codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
	                var descrizioneDado= (String) tableDadoSelezionatoModel.getValueAt(0, 1);
	                String pathDado = "";
	                
	                for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Dado) {
							if(personalizzazione.getCodicePersonalizzazione().equals(codiceDado)) {
								pathDado = personalizzazione.getPath();
								break;
							}
							else
								continue;
						}
					}
	                
	                var dado = new Dado(codiceDado, descrizioneDado, pathDado);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(dado);      
				}
                			
				GiocoDellOcaGUI.this.giocoDellOca.avviaPartita();
			}
		});
		

		btnAvviaPartitaFromSelDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePedinaSelezionataModel.getRowCount() > 0) {
					var codicePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 0);
	                var descrizionePedina= (String) tablePedinaSelezionataModel.getValueAt(0, 1);
	                String pathPedina = "";
	                
	                for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Pedina) {
							if(personalizzazione.getCodicePersonalizzazione().equals(codicePedina))
								pathPedina = personalizzazione.getPath();
							else
								continue;
						}
					}
	                
	                var pedina = new Pedina(codicePedina, descrizionePedina, pathPedina);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(pedina);      
				}
				
				if(tableDadoSelezionatoModel.getRowCount() > 0) {
					var codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
	                var descrizioneDado= (String) tableDadoSelezionatoModel.getValueAt(0, 1);
	                String pathDado = "";
	                
	                for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Dado) {
							if(personalizzazione.getCodicePersonalizzazione().equals(codiceDado))
								pathDado = personalizzazione.getPath();
							else
								continue;
						}
					}
	                
	                var dado = new Dado(codiceDado, descrizioneDado, pathDado);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(dado);      
				} 
                
				GiocoDellOcaGUI.this.giocoDellOca.avviaPartita();
			}
		});
		
		btnReturnToMenuFromSelTipReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		
		btnReturnToMenuFromSelRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		
		btnReturnToMenuFromSelRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		
		btnReturnToMenuFromSelScen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		
		btnReturnToMenuFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		

		btnReturnToMenuFromSelPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		

		btnReturnToMenuFromSelDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
				tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
            	tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(layeredPane, menuPrincipalePanel);
			}
		});
		
		tableRegoleSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableRegoleSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {								
						String codiceRegola = (String) tableRegoleSelezionabiliModel.getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionabiliModel.getValueAt(row, 1);					
						tableRegoleSelezionabiliModel.removeRow(row);
						tableRegoleSelezionateModel.addRow(new Object[] {codiceRegola, descrizioneRegola});						
					}
                }
			}
		});
		
		tableRegoleSelezionate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableRegoleSelezionate.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 1);
						tableRegoleSelezionateModel.removeRow(row);
						tableRegoleSelezionabiliModel.addRow(new Object[] {codiceRegola, descrizioneRegola});
					}
                }
			}
		});
		
		tableScenariSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableScenarioSelezionato.getRowCount() == 0) {
	            	int row = tableScenariSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceScenario= (String) tableScenariSelezionabiliModel.getValueAt(row, 0);
						String descrizioneScenario = (String) tableScenariSelezionabiliModel.getValueAt(row, 1);
						tableScenariSelezionabiliModel.removeRow(row);
						tableScenarioSelezionatoModel.addRow(new Object[] {codiceScenario, descrizioneScenario});
					}
                }
			}
		});
		
		tableScenarioSelezionato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableScenarioSelezionato.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceScenario= (String) tableScenarioSelezionatoModel.getValueAt(row, 0);
						String descrizioneScenario = (String) tableScenarioSelezionatoModel.getValueAt(row, 1);
						tableScenarioSelezionatoModel.removeRow(row);
						tableScenariSelezionabiliModel.addRow(new Object[] {codiceScenario, descrizioneScenario});
					}
                }
			}
		});
		
		tableRegoleSetSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableRegoleSetSelezionato.getRowCount() == 0) {
	            	int row = tableRegoleSetSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						var codiceRegoleSet = (String) tableRegoleSetSelezionabiliModel.getValueAt(row, 0);
						var listaRegoleSet = mapRegoleSet.get(codiceRegoleSet);
						
						tableRegoleSetSelezionabiliModel.removeRow(row);
						
						for (var regola: listaRegoleSet) {						
							tableRegoleSetSelezionatoModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione(), regola.getProprietaRegola()});						
						}

					}
                }
			}
		});
		
		tableRegoleSetSelezionato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {          	
                	var rowCount = tableRegoleSetSelezionato.getRowCount();
                    var regoleSelezionate = new LinkedHashSet<Regola>(); 
                    
                	for (int row = 0; row < rowCount; row++){                		
                		var codice = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 0);
                		var descrizione = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 1);
                		var proprieta = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 2);
                		
                    	TipologiaRegolaEnum tipologiaRegola = null;
                		
                    	outerLoop:
                		for (var entry : mapRegoleSet.entrySet()) {
                            for (var value :  entry.getValue()) {
                           	  	if (value.getCodiceRegola().equals(codice)) {
                           	  		tipologiaRegola = value.getTipologiaRegola();
                           	  		break outerLoop;
                           	  	}
                          	  }                        	                         
                		}
                		
                		var regola = new Regola(codice, descrizione, proprieta, tipologiaRegola);

                		regoleSelezionate.add(regola);      		
            		}
                	
                	tableRegoleSetSelezionatoModel.setRowCount(0);		
                	
                	String codiceRegoleSet = null;
                	
                	 for (var entry : mapRegoleSet.entrySet()) {
                         if (entry.getValue().containsAll(regoleSelezionate))
                        	 codiceRegoleSet = entry.getKey();                         
                        }
							
                	 tableRegoleSetSelezionabiliModel.addRow(new Object[] {codiceRegoleSet});						
						
				}
                
			}
		});
		
		tablePedineSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tablePedinaSelezionata.getRowCount() == 0) {
	            	int row = tablePedineSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codicePedina= (String) tablePedineSelezionabiliModel.getValueAt(row, 0);
						String descrizionePedina = (String) tablePedineSelezionabiliModel.getValueAt(row, 1);
						tablePedineSelezionabiliModel.removeRow(row);
						tablePedinaSelezionataModel.addRow(new Object[] {codicePedina, descrizionePedina});
					}
                }
			}
		});
		
		tablePedinaSelezionata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tablePedinaSelezionata.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codicePedina= (String) tablePedinaSelezionataModel.getValueAt(row, 0);
						String descrizionePedina = (String) tablePedinaSelezionataModel.getValueAt(row, 1);
						tablePedinaSelezionataModel.removeRow(row);
						tablePedineSelezionabiliModel.addRow(new Object[] {codicePedina, descrizionePedina});
					}
                }
			}
		});
		
		tableDadiSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableDadoSelezionato.getRowCount() == 0) {
	            	int row = tableDadiSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceDado= (String) tableDadiSelezionabiliModel.getValueAt(row, 0);
						String descrizioneDado = (String) tableDadiSelezionabiliModel.getValueAt(row, 1);
						tableDadiSelezionabiliModel.removeRow(row);
						tableDadoSelezionatoModel.addRow(new Object[] {codiceDado, descrizioneDado});
					}
                }
			}
		});
		
		tableDadoSelezionato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableDadoSelezionato.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceDado= (String) tableDadoSelezionatoModel.getValueAt(row, 0);
						String descrizioneDado = (String) tableDadoSelezionatoModel.getValueAt(row, 1);
						tableDadoSelezionatoModel.removeRow(row);
						tableDadiSelezionabiliModel.addRow(new Object[] {codiceDado, descrizioneDado});
					}
                }
			}
		});
		
	}
}
