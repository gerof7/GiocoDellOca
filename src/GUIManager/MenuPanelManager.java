package GUIManager;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;

import GUIComponents.ButtonCustom;

public class MenuPanelManager {

	private JLayeredPane layeredPane;
	private JPanel menuPrincipalePanel;
	private JLabel lblTitleMenuPrincipale;
	private ButtonCustom btnConfiguraNuovaPartitaSP;
	private JPanel selezioneTipologiaRegolePanel;
	private JLabel lblTitleSelezioneTipologiaRegole;
	private ButtonCustom btnTipologiaRegoleSet;
	private ButtonCustom btnTipologiaRegoleSingole;
	private ButtonCustom btnReturnToMenuFromSelTipReg;
	private JPanel selezioneRegoleSingolePanel;
	private JLabel lblTitleSelezioneRegoleSingole;
	private JScrollPane scrollPaneRegoleSelezionabili;
	
	
	public MenuPanelManager(JPanel contentPane) {
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_609521392636900");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		menuPrincipalePanel = new JPanel();
		layeredPane.add(menuPrincipalePanel, "name_610369483648900");
		menuPrincipalePanel.setLayout(null);
		
		lblTitleMenuPrincipale = new JLabel("Gioco dell'Oca");
		lblTitleMenuPrincipale.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleMenuPrincipale.setBounds(179, 11, 150, 36);
		menuPrincipalePanel.add(lblTitleMenuPrincipale);
		
		btnConfiguraNuovaPartitaSP = new ButtonCustom("Nuova partita singleplayer", ButtonCustom.ButtonStyle.PRIMARY);
		
		btnConfiguraNuovaPartitaSP.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnConfiguraNuovaPartitaSP.setBounds(163, 72, 182, 45);
		menuPrincipalePanel.add(btnConfiguraNuovaPartitaSP);
		
		selezioneTipologiaRegolePanel = new JPanel();
		layeredPane.add(selezioneTipologiaRegolePanel, "name_610398291392000");
		selezioneTipologiaRegolePanel.setLayout(null);
		
		lblTitleSelezioneTipologiaRegole = new JLabel("Selezione tipologia regole");
		lblTitleSelezioneTipologiaRegole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitleSelezioneTipologiaRegole.setBounds(118, 11, 273, 36);
		selezioneTipologiaRegolePanel.add(lblTitleSelezioneTipologiaRegole);
		
		btnTipologiaRegoleSet = new ButtonCustom("Set di regole predefinite", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaRegoleSet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaRegoleSet.setBounds(265, 108, 167, 42);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSet);
		
		btnTipologiaRegoleSingole = new ButtonCustom("Regole singole", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaRegoleSingole.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTipologiaRegoleSingole.setBounds(83, 108, 167, 42);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSingole);
		
		btnReturnToMenuFromSelTipReg = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelTipReg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnReturnToMenuFromSelTipReg.setBounds(0, 0, 57, 25);
		selezioneTipologiaRegolePanel.add(btnReturnToMenuFromSelTipReg);
		
		selezioneRegoleSingolePanel = new JPanel();
		layeredPane.add(selezioneRegoleSingolePanel, "name_166112297214100");
		selezioneRegoleSingolePanel.setLayout(null);
		
		lblTitleSelezioneRegoleSingole = new JLabel("Selezione regole singole");
		lblTitleSelezioneRegoleSingole.setBounds(127, 11, 254, 30);
		lblTitleSelezioneRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 22));
		selezioneRegoleSingolePanel.add(lblTitleSelezioneRegoleSingole);
		
		scrollPaneRegoleSelezionabili = new JScrollPane();
		scrollPaneRegoleSelezionabili.setBounds(30, 51, 205, 158);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionabili);
		
        ToolTipManager.sharedInstance().setInitialDelay(200);

	}


	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}


	public JPanel getMenuPrincipalePanel() {
		return menuPrincipalePanel;
	}


	public JLabel getLblTitleMenuPrincipale() {
		return lblTitleMenuPrincipale;
	}


	public JButton getBtnConfiguraNuovaPartitaSP() {
		return btnConfiguraNuovaPartitaSP;
	}


	public JPanel getSelezioneTipologiaRegolePanel() {
		return selezioneTipologiaRegolePanel;
	}


	public JLabel getLblTitleSelezioneTipologiaRegole() {
		return lblTitleSelezioneTipologiaRegole;
	}


	public JButton getBtnTipologiaRegoleSet() {
		return btnTipologiaRegoleSet;
	}


	public JButton getBtnTipologiaRegoleSingole() {
		return btnTipologiaRegoleSingole;
	}


	public JButton getBtnReturnToMenuFromSelTipReg() {
		return btnReturnToMenuFromSelTipReg;
	}


	public JPanel getSelezioneRegoleSingolePanel() {
		return selezioneRegoleSingolePanel;
	}


	public JLabel getLblTitleSelezioneRegoleSingole() {
		return lblTitleSelezioneRegoleSingole;
	}


	public JScrollPane getScrollPaneRegoleSelezionabili() {
		return scrollPaneRegoleSelezionabili;
	}
	
	
}
