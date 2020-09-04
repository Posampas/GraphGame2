package GraphGame.MathFunctions;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Mathematics {

    public static double calculateDistance(double x1, double y1, double x2, double y2){

        return sqrt(pow(x1-x2,2) + pow(y1-y2,2));
    }
}
