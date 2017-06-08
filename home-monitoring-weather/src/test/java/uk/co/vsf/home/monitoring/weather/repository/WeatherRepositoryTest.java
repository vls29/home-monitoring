package uk.co.vsf.home.monitoring.weather.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import uk.co.vsf.home.monitoring.weather.domain.WeatherData;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeatherRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private WeatherRepository repository;

	@Test
	public void insert() throws Exception {
		assertEquals(0L, this.repository.count());
		WeatherData data = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		this.repository.persist(data);
		assertEquals(1L, this.repository.count());
	}

	@Test
	public void insert_NullInput() throws Exception {
		assertEquals(0L, this.repository.count());

		try {
			this.repository.persist(null);
			fail();
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void updateFails() throws Exception {
		assertEquals(0L, this.repository.count());
		WeatherData data = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		this.repository.persist(data);
		assertEquals(1L, this.repository.count());

		WeatherData dataUpdated = new WeatherData(data.getDateTime(), new BigDecimal("20.213"),
				new BigDecimal("24.579"));
		this.repository.persist(dataUpdated);
	}

	@Test
	public void findById() throws Exception {

		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		assertEquals(3L, this.repository.count());

		WeatherData foundData = this.repository.findByDateTime(data1.getDateTime());
		assertNotNull(foundData);

		assertEquals(data1.getDateTime(), foundData.getDateTime());
	}

	@Test(expected = NoResultException.class)
	public void findById_NoRecordFound() {

		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		assertEquals(3L, this.repository.count());

		this.repository.findByDateTime(LocalDateTime.now().minusDays(1L));
	}

	@Test
	public void findById_NullInput() throws Exception {

		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		assertEquals(3L, this.repository.count());
		try {
			this.repository.findByDateTime(null);
			fail();
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}

	@Test
	public void findByDate_1() throws Exception {
		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data4 = new WeatherData(LocalDateTime.now().minusMinutes(1L), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data5 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 04), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		this.entityManager.persist(data4);
		this.entityManager.persist(data5);
		assertEquals(5L, this.repository.count());

		List<WeatherData> foundData = this.repository.findByDate(data1.getDateTime().toLocalDate());
		assertNotNull(foundData);

		assertEquals(2, foundData.size());
		assertEquals(data4.getDateTime(), foundData.get(0).getDateTime());
		assertEquals(data1.getDateTime(), foundData.get(1).getDateTime());
	}

	@Test
	public void findByDate_2() throws Exception {
		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data4 = new WeatherData(LocalDateTime.now().minusMinutes(1L), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data5 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 04), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		this.entityManager.persist(data4);
		this.entityManager.persist(data5);
		assertEquals(5L, this.repository.count());

		List<WeatherData> foundData = this.repository.findByDate(LocalDate.of(2010, 01, 01));
		assertNotNull(foundData);

		assertEquals(2, foundData.size());
		assertEquals(data3.getDateTime(), foundData.get(0).getDateTime());
		assertEquals(data5.getDateTime(), foundData.get(1).getDateTime());
	}

	@Test
	public void findByDate_NoRecordsFound() throws Exception {
		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data4 = new WeatherData(LocalDateTime.now().minusMinutes(1L), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data5 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 04), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		this.entityManager.persist(data4);
		this.entityManager.persist(data5);
		assertEquals(5L, this.repository.count());

		List<WeatherData> foundData = this.repository.findByDate(LocalDate.now().minusDays(1L));
		assertNotNull(foundData);

		assertEquals(0, foundData.size());
	}

	@Test
	public void findByDate_NullInput() throws Exception {
		WeatherData data1 = new WeatherData(LocalDateTime.now(), new BigDecimal("10.113"), new BigDecimal("14.589"));
		WeatherData data2 = new WeatherData(LocalDateTime.of(2005, 11, 30, 4, 53), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data3 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 03), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data4 = new WeatherData(LocalDateTime.now().minusMinutes(1L), new BigDecimal("10.113"),
				new BigDecimal("14.589"));
		WeatherData data5 = new WeatherData(LocalDateTime.of(2010, 01, 01, 14, 04), new BigDecimal("10.113"),
				new BigDecimal("14.589"));

		assertEquals(0L, this.repository.count());
		this.entityManager.persist(data1);
		this.entityManager.persist(data2);
		this.entityManager.persist(data3);
		this.entityManager.persist(data4);
		this.entityManager.persist(data5);
		assertEquals(5L, this.repository.count());

		try {
			this.repository.findByDate(null);
			fail();
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}
}
