package GiocoDellOca;
/**
*
* @author Francesco
*/
public class Giocatore {
	
	private String nomeGiocatore;
	private int numeroGiocatore;
	private Pedina pedina;
	private Dado dado;
	
	public Giocatore(String nomeGiocatore, int numeroGiocatore) {
		
		this.nomeGiocatore = nomeGiocatore;
		this.numeroGiocatore = numeroGiocatore;
	}
	
	public String getNome() {
		return nomeGiocatore;
	}
	
	public int getNumero() {
		return numeroGiocatore;
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
	
	public void setNome(String nome) {
		this.nomeGiocatore = nome;
	}
		
}
