package mx.unam.ciencias.proyectoBDD;
/**
 * Clase para excepciones de palabras reservadas inválidas.
 */
public class ExcepcionPalabraReservada extends Exception{
	private Notificaciones n = new Notificaciones(); 
	/**
	* Constructor que lanza la excepción
	*/
	public ExcepcionPalabraReservada(){
		n.notificacion("VERIFIQUE SUS DATOS SE HA ENCONTRADO PALABRA RESERVADA DE SQLite");
    }

}