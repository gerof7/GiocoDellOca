package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GUIComponents.ButtonCustom;
import GUIComponents.ButtonCustom.ButtonStyle;
import GUIComponents.ComboItem;
import GUIComponents.CustomCellEditorRegoleSingole;
import GiocoDellOca.Casella;
import GiocoDellOca.CasellaFine;
import GiocoDellOca.CasellaSpeciale;
import GiocoDellOca.Dado;
import GiocoDellOca.Giocatore;
import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Pedina;
import GiocoDellOca.Personalizzazione;
import GiocoDellOca.Regola;
import GiocoDellOca.Scenario;
import GiocoDellOca.TipologiaRegolaEnum;
import Utilities.MossaResult;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.GroupLayout;

public class GiocoDellOcaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableRegoleSelezionabili;
	private GiocoDellOca giocoDellOca;
	private List<Regola> listaRegoleSingole;
	private Map<String, Set<Regola>> mapRegoleSet;
	private List<Scenario> listScenari;
	private List<Personalizzazione> listPersonalizzazioni;
	private JTable tableRegoleSelezionate;
	private JTable tableScenariSelezionabili;
	private JTable tableScenarioSelezionato;
	private JTable tableRegoleSetSelezionabili;
	private JTable tableRegoleSetSelezionato;
	private JTable tablePedineSelezionabili;
	private JTable tablePedinaSelezionata;
	private JTable tableDadiSelezionabili;
	private JTable tableDadoSelezionato;
	private List<ButtonCustom> buttonsCaselle;
	private List<Pedina> pedine; 
	private int turnoCorrente;
	private Giocatore giocatoreInSessione;
    private Random random;
    private String dadoPath;
    private Map<Integer, Casella> caselleMap;
    private int numeroDadi;
    private List<JLabel> dadiLabels;
    private JPanel tabellonePanel;
    private ButtonCustom lanciaDadoButton;
	private DefaultTableModel tableRegoleSetSelezionabiliModel;
	private DefaultTableModel tableRegoleSetSelezionatoModel;
	private DefaultTableModel tableRegoleSelezionabiliModel;
	private DefaultTableModel tableRegoleSelezionateModel;
	private DefaultTableModel tableScenariSelezionabiliModel;
	private	DefaultTableModel tableScenarioSelezionatoModel;
	private DefaultTableModel tablePedineSelezionabiliModel;
	private DefaultTableModel tablePedinaSelezionataModel;
	private DefaultTableModel tableDadiSelezionabiliModel;
	private DefaultTableModel tableDadoSelezionatoModel;
	private JLayeredPane layeredPane;
	private JPanel menuPrincipalePanel;
	private boolean isPartitaMultiplayer;
	private int numeroGiocatoriMP;
	private int numeroGiocatoreCorrente;
	private int numeroGiocatoreInTurno = 1;
	private JLabel lblGiocatoreInTurno;
	private JLabel lblGiocatoreCorrente;
	private JPanel dadiPanel;
	private JPanel tabelloneMainPanel;
	private JPanel adminPanel;
	private JPanel gestioneScenariPanel;
	private JDialog dlgLoginAdmin;
	private JTextField txtAdminUser;
	private JPasswordField txtAdminPass;
	private DefaultTableModel modelScenari;
	private DefaultTableModel modelSetRegole;
	private DefaultTableModel modelRegoleInSet;
	private Map<String, List<Object[]>> modificheTemporaneeSetRegole = new HashMap<>();
	private String ultimoSetSelezionato = null;
	private JPanel gestioneRegoleSetPanel;
	private DefaultTableModel modelPersonalizzazioniPedine;
	private DefaultTableModel modelPersonalizzazioniDadi;
	private JPanel gestionePersonalizzazioniPanel;
	private JComboBox<ComboItem> regoleSetDropdown;
	private JComboBox<ComboItem> scenarioDropdown;
	@SuppressWarnings("unchecked")
	private JComboBox<ComboItem>[] dadoDropdown = new JComboBox[4];
	@SuppressWarnings("unchecked")
	private JComboBox<ComboItem>[] pedinaDropdown = new JComboBox[4];


	private void SwitchToPanel (JLayeredPane layeredPane, JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.revalidate();
		layeredPane.repaint();
	}
	
	private void hideColumn(JTable table, int columnIndex) {
		TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(columnIndex);
        columnModel.removeColumn(column);
    }
	
	private void aggiornaGiocatoreAttuale() {
		var giocatori = GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getAllGiocatori();
	    Giocatore g = giocatori.get(numeroGiocatoreInTurno);
	    if (g != null) {
	    	lblGiocatoreInTurno.setText("Turno attuale: " + g.getNome());
	    } else {
	    	lblGiocatoreInTurno.setText("");
	    }
	}
	
	private void aggiornaTabellone() {
	    for (int i = 0; i < caselleMap.size(); i++) {
	        Casella casella = caselleMap.get(i + 1);
	        JPanel casellaPanel = (JPanel) tabellonePanel.getComponent(i);
	        casellaPanel.removeAll();

	        if (casella instanceof CasellaSpeciale) {
	            CasellaSpeciale casellaSpeciale = (CasellaSpeciale) casella;
	            switch (casellaSpeciale.getTipologiaCasellaSpeciale()) {
	                case Oca:
	                    casellaPanel.setBackground(Color.YELLOW);
	                    break;
	                case Ponte:
	                    casellaPanel.setBackground(Color.CYAN);
	                    break;
	                case Locanda:
	                    casellaPanel.setBackground(Color.PINK);
	                    break;
	                case Prigione:
	                    casellaPanel.setBackground(Color.RED);
	                    break;
	                case Labirinto:
	                    casellaPanel.setBackground(Color.ORANGE);
	                    break;
	                case Scheletro:
	                    casellaPanel.setBackground(Color.DARK_GRAY);
	                    break;
	            }
	        } else if (casella instanceof CasellaFine) {
	            casellaPanel.setBackground(Color.GREEN);
	        } else {
	            casellaPanel.setBackground(Color.LIGHT_GRAY);
	        }

	        JLabel casellaLabel = new JLabel("Casella " + (i + 1));
	        casellaPanel.add(casellaLabel);
	    }
	    
        var giocatori = GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getAllGiocatori();
        int index = 1;

	    for (Pedina pedina : pedine) {
	        int posizioneCorrente = pedina.getPosizione();
	        JPanel casellaPanel = (JPanel) tabellonePanel.getComponent(posizioneCorrente - 1);

	        ImageIcon originalIcon = new ImageIcon(pedina.getPath());
	        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	        JLabel pedinaLabel = new JLabel(new ImageIcon(scaledImage));
	        
	        Giocatore giocatoreAssociato = giocatori.get(index);

	        if (giocatoreAssociato != null) {
	            pedinaLabel.setToolTipText(giocatoreAssociato.getNome());
	        }

	        if (!GiocoDellOcaGUI.this.isPartitaMultiplayer && pedina == pedine.get(0))
	            pedinaLabel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2)); 
	            
	        casellaPanel.add(pedinaLabel);
	        index++;
	    }

	    tabellonePanel.revalidate();
	    tabellonePanel.repaint();
	}


	private void animaDadi(List<JLabel> dadiLabels, ActionListener afterAnimation) {
	    Timer timer = new Timer(100, null);
	    int numeroDadi = dadiLabels.size();
	    int[] risultatiFinali = new int[numeroDadi];

	    timer.addActionListener(new ActionListener() {
	        int counter = 0;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            for (int i = 0; i < numeroDadi; i++) {
	                int numeroDado = random.nextInt(6) + 1;
	                JLabel dadoLabel = dadiLabels.get(i);

	                String path = getDadoFacePath(numeroDado);
	                dadoLabel.setIcon(new ImageIcon(path));

	                if (counter == 9) {
	                    risultatiFinali[i] = numeroDado;
	                }
	            }

	            counter++;

	            if (counter >= 10) {
	                ((Timer) e.getSource()).stop();

	                dadiLabels.get(0).putClientProperty("risultatiFinali", risultatiFinali);

	                afterAnimation.actionPerformed(null);
	            }
	        }
	    });

	    timer.start();
	}

//	private String getDadoFacePath(int numeroFaccia) {
//	    int lastUnderscoreIndex = GiocoDellOcaGUI.this.dadoPath.lastIndexOf('_');
//	    int dotIndex = GiocoDellOcaGUI.this.dadoPath.lastIndexOf('.');
//
//	    if (lastUnderscoreIndex != -1 && dotIndex != -1 && lastUnderscoreIndex < dotIndex) {
//	        return GiocoDellOcaGUI.this.dadoPath.substring(0, lastUnderscoreIndex + 1)
//	                + numeroFaccia
//	                + GiocoDellOcaGUI.this.dadoPath.substring(dotIndex);
//	    } else {
//	        return "./src/images/dadoclassico_" + numeroFaccia + ".png";
//	    }
//	}
	
	private String getDadoFacePath(int numeroFaccia) {
	    String path = GiocoDellOcaGUI.this.dadoPath;

	    if (path == null || path.isBlank()) {
	        return "./src/images/dadoclassico_" + numeroFaccia + ".png";
	    }

	    int lastUnderscoreIndex = path.lastIndexOf('_');
	    int dotIndex = path.lastIndexOf('.');

	    boolean formatoOK = lastUnderscoreIndex != -1 && dotIndex != -1 && lastUnderscoreIndex < dotIndex;

	    if (!formatoOK) {
	        return path;
	    }

	    String faceNumber = path.substring(lastUnderscoreIndex + 1, dotIndex);

	    boolean isNumeroValido = faceNumber.matches("[1-6]");

	    if (!isNumeroValido) {
	        return path;
	    }

	    return path.substring(0, lastUnderscoreIndex + 1)
	            + numeroFaccia
	            + path.substring(dotIndex);
	}


	 
	private void lanciaDado() {
	    lanciaDadoButton.setEnabled(false);
	
	    boolean isMultiplayer = GiocoDellOcaGUI.this.isPartitaMultiplayer;
	    var gioco = GiocoDellOcaGUI.this.giocoDellOca;
	
	    if (isMultiplayer) {
	        Giocatore giocatoreCorrente = gioco.getPartitaCorrente()
	                .getAllGiocatori()
	                .get(numeroGiocatoreInTurno);
	
	        impostaDadiGiocatore(giocatoreCorrente);
	
	        animaDadi(dadiLabels, e -> {
	            int[] risultati = (int[]) dadiLabels.get(0).getClientProperty("risultatiFinali");
	            int risultatoTotale = Arrays.stream(risultati).sum();
	
	            JOptionPane.showMessageDialog(null,
	                    giocatoreCorrente.getNome() + " ha lanciato un totale di: " + risultatoTotale);
	
	            Pedina pedinaCorrente = pedine.get(numeroGiocatoreInTurno - 1);
	            MossaResult risultato = gioco.eseguiTurnoGiocatore(pedinaCorrente, risultatoTotale);
	
	            for (String msg : risultato.getNotifiche())
	                JOptionPane.showMessageDialog(null, msg);
	
	            if (risultato.isGiocoTerminato()) {
	                terminaGioco();
	                return;
	            }
	
	            aggiornaTabellone();
	            numeroGiocatoreInTurno = (numeroGiocatoreInTurno % numeroGiocatoriMP) + 1;
	            aggiornaGiocatoreAttuale();
	            lanciaDadoButton.setEnabled(true);
	        });
	
	        return;
	    }
	
	    if (turnoCorrente == 0) { 
	    	
	        impostaDadiGiocatore(giocatoreInSessione);

	        animaDadi(dadiLabels, e -> {
	            int[] risultati = (int[]) dadiLabels.get(0).getClientProperty("risultatiFinali");
	            int risultatoTotale = Arrays.stream(risultati).sum();
	
	            JOptionPane.showMessageDialog(null,
	                    giocatoreInSessione.getNome() + " ha lanciato un totale di: " + risultatoTotale);
	
	            Pedina pedinaCorrente = pedine.get(turnoCorrente);
	            MossaResult risultato = gioco.eseguiTurnoGiocatore(pedinaCorrente, risultatoTotale);
	
	            for (String msg : risultato.getNotifiche())
	                JOptionPane.showMessageDialog(null, msg);
	
	            if (risultato.isGiocoTerminato()) {
	                terminaGioco();
	                return;
	            }
	
	            aggiornaTabellone();
	            turnoCorrente = (turnoCorrente + 1) % pedine.size();
	            avviaTurnoBot();
	        });
	    } 
	    else { 
	        dadiPanel.removeAll();
	        dadiPanel.revalidate();
	        dadiPanel.repaint();
	
	        GiocoDellOcaGUI.this.dadoPath = "./src/images/dadoclassico_1.png";
	        GiocoDellOcaGUI.this.dadiLabels.clear();
	
	        for (int i = 0; i < numeroDadi; i++) {
	            JLabel dadoLabel = new JLabel(new ImageIcon(GiocoDellOcaGUI.this.dadoPath));
	            GiocoDellOcaGUI.this.dadiLabels.add(dadoLabel);
	            dadiPanel.add(dadoLabel);
	        }
	
	        animaDadi(dadiLabels, e -> {
	            int[] risultati = (int[]) dadiLabels.get(0).getClientProperty("risultatiFinali");
	            int risultatoTotale = Arrays.stream(risultati).sum();
	
	            JOptionPane.showMessageDialog(null,
	                    "Il bot ha lanciato un totale di: " + risultatoTotale);
	
	            Pedina pedinaCorrente = pedine.get(turnoCorrente);
	            MossaResult risultato = gioco.eseguiTurnoGiocatore(pedinaCorrente, risultatoTotale);
	
	            for (String msg : risultato.getNotifiche())
	                JOptionPane.showMessageDialog(null, msg);
	
	            if (risultato.isGiocoTerminato()) {
	                terminaGioco();
	                return;
	            }
	
	            aggiornaTabellone();
	            turnoCorrente = (turnoCorrente + 1) % pedine.size();
	
	            if (turnoCorrente == 0)
	                lanciaDadoButton.setEnabled(true);
	            else
	                avviaTurnoBot();
	        });
	    }
	}
		
	private void impostaDadiGiocatore(Giocatore giocatore) {
		dadiPanel.removeAll(); 
		dadiPanel.revalidate();  
		dadiPanel.repaint(); 
		
	    int numeroDadi = GiocoDellOcaGUI.this.numeroDadi;

	    String dadoPath = (giocatore.getDado() != null)
	            ? giocatore.getDado().getPath()
	            : "./src/images/dadoclassico_1.png";
	    
        GiocoDellOcaGUI.this.dadoPath = dadoPath;

	    GiocoDellOcaGUI.this.dadiLabels.clear();
	    
	    for (int i = 0; i < numeroDadi; i++) {
	        JLabel dadoLabel = new JLabel(new ImageIcon(dadoPath));
	        GiocoDellOcaGUI.this.dadiLabels.add(dadoLabel);
            dadiPanel.add(dadoLabel);
	    }
	   
	}
	
	private void avviaTurnoBot() {
	    Timer botTimer = new Timer(500, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            lanciaDado();
	        }
	    });
	    botTimer.setRepeats(false);
	    botTimer.start();
	}

	private void terminaGioco() {
	    buttonsCaselle.clear();
	    pedine.clear();
	    turnoCorrente = 0;
	    numeroGiocatoreInTurno = 1;
	    giocatoreInSessione = null;
	    dadoPath = null;
	
	    tabellonePanel.removeAll();
	    tabellonePanel.revalidate();
	    tabellonePanel.repaint();
	
	    if (lblGiocatoreInTurno != null) lblGiocatoreInTurno.setText("");
	    if (lblGiocatoreCorrente != null) lblGiocatoreCorrente.setText("");
	
	    returnToMenuActions();
	}

	
	private void inizializzaTabellone() {

    GiocoDellOcaGUI.this.buttonsCaselle = new ArrayList<>();
    GiocoDellOcaGUI.this.pedine = new ArrayList<>();
    GiocoDellOcaGUI.this.turnoCorrente = 0;
    GiocoDellOcaGUI.this.giocatoreInSessione = GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getGiocatoreInSessione();
    GiocoDellOcaGUI.this.random = new Random();

    var tabellone = GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getTabellone();
    var caselleMap = tabellone.getCaselleMap();
    GiocoDellOcaGUI.this.caselleMap = caselleMap;

    var numeroDadi = tabellone.getNumeroDadi();
    GiocoDellOcaGUI.this.numeroDadi = (numeroDadi > 0) ? numeroDadi : 1;

    int numeroCaselle = caselleMap.size();
    int lato = (int) Math.ceil(Math.sqrt(numeroCaselle));

    tabellonePanel.removeAll();
    tabellonePanel.setLayout(new GridLayout(lato, lato));

    if (!GiocoDellOcaGUI.this.isPartitaMultiplayer) {
        var pedinaGiocatore = GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getGiocatoreInSessione().getPedina();
        pedine.add(pedinaGiocatore);
        pedine.add(new Pedina("pedina_bot", "Pedina Bot", "./src/images/KratosGoose.png"));
    } else {
        var giocatori = GiocoDellOcaGUI.this.giocoDellOca.getPartitaCorrente().getAllGiocatori();
        for (Giocatore giocatore : giocatori.values()) {
            pedine.add(giocatore.getPedina());
        }
    }

    for (int i = 1; i <= numeroCaselle; i++) {
        Casella casella = caselleMap.get(i);
        JPanel casellaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        casellaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if (casella instanceof CasellaSpeciale) {
            CasellaSpeciale cs = (CasellaSpeciale) casella;
            switch (cs.getTipologiaCasellaSpeciale()) {
                case Oca -> casellaPanel.setBackground(Color.YELLOW);
                case Ponte -> casellaPanel.setBackground(Color.CYAN);
                case Locanda -> casellaPanel.setBackground(Color.PINK);
                case Prigione -> casellaPanel.setBackground(Color.RED);
                case Labirinto -> casellaPanel.setBackground(Color.ORANGE);
                case Scheletro -> casellaPanel.setBackground(Color.DARK_GRAY);
            }
        } else if (casella instanceof CasellaFine) {
            casellaPanel.setBackground(Color.GREEN);
        } else {
            casellaPanel.setBackground(Color.LIGHT_GRAY);
        }

        ButtonCustom button = new ButtonCustom("Casella " + i, ButtonStyle.WHITE);
        if (casella.getDescrizione() != null && !casella.getDescrizione().isEmpty())
            casellaPanel.setToolTipText("Casella " + i + ": " + casella.getDescrizione());
        else
            casellaPanel.setToolTipText("Casella " + i);

        casellaPanel.add(button);
        buttonsCaselle.add(button);
        tabellonePanel.add(casellaPanel);
    }

    tabellonePanel.revalidate();
    tabellonePanel.repaint();

    tabelloneMainPanel.removeAll();
    tabelloneMainPanel.setLayout(new BorderLayout());

    ButtonCustom menuButton = new ButtonCustom("Menu", ButtonStyle.DESTRUCTIVE);
    menuButton.addActionListener(e -> {
        int conferma = JOptionPane.showConfirmDialog(null, "Vuoi tornare al menu principale?", "Conferma", JOptionPane.YES_NO_OPTION);
        if (conferma == JOptionPane.YES_OPTION) {
            terminaGioco();
            lblGiocatoreCorrente.setText("");
        }
    });

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(menuButton, BorderLayout.WEST);

    if (GiocoDellOcaGUI.this.isPartitaMultiplayer) {
        lblGiocatoreInTurno = new JLabel("Turno attuale: ");
        lblGiocatoreInTurno.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGiocatoreInTurno.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(lblGiocatoreInTurno, BorderLayout.CENTER);
        aggiornaGiocatoreAttuale();
    }

    tabelloneMainPanel.add(topPanel, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane(tabellonePanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    tabelloneMainPanel.add(scrollPane, BorderLayout.CENTER);

    dadiLabels = new ArrayList<>();
    dadiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    if (!GiocoDellOcaGUI.this.isPartitaMultiplayer) {
        var pathDado = "./src/images/dadoclassico_1.png";
        if (GiocoDellOcaGUI.this.giocatoreInSessione != null && GiocoDellOcaGUI.this.giocatoreInSessione.getDado() != null) {
            pathDado = GiocoDellOcaGUI.this.giocatoreInSessione.getDado().getPath();
        }
        GiocoDellOcaGUI.this.dadoPath = pathDado;

        for (int i = 0; i < GiocoDellOcaGUI.this.numeroDadi; i++) {
            JLabel dadoLabel = new JLabel(new ImageIcon(GiocoDellOcaGUI.this.dadoPath));
            GiocoDellOcaGUI.this.dadiLabels.add(dadoLabel);
            dadiPanel.add(dadoLabel);
        }
    }

    lanciaDadoButton = new ButtonCustom("Lancia il dado", ButtonStyle.PRIMARY);
    lanciaDadoButton.addActionListener(e -> lanciaDado());

    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(lanciaDadoButton);
    bottomPanel.add(dadiPanel);

    tabelloneMainPanel.add(bottomPanel, BorderLayout.SOUTH);

    SwitchToPanel(layeredPane, tabelloneMainPanel);
    aggiornaTabellone();
}
	
	private void configuraUtenteDaSelezione(
	        DefaultTableModel tablePedinaSelezionataModel,
	        DefaultTableModel tableDadoSelezionatoModel,
	        DefaultTableModel tablePedineSelezionabiliModel,
	        DefaultTableModel tableDadiSelezionabiliModel,
	        List<Personalizzazione> listPersonalizzazioni,
	        JPanel selezioneNumeroGiocatoriPanel) {
	    
	    if (tablePedinaSelezionataModel.getRowCount() > 0) {
	        String codicePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 0);
	        GiocoDellOcaGUI.this.giocoDellOca.impostaPedinaGiocatore(codicePedina, 1, false);
	    }

	    if (tableDadoSelezionatoModel.getRowCount() > 0) {
	        String codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
	        GiocoDellOcaGUI.this.giocoDellOca.impostaDadoGiocatore(codiceDado, 1, false);
	    }

	    SwitchToPanel(layeredPane, selezioneNumeroGiocatoriPanel);

	    tablePedinaSelezionataModel.setRowCount(0);
	    tableDadoSelezionatoModel.setRowCount(0);
	    tablePedineSelezionabiliModel.setRowCount(0);
	    tableDadiSelezionabiliModel.setRowCount(0);
	    
	    var codicePedinaDefaultG2 = GiocoDellOcaGUI.this.giocoDellOca.getCodiciPedineGiocatoriDefault().get(2); 
	    var codiceDadoDefaultG2 = GiocoDellOcaGUI.this.giocoDellOca.getCodiciDadiGiocatoriDefault().get(2);

	    for (var personalizzazione : listPersonalizzazioni) {
	        if (personalizzazione instanceof Pedina) { 
	        	if (codicePedinaDefaultG2 != null && personalizzazione.getCodicePersonalizzazione().equals(codicePedinaDefaultG2))
	        		tablePedinaSelezionataModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()}); 
	        	else 
	        		tablePedineSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()}); 
        	}
	        else if (personalizzazione instanceof Dado)
	        { 
	        	if (codiceDadoDefaultG2 != null && personalizzazione.getCodicePersonalizzazione().equals(codiceDadoDefaultG2))
	        		tableDadoSelezionatoModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()}); 
	        	else 
	        		tableDadiSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()}); 
        	}	    
        }
	    
	}


	private void returnToMenuActions() {
		tableRegoleSetSelezionabiliModel.setRowCount(0);
		tableRegoleSetSelezionatoModel.setRowCount(0);	
    	tableRegoleSelezionabiliModel.setRowCount(0);
    	tableRegoleSelezionateModel.setRowCount(0);	
    	tableScenariSelezionabiliModel.setRowCount(0);
    	tableScenarioSelezionatoModel.setRowCount(0);
    	tablePedineSelezionabiliModel.setRowCount(0);
    	tablePedinaSelezionataModel.setRowCount(0);
    	tableDadiSelezionabiliModel.setRowCount(0);
    	tableDadoSelezionatoModel.setRowCount(0);
    	GiocoDellOcaGUI.this.isPartitaMultiplayer = false;
    	GiocoDellOcaGUI.this.numeroGiocatoreCorrente = 0;
    	GiocoDellOcaGUI.this.numeroGiocatoriMP = 0;
    	GiocoDellOcaGUI.this.numeroGiocatoreInTurno = 1;
		lblGiocatoreCorrente.setText("");
	    GiocoDellOcaGUI.this.giocoDellOca.resetPartita();
		SwitchToPanel(layeredPane, menuPrincipalePanel);
	}
	
	private void selezionaComboPerCodici(JComboBox<ComboItem>[] comboArray, Map<Integer, String> codiciMap) {
	    for (int j = 0; j < comboArray.length; j++) {
	        JComboBox<ComboItem> combo = comboArray[j];
	        String codiceDaSelezionare = codiciMap.get(j+1);
	        
	        if (codiceDaSelezionare == null) {
	            combo.setSelectedIndex(0);
	            continue;
	        }

	        for (int i = 0; i < combo.getItemCount(); i++) {
	            ComboItem item = combo.getItemAt(i);
	            if (codiceDaSelezionare.equals(item.getCodice())) {
	                combo.setSelectedIndex(i);
	                break;
	            }
	        }
	    }
	}
	
	private void setTextFieldsDaMappa(JTextField[] fields, Map<Integer, String> valoriMap) {
	    for (int i = 0; i < fields.length; i++) {
	        String valore = valoriMap.get(i + 1);
	        fields[i].setText(valore != null ? valore : ""); 
	    }
	}
	
	private void mostraDialogLoginAdmin() {
	    if (dlgLoginAdmin == null) {
	        dlgLoginAdmin = new JDialog(SwingUtilities.getWindowAncestor(layeredPane), "Login Amministratore", JDialog.ModalityType.APPLICATION_MODAL);
	        dlgLoginAdmin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        JPanel content = new JPanel(new BorderLayout(10, 10));
	        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

	        JLabel title = new JLabel("Accesso Amministratore", SwingConstants.CENTER);
	        title.setFont(new Font("Arial", Font.BOLD, 18));
	        content.add(title, BorderLayout.NORTH);

	        JPanel form = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(6, 6, 6, 6);
	        gbc.anchor = GridBagConstraints.WEST;
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        txtAdminUser = new JTextField(16);
	        txtAdminPass = new JPasswordField(16);

	        gbc.gridx = 0; gbc.gridy = 0;
	        form.add(new JLabel("Username:"), gbc);
	        gbc.gridx = 1;
	        form.add(txtAdminUser, gbc);

	        gbc.gridx = 0; gbc.gridy = 1;
	        form.add(new JLabel("Password:"), gbc);
	        gbc.gridx = 1;
	        form.add(txtAdminPass, gbc);

	        content.add(form, BorderLayout.CENTER);

	        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        JButton btnAnnulla = new JButton("Annulla");
	        JButton btnLogin = new JButton("Accedi");

	        btnAnnulla.addActionListener(e -> dlgLoginAdmin.dispose());
	        btnLogin.addActionListener(e -> {
	            String u = txtAdminUser.getText().trim();
	            String p = new String(txtAdminPass.getPassword());

	            if ("admin".equals(u) && "admin".equals(p)) {
	                JOptionPane.showMessageDialog(dlgLoginAdmin, "Autenticazione effettuata!");
	                dlgLoginAdmin.dispose();
	                SwitchToPanel(layeredPane, adminPanel);
	            } else {
	                JOptionPane.showMessageDialog(dlgLoginAdmin, "Credenziali errate.", "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        });

	        actions.add(btnAnnulla);
	        actions.add(btnLogin);
	        content.add(actions, BorderLayout.SOUTH);

	        dlgLoginAdmin.setContentPane(content);
	        dlgLoginAdmin.pack();
	        dlgLoginAdmin.setLocationRelativeTo(layeredPane);
	    }

	    txtAdminUser.setText("");
	    txtAdminPass.setText("");
	    dlgLoginAdmin.setVisible(true);
	}

	private JPanel creaAdminPanel() {
	    JPanel panel = new JPanel(new BorderLayout());

	    JLabel lblTitle = new JLabel("Pannello Amministratore", SwingConstants.CENTER);
	    lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
	    lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
	    panel.add(lblTitle, BorderLayout.NORTH);

	    JPanel centerPanel = new JPanel(new GridLayout(3, 1, 16, 16));
	    centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));

	    JButton btnGestioneScenari = new JButton("Gestione Scenari");
	    JButton btnGestioneRegoleSet = new JButton("Gestione Set di Regole");
	    JButton btnGestionePersonalizzazioni = new JButton("Gestione Personalizzazioni");

	    centerPanel.add(btnGestioneScenari);
	    centerPanel.add(btnGestioneRegoleSet);       
	    centerPanel.add(btnGestionePersonalizzazioni);

	    panel.add(centerPanel, BorderLayout.CENTER);

	    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton btnBack = new JButton("Torna al Menu");
	    btnBack.addActionListener(e -> SwitchToPanel(layeredPane, menuPrincipalePanel));
	    southPanel.add(btnBack);
	    panel.add(southPanel, BorderLayout.SOUTH);

	    btnGestioneScenari.addActionListener(e -> {
	    	caricaScenariInTabella();
	        SwitchToPanel(layeredPane, gestioneScenariPanel);
	    });
	    
	    btnGestioneRegoleSet.addActionListener(e -> {
	        caricaSetRegoleInTabella();
	        SwitchToPanel(layeredPane, gestioneRegoleSetPanel);
	    });
	    
	    btnGestionePersonalizzazioni.addActionListener(e -> {
	        caricaPersonalizzazioniInTabella();
	        SwitchToPanel(layeredPane, gestionePersonalizzazioniPanel);
	    });



	    return panel;
	}

	@SuppressWarnings("serial")
	private JPanel creaGestioneScenariPanel() {
	    JPanel panel = new JPanel(new BorderLayout());

	    JLabel title = new JLabel("Gestione Scenari", SwingConstants.CENTER);
	    title.setFont(new Font("Arial", Font.BOLD, 24));
	    title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
	    panel.add(title, BorderLayout.NORTH);

	    String[] cols = {
	        "Codice", "Descrizione",
	        "Oca", "Ponte", "Locanda",
	        "Prigione", "Labirinto", "Scheletro"
	    };

	    DefaultTableModel model = new DefaultTableModel(cols, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return true;
	        }
	    };

	    JTable table = new JTable(model);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.getColumnModel().getColumn(0).setPreferredWidth(140); 
	    table.getColumnModel().getColumn(1).setPreferredWidth(220); 
	    for (int c = 2; c < cols.length; c++) {
	        table.getColumnModel().getColumn(c).setPreferredWidth(220);
	    }
	    JScrollPane scroll = new JScrollPane(table);
	    panel.add(scroll, BorderLayout.CENTER);

	    for (Scenario s : giocoDellOca.getListaScenari()) {
	        model.addRow(new Object[]{
	            s.getCodiceScenario(),
	            s.getDescrizione(),
	            s.getDescrizioneCasellaOca(),
	            s.getDescrizioneCasellaPonte(),
	            s.getDescrizioneCasellaLocanda(),
	            s.getDescrizioneCasellaPrigione(),
	            s.getDescrizioneCasellaLabirinto(),
	            s.getDescrizioneCasellaScheletro()
	        });
	    }
	    
	    this.modelScenari = model;

	    JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton btnNuovo = new JButton("Nuovo");
	    JButton btnElimina = new JButton("Elimina");
	    JButton btnSalva = new JButton("Salva Modifiche");
	    JButton btnIndietro = new JButton("Indietro");
	    south.add(btnNuovo);
	    south.add(btnElimina);
	    south.add(btnSalva);
	    south.add(btnIndietro);
	    panel.add(south, BorderLayout.SOUTH);

	    btnNuovo.addActionListener(e -> {
	        model.addRow(new Object[]{"", "", "", "", "", "", "", ""});
	    });

	    btnElimina.addActionListener(e -> {
	        int row = table.getSelectedRow();
	        if (row >= 0) {
	            model.removeRow(row);
	        } else {
	            JOptionPane.showMessageDialog(panel, "Seleziona una riga da eliminare.", "Info", JOptionPane.INFORMATION_MESSAGE);
	        }
	    });

	    btnSalva.addActionListener(e -> {
	        List<Scenario> nuovi = new ArrayList<>();

	        for (int i = 0; i < model.getRowCount(); i++) {
	            String cod = safeStr(model.getValueAt(i, 0));
	            String desc = safeStr(model.getValueAt(i, 1));

	            if (cod.isBlank() || desc.isBlank()) {
	                JOptionPane.showMessageDialog(panel,
	                        "Codice e Descrizione sono obbligatori (riga " + (i + 1) + ").",
	                        "Errore", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            Scenario s = new Scenario(
	                cod,
	                desc,
	                safeStr(model.getValueAt(i, 2)),  
	                safeStr(model.getValueAt(i, 3)),  
	                safeStr(model.getValueAt(i, 4)),  
	                safeStr(model.getValueAt(i, 5)), 
	                safeStr(model.getValueAt(i, 6)),  
	                safeStr(model.getValueAt(i, 7))   
	            );
	            nuovi.add(s);
	        }

	        giocoDellOca.replaceAllScenari(nuovi);
	        caricaScenariInTabella();
	        JOptionPane.showMessageDialog(panel, "Scenari salvati correttamente!", "OK", JOptionPane.INFORMATION_MESSAGE);
	    });

	    btnIndietro.addActionListener(e -> {
	        SwitchToPanel(layeredPane, adminPanel);
	    });

	    return panel;
	}

	private void caricaScenariInTabella() {
	    modelScenari.setRowCount(0);

	    for (Scenario s : giocoDellOca.getListaScenari()) {
	        modelScenari.addRow(new Object[]{
	            s.getCodiceScenario(),
	            s.getDescrizione(),
	            s.getDescrizioneCasellaOca(),
	            s.getDescrizioneCasellaPonte(),
	            s.getDescrizioneCasellaLocanda(),
	            s.getDescrizioneCasellaPrigione(),
	            s.getDescrizioneCasellaLabirinto(),
	            s.getDescrizioneCasellaScheletro()
	        });
	    }
	}

	
	private static String safeStr(Object v) {
	    return v == null ? "" : v.toString();
	}
	
	@SuppressWarnings("serial")
	private JPanel creaGestioneRegoleSetPanel() {

	    JPanel panel = new JPanel(new BorderLayout());

	    JLabel titolo = new JLabel("Gestione Set di Regole", SwingConstants.CENTER);
	    titolo.setFont(new Font("SansSerif", Font.BOLD, 22));
	    panel.add(titolo, BorderLayout.NORTH);

	    modelSetRegole = new DefaultTableModel(new String[]{"Nome Set"}, 0) {
	        @Override public boolean isCellEditable(int r, int c) { return false; }
	    };

	    JTable tableSetRegole = new JTable(modelSetRegole);
	    JScrollPane scrollSet = new JScrollPane(tableSetRegole);
	    scrollSet.setBorder(BorderFactory.createTitledBorder("Elenco set"));

	    modelRegoleInSet = new DefaultTableModel(
	        new String[]{"Codice", "Descrizione", "Proprietà", "Tipologia"}, 0
	    ) {
	        @Override public boolean isCellEditable(int r, int c) {
	            return c == 2; 
	        }
	    };

	    JTable tableRegoleInSet = new JTable(modelRegoleInSet);
	    JScrollPane scrollRegole = new JScrollPane(tableRegoleInSet);
	    scrollRegole.setBorder(BorderFactory.createTitledBorder("Regole del set selezionato"));

	    TableColumn colProprieta = tableRegoleInSet.getColumnModel().getColumn(2);

	    colProprieta.setCellEditor(new DefaultCellEditor(new JComboBox<>()) {
	        private JComboBox<String> comboBox;

	        @Override
	        public Component getTableCellEditorComponent(JTable table, Object value,
	                                                     boolean isSelected, int row, int column) {

	            String tipologia = table.getModel().getValueAt(row, 3).toString();

	            comboBox = new JComboBox<>();

	            if (tipologia.equals("NumeroCaselle")) {
	                comboBox.addItem("42");
	                comboBox.addItem("63");
	                comboBox.addItem("90");
	            } else if (tipologia.equals("NumeroDadi")) {
	                comboBox.addItem("1");
	                comboBox.addItem("2");
	                comboBox.addItem("3");
	            }

	            comboBox.setSelectedItem(value);

	            return comboBox;
	        }

	        @Override
	        public Object getCellEditorValue() {
	            return comboBox.getSelectedItem();
	        }
	    });



	    JPanel crudPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton btnNuovo = new JButton("Nuovo set");
	    JButton btnElimina = new JButton("Elimina set");
	    JButton btnSalva = new JButton("Salva");
	    JButton btnIndietro = new JButton("Indietro");

	    crudPanel.add(btnNuovo);
	    crudPanel.add(btnElimina);
	    crudPanel.add(btnSalva);
	    crudPanel.add(btnIndietro);

	    JPanel centerPanel = new JPanel(new GridLayout(2, 1));
	    centerPanel.add(scrollSet);
	    centerPanel.add(scrollRegole);

	    panel.add(centerPanel, BorderLayout.CENTER);
	    panel.add(crudPanel, BorderLayout.SOUTH);

	    Runnable caricaSetDaGioco = () -> {
	        modelSetRegole.setRowCount(0);
	        for (String setName : giocoDellOca.getMapRegoleSet().keySet()) {
	            modelSetRegole.addRow(new Object[]{ setName });
	        }
	    };

	    caricaSetDaGioco.run();

	    tableSetRegole.getSelectionModel().addListSelectionListener(e -> {

	        if (e.getValueIsAdjusting()) return;

	        int row = tableSetRegole.getSelectedRow();
	        if (row < 0) return;

	        String nomeSet = safeStr(modelSetRegole.getValueAt(row, 0));
	        if (nomeSet.isBlank()) return;

	        salvaSetTemporaneo();

	        ultimoSetSelezionato = nomeSet;

	        modelRegoleInSet.setRowCount(0);

	        if (modificheTemporaneeSetRegole.containsKey(nomeSet)) {
	            for (Object[] riga : modificheTemporaneeSetRegole.get(nomeSet)) {
	                modelRegoleInSet.addRow(riga);
	            }
	        } else {
	            var regoleSet = giocoDellOca.getMapRegoleSet().get(nomeSet);

	            if (regoleSet != null) {
	                for (var r : regoleSet) {
	                    modelRegoleInSet.addRow(new Object[]{
	                        r.getCodiceRegola(),
	                        r.getDescrizione(),
	                        r.getProprietaRegola(),
	                        r.getTipologiaRegola().toString()
	                    });
	                }
	            }
	        }
	    });

	    btnNuovo.addActionListener(e -> {
	        String nomeSet = JOptionPane.showInputDialog(panel, "Nome del nuovo set:");
	        if (nomeSet == null || nomeSet.isBlank()) return;

	        modelSetRegole.addRow(new Object[]{ nomeSet });

	        List<Object[]> righe = new ArrayList<>();
	        righe.add(new Object[]{
	            "regola1_default_" + nomeSet, "Numero caselle", "63", "NumeroCaselle"
	        });
	        righe.add(new Object[]{
	            "regola2_default_" + nomeSet, "Numero dadi", "2", "NumeroDadi"
	        });

	        modificheTemporaneeSetRegole.put(nomeSet, righe);

	        ultimoSetSelezionato = nomeSet;

	        modelRegoleInSet.setRowCount(0);
	        for (var r : righe) modelRegoleInSet.addRow(r);
	    });

	    btnElimina.addActionListener(e -> {
	        int row = tableSetRegole.getSelectedRow();
	        if (row < 0) return;

	        String nomeSet = safeStr(modelSetRegole.getValueAt(row, 0));

	        modelSetRegole.removeRow(row);
	        modificheTemporaneeSetRegole.remove(nomeSet);

	        modelRegoleInSet.setRowCount(0);
	        ultimoSetSelezionato = null;
	    });

	    btnSalva.addActionListener(e -> {

	        Map<String, Set<Regola>> nuovi = new LinkedHashMap<>();

	        for (int i = 0; i < modelSetRegole.getRowCount(); i++) {
	            String key = safeStr(modelSetRegole.getValueAt(i, 0));
	            if (key.isBlank()) continue;

	            List<Object[]> righe = modificheTemporaneeSetRegole.get(key);
	            if (righe == null) continue;

	            Set<Regola> setRegole = new LinkedHashSet<>();
	            for (Object[] r : righe) {
	                String cod = safeStr(r[0]);
	                String desc = safeStr(r[1]);
	                String prop = safeStr(r[2]);
	                String tipo = safeStr(r[3]);

	                setRegole.add(new Regola(cod, desc, prop, TipologiaRegolaEnum.valueOf(tipo)));
	            }

	            nuovi.put(key, setRegole);
	        }

	        giocoDellOca.replaceAllSetRegole(nuovi);

	        JOptionPane.showMessageDialog(panel, "Salvataggio completato!");
	    });

	    btnIndietro.addActionListener(e -> {
	    	modificheTemporaneeSetRegole.clear();
	        modelRegoleInSet.setRowCount(0);
	        ultimoSetSelezionato = null;
	        SwitchToPanel(layeredPane, adminPanel);
	    });

	    return panel;
	}


	private void salvaSetTemporaneo() {
	    if (ultimoSetSelezionato == null) return;

	    List<Object[]> lista = new ArrayList<>();

	    for (int r = 0; r < modelRegoleInSet.getRowCount(); r++) {
	        Object[] rowData = new Object[4];
	        for (int c = 0; c < 4; c++) {
	            rowData[c] = modelRegoleInSet.getValueAt(r, c);
	        }
	        lista.add(rowData);
	    }

	    modificheTemporaneeSetRegole.put(ultimoSetSelezionato, lista);
	}
	
	private void caricaSetRegoleInTabella() {
	    modelSetRegole.setRowCount(0);
	    for (String nomeSet : giocoDellOca.getMapRegoleSet().keySet()) {
	        modelSetRegole.addRow(new Object[]{ nomeSet });
	    }
	}

	private void caricaPersonalizzazioniInTabella() {
	    modelPersonalizzazioniPedine.setRowCount(0);
	    modelPersonalizzazioniDadi.setRowCount(0);

	    for (Personalizzazione p : giocoDellOca.getListaPersonalizzazioni()) {
	        if (p instanceof Pedina) {
	            modelPersonalizzazioniPedine.addRow(
	                new Object[]{ p.getCodicePersonalizzazione(), p.getDescrizione(), p.getPath() }
	            );
	        } else if (p instanceof Dado) {
	            modelPersonalizzazioniDadi.addRow(
	                new Object[]{ p.getCodicePersonalizzazione(), p.getDescrizione(), p.getPath() }
	            );
	        }
	    }
	}
	
	@SuppressWarnings("serial")
	private JPanel creaGestionePersonalizzazioniPanel() {

	    JPanel panel = new JPanel(new BorderLayout());

	    JLabel titolo = new JLabel("Gestione Personalizzazioni", SwingConstants.CENTER);
	    titolo.setFont(new Font("SansSerif", Font.BOLD, 22));
	    panel.add(titolo, BorderLayout.NORTH);

	    String[] colonne = {"Codice", "Descrizione", "Path Immagine"};

	    DefaultTableModel modelPedine = new DefaultTableModel(colonne, 0) {
	        @Override public boolean isCellEditable(int r, int c) { return true; }
	    };

	    DefaultTableModel modelDadi = new DefaultTableModel(colonne, 0) {
	        @Override public boolean isCellEditable(int r, int c) { return true; }
	    };

	    this.modelPersonalizzazioniPedine = modelPedine;
	    this.modelPersonalizzazioniDadi = modelDadi;

	    JTable tablePedine = new JTable(modelPedine);
	    tablePedine.setRowHeight(32);

	    JTable tableDadi = new JTable(modelDadi);
	    tableDadi.setRowHeight(32);

	    TableColumn colP = tablePedine.getColumnModel().getColumn(2);
	    TableColumn colD = tableDadi.getColumnModel().getColumn(2);

	    colP.setCellRenderer(new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                                                       boolean hasFocus, int row, int column) {
	            JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            lbl.setText(null);
	            if (value instanceof String path && !path.isBlank()) {
	                ImageIcon ic = new ImageIcon(path);
	                Image scaled = ic.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	                lbl.setIcon(new ImageIcon(scaled));
	            } else lbl.setText("Nessuna immagine");
	            return lbl;
	        }
	    });

	    colD.setCellRenderer(colP.getCellRenderer());

	    JPanel center = new JPanel(new GridLayout(2, 1));

	    JScrollPane scrollPedine = new JScrollPane(tablePedine);
	    scrollPedine.setBorder(BorderFactory.createTitledBorder("Pedine"));

	    JScrollPane scrollDadi = new JScrollPane(tableDadi);
	    scrollDadi.setBorder(BorderFactory.createTitledBorder("Dadi"));

	    center.add(scrollPedine);
	    center.add(scrollDadi);

	    panel.add(center, BorderLayout.CENTER);

	    JPanel crud = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	    JButton btnNuovaPedina = new JButton("Nuova Pedina");
	    JButton btnEliminaPedina = new JButton("Elimina Pedina");
	    JButton btnNuovoDado = new JButton("Nuovo Dado");
	    JButton btnEliminaDado = new JButton("Elimina Dado");
	    JButton btnSalva = new JButton("Salva");
	    JButton btnIndietro = new JButton("Indietro");

	    crud.add(btnNuovaPedina);
	    crud.add(btnEliminaPedina);
	    crud.add(btnNuovoDado);
	    crud.add(btnEliminaDado);
	    crud.add(btnSalva);
	    crud.add(btnIndietro);

	    panel.add(crud, BorderLayout.SOUTH);

	    btnNuovaPedina.addActionListener(e -> {
	        modelPedine.addRow(new Object[]{"codice", "descrizione", "path/immagine.png"});
	    });

	    btnEliminaPedina.addActionListener(e -> {
	        int r = tablePedine.getSelectedRow();
	        if (r >= 0) modelPedine.removeRow(r);
	    });

	    btnNuovoDado.addActionListener(e -> {
	        modelDadi.addRow(new Object[]{"codice", "descrizione", "path/immagine.png"});
	    });

	    btnEliminaDado.addActionListener(e -> {
	        int r = tableDadi.getSelectedRow();
	        if (r >= 0) modelDadi.removeRow(r);
	    });

	    btnSalva.addActionListener(e -> {

	        List<Personalizzazione> nuove = new ArrayList<>();

	        for (int i = 0; i < modelPedine.getRowCount(); i++) {
	            String cod = safeStr(modelPedine.getValueAt(i, 0));
	            String desc = safeStr(modelPedine.getValueAt(i, 1));
	            String path = safeStr(modelPedine.getValueAt(i, 2));

	            if (cod.isBlank() || path.isBlank()) {
	                JOptionPane.showMessageDialog(panel, "Codice e Path immagine devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            nuove.add(new Pedina(cod, desc, path));
	        }

	        for (int i = 0; i < modelDadi.getRowCount(); i++) {
	            String cod = safeStr(modelDadi.getValueAt(i, 0));
	            String desc = safeStr(modelDadi.getValueAt(i, 1));
	            String path = safeStr(modelDadi.getValueAt(i, 2));

	            if (cod.isBlank() || path.isBlank()) {
	                JOptionPane.showMessageDialog(panel, "Codice e Path immagine devono essere compilati!", "Errore", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            nuove.add(new Dado(cod, desc, path));
	        }

	        giocoDellOca.replaceAllPersonalizzazioni(nuove);

	        JOptionPane.showMessageDialog(panel, "Salvato correttamente!");
	    });

	    btnIndietro.addActionListener(e -> {
	        SwitchToPanel(layeredPane, adminPanel);
	    });

	    return panel;
	}

	private void refreshImpostazioniDropdown() {

	    regoleSetDropdown.removeAllItems();
	    regoleSetDropdown.addItem(new ComboItem("0", "Seleziona"));

	    var regoleSet = giocoDellOca.getMapRegoleSet();

	    for (var entry : regoleSet.entrySet()) {
	        String codice = entry.getKey();
	        Set<Regola> set = entry.getValue();

	        String descrizione = set.stream()
	            .map(r -> r.getDescrizione() + " " + r.getProprietaRegola())
	            .limit(2)
	            .collect(Collectors.joining(", "));

	        regoleSetDropdown.addItem(new ComboItem(codice, codice + " : " + descrizione));
	    }

	    scenarioDropdown.removeAllItems();
	    scenarioDropdown.addItem(new ComboItem("0", "Seleziona"));

	    for (Scenario s : giocoDellOca.getListaScenari()) {
	        scenarioDropdown.addItem(new ComboItem(s.getCodiceScenario(), s.getDescrizione()));
	    }

	    var personalizzazioni = giocoDellOca.getListaPersonalizzazioni();

	    for (int i = 0; i < 4; i++) {
	        dadoDropdown[i].removeAllItems();
	        dadoDropdown[i].addItem(new ComboItem("0", "Seleziona"));

	        for (var p : personalizzazioni) {
	            if (p instanceof Dado) {
	                dadoDropdown[i].addItem(new ComboItem(p.getCodicePersonalizzazione(), p.getDescrizione()));
	            }
	        }
	    }

	    for (int i = 0; i < 4; i++) {
	        pedinaDropdown[i].removeAllItems();
	        pedinaDropdown[i].addItem(new ComboItem("0", "Seleziona"));

	        for (var p : personalizzazioni) {
	            if (p instanceof Pedina) {
	                pedinaDropdown[i].addItem(new ComboItem(p.getCodicePersonalizzazione(), p.getDescrizione()));
	            }
	        }
	    }

	}

	private void selectComboByCodice(JComboBox<ComboItem> combo, String codice) {
	    for (int i = 0; i < combo.getItemCount(); i++) {
	        ComboItem item = combo.getItemAt(i);
	        if (item.getCodice().equals(codice)) {
	            combo.setSelectedIndex(i);
	            return;
	        }
	    }
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiocoDellOcaGUI frame = new GiocoDellOcaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public GiocoDellOcaGUI() {		
		
		this.giocoDellOca = new GiocoDellOca();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		this.setTitle("Gioco dell'Oca");
		ImageIcon icon = new ImageIcon("./src/images/Icon.png");
		this.setIconImage(icon.getImage());
		
		JLabel lblTitleMenuPrincipale;
		ButtonCustom btnConfiguraNuovaPartitaSP;
		JPanel selezioneTipologiaRegolePanel;
		JLabel lblTitleSelezioneTipologiaRegole;
		ButtonCustom btnTipologiaRegoleSet;
		ButtonCustom btnTipologiaRegoleSingole;
		ButtonCustom btnReturnToMenuFromSelTipReg;
		JPanel selezioneRegoleSingolePanel;
		JLabel lblTitleSelezioneRegoleSingole;
		JScrollPane scrollPaneRegoleSelezionabili;
		
		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_609521392636900");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		menuPrincipalePanel = new JPanel();
		layeredPane.add(menuPrincipalePanel, "name_610369483648900");
		menuPrincipalePanel.setLayout(null);
		
		lblTitleMenuPrincipale = new JLabel("Gioco dell'Oca");
		lblTitleMenuPrincipale.setFont(new Font("Segoe UI", Font.BOLD, 99));
		lblTitleMenuPrincipale.setBounds(420, 10, 672, 112);
		menuPrincipalePanel.add(lblTitleMenuPrincipale);
		
		btnConfiguraNuovaPartitaSP = new ButtonCustom("Nuova partita singleplayer", ButtonCustom.ButtonStyle.PRIMARY);
		
		btnConfiguraNuovaPartitaSP.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		btnConfiguraNuovaPartitaSP.setBounds(564, 220, 384, 92);
		menuPrincipalePanel.add(btnConfiguraNuovaPartitaSP);
		
		ButtonCustom btnConfiguraNuovaPartitaMP = new ButtonCustom("Nuova partita multiplayer", ButtonStyle.PRIMARY);
		
		btnConfiguraNuovaPartitaMP.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		btnConfiguraNuovaPartitaMP.setBounds(564, 365, 384, 92);
		menuPrincipalePanel.add(btnConfiguraNuovaPartitaMP);
		
		ButtonCustom btnGestisciImpostazioni = new ButtonCustom("Gestisci impostazioni", ButtonStyle.PRIMARY);
		btnGestisciImpostazioni.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		btnGestisciImpostazioni.setBounds(564, 510, 384, 92);
		menuPrincipalePanel.add(btnGestisciImpostazioni);
		
		ButtonCustom btnPannelloAdmin = new ButtonCustom("Pannello Admin", ButtonStyle.PRIMARY);
		btnPannelloAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		btnPannelloAdmin.setBounds(564, 655, 384, 92);
		menuPrincipalePanel.add(btnPannelloAdmin);
		btnPannelloAdmin.addActionListener(e -> mostraDialogLoginAdmin());

		adminPanel = creaAdminPanel();
		gestioneScenariPanel = creaGestioneScenariPanel();
		gestioneRegoleSetPanel = creaGestioneRegoleSetPanel();
		gestionePersonalizzazioniPanel = creaGestionePersonalizzazioniPanel();

		layeredPane.add(adminPanel, "adminPanel");
		layeredPane.add(gestioneScenariPanel, "gestioneScenariPanel");
		layeredPane.add(gestioneRegoleSetPanel, "gestioneRegoleSetPanel");
		layeredPane.add(gestionePersonalizzazioniPanel, "gestionePersonalizzazioni");

		
		selezioneTipologiaRegolePanel = new JPanel();
		layeredPane.add(selezioneTipologiaRegolePanel, "name_610398291392000");
		selezioneTipologiaRegolePanel.setLayout(null);
		
		lblTitleSelezioneTipologiaRegole = new JLabel("Selezione tipologia regole");
		lblTitleSelezioneTipologiaRegole.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezioneTipologiaRegole.setBounds(383, 10, 745, 81);
		selezioneTipologiaRegolePanel.add(lblTitleSelezioneTipologiaRegole);
		
		btnTipologiaRegoleSet = new ButtonCustom("Set di regole predefinite", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 28));
		btnTipologiaRegoleSet.setBounds(831, 344, 365, 128);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSet);
		
		btnTipologiaRegoleSingole = new ButtonCustom("Regole singole", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 28));
		btnTipologiaRegoleSingole.setBounds(316, 344, 326, 128);
		selezioneTipologiaRegolePanel.add(btnTipologiaRegoleSingole);
		
		btnReturnToMenuFromSelTipReg = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelTipReg.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelTipReg.setBounds(0, 0, 178, 74);
		selezioneTipologiaRegolePanel.add(btnReturnToMenuFromSelTipReg);
		
		selezioneRegoleSingolePanel = new JPanel();
		layeredPane.add(selezioneRegoleSingolePanel, "name_166112297214100");
		selezioneRegoleSingolePanel.setLayout(null);
		
		lblTitleSelezioneRegoleSingole = new JLabel("Selezione regole singole");
		lblTitleSelezioneRegoleSingole.setBounds(414, 10, 683, 81);
		lblTitleSelezioneRegoleSingole.setFont(new Font("Segoe UI", Font.BOLD, 60));
		selezioneRegoleSingolePanel.add(lblTitleSelezioneRegoleSingole);
		
		scrollPaneRegoleSelezionabili = new JScrollPane();
		scrollPaneRegoleSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSelezionabili.setBounds(10, 211, 731, 462);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionabili);
		
        ToolTipManager.sharedInstance().setInitialDelay(200);		
		
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
		tableRegoleSelezionabili.setRowHeight(40);
		tableRegoleSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
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
		scrollPaneRegoleSelezionabili.setViewportView(tableRegoleSelezionabili);
		
		tableRegoleSelezionabiliModel = (DefaultTableModel) tableRegoleSelezionabili.getModel();
		
		hideColumn(tableRegoleSelezionabili, 0);
		
		JScrollPane scrollPaneRegoleSelezionate;
		ButtonCustom btnAvanzaToSelezionaScenarioRegSing;
		ButtonCustom btnReturnToMenuFromSelRegSing;
		
		scrollPaneRegoleSelezionate = new JScrollPane();
		scrollPaneRegoleSelezionate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSelezionate.setBounds(771, 211, 731, 462);
		selezioneRegoleSingolePanel.add(scrollPaneRegoleSelezionate);
		
		tableRegoleSelezionate = new JTable(){
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
		tableRegoleSelezionate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableRegoleSelezionate.setRowHeight(40);
            
		tableRegoleSelezionate.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceRegola", "Regola selezionata", "Valore"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRegoleSelezionate.getColumnModel().getColumn(0).setResizable(false);
		tableRegoleSelezionate.getColumnModel().getColumn(1).setResizable(false);
		tableRegoleSelezionate.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableRegoleSelezionate.getColumnModel().getColumn(2).setResizable(false);
		tableRegoleSelezionate.getColumnModel().getColumn(2).setPreferredWidth(50);
		scrollPaneRegoleSelezionate.setViewportView(tableRegoleSelezionate);
		
		tableRegoleSelezionateModel = (DefaultTableModel) tableRegoleSelezionate.getModel();

		btnAvanzaToSelezionaScenarioRegSing = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnAvanzaToSelezionaScenarioRegSing.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvanzaToSelezionaScenarioRegSing.setBounds(1150, 702, 352, 52);
		selezioneRegoleSingolePanel.add(btnAvanzaToSelezionaScenarioRegSing);
				
		btnReturnToMenuFromSelRegSing = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelRegSing.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelRegSing.setBounds(0, 0, 178, 74);
		selezioneRegoleSingolePanel.add(btnReturnToMenuFromSelRegSing);
		
		Map<String, String[]> dropdownValuesTableRegoleSelezionate = new HashMap<>();
		dropdownValuesTableRegoleSelezionate.put("Casella", new String[]{"42", "63","90"});
		dropdownValuesTableRegoleSelezionate.put("Dado", new String[]{"1","2" ,"3"});
		TableColumn statusColumn = tableRegoleSelezionate.getColumnModel().getColumn(2);
        statusColumn.setCellEditor(new CustomCellEditorRegoleSingole(dropdownValuesTableRegoleSelezionate, giocoDellOca));
		
		hideColumn(tableRegoleSelezionate, 0);
		
		JPanel selezioneScenarioPanel;
		JLabel lblTitleSelezioneScenario;
		JScrollPane scrollPaneScenariSelezionabili;
		
		selezioneScenarioPanel = new JPanel();
		layeredPane.add(selezioneScenarioPanel, "name_769942002122600");
		selezioneScenarioPanel.setLayout(null);
		
		lblTitleSelezioneScenario = new JLabel("Selezione scenario");
		lblTitleSelezioneScenario.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezioneScenario.setBounds(414, 10, 683, 81);
		selezioneScenarioPanel.add(lblTitleSelezioneScenario);
		
		scrollPaneScenariSelezionabili = new JScrollPane();
		scrollPaneScenariSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneScenariSelezionabili.setBounds(10, 211, 731, 461);
		selezioneScenarioPanel.add(scrollPaneScenariSelezionabili);
		
		tableScenariSelezionabili = new JTable(){
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
		tableScenariSelezionabili.setRowHeight(40);
		tableScenariSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableScenariSelezionabili.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceScenario", "Scenario selezionabile"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableScenariSelezionabili.getColumnModel().getColumn(0).setResizable(false);
		tableScenariSelezionabili.getColumnModel().getColumn(0).setPreferredWidth(118);
		tableScenariSelezionabili.getColumnModel().getColumn(1).setResizable(false);
		tableScenariSelezionabili.getColumnModel().getColumn(1).setPreferredWidth(78);
		scrollPaneScenariSelezionabili.setViewportView(tableScenariSelezionabili);
		
		tableScenariSelezionabiliModel = (DefaultTableModel) tableScenariSelezionabili.getModel();
		
		hideColumn(tableScenariSelezionabili, 0);
		
		JScrollPane scrollPaneScenarioSelezionato;
		ButtonCustom btnReturnToSelRegole;
		ButtonCustom btnReturnToMenuFromSelScen;
		ButtonCustom btnAvanzaToSelezionePers;
		JPanel selezioneRegoleSetPanel;
		JLabel lblTitleSelezioneRegoleSet;
		JScrollPane scrollPaneRegoleSetSelezionabili;
		
		scrollPaneScenarioSelezionato = new JScrollPane();
		scrollPaneScenarioSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneScenarioSelezionato.setBounds(771, 211, 731, 462);
		selezioneScenarioPanel.add(scrollPaneScenarioSelezionato);
		
		tableScenarioSelezionato = new JTable(){
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
		tableScenarioSelezionato.setRowHeight(40);
		tableScenarioSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableScenarioSelezionato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceScenario", "Scenario selezionato"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableScenarioSelezionato.getColumnModel().getColumn(0).setResizable(false);
		tableScenarioSelezionato.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableScenarioSelezionato.getColumnModel().getColumn(1).setResizable(false);
		tableScenarioSelezionato.getColumnModel().getColumn(1).setPreferredWidth(84);
		scrollPaneScenarioSelezionato.setViewportView(tableScenarioSelezionato);
				
		btnReturnToSelRegole = new ButtonCustom("Selezione regole", ButtonCustom.ButtonStyle.WHITE);	
		btnReturnToSelRegole.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnReturnToSelRegole.setBounds(10, 702, 352, 52);
		selezioneScenarioPanel.add(btnReturnToSelRegole);
		
		btnReturnToMenuFromSelScen = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelScen.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelScen.setBounds(0, 0, 178, 74);
		selezioneScenarioPanel.add(btnReturnToMenuFromSelScen);
		
		btnAvanzaToSelezionePers = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.WHITE);
		btnAvanzaToSelezionePers.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvanzaToSelezionePers.setBounds(1032, 702, 470, 52);
		selezioneScenarioPanel.add(btnAvanzaToSelezionePers);
		
		tableScenarioSelezionatoModel = (DefaultTableModel) tableScenarioSelezionato.getModel();
		
		hideColumn(tableScenarioSelezionato, 0);
		
		selezioneRegoleSetPanel = new JPanel();
		layeredPane.add(selezioneRegoleSetPanel, "name_790010692461100");
		selezioneRegoleSetPanel.setLayout(null);
		
		lblTitleSelezioneRegoleSet = new JLabel("Selezione set di regole");
		lblTitleSelezioneRegoleSet.setBounds(439, 10, 634, 81);
		lblTitleSelezioneRegoleSet.setFont(new Font("Segoe UI", Font.BOLD, 60));
		selezioneRegoleSetPanel.add(lblTitleSelezioneRegoleSet);
		
		scrollPaneRegoleSetSelezionabili = new JScrollPane();
		scrollPaneRegoleSetSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSetSelezionabili.setBounds(10, 211, 731, 462);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionabili);
				
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
		tableRegoleSetSelezionabili.setRowHeight(40);
		tableRegoleSetSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		scrollPaneRegoleSetSelezionabili.setViewportView(tableRegoleSetSelezionabili);
		
		tableRegoleSetSelezionabiliModel = (DefaultTableModel) tableRegoleSetSelezionabili.getModel();
		
		JScrollPane scrollPaneRegoleSetSelezionato;
		ButtonCustom btnAvanzaToSelezionaScenarioRegSet;
		ButtonCustom btnReturnToMenuFromSelRegSet;
		
		scrollPaneRegoleSetSelezionato = new JScrollPane();
		scrollPaneRegoleSetSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneRegoleSetSelezionato.setBounds(771, 211, 731, 462);
		selezioneRegoleSetPanel.add(scrollPaneRegoleSetSelezionato);
		
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
		tableRegoleSetSelezionato.setRowHeight(40);
		tableRegoleSetSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
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

		btnAvanzaToSelezionaScenarioRegSet = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnAvanzaToSelezionaScenarioRegSet.setFont(new Font("Tahoma", Font.BOLD, 30));;		
		btnAvanzaToSelezionaScenarioRegSet.setBounds(1150, 702, 352, 52);
		selezioneRegoleSetPanel.add(btnAvanzaToSelezionaScenarioRegSet);
		
		btnReturnToMenuFromSelRegSet = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelRegSet.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelRegSet.setBounds(0, 0, 178, 74);
		selezioneRegoleSetPanel.add(btnReturnToMenuFromSelRegSet);
		
		hideColumn(tableRegoleSetSelezionato, 0);
		
		JPanel selezioneTipologiaPersonalizzazionePanel;
		JLabel lblTitleSelezioneTipologiaPersonalizzazione;
		ButtonCustom btnReturnToMenuFromSelPers;
		ButtonCustom btnTipologiaPersDado;
		ButtonCustom btnTipologiaPersPedina;
		ButtonCustom btnReturnToSelScenFromSelPers;
		
		selezioneTipologiaPersonalizzazionePanel = new JPanel();
		layeredPane.add(selezioneTipologiaPersonalizzazionePanel, "name_1048915761636599");
		selezioneTipologiaPersonalizzazionePanel.setLayout(null);
		
		lblTitleSelezioneTipologiaPersonalizzazione = new JLabel("Selezione personalizzazioni");
		lblTitleSelezioneTipologiaPersonalizzazione.setBounds(367, 10, 777, 81);
		lblTitleSelezioneTipologiaPersonalizzazione.setFont(new Font("Segoe UI", Font.BOLD, 60));
		selezioneTipologiaPersonalizzazionePanel.add(lblTitleSelezioneTipologiaPersonalizzazione);
		
		btnReturnToMenuFromSelPers = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelPers.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelPers.setBounds(0, 0, 178, 74);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToMenuFromSelPers);
		
		btnTipologiaPersDado = new ButtonCustom("Dadi", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaPersDado.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnTipologiaPersDado.setBounds(831, 344, 326, 128);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersDado);
		
		btnTipologiaPersPedina = new ButtonCustom("Pedina", ButtonCustom.ButtonStyle.WHITE);
		btnTipologiaPersPedina.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnTipologiaPersPedina.setBounds(316, 344, 326, 128);
		selezioneTipologiaPersonalizzazionePanel.add(btnTipologiaPersPedina);
		
		btnReturnToSelScenFromSelPers = new ButtonCustom("Selezione scenario", ButtonCustom.ButtonStyle.WHITE);
		btnReturnToSelScenFromSelPers.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnReturnToSelScenFromSelPers.setBounds(10, 702, 352, 52);
		selezioneTipologiaPersonalizzazionePanel.add(btnReturnToSelScenFromSelPers);
		
		lblGiocatoreCorrente = new JLabel("", SwingConstants.CENTER);
		lblGiocatoreCorrente.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblGiocatoreCorrente.setBounds(367, 90, 777, 50);
		lblGiocatoreCorrente.setVisible(false);
		selezioneTipologiaPersonalizzazionePanel.add(lblGiocatoreCorrente);
		
		JPanel selezionePedinaPanel;
		JLabel lblTitleSelezionePedina;
		ButtonCustom btnReturnToMenuFromSelPedina;
		JScrollPane scrollPanePedineSelezionabili;
		
		selezionePedinaPanel = new JPanel();
		layeredPane.add(selezionePedinaPanel, "name_1050308981416700");
		selezionePedinaPanel.setLayout(null);
		
		lblTitleSelezionePedina = new JLabel("Selezione pedina");
		lblTitleSelezionePedina.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezionePedina.setBounds(518, 10, 476, 81);
		selezionePedinaPanel.add(lblTitleSelezionePedina);
		
		btnReturnToMenuFromSelPedina = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelPedina.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelPedina.setBounds(0, 0, 178, 74);
		selezionePedinaPanel.add(btnReturnToMenuFromSelPedina);
		
		
		scrollPanePedineSelezionabili = new JScrollPane();
		scrollPanePedineSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPanePedineSelezionabili.setBounds(10, 211, 731, 462);
		selezionePedinaPanel.add(scrollPanePedineSelezionabili);
			
		tablePedineSelezionabili = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                	String codice = (String) getModel().getValueAt(row, 0);
                    Personalizzazione personalizzazione = null;
                    
                    for(var p : listPersonalizzazioni) {
                    	if(p.getCodicePersonalizzazione().equals(codice))
                    	{
                    		personalizzazione = p;
                    		break;
                    	}                  		
                    }

                    return "<html>"
                    + "<div style='text-align: center;'>"
                    + personalizzazione.getDescrizione() + "<br>"
                    + "<img src='file:" + personalizzazione.getPath() 
                    + "' width='50' height='50' style='display: block; margin: 0 auto;' />"
                    + "</div>"
                    + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tablePedineSelezionabili.setRowHeight(40);
		tablePedineSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		
		hideColumn(tablePedineSelezionabili, 0);
		
		JScrollPane scrollPanePedinaSelezionata;
		ButtonCustom btnSelezionePersonalizzazioniFromSelPed;
		ButtonCustom btnAvviaPartitaFromSelPedina;
		ButtonCustom btnConfiguraUtentiOspitiFromSelPedina;
		ButtonCustom btnConfiguraProssimoUtenteFromSelPedina;
		
		scrollPanePedinaSelezionata = new JScrollPane();
		scrollPanePedinaSelezionata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPanePedinaSelezionata.setBounds(771, 211, 731, 462);
		selezionePedinaPanel.add(scrollPanePedinaSelezionata);
		
		tablePedinaSelezionata = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                	String codice = (String) getModel().getValueAt(row, 0);
                    Personalizzazione personalizzazione = null;
                    
                    for(var p : listPersonalizzazioni) {
                    	if(p.getCodicePersonalizzazione().equals(codice))
                    	{
                    		personalizzazione = p;
                    		break;
                    	}                  		
                    }

                    return "<html>"
                    + "<div style='text-align: center;'>"
                    + personalizzazione.getDescrizione() + "<br>"
                    + "<img src='file:" + personalizzazione.getPath() 
                    + "' width='50' height='50' style='display: block; margin: 0 auto;' />"
                    + "</div>"
                    + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tablePedinaSelezionata.setRowHeight(40);
		tablePedinaSelezionata.setFont(new Font("Tahoma", Font.PLAIN, 20));
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

		btnSelezionePersonalizzazioniFromSelPed = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.WHITE);
		btnSelezionePersonalizzazioniFromSelPed.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSelezionePersonalizzazioniFromSelPed.setBounds(10, 702, 463, 52);
		selezionePedinaPanel.add(btnSelezionePersonalizzazioniFromSelPed);
		
		btnAvviaPartitaFromSelPedina = new ButtonCustom("Avvia partita", ButtonCustom.ButtonStyle.PRIMARY);
		btnAvviaPartitaFromSelPedina.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvviaPartitaFromSelPedina.setBounds(1150, 702, 352, 52);
		
		btnConfiguraUtentiOspitiFromSelPedina = new ButtonCustom("Configura utenti ospiti", ButtonCustom.ButtonStyle.PRIMARY);
		btnConfiguraUtentiOspitiFromSelPedina.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnConfiguraUtentiOspitiFromSelPedina.setBounds(1150, 702, 352, 52);
		
		btnConfiguraProssimoUtenteFromSelPedina = new ButtonCustom("", ButtonCustom.ButtonStyle.PRIMARY);
		btnConfiguraProssimoUtenteFromSelPedina.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnConfiguraProssimoUtenteFromSelPedina.setBounds(1150, 702, 352, 52);
				
		hideColumn(tablePedinaSelezionata, 0);
		
		JPanel selezioneDadiPanel;
		JLabel lblTitleSelezioneDadi;
		ButtonCustom btnReturnToMenuFromSelDado;
		JScrollPane scrollPaneDadiSelezionabili;
		
		selezioneDadiPanel = new JPanel();
		layeredPane.add(selezioneDadiPanel, "name_1050468066312900");
		selezioneDadiPanel.setLayout(null);
		
		lblTitleSelezioneDadi = new JLabel("Selezione dadi");
		lblTitleSelezioneDadi.setFont(new Font("Segoe UI", Font.BOLD, 60));
		lblTitleSelezioneDadi.setBounds(550, 10, 411, 81);
		selezioneDadiPanel.add(lblTitleSelezioneDadi);
		
		btnReturnToMenuFromSelDado = new ButtonCustom("Menu", ButtonCustom.ButtonStyle.DESTRUCTIVE);
		btnReturnToMenuFromSelDado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnReturnToMenuFromSelDado.setBounds(0, 0, 178, 74);
		selezioneDadiPanel.add(btnReturnToMenuFromSelDado);
		
		
		scrollPaneDadiSelezionabili = new JScrollPane();
		scrollPaneDadiSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneDadiSelezionabili.setBounds(10, 211, 731, 462);
		selezioneDadiPanel.add(scrollPaneDadiSelezionabili);
		
		tableDadiSelezionabili = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                	String codice = (String) getModel().getValueAt(row, 0);
                    Personalizzazione personalizzazione = null;
                    
                    for(var p : listPersonalizzazioni) {
                    	if(p.getCodicePersonalizzazione().equals(codice))
                    	{
                    		personalizzazione = p;
                    		break;
                    	}                  		
                    }

                    return "<html>"
                    + "<div style='text-align: center;'>"
                    + personalizzazione.getDescrizione() + "<br>"
                    + "<img src='file:" + personalizzazione.getPath() 
                    + "' width='50' height='50' style='display: block; margin: 0 auto;' />"
                    + "</div>"
                    + "</html>";
                }
                return super.getToolTipText(e);
            }
		};
		tableDadiSelezionabili.setRowHeight(40);
		tableDadiSelezionabili.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		
		hideColumn(tableDadiSelezionabili, 0);
		
		JScrollPane scrollPaneDadoSelezionato;
		ButtonCustom btnSelezionePersonalizzazioniFromSelDadi;
		ButtonCustom btnAvviaPartitaFromSelDado;
		ButtonCustom btnConfiguraUtentiOspitiFromSelDado;
		ButtonCustom btnConfiguraProssimoUtenteFromSelDado;
		
		scrollPaneDadoSelezionato = new JScrollPane();
		scrollPaneDadoSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneDadoSelezionato.setBounds(771, 211, 731, 462);
		selezioneDadiPanel.add(scrollPaneDadoSelezionato);
		
		tableDadoSelezionato = new JTable(){
			@Override
            public String getToolTipText(java.awt.event.MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());
                
                if (row >= 0 && column >= 0) {
                    String codice = (String) getModel().getValueAt(row, 0);
                    Personalizzazione personalizzazione = null;
                    
                    for(var p : listPersonalizzazioni) {
                    	if(p.getCodicePersonalizzazione().equals(codice))
                    	{
                    		personalizzazione = p;
                    		break;
                    	}                  		
                    }

                    return "<html>"
                    + "<div style='text-align: center;'>"
                    + personalizzazione.getDescrizione() + "<br>"
                    + "<img src='file:" + personalizzazione.getPath() 
                    + "' width='50' height='50' style='display: block; margin: 0 auto;' />"
                    + "</div>"
                    + "</html>";
                                       
                }
                return super.getToolTipText(e);
            }
		};
		tableDadoSelezionato.setRowHeight(40);
		tableDadoSelezionato.setFont(new Font("Tahoma", Font.PLAIN, 20));
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

		btnSelezionePersonalizzazioniFromSelDadi = new ButtonCustom("Selezione personalizzazioni", ButtonCustom.ButtonStyle.WHITE);
		btnSelezionePersonalizzazioniFromSelDadi.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSelezionePersonalizzazioniFromSelDadi.setBounds(10, 702, 454, 52);
		selezioneDadiPanel.add(btnSelezionePersonalizzazioniFromSelDadi);
				
		btnAvviaPartitaFromSelDado = new ButtonCustom("Avvia partita", ButtonCustom.ButtonStyle.PRIMARY);
		btnAvviaPartitaFromSelDado.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAvviaPartitaFromSelDado.setBounds(1150, 702, 352, 52);
		
		btnConfiguraUtentiOspitiFromSelDado = new ButtonCustom("Configura utenti ospiti", ButtonCustom.ButtonStyle.PRIMARY);
		btnConfiguraUtentiOspitiFromSelDado.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnConfiguraUtentiOspitiFromSelDado.setBounds(1150, 702, 352, 52);
		
		btnConfiguraProssimoUtenteFromSelDado = new ButtonCustom("", ButtonCustom.ButtonStyle.PRIMARY);
		btnConfiguraProssimoUtenteFromSelDado.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnConfiguraProssimoUtenteFromSelDado.setBounds(1150, 702, 352, 52);
			
		hideColumn(tableDadoSelezionato, 0);
		
		tabelloneMainPanel = new JPanel();
		layeredPane.add(tabelloneMainPanel, "name_20382102642300");		
		tabellonePanel = new JPanel();
		
		JPanel selezioneNumeroGiocatoriPanel = new JPanel(new BorderLayout());
		layeredPane.add(selezioneNumeroGiocatoriPanel, "name_9545681007400");

		JPanel contenutoCentratoselezioneNumeroGiocatoriPanel = new JPanel();
		contenutoCentratoselezioneNumeroGiocatoriPanel.setLayout(new BoxLayout(contenutoCentratoselezioneNumeroGiocatoriPanel, BoxLayout.Y_AXIS));
		contenutoCentratoselezioneNumeroGiocatoriPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel titoloSelezioneNumeroGiocatori = new JLabel("Seleziona il numero di giocatori", JLabel.CENTER);
		titoloSelezioneNumeroGiocatori.setFont(new Font("Arial", Font.BOLD, 28));
		titoloSelezioneNumeroGiocatori.setAlignmentX(Component.CENTER_ALIGNMENT);

		contenutoCentratoselezioneNumeroGiocatoriPanel.add(Box.createVerticalStrut(30));
		contenutoCentratoselezioneNumeroGiocatoriPanel.add(titoloSelezioneNumeroGiocatori);
		contenutoCentratoselezioneNumeroGiocatoriPanel.add(Box.createVerticalStrut(30));

		ButtonCustom btn2Players = new ButtonCustom("2", ButtonStyle.WHITE);
		ButtonCustom btn3Players = new ButtonCustom("3", ButtonStyle.WHITE);
		ButtonCustom btn4Players = new ButtonCustom("4", ButtonStyle.WHITE);

		Dimension buttonSize = new Dimension(150, 50);
		btn2Players.setMaximumSize(buttonSize);
		btn3Players.setMaximumSize(buttonSize);
		btn4Players.setMaximumSize(buttonSize);

		btn2Players.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn3Players.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn4Players.setAlignmentX(Component.CENTER_ALIGNMENT);

		contenutoCentratoselezioneNumeroGiocatoriPanel.add(btn2Players);
		contenutoCentratoselezioneNumeroGiocatoriPanel.add(Box.createVerticalStrut(15));
		contenutoCentratoselezioneNumeroGiocatoriPanel.add(btn3Players);
		contenutoCentratoselezioneNumeroGiocatoriPanel.add(Box.createVerticalStrut(15));
		contenutoCentratoselezioneNumeroGiocatoriPanel.add(btn4Players);

		selezioneNumeroGiocatoriPanel.add(contenutoCentratoselezioneNumeroGiocatoriPanel, BorderLayout.CENTER);
		
		JLabel gestioneImpostazioniTitle = new JLabel("Impostazioni");
        gestioneImpostazioniTitle.setFont(new Font("Segoe UI", Font.BOLD, 60));
        gestioneImpostazioniTitle.setHorizontalAlignment(SwingConstants.CENTER);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 20);

        JLabel[] gestionImpostazioniNomeLabel = new JLabel[4];
        JTextField[] gestioneImpostazioniNomeField = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            gestionImpostazioniNomeLabel[i] = new JLabel("Nome Giocatore " + (i + 1) + ":");
            gestionImpostazioniNomeLabel[i].setFont(labelFont);
            gestioneImpostazioniNomeField[i] = new JTextField(15);
        }

        JLabel regoleSetLabel = new JLabel("Set di regole:");
        JLabel scenarioLabel = new JLabel("Scenario:");
        regoleSetLabel.setFont(labelFont);
        scenarioLabel.setFont(labelFont);

        regoleSetDropdown = new JComboBox<>();
        scenarioDropdown = new JComboBox<>();
        regoleSetDropdown.setFont(labelFont);
        scenarioDropdown.setFont(labelFont);
        
        regoleSetDropdown.addItem(new ComboItem("0", "Seleziona"));
        scenarioDropdown.addItem(new ComboItem("0", "Seleziona"));
        
        var listaScenari = GiocoDellOcaGUI.this.giocoDellOca.getListaScenari();
        var regoleSet = GiocoDellOcaGUI.this.giocoDellOca.getMapRegoleSet();
        
        for (var entry : regoleSet.entrySet()) {
            String codice = entry.getKey(); 
            Set<Regola> set = entry.getValue();

            String descrizione = set.stream()
                .map(r -> r.getDescrizione() + " " + r.getProprietaRegola())
                .limit(2)
                .collect(Collectors.joining(", "));

            String testoCombo = codice + " : " + descrizione + (set.size() > 2 ? " ..." : "");

            regoleSetDropdown.addItem(new ComboItem(codice, testoCombo));
        }

        for (Scenario s : listaScenari) {
            String codice = s.getCodiceScenario();
            String descrizione = s.getDescrizione();
            scenarioDropdown.addItem(new ComboItem(codice, descrizione));
        }
        
        regoleSetDropdown.addActionListener(e -> {
            ComboItem selected = (ComboItem) regoleSetDropdown.getSelectedItem();
            if (selected != null  && !selected.getCodice().equals("0")) {
                regoleSetDropdown.setToolTipText("<html>" + selected.getDescrizione() + "</html>");
            }
        });
        
        scenarioDropdown.addActionListener(e -> {
            ComboItem selected = (ComboItem) scenarioDropdown.getSelectedItem();
            if (selected != null  && !selected.getCodice().equals("0")) {
            	scenarioDropdown.setToolTipText("<html>" + selected.getDescrizione() + "</html>");
            }
        });

        JLabel[] dadoLabel = new JLabel[4];
        JLabel[] pedinaLabel = new JLabel[4];
        
        var personalizzazioni = GiocoDellOcaGUI.this.giocoDellOca.getListaPersonalizzazioni();

        for (int i = 0; i < 4; i++) {
            dadoDropdown[i] = new JComboBox<>();
        	dadoDropdown[i].addItem(new ComboItem("0", "Seleziona"));
            dadoLabel[i] = new JLabel("Dado Giocatore " + (i + 1) + ":");
            dadoLabel[i].setFont(labelFont);
            dadoDropdown[i].setFont(labelFont);
            
            for (var p : personalizzazioni) {
            	if(p instanceof Dado) {
	                String codice = p.getCodicePersonalizzazione();
	                String descrizione = p.getDescrizione();
	                dadoDropdown[i].addItem(new ComboItem(codice, descrizione));
            	}
            }
            
            var dadoDropdownAttuale = dadoDropdown[i];
            dadoDropdown[i].addActionListener(e -> {
                ComboItem selected = (ComboItem) dadoDropdownAttuale.getSelectedItem();
                if (selected != null && !selected.getCodice().equals("0")) {
                	
                	var codice = selected.getCodice();
                	var path = "";
                	for (var p : personalizzazioni){
                		if(p.getCodicePersonalizzazione() == codice) {
                			path = p.getPath();
                			break;
                		}
                	}
                	
                	dadoDropdownAttuale.setToolTipText("<html>"
                            + "<div style='text-align: center;'>"
                            + selected.getDescrizione() + "<br>"
                            + "<img src='file:" + path
                            + "' width='50' height='50' style='display: block; margin: 0 auto;' />"
                            + "</div>"
                            + "</html>");
                }
            });
            
            pedinaDropdown[i] = new JComboBox<>();
            pedinaDropdown[i].addItem(new ComboItem("0", "Seleziona"));
            pedinaLabel[i] = new JLabel("Pedina Giocatore " + (i + 1) + ":");
            pedinaLabel[i].setFont(labelFont);
            pedinaDropdown[i].setFont(labelFont);
            
            for (var p : personalizzazioni) {
            	if(p instanceof Pedina) {
	                String codice = p.getCodicePersonalizzazione();
	                String descrizione = p.getDescrizione();
	                pedinaDropdown[i].addItem(new ComboItem(codice, descrizione));
            	}
            }
            
            var pedinaDropdownAttuale = pedinaDropdown[i];
            pedinaDropdown[i].addActionListener(e -> {
                ComboItem selected = (ComboItem) pedinaDropdownAttuale.getSelectedItem();
                if (selected != null  && !selected.getCodice().equals("0")) {
                	
                	var codice = selected.getCodice();
                	var path = "";
                	for (var p : personalizzazioni){
                		if(p.getCodicePersonalizzazione() == codice) {
                			path = p.getPath();
                			break;
                		}
                	}
                	
                	pedinaDropdownAttuale.setToolTipText("<html>"
                            + "<div style='text-align: center;'>"
                            + selected.getDescrizione() + "<br>"
                            + "<img src='file:" + path
                            + "' width='50' height='50' style='display: block; margin: 0 auto;' />"
                            + "</div>"
                            + "</html>");
                }
            });
        }
      
        JPanel gestioneImpostazioniFormPanel = new JPanel();
        GroupLayout layoutGestisciImpostazioni = new GroupLayout(gestioneImpostazioniFormPanel);
        gestioneImpostazioniFormPanel.setLayout(layoutGestisciImpostazioni);
        layoutGestisciImpostazioni.setAutoCreateGaps(true);
        layoutGestisciImpostazioni.setAutoCreateContainerGaps(true);

        layoutGestisciImpostazioni.setHorizontalGroup(
            layoutGestisciImpostazioni.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layoutGestisciImpostazioni.createSequentialGroup()
                    .addComponent(gestionImpostazioniNomeLabel[0]).addComponent(gestioneImpostazioniNomeField[0])
                    .addGap(30)
                    .addComponent(gestionImpostazioniNomeLabel[1]).addComponent(gestioneImpostazioniNomeField[1]))
                .addGroup(layoutGestisciImpostazioni.createSequentialGroup()
                    .addComponent(gestionImpostazioniNomeLabel[2]).addComponent(gestioneImpostazioniNomeField[2])
                    .addGap(30)
                    .addComponent(gestionImpostazioniNomeLabel[3]).addComponent(gestioneImpostazioniNomeField[3]))
                .addGroup(layoutGestisciImpostazioni.createSequentialGroup()
                    .addComponent(regoleSetLabel)
                    .addComponent(regoleSetDropdown, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                    .addGap(40)
                    .addComponent(scenarioLabel)
                    .addComponent(scenarioDropdown, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                .addGroup(layoutGestisciImpostazioni.createSequentialGroup()
                    .addComponent(dadoLabel[0]).addComponent(dadoDropdown[0])
                    .addGap(20)
                    .addComponent(dadoLabel[1]).addComponent(dadoDropdown[1])
                    .addGap(20)
                    .addComponent(dadoLabel[2]).addComponent(dadoDropdown[2])
                    .addGap(20)
                    .addComponent(dadoLabel[3]).addComponent(dadoDropdown[3]))
                .addGroup(layoutGestisciImpostazioni.createSequentialGroup()
                    .addComponent(pedinaLabel[0]).addComponent(pedinaDropdown[0])
                    .addGap(20)
                    .addComponent(pedinaLabel[1]).addComponent(pedinaDropdown[1])
                    .addGap(20)
                    .addComponent(pedinaLabel[2]).addComponent(pedinaDropdown[2])
                    .addGap(20)
                    .addComponent(pedinaLabel[3]).addComponent(pedinaDropdown[3]))
        );

        layoutGestisciImpostazioni.setVerticalGroup(
            layoutGestisciImpostazioni.createSequentialGroup()
                .addGroup(layoutGestisciImpostazioni.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(gestionImpostazioniNomeLabel[0]).addComponent(gestioneImpostazioniNomeField[0])
                    .addComponent(gestionImpostazioniNomeLabel[1]).addComponent(gestioneImpostazioniNomeField[1]))
                .addGroup(layoutGestisciImpostazioni.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(gestionImpostazioniNomeLabel[2]).addComponent(gestioneImpostazioniNomeField[2])
                    .addComponent(gestionImpostazioniNomeLabel[3]).addComponent(gestioneImpostazioniNomeField[3]))
                .addGap(30)
                .addGroup(layoutGestisciImpostazioni.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(regoleSetLabel).addComponent(regoleSetDropdown)
                    .addComponent(scenarioLabel).addComponent(scenarioDropdown))
                .addGap(40)
                .addGroup(layoutGestisciImpostazioni.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dadoLabel[0]).addComponent(dadoDropdown[0])
                    .addComponent(dadoLabel[1]).addComponent(dadoDropdown[1])
                    .addComponent(dadoLabel[2]).addComponent(dadoDropdown[2])
                    .addComponent(dadoLabel[3]).addComponent(dadoDropdown[3]))
                .addGroup(layoutGestisciImpostazioni.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pedinaLabel[0]).addComponent(pedinaDropdown[0])
                    .addComponent(pedinaLabel[1]).addComponent(pedinaDropdown[1])
                    .addComponent(pedinaLabel[2]).addComponent(pedinaDropdown[2])
                    .addComponent(pedinaLabel[3]).addComponent(pedinaDropdown[3]))
        );
        
        var codiceScenarioDaSelezionare = GiocoDellOcaGUI.this.giocoDellOca.getCodiceScenarioDefault();
        if (codiceScenarioDaSelezionare != null && codiceScenarioDaSelezionare.isEmpty()) {
        	for (int i = 0; i < scenarioDropdown.getItemCount(); i++) {
        	    ComboItem item = scenarioDropdown.getItemAt(i);
        	    if (item.getCodice().equals(codiceScenarioDaSelezionare)) {
        	    	scenarioDropdown.setSelectedIndex(i);
        	        break;
        	    }
        	}
        }
        
        var codiceRegoleSetDaSelezionare = GiocoDellOcaGUI.this.giocoDellOca.getCodiceRegoleSetDefault();
        if (codiceRegoleSetDaSelezionare != null && codiceRegoleSetDaSelezionare.isEmpty()) {
        	for (int i = 0; i < regoleSetDropdown.getItemCount(); i++) {
        	    ComboItem item = regoleSetDropdown.getItemAt(i);
        	    if (item.getCodice().equals(codiceRegoleSetDaSelezionare)) {
        	    	regoleSetDropdown.setSelectedIndex(i);
        	        break;
        	    }
        	}
        }
        
        JPanel gestioneImpostazioniBottomPanel = new JPanel(new BorderLayout());
        gestioneImpostazioniBottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        ButtonCustom btnReturnToMenuFromGestioneImpostazioni = new ButtonCustom("Menu", ButtonStyle.DESTRUCTIVE);
        btnReturnToMenuFromGestioneImpostazioni.setFont(new Font("Segoe UI", Font.BOLD, 20));

        ButtonCustom salvaImpostazioniButton = new ButtonCustom("Salva impostazioni", ButtonStyle.WHITE);
        salvaImpostazioniButton.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JPanel gestioneImpostazioniButtonsPanel = new JPanel(new BorderLayout());
        gestioneImpostazioniButtonsPanel.add(btnReturnToMenuFromGestioneImpostazioni, BorderLayout.WEST);
        gestioneImpostazioniButtonsPanel.add(salvaImpostazioniButton, BorderLayout.EAST);
        gestioneImpostazioniBottomPanel.add(gestioneImpostazioniButtonsPanel, BorderLayout.CENTER);
        
        JPanel gestioneImpostazioniTopPanel = new JPanel(new BorderLayout());        
        gestioneImpostazioniTopPanel.add(btnReturnToMenuFromGestioneImpostazioni, BorderLayout.WEST);
        gestioneImpostazioniTopPanel.add(gestioneImpostazioniTitle, BorderLayout.CENTER);
        
        JScrollPane gestioneImpostazioniFormPanelScroll = new JScrollPane(gestioneImpostazioniFormPanel);
        gestioneImpostazioniFormPanelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gestioneImpostazioniFormPanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JPanel gestioneImpostazioniMainPanel = new JPanel(new BorderLayout(20, 20));
        gestioneImpostazioniMainPanel.add(gestioneImpostazioniTopPanel, BorderLayout.NORTH);
        gestioneImpostazioniMainPanel.add(gestioneImpostazioniFormPanelScroll, BorderLayout.CENTER);
        gestioneImpostazioniMainPanel.add(gestioneImpostazioniBottomPanel, BorderLayout.SOUTH);
        gestioneImpostazioniMainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
		
		btnConfiguraNuovaPartitaMP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiocoDellOcaGUI.this.isPartitaMultiplayer = true;
			
				GiocoDellOcaGUI.this.giocoDellOca.configuraNuovaPartita();
				
				GiocoDellOcaGUI.this.listaRegoleSingole = giocoDellOca.getListaRegoleSingole();
				GiocoDellOcaGUI.this.mapRegoleSet = giocoDellOca.getMapRegoleSet();
				GiocoDellOcaGUI.this.listScenari = giocoDellOca.getListaScenari();
				GiocoDellOcaGUI.this.listPersonalizzazioni = giocoDellOca.getListaPersonalizzazioni();
				
				var codiceRegoleSetDefault = GiocoDellOcaGUI.this.giocoDellOca.getCodiceRegoleSetDefault();
				var codiceScenarioDefault = GiocoDellOcaGUI.this.giocoDellOca.getCodiceScenarioDefault();
				var codicePedinaDefaultG1 = GiocoDellOcaGUI.this.giocoDellOca.getCodiciPedineGiocatoriDefault().get(1);
				var codiceDadoDefaultG1 = GiocoDellOcaGUI.this.giocoDellOca.getCodiciDadiGiocatoriDefault().get(1);
								
				if(tableRegoleSelezionabiliModel.getRowCount() == 0 &&  tableRegoleSelezionateModel.getRowCount() == 0) 
				{
					for(var regola : listaRegoleSingole) {
						tableRegoleSelezionabiliModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione()});
					}
				}
				
				if(tableRegoleSetSelezionabiliModel.getRowCount() == 0 && tableRegoleSetSelezionatoModel.getRowCount() == 0) 
				{
					for (var key : mapRegoleSet.keySet()) {
						if(codiceRegoleSetDefault != null && key.equals(codiceRegoleSetDefault)) {
							var listaRegoleSet = mapRegoleSet.get(codiceRegoleSetDefault);
							for (var regola: listaRegoleSet) {						
								tableRegoleSetSelezionatoModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione(), regola.getProprietaRegola()});						
							}
						}
						else
							tableRegoleSetSelezionabiliModel.addRow(new Object[] {key});
					}
				}
				
				if(tableScenariSelezionabiliModel.getRowCount() == 0 && tableScenarioSelezionatoModel.getRowCount() == 0) 
				{
					for(var scenario : listScenari) {
						if (codiceScenarioDefault != null && scenario.getCodiceScenario().equals(codiceScenarioDefault))
							tableScenarioSelezionatoModel.addRow(new Object[] {scenario.getCodiceScenario(), scenario.getDescrizione()});
						else
							tableScenariSelezionabiliModel.addRow(new Object[] {scenario.getCodiceScenario(), scenario.getDescrizione()});
					}
				}
				
				if(tablePedineSelezionabili.getRowCount() == 0 && tablePedinaSelezionata.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Pedina)
						{
							if(codicePedinaDefaultG1 != null && personalizzazione.getCodicePersonalizzazione().equals(codicePedinaDefaultG1))
								tablePedinaSelezionataModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
							else
								tablePedineSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
						}
					}
				}
				
				if(tableDadiSelezionabiliModel.getRowCount() == 0 && tableDadoSelezionatoModel.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Dado)
						{
							if(codiceDadoDefaultG1 != null && personalizzazione.getCodicePersonalizzazione().equals(codiceDadoDefaultG1))
								tableDadoSelezionatoModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
							else
								tableDadiSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
						}
					}
				}
									
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});
		
		btnConfiguraNuovaPartitaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiocoDellOcaGUI.this.isPartitaMultiplayer = false;
				
				GiocoDellOcaGUI.this.giocoDellOca.configuraNuovaPartita();
				
				GiocoDellOcaGUI.this.listaRegoleSingole = giocoDellOca.getListaRegoleSingole();
				GiocoDellOcaGUI.this.mapRegoleSet = giocoDellOca.getMapRegoleSet();
				GiocoDellOcaGUI.this.listScenari = giocoDellOca.getListaScenari();
				GiocoDellOcaGUI.this.listPersonalizzazioni = giocoDellOca.getListaPersonalizzazioni();
				
				var codiceRegoleSetDefault = GiocoDellOcaGUI.this.giocoDellOca.getCodiceRegoleSetDefault();
				var codiceScenarioDefault = GiocoDellOcaGUI.this.giocoDellOca.getCodiceScenarioDefault();
				var codicePedinaDefaultG1 = GiocoDellOcaGUI.this.giocoDellOca.getCodiciPedineGiocatoriDefault().get(1);
				var codiceDadoDefaultG1 = GiocoDellOcaGUI.this.giocoDellOca.getCodiciDadiGiocatoriDefault().get(1);
								
				if(tableRegoleSelezionabiliModel.getRowCount() == 0 &&  tableRegoleSelezionateModel.getRowCount() == 0) 
				{
					for(var regola : listaRegoleSingole) {
						tableRegoleSelezionabiliModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione()});
					}
				}
				
				if(tableRegoleSetSelezionabiliModel.getRowCount() == 0 && tableRegoleSetSelezionatoModel.getRowCount() == 0) 
				{
					for (var key : mapRegoleSet.keySet()) {
						if(codiceRegoleSetDefault != null && key.equals(codiceRegoleSetDefault)) {
							var listaRegoleSet = mapRegoleSet.get(codiceRegoleSetDefault);
							for (var regola: listaRegoleSet) {						
								tableRegoleSetSelezionatoModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione(), regola.getProprietaRegola()});						
							}
						}
						else
							tableRegoleSetSelezionabiliModel.addRow(new Object[] {key});
					}
				}
				
				if(tableScenariSelezionabiliModel.getRowCount() == 0 && tableScenarioSelezionatoModel.getRowCount() == 0) 
				{
					for(var scenario : listScenari) {
						if(codiceScenarioDefault != null && scenario.getCodiceScenario().equals(codiceScenarioDefault))
							tableScenarioSelezionatoModel.addRow(new Object[] {scenario.getCodiceScenario(), scenario.getDescrizione()});
						else
							tableScenariSelezionabiliModel.addRow(new Object[] {scenario.getCodiceScenario(), scenario.getDescrizione()});
					}
				}
				
				if(tablePedineSelezionabili.getRowCount() == 0 && tablePedinaSelezionata.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Pedina)
						{
							if(codicePedinaDefaultG1 != null && personalizzazione.getCodicePersonalizzazione().equals(codicePedinaDefaultG1))
								tablePedinaSelezionataModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
							else
								tablePedineSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
						}
					}
				}
				
				if(tableDadiSelezionabiliModel.getRowCount() == 0 && tableDadoSelezionatoModel.getRowCount() == 0) 
				{
					for(var personalizzazione : listPersonalizzazioni) {
						if(personalizzazione instanceof Dado)
						{
							if(codiceDadoDefaultG1 != null && personalizzazione.getCodicePersonalizzazione().equals(codiceDadoDefaultG1))
								tableDadoSelezionatoModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
							else
								tableDadiSelezionabiliModel.addRow(new Object[] {personalizzazione.getCodicePersonalizzazione(), personalizzazione.getDescrizione()});
						}
					}
				}
									
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});

				
		btnTipologiaRegoleSingole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneRegoleSingolePanel);
			}
		});
				
		btnAvanzaToSelezionaScenarioRegSing.addActionListener(e -> {
			GiocoDellOcaGUI.this.giocoDellOca.impostaRegoleSingole(tableRegoleSelezionateModel);
		    SwitchToPanel(layeredPane, selezioneScenarioPanel);
		});
				
		btnAvanzaToSelezionaScenarioRegSet.addActionListener(e -> {
			GiocoDellOcaGUI.this.giocoDellOca.impostaRegoleDaSet(tableRegoleSetSelezionatoModel);
		    SwitchToPanel(layeredPane, selezioneScenarioPanel);
		});
		
		btnTipologiaRegoleSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneRegoleSetPanel);
			}
		});
		
		btnReturnToSelRegole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaRegolePanel);
			}
		});
		
		btnAvanzaToSelezionePers.addActionListener(e -> {
		    if (tableScenarioSelezionatoModel.getRowCount() > 0) {
		        String codiceScenario = (String) tableScenarioSelezionatoModel.getValueAt(0, 0);

		        GiocoDellOcaGUI.this.giocoDellOca.impostaScenario(codiceScenario);

		        SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
		    }
		});

		

		btnReturnToSelScenFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneScenarioPanel);
			}
		});
		

		btnTipologiaPersPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!GiocoDellOcaGUI.this.isPartitaMultiplayer) {
					selezionePedinaPanel.remove(btnConfiguraUtentiOspitiFromSelPedina);
					selezionePedinaPanel.remove(btnConfiguraProssimoUtenteFromSelPedina);
					selezionePedinaPanel.add(btnAvviaPartitaFromSelPedina);
					selezionePedinaPanel.revalidate(); 
					selezionePedinaPanel.repaint(); 

				}
				else {
					if(GiocoDellOcaGUI.this.numeroGiocatoriMP == 0) {
						selezionePedinaPanel.remove(btnAvviaPartitaFromSelPedina);
						selezionePedinaPanel.remove(btnConfiguraProssimoUtenteFromSelPedina);
						selezionePedinaPanel.add(btnConfiguraUtentiOspitiFromSelPedina);
						selezionePedinaPanel.revalidate(); 
						selezionePedinaPanel.repaint(); 
					}
					else {
						if(GiocoDellOcaGUI.this.numeroGiocatoriMP > 2 && GiocoDellOcaGUI.this.numeroGiocatoreCorrente < GiocoDellOcaGUI.this.numeroGiocatoriMP) 
						{
							
							var numeroGiocatore = GiocoDellOcaGUI.this.numeroGiocatoreCorrente + 1;
							btnConfiguraProssimoUtenteFromSelPedina.setText("Configura Giocatore " + numeroGiocatore);
							
							selezionePedinaPanel.remove(btnConfiguraUtentiOspitiFromSelPedina);
							selezionePedinaPanel.remove(btnAvviaPartitaFromSelPedina);
							selezionePedinaPanel.add(btnConfiguraProssimoUtenteFromSelPedina);
							selezionePedinaPanel.revalidate(); 
							selezionePedinaPanel.repaint(); 
						}
						else {
							selezionePedinaPanel.remove(btnConfiguraProssimoUtenteFromSelPedina);
							selezionePedinaPanel.remove(btnConfiguraUtentiOspitiFromSelPedina);
							selezionePedinaPanel.add(btnAvviaPartitaFromSelPedina);
							selezionePedinaPanel.revalidate(); 
							selezionePedinaPanel.repaint(); 
						}
					}
				}
				SwitchToPanel(layeredPane, selezionePedinaPanel);
			}
		});
		

		btnTipologiaPersDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if(!GiocoDellOcaGUI.this.isPartitaMultiplayer) {
					selezioneDadiPanel.remove(btnConfiguraUtentiOspitiFromSelDado);
					selezioneDadiPanel.remove(btnConfiguraProssimoUtenteFromSelDado);
					selezioneDadiPanel.add(btnAvviaPartitaFromSelDado);
					selezioneDadiPanel.revalidate(); 
					selezioneDadiPanel.repaint(); 

				}
				else {					
					if(GiocoDellOcaGUI.this.numeroGiocatoriMP == 0) {
						selezioneDadiPanel.remove(btnAvviaPartitaFromSelDado);
						selezioneDadiPanel.remove(btnConfiguraProssimoUtenteFromSelDado);
						selezioneDadiPanel.add(btnConfiguraUtentiOspitiFromSelDado);
						selezioneDadiPanel.revalidate(); 
						selezioneDadiPanel.repaint(); 
					}
					else {
						if(GiocoDellOcaGUI.this.numeroGiocatoriMP > 2 && GiocoDellOcaGUI.this.numeroGiocatoreCorrente < GiocoDellOcaGUI.this.numeroGiocatoriMP) 
						{
							var numeroGiocatore = GiocoDellOcaGUI.this.numeroGiocatoreCorrente + 1;
							btnConfiguraProssimoUtenteFromSelDado.setText("Configura Giocatore " + numeroGiocatore);
							
							selezioneDadiPanel.remove(btnAvviaPartitaFromSelDado);
							selezioneDadiPanel.remove(btnConfiguraUtentiOspitiFromSelDado);
							selezioneDadiPanel.add(btnConfiguraProssimoUtenteFromSelDado);
							selezioneDadiPanel.revalidate(); 
							selezioneDadiPanel.repaint(); 
						}
						else {
							selezioneDadiPanel.remove(btnConfiguraProssimoUtenteFromSelDado);
							selezioneDadiPanel.remove(btnConfiguraUtentiOspitiFromSelDado);
							selezioneDadiPanel.add(btnAvviaPartitaFromSelDado);
							selezioneDadiPanel.revalidate(); 
							selezioneDadiPanel.repaint(); 
						}
					}
				}
				SwitchToPanel(layeredPane, selezioneDadiPanel);
			}
		});
		

		btnSelezionePersonalizzazioniFromSelPed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
			}
		});
		

		btnSelezionePersonalizzazioniFromSelDadi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
			}
		});
		
		ActionListener avviaPartitaListener = e -> {
		    boolean isMultiplayer = GiocoDellOcaGUI.this.isPartitaMultiplayer;

		    if (tablePedinaSelezionataModel.getRowCount() > 0) {
		        String codicePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 0);
		        GiocoDellOcaGUI.this.giocoDellOca.impostaPedinaGiocatore(
		            codicePedina,
		            GiocoDellOcaGUI.this.numeroGiocatoreCorrente,
		            isMultiplayer
		        );
		    } else {
		        GiocoDellOcaGUI.this.giocoDellOca.impostaPedinaGiocatore(null, GiocoDellOcaGUI.this.numeroGiocatoreCorrente, isMultiplayer);
		    }

		    if (tableDadoSelezionatoModel.getRowCount() > 0) {
		        String codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
		        GiocoDellOcaGUI.this.giocoDellOca.impostaDadoGiocatore(
		            codiceDado,
		            GiocoDellOcaGUI.this.numeroGiocatoreCorrente,
		            isMultiplayer
		        );
		    } else {
		        GiocoDellOcaGUI.this.giocoDellOca.impostaDadoGiocatore(null, GiocoDellOcaGUI.this.numeroGiocatoreCorrente, isMultiplayer);
		    }

		    GiocoDellOcaGUI.this.giocoDellOca.avviaPartita();
		    inizializzaTabellone();
		};


		btnAvviaPartitaFromSelPedina.addActionListener(avviaPartitaListener);
		btnAvviaPartitaFromSelDado.addActionListener(avviaPartitaListener);
	
		btnReturnToMenuFromSelTipReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		
		btnReturnToMenuFromSelRegSing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		
		btnReturnToMenuFromSelRegSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		
		btnReturnToMenuFromSelScen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		
		btnReturnToMenuFromSelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		

		btnReturnToMenuFromSelPedina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		

		btnReturnToMenuFromSelDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		
		btnReturnToMenuFromGestioneImpostazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenuActions();
			}
		});
		
		tableRegoleSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableRegoleSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {								
						String codiceRegola = (String) tableRegoleSelezionabiliModel.getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionabiliModel.getValueAt(row, 1);					
						tableRegoleSelezionabiliModel.removeRow(row);
						tableRegoleSelezionateModel.addRow(new Object[] {codiceRegola, descrizioneRegola});						
					}
                }
			}
		});
		
		tableRegoleSelezionate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableRegoleSelezionate.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 0);
						String descrizioneRegola = (String) tableRegoleSelezionateModel.getValueAt(row, 1);
						tableRegoleSelezionateModel.removeRow(row);
						tableRegoleSelezionabiliModel.addRow(new Object[] {codiceRegola, descrizioneRegola});
					}
                }
			}
		});
		
		tableScenariSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableScenarioSelezionato.getRowCount() == 0) {
	            	int row = tableScenariSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceScenario= (String) tableScenariSelezionabiliModel.getValueAt(row, 0);
						String descrizioneScenario = (String) tableScenariSelezionabiliModel.getValueAt(row, 1);
						tableScenariSelezionabiliModel.removeRow(row);
						tableScenarioSelezionatoModel.addRow(new Object[] {codiceScenario, descrizioneScenario});
					}
                }
			}
		});
		
		tableScenarioSelezionato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableScenarioSelezionato.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceScenario= (String) tableScenarioSelezionatoModel.getValueAt(row, 0);
						String descrizioneScenario = (String) tableScenarioSelezionatoModel.getValueAt(row, 1);
						tableScenarioSelezionatoModel.removeRow(row);
						tableScenariSelezionabiliModel.addRow(new Object[] {codiceScenario, descrizioneScenario});
					}
                }
			}
		});
		
		tableRegoleSetSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableRegoleSetSelezionato.getRowCount() == 0) {
	            	int row = tableRegoleSetSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						var codiceRegoleSet = (String) tableRegoleSetSelezionabiliModel.getValueAt(row, 0);
						var listaRegoleSet = mapRegoleSet.get(codiceRegoleSet);
						
						tableRegoleSetSelezionabiliModel.removeRow(row);
						
						for (var regola: listaRegoleSet) {						
							tableRegoleSetSelezionatoModel.addRow(new Object[] {regola.getCodiceRegola(), regola.getDescrizione(), regola.getProprietaRegola()});						
						}

					}
                }
			}
		});
		
		tableRegoleSetSelezionato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {          	
                	var rowCount = tableRegoleSetSelezionato.getRowCount();
                    var regoleSelezionate = new LinkedHashSet<Regola>(); 
                    
                	for (int row = 0; row < rowCount; row++){                		
                		var codice = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 0);
                		var descrizione = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 1);
                		var proprieta = (String) tableRegoleSetSelezionatoModel.getValueAt(row, 2);
                		
                    	TipologiaRegolaEnum tipologiaRegola = null;
                		
                    	outerLoop:
                		for (var entry : mapRegoleSet.entrySet()) {
                            for (var value :  entry.getValue()) {
                           	  	if (value.getCodiceRegola().equals(codice)) {
                           	  		tipologiaRegola = value.getTipologiaRegola();
                           	  		break outerLoop;
                           	  	}
                          	  }                        	                         
                		}
                		
                		var regola = new Regola(codice, descrizione, proprieta, tipologiaRegola);

                		regoleSelezionate.add(regola);      		
            		}
                	
                	tableRegoleSetSelezionatoModel.setRowCount(0);		
                	
                	String codiceRegoleSet = null;
                	
                	 for (var entry : mapRegoleSet.entrySet()) {
                         if (entry.getValue().containsAll(regoleSelezionate))
                        	 codiceRegoleSet = entry.getKey();                         
                        }
							
                	 tableRegoleSetSelezionabiliModel.addRow(new Object[] {codiceRegoleSet});						
						
				}
                
			}
		});
		
		tablePedineSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tablePedinaSelezionata.getRowCount() == 0) {
	            	int row = tablePedineSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codicePedina= (String) tablePedineSelezionabiliModel.getValueAt(row, 0);
						String descrizionePedina = (String) tablePedineSelezionabiliModel.getValueAt(row, 1);
						tablePedineSelezionabiliModel.removeRow(row);
						tablePedinaSelezionataModel.addRow(new Object[] {codicePedina, descrizionePedina});
					}
                }
			}
		});
		
		tablePedinaSelezionata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tablePedinaSelezionata.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codicePedina= (String) tablePedinaSelezionataModel.getValueAt(row, 0);
						String descrizionePedina = (String) tablePedinaSelezionataModel.getValueAt(row, 1);
						tablePedinaSelezionataModel.removeRow(row);
						tablePedineSelezionabiliModel.addRow(new Object[] {codicePedina, descrizionePedina});
					}
                }
			}
		});
		
		tableDadiSelezionabili.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableDadoSelezionato.getRowCount() == 0) {
	            	int row = tableDadiSelezionabili.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceDado= (String) tableDadiSelezionabiliModel.getValueAt(row, 0);
						String descrizioneDado = (String) tableDadiSelezionabiliModel.getValueAt(row, 1);
						tableDadiSelezionabiliModel.removeRow(row);
						tableDadoSelezionatoModel.addRow(new Object[] {codiceDado, descrizioneDado});
					}
                }
			}
		});
		
		tableDadoSelezionato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
	            	int row = tableDadoSelezionato.rowAtPoint(e.getPoint());
					if(row >= 0) {		
						String codiceDado= (String) tableDadoSelezionatoModel.getValueAt(row, 0);
						String descrizioneDado = (String) tableDadoSelezionatoModel.getValueAt(row, 1);
						tableDadoSelezionatoModel.removeRow(row);
						tableDadiSelezionabiliModel.addRow(new Object[] {codiceDado, descrizioneDado});
					}
                }
			}
		});
		
		btnConfiguraUtentiOspitiFromSelDado.addActionListener(e -> {
		    configuraUtenteDaSelezione(
		        tablePedinaSelezionataModel,
		        tableDadoSelezionatoModel,
		        tablePedineSelezionabiliModel,
		        tableDadiSelezionabiliModel,
		        listPersonalizzazioni,
		        selezioneNumeroGiocatoriPanel
		    );
		});
		
		btnConfiguraUtentiOspitiFromSelPedina.addActionListener(e -> {
		    configuraUtenteDaSelezione(
		        tablePedinaSelezionataModel,
		        tableDadoSelezionatoModel,
		        tablePedineSelezionabiliModel,
		        tableDadiSelezionabiliModel,
		        listPersonalizzazioni,
		        selezioneNumeroGiocatoriPanel
		    );
		});
		
		ActionListener setupMultiplayer = e -> {
	        int numeroGiocatori = Integer.parseInt(((ButtonCustom) e.getSource()).getActionCommand());
	        GiocoDellOcaGUI.this.numeroGiocatoreCorrente = 2;
	        GiocoDellOcaGUI.this.numeroGiocatoriMP = numeroGiocatori;
	        GiocoDellOcaGUI.this.giocoDellOca.aggiungiGiocatoriOspiti(numeroGiocatori);

	        SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
	        lblGiocatoreCorrente.setText("Giocatore 2");
	        lblGiocatoreCorrente.setVisible(true);
	        btnReturnToSelScenFromSelPers.setVisible(false);
	    };

	    btn2Players.setActionCommand("2");
	    btn3Players.setActionCommand("3");
	    btn4Players.setActionCommand("4");
	    
		btn2Players.addActionListener(setupMultiplayer);		
		btn3Players.addActionListener(setupMultiplayer);		
		btn4Players.addActionListener(setupMultiplayer);
		
		ActionListener configuraProssimoGiocatoreListener = e -> {
		    var gioco = GiocoDellOcaGUI.this.giocoDellOca;
		
		    gioco.configuraGiocatoreCorrente(
		        GiocoDellOcaGUI.this.numeroGiocatoreCorrente,
		        tablePedinaSelezionataModel,
		        tableDadoSelezionatoModel,
		        listPersonalizzazioni
		    );
		
		    GiocoDellOcaGUI.this.numeroGiocatoreCorrente++;
		    SwitchToPanel(layeredPane, selezioneTipologiaPersonalizzazionePanel);
		    lblGiocatoreCorrente.setText("Giocatore " + GiocoDellOcaGUI.this.numeroGiocatoreCorrente);
		
		    tablePedinaSelezionataModel.setRowCount(0);
		    tableDadoSelezionatoModel.setRowCount(0);
		    tablePedineSelezionabiliModel.setRowCount(0);
		    tableDadiSelezionabiliModel.setRowCount(0);
		
		    var codicePedinaDefault = gioco.getCodiciPedineGiocatoriDefault()
		            .get(GiocoDellOcaGUI.this.numeroGiocatoreCorrente);
		    var codiceDadoDefault = gioco.getCodiciDadiGiocatoriDefault()
		            .get(GiocoDellOcaGUI.this.numeroGiocatoreCorrente);
		
		    for (var personalizzazione : listPersonalizzazioni) {
		        if (personalizzazione instanceof Pedina p) {
		            if (codicePedinaDefault != null && p.getCodicePersonalizzazione().equals(codicePedinaDefault))
		                tablePedinaSelezionataModel.addRow(new Object[]{p.getCodicePersonalizzazione(), p.getDescrizione()});
		            else
		                tablePedineSelezionabiliModel.addRow(new Object[]{p.getCodicePersonalizzazione(), p.getDescrizione()});
		        }
		    }
		
		    for (var personalizzazione : listPersonalizzazioni) {
		        if (personalizzazione instanceof Dado d) {
		            if (codiceDadoDefault != null && d.getCodicePersonalizzazione().equals(codiceDadoDefault))
		                tableDadoSelezionatoModel.addRow(new Object[]{d.getCodicePersonalizzazione(), d.getDescrizione()});
		            else
		                tableDadiSelezionabiliModel.addRow(new Object[]{d.getCodicePersonalizzazione(), d.getDescrizione()});
		        }
		    }
		};
		
		btnConfiguraProssimoUtenteFromSelDado.addActionListener(configuraProssimoGiocatoreListener);
		btnConfiguraProssimoUtenteFromSelPedina.addActionListener(configuraProssimoGiocatoreListener);

		btnGestisciImpostazioni.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		
		        refreshImpostazioniDropdown(); 
		
		        var codiciDadiGiocatoriDefault = giocoDellOca.getCodiciDadiGiocatoriDefault();
		        var codiciPedineGiocatoriDefault = giocoDellOca.getCodiciPedineGiocatoriDefault();
		        var nomiGiocatoriDefault = giocoDellOca.getNomiGiocatoriDefault();
		
		        selezionaComboPerCodici(dadoDropdown, codiciDadiGiocatoriDefault);
		        selezionaComboPerCodici(pedinaDropdown, codiciPedineGiocatoriDefault);
		
		        setTextFieldsDaMappa(gestioneImpostazioniNomeField, nomiGiocatoriDefault);
		
		        String codiceScenarioSalvato = giocoDellOca.getCodiceScenarioDefault();
		        if (codiceScenarioSalvato != null) {
		            selectComboByCodice(scenarioDropdown, codiceScenarioSalvato);
		        }
		
		        String codiceRegoleSetSalvato = giocoDellOca.getCodiceRegoleSetDefault();
		        if (codiceRegoleSetSalvato != null) {
		            selectComboByCodice(regoleSetDropdown, codiceRegoleSetSalvato);
		        }
		
		        SwitchToPanel(layeredPane, gestioneImpostazioniMainPanel);
		    }
		});
		
		salvaImpostazioniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome1 = gestioneImpostazioniNomeField[0].getText().trim();
				String nome2 = gestioneImpostazioniNomeField[1].getText().trim();
				String nome3 = gestioneImpostazioniNomeField[2].getText().trim();
				String nome4 = gestioneImpostazioniNomeField[3].getText().trim();
				
				ComboItem regoleSetItem = (ComboItem) regoleSetDropdown.getSelectedItem();
		        ComboItem scenarioItem = (ComboItem) scenarioDropdown.getSelectedItem();
		        String codiceRegoleSet = regoleSetItem != null && !regoleSetItem.getCodice().equals("0") ? regoleSetItem.getCodice() : null;
		        String codiceScenario = scenarioItem != null && !scenarioItem.getCodice().equals("0") ? scenarioItem.getCodice() : null;
		        
				ComboItem dadoGiocatore1Item = (ComboItem) dadoDropdown[0].getSelectedItem();
				ComboItem dadoGiocatore2Item = (ComboItem) dadoDropdown[1].getSelectedItem();
				ComboItem dadoGiocatore3Item = (ComboItem) dadoDropdown[2].getSelectedItem();
				ComboItem dadoGiocatore4Item = (ComboItem) dadoDropdown[3].getSelectedItem();
		        String codiceDadoGiocatore1 = dadoGiocatore1Item != null && !dadoGiocatore1Item.getCodice().equals("0") ? dadoGiocatore1Item.getCodice() : null;
		        String codiceDadoGiocatore2 = dadoGiocatore2Item != null && !dadoGiocatore2Item.getCodice().equals("0") ? dadoGiocatore2Item.getCodice() : null;
		        String codiceDadoGiocatore3 = dadoGiocatore3Item != null && !dadoGiocatore3Item.getCodice().equals("0") ? dadoGiocatore3Item.getCodice() : null;
		        String codiceDadoGiocatore4 = dadoGiocatore4Item != null && !dadoGiocatore4Item.getCodice().equals("0")? dadoGiocatore4Item.getCodice() : null;
				
				ComboItem pedinaGiocatore1Item = (ComboItem) pedinaDropdown[0].getSelectedItem();
				ComboItem pedinaGiocatore2Item = (ComboItem) pedinaDropdown[1].getSelectedItem();
				ComboItem pedinaGiocatore3Item = (ComboItem) pedinaDropdown[2].getSelectedItem();
				ComboItem pedinaGiocatore4Item = (ComboItem) pedinaDropdown[3].getSelectedItem();
				String codicePedinaGiocatore1 = pedinaGiocatore1Item != null && !pedinaGiocatore1Item.getCodice().equals("0") ? pedinaGiocatore1Item.getCodice() : null;
		        String codicePedinaGiocatore2 = pedinaGiocatore2Item != null && !pedinaGiocatore2Item.getCodice().equals("0") ? pedinaGiocatore2Item.getCodice() : null;
		        String codicePedinaGiocatore3 = pedinaGiocatore3Item != null && !pedinaGiocatore3Item.getCodice().equals("0") ? pedinaGiocatore3Item.getCodice() : null;
		        String codicePedinaGiocatore4 = pedinaGiocatore4Item != null && !pedinaGiocatore4Item.getCodice().equals("0") ? pedinaGiocatore4Item.getCodice() : null;

		        GiocoDellOcaGUI.this.giocoDellOca.salvaImpostazioni(
		                nome1, nome2, nome3, nome4,
		                codiceRegoleSet,
		                codiceScenario,
		                codiceDadoGiocatore1, codiceDadoGiocatore2, codiceDadoGiocatore3, codiceDadoGiocatore4,
		                codicePedinaGiocatore1, codicePedinaGiocatore2, codicePedinaGiocatore3, codicePedinaGiocatore4
		            );
		        
		        JOptionPane.showMessageDialog(null, "Impostazioni salvate correttamente!");


			}
		});
		
	}
}
