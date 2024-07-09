package GUIManager;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUIComponents.ButtonCustom;

public class TabellaPedinaSelezionataManager {

	private JScrollPane scrollPanePedinaSelezionata;
	private JTable tablePedinaSelezionata;
	private DefaultTableModel tablePedinaSelezionataModel;
	private ButtonCustom btnSelezionePersonalizzazioniFromSelPed;
	private ButtonCustom btnAvviaPartitaFromSelPedina;
	
	
	public TabellaPedinaSelezionataManager(TabellaPedineSelezionabiliManager tablePedineSelezionabiliManager) {
		scrollPanePedinaSelezionata = new JScrollPane();
		scrollPanePedinaSelezionata.setBounds(274, 56, 205, 158);
		tablePedineSelezionabiliManager.getSelezionePedinaPanel().add(scrollPanePedinaSelezionata);
		
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
		
		tablePedinaSelezionataModel = (DefaultTableModel) tablePedinaSelezionata.getModel();

		btnSelezionePersonalizzazioniFromSelPed = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnSelezionePersonalizzazioniFromSelPed.setBounds(30, 227, 195, 30);
		tablePedineSelezionabiliManager.getSelezionePedinaPanel().add(btnSelezionePersonalizzazioniFromSelPed);
		
		btnAvviaPartitaFromSelPedina = new ButtonCustom("Avvia partita", ButtonCustom.ButtonStyle.PRIMARY);
		btnAvviaPartitaFromSelPedina.setBounds(360, 227, 119, 30);
		tablePedineSelezionabiliManager.getSelezionePedinaPanel().add(btnAvviaPartitaFromSelPedina);
		
	}


	public JButton getBtnSelezionePersonalizzazioniFromSelPed() {
		return btnSelezionePersonalizzazioniFromSelPed;
	}


	public JButton getBtnAvviaPartitaFromSelPedina() {
		return btnAvviaPartitaFromSelPedina;
	}


	public JScrollPane getScrollPanePedinaSelezionata() {
		return scrollPanePedinaSelezionata;
	}


	public JTable getTablePedinaSelezionata() {
		return tablePedinaSelezionata;
	}


	public DefaultTableModel getTablePedinaSelezionataModel() {
		return tablePedinaSelezionataModel;
	}
}
