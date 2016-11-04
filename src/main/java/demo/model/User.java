package demo.model;

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
    private int type; //Tipo de usuario (0 = coche, 1 = bicicleta, 2 = peaton)
    @GeoSpatialIndexed
    private double[] location; 	//Indexamos BD por localicacion

    public User (){
        //Contructor vacío para parser Jackson JSON/HATEOAS
    }

    public User (int type, double[] location){
        this.type = type;
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation (double [] location){
        this.location = location;
    }

    @Override
    public String toString() {
        return "User(type = "+type+", location = " + location[0] + " - " + location[1] + ")";
    }
}
