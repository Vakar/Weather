package utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import space.vakar.weather.domain.TemperatureUnit;

public class TemperatureUnitAdapter extends XmlAdapter<String, TemperatureUnit>{

	@Override
	public String marshal(TemperatureUnit unit) throws Exception {
		return unit.name().toLowerCase();
	}

	@Override
	public TemperatureUnit unmarshal(String unit) throws Exception {
		return TemperatureUnit.valueOf(unit.toUpperCase());
	}
}