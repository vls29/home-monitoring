package uk.co.vsf.home.monitoring.repository;

import org.springframework.data.repository.Repository;

import uk.co.vsf.home.monitoring.domain.HomeMonitoringData;
import uk.co.vsf.home.monitoring.domain.HomeMonitoringId;

public interface HomeMonitoringRepository extends Repository<HomeMonitoringData, HomeMonitoringId>, CustomHomeMonitoringRepository {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count();
}
