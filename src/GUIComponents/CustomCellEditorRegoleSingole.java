package GUIComponents;

import java.awt.Component;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.TipologiaRegolaEnum;

public class CustomCellEditorRegoleSingole extends AbstractCellEditor implements TableCellEditor {
    private JComboBox<String> comboBox;
    private Map<String, String[]> dropdownValues;
	private GiocoDellOca giocoDellOca;

    public CustomCellEditorRegoleSingole(Map<String, String[]> dropdownValues, GiocoDellOca giocoDellOca) {
        this.dropdownValues = dropdownValues;
        this.giocoDellOca = giocoDellOca;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	
        String name = "";
        String codiceRegola = (String) table.getModel().getValueAt(row, 0);
        var listaRegoleSingole = giocoDellOca.getListaRegoleSingole();
        
        TipologiaRegolaEnum tipologiaRegola = null;
		
		for (var elem : listaRegoleSingole) {
           	  	if (elem.getCodiceRegola().equals(codiceRegola)) 
           	  	{
           	  		tipologiaRegola = elem.getTipologiaRegola();    
           	  		break;
           	  	}
		}
		
		if(tipologiaRegola == TipologiaRegolaEnum.NumeroCaselle)
			name = "Casella";
		else if (tipologiaRegola == TipologiaRegolaEnum.NumeroDadi)
			name = "Dado";

        String[] options = dropdownValues.get(name);
        if (options != null) {
            comboBox = new JComboBox<>(options);
        } else {
            comboBox = new JComboBox<>(new String[]{});
        }

        comboBox.setSelectedItem(value);

        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

}
