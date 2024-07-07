package GUIManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelleRegoleSelezionateManager {

	private JScrollPane scrollPaneRegoleSelezionate;
	private JTable tableRegoleSelezionate;
	private DefaultTableModel tableRegoleSelezionateModel;
	private JButton btnAvanzaToSelezionaScenarioRegSing;
	private JButton btnReturnToMenuFromSelRegSing;
	
	
	public JButton getBtnAvanzaToSelezionaScenarioRegSing() {
		return btnAvanzaToSelezionaScenarioRegSing;
	}

	public JButton getBtnReturnToMenuFromSelRegSing() {
		return btnReturnToMenuFromSelRegSing;
	}

	public TabelleRegoleSelezionateManager(MenuPanelManager menuPaneManager) {
		scrollPaneRegoleSelezionate = new JScrollPane();
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
		
		tableRegoleSelezionateModel = (DefaultTableModel) tableRegoleSelezionate.getModel();

		//Bottoni
		btnAvanzaToSelezionaScenarioRegSing = new JButton("Selezione scenario ");
		btnAvanzaToSelezionaScenarioRegSing.setBounds(326, 227, 153, 30);
		menuPaneManager.getSelezioneRegoleSingolePanel().add(btnAvanzaToSelezionaScenarioRegSing);
				
		btnReturnToMenuFromSelRegSing = new JButton("Menu");
		btnReturnToMenuFromSelRegSing.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelRegSing.setBounds(0, 0, 57, 25);
		menuPaneManager.getSelezioneRegoleSingolePanel().add(btnReturnToMenuFromSelRegSing);
				
	}

	public JScrollPane getScrollPaneRegoleSelezionate() {
		return scrollPaneRegoleSelezionate;
	}

	public JTable getTableRegoleSelezionate() {
		return tableRegoleSelezionate;
	}

	public DefaultTableModel getTableRegoleSelezionateModel() {
		return tableRegoleSelezionateModel;
	}
	
	
}
