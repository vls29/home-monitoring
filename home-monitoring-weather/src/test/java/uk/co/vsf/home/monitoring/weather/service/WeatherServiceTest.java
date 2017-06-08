package uk.co.vsf.home.monitoring.weather.service;

import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.vsf.home.monitoring.repository.WeatherRepository;
import uk.co.vsf.home.monitoring.weather.domain.WeatherData;

import static org.easymock.EasyMock.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WeatherServiceTest {

	private IMocksControl control;
	
	private WeatherService weatherService;
	
	private WeatherRepository weatherRepositoryMock;
	
	@Before
	public void before() {
		control = createStrictControl();
		
		weatherRepositoryMock = control.createMock(WeatherRepository.class);
		weatherService = new WeatherService(weatherRepositoryMock);
	}
	
	@Test
	public void saveWeatherData() {
		
		BigDecimal windSpeed = new BigDecimal("100.223");
		BigDecimal gustSpeed = new BigDecimal("200.446");
		
		weatherRepositoryMock.persist(new WeatherData(LocalDateTime.now(), windSpeed, gustSpeed));
		
		control.replay();
		weatherService.save(windSpeed, gustSpeed);
		control.verify();
	}
}
