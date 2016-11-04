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
	private boolean hasVirus;
	
	public Bank(double[] location){
		this.location = location;
		this.numBalls = 0;
		this.hasVirus = false;
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

	public boolean hasVirus() { return this.hasVirus; }

	public void setVirus(boolean state) { this.hasVirus = state; }
}
