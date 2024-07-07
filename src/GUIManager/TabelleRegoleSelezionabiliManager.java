package GUIManager;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelleRegoleSelezionabiliManager {

	private JTable tableRegoleSelezionabili;
	private DefaultTableModel tableRegoleSelezionabiliModel;
	
	public TabelleRegoleSelezionabiliManager(MenuPanelManager menuPaneManager) {
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
		menuPaneManager.getScrollPaneRegoleSelezionabili().setViewportView(tableRegoleSelezionabili);
		
		tableRegoleSelezionabiliModel = (DefaultTableModel) tableRegoleSelezionabili.getModel();
	}

	public JTable getTableRegoleSelezionabili() {
		return tableRegoleSelezionabili;
	}

	public DefaultTableModel getTableRegoleSelezionabiliModel() {
		return tableRegoleSelezionabiliModel;
	}
	
	
}
