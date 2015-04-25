package mx.unam.ciencias.proyectoBDD;
public class Empresa{
	private int id;
	private String nombre;
	private String descripcion;
	private int fundadaEn;
	private String sitioWeb;
	private String urlImagen;

	/**
	* Constructor único
	* @param nombre el nombre de la empresa 
	* @param descripcion una descripción de la empresa
	* @param fundadaEn el año en el que fue fundada
	* @param sitioWeb  una dirección web de la empresa
	* @param urlImagen el nombre de la imágen
	*/
	public Empresa(String nombre,String descripcion,int fundadaEn,String sitioWeb,String urlImagen){
		this.nombre      = nombre;
		this.descripcion = descripcion;
		this.fundadaEn   = fundadaEn;
		this.sitioWeb    = sitioWeb;
		this.urlImagen   = urlImagen;

	}

	/**
	* @return String el nombre de la empresa
	*/
	public String getNombre(){
		return nombre;
	}

	/**
	* @return String la descripción de la empresa
	*/
	public String getDescripcion(){
		return descripcion;
	}

	/**
	* @return int el año en que la empresa fue fundada
	*/
	public int getAnioF(){
		return fundadaEn;
	}

	/**
	* @return String la dirección del sitio web de la empresa
	*/
	public String getSitioWeb(){
		return sitioWeb;
	}

	/**
	* @return String el nombre de la imagen
	*/
	public String getUrlImagen(){
		return urlImagen;
	}
}