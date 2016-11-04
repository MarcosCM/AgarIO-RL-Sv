package sv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "balls")
public class Ball {

	@Id
	private String id;
	@GeoSpatialIndexed
    private double[] location;
	private Bank bank;
	
	public Ball(double[] loc, Bank bank){
		this.location = loc;
		this.bank = bank;
	}
	
	public double[] getLocation(){
		return this.location;
	}
	
	public void setLocation(double[] loc){
		this.location = loc;
	}
	
	public Bank getBank(){
		return this.bank;
	}
	
	public void setBank(Bank bank){
		this.bank = bank;
	}
}
