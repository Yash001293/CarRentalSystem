package All;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
	
	private String carId; 
	
	private String brand;
	
	private String model;
	
	private double basePricePerDay;
	
	private boolean isAvailable;
//using consgtructor by field
	public Car(String carId, String brand, String model, double basePricePerDay) {
	this.carId = carId;
	this.brand = brand;
	this.model = model;
	this.basePricePerDay = basePricePerDay;
	this.isAvailable = true;
}

// using getter setter method
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	} 

	public double calculatePrice(int rentalDays) {
		return basePricePerDay * rentalDays;
	}
	

	public void setBasePricePerDay(double basePricePerDay) {
		this.basePricePerDay = basePricePerDay;
	}

	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void rent() {
		isAvailable=false;
	}
	
	public void returnCar() {
		isAvailable=true;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
  }

class Customer{
	
	 private String customerId;
	 
	 private String name;
	 
	

//using constructor by field	 
	public Customer(String customerId, String name) {
		
		this.customerId = customerId;
		this.name = name;
	}
	
//using getter setter method
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
	
	 
}


class Rental{
	
	private Car car;
	
	private Customer customer;
	
	private int days;
	
//using constructor by field	
	public Rental(Car car, Customer customer, int days) {
		super();
		this.car = car;
		this.customer = customer;
		this.days = days;
	}

//using getter setter method
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	
	
}
 class CarRentalSystem {
	 
     private List<Car> cars;
	 
	 private List<Customer> customers;
	 
	 private List<Rental> rentals;
	 
	 public CarRentalSystem() {
		 cars =new ArrayList<>();
		 customers=new ArrayList<>();
		 rentals=new ArrayList<>();
	 }
	 public void addCar(Car car) {
		 cars.add(car);
	 }
	 
	 public void addCustomer(Customer customer) {
		 customers.add(customer);
	 }
	 
	 public void rentCar(Car car,Customer customer,int days) {
		 if(car.isAvailable()) {
			 car.rent();
			 rentals.add(new Rental(car,customer,days));
			 
		 }else {
			 System.out.println("Car is not available for rent.");
		 }
	 }
	 
	 public void returnCar(Car car) {
		 car.returnCar();
		 Rental rentalToRemove=null;
		 for(Rental rental:rentals) {
			 if(rental.getCar()==car) {
				 rentalToRemove=rental;
				 break;
			 }
		 }
		 
		 if(rentalToRemove !=null) {
			 rentals.remove(rentalToRemove);
			 System.out.println("Car returned successfully.");
		 }else {
			 System.out.println("Car was not rented.");
		 }
	 }
	 
	 public void menu() {
		 Scanner sc=new Scanner(System.in);
		 
		 while(true) {
			 System.out.println("=== Car Rental System ===");
			 System.out.println("1.Rent a Car");
			 System.out.println("2.Return a Car");
			 System.out.println("3.Exit");
			 System.out.println("Enter your choice:");
			 
			 int choice=sc.nextInt();
			 sc.nextLine(); //consume newline
			 
			 if(choice==1) {
				 System.out.println("\n == Rent a car ==\n");
				 System.out.println("Enter your name:");
				 String customerName=sc.nextLine();
				 
				 System.out.println("\nAvailable Cars:");
				 for(Car car :cars) {
					 if(car.isAvailable()) {
						 System.out.println(car.getCarId()+" - "+car.getBrand()+" -  "+car.getModel());
					 }
				 }
				 
				 System.out.println("\nEnter the car ID you want to rent:");
				 String carId=sc.nextLine();
				 
				 System.out.println("Enter the number of days for rental:");
				 int rentalDays =sc.nextInt();
				 sc.nextLine(); //consume newline
				 
				 Customer newCustomer =new Customer("CUS"+(customers.size()+1),customerName);
				 addCustomer(newCustomer);
				 
				 Car selectedCar=null;
				 for(Car car : cars) {
					 if(car.getCarId().equals(carId) && car.isAvailable()) {
						 selectedCar=car;
						 break;
					 }
				 }
				 
				 if(selectedCar !=null) {
					 double totalPrice = selectedCar.calculatePrice(rentalDays);
					 System.out.println("\n== Rental Information ==\n");
					 System.out.println("Customer ID:"+newCustomer.getCustomerId());
					 System.out.println("Customer Name:"+newCustomer.getName());
					 System.out.println("Car:"+selectedCar.getBrand()+" "+selectedCar.getModel());
					 System.out.println("Rental Days:"+rentalDays);
					 System.out.printf("Total Price:$%.2f%n",totalPrice);
					 
					 System.out.println("\n Conform rental (Y/N):");
					 String conform=sc.nextLine();
					 
					 if(conform.equalsIgnoreCase("Y")) {
						 rentCar(selectedCar,newCustomer,rentalDays);
						 System.out.println("\n Car rented successfully.");
					 }else {
						 System.out.println("\n Rental canceled.");
					 }
				 }else {
					 System.out.println("\n Invalid car selection or car not available for rent.");
				 }
				 }else if(choice==2) {
					 System.out.println("\n== Return a car ==\n");
					 System.out.println("Enter the car ID you want to return:");
					 String carId=sc.nextLine();
					 
					 Car carToReturn=null;
					 for(Car car:cars) {
						 if(car.getCarId().equals(carId) && !car.isAvailable()){
							 carToReturn =car;
							 break;
						 }
					 
				 }
					 if(carToReturn !=null) {
						 Customer customer=null;
						 for(Rental rental : rentals) {
							 if(rental.getCar()==carToReturn) {
								 customer = rental.getCustomer();
								 break;
							 }
						 }
						 
						 if(customer !=null) {
							 returnCar(carToReturn);
							 System.out.println("Car returned successfully by "+ customer.getName());
						 }else {
							 System.out.println("Car was not rented or rental information is missing.");
						 }
					 }else {
						 System.out.println("Invalid car ID or car is not rented.");
						 }
				 }else if (choice==3) {
					 break;
					 }else {
						 System.out.println("Invalid choice.PLease enter a valid option.");
			 }
		 }
		 System.out.println("\n Thank you for using the Car Rental System.");
	 }
 }
public class Project{
	public static void main(String[] args) {
		CarRentalSystem rentalSystem=new CarRentalSystem();
		
		Car car1=new Car("C1","Toyoto","Camry",50.0);
		Car car2=new Car("C2","Honda","Accord",70.0);
		Car car3=new Car("C3","Mahindra","Thar",120.0);
		
		rentalSystem.addCar(car1);
		rentalSystem.addCar(car2);
		rentalSystem.addCar(car3);
		
		rentalSystem.menu();
	}
}