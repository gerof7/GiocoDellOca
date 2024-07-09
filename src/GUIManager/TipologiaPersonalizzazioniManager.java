package GUIManager;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUIComponents.ButtonCustom;

public class TipologiaPersonalizzazioniManager {

	private JPanel selezioneTipologiaPersonalizzazionePanel;
	private JLabel lblTitleSelezioneTipologiaPersonalizzazione;
	private ButtonCustom btnReturnToMenuFromSelPers;
	private ButtonCustom btnTipologiaPersDado;
	private ButtonCustom btnTipologiaPersPedina;
	private ButtonCustom btnReturnToSelScenFromSelPers;
	
	public TipologiaPersonalizzazioniManager(MenuPanelManager menuPaneManager) {
		
		selezioneTipologiaPersonalizzazionePanel = new JPanel();
		menuPaneManager.getLayeredPane().add(selezioneTipologiaPersonalizzazionePanel, "name_1048915761636599");
		selezioneTipologiaPersonalizzazionePanel.setLayout(null);
		
		lblTitleSelezioneTipologiaPersonalizzazione = new JLabel("Selezione personalizzazioni");
		lblTitleSelezioneTipologiaPersonalizzazione.setBounds(112, 11, 285, 36);
		lblTitleSelezioneTipologiaPersonalizzazione.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneTipologiaPersonalizzazionePanel.add(lblTitleSelezioneTipologiaPersonalizzazione);
		
		btnReturnToMenuFromSelPers = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelPers.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelPers.setBounds(0, 0, 57, 25);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToMenuFromSelPers);
		
		btnTipologiaPersDado = new ButtonCustom("Dadi", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaPersDado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaPersDado.setBounds(265, 108, 167, 42);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersDado);
		
		btnTipologiaPersPedina = new ButtonCustom("Pedina", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaPersPedina.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaPersPedina.setBounds(83, 108, 167, 42);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersPedina);
		
		btnReturnToSelScenFromSelPers = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnReturnToSelScenFromSelPers.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnReturnToSelScenFromSelPers.setBounds(10, 242, 167, 42);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToSelScenFromSelPers);
		
	}

	public JPanel getSelezioneTipologiaPersonalizzazionePanel() {
		return selezioneTipologiaPersonalizzazionePanel;
	}

	public JLabel getLblTitleSelezioneTipologiaPersonalizzazione() {
		return lblTitleSelezioneTipologiaPersonalizzazione;
	}

	public JButton getBtnReturnToMenuFromSelPers() {
		return btnReturnToMenuFromSelPers;
	}

	public JButton getBtnTipologiaPersDado() {
		return btnTipologiaPersDado;
	}

	public JButton getBtnTipologiaPersPedina() {
		return btnTipologiaPersPedina;
	}

	public JButton getBtnReturnToSelScenFromSelPers() {
		return btnReturnToSelScenFromSelPers;
	}
}
