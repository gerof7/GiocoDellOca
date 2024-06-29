package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Regola;
import GiocoDellOca.Scenario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Color;

public class GiocoDellOcaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRegoleSelezionabili;
	private GiocoDellOca giocoDellOca;
	private List<Regola> listaRegoleSingole;
	private Map<String, Set<Regola>> mapRegoleSet;
	private List<Scenario> listScenari;
	private JTable tableRegoleSelezionate;
	private JTable tableScenariSelezionabili;
	private JTable tableScenarioSelezionato;
	private JTable tableRegoleSetSelezionabili;
	private JTable tableRegoleSetSelezionato;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_609521392636900");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel menuPrincipalePanel = new JPanel();
		layeredPane.add(menuPrincipalePanel, "name_610369483648900");
		menuPrincipalePanel.setLayout(null);
		
		JLabel lblTitleMenuPrincipale = new JLabel("Gioco dell'Oca");
		lblTitleMenuPrincipale.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleMenuPrincipale.setBounds(137, 11, 149, 36);
		menuPrincipalePanel.add(lblTitleMenuPrincipale);
		
		JButton btnConfiguraNuovaPartitaSP = new JButton("Nuova partita singleplayer");
		
		btnConfiguraNuovaPartitaSP.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnConfiguraNuovaPartitaSP.setBounds(121, 72, 182, 45);
		menuPrincipalePanel.add(btnConfiguraNuovaPartitaSP);
		
		JPanel selezioneTipologiaRegolePanel = new JPanel();
		layeredPane.add(selezioneTipologiaRegolePanel, "name_610398291392000");
		selezioneTipologiaRegolePanel.setLayout(null);
		
		JLabel lblTitleSelezioneTipologiaRegole = new JLabel("Selezione tipologia regole");
		lblTitleSelezioneTipologiaRegole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneTipologiaRegole.setBounds(78, 11, 268, 36);
		selezioneTipologiaRegolePanel.add(lblTitleSelezioneTipologiaRegole);
		
		JButton btnTipologiaRegoleSet = new JButton("Set di regole predefinite");	
		btnTipologiaRegoleSet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaRegoleSet.setBounds(217, 108, 167, 42);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSet);
		
		JButton btnTipologiaRegoleSingole = new JButton("Regole singole");
		btnTipologiaRegoleSingole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaRegoleSingole.setBounds(40, 108, 167, 42);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSingole);
		
		JButton btnReturnToMenuFromSelTipReg = new JButton("Menu");		
		btnReturnToMenuFromSelTipReg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelTipReg.setBounds(0, 0, 57, 25);
		selezioneTipologiaRegolePanel.add(btnReturnToMenuFromSelTipReg);
		
		JPanel selezioneRegoleSingolePanel = new JPanel();
		layeredPane.add(selezioneRegoleSingolePanel, "name_166112297214100");
		selezioneRegoleSingolePanel.setLayout(null);
		
		JLabel lblTitleSelezioneRegoleSingole = new JLabel("Selezione regole singole");
		lblTitleSelezioneRegoleSingole.setBounds(87, 11, 249, 30);
		lblTitleSelezioneRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSingolePanel.add(lblTitleSelezioneRegoleSingole);
		
		JScrollPane scrollPaneRegoleSelezionabili = new JScrollPane();
		scrollPaneRegoleSelezionabili.setBounds(10, 52, 192, 158);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionabili);
		
        ToolTipManager.sharedInstance().setInitialDelay(200);
		
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
		scrollPaneRegoleSelezionabili.setViewportView(tableRegoleSelezionabili);
		
		DefaultTableModel tableRegoleSelezionabiliModel = (DefaultTableModel) tableRegoleSelezionabili.getModel();
		hideColumn(tableRegoleSelezionabili, 0);
		
		JScrollPane scrollPaneRegoleSelezionate = new JScrollPane();
		scrollPaneRegoleSelezionate.setBounds(222, 52, 192, 158);
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
		
		JButton btnAvanzaToSelezionaScenarioRegSing = new JButton("Selezione scenario ");
		btnAvanzaToSelezionaScenarioRegSing.setBounds(261, 221, 153, 30);
		selezioneRegoleSingolePanel.add(btnAvanzaToSelezionaScenarioRegSing);
		
		JButton btnReturnToMenuFromSelRegSing = new JButton("Menu");
		btnReturnToMenuFromSelRegSing.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelRegSing.setBounds(0, 0, 57, 25);
		selezioneRegoleSingolePanel.add(btnReturnToMenuFromSelRegSing);
		
		JPanel selezioneScenarioPanel = new JPanel();
		layeredPane.add(selezioneScenarioPanel, "name_769942002122600");
		selezioneScenarioPanel.setLayout(null);
		
		JLabel lblTitleSelezioneScenario = new JLabel("Selezione scenario");
		lblTitleSelezioneScenario.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneScenario.setBounds(117, 11, 189, 30);
		selezioneScenarioPanel.add(lblTitleSelezioneScenario);
		
		JScrollPane scrollPaneScenariSelezionabili = new JScrollPane();
		scrollPaneScenariSelezionabili.setBounds(10, 52, 192, 158);
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
		
		JScrollPane scrollPaneScenarioSelezionato = new JScrollPane();
		scrollPaneScenarioSelezionato.setBounds(222, 52, 192, 158);
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
		
		JButton btnReturnToSelRegole = new JButton("Selezione regole");		
		btnReturnToSelRegole.setBounds(10, 221, 153, 30);
		selezioneScenarioPanel.add(btnReturnToSelRegole);
		
		JButton btnReturnToMenuFromSelScen = new JButton("Menu");
		btnReturnToMenuFromSelScen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelScen.setBounds(0, 0, 57, 25);
		selezioneScenarioPanel.add(btnReturnToMenuFromSelScen);
		
		DefaultTableModel tableScenarioSelezionatoModel = (DefaultTableModel) tableScenarioSelezionato.getModel();
		
		JPanel selezioneRegoleSetPanel = new JPanel();
		layeredPane.add(selezioneRegoleSetPanel, "name_790010692461100");
		selezioneRegoleSetPanel.setLayout(null);
		
		JLabel lblTitleSelezioneRegoleSet = new JLabel("Selezione set di regole");
		lblTitleSelezioneRegoleSet.setBounds(96, 11, 232, 30);
		lblTitleSelezioneRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSetPanel.add(lblTitleSelezioneRegoleSet);
		
		JScrollPane scrollPaneRegoleSetSelezionabili = new JScrollPane();
		scrollPaneRegoleSetSelezionabili.setBounds(10, 52, 192, 158);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionabili);
		
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
		
		JScrollPane scrollPaneRegoleSetSelezionato = new JScrollPane();
		scrollPaneRegoleSetSelezionato.setBounds(222, 52, 192, 158);
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
		
		JButton btnAvanzaToSelezionaScenarioRegSet = new JButton("Selezione scenario");		
		btnAvanzaToSelezionaScenarioRegSet.setBounds(261, 221, 153, 30);
		selezioneRegoleSetPanel.add(btnAvanzaToSelezionaScenarioRegSet);
		
		JButton btnReturnToMenuFromSelRegSet = new JButton("Menu");
		btnReturnToMenuFromSelRegSet.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelRegSet.setBounds(0, 0, 57, 25);
		selezioneRegoleSetPanel.add(btnReturnToMenuFromSelRegSet);
		
		btnConfiguraNuovaPartitaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GiocoDellOcaGUI.this.giocoDellOca.configuraNuovaPartitaSP();
				
				GiocoDellOcaGUI.this.listaRegoleSingole = giocoDellOca.getListaRegoleSingole();
				GiocoDellOcaGUI.this.mapRegoleSet = giocoDellOca.getMapRegoleSet();
				GiocoDellOcaGUI.this.listScenari = giocoDellOca.getListaScenari();
				
				if(tableRegoleSelezionabiliModel.getRowCount() == 0 && tableRegoleSelezionateModel.getRowCount() == 0) 
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
					
				
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});
		
		btnTipologiaRegoleSingole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneRegoleSingolePanel);
			}
		});
		
		btnAvanzaToSelezionaScenarioRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneScenarioPanel);
			}
		});
		
		btnAvanzaToSelezionaScenarioRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		btnReturnToMenuFromSelTipReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableRegoleSetSelezionabiliModel.setRowCount(0);
            	tableRegoleSetSelezionatoModel.setRowCount(0);	
            	tableRegoleSelezionabiliModel.setRowCount(0);
            	tableRegoleSelezionateModel.setRowCount(0);	
				tableScenariSelezionabiliModel.setRowCount(0);
            	tableScenarioSelezionatoModel.setRowCount(0);
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
		
	}
}
