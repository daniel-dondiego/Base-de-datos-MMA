package mx.unam.ciencias.proyectoBDD;
/**
* Clase que modela una pelea
*/
public class Pelea{
	private int idPeleador;
	private int idOponente;
	private int idGanador; 
	private int idEvento;  
	private int round;     
	private String metodo ="";
	private VerificaPalabraReservada vPR = new VerificaPalabraReservada(); 

	/**
	* Constructor que recibe los datos de la pelea
	* @param idPeleador el peleador
	* @param idOponente su oponente
	* @param idGanador el ganador
	* @param idEvento el evento en el que pelearon
	* @param round  el raun en el que ganó
	* @param metodo el metodo que usó
	*/
	public Pelea(int idPeleador,int idOponente,int idGanador,int idEvento,int round,String metodo){

		this.idPeleador = idPeleador;
		this.idOponente = idOponente;
		this.idGanador = idGanador;
		this.idEvento = idEvento; 
		this.round = round;  
		this.metodo = metodo; 
		
	}

	/**
	* Regresa el id de el peleador
	* @return int el id de el peleador
	*/
	public int getIdPeleador(){
		return idPeleador;
	}

	/**
	* Regresa el id de el oponente
	* @return int el id de el oponente
	*/
	public int getIdOponente(){
		return idOponente;
	}

	/**
	* Regresa el id de el ganador
	* @return int el id de el ganador
	*/
	public int getIdGanador(){
		return idGanador;
	}

	/**
	* Regresa el id de el evento 
	* @return int el id de el evento
	*/
	public int getIdEvento(){
		return idEvento;
	}

	/**
	* Regresa el metodo de como fue ganada la pelea
	* @return String el metodo de como fue ganada la pelea
	*/
	public String getMetodo(){
		return metodo;
	}

	
}