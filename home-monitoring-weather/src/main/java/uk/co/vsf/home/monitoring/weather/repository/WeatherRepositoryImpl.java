package uk.co.vsf.home.monitoring.weather.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import uk.co.vsf.home.monitoring.weather.domain.WeatherData;

@Repository
public class WeatherRepositoryImpl implements CustomWeatherRepository {

	private EntityManager entityManager;

	public WeatherRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public WeatherData findByDateTime(LocalDateTime dateTime) {
		if (dateTime == null) {
			throw new IllegalArgumentException("Must provide a date-time");
		}

		Query query = entityManager.createQuery("FROM WeatherData wd WHERE wd.id.date=? and wd.id.time=?");
		query.setParameter(1, formatDate(dateTime.toLocalDate()));
		query.setParameter(2, formatTime(dateTime.toLocalTime()));

		return (WeatherData) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeatherData> findByDate(LocalDate date) throws NoResultException {
		if (date == null) {
			throw new IllegalArgumentException("Must provide a date");
		}

		Query query = entityManager.createQuery("FROM WeatherData wd WHERE wd.id.date=?");
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
	public void persist(WeatherData data) {
		if(data == null ){
			throw new IllegalArgumentException("Must have some data to persist...");
		}
		
		this.entityManager.persist(data);
	}
}
