package sv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "banks")
public class Bank {

	@Id
	private String id;
	@GeoSpatialIndexed
    private double[] location;
	
	private int numBalls;
	
	public Bank(double[] location){
		this.location = location;
		this.numBalls = 0;
	}
	
	public double[] getLocation(){
		return this.location;
	}
	
	public void setLocation(double[] location){
		this.location = location;
	}
	
	public int getNumBalls(){
		return this.numBalls;
	}
	
	public void setNumBalls(int numBalls){
		this.numBalls = numBalls;
	}
}
