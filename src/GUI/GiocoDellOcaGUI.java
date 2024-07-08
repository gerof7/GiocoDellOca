package GUI;
import GUIManager.MenuPanelManager;
import GUIManager.TabellaDadiSelezionabiliManager;
import GUIManager.TabellaDadoSelezionatoManager;
import GUIManager.TabellaPedinaSelezionataManager;
import GUIManager.TabellePedineSelezionabiliManager;
import GUIManager.TabelleRegoleSelezionabiliManager;
import GUIManager.TabelleRegoleSelezionateManager;
import GUIManager.TabelleRegoleSetSelezionabiliManager;
import GUIManager.TabelleRegoleSetSelezionatoManager;
import GUIManager.TabelleScenariSelezionabiliManager;
import GUIManager.TabelleScenarioSelezionatoManager;
import GUIManager.TipologiaPersonalizzazioniManager;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
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
		
		//TipologiaPersonalizzazioni
		var tipologiaPersonalizzazioniManager = new TipologiaPersonalizzazioniManager(menuPaneManager);
		
		//TablePedineSelezionabili
		var tablePedineSelezionabiliManager = new TabellePedineSelezionabiliManager(menuPaneManager);	
		tablePedineSelezionabili = tablePedineSelezionabiliManager.getTablePedineSelezionabili();
		hideColumn(tablePedineSelezionabili, 0);
		
		//TablePedinaSelezionata
		var tablePedineSelezionataManager = new TabellaPedinaSelezionataManager(tablePedineSelezionabiliManager);
		tablePedinaSelezionata = tablePedineSelezionataManager.getTablePedinaSelezionata();
		hideColumn(tablePedinaSelezionata, 0);

		//TableDadiSelezionabili
		var tableDadiSelezionabiliManager = new TabellaDadiSelezionabiliManager(menuPaneManager);
		tableDadiSelezionabili = tableDadiSelezionabiliManager.getTableDadiSelezionabili();
		hideColumn(tableDadiSelezionabili, 0);

		//TableDadoSelezionato
		var tableDadoSelezionatoManager = new TabellaDadoSelezionatoManager(tableDadiSelezionabiliManager);
		tableDadoSelezionato = tableDadoSelezionatoManager.getTableDadoSelezionato();
		hideColumn(tableDadoSelezionato, 0);
		
		//Action listeners
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
				
				if(tablePedineSelezionabiliManager.getTablePedineSelezionabili().getRowCount() == 0 && tablePedineSelezionataManager.getTablePedinaSelezionata().getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione.getTipologiaPersonalizzazione() == TipologiaPersonalizzazioneEnum.Pedina)
							tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
					}
				}
				
				if(tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().getRowCount() == 0 && tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione.getTipologiaPersonalizzazione() == TipologiaPersonalizzazioneEnum.Dado)
							tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
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
		
		tableRegoleSelezionateManager.getBtnAvanzaToSelezionaScenarioRegSing().addActionListener(new ActionListener() {
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
		
		tableRegoleSetSelezionatoManager.getBtnAvanzaToSelezionaScenarioRegSet().addActionListener(new ActionListener() {
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
		

		tipologiaPersonalizzazioniManager.getBtnReturnToSelScenFromSelPers().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tableScenariSelezionabiliManager.getSelezioneScenarioPanel());
			}
		});
		

		tipologiaPersonalizzazioniManager.getBtnTipologiaPersPedina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tablePedineSelezionabiliManager.getSelezionePedinaPanel());
			}
		});
		

		tipologiaPersonalizzazioniManager.getBtnTipologiaPersDado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tableDadiSelezionabiliManager.getSelezioneDadiPanel());
			}
		});
		

		tablePedineSelezionataManager.getBtnSelezionePersonalizzazioniFromSelPed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tipologiaPersonalizzazioniManager.getSelezioneTipologiaPersonalizzazionePanel());
			}
		});
		

		tableDadoSelezionatoManager.getBtnSelezionePersonalizzazioniFromSelDadi().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), tipologiaPersonalizzazioniManager.getSelezioneTipologiaPersonalizzazionePanel());
			}
		});
		

		tablePedineSelezionataManager.getBtnAvviaPartitaFromSelPedina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePedineSelezionataManager.getTablePedinaSelezionataModel().getRowCount() > 0) {
					var codicePedina = (String) tablePedineSelezionataManager.getTablePedinaSelezionataModel().getValueAt(0, 0);
	                var descrizionePedina= (String) tablePedineSelezionataManager.getTablePedinaSelezionataModel().getValueAt(0, 1);
	                
	                var pedina = new Personalizzazione(codicePedina, descrizionePedina, TipologiaPersonalizzazioneEnum.Pedina);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(pedina);      
				}
				
				if(tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getRowCount() > 0) {
					var codiceDado = (String) tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getValueAt(0, 0);
	                var descrizioneDado= (String) tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getValueAt(0, 1);
	                
	                var dado = new Personalizzazione(codiceDado, descrizioneDado, TipologiaPersonalizzazioneEnum.Dado);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(dado);      
				}
                			
				GiocoDellOcaGUI.this.giocoDellOca.avviaPartita();
			}
		});
		

		tableDadoSelezionatoManager.getBtnAvviaPartitaFromSelDado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tablePedineSelezionataManager.getTablePedinaSelezionataModel().getRowCount() > 0) {
					var codicePedina = (String) tablePedineSelezionataManager.getTablePedinaSelezionataModel().getValueAt(0, 0);
	                var descrizionePedina= (String) tablePedineSelezionataManager.getTablePedinaSelezionataModel().getValueAt(0, 1);
	                
	                var pedina = new Personalizzazione(codicePedina, descrizionePedina, TipologiaPersonalizzazioneEnum.Pedina);
	                
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addPersonalizzazioneToList(pedina);      
				}
				
				if(tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getRowCount() > 0) {
					var codiceDado = (String) tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getValueAt(0, 0);
	                var descrizioneDado= (String) tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getValueAt(0, 1);
	                
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
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		tableRegoleSelezionateManager.getBtnReturnToMenuFromSelRegSing().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		tableRegoleSetSelezionatoManager.getBtnReturnToMenuFromSelRegSet().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
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
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		tipologiaPersonalizzazioniManager.getBtnReturnToMenuFromSelPers().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		

		tablePedineSelezionabiliManager.getBtnReturnToMenuFromSelPedina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		

		tableDadiSelezionabiliManager.getBtnReturnToMenuFromSelDado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliManager.getTableRegoleSetSelezionabiliModel().setRowCount(0);
				tableRegoleSetSelezionatoManager.getTableRegoleSetSelezionatoModel().setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateManager.getTableRegoleSelezionateModel().setRowCount(0);	
            	tableScenariSelezionabiliManager.getTableScenariSelezionabiliModel().setRowCount(0);
            	tableScenarioSelezionatoManager.getTableScenarioSelezionatoModel().setRowCount(0);
            	tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().setRowCount(0);
            	tablePedineSelezionataManager.getTablePedinaSelezionataModel().setRowCount(0);
            	tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().setRowCount(0);
            	tableDadoSelezionatoManager.getTableDadoSelezionatoModel().setRowCount(0);
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
						String codicePedina= (String) tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().getValueAt(row, 0);
						String descrizionePedina = (String) tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().getValueAt(row, 1);
						tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().removeRow(row);
						tablePedineSelezionataManager.getTablePedinaSelezionataModel().addRow(new Object[] {codicePedina, descrizionePedina});
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
						String codicePedina= (String) tablePedineSelezionataManager.getTablePedinaSelezionataModel().getValueAt(row, 0);
						String descrizionePedina = (String) tablePedineSelezionataManager.getTablePedinaSelezionataModel().getValueAt(row, 1);
						tablePedineSelezionataManager.getTablePedinaSelezionataModel().removeRow(row);
						tablePedineSelezionabiliManager.getTablePedineSelezionabiliModel().addRow(new Object[] {codicePedina, descrizionePedina});
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
						String codiceDado= (String) tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().getValueAt(row, 0);
						String descrizioneDado = (String) tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().getValueAt(row, 1);
						tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().removeRow(row);
						tableDadoSelezionatoManager.getTableDadoSelezionatoModel().addRow(new Object[] {codiceDado, descrizioneDado});
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
						String codiceDado= (String) tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getValueAt(row, 0);
						String descrizioneDado = (String) tableDadoSelezionatoManager.getTableDadoSelezionatoModel().getValueAt(row, 1);
						tableDadoSelezionatoManager.getTableDadoSelezionatoModel().removeRow(row);
						tableDadiSelezionabiliManager.getTableDadiSelezionabiliModel().addRow(new Object[] {codiceDado, descrizioneDado});
					}
                }
			}
		});
		
	}
}
