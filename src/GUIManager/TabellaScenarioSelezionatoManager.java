package GUIManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabellaScenarioSelezionatoManager {
	
	private JScrollPane scrollPaneScenarioSelezionato;
	private JTable tableScenarioSelezionato;
	private JButton btnReturnToSelRegole;
	private JButton btnReturnToMenuFromSelScen;
	private JButton btnAvanzaToSelezionePers;
	private DefaultTableModel tableScenarioSelezionatoModel;
	private JPanel selezioneRegoleSetPanel;
	private JLabel lblTitleSelezioneRegoleSet;
	private JScrollPane scrollPaneRegoleSetSelezionabili;

	public TabellaScenarioSelezionatoManager(MenuPanelManager menuPaneManager, TabellaScenariSelezionabiliManager tableScenariSelezionabiliManager) {
		scrollPaneScenarioSelezionato = new JScrollPane();
		scrollPaneScenarioSelezionato.setBounds(274, 52, 205, 158);
		tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(scrollPaneScenarioSelezionato);
		
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
		btnReturnToSelRegole = new JButton("Selezione regole");		
		btnReturnToSelRegole.setBounds(30, 227, 153, 30);
		tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(btnReturnToSelRegole);
		
		btnReturnToMenuFromSelScen = new JButton("Menu");
		btnReturnToMenuFromSelScen.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelScen.setBounds(0, 0, 57, 25);
		tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(btnReturnToMenuFromSelScen);
		
		btnAvanzaToSelezionePers = new JButton("Selezione personalizzazioni");
		btnAvanzaToSelezionePers.setBounds(284, 227, 195, 30);
		tableScenariSelezionabiliManager.getSelezioneScenarioPanel().add(btnAvanzaToSelezionePers);
		
		tableScenarioSelezionatoModel = (DefaultTableModel) tableScenarioSelezionato.getModel();
		
		selezioneRegoleSetPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneRegoleSetPanel, "name_790010692461100");
		selezioneRegoleSetPanel.setLayout(null);
		
		lblTitleSelezioneRegoleSet = new JLabel("Selezione set di regole");
		lblTitleSelezioneRegoleSet.setBounds(137, 11, 235, 30);
		lblTitleSelezioneRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSetPanel.add(lblTitleSelezioneRegoleSet);
		
		scrollPaneRegoleSetSelezionabili = new JScrollPane();
		scrollPaneRegoleSetSelezionabili.setBounds(30, 51, 205, 158);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionabili);

	}

	public JScrollPane getScrollPaneScenarioSelezionato() {
		return scrollPaneScenarioSelezionato;
	}

	public JTable getTableScenarioSelezionato() {
		return tableScenarioSelezionato;
	}

	public JButton getBtnReturnToSelRegole() {
		return btnReturnToSelRegole;
	}

	public JButton getBtnReturnToMenuFromSelScen() {
		return btnReturnToMenuFromSelScen;
	}

	public JButton getBtnAvanzaToSelezionePers() {
		return btnAvanzaToSelezionePers;
	}

	public DefaultTableModel getTableScenarioSelezionatoModel() {
		return tableScenarioSelezionatoModel;
	}

	public JPanel getSelezioneRegoleSetPanel() {
		return selezioneRegoleSetPanel;
	}

	public JLabel getLblTitleSelezioneRegoleSet() {
		return lblTitleSelezioneRegoleSet;
	}

	public JScrollPane getScrollPaneRegoleSetSelezionabili() {
		return scrollPaneRegoleSetSelezionabili;
	}
}
