package com.sc0ville.app;

import com.sc0ville.app.model.DataPoint;
import com.sc0ville.app.model.FlightPath;
import com.sc0ville.app.model.LidarPoint;
import com.sc0ville.app.model.MapPoint;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sc0ville.app.Constant.NEW_LINE_SEPARATOR;

/**
 * FileOperator
 *
 * Created by Supun on 28/07/2019.
 */

public class FIleOperator
{

    private String flightPath;
    private String lidarPath;
    private List<DataPoint> flightPathList;
    private List<DataPoint> lidarPointList;

    public FIleOperator()
    {

    }

    public void read(String filePath, String type)
    {
        List<DataPoint> dataPointList = new ArrayList<>();

        try
        {
            CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT);
            List<CSVRecord> list = parser.getRecords();
            for (CSVRecord record : list)
            {
                DataPoint dataPoint = null;

                String[] arr = new String[record.size()];
                int i = 0;

                for (String str : record)
                {
                    arr[i++] = str;
                }
                if(Constant.FLIGHT_PATH.equals(type))
                {
                    double x = Double.parseDouble(arr[0]);
                    double y = Double.parseDouble(arr[1]);
                    dataPoint = new FlightPath(x,y);
                    dataPointList.add(dataPoint);
                }
                else if(Constant.LIDAR_POINT.equals(type))
                {
                    double angle = Double.parseDouble(arr[0]);
                    int value = Integer.parseInt(arr[1]);
                    dataPoint = new LidarPoint(angle,value);
                    dataPointList.add(dataPoint);
                }
            }
            if(Constant.FLIGHT_PATH.equals(type))
            {
               setFlightPathList(dataPointList);
            }
            else if(Constant.LIDAR_POINT.equals(type))
            {
                setLidarPointList(dataPointList);
            }
            parser.close();
        }catch (Exception ex)
        {
            System.out.println("Error reading CSV");
        }
    }

    public void write(String path, List<MapPoint> list)
    {
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try
        {
            fileWriter = new FileWriter(path);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            for(MapPoint mapPoint:list)
            {
                List mapList = new ArrayList();
                mapList.add(String.valueOf(mapPoint.getxStart()));
                mapList.add(String.valueOf(mapPoint.getyStart()));
                mapList.add(String.valueOf(mapPoint.getxEnd()));
                mapList.add(String.valueOf(mapPoint.getyEnd()));
                csvFilePrinter.printRecord(mapList);
            }
        }catch (Exception ex)
        {
            System.out.println("Error writing the file");
        }
        finally {
            try
            {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            }catch (IOException ex)
            {
                System.out.println("Error closing the writers");
            }
        }
    }

    public String getFlightPath()
    {
        return flightPath;
    }

    public void setFlightPath(String flightPath)
    {
        this.flightPath = flightPath;
    }

    public String getLidarPath()
    {
        return lidarPath;
    }

    public void setLidarPath(String lidarPath)
    {
        this.lidarPath = lidarPath;
    }

    public List<DataPoint> getFlightPathList()
    {
        if(flightPathList == null)
        {
            return new ArrayList<>();
        }else
            {
            return flightPathList;
        }

    }

    public void setFlightPathList(List<DataPoint> flightPathList)
    {
        this.flightPathList = flightPathList;
    }

    public List<DataPoint> getLidarPointList()
    {
        if(lidarPointList==null)
        {
            return new ArrayList<>();
        }
        else
            {
            return lidarPointList;
        }

    }

    public void setLidarPointList(List<DataPoint> lidarPointList)
    {
        this.lidarPointList = lidarPointList;
    }

    public static void setOperator(String args[])
    {
        FIleOperator fIleOperator = new FIleOperator();
        if(Constant.FLIGHT_PATH.equals(args[0]))
        {
            fIleOperator.setFlightPath(args[1]);
            fIleOperator.read(fIleOperator.getFlightPath(), Constant.FLIGHT_PATH);

        }
        if(Constant.LIDAR_POINT.equals(args[2]))
        {
            fIleOperator.setLidarPath(args[3]);
            fIleOperator.read(fIleOperator.getLidarPath(),Constant.LIDAR_POINT);
        }
    }


    public static void main(String[] args)
    {
        if(args.length != 4)
        {
            System.out.println("Invalid arguments");
        } else
            {
            setOperator(args);
        }

    }
}
