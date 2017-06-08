package uk.co.vsf.home.monitoring.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import uk.co.vsf.home.monitoring.domain.HomeMonitoringData;

public interface CustomHomeMonitoringRepository {
	
	/**
	 * Saves a given entity.  If the entity already exists, will update the entity.
	 * 
	 * @param entity
	 */
	void saveOrUpdate(HomeMonitoringData data);

	/**
	 * Find a record by exact date and time
	 * 
	 * @param dateTime
	 *            of the recording
	 * @return weather data instace if one exists for the relevant time.
	 */
	HomeMonitoringData findByDateTime(LocalDateTime dateTime) throws NoResultException;

	/**
	 * Will return a list of HomeMonitoringData for the chosen date. Data returned will
	 * be in the order date, time.
	 * 
	 * @param date
	 *            to find entries for
	 * @return data if any found in order of date, time
	 */
	List<HomeMonitoringData> findByDate(LocalDate date);
}
