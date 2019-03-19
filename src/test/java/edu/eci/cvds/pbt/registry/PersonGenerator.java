package edu.eci.cvds.pbt.registry;
import edu.eci.cvds.pbt.registry.Person;
import static org.quicktheories.QuickTheory.qt;
import org.quicktheories.core.*;
import org.quicktheories.generators.*;
import org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;


public class PersonGenerator {
    public static Gen<Person> persons() {      
        return names().zip(genders(),allages(),idsGen(), aliveGen(), (n,g,ag,id,al)-> new Person(n, id, ag, g, al));
    }
    
    public static Gen<String> names(){       
        return strings().basicLatinAlphabet().ofLengthBetween(4,10).zip(Generate.constant(" "),(nombre,espacio)-> new String(nombre+espacio))
                .zip(strings().basicLatinAlphabet().ofLengthBetween(4,10),(n,a)-> new String(n+a));
    }
    public static Gen<Gender> genders(){
        return Generate.enumValues(Gender.class);
    }
    
    public static Gen<Integer> allages(){
        return integers().from(-2).upToAndIncluding(200);
    }
    
    public static Gen<Integer> idsGen(){
        return integers().from(100).upToAndIncluding(999999999);    
    }   
    
    public static Gen<Boolean> aliveGen(){
        return Generate.booleans();
    }
}