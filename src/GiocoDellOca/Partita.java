package GiocoDellOca;
/**
*
* @author Francesco
*/
public class Partita {
	
	private Impostazioni impostazioni;
	private Tabellone tabellone;
	private Giocatore giocatoreInSessione;
	
	public Partita(Giocatore giocatore) {      
        this.impostazioni = new Impostazioni();
        this.giocatoreInSessione = giocatore;
    }
	
	public Impostazioni getImpostazioni() {
		return impostazioni;
	}

	public Tabellone getTabellone() {
		return tabellone;
	}

	public Giocatore getGiocatoreInSessione() {
		return giocatoreInSessione;
	}

	public void setTabellone(Tabellone tabellone) {
		this.tabellone = tabellone;
	}
	
	public void impostaPartitaSP() {
		var regole = impostazioni.getElencoRegole();
		var scenario = impostazioni.getScenario();
		var personalizzazioni = impostazioni.getElencoPersonalizzazioni();
		var dado = new Dado("Dado_Classico", "Il dado del giocatore è il dado classico", "./src/images/dadoclassico_1.png");
		var pedina = new Pedina("Pedina_Oca", "La pedina del giocatore è un'oca", "./src/images/ScarfGoose.png");
		
		for(var personalizzazione : personalizzazioni){
			if(personalizzazione instanceof Dado)
				dado = (Dado) personalizzazione;
			else if ( personalizzazione instanceof Pedina)
				pedina = (Pedina) personalizzazione;
		}
		
		this.tabellone = new Tabellone(regole, scenario);
		this.giocatoreInSessione.setDado(dado);
		this.giocatoreInSessione.setPedina(pedina);
	}
	
}
