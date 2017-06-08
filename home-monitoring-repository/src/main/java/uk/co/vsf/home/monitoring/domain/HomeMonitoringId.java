package uk.co.vsf.home.monitoring.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Embeddable
public class HomeMonitoringId implements Serializable {

	private static final long serialVersionUID = -598626944367587036L;

	@Column(nullable = false)
	private String date;

	@Column(nullable = false)
	private String time;

	protected HomeMonitoringId() {

	}

	public HomeMonitoringId(LocalDateTime dateTime) {
		if (dateTime == null) {
			throw new IllegalArgumentException("Must have a date-time");
		}
		time = DateTimeFormatter.ofPattern("HH:mm:ss").format(dateTime);
		date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(dateTime);
	}

	public LocalDateTime getDateTime() {
		return LocalDateTime.parse(date + "T" + time);
	}
	
	@Override
	public String toString() {
		ReflectionToStringBuilder rtsb = new ReflectionToStringBuilder(this);
		return rtsb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomeMonitoringId other = (HomeMonitoringId) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
