package com.sc0ville.app.model;

/**
 * LidarPoint
 *
 * Created by Supun on 28/07/2019.
 */
public class MapPoint
{
    private double xStart;
    private double yStart;
    private double xEnd;
    private double yEnd;

    public MapPoint(double xStart, double yStart, double xEnd, double yEnd)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public boolean equals(Object obj)
    {
        MapPoint mapPoint = (MapPoint) obj;
        boolean status = false;
        if(this.xStart == mapPoint.xStart && this.xEnd == mapPoint.xEnd &&
                this.yStart == mapPoint.yStart && this.yEnd == mapPoint.yEnd)
        {
            status = true;
        }
        return status;
    }

    public double getxStart()
    {
        return xStart;
    }

    public void setxStart(double xStart)
    {
        this.xStart = xStart;
    }

    public double getyStart()
    {
        return yStart;
    }

    public void setyStart(double yStart)
    {
        this.yStart = yStart;
    }

    public double getxEnd()
    {
        return xEnd;
    }

    public void setxEnd(double xEnd)
    {
        this.xEnd = xEnd;
    }

    public double getyEnd()
    {
        return yEnd;
    }

    public void setyEnd(double yEnd)
    {
        this.yEnd = yEnd;
    }
}
