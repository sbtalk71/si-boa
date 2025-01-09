package com.demo.spring.soap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "https://www.w3schools.com/xml/")
public class FahrenheitToCelsius {
	
	private String fahrenheit;

	@XmlElement(name = "Fahrenheit",namespace = "https://www.w3schools.com/xml/")
	public String getFahrenheit() {
		return fahrenheit;
	}

	public void setFahrenheit(String fahrenheit) {
		this.fahrenheit = fahrenheit;
	}

}
