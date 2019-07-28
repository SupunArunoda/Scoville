package com.sc0ville.app;

import com.sc0ville.app.model.DataPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MapperTest
{
    List<DataPoint> flighPathList = new ArrayList<>();
    List<DataPoint> lidarPathList = new ArrayList<>();
    FIleOperator fIleOperator;
    @Before
    public void generateData()
    {
        String flightPath = "C:\\Users\\Supun\\IdeaProjects\\LidarSimulation\\sample\\FlightPath.csv";
        String lidarPath = "C:\\Users\\Supun\\IdeaProjects\\LidarSimulation\\sample\\LIDARPoints.csv";
        fIleOperator = new FIleOperator();
        fIleOperator.read(lidarPath,Constant.LIDAR_POINT);
        fIleOperator.read(flightPath,Constant.FLIGHT_PATH);
        flighPathList = fIleOperator.getFlightPathList();
        lidarPathList = fIleOperator.getLidarPointList();
    }

    @Test
    public void process()
    {
        //String mapperPath = "C:\\Users\\Supun\\IdeaProjects\\LidarSimulation\\sample\\FlightPath.csv";
        Mapper mapper = new Mapper(flighPathList,lidarPathList);
        mapper.process();
        //fIleOperator.write();

    }
}
