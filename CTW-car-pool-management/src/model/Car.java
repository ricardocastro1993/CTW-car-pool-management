package model;

import java.sql.Blob;

public class Car {

	private String brand;
	private String model;
	private int seats;
	private String licensePlate;
	private EngineType engineType;
	private int currentAutonomy;
	private Blob image;

	public Car(String brand, String model, int seats, String licensePlate, EngineType engineType, int currentAutonomy,
			Blob image) {
		this.brand = brand;
		this.model = model;
		this.seats = seats;
		this.licensePlate = licensePlate;
		this.engineType = engineType;
		this.currentAutonomy = currentAutonomy;
		this.image = image;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getSeats() {
		return seats;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public int getCurrentAutonomy() {
		return currentAutonomy;
	}

	public Blob getImgae() {
		return image;
	}

	@Override
	public String toString() {
		return brand + " " + model;
	}

}
