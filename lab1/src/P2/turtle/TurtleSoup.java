/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set; 
public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
       
        try {
        	for(int i = 0;i<4;i++)
        	{
        		turtle.forward(sideLength);
        		turtle.turn(90);
        	}
        }
        catch (Exception e)
        {
        	 throw new RuntimeException("implement me!");
        }
        	
        }
    

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
      
       try {
    	   double angle=180- 360.0/sides;
           return angle;
       }
       catch(Exception e) {
    	   
    	   throw new RuntimeException("implement me!");
	}
        
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
       try {
		return (int)Math.ceil(360.0/(180.0-angle));
	} catch (Exception e) {
		throw new RuntimeException("implement me!");
	} 
    	
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
       
    	try {
    		double a = 180 - calculateRegularPolygonAngle(sides);
        	for(int i = 0;i < sides;i++)
        	{
        		turtle.forward(sideLength);
        		turtle.turn(a);
        	}
    	}catch (Exception e) {
    		 throw new RuntimeException("implement me!");
		}
    	
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	try {
    		double angle = Math.toDegrees(Math.atan2(targetY - currentY,targetX - currentX ));
    		angle = 90 - angle - currentBearing;
    		if (angle<0)
    		{
    			angle = angle+360;
    		}
    		return angle;
    	}
    	catch(Exception e)
    	{
    		throw new RuntimeException("implement me!");
    	}
        
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        double angle = 0.0;
        double currentbearing=0.0;
        try {
        	List<Double> degrees = new ArrayList<>();
        	if(xCoords.size()!=yCoords.size())
        	{
        		System.exit(1);
        	}
        	if(xCoords.size()<=1)
        	{
        		System.exit(1);
        	}
        	for(int i = 0;i < xCoords.size() - 1;i++) {
           		angle = calculateBearingToPoint(currentbearing,xCoords.get(i),yCoords.get(i),xCoords.get(i + 1),yCoords.get(i + 1));
           		degrees.add(angle);
           		currentbearing = (currentbearing+angle)%360;
           	}
           	return degrees;
        	
		} catch (Exception e) {
			throw new RuntimeException("implement me!");
		}
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	try {
    		if(points.size()<3)
    		{
    			return points;
    		}
    	        Set<Point> convexHullPoints = new HashSet<Point>();
    	        Point a = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
    	        for (Point i : points) {
    	            if (i.x() < a.x() || (i.x() == a.x() && i.y() < a.y()))
    	                a = i;
    	        }
    	        Point maxPoint = a, minPoint = null, lastPoint = a;
    	        double x1 = 0.0, y1 = -1.0;
    	        do {
    	            convexHullPoints.add(maxPoint);
    	            double minData = Double.MAX_VALUE, x2 = 0.0, y2 = 0.0;
    	            for (Point j : points) {
    	                if ((!convexHullPoints.contains(j) || j == a) && (j != lastPoint)) {
    	                    double x3 = j.x() - maxPoint.x(), y3 = j.y() - maxPoint.y();
    	                    double data = Math
    	                            .acos((x1 * x3 + y1 * y3) / Math.sqrt(x1 * x1 + y1 * y1) / Math.sqrt(x3 * x3 + y3 * y3));   	                 
    	                    if (data < minData || (data == minData && x3 * x3 + y3 * y3 > Math.pow(j.x() - minPoint.x(), 2)
    	                            + Math.pow(j.y() - minPoint.y(), 2))) {
    	                        minPoint = j;
    	                        minData = data;
    	                        x2 = x3;
    	                        y2 = y3;
    	                    }
    	                }
    	            }
    	            x1 = x2;
    	            y1 = y2;
    	            lastPoint = maxPoint;
    	            maxPoint = minPoint;
    	        } while (maxPoint != a);
    	        return convexHullPoints;    		
    	}
    	catch (Exception e) {
    		throw new RuntimeException("implement me!");
		}
        
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	try {
    		turtle.turn(126);
    		PenColor k = null;
    		int r;
    		int m;
    		double a = 180 - calculateRegularPolygonAngle(5);  		
        	for(int i = 0;i < 4;i++)
        	{
        		r=(int)(Math.random()*5);
    			switch(r) {
    			case 0:
    				turtle.color(k.BLUE);
    				break;
    			case 1:
    				turtle.color(k.GREEN);
    				break;
    			case 2:
    				turtle.color(k.RED);
    				break;
    			case 3:
    				turtle.color(k.BLACK);
    				break;
    			case 4:
    				turtle.color(k.YELLOW);
    				break;
    			}
            		drawRegularPolygon(turtle,6,120);
            		turtle.turn(90);
        	}
        	for(int i =0;i<5;i++)
        	{
        	turtle.color(k.RED);
        	turtle.turn(72);
        	turtle.forward(80);
        	turtle.color(k.RED);
        	turtle.turn(216);
        	turtle.forward(80);
        	turtle.color(k.RED);
        	turtle.turn(216);
        	turtle.forward(80);
        	turtle.color(k.RED);
        	turtle.turn(216);
        	turtle.forward(80);
        	turtle.color(k.RED);
        	turtle.turn(216);
        	turtle.forward(80);
        	turtle.turn(70);
        	}
    	}catch (Exception e) {
			throw new RuntimeException("implement me!");
		}
        
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle,6,40);
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }
    

}
