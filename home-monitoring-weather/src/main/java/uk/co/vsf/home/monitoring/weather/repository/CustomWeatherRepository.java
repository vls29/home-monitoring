package uk.co.vsf.home.monitoring.weather.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import uk.co.vsf.home.monitoring.weather.domain.WeatherData;

public interface CustomWeatherRepository {
	
	/**
	 * Saves a given entity.  If the entity already exists, will fail to update.
	 * 
	 * @param entity
	 */
	void persist(WeatherData data);

	/**
	 * Find a record by exact date and time
	 * 
	 * @param dateTime
	 *            of the recording
	 * @return weather data instace if one exists for the relevant time.
	 */
	WeatherData findByDateTime(LocalDateTime dateTime) throws NoResultException;

	/**
	 * Will return a list of WeatherData for the chosen date. Data returned will
	 * be in the order date, time.
	 * 
	 * @param date
	 *            to find entries for
	 * @return data if any found in order of date, time
	 */
	List<WeatherData> findByDate(LocalDate date);
}
