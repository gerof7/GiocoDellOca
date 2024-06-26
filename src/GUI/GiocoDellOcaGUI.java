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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GiocoDellOcaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRegoleSelezionabili;
	private GiocoDellOca giocoDellOca;
	private JTable tableRegoleSelezionate;
	
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
		giocoDellOca.configuraNuovaPartitaSP();
		
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
		
		JPanel selezioneRegoleSingolePanel = new JPanel();
		layeredPane.add(selezioneRegoleSingolePanel, "name_166112297214100");
		selezioneRegoleSingolePanel.setLayout(null);
		
		JLabel lblTitleSelezioneRegoleSingole = new JLabel("Selezione regole singole");
		lblTitleSelezioneRegoleSingole.setBounds(87, 11, 249, 30);
		lblTitleSelezioneRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSingolePanel.add(lblTitleSelezioneRegoleSingole);
		
		JScrollPane scrollPaneRegoleSelezionabili = new JScrollPane();
		scrollPaneRegoleSelezionabili.setBounds(0, 52, 192, 158);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionabili);
		
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
		
        ToolTipManager.sharedInstance().setInitialDelay(200);
		
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

		Map<String, Regola> regoleMap = giocoDellOca.getRegole();
		
		for(String key : regoleMap.keySet()) {
			Regola regola = regoleMap.get(key);
			tableRegoleSelezionabiliModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione()});
		}
			
		btnConfiguraNuovaPartitaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});
		
		btnTipologiaRegoleSingole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneRegoleSingolePanel);
			}
		});
		
		tableRegoleSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableRegoleSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceRegola = (String) tableRegoleSelezionabili.getModel().getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionabili.getModel().getValueAt(row, 1);
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
						String codiceRegola = (String) tableRegoleSelezionate.getModel().getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionate.getModel().getValueAt(row, 1);
						tableRegoleSelezionateModel.removeRow(row);
						tableRegoleSelezionabiliModel.addRow(new Object[] {codiceRegola, descrizioneRegola});
					}
                }
			}
		});
	}
		
}
