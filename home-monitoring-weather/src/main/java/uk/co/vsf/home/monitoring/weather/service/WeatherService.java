package uk.co.vsf.home.monitoring.weather.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.vsf.home.monitoring.repository.WeatherRepository;
import uk.co.vsf.home.monitoring.weather.domain.WeatherData;

public class WeatherService {

	private WeatherRepository weatherRepository;
	
	public WeatherService(WeatherRepository weatherRepository) {
		this.weatherRepository = weatherRepository;
	}

	public void save(BigDecimal windSpeed, BigDecimal gustSpeed) {
		weatherRepository.persist(new WeatherData(LocalDateTime.now(), windSpeed, gustSpeed));
	}

}
