package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import utils.DateTimeUtils;

public class ReservationManager {

	private List<Reservation> reservations;

	public ReservationManager() {
		reservations = new ArrayList<>();
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}

	public List<Reservation> getReservations(Car car, String startDate, String endDate) {
		List<Reservation> filterList = new ArrayList<>();
		Timestamp tsStartDate = DateTimeUtils.getTimestamp(startDate);
		Timestamp tsEndDate = DateTimeUtils.getTimestamp(endDate);

		for (Reservation reservation : reservations) {
			if (reservation.getCar().equals(car) && reservation.getPickUpDate().getTime() >= tsStartDate.getTime()
					&& reservation.getDropOffDate().getTime() <= tsEndDate.getTime()) {
				filterList.add(reservation);
			}
		}
		return filterList;
	}

	public void printReservations() {
		System.out.println("Reservas:");
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}

}
