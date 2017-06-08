package uk.co.vsf.home.monitoring.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Embeddable
public class HotWaterData {

	@Column(name = "hotwater", precision = 5, scale = 2, updatable = false)
	private BigDecimal temperature;

	@Column(name = "immersion")
	private Boolean immersionOn;

	protected HotWaterData() {

	}

	public HotWaterData(BigDecimal temperature, Boolean immersionOn) {
		this.temperature = temperature;
		this.immersionOn = immersionOn;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public Boolean getImmersionOn() {
		return immersionOn;
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
		result = prime * result + ((immersionOn == null) ? 0 : immersionOn.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
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
		HotWaterData other = (HotWaterData) obj;
		if (immersionOn == null) {
			if (other.immersionOn != null)
				return false;
		} else if (!immersionOn.equals(other.immersionOn))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}
}
