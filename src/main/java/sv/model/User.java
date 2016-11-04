package sv.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Jorge Martinez on 4/4/16.
 */

@Document(collection = "locations")
public class User {

    @Id
    private String id;
    private int mass;
    @GeoSpatialIndexed
    private double[] location; //Indexamos BD por localicacion

    public User (){
        //Contructor vacio para parser Jackson JSON/HATEOAS
    }

    public User (int mass, double[] location){
        this.mass = mass;
        this.location = location;
    }

    public int getType() {
        return mass;
    }

    public void setType(int mass) {
        this.mass = mass;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation (double [] location){
        this.location = location;
    }

    @Override
    public String toString() {
        return "User(mass = "+mass+", location = " + location[0] + " - " + location[1] + ")";
    }
}
