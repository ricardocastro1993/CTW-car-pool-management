package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import utils.DateTimeUtils;

public class Pool {

	private List<Car> cars;
	private ReservationManager reservationManager;

	public Pool() {
		cars = new ArrayList<>();
		reservationManager = new ReservationManager();
		readCarPool();
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public List<Car> getCars() {
		return cars;
	}

	public String getCarDetails(Car car) {
		String details = "";
		if (cars.contains(car)) {
			details = "Brand: " + car.getBrand() + "\nModel: " + car.getModel() + "\nSeats: " + car.getSeats()
					+ "\nLicense plate: " + car.getLicensePlate() + "\nEngine type: " + car.getEngineType()
					+ "\nCurrent autonomy: " + car.getCurrentAutonomy() + "km";
		} else {
			details = "The car does not belong to the pool!";
		}
		return details;
	}

	public void removeCar(Car car) {
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getLicensePlate().equals(car.getLicensePlate())) {
				cars.remove(i);
				break;
			}
		}
	}

	public Reservation reserveCar(Car car) {

		Reservation reservation = null;

		if (cars.contains(car)) {

			// Insert dates
			String pickUpDate = JOptionPane.showInputDialog("Insert pickup date and hour:");
			String dropOffDate = JOptionPane.showInputDialog("Insert drop-off date and hour:");
			Timestamp tsPickUpDate = DateTimeUtils.getTimestamp(pickUpDate);
			Timestamp tsDropOffDate = DateTimeUtils.getTimestamp(dropOffDate);

			// Insert driver information
			String driverName = JOptionPane.showInputDialog("Insert driver name:");
			String driverContact = JOptionPane.showInputDialog("Insert driver contact:");
			String driverLicense = JOptionPane.showInputDialog("Insert license number:");
			Driver driver = new Driver(driverName, driverContact, driverLicense);

			reservation = new Reservation(tsPickUpDate, tsDropOffDate, car, driver);
			reservationManager.addReservation(reservation);
		} else {
			System.out.println("Error: Car does not exist in the pool!");
		}
		return reservation;
	}

	public ReservationManager getReservationManager() {
		return reservationManager;
	}

	public void readCarPool() {
		File file = new File("ficheiros/frota.csv");
		try {
			Scanner scanner = new Scanner(file);

			// Jump title
			scanner.nextLine();

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineFields = line.split(";");
				String brand = lineFields[0];
				String model = lineFields[1];
				int seats = Integer.parseInt(lineFields[2]);
				String licensePlate = lineFields[3];
				EngineType engineType = EngineType.valueOf(lineFields[4]);
				int autonomy = Integer.parseInt(lineFields[5]);
				Car car = new Car(brand, model, seats, licensePlate, engineType, autonomy, null);
				cars.add(car);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void init() {
		Scanner scanner = new Scanner(System.in);
		int userOption;
		Car car = null;

		do {
			showMenu();
			userOption = scanner.nextInt();
			switch (userOption) {
			case 1:
				car = createNewCar();
				addCar(car);
				break;
			case 2:
				System.out.println("Car list:");
				System.out.println(getCars());
				break;
			case 3:
				car = getCarByBrandAndModel();
				System.out.println("Car details:");
				System.out.println(getCarDetails(car));
				break;
			case 4:
				car = getCarByBrandAndModel();
				removeCar(car);
				break;
			case 5:
				car = getCarByBrandAndModel();
				reserveCar(car);
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		} while (userOption != 0);

		scanner.close();
	}

	private Car getCarByBrandAndModel() {
		Scanner scanner = new Scanner(System.in);
		Car car = null;

		System.out.println("Insert brand:");
		String brand = scanner.nextLine();

		System.out.println("Insert model:");
		String model = scanner.nextLine();

		for (Car c : cars) {
			if (c.getBrand().equals(brand) && c.getModel().equals(model)) {
				car = c;
				break;
			}
		}
		return car;
	}

	private void showMenu() {
		System.out.println("Menu:");
		System.out.println("1. Add new car");
		System.out.println("2. List all cars");
		System.out.println("3. Consult car details");
		System.out.println("4. Remove car");
		System.out.println("5. Reserve a car");
		System.out.println("6. Consult reserve history");
		System.out.println("0. Exit");
		System.out.println("Choose an option: ");
	}

	private Car createNewCar() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Insert brand:");
		String brand = scanner.nextLine();

		System.out.println("Insert model:");
		String model = scanner.nextLine();

		System.out.println("Insert number of seats:");
		int seats = Integer.parseInt(scanner.nextLine());

		System.out.println("Insert license plate:");
		String licensePlate = scanner.nextLine();

		System.out.println("Insert engine type (COMBUSTION, ELECTRIC, HYBRID):");
		EngineType engineType = EngineType.valueOf(scanner.nextLine());

		System.out.println("Insert autonomy:");
		int autonomy = Integer.parseInt(scanner.nextLine());

		Car car = new Car(brand, model, seats, licensePlate, engineType, autonomy, null);

		return car;
	}
}
