package GUIManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabellePedineSelezionabiliManager {

	private JPanel selezionePedinaPanel;
	private JLabel lblTitleSelezionePedina;
	private JButton btnReturnToMenuFromSelPedina;
	private JScrollPane scrollPanePedineSelezionabili;
	private JTable tablePedineSelezionabili;
	private DefaultTableModel tablePedineSelezionabiliModel;
	
	public TabellePedineSelezionabiliManager(MenuPanelManager menuPaneManager) {
		selezionePedinaPanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezionePedinaPanel, "name_1050308981416700");
		selezionePedinaPanel.setLayout(null);
		
		lblTitleSelezionePedina = new JLabel("Selezione pedina");
		lblTitleSelezionePedina.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezionePedina.setBounds(166, 10, 177, 36);
		selezionePedinaPanel.add(lblTitleSelezionePedina);
		
		btnReturnToMenuFromSelPedina = new JButton("Menu");
		btnReturnToMenuFromSelPedina.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelPedina.setBounds(0, 0, 57, 25);
		selezionePedinaPanel.add(btnReturnToMenuFromSelPedina);
		
		
		scrollPanePedineSelezionabili = new JScrollPane();
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
		
		tablePedineSelezionabiliModel = (DefaultTableModel) tablePedineSelezionabili.getModel();

	}

	public JPanel getSelezionePedinaPanel() {
		return selezionePedinaPanel;
	}

	public JLabel getLblTitleSelezionePedina() {
		return lblTitleSelezionePedina;
	}

	public JButton getBtnReturnToMenuFromSelPedina() {
		return btnReturnToMenuFromSelPedina;
	}

	public JScrollPane getScrollPanePedineSelezionabili() {
		return scrollPanePedineSelezionabili;
	}

	public JTable getTablePedineSelezionabili() {
		return tablePedineSelezionabili;
	}

	public DefaultTableModel getTablePedineSelezionabiliModel() {
		return tablePedineSelezionabiliModel;
	}
}
