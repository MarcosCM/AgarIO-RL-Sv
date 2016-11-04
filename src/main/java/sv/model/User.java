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

    public User (int type, double[] location){
        this.mass = type;
        this.location = location;
    }

    public int getType() {
        return mass;
    }

    public void setType(int type) {
        this.mass = type;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation (double [] location){
        this.location = location;
    }

    @Override
    public String toString() {
        return "User(type = "+mass+", location = " + location[0] + " - " + location[1] + ")";
    }
}
