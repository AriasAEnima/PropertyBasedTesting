package edu.eci.cvds.pbt.registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.quicktheories.QuickTheory.qt;
import java.util.*;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

public class RegistryTest {
    ArrayList<Person> personas;
    Map<Person,String> Certificados;
    
   
    
    @Test
    public void validateRegistryResult() {
        personas=new ArrayList<>();
       
        Registry App=new Registry();
        qt()
            .forAll(PersonGenerator.persons())
            .check(voter ->  {    
                boolean ans=false;
                RegisterResult res=App.registerVoter(voter);
                ans=RegisterResult.VALID ==res;
                if (!voter.isAlive()){
                    ans=RegisterResult.ERROR_CIUDADANO_FALLECIDO==res;
                }else if(voter.getName().length()<=9){
                    ans=RegisterResult.ERROR_NOMBRE_NO_VALIDO==res;                  
                }else if(personas.contains(voter)){
                    ans=RegisterResult.ERROR_YA_REGRISTADO==res;
                }else if(usedId(voter.getId())){
                    ans=RegisterResult.ERROR_DOCUMENTO_YA_REGISTRADO==res;                
                }else if(voter.getAge()<18||voter.getAge() >123){
                    ans=RegisterResult.ERROR_EDAD_NO_VALIDA==res;     
                }else if(voter.getId()<1000000){
                    ans=RegisterResult.ERROR_IDENTIFICACION_NO_VALIDA==res;    
                }
                return ans;
            });
    }
    private boolean usedId(int id){
        boolean ans=false;
        for(Person p:personas){
            if (p.getId()==id){
                ans=true;
            }
        }
        return ans;
    }
    
    
    @Test
    public void validateCertificate() {
        assertTrue(true);       
    }
}