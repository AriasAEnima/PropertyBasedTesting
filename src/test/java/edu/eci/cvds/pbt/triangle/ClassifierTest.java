package edu.eci.cvds.pbt.triangle;

import org.junit.Test;
import edu.eci.cvds.pbt.PBTClassifier;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

public class ClassifierTest {

    @Test
    public void addingTwoPositiveIntegersAlwaysGivesAPositiveInteger(){
      Classifier classifier = new Classifier();
      PBTClassifier pbtClassifier = new PBTClassifier("pruebas");

      qt()
      .forAll(integers().between(-1, 20)
             , integers().between(-1, 20)
             , integers().between(-1, 20))
      .check((a,b,c) -> {
            TriangleType classification = classifier.classify(a, b, c);

            if (a <= 0 || b<=0 || c<=0) {
              pbtClassifier.collect("Not a triangle");              
              return classification == TriangleType.NotATriangle;
            } else if(a+b<=c || a+c<=b || b+c<=a){
              pbtClassifier.collect("Not a triangle");              
              return classification == TriangleType.NotATriangle;
            }else if(a==b && b==c){
              pbtClassifier.collect("Triangle Equilateral");              
              return classification == TriangleType.Equilateral;
            }else if(a==b || b==c || a==c){
              pbtClassifier.collect("Triangle Isosceles");              
              return classification == TriangleType.Isosceles;   
            }else{
              pbtClassifier.collect("Triangle Scalene");              
              return classification == TriangleType.Scalene;
            }
            
            
          }); 
      pbtClassifier.results();
  }
}