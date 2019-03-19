package edu.eci.cvds.pbt.triangle;

public class Classifier {
    public TriangleType classify(int x, int y, int z) {
        TriangleType t = TriangleType.NotATriangle;
        // TODO Implement
        if(x>0 && y>0 && z>0 && x+y>z && y+z>x && x+z>y){
            if(x==y && y==z ){
                t= TriangleType.Equilateral;
            }
            else if(x==y || y==z || x==z){
                t=TriangleType.Isosceles;
            }
            else{
                t=TriangleType.Scalene;
            }
        }
        return t;
    }
}