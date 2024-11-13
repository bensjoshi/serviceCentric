/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author N1007475
 */
public class Client {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
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
            
            System.out.println(response.toString());  // Print the full response
            
            
        } catch (IOException e) {
            e.printStackTrace();  // Handle any IOException
        }
    }
}