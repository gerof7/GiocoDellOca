package GiocoDellOca;
/**
*
* @author Francesco
*/
public class Giocatore {
	
	private String nomeGiocatore;
	private int idGiocatore;
	
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
	
}
