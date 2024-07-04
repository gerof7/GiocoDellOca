package GUI;
import GUIManager.MenuPanelManager;
import GUIManager.TabelleRegoleSelezionabiliManager;

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
		
		//Inizio semplificazione col manager
		
		MenuPanelManager menuPaneManager = new MenuPanelManager(contentPane);
		
		/*JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_609521392636900");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel menuPrincipalePanel = new JPanel();
		layeredPane.add(menuPrincipalePanel, "name_610369483648900");
		menuPrincipalePanel.setLayout(null);
		
		JLabel lblTitleMenuPrincipale = new JLabel("Gioco dell'Oca");
		lblTitleMenuPrincipale.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleMenuPrincipale.setBounds(179, 11, 150, 36);
		menuPrincipalePanel.add(lblTitleMenuPrincipale);
		
		JButton btnConfiguraNuovaPartitaSP = new JButton("Nuova partita singleplayer");
		
		btnConfiguraNuovaPartitaSP.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnConfiguraNuovaPartitaSP.setBounds(163, 72, 182, 45);
		menuPrincipalePanel.add(btnConfiguraNuovaPartitaSP);
		
		JPanel selezioneTipologiaRegolePanel = new JPanel();
		layeredPane.add(selezioneTipologiaRegolePanel, "name_610398291392000");
		selezioneTipologiaRegolePanel.setLayout(null);
		
		JLabel lblTitleSelezioneTipologiaRegole = new JLabel("Selezione tipologia regole");
		lblTitleSelezioneTipologiaRegole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneTipologiaRegole.setBounds(118, 11, 273, 36);
		selezioneTipologiaRegolePanel.add(lblTitleSelezioneTipologiaRegole);
		
		JButton btnTipologiaRegoleSet = new JButton("Set di regole predefinite");	
		btnTipologiaRegoleSet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaRegoleSet.setBounds(265, 108, 167, 42);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSet);
		
		JButton btnTipologiaRegoleSingole = new JButton("Regole singole");
		btnTipologiaRegoleSingole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaRegoleSingole.setBounds(83, 108, 167, 42);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSingole);
		
		JButton btnReturnToMenuFromSelTipReg = new JButton("Menu");		
		btnReturnToMenuFromSelTipReg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelTipReg.setBounds(0, 0, 57, 25);
		selezioneTipologiaRegolePanel.add(btnReturnToMenuFromSelTipReg);
		
		JPanel selezioneRegoleSingolePanel = new JPanel();
		layeredPane.add(selezioneRegoleSingolePanel, "name_166112297214100");
		selezioneRegoleSingolePanel.setLayout(null);
		
		JLabel lblTitleSelezioneRegoleSingole = new JLabel("Selezione regole singole");
		lblTitleSelezioneRegoleSingole.setBounds(127, 11, 254, 30);
		lblTitleSelezioneRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSingolePanel.add(lblTitleSelezioneRegoleSingole);
		
		JScrollPane scrollPaneRegoleSelezionabili = new JScrollPane();
		scrollPaneRegoleSelezionabili.setBounds(30, 51, 205, 158);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionabili);
		
        ToolTipManager.sharedInstance().setInitialDelay(200);*/
        
		//TableRegoleSelezionabili
		var tableRegoleSelezionabiliManager = new TabelleRegoleSelezionabiliManager();
		tableRegoleSelezionabili = tableRegoleSelezionabiliManager.createTabelleRegoleSelezionabili(menuPaneManager);
		/*tableRegoleSelezionabili = new JTable() {
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
		menuPaneManager.getScrollPaneRegoleSelezionabili().setViewportView(tableRegoleSelezionabili);
		
		DefaultTableModel tableRegoleSelezionabiliModel = (DefaultTableModel) tableRegoleSelezionabili.getModel();*/
		
		hideColumn(tableRegoleSelezionabili, 0);
		
		//TableRegoleSelezionate
		JScrollPane scrollPaneRegoleSelezionate = new JScrollPane();
		scrollPaneRegoleSelezionate.setBounds(274, 51, 205, 158);
		menuPaneManager.getSelezioneRegoleSingolePanel().add(scrollPaneRegoleSelezionate);
		
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
		
		DefaultTableModel tableRegoleSelezionateModel = (DefaultTableModel) tableRegoleSelezionate.getModel();
		hideColumn(tableRegoleSelezionate, 0);
		
		//Bottoni
		JButton btnAvanzaToSelezionaScenarioRegSing = new JButton("Selezione scenario ");
		btnAvanzaToSelezionaScenarioRegSing.setBounds(326, 227, 153, 30);
		menuPaneManager.getSelezioneRegoleSingolePanel().add(btnAvanzaToSelezionaScenarioRegSing);
		
		JButton btnReturnToMenuFromSelRegSing = new JButton("Menu");
		btnReturnToMenuFromSelRegSing.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelRegSing.setBounds(0, 0, 57, 25);
		menuPaneManager.getSelezioneRegoleSingolePanel().add(btnReturnToMenuFromSelRegSing);
		
		JPanel selezioneScenarioPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneScenarioPanel, "name_769942002122600");
		selezioneScenarioPanel.setLayout(null);
		
		JLabel lblTitleSelezioneScenario = new JLabel("Selezione scenario");
		lblTitleSelezioneScenario.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneScenario.setBounds(158, 11, 192, 30);
		selezioneScenarioPanel.add(lblTitleSelezioneScenario);
		
		JScrollPane scrollPaneScenariSelezionabili = new JScrollPane();
		scrollPaneScenariSelezionabili.setBounds(30, 51, 205, 158);
		selezioneScenarioPanel.add(scrollPaneScenariSelezionabili);
		
		//TableScenariSelezionabili
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
		tableScenariSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Scenario selezionabile", "Descrizione"
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
		
		DefaultTableModel tableScenariSelezionabiliModel = (DefaultTableModel) tableScenariSelezionabili.getModel();
		
		//TableScenarioSelezionato
		JScrollPane scrollPaneScenarioSelezionato = new JScrollPane();
		scrollPaneScenarioSelezionato.setBounds(274, 52, 205, 158);
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
		tableScenarioSelezionato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Scenario selezionato", "Descrizione"
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
		JButton btnReturnToSelRegole = new JButton("Selezione regole");		
		btnReturnToSelRegole.setBounds(30, 227, 153, 30);
		selezioneScenarioPanel.add(btnReturnToSelRegole);
		
		JButton btnReturnToMenuFromSelScen = new JButton("Menu");
		btnReturnToMenuFromSelScen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelScen.setBounds(0, 0, 57, 25);
		selezioneScenarioPanel.add(btnReturnToMenuFromSelScen);
		
		JButton btnAvanzaToSelezionePers = new JButton("Selezione personalizzazioni");
		btnAvanzaToSelezionePers.setBounds(284, 227, 195, 30);
		selezioneScenarioPanel.add(btnAvanzaToSelezionePers);
		
		DefaultTableModel tableScenarioSelezionatoModel = (DefaultTableModel) tableScenarioSelezionato.getModel();
		
		JPanel selezioneRegoleSetPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneRegoleSetPanel, "name_790010692461100");
		selezioneRegoleSetPanel.setLayout(null);
		
		JLabel lblTitleSelezioneRegoleSet = new JLabel("Selezione set di regole");
		lblTitleSelezioneRegoleSet.setBounds(137, 11, 235, 30);
		lblTitleSelezioneRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSetPanel.add(lblTitleSelezioneRegoleSet);
		
		JScrollPane scrollPaneRegoleSetSelezionabili = new JScrollPane();
		scrollPaneRegoleSetSelezionabili.setBounds(30, 51, 205, 158);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionabili);
		
		//TableRegoleSetSelezionabili
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
		scrollPaneRegoleSetSelezionabili.setViewportView(tableRegoleSetSelezionabili);
		
		DefaultTableModel tableRegoleSetSelezionabiliModel = (DefaultTableModel) tableRegoleSetSelezionabili.getModel();
		
		//TableRegoleSetSelezionato
		JScrollPane scrollPaneRegoleSetSelezionato = new JScrollPane();
		scrollPaneRegoleSetSelezionato.setBounds(274, 51, 205, 158);
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
		
		DefaultTableModel tableRegoleSetSelezionatoModel = (DefaultTableModel) tableRegoleSetSelezionato.getModel();
		hideColumn(tableRegoleSetSelezionato, 0);
		
		//Bottoni
		JButton btnAvanzaToSelezionaScenarioRegSet = new JButton("Selezione scenario");		
		btnAvanzaToSelezionaScenarioRegSet.setBounds(326, 219, 153, 30);
		selezioneRegoleSetPanel.add(btnAvanzaToSelezionaScenarioRegSet);
		
		JButton btnReturnToMenuFromSelRegSet = new JButton("Menu");
		btnReturnToMenuFromSelRegSet.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelRegSet.setBounds(0, 0, 57, 25);
		selezioneRegoleSetPanel.add(btnReturnToMenuFromSelRegSet);
		
		JPanel selezioneTipologiaPersonalizzazionePanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneTipologiaPersonalizzazionePanel, "name_1048915761636599");
		selezioneTipologiaPersonalizzazionePanel.setLayout(null);
		
		JLabel lblTitleSelezioneTipologiaPersonalizzazione = new JLabel("Selezione personalizzazioni");
		lblTitleSelezioneTipologiaPersonalizzazione.setBounds(112, 11, 285, 36);
		lblTitleSelezioneTipologiaPersonalizzazione.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneTipologiaPersonalizzazionePanel.add(lblTitleSelezioneTipologiaPersonalizzazione);
		
		JButton btnReturnToMenuFromSelPers = new JButton("Menu");
		btnReturnToMenuFromSelPers.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelPers.setBounds(0, 0, 57, 25);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToMenuFromSelPers);
		
		JButton btnTipologiaPersDado = new JButton("Dadi");
		btnTipologiaPersDado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaPersDado.setBounds(265, 108, 167, 42);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersDado);
		
		JButton btnTipologiaPersPedina = new JButton("Pedina");
		btnTipologiaPersPedina.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaPersPedina.setBounds(83, 108, 167, 42);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersPedina);
		
		JButton btnReturnToSelScenFromSelPers = new JButton("Selezione scenario");
		btnReturnToSelScenFromSelPers.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnReturnToSelScenFromSelPers.setBounds(10, 242, 167, 42);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToSelScenFromSelPers);
		
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
		
		//TablePedineSelezionabili
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
				
				if(tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().getRowCount() == 0 && tableRegoleSelezionateModel.getRowCount() == 0) 
				{
					for(var regola : listaRegoleSingole) {
						tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione()});
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
				
				for (int row = 0; row < tableRegoleSelezionateModel.getRowCount(); row++) {
		                var codiceRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 0);
		                var descrizioneRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 1);
		                var proprietaRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 2);
		                
		                var regola = new Regola(codiceRegola, descrizioneRegola, proprietaRegola);
		                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addRegolaToList(regola);		                
				}
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneScenarioPanel);
			}
		});
		
		btnAvanzaToSelezionaScenarioRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole().clear();
				
				for (int row = 0; row < tableRegoleSetSelezionatoModel.getRowCount(); row++) {
		                var codiceRegola = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 0);
		                var descrizioneRegola = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 1);
		                var proprietaRegola = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 2);
		                
		                var regola = new Regola(codiceRegola, descrizioneRegola, proprietaRegola);
		                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().addRegolaToList(regola);		                
				}
				
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneScenarioPanel);
			}
		});
		
		menuPaneManager.getBtnTipologiaRegoleSet().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneRegoleSetPanel);
			}
		});
		
		btnReturnToSelRegole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getSelezioneTipologiaRegolePanel());
			}
		});
		
		btnAvanzaToSelezionePers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(tableScenarioSelezionatoModel.getRowCount() > 0)
				{
	                var codiceScenario = (String) tableScenarioSelezionatoModel.getValueAt(0, 0);
	                var descrizioneScenario = (String) tableScenarioSelezionatoModel.getValueAt(0, 1);
	                
	                var scenario = new Scenario(codiceScenario, descrizioneScenario);
	                GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getImpostazioni().setScenario(scenario);               				
					
					SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneTipologiaPersonalizzazionePanel);
				}
			}
		});
		

		btnReturnToSelScenFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneScenarioPanel);
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
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneTipologiaPersonalizzazionePanel);
			}
		});
		

		btnSelezionePersonalizzazioniFromSelDadi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(menuPaneManager.getLayeredPane(), selezioneTipologiaPersonalizzazionePanel);
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
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelScen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		
		btnReturnToMenuFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		

		btnReturnToMenuFromSelPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
            	tablePedineSelezionabiliModel.setRowCount(0);
            	tablePedinaSelezionataModel.setRowCount(0);
            	tableDadiSelezionabiliModel.setRowCount(0);
            	tableDadoSelezionatoModel.setRowCount(0);
				SwitchToPanel(menuPaneManager.getLayeredPane(), menuPaneManager.getMenuPrincipalePanel());
			}
		});
		

		btnReturnToMenuFromSelDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliManager.getTableRegoleSelezionabiliModel().setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
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
						tableRegoleSelezionateModel.addRow(new Object[] {codiceRegola, descrizioneRegola, ""});
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
                		
                		var regola = new Regola(codice, descrizione, proprieta);

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
