

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello world!");
        System.out.println("This is an example!");


        //Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        //Spara värden i JSON Object
        //första är nyckelord och andra är värdet
        jsonOb.put("namn", "Omid");
        jsonOb.put("age", 31);

        //skriva ut värden
        System.out.println("Mitt namn är: " +jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal");

        //-------------------------------------------------------------------

        //Hämta data från Json fil
        Object o = new JSONParser().parse(new FileReader("C:\\Users\\Lenovo\\OneDrive\\Skrivbord\\data.json\\data.json"));

        JSONObject jsonData = (JSONObject) o;
        //Konverera data till  ett jasonObject
        JSONObject person = (JSONObject) jsonData.get("p1");
        JSONObject person2 = (JSONObject) jsonData.get("p2");



        String name2=(String) person2.get("name");
        String country2=(String) person2.get("land");
        String color2=(String) person2.get("color");
        String age2=(String) person2.get("age");

        String name= (String) person.get("name");
        String country= (String) person.get("land");
        String color= (String) person.get("color");
        String age=(String) person.get("age");

        System.out.println("P1 Name :" + name);
        System.out.println(" p1 age:" + age );
        System.out.println("P1 land: " + country);

        System.out.println("P2 name:" + name2);
        System.out.println("P2 age:" + age2);
        System.out.println("p2 land: " + country2);

        fetchJsonFromAPI();

    }

    static <URl> void fetchJsonFromAPI() throws IOException, ParseException {
        //Spara URL till API
        URL url =  new URL("https://api.wheretheiss.at/v1/satellites/25544");
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();

        // Sätta upp HTPPRequest
        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() == 200) System.out.println( "koppling lyckades");
        else System.out.println(" Koppling är misslyckas");

//skapa StrBuilder och Scan object
        StringBuilder strData = new StringBuilder();
        Scanner scanner=new Scanner(url.openStream());

        // Bygger upp str med ISS data
        while (scanner.hasNext()){
            strData.append(scanner.nextLine());
        }
        scanner.close(); //Stänger kopplingen
        System.out.println(strData);

        //Skapar JSONObject av fetched data
        JSONObject dataObject = (JSONObject) new JSONParser().parse(String.valueOf(strData));

        System.out.println("Latitude" + dataObject.get("latitude") + "Höjd äver havet:" + dataObject.get("longitude"));

        /*
        Double latitude = Double.parseDouble(dataObject.get("latitude").toString());
        Double longitude = Double.parseDouble(dataObject.get("longitude").toString());

        System.out.println("The latitude is: " + latitude);
        System.out.println("The longitude is: " + longitude);
*/




    }

}