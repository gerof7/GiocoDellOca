package GUI;
import GUIManager.MenuPanelManager;
import GUIManager.TabelleRegoleSelezionabiliManager;
import GUIManager.TabelleRegoleSelezionateManager;
import GUIManager.TabelleRegoleSetSelezionabiliManager;
import GUIManager.TabelleRegoleSetSelezionatoManager;
import GUIManager.TabelleScenariSelezionabiliManager;
import GUIManager.TabelleScenarioSelezionatoManager;
import GUIManager.TipologiaPersonalizzazioniManager;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Personalizzazione;
import GiocoDellOca.Regola;
import GiocoDellOca.Scenario;
import GiocoDellOca.TipologiaPersonalizzazioneEnum;

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
		this.giocoDellOca = new GiocoDellOca();
		//Creazione panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 341);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//Inizio semplificazione con i manager
		
		MenuPanelManager menuPaneManager = new MenuPanelManager(contentPane);
        
		//TableRegoleSelezionabili
		var tableRegoleSelezionabiliManager = new TabelleRegoleSelezionabiliManager(menuPaneManager);
		tableRegoleSelezionabili = tableRegoleSelezionabiliManager.getTableRegoleSelezionabili();		
		hideColumn(tableRegoleSelezionabili, 0);
		
		//TableRegoleSelezionate
		var tableRegoleSelezionateManager = new TabelleRegoleSelezionateManager(menuPaneManager);
		tableRegoleSelezionate = tableRegoleSelezionateManager.getTableRegoleSelezionate();
		hideColumn(tableRegoleSelezionate, 0);
		
		//Bottoni
		JButton btnAvanzaToSelezionaScenarioRegSing = tableRegoleSelezionateManager.getBtnAvanzaToSelezionaScenarioRegSing();
		JButton btnReturnToMenuFromSelRegSing = tableRegoleSelezionateManager.getBtnReturnToMenuFromSelRegSing();
		
		//TableScenariSelezionabili
		var tableScenariSelezionabiliManager = new TabelleScenariSelezionabiliManager(menuPaneManager);
		tableScenariSelezionabili = tableScenariSelezionabiliManager.getTableScenariSelezionabili();
		
		//TableScenarioSelezionato
		var tableScenarioSelezionatoManager = new TabelleScenarioSelezionatoManager(menuPaneManager, tableScenariSelezionabiliManager);
		tableScenarioSelezionato = tableScenarioSelezionatoManager.getTableScenarioSelezionato();
		
		//TableRegoleSetSelezionabili
		var tableRegoleSetSelezionabiliManager = new TabelleRegoleSetSelezionabiliManager(tableScenarioSelezionatoManager);
		tableRegoleSetSelezionabili = tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabili();
		//TableRegoleSetSelezionato
		var tableRegoleSetSelezionatoManager = new TabelleRegoleSetSelezionatoManager(tableScenarioSelezionatoManager);
		tableRegoleSetSelezionato = tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionato();
		hideColumn(tableRegoleSetSelezionato, 0);
		//Bottoni
		JButton btnAvanzaToSelezionaScenarioRegSet = tableRegoleSetSelezionatoManager.getBtnAvanzaToSelezionaScenarioRegSet();
		JButton btnReturnToMenuFromSelRegSet = tableRegoleSetSelezionatoManager.getBtnReturnToMenuFromSelRegSet();
		
		//TipologiaPersonalizzazioni
		var tipologiaPersonalizzazioniManager = new TipologiaPersonalizzazioniManager(menuPaneManager);
		JButton btnReturnToMenuFromSelPers = tipologiaPersonalizzazioniManager.getBtnReturnToMenuFromSelPers();
		JButton btnTipologiaPersDado = tipologiaPersonalizzazioniManager.getBtnTipologiaPersDado();
		JButton btnTipologiaPersPedina = tipologiaPersonalizzazioniManager.getBtnTipologiaPersPedina();
		
		JButton btnReturnToSelScenFromSelPers = tipologiaPersonalizzazioniManager.getBtnReturnToSelScenFromSelPers();
		//TablePedineSelezionabili
		JPanel selezionePedinaPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezionePedinaPanel, "name_1050308981416700");
		selezionePedinaPanel.setLayout(null);
		
		JLabel lblTitleSelezionePedina = new JLabel("Selezione pedina");
		lblTitleSelezionePedina.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezionePedina.setBounds(166, 10, 177, 36);
		selezionePedinaPanel.add(lblTitleSelezionePedina);
		
		JButton btnReturnToMenuFromSelPedina = new JButton("Menu");
		btnReturnToMenuFromSelPedina.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelPedina.setBounds(0, 0, 57, 25);
		selezionePedinaPanel.add(btnReturnToMenuFromSelPedina);
		
		
		JScrollPane scrollPanePedineSelezionabili = new JScrollPane();
		scrollPanePedineSelezionabili.setBounds(30, 56, 205, 158);
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
		
		DefaultTableModel tablePedineSelezionabiliModel = (DefaultTableModel) tablePedineSelezionabili.getModel();
		hideColumn(tablePedineSelezionabili, 0);
		
		//TablePedinaSelezionata
		JScrollPane scrollPanePedinaSelezionata = new JScrollPane();
		scrollPanePedinaSelezionata.setBounds(274, 56, 205, 158);
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
		
		DefaultTableModel tablePedinaSelezionataModel = (DefaultTableModel) tablePedinaSelezionata.getModel();
		hideColumn(tablePedinaSelezionata, 0);
		
		//Bottoni
		JButton btnSelezionePersonalizzazioniFromSelPed = new JButton("Selezione personalizzazioni");
		btnSelezionePersonalizzazioniFromSelPed.setBounds(30, 227, 195, 30);
		selezionePedinaPanel.add(btnSelezionePersonalizzazioniFromSelPed);
		
		JButton btnAvviaPartitaFromSelPedina = new JButton("Avvia partita");
		btnAvviaPartitaFromSelPedina.setBounds(360, 227, 119, 30);
		selezionePedinaPanel.add(btnAvviaPartitaFromSelPedina);
		
		JPanel selezioneDadiPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneDadiPanel, "name_1050468066312900");
		selezioneDadiPanel.setLayout(null);
		
		JLabel lblTitleSelezioneDadi = new JLabel("Selezione dadi");
		lblTitleSelezioneDadi.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneDadi.setBounds(175, 10, 158, 36);
		selezioneDadiPanel.add(lblTitleSelezioneDadi);
		
		JButton btnReturnToMenuFromSelDado = new JButton("Menu");
		btnReturnToMenuFromSelDado.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelDado.setBounds(0, 0, 57, 25);
		selezioneDadiPanel.add(btnReturnToMenuFromSelDado);
		
		//TableDadiSelezionabili
		JScrollPane scrollPaneDadiSelezionabili = new JScrollPane();
		scrollPaneDadiSelezionabili.setBounds(30, 56, 205, 158);
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
		
		DefaultTableModel tableDadiSelezionabiliModel = (DefaultTableModel) tableDadiSelezionabili.getModel();
		hideColumn(tableDadiSelezionabili, 0);
		
		//TableDadoSelezionato
		JScrollPane scrollPaneDadoSelezionato = new JScrollPane();
		scrollPaneDadoSelezionato.setBounds(274, 56, 205, 158);
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
		
		DefaultTableModel tableDadoSelezionatoModel = (DefaultTableModel) tableDadoSelezionato.getModel();
		hideColumn(tableDadoSelezionato, 0);
		
		//Bottoni
		JButton btnSelezionePersonalizzazioniFromSelDadi = new JButton("Selezione personalizzazioni");
		btnSelezionePersonalizzazioniFromSelDadi.setBounds(30, 227, 195, 30);
		selezioneDadiPanel.add(btnSelezionePersonalizzazioniFromSelDadi);
		
		JButton btnAvviaPartitaFromSelDado = new JButton("Avvia partita");
		btnAvviaPartitaFromSelDado.setBounds(360, 227, 119, 30);
		selezioneDadiPanel.add(btnAvviaPartitaFromSelDado);
		
		//Action listener
		menuPaneManager.getBtnConfiguraNuovaPartitaSP().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.configuraNuovaPartitaSP();
				
				GiocoDellOcaGUI.this.listaRegoleSingole = giocoDellOca.getListaRegoleSingole();
				GiocoDellOcaGUI.this.mapRegoleSet = giocoDellOca.getMapRegoleSet();
				GiocoDellOcaGUI.this.listScenari = giocoDellOca.getListaScenari();
				GiocoDellOcaGUI.this.listPersonalizzazioni = giocoDellOca.getListaPersonalizzazioni();
				
				if(tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().getRowCount() == 0 &&  tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getRowCount() == 0) 
				{
					for(var regola : listaRegoleSingole) {
						tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione()});
					}
				}
				
				if(tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().getRowCount() == 0 && tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getRowCount() == 0) 
				{
					for (var key : mapRegoleSet.keySet()) {
						tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().addRow(new Object[] {key});
					}
				}
				
				if(tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().getRowCount() == 0 && tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().getRowCount() == 0) 
				{
					for(var scenario : listScenari) {
						tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().addRow(new Object[] {scenario.getCodiceScenario(), scenario.getDescrizione()});
					}
				}
				
				if(tablePedineSelezionabiliModel.getRowCount() == 0 && tablePedinaSelezionataModel.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione.getTipologiaPersonalizzazione() == TipologiaPersonalizzazioneEnum.Pedina)
							tablePedineSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
					}
				}
				
				if(tableDadiSelezionabiliModel.getRowCount() == 0 && tableDadoSelezionatoModel.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione.getTipologiaPersonalizzazione() == TipologiaPersonalizzazioneEnum.Dado)
							tableDadiSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
					}
				}
									
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getSelezioneTipologiaRegolePanel());
			}
		});
		
		menuPaneManager.getBtnTipologiaRegoleSingole().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getSelezioneRegoleSingolePanel());
			}
		});
		
		btnAvanzaToSelezionaScenarioRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole().clear();
				
				for (int row = 0; row < tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getRowCount(); row++) {
		                var codiceRegola = (String) tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getValueAt(row, 0);
		                var descrizioneRegola = (String) tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getValueAt(row, 1);
		                var proprietaRegola = (String) tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getValueAt(row, 2);
		                
		                var regola = new Regola(codiceRegola, descrizioneRegola, proprietaRegola);
		                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addRegolaToList(regola);		                
				}
				SwitchToPanel(menuPaneManager.getLayeredPane(), tableScenariSelezionabiliManager.getSelezioneScenarioPanel());
			}
		});
		
		btnAvanzaToSelezionaScenarioRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole().clear();
				
				for (int row = 0; row < tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getRowCount(); row++) {
		                var codiceRegola = (String) tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getValueAt(row, 0);
		                var descrizioneRegola = (String) tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getValueAt(row, 1);
		                var proprietaRegola = (String) tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getValueAt(row, 2);
		                
		                var regola = new Regola(codiceRegola, descrizioneRegola, proprietaRegola);
		                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addRegolaToList(regola);		                
				}
				
				SwitchToPanel(menuPaneManager.getLayeredPane(), tableScenariSelezionabiliManager.getSelezioneScenarioPanel());
			}
		});
		
		menuPaneManager.getBtnTipologiaRegoleSet().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel());
			}
		});
		
		tableScenarioSelezionatoManager.getBtnReturnToSelRegole().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getSelezioneTipologiaRegolePanel());
			}
		});
		
		tableScenarioSelezionatoManager.getBtnAvanzaToSelezionePers().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().getRowCount() > 0)
				{
	                var codiceScenario = (String) tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().getValueAt(0, 0);
	                var descrizioneScenario = (String) tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().getValueAt(0, 1);
	                
	                var scenario = new Scenario(codiceScenario, descrizioneScenario);
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().setScenario(scenario);               				
					
					SwitchToPanel(menuPaneManager.getLayeredPane(), tipologiaPersonalizzazioniManager.getSelezioneTipologiaPersonalizzazionePanel());
				}
			}
		});
		

		btnReturnToSelScenFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tableScenariSelezionabiliManager.getSelezioneScenarioPanel());
			}
		});
		

		btnTipologiaPersPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezionePedinaPanel);
			}
		});
		

		btnTipologiaPersDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneDadiPanel);
			}
		});
		

		btnSelezionePersonalizzazioniFromSelPed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tipologiaPersonalizzazioniManager.getSelezioneTipologiaPersonalizzazionePanel());
			}
		});
		

		btnSelezionePersonalizzazioniFromSelDadi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tipologiaPersonalizzazioniManager.getSelezioneTipologiaPersonalizzazionePanel());
			}
		});
		

		btnAvviaPartitaFromSelPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePedinaSelezionataModel.getRowCount() > 0) {
					var codicePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 0);
	                var descrizionePedina= (String) tablePedinaSelezionataModel.getValueAt(0, 1);
	                
	                var pedina = new Personalizzazione(codicePedina, descrizionePedina, TipologiaPersonalizzazioneEnum.Pedina);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(pedina);      
				}
				
				if(tableDadoSelezionatoModel.getRowCount() > 0) {
					var codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
	                var descrizioneDado= (String) tableDadoSelezionatoModel.getValueAt(0, 1);
	                
	                var dado = new Personalizzazione(codiceDado, descrizioneDado, TipologiaPersonalizzazioneEnum.Dado);
	                
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
	                
	                var pedina = new Personalizzazione(codicePedina, descrizionePedina, TipologiaPersonalizzazioneEnum.Pedina);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(pedina);      
				}
				
				if(tableDadoSelezionatoModel.getRowCount() > 0) {
					var codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
	                var descrizioneDado= (String) tableDadoSelezionatoModel.getValueAt(0, 1);
	                
	                var dado = new Personalizzazione(codiceDado, descrizioneDado, TipologiaPersonalizzazioneEnum.Dado);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(dado);      
				} 
                
				GiocoDellOcaGUI.this.giocoDellOca.avviaPartita();
			}
		});
		
		menuPaneManager.getBtnReturnToMenuFromSelTipReg().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		tableScenarioSelezionatoManager.getBtnReturnToMenuFromSelScen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		

		btnReturnToMenuFromSelPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		

		btnReturnToMenuFromSelDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		tableRegoleSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableRegoleSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceRegola = (String) tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().getValueAt(row, 1);
						tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().removeRow(row);
						tableRegoleSelezionateManager.getTableRegoleSelezionateModel().addRow(new Object[] {codiceRegola, descrizioneRegola, ""});
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
						String codiceRegola = (String) tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionateManager.getTableRegoleSelezionateModel().getValueAt(row, 1);
						tableRegoleSelezionateManager.getTableRegoleSelezionateModel().removeRow(row);
						tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().addRow(new Object[] {codiceRegola, descrizioneRegola});
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
						String codiceScenario= (String) tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().getValueAt(row, 0);
						String descrizioneScenario = (String) tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().getValueAt(row, 1);
						tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().removeRow(row);
						tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().addRow(new Object[] {codiceScenario, descrizioneScenario});
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
						String codiceScenario= (String) tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().getValueAt(row, 0);
						String descrizioneScenario = (String) tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().getValueAt(row, 1);
						tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().removeRow(row);
						tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().addRow(new Object[] {codiceScenario, descrizioneScenario});
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
						var codiceRegoleSet = (String) tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().getValueAt(row, 0);
						var listaRegoleSet = mapRegoleSet.get(codiceRegoleSet);
						
						tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().removeRow(row);
						
						for (var regola: listaRegoleSet) {						
							tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione(), regola.getProprietaRegola()});						
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
                		var codice = (String) tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getValueAt(row, 0);
                		var descrizione = (String) tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getValueAt(row, 1);
                		var proprieta = (String) tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().getValueAt(row, 2);
                		
                		var regola = new Regola(codice, descrizione, proprieta);

                		regoleSelezionate.add(regola);      		
            		}
                	
                	tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);		
                	
                	String codiceRegoleSet = null;
                	
                	 for (var entry : mapRegoleSet.entrySet()) {
                         if (entry.getValue().containsAll(regoleSelezionate))
                        	 codiceRegoleSet = entry.getKey();                         
                        }
							
                	 tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().addRow(new Object[] {codiceRegoleSet});						
						
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
