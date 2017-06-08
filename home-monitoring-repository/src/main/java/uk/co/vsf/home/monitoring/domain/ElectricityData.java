package uk.co.vsf.home.monitoring.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Embeddable
public class ElectricityData {

	@Column(updatable = false)
	private BigInteger energyGenWattHrs;
	@Column(updatable = false)
	private BigInteger powerGenWattHrs;

	@Column(precision = 7, scale = 2, updatable = false)
	private BigDecimal importWatts;

	@Column(precision = 6, scale = 2, updatable = false)
	private BigDecimal exportWatts;

	@Column(precision = 5, scale = 2, updatable = false)
	private BigDecimal mainsVoltage;

	protected ElectricityData() {

	}

	public ElectricityData(boolean a) {

	}

	public BigInteger getEnergyGenWattHrs() {
		return energyGenWattHrs;
	}

	public BigInteger getPowerGenWattHrs() {
		return powerGenWattHrs;
	}

	public BigDecimal getImportWatts() {
		return importWatts;
	}

	public BigDecimal getExportWatts() {
		return exportWatts;
	}

	public BigDecimal getMainsVoltage() {
		return mainsVoltage;
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
		result = prime * result + ((energyGenWattHrs == null) ? 0 : energyGenWattHrs.hashCode());
		result = prime * result + ((exportWatts == null) ? 0 : exportWatts.hashCode());
		result = prime * result + ((importWatts == null) ? 0 : importWatts.hashCode());
		result = prime * result + ((mainsVoltage == null) ? 0 : mainsVoltage.hashCode());
		result = prime * result + ((powerGenWattHrs == null) ? 0 : powerGenWattHrs.hashCode());
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
		ElectricityData other = (ElectricityData) obj;
		if (energyGenWattHrs == null) {
			if (other.energyGenWattHrs != null)
				return false;
		} else if (!energyGenWattHrs.equals(other.energyGenWattHrs))
			return false;
		if (exportWatts == null) {
			if (other.exportWatts != null)
				return false;
		} else if (!exportWatts.equals(other.exportWatts))
			return false;
		if (importWatts == null) {
			if (other.importWatts != null)
				return false;
		} else if (!importWatts.equals(other.importWatts))
			return false;
		if (mainsVoltage == null) {
			if (other.mainsVoltage != null)
				return false;
		} else if (!mainsVoltage.equals(other.mainsVoltage))
			return false;
		if (powerGenWattHrs == null) {
			if (other.powerGenWattHrs != null)
				return false;
		} else if (!powerGenWattHrs.equals(other.powerGenWattHrs))
			return false;
		return true;
	}
}
