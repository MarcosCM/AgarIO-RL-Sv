package sv;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@Document(collection = "locations")
public class Localizable {

    @Id
    private String id;
    private int mass;
    @GeoSpatialIndexed
    private double[] location; //Indexamos BD por localicacion
    private String type;

	public Localizable(){
        //Contructor vacio para parser Jackson JSON/HATEOAS
    }

    public Localizable (int mass, double[] location, String type){
        this.mass = mass;
        this.location = location;
        this.type = type;
    }
    
	public int getMass() {
		return mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    @Override
    public String toString() {
        return "User(mass = "+mass+", location = " + location[0] + " - " + location[1] + ")";
    }
}
