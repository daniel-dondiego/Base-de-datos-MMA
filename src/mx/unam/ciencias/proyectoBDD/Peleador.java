package mx.unam.ciencias.proyectoBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
* Clase que modela un peleador
*/
public class Peleador{
	private int id;
	private String nombre ="";
	private	String apodo ="";
	private String apellido="";
	private String nacionalidad="";
	private	int edad;
	private	String division;
	private int ganadas; 
	private int perdidas;
	private int empatadas;
	private String urlImagen ="";
	private VerificaPalabraReservada vPR = new VerificaPalabraReservada();

	/**
	* Constructor para todos los campos no nulos
	*@param nombre el nombre de el peleador
	*@param apodo el apodo de el peleador
	*@param apellido el apellido de el peleador
	*@param nacionalidad la nacionalidad de el peleador
	*@param edad    la edad de el peleador
	*@param division la división de el peleador
	*@param ganadas    el número de peleas ganadas de el peleador
	*@param perdidas    el número de peleas perdidas de el peleador
	*@param empatadas    el número de peleas empatadas de el peleador
	*@param urlImagen el nombre de la imagen de el peleador
	*/
	public Peleador(String nombre,String apodo,String apellido,
					String nacionalidad,int edad,String division,
					int ganadas,int perdidas,int empatadas,String urlImagen){
		this.nombre = nombre;
		this.apodo = apodo;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.division = division;
		this.ganadas = ganadas;
		this.perdidas = perdidas;
		this.empatadas = empatadas;
		this.urlImagen = urlImagen;

	}

	/**
	* Constructor para peleadores sin apodo
	*@param nombre el nombre de el peleador
	*@param apellido el apellido de el peleador
	*@param nacionalidad la nacionalidad de el peleador
	*@param edad    la edad de el peleador
	*@param division la división de el peleador
	*@param ganadas    el número de peleas ganadas de el peleador
	*@param perdidas    el número de peleas perdidas de el peleador
	*@param empatadas    el número de peleas empatadas de el peleador
	*@param urlImagen el nombre de la imagen de el peleador
	*/
	public Peleador(String nombre,String apellido,
					String nacionalidad,int edad,String division,
					int ganadas,int perdidas,int empatadas,String urlImagen){
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
		this.division = division;
		this.ganadas = ganadas;
		this.perdidas = perdidas;
		this.empatadas = empatadas;
		this.urlImagen = urlImagen;

	}
	/**
	* Constructor que no recibe parámetros
	*/
	public Peleador(){

	}
	/**
	* asocia a un peleador una empresa donde pelea
	* @param idPeleador un peleador
	* @param idEmpresa una empresa
	*/
	public void peleaEn(int idPeleador,int idEmpresa){
		
	}



	/**
	* Método que devuelve las divisiones de peso
	* @return divisiones la lista con las divisiones de peso
	*/
	public ObservableList<String> getDivisionesDePeso(){
		ObservableList<String> divisiones = FXCollections.observableArrayList("Flyweights","Bantamweights",
								"Featherweights","Lightweights",
								"Welterweights","Middleweights",
								"Light Heavyweights","Heavyweights");
		return divisiones;
	}

	/**
	* Regresa el id de el peleador
	* @return int el id de el peleador
	*/
	public int getId(){
		return id;
	}

	/**
	* Regresa el nombre de el peleador
	* @return String el nombre de el peleador
	*/
	public String getNombre(){
		return nombre;
	}

	/**
	* Regresa el apodo de el peleador
	* @return String el apodo de el peleador
	*/
	public String getApodo(){
		return apodo;
	}

	/**
	* Regresa el apellido de el peleador
	* @return String el apellido de el peleador
	*/
	public String getApellido(){
		return apellido;
	}

	/**
	* Regresa la nacionalidad de el peleador
	* @return String la nacionalidad de el peleador
	*/
	public String getNacionalidad(){
		return nacionalidad;
	}

	/**
	* Regresa la edad de el peleador
	* @return int la edad de el peleador
	*/
	public int getEdad(){
		return edad;
	}

	/**
	* Regresa la división de peso en la que se encuentra el peleador
	* @return String la división de peso de el peleador
	*/
	public String getDivision(){
		return division;
	}

	/**
	* Regresa el numero de peleas ganadas de el peleador
	* @return int el numero de peleas empatadas de el peleador
	*/
	public int getGanadas(){
		return ganadas;
	}

	/**
	* Regresa el numero de peleas perdidas de el peleador
	* @return int el numero de peleas empatadas de el peleador
	*/
	public int getPerdidas(){
		return perdidas;
	}

	/**
	* Regresa el numero de peleas empatadas de el peleador
	* @return int el numero de peleas empatadas de el peleador
	*/
	public int getEmpatadas(){
		return empatadas;
	}
	
	/**
	* Regresa el nombre de la imagen asociada al peleador
	* @return String el nombre de la imagen asociada al peleador
	*/
	public String getUrlImagen(){
		return urlImagen;
	}

	
}