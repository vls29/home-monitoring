package uk.co.vsf.home.monitoring.domain;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "DATA")
public class HomeMonitoringData {

	@EmbeddedId
	private HomeMonitoringId id;

	@Embedded
	private WeatherData weatherData;

	@Embedded
	private ElectricityData electricityData;

	@Embedded
	private HotWaterData hotWaterData;

	protected HomeMonitoringData() {

	}

	public HomeMonitoringData(LocalDateTime dateTime, WeatherData weatherData) {
		this.id = new HomeMonitoringId(dateTime);
		this.weatherData = weatherData;
	}

	public HomeMonitoringData(LocalDateTime dateTime, ElectricityData electricityData) {
		this.id = new HomeMonitoringId(dateTime);
		this.electricityData = electricityData;
	}

	public HomeMonitoringData(LocalDateTime dateTime, HotWaterData hotWaterData) {
		this.id = new HomeMonitoringId(dateTime);
		this.hotWaterData = hotWaterData;
	}

	public LocalDateTime getDateTime() {
		return this.id.getDateTime();
	}

	public HomeMonitoringId getId() {
		return id;
	}

	public WeatherData getWeatherData() {
		return weatherData;
	}

	public ElectricityData getElectricityData() {
		return electricityData;
	}

	public HotWaterData getHotWaterData() {
		return hotWaterData;
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
		result = prime * result + ((electricityData == null) ? 0 : electricityData.hashCode());
		result = prime * result + ((hotWaterData == null) ? 0 : hotWaterData.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((weatherData == null) ? 0 : weatherData.hashCode());
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
		HomeMonitoringData other = (HomeMonitoringData) obj;
		if (electricityData == null) {
			if (other.electricityData != null)
				return false;
		} else if (!electricityData.equals(other.electricityData))
			return false;
		if (hotWaterData == null) {
			if (other.hotWaterData != null)
				return false;
		} else if (!hotWaterData.equals(other.hotWaterData))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (weatherData == null) {
			if (other.weatherData != null)
				return false;
		} else if (!weatherData.equals(other.weatherData))
			return false;
		return true;
	}
}
