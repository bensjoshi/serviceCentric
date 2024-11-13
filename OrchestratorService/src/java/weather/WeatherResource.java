/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author bensj
 */
@Path("weather")
public class WeatherResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WeatherResource
     */
    public WeatherResource() {
    }

    /**
     * Retrieves representation of an instance of weather.WeatherResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("lon", "-1.15");
        parameters.put("lat", "52.95");
        parameters.put("lang", "en");
        parameters.put("unit", "metric");
        parameters.put("output", "json");

        // Convert parameters to String
        String convertedParamsToString = parameters.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())  // Correct lambda expression
            .collect(Collectors.joining("&"));

        try {
            // Remove the extra space before the URL
            URL url = new URL("https://www.7timer.info/bin/civillight.php?" + convertedParamsToString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
        
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder(); // StringBuilder is more efficient for concatenation
            
            while ((line = in.readLine()) != null) {
                response.append(line).append("\r\n");  // Append each line to the response
            }
            in.close();  // Don't forget to close the BufferedReader
            
            return(response.toString());  // Print the full response
            
            
        } catch (IOException e) {
            return("Error");  // Handle any IOException
        }
    }

    /**
     * PUT method for updating or creating an instance of WeatherResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
