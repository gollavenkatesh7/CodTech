import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

/**
 * A Java application that fetches weather data from a public REST API
 * and displays the data in a structured format.
 */
public class WeatherAPIClient {
    
    private static final String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=37.7749&longitude=-122.4194&current_weather=true";
    
    public static void main(String[] args) {
        try {
            String response = fetchWeatherData();
            parseAndDisplayWeather(response);
        } catch (IOException e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
    
    /**
     * Fetches weather data from the public REST API.
     * @return JSON response as a String.
     * @throws IOException if an error occurs during the HTTP request.
     */
    public static String fetchWeatherData() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("HTTP response code: " + responseCode);
        }
        
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        
        return response.toString();
    }
    
    /**
     * Parses and displays the weather data in a structured format.
     * @param response The JSON response from the API.
     */
    public static void parseAndDisplayWeather(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONObject currentWeather = jsonObject.getJSONObject("current_weather");
        
        System.out.println("Weather Data:");
        System.out.println("Temperature: " + currentWeather.getDouble("temperature") + "°C");
        System.out.println("Wind Speed: " + currentWeather.getDouble("windspeed") + " km/h");
        System.out.println("Weather Code: " + currentWeather.getInt("weathercode"));
    }
}
