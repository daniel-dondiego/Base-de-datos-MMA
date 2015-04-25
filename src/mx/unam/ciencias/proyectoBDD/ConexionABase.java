package mx.unam.ciencias.proyectoBDD;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
* Clase que conecta a la base de datos
*/
public class ConexionABase{
	private Connection c = null;
	private Notificaciones n = new Notificaciones();

	/**
	* Constructor que realiza la conexión
	*/
	public ConexionABase(){
	try {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:lib/bdd/MMA.db");
	} catch ( Exception e ) {
	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	System.exit(0);
	}
	System.out.println("Opened database successfully");
	}

	/**
	* regresa la conexión creada
	* @return Connection la conexión a la base de datos
	* @throws SQLException si no se obtiene una conexión
	*/
	public Connection getConexion()throws SQLException{
		return c;
	}

	/**
	* Agrega un nuevo peleador
	* @param p el peleador a ser agregado
	* @return <tt>true</tt> si se agregó el peleador,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean agregaPeleador(Peleador p){
		try{
			Statement st = c.createStatement();
			String s = "INSERT INTO peleador ";
			if(p.getApodo() == null || p.getApodo().equals("")){
				s += String.format("(nombre,apellido,nacionalidad,edad,division,peleas_ganadas,peleas_perdidas,peleas_empatadas,url_imagen) VALUES ('%s','%s','%s','%d','%s','%d','%d','%d','%s');",p.getNombre(),p.getApellido(),p.getNacionalidad(),p.getEdad(),p.getDivision(),p.getGanadas(),p.getPerdidas(),p.getEmpatadas(),p.getUrlImagen());
			}else{
				s += String.format("(nombre,apodo,apellido,nacionalidad,edad,division,peleas_ganadas,peleas_perdidas,peleas_empatadas,url_imagen) VALUES ('%s','%s','%s','%s','%d','%s','%d','%d','%d','%s');",p.getNombre(),p.getApodo(),p.getApellido(),p.getNacionalidad(),p.getEdad(),p.getDivision(),p.getGanadas(),p.getPerdidas(),p.getEmpatadas(),p.getUrlImagen());
			}
			st.executeUpdate(s);
			st.close();
			c.commit();
			n.notificacion(String.format("Se ha agregado a %s %s a la base de datos correctamente",p.getNombre(),p.getApellido()));
		}catch(SQLException e){
			return false;
			}
		return true;
	}

	/**
	* Agrega una empresa
	* @param emp la empresa a ser agregada
	* @return <tt>true</tt> si se agregó la empresa,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean agregarEmpresa(Empresa emp){
		try{
			Statement st = c.createStatement();
			String s = String.format("INSERT INTO empresa (nombre,descripción,fundada_en,sitio_web,url_imagen) VALUES ('%s','%s','%d','%s','%s');",emp.getNombre(),emp.getDescripcion(),emp.getAnioF(),emp.getSitioWeb(),emp.getUrlImagen());
			st.executeUpdate(s);
			st.close();
			c.commit();
		}catch(SQLException e){
			return false;
		}
		return true;
	}

	/**
	* Agrega un nuevo evento
	* @param ev el evento a ser agregado
	* @return <tt>true</tt> si se agregó el evento,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean agregarEvento(Evento ev){
		try{
			Statement st = c.createStatement();
			String s = String.format("INSERT INTO evento (día,mes,anio,nombre,lugar) VALUES ('%d','%d','%d','%s','%s');",ev.getDia(),ev.getMes(),ev.getAnio(),ev.getNombre(),ev.getLugar());
			st.executeUpdate(s);
			st.close();
			c.commit();
		}catch(SQLException e){
			return false;
		}
		return true;
	}

	
	/**
	* busca un peleador por nombre y lo regresa
	* @param nombreP el nombre de el peleador a ser buscado
	* @return peleador el peleador con ese nombre
	* @throws SQLException si no se puede obtener
	*/
	public Peleador getPeleador(String nombreP)throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(String.format("SELECT * FROM peleador WHERE nombre = '%s';",nombreP));
		Peleador peleador;
		String nombre = rs.getString("nombre");
		String apodo = rs.getString("apodo");
		String apellido = rs.getString("apellido");
		String nacionalidad = rs.getString("nacionalidad");
		int edad = rs.getInt("edad");
		String division = rs.getString("division");
		int ganadas = rs.getInt("peleas_ganadas");
		int perdidas = rs.getInt("peleas_perdidas");
		int empatadas = rs.getInt("peleas_empatadas");
		String imagen = rs.getString("url_imagen");
		if(apodo == null || apodo.equals("")){
			peleador = new Peleador(nombre,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
		}else
			peleador = new Peleador(nombre,apodo,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
		return peleador;
	}


	/**
	* regresa los peleadores en la base de datos
	* @return lP todos los peleadores de la base de datos
	* @throws SQLException si no se puede obtener
	*/
	public ArrayList<Peleador> getPeleadores()throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM peleador;");
		ArrayList<Peleador> lP = new ArrayList<Peleador>();
		while(rs.next()){
			Peleador peleador;
			String nombre = rs.getString("nombre");
			String apodo = rs.getString("apodo");
			String apellido = rs.getString("apellido");
			String nacionalidad = rs.getString("nacionalidad");
			int edad = rs.getInt("edad");
			String division = rs.getString("division");
			int ganadas = rs.getInt("peleas_ganadas");
			int perdidas = rs.getInt("peleas_perdidas");
			int empatadas = rs.getInt("peleas_empatadas");
			String imagen = rs.getString("url_imagen");
			if(apodo == null || apodo.equals("")){
				peleador= new Peleador(nombre,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
			}else
				peleador = new Peleador(nombre,apodo,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
			lP.add(peleador);	
		}
		return lP;
	}

	/**
	* regresa las edades de los peleadores sin repetirse
	* @return edades las edades de los peleadores 
	* @throws SQLException si no se puede obtener
	*/
	public ArrayList<Integer> getEdades()throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT edad FROM peleador;");
		ArrayList<Integer> edades = new ArrayList<Integer>();
		while(rs.next()){
			int edad = rs.getInt("edad");
			if(!edades.contains(edad)){
				edades.add(edad);
			}
		}
		return edades;
	}
	/**
	* regresa las divisiones de peso sin repetirse dada una edad
	* @param edadSolicitada la edad con la cual se buscarán las divisiones de peso
	* @return divisiones la lista con las divisiones
	* @throws SQLException si no se puede obtener
	*/
	public ArrayList<String> getDivisiones(int edadSolicitada)throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(String.format("SELECT division FROM peleador WHERE edad = '%d';",edadSolicitada));
		ArrayList<String> divisiones = new ArrayList<String>();
		while(rs.next()){
			String division = rs.getString("division");
			if(!divisiones.contains(division)){
				divisiones.add(division);
			}
		}
		return divisiones;
	}

	/**
	* regresa las divisiones de peso disponibles
	* @return divisiones la lista con todas las divisiones de peso sin repetirse
	* @throws SQLException si no se puede obtener
	*/
	public ArrayList<String> getDivisiones()throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT division FROM peleador;");
		ArrayList<String> divisiones = new ArrayList<String>();
		while(rs.next()){
			String division = rs.getString("division");
			if(!divisiones.contains(division)){
				divisiones.add(division);
			}
		}
		return divisiones;
	}

	/**
	* regresa los peladores con determinada edad y en determinada división
	* @param edadSolicitada la edad solicitada
	* @param divisionSolicitada la division de peso solicitada
	* @return lP la lista con los peleadores buscados por edad y división 
	* @throws SQLException si no se puede obtener
	*/
	public ArrayList<Peleador> filtradoPorEdadDiv(int edadSolicitada,String divisionSolicitada)throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(String.format("SELECT * FROM peleador WHERE edad = '%d' AND division = '%s';",edadSolicitada,divisionSolicitada));
		ArrayList<Peleador> lP = new ArrayList<Peleador>();
				while(rs.next()){
					Peleador peleador;
					String nombre = rs.getString("nombre");
					String apodo = rs.getString("apodo");
					String apellido = rs.getString("apellido");
					String nacionalidad = rs.getString("nacionalidad");
					int edad = rs.getInt("edad");
					String division = rs.getString("division");
					int ganadas = rs.getInt("peleas_ganadas");
					int perdidas = rs.getInt("peleas_perdidas");
					int empatadas = rs.getInt("peleas_empatadas");
					String imagen = rs.getString("url_imagen");
					if(apodo == null || apodo.equals("")){
						peleador= new Peleador(nombre,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
					}else
						peleador = new Peleador(nombre,apodo,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
					lP.add(peleador);	
				}
				return lP;

	}

	/**
	* regresa los peladores con determinada edad 
	* @param edadSolicitada la edad solicitada
	* @return lP la lista con los peleadores buscados por edad y división 
	* @throws SQLException si no se puede obtener
	*/
	public ArrayList<Peleador> filtradoPorEdad(int edadSolicitada)throws SQLException{
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(String.format("SELECT * FROM peleador WHERE edad = '%d';",edadSolicitada));
		ArrayList<Peleador> lP = new ArrayList<Peleador>();
				while(rs.next()){
					Peleador peleador;
					String nombre = rs.getString("nombre");
					String apodo = rs.getString("apodo");
					String apellido = rs.getString("apellido");
					String nacionalidad = rs.getString("nacionalidad");
					int edad = rs.getInt("edad");
					String division = rs.getString("division");
					int ganadas = rs.getInt("peleas_ganadas");
					int perdidas = rs.getInt("peleas_perdidas");
					int empatadas = rs.getInt("peleas_empatadas");
					String imagen = rs.getString("url_imagen");
					if(apodo == null || apodo.equals("")){
						peleador= new Peleador(nombre,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
					}else
						peleador = new Peleador(nombre,apodo,apellido,nacionalidad,edad,division,ganadas,perdidas,empatadas,imagen);
					lP.add(peleador);	
				}
				return lP;

	}

	

	/**
	* cierra la conexión con la base
	* @throws SQLException si no se puede cerrar
	*/
	public void cerrarConexion()throws SQLException{
		c.close();
	}

}