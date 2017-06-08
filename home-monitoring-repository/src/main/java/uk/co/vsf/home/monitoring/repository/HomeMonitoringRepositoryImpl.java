package uk.co.vsf.home.monitoring.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import uk.co.vsf.home.monitoring.domain.HomeMonitoringData;

@Repository
public class HomeMonitoringRepositoryImpl implements CustomHomeMonitoringRepository {

	private EntityManager entityManager;

	public HomeMonitoringRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public HomeMonitoringData findByDateTime(LocalDateTime dateTime) {
		if (dateTime == null) {
			throw new IllegalArgumentException("Must provide a date-time");
		}

		Query query = entityManager.createQuery("FROM HomeMonitoringData wd WHERE wd.id.date=? and wd.id.time=?");
		query.setParameter(1, formatDate(dateTime.toLocalDate()));
		query.setParameter(2, formatTime(dateTime.toLocalTime()));

		return (HomeMonitoringData) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HomeMonitoringData> findByDate(LocalDate date) throws NoResultException {
		if (date == null) {
			throw new IllegalArgumentException("Must provide a date");
		}

		Query query = entityManager.createQuery("FROM HomeMonitoringData wd WHERE wd.id.date=?");
		query.setParameter(1, formatDate(date));

		return query.getResultList();
	}

	private String formatDate(LocalDate date) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
	}

	private String formatTime(LocalTime time) {
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(time);
	}

	@Override
	public void saveOrUpdate(HomeMonitoringData data) {
		if(data == null ){
			throw new IllegalArgumentException("Must have some data to persist...");
		}
		
		this.entityManager.persist(data);
	}
}
