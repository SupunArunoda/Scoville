package com.sc0ville.app;

import com.sc0ville.app.model.DataPoint;
import com.sc0ville.app.model.FlightPath;
import com.sc0ville.app.model.LidarPoint;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileOperatorTest
{
    List<DataPoint> flighPathList = new ArrayList<>();
    List<DataPoint> lidarPathList = new ArrayList<>();
    @Before
    public void generateData()
    {
        flighPathList.add(new FlightPath(0,1));
        flighPathList.add(new FlightPath(12.87234,3.62299));
        flighPathList.add(new FlightPath(1,1));
        flighPathList.add(new FlightPath(13.91074,4.694889));
        flighPathList.add(new FlightPath(2,1));
        flighPathList.add(new FlightPath(14.91222,5.696368));

        lidarPathList.add(new LidarPoint(0,533));
        lidarPathList.add(new LidarPoint(1.09668,8964));
        lidarPathList.add(new LidarPoint(1.771484,8967));
        lidarPathList.add(new LidarPoint(2.446777,8970));
        lidarPathList.add(new LidarPoint(3.121582,8976));
        lidarPathList.add(new LidarPoint(3.796875,8982));
    }

    @Test
    public void readCSV()
    {
        String flightPath = "C:\\Users\\Supun\\IdeaProjects\\LidarSimulation\\sample\\FlightPath_Test.csv";
        String lidarPath = "C:\\Users\\Supun\\IdeaProjects\\LidarSimulation\\sample\\LIDARPoints_Test.csv";
        FIleOperator fIleOperator = new FIleOperator();
        fIleOperator.read(lidarPath,Constant.LIDAR_POINT);
        fIleOperator.read(flightPath,Constant.FLIGHT_PATH);
        assertEquals(flighPathList,fIleOperator.getFlightPathList());
        assertEquals(lidarPathList,fIleOperator.getLidarPointList());
    }

}
