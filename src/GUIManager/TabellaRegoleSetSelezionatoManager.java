package GUIManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUIComponents.ButtonCustom;

public class TabellaRegoleSetSelezionatoManager {

	private JScrollPane scrollPaneRegoleSetSelezionato;
	private JTable tableRegoleSetSelezionato;
	private DefaultTableModel tableRegoleSetSelezionatoModel;
	
	private ButtonCustom btnAvanzaToSelezionaScenarioRegSet;
	private ButtonCustom btnReturnToMenuFromSelRegSet;
	
	public TabellaRegoleSetSelezionatoManager(TabellaScenarioSelezionatoManager tableScenarioSelezionatoManager) {
		scrollPaneRegoleSetSelezionato = new JScrollPane();
		scrollPaneRegoleSetSelezionato.setBounds(274, 51, 205, 158);
		tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel().add(scrollPaneRegoleSetSelezionato);
		
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
		
		tableRegoleSetSelezionatoModel = (DefaultTableModel) tableRegoleSetSelezionato.getModel();

		btnAvanzaToSelezionaScenarioRegSet = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);;		
		btnAvanzaToSelezionaScenarioRegSet.setBounds(326, 219, 153, 30);
		tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel().add(btnAvanzaToSelezionaScenarioRegSet);
		
		btnReturnToMenuFromSelRegSet = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelRegSet.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelRegSet.setBounds(0, 0, 57, 25);
		tableScenarioSelezionatoManager.getSelezioneRegoleSetPanel().add(btnReturnToMenuFromSelRegSet);
		
	}

	public JButton getBtnAvanzaToSelezionaScenarioRegSet() {
		return btnAvanzaToSelezionaScenarioRegSet;
	}

	public JButton getBtnReturnToMenuFromSelRegSet() {
		return btnReturnToMenuFromSelRegSet;
	}

	public JScrollPane getScrollPaneRegoleSetSelezionato() {
		return scrollPaneRegoleSetSelezionato;
	}

	public JTable getTableRegoleSetSelezionato() {
		return tableRegoleSetSelezionato;
	}

	public DefaultTableModel getTableRegoleSetSelezionatoModel() {
		return tableRegoleSetSelezionatoModel;
	}
	
}
