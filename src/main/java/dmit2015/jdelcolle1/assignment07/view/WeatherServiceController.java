package dmit2015.jdelcolle1.assignment07.view;

import dmit2015.jdelcolle1.assignment07.model.OpenWeatherData;
import dmit2015.jdelcolle1.assignment07.model.WeatherService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

@Named
public class WeatherServiceController {

    @Inject
    @RestClient
    private WeatherService _weatherService;

    @Inject
    @ConfigProperty(name="api.openweathermap.org.ApiKey")
    private String _weatherApiKey;

    @Inject
    @ConfigProperty(name="dmit2015.jdelcolle1.assignment07.units")
    private String _units;

    @Inject
    @ConfigProperty(name="dmit2015.jdelcolle1.assignment07.cityname")
    @Getter
    private String cityName;

    @Getter
    private OpenWeatherData weatherData;

    @PostConstruct
    void init() {
        try {
            weatherData = _weatherService.findByCityName(
                    cityName, _weatherApiKey, _units);
        } catch (Exception ex) {
            ex.printStackTrace();
            Messages.addGlobalError("Error fetching weather data with exception {0}", ex.getMessage());
        }
    }
}
