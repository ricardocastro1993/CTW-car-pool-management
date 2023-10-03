package model;

import java.sql.Timestamp;

import utils.DateTimeUtils;

public class Reservation {

	private Timestamp pickUpDate;
	private Timestamp dropOffDate;
	private Car car;
	private Driver driver;

	public Reservation(Timestamp pickUpDate, Timestamp dropOffDate, Car car, Driver driver) {
		this.pickUpDate = pickUpDate;
		this.dropOffDate = dropOffDate;
		this.car = car;
		this.driver = driver;
	}

	public Timestamp getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Timestamp pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public Timestamp getDropOffDate() {
		return dropOffDate;
	}

	public void setDropOffDate(Timestamp dropOffDate) {
		this.dropOffDate = dropOffDate;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		String pickDate = DateTimeUtils.getDateFormat(pickUpDate.getTime());
		String dropDate = DateTimeUtils.getDateFormat(dropOffDate.getTime());
		return car.getBrand() + " " + car.getModel() + " | Pickp up date: " + pickDate + " | Drop off date: " + dropDate
				+ " | Driver name: " + driver.getName();
	}

}
