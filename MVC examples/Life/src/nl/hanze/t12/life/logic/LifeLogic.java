package nl.hanze.t12.life.logic;

import nl.hanze.t12.life.exception.*;

//import java.awt.BorderLayout;
//import java.awt.Container;
import java.util.*;

import nl.hanze.t12.life.logic.CarQueue;
import nl.hanze.t12.life.logic.Car;
import nl.hanze.t12.life.logic.Location;

public class LifeLogic extends AbstractModel implements Runnable {
	private int size;
	private static final int MIN_SIZE=10;
	private static final int MAX_SIZE=50;
	private boolean sizeIsSet;
	
	//private float degree;
	//private static final float MIN_DEGREE=0.0f;
	//private static final float MAX_DEGREE=1.0f;
	//private boolean degreeIsSet;
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

	private boolean initRun;
	
//	private int[][] fieldOriginal;
//	private int[][] fieldUnderConstruction;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    public Car[][] cars;
	//private Random r;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    //private int tickPause = 100;
  
    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
	
	private boolean run;
	
	public void setNumberOfOpenSpots(int numberOfOpenSpots) {
		this.numberOfOpenSpots = numberOfOpenSpots;
	}

	public LifeLogic() {
		size=MIN_SIZE-1;
		//degree=MIN_DEGREE-1;
		sizeIsSet=false;
		//degreeIsSet=false;
		//r=new Random();
		run=false;

        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
	
        //this.numberOfFloors = numberOfFloors;
        this.numberOfRows = 8;
        this.numberOfPlaces = 20;
        this.setNumberOfOpenSpots(numberOfRows*numberOfPlaces);
        cars = new Car[numberOfRows][numberOfPlaces];
        

/*        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack(); 
        setVisible(true);*/

	}
	
	public void setSize(int size) throws LifeException {
		if (size<MIN_SIZE) 
			throw new LifeException("Size too small");
		if (size>MAX_SIZE)
			throw new LifeException("Size too large");
		this.size=size;
		sizeIsSet=true;
		//fieldOriginal=new int[size][size];
		//fieldUnderConstruction=new int[size][size];
		initRun=false;
	}
	
	public void doStep() throws LifeException {
		if (!sizeIsSet)
			throw new LifeException("Size is not set yet");
		if (!initRun)
			throw new LifeException("Run init first");
		calculateRound();
		notifyViews();
	}
	
	public Car[][] getState() {
		return cars;
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		run=false;
	}

	@Override
	public void run() {
		if(true) {
			calculateRound();
			notifyViews();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void calculateRound() {
		if(run) {	
	    	//handle Entrance
	    	carsArriving();
	    	carsEntering(entrancePassQueue);
	    	carsEntering(entranceCarQueue);  	
	
	    	advanceTime();
	    	// handle Exit
	        carsReadyToLeave();
	        carsPaying();
	        carsLeaving();
		}
		else return;
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getRow()][location.getPlace()];
    }
    
    public boolean getCarAtLocation(int row, int place) {
    	return cars[row][place].getHasToPay();
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            setNumberOfOpenSpots(getNumberOfOpenSpots() - 1);
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        setNumberOfOpenSpots(getNumberOfOpenSpots() + 1);
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = new Location(row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = new Location(row, place);
                	Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        
        return null;
    }

    public void tick() {
      
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = new Location(row, place);
                	Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        
    }

    private boolean locationIsValid(Location location) {
        int row = location.getRow();
        int place = location.getPlace();
        if (row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
	
	
	
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			this.getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
    		Car car = queue.removeCar();
    		Location freeLocation = this.getFirstFreeLocation();
            this.setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
    	Car car = this.getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = this.getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
    		Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	this.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
}