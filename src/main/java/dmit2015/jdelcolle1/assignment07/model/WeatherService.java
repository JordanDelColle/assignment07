package dmit2015.jdelcolle1.assignment07.model;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.openweathermap.org/data/2.5/weather")
public interface WeatherService {

    @GET
    OpenWeatherData findByCityName(
            @QueryParam("q") String cityName,
            @QueryParam("appid") String apiKey,
            @QueryParam("units") String units);

    @GET
    OpenWeatherData findByGpsLocation(
            @QueryParam("lat") double latitude,
            @QueryParam("lon") double longitude,
            @QueryParam("appid") String apiKey,
            @QueryParam("units") String units);
}
