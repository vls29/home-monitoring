package uk.co.vsf.home.monitoring.weather.repository;

import org.springframework.data.repository.Repository;

import uk.co.vsf.home.monitoring.domain.HomeMonitoringId;
import uk.co.vsf.home.monitoring.weather.domain.WeatherData;

public interface WeatherRepository extends Repository<WeatherData, HomeMonitoringId>, CustomWeatherRepository {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count();
}
