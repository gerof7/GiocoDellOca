package GiocoDellOca;
/**
*
* @author Francesco
*/
public class Giocatore {
	
	private String nomeGiocatore;
	private int idGiocatore;
	private Pedina pedina;
	private Dado dado;
	
	public Giocatore(String nomeGiocatore, int idGiocatore) {
		
		this.nomeGiocatore = nomeGiocatore;
		this.idGiocatore = idGiocatore;
	}
	
	public String getNome() {
		return nomeGiocatore;
	}
	
	public int getId() {
		return idGiocatore;
	}

	public Pedina getPedina() {
		return pedina;
	}

	public void setPedina(Pedina pedina) {
		this.pedina = pedina;
	}

	public Dado getDado() {
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}
		
}
