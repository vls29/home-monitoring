package uk.co.vsf.home.monitoring.domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class HomeMonitoringIdTest {

	@Test(expected = IllegalArgumentException.class)
	public void construct_NoInput() {
		new HomeMonitoringId(null);
	}

	@Test
	public void pastDateOk() {
		String dateInput = "2010-12-25";
		String timeInput = "00:00:00";

		LocalDateTime ldt = LocalDateTime.parse(dateInput + "T" + timeInput);
		HomeMonitoringId id = new HomeMonitoringId(ldt);
		assertEquals(id.getDateTime(), ldt);

		String date = (String) ReflectionTestUtils.getField(id, "date");
		assertEquals(dateInput, date);

		String time = (String) ReflectionTestUtils.getField(id, "time");
		assertEquals(timeInput, time);
	}

	@Test
	public void futureDateOk() {
		String dateInput = "2050-11-30";
		String timeInput = "14:52:10";

		LocalDateTime ldt = LocalDateTime.parse(dateInput + "T" + timeInput);
		HomeMonitoringId id = new HomeMonitoringId(ldt);
		assertEquals(id.getDateTime(), ldt);

		String date = (String) ReflectionTestUtils.getField(id, "date");
		assertEquals(dateInput, date);

		String time = (String) ReflectionTestUtils.getField(id, "time");
		assertEquals(timeInput, time);
	}
}
