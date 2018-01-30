package nl.hanze.t12.life.logic;

import java.util.LinkedList;
import java.util.Queue;

import nl.hanze.t12.life.logic.Car;

public class CarQueue {
    public Queue<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar(Car car) {
        return queue.poll();
        
    }

    public int carsInQueue(){
    	return queue.size();
    }
    
}
