package GUIManager;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabellaRegoleSetSelezionabiliManager {

	private JTable tableRegoleSetSelezionabili;
	private DefaultTableModel tableRegoleSetSelezionabiliModel;
	
	public TabellaRegoleSetSelezionabiliManager(TabellaScenarioSelezionatoManager tableScenarioSelezionatoManager) {
		tableRegoleSetSelezionabili = new JTable(){
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
		tableRegoleSetSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Set di regole selezionabili"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRegoleSetSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tableRegoleSetSelezionabili.getColumnModel().getColumn(0).setPreferredWidth(134);
		tableScenarioSelezionatoManager.getScrollPaneRegoleSetSelezionabili().setViewportView(tableRegoleSetSelezionabili);
		
		tableRegoleSetSelezionabiliModel = (DefaultTableModel) tableRegoleSetSelezionabili.getModel();

	}

	public JTable getTableRegoleSetSelezionabili() {
		return tableRegoleSetSelezionabili;
	}

	public DefaultTableModel getTableRegoleSetSelezionabiliModel() {
		return tableRegoleSetSelezionabiliModel;
	}
}
