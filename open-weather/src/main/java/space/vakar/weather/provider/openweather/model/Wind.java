package space.vakar.weather.provider.openweather.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Wind {

	private WindSpeed speed;
	private Gusts gusts;
	private WindDirection direction;
	public Wind() {
		
	}

	public Wind(WindSpeed speed, WindDirection direction) {
		this.speed = speed;
		this.direction = direction;
	}

	public WindSpeed getSpeed() {
		return speed;
	}

	@XmlElement
	public void setSpeed(WindSpeed speed) {
		this.speed = speed;
	}

	public Gusts getGusts() {
		return gusts;
	}

	@XmlElement
	public void setGusts(Gusts gusts) {
		this.gusts = gusts;
	}

	public WindDirection getDirection() {
		return direction;
	}

	@XmlElement
	public void setDirection(WindDirection direction) {
		this.direction = direction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(speed, gusts, direction);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Wind) {
			Wind that = (Wind) object;
			return Objects.equals(this.speed, that.speed)
					&& Objects.equals(this.gusts, that.gusts)
					&& Objects.equals(this.direction, that.direction);
		}
		return false;
	}

	@Override
	public String toString() {
		String format = "Wind [speed=%s, gusts=%s, direction=%s]";
		return String.format(format, speed,	gusts, direction);
	}
}
