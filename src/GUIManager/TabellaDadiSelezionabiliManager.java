package GUIManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import GUIComponents.ButtonCustom;

public class TabellaDadiSelezionabiliManager {

	private JPanel selezioneDadiPanel;
	private JLabel lblTitleSelezioneDadi;
	private ButtonCustom btnReturnToMenuFromSelDado;
	private JScrollPane scrollPaneDadiSelezionabili;
	private JTable tableDadiSelezionabili;
	private DefaultTableModel tableDadiSelezionabiliModel;
	
	public TabellaDadiSelezionabiliManager(MenuPanelManager menuPaneManager) {
		selezioneDadiPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneDadiPanel, "name_1050468066312900");
		selezioneDadiPanel.setLayout(null);
		
		lblTitleSelezioneDadi = new JLabel("Selezione dadi");
		lblTitleSelezioneDadi.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneDadi.setBounds(175, 10, 158, 36);
		selezioneDadiPanel.add(lblTitleSelezioneDadi);
		
		btnReturnToMenuFromSelDado = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelDado.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelDado.setBounds(0, 0, 57, 25);
		selezioneDadiPanel.add(btnReturnToMenuFromSelDado);
		
		
		scrollPaneDadiSelezionabili = new JScrollPane();
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
		
		tableDadiSelezionabiliModel = (DefaultTableModel) tableDadiSelezionabili.getModel();
		
	}

	public JPanel getSelezioneDadiPanel() {
		return selezioneDadiPanel;
	}

	public JLabel getLblTitleSelezioneDadi() {
		return lblTitleSelezioneDadi;
	}

	public JButton getBtnReturnToMenuFromSelDado() {
		return btnReturnToMenuFromSelDado;
	}

	public JScrollPane getScrollPaneDadiSelezionabili() {
		return scrollPaneDadiSelezionabili;
	}

	public JTable getTableDadiSelezionabili() {
		return tableDadiSelezionabili;
	}

	public DefaultTableModel getTableDadiSelezionabiliModel() {
		return tableDadiSelezionabiliModel;
	}
}
