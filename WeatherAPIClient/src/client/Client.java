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


/**
 *
 * @author N1007475
 */
public class Client {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String url = "http://localhost:8080/OrchestratorService/webresources/weather"; // Example API endpoint
        try {
            // Create a URL object from the string
            URL obj = new URL(url);
            
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            
            // Set the request method to GET
            connection.setRequestMethod("GET");
            

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    response.append("\n");
                }
                in.close();

                // Print the response
                System.out.println("Response: " + response.toString());
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
