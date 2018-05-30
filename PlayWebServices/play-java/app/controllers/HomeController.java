package controllers;
import play.mvc.*;
import views.html.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.api.libs.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Este controlador contiene una acción para gestionar solicitudes HTTP en la página principal de la aplicación.
 * @author Raúl González Cruz    <raul.gonzalezcz@udlap.mx> ID: 151211
          Misael Cabrera Aguilar <misael.cabreraar@udlap.mx> ID: 150916
 * @version 0.1
 * @since 2017-04-16
 */
public class HomeController extends Controller {


/* El siguiente método solamente despliega un mensaje dentro del template index. Es decir, 
una acción genera una página HTML con un mensaje de bienvenida.*/
    public Result index() {
        return ok(index.render("Hola mundo, servicios web!!"));
    }

/*
* El siguiente método hace una multiplicación de enteros.
* @param a el primer  entero.
* @param b el segundo entero.
* @return Resultado en formato Json.
*/
public Result getMultiplicacion(int a, int b){
  ObjectNode result = Json.newObject();
  result.put("resultado", a*b );
  return ok(result);
}

/*
* El siguiente método verifica si una cadena es un palíndromo.
* @param word la palabra a validar.
* @return true o false en formato Json.
*/
public Result getPalindromo(String word){
  ObjectNode result = Json.newObject();
  String myWord = word.replaceAll("\\s+","");
  String reverse = new StringBuffer(myWord).reverse().toString();
  result.put("resultado", reverse.equalsIgnoreCase(myWord));
  return ok(result);
}

/*
* El siguiente método convierte una cantidad de dólares en pesos mexicanos.
* @param money la cantidad en dólares
* @return la cantidad en pesos mexicanos en formato Json.
*/
public Result getPesosMX(double money){
  ObjectNode result = Json.newObject();
  result.put("resultado", money*18.67);
  return ok(result);
}

/*
* El siguiente método convierte la temperatura de grados Fahrenheit a grados Celsius. 
* @param grados Fahrenheit
* @return grados Celsius en formato Json.
*/
public Result getCelsius(double grados){
  ObjectNode result = Json.newObject();
  result.put("resultado", ((grados - 32) * 5)/9);
  return ok(result);
}

/*
* El siguiente método regresa la capital dado un país europeo.
* @param country el país europeo.
* @return la capital del país en formato Json.
*/
public Result getCapital(String country){
  ObjectNode result = Json.newObject();
//Archivo CSV que contiene las capitales de los países europeos
   String csvFile = "/home/raul/Escritorio/Play/play-java-webservices/dataExtra/Paises&Embajadas.csv";
        String line = "";
        String cvsSplitBy = ","; //Separador entre las cadenas de texto de una misma línea

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	//Leemos línea por línea del archivo...
            while ((line = br.readLine()) != null) {
                // Usamos la coma como separador
                String[] countryL = line.split(cvsSplitBy);
		if(countryL[0].equalsIgnoreCase(country))
			//Hemos encontrado el país europeo
                	result.put("resultado", countryL[3]);

            }
	//ocurrió un error al leer una línea
        } catch (IOException e) {
            e.printStackTrace();
        }//En caso de que el país dado no haya sido escrito correctamente...
	if(result.toString().equals(""))
		result.put("resultado", "No existe el país dado. Revisa que lo hayas escrito correctamente");	
  return ok(result);
}

/*
* El siguiente método regresa la ubicación de la embajada de México en el país europeo.
* @param country el país europeo.
* @return la ubicación de la embajada de México (latitud, longitud) en formato Json.
*/
public Result getUbicacion(String country){
  ObjectNode result = Json.newObject();
//Archivo CSV que contiene la ubicación de la embajada de México en los países europeos
   String csvFile = "/home/raul/Escritorio/Play/play-java-webservices/dataExtra/Paises&Embajadas.csv";
        String line = "";
        String cvsSplitBy = ","; //Separador entre las cadenas de texto de una misma línea

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	//Leemos línea por línea del archivo...
            while ((line = br.readLine()) != null) {
                // Usamos la coma como separador
                String[] countryL = line.split(cvsSplitBy);
		if(countryL[0].equalsIgnoreCase(country)){
		//Hemos encontrado el país europeo, recuperamos latitud y longitud
		String latitud = countryL[1].substring(1);
		String longitud = countryL[2].substring(0,countryL[2].length()-1);	
		String localizacion = latitud + ", " + longitud;
                	result.put("localización", localizacion);
		}
            }
	//ocurrió un error al leer una línea
        } catch (IOException e) {
            e.printStackTrace();
        }	
  return ok(result);
}

}
