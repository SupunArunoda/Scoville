package com.sc0ville.app.model;


/**
 * FlightPath
 *
 * Created by Supun on 28/07/2019.
 */
public class FlightPath implements DataPoint
{
    private double x;
    private double y;

    public FlightPath(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj)
    {
        FlightPath fPath = (FlightPath) obj;
        boolean status = false;
        if(this.x == fPath.x && this.y == fPath.y)
        {
            status = true;
        }
        return status;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}
