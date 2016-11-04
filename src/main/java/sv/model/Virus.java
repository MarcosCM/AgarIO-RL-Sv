package sv.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "virus")
public class Virus extends Ball{

    public Virus(double[] loc, Bank bank){
       super(loc, bank);
    }



}
