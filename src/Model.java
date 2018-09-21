
import java.io.*;
import java.sql.*;
import java.util.*;

public class Model {
    private Connection conexion = null;

    public Model() {


        HashMap<String, String> config = this.guardarConfig("src/BBDD.ini");
        try {
            conexion = DriverManager.getConnection(config.get("url") + (config.get("dbName"))
                    , (config.get("userName")), (config.get("password")));
            if (!conexion.isClosed())
                System.out.println("Conexion con la bbdd establecida");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void escribirFichero(String Fichero, ArrayList<String> cosas) throws FileNotFoundException, IOException {
        try {

            FileWriter fw = new FileWriter(Fichero, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String elto : cosas) {
                fw.write(elto + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> guardarConfig(String fichero) {
        Properties p = new Properties();
        FileInputStream fl = null;
        try {
            fl = new FileInputStream(fichero);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            p.load(fl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> config = new HashMap<String, String>();
        config.put("url", p.getProperty("url"));
        config.put("dbName", p.getProperty("dbName"));
        config.put("driver", p.getProperty("driver"));
        config.put("userName", p.getProperty("userName"));
        config.put("password", p.getProperty("password"));
        return config;

    }

    public void mostrarFichero(String fichero) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(fichero);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            System.out.println(cadena);
        }
        b.close();
    }
    // Guardamos los datos del fichero en una lista para pasarlo a la bbdd
    public ArrayList<String>  ficheroLista (String fichero,ArrayList<String> cosas) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(fichero);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
           cosas.add(cadena);
        }
        b.close();
        return cosas;
    }


    public void consultarBBDD() {


        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM personas")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  " " + rs.getString("Nombre")+
                        " " + rs.getString("CaracteristicaUno")+  " " + rs.getString("CaracteristicaDos")
                        +  " " + rs.getString("CaracteristicaTres"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void escribirBBDD(ArrayList<String> cosas) {


        try (PreparedStatement stmt = conexion.prepareStatement("INSERT INTO personas(ID,Nombre,CaracteristicaUno,CaracteristicaDos,CaracteristicaTres) VALUES (?,?,?,?,?)")) {
            ResultSet rs = stmt.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
