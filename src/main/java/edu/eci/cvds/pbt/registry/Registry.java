package edu.eci.cvds.pbt.registry;
import java.util.*;

public class Registry {  
    Map<Person,String> Certificados;
    public Registry(){
        Certificados= new HashMap<Person,String>();
    }
    
    public RegisterResult registerVoter(Person p) {
        RegisterResult r=RegisterResult.VALID;
        if(!p.isAlive()){
            r=RegisterResult.ERROR_CIUDADANO_FALLECIDO;
        }else if(p.getName().length()<=9){
            r=RegisterResult.ERROR_NOMBRE_NO_VALIDO;
        }else if(Certificados.containsValue(p)){            
            r=RegisterResult.ERROR_YA_REGRISTADO;
        }else if(existID(p.getId())){
            r=RegisterResult.ERROR_DOCUMENTO_YA_REGISTRADO;
        }else if(p.getAge()<18 || p.getAge()>123){
            r=RegisterResult.ERROR_EDAD_NO_VALIDA;
        }else if(p.getId()<1000000){
            r= RegisterResult.ERROR_IDENTIFICACION_NO_VALIDA;
        }else{
            Certificados.put(p,"CERTIFICADO DE VOTACION DE: "+p.getName()+" IDENTIFICACION: "+p.getId());
        
        }       
        return r;
    }

    public Optional<String> generateCertificate(Person per) {       
        return Optional.ofNullable(Certificados.get(per));
    }
    
    public boolean existID(int id){
        boolean ans=false;
        for(Person p: Certificados.keySet()){
            ans=ans||p.getId()==id;
        }
        return ans;
    }
}