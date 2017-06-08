package uk.co.vsf.home.monitoring.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Embeddable
public class WeatherData implements Serializable {

	private static final long serialVersionUID = 9083144591538907113L;

	@Column(precision = 5, scale = 2, updatable = false)
	private BigDecimal windSpeed;

	@Column(precision = 5, scale = 2, updatable = false)
	private BigDecimal gustSpeed;

	protected WeatherData() {
	}

	public WeatherData(BigDecimal windSpeed, BigDecimal gustSpeed) {
		this.windSpeed = windSpeed;
		this.gustSpeed = gustSpeed;
	}

	public BigDecimal getWindSpeed() {
		return windSpeed;
	}

	public BigDecimal getGustSpeed() {
		return gustSpeed;
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
		result = prime * result + ((gustSpeed == null) ? 0 : gustSpeed.hashCode());
		result = prime * result + ((windSpeed == null) ? 0 : windSpeed.hashCode());
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
		WeatherData other = (WeatherData) obj;
		if (gustSpeed == null) {
			if (other.gustSpeed != null)
				return false;
		} else if (!gustSpeed.equals(other.gustSpeed))
			return false;
		if (windSpeed == null) {
			if (other.windSpeed != null)
				return false;
		} else if (!windSpeed.equals(other.windSpeed))
			return false;
		return true;
	}
}
