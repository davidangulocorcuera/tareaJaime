import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends Model {


    public static void main(String[] args) {
        Model app = new Model();
        System.out.println("Escribe 'leerFichero' para leer el fichero , o 'escribirFichero' para escribir en él, o " +
                "'leerbbdd' para mostrar el contenido de la base de datos, o 'escribirbbdd' para realizar una insercion"
        );


        Scanner sc = new Scanner(System.in);
        String opcion = sc.next();
        opcion = opcion.toLowerCase();
        if (opcion.equals("leerfichero") || opcion.equals("escribirfichero") || opcion.equals("salir") ||
                opcion.equals("leerbbdd")|| opcion.equals("escribirbbdd") || opcion.equals("borrarpersona")) {
            switch (opcion) {
                case "escribirfichero":
                    try {
                        sc.nextLine();
                        System.out.println("A continuación escribe lo que deseas que se escriba en el archivo");
                        ArrayList<String> cosas = new ArrayList<String>();
                        System.out.println("escribe tu ID");
                        cosas.add(sc.nextLine());
                        System.out.println("escribe tu nombre");
                        cosas.add(sc.nextLine());
                        System.out.println("escribe tu primera característica");
                        cosas.add(sc.nextLine());
                        System.out.println("escribe tu segunda característica");
                        cosas.add(sc.nextLine());
                        System.out.println("escribe tu tercera característica");
                        cosas.add(sc.nextLine());
                        app.escribirFichero("src/Persona.txt", cosas);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case "leerfichero":
                    try {
                        app.mostrarFichero("src/Persona.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "leerbbdd":
                    app.consultarBBDD();
                    break;
                case "escribirbbdd":
                    sc.nextLine();
                    System.out.println("A continuación escribe lo que deseas que se escriba en el archivo");
                    ArrayList<String> cosas = new ArrayList<String>();
                    System.out.println("escribe tu ID");
                    cosas.add(sc.nextLine());
                    System.out.println("escribe tu nombre");
                    cosas.add(sc.nextLine());
                    System.out.println("escribe tu primera característica");
                    cosas.add(sc.nextLine());
                    System.out.println("escribe tu segunda característica");
                    cosas.add(sc.nextLine());
                    System.out.println("escribe tu tercera característica");
                    cosas.add(sc.nextLine());
                    app.escribirBBDD(cosas);
                    break;
                default:
                    System.out.println("fallo en el sistema , adiós");
                case "salir":
                    break;
                case "borrarpersona":
                    System.out.println("escribe el id de la persona que quieres eliminar");
                    int id =sc.nextInt();
                    app.borrarbbdd(id);
                    break;
            }
        } else {
            System.out.println("inténtalo otra vez que no es tan dificil,recuerda escribe " +
                    "'leer' para leer el archivo , o 'escribir' para escribir en él.");
        }

    }

}
