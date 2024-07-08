package GUIManager;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabellaDadoSelezionatoManager {
	
	private JScrollPane scrollPaneDadoSelezionato;
	private JTable tableDadoSelezionato;
	private DefaultTableModel tableDadoSelezionatoModel;
	private JButton btnSelezionePersonalizzazioniFromSelDadi;
	private JButton btnAvviaPartitaFromSelDado;

	public TabellaDadoSelezionatoManager(TabellaDadiSelezionabiliManager tableDadiSelezionabiliManager) {
		scrollPaneDadoSelezionato = new JScrollPane();
		scrollPaneDadoSelezionato.setBounds(274, 56, 205, 158);
		tableDadiSelezionabiliManager.getSelezioneDadiPanel().add(scrollPaneDadoSelezionato);
		
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
		
		tableDadoSelezionatoModel = (DefaultTableModel) tableDadoSelezionato.getModel();

		//Bottoni
		btnSelezionePersonalizzazioniFromSelDadi = new JButton("Selezione personalizzazioni");
		btnSelezionePersonalizzazioniFromSelDadi.setBounds(30, 227, 195, 30);
		tableDadiSelezionabiliManager.getSelezioneDadiPanel().add(btnSelezionePersonalizzazioniFromSelDadi);
				
		btnAvviaPartitaFromSelDado = new JButton("Avvia partita");
		btnAvviaPartitaFromSelDado.setBounds(360, 227, 119, 30);
		tableDadiSelezionabiliManager.getSelezioneDadiPanel().add(btnAvviaPartitaFromSelDado);
				
	}

	public JButton getBtnSelezionePersonalizzazioniFromSelDadi() {
		return btnSelezionePersonalizzazioniFromSelDadi;
	}

	public JButton getBtnAvviaPartitaFromSelDado() {
		return btnAvviaPartitaFromSelDado;
	}

	public JScrollPane getScrollPaneDadoSelezionato() {
		return scrollPaneDadoSelezionato;
	}

	public JTable getTableDadoSelezionato() {
		return tableDadoSelezionato;
	}

	public DefaultTableModel getTableDadoSelezionatoModel() {
		return tableDadoSelezionatoModel;
	}
}
