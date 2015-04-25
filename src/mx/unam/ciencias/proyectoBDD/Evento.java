package mx.unam.ciencias.proyectoBDD;
public class Evento{
	private int dia;    
	private int mes;   
	private int anio;  
	private String nombre;
	private String lugar; 

	/**
	* Constructor del evento
	* @param dia el día del evento
	* @param mes  el mes del evento
	* @param anio el año del evento
	* @param nombre el nombre del evento
	* @param lugar el lugar donde es el evento
	*/
	public Evento(int dia,int mes,int anio,String nombre,String lugar){
		this.dia = dia;  
		this.mes = mes;  
		this.anio = anio; 
		this.nombre = nombre;
		this.lugar = lugar;
	}

	/**
	* @return int el día del evento
	*/
	public int getDia(){
		return dia;
	}

	/**
	* @return int el mes del evento
	*/
	public int getMes(){
		return mes;
	}

	/**
	* @return int el año del evento
	*/
	public int getAnio(){
		return anio;
	}

	/**
	* @return String el nombre del evento
	*/
	public String getNombre(){
		return nombre;
	}

	/**
	* @return String el lugar del evento
	*/
	public String getLugar(){
		return lugar;
	}

}