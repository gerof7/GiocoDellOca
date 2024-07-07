package GUIManager;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelleScenariSelezionabiliManager {

	private JPanel selezioneScenarioPanel;
	private JLabel lblTitleSelezioneScenario;
	private JScrollPane scrollPaneScenariSelezionabili;
	private JTable tableScenariSelezionabili;
	private DefaultTableModel tableScenariSelezionabiliModel;
	
	
	public TabelleScenariSelezionabiliManager(MenuPanelManager menuPaneManager) {
		selezioneScenarioPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneScenarioPanel, "name_769942002122600");
		selezioneScenarioPanel.setLayout(null);
		
		lblTitleSelezioneScenario = new JLabel("Selezione scenario");
		lblTitleSelezioneScenario.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneScenario.setBounds(158, 11, 192, 30);
		selezioneScenarioPanel.add(lblTitleSelezioneScenario);
		
		scrollPaneScenariSelezionabili = new JScrollPane();
		scrollPaneScenariSelezionabili.setBounds(30, 51, 205, 158);
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
		
		tableScenariSelezionabiliModel = (DefaultTableModel) tableScenariSelezionabili.getModel();

	}


	public JPanel getSelezioneScenarioPanel() {
		return selezioneScenarioPanel;
	}


	public JLabel getLblTitleSelezioneScenario() {
		return lblTitleSelezioneScenario;
	}


	public JScrollPane getScrollPaneScenariSelezionabili() {
		return scrollPaneScenariSelezionabili;
	}


	public JTable getTableScenariSelezionabili() {
		return tableScenariSelezionabili;
	}


	public DefaultTableModel getTableScenariSelezionabiliModel() {
		return tableScenariSelezionabiliModel;
	}
}
