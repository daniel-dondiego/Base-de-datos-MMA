package mx.unam.ciencias.proyectoBDD;

/*
* Clase para verificar que los strings a agregar no sean palabras de sqlite
*/
public class VerificaPalabraReservada{
	private String[] palabrasReservadasSQLite = {"ABORT","ATTACH","CLUSTER","CONFLICT",
		"DEFERRED","DEFERRABLE","DETACH","EACH","EXCEPT","FAIL","GLOB",
		"IMMEDIATE","INITIALLY","INSTEAD","INTERSECT","ISNULL","NOTNULL",
		"OF","PRAGMA","RAISE","STATEMENT","TEMP","TRIGGER","VACUUM","VIEW",
		"SELECT","DELETE","INSERT","UPDATE","CREATE","ALTER","DROP"};

	public VerificaPalabraReservada(){
	}

	/**
	* nos permite ver si el contenido no es una palabra reservada de SQLite
	* @param contenido el contenido a analizar
	* @return boolean <tt>true</tt> si el string es válido,
    *         <tt>false</tt> en otro caso.
	*/
	public boolean contenidoValido(String contenido){
		for(String s : palabrasReservadasSQLite){
			if(contenido.equals(s)){
				return false;
			}
		}
		return true;
	}
	/**
	* devuelve el arreglo con las palabras reservadas de SQLite
	* @return String[] el arreglo de palabras reservadas
	*/
	public String[] getPalabrasReservadasSQLite(){
		return palabrasReservadasSQLite;
	}
}