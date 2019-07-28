package com.sc0ville.app;

import com.sc0ville.app.model.DataPoint;
import com.sc0ville.app.model.FlightPath;
import com.sc0ville.app.model.LidarPoint;
import com.sc0ville.app.model.MapPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper
 *
 * Created by Supun on 28/07/2019.
 */
public class Mapper
{

    private List<DataPoint> flightPathList;
    private List<DataPoint> lidarPointList;
    private List<MapPoint> mapPointList;

    public Mapper(List<DataPoint> flightPathList, List<DataPoint> lidarPointList)
    {
        this.flightPathList = flightPathList;
        this.lidarPointList = lidarPointList;
        this.mapPointList = new ArrayList<>();
    }

    public void process()
    {
        int flightCount = 0;
        for (DataPoint dataPoint : flightPathList)
        {
            if(dataPoint instanceof FlightPath)
            {
                FlightPath fPath = (FlightPath)dataPoint;
                flightCount++;
                if(fPath.getY()==Constant.FLIGHT_CONSTANT)
                {
                    double x = fPath.getX();// id
                    DataPoint dataLocal = flightPathList.get(flightCount);
                    double xPos = -1, yPos = -1;
                    if(dataLocal instanceof FlightPath)
                    {
                        FlightPath flightLocal = (FlightPath)dataLocal;
                        xPos = flightLocal.getX();
                        yPos = flightLocal.getY();
                    }
                    int count =0;
                    for (DataPoint dPoint : lidarPointList)
                    {
                        if(dPoint instanceof LidarPoint)
                        {
                            LidarPoint lPoint = (LidarPoint)dPoint;
                            count++;
                            if( x == lPoint.getAngle())// equal id of Lidar point
                            {
                                double noOfPoints = lPoint.getPoint();
                                int max = Integer.MAX_VALUE;
                                int min = Integer.MIN_VALUE;
                                int maxCount =0, minCount =0;
                                boolean maxEnable = false, minEnable = false;
                                List<Integer> maxList = new ArrayList<>();
                                List<Integer> minList = new ArrayList<>();
                                List<Double> maxAngleList = new ArrayList<>();
                                List<Double> minAngleList = new ArrayList<>();
                                double xStart = -1, yStart = -1, xEnd = -1, yEnd = -1, maxStart = -1;
                                for(int i=count;i<noOfPoints;i++)// loop for all the points
                                {
                                    DataPoint dCountPoint = lidarPointList.get(i);
                                    if(dCountPoint instanceof LidarPoint)
                                    {
                                        LidarPoint lCountPoint = (LidarPoint)dCountPoint;
                                        int point = lCountPoint.getPoint();
                                        double angle = lCountPoint.getAngle();
                                        if(!minEnable && point<max)
                                        {
                                            maxList.add(point);
                                            maxAngleList.add(angle);
                                            maxEnable = true;
                                            max = point;
                                        }
                                        else if(maxEnable && point>min)
                                        {
                                            minList.add(point);
                                            minAngleList.add(angle);
                                            minEnable = true;
                                            min = point;
                                        }
                                        else if(maxEnable && minEnable)
                                        {
                                            Double endAngle = minAngleList.get(minAngleList.size() - 1);//get the last element angle
                                            Double startAngle = maxAngleList.get(0);// get the starting angle
                                            Double sweepAngle = endAngle-startAngle;
                                            Double startDistance = maxList.get(0)/1000.0;
                                            Double endDistance = minList.get(minList.size() - 1)/1000.0;
                                            Double offsetAngle = new Double(180-sweepAngle);
                                            double atan = Math.atan(Math.sin(offsetAngle) / ((startDistance / endDistance) + Math.cos(offsetAngle)));
                                            double fLength = startDistance*Math.sin(atan);
                                            double eLength = startDistance*Math.cos(atan);
                                            double gLength = endDistance * Math.cos(offsetAngle);


                                            if(startAngle>0 && startAngle<180 && endAngle>0 && endAngle<180)
                                            {
                                                xEnd = xPos+eLength;
                                                xStart = xPos-gLength;
                                                yStart = yPos+fLength;
                                                yEnd = yPos+fLength;
                                            }
                                            else if(startAngle>90 && startAngle<270 && endAngle>90 && endAngle<270)
                                            {
                                                xEnd = xPos-fLength;
                                                xStart = xPos-fLength;
                                                yStart = yPos-gLength;
                                                yEnd = yPos+eLength;
                                            }
                                            else if(startAngle>180 && startAngle<360 && endAngle>180 && endAngle<360)
                                            {
                                                xEnd = xPos+gLength;
                                                xStart = xPos-eLength;
                                                yStart = yPos-fLength;
                                                yEnd = yPos-fLength;
                                            }
                                            else if(startAngle>270 && startAngle<90 && endAngle>270 && endAngle<90)
                                            {
                                                xEnd = xPos+fLength;
                                                xStart = xPos+fLength;
                                                yStart = yPos-eLength;
                                                yEnd = yPos+gLength;
                                            }
                                            MapPoint mapPoint = new MapPoint(xStart,yStart,xEnd,yEnd);
                                            if(!mapPointList.contains(mapPoint)) // check the same point is in the list
                                            {
                                                mapPointList.add(mapPoint);
                                            }

                                            maxList.clear();
                                            minList.clear();
                                            maxAngleList.clear();
                                            minAngleList.clear();
                                            maxEnable = false;
                                            minEnable = false;
                                            max = Integer.MAX_VALUE;
                                            min = Integer.MIN_VALUE;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public List<DataPoint> getFlightPathList()
    {
        return flightPathList;
    }

    public void setFlightPathList(List<DataPoint> flightPathList)
    {
        this.flightPathList = flightPathList;
    }

    public List<DataPoint> getLidarPointList()
    {
        return lidarPointList;
    }

    public void setLidarPointList(List<DataPoint> lidarPointList)
    {
        this.lidarPointList = lidarPointList;
    }

    public List<MapPoint> getMapPointList()
    {
        return mapPointList;
    }

    public void setMapPointList(List<MapPoint> mapPointList)
    {
        this.mapPointList = mapPointList;
    }
}
