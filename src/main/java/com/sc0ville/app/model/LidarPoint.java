package com.sc0ville.app.model;

/**
 * LidarPoint
 *
 * Created by Supun on 28/07/2019.
 */

public class LidarPoint implements DataPoint
{
    private double angle;
    private int point;

    public LidarPoint(double angle, int point)
    {
        this.angle = angle;
        this.point = point;
    }

    public boolean equals(Object obj)
    {
        LidarPoint lPoint = (LidarPoint) obj;
        boolean status = false;
        if(this.angle == lPoint.angle && this.point == lPoint.point)
        {
            status = true;
        }
        return status;
    }
    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }
}
