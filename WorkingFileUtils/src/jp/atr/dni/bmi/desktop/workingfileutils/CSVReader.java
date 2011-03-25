package jp.atr.dni.bmi.desktop.workingfileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogData;
import jp.atr.dni.bmi.desktop.neuroshareutils.SegmentData;
import jp.atr.dni.bmi.desktop.neuroshareutils.TextEventData;

/**
 *
 * @author kharada
 * @version 2011/03/11
 */
public class CSVReader {

    public ArrayList<AnalogData> getAnalogData(String fileFullPath) throws IOException {

        ArrayList<String> lines = readCSVFile(fileFullPath);
        ArrayList<AnalogData> data = new ArrayList<AnalogData>();
        double samplingRate = 0;

        for (int ii = 0; ii < lines.size(); ii++) {

            String[] parts = lines.get(ii).split(",");

            if (ii == 0) {
                // Skip header.
                samplingRate = Double.parseDouble(parts[1]);
                continue;
            }

            double timeStamp = 0;

            ArrayList<Double> values = new ArrayList<Double>();

            boolean addFlag = true;

            for (int valNDX = 0; valNDX < parts.length; valNDX++) {
                if (valNDX == 0) {
                    // Skip timeStamp.
                    timeStamp = Double.parseDouble(parts[0]);
                    continue;
                }

                try {
                    Double d = Double.parseDouble(parts[valNDX]);
                    values.add(d);
                } catch (NumberFormatException ex) {

                    if (!(parts[valNDX].equals("NaN"))) {
                        throw ex;
                    }

                    // if data is NaN and values exist, then register data.
                    if (values.size() != 0) {
                        data.add(new AnalogData(timeStamp, values.size(), values));
                    }

                    timeStamp = timeStamp + (values.size() + 1) / samplingRate;
                    values.clear();

                    if (valNDX == parts.length - 1) {
                        // last col is NaN.
                        addFlag = false;
                    }
                }
            }

            if (addFlag) {
                data.add(new AnalogData(timeStamp, values.size(), values));
            } else {
                addFlag = true;
            }

        }

        return data;
    }

    public ArrayList<TextEventData> getTextEventData(String fileFullPath) throws IOException {

        ArrayList<String> lines = readCSVFile(fileFullPath);
        ArrayList<TextEventData> data = new ArrayList<TextEventData>();

        for (int ii = 0; ii < lines.size(); ii++) {

            String[] parts = lines.get(ii).split(",");

            if (ii == 0) {
                // Skip header.
                continue;
            }

            double timeStamp = Double.parseDouble(parts[0]);

            String value = parts[1];

            data.add(new TextEventData(timeStamp, value.length(), value));

        }

        return data;
    }

    public ArrayList<Double> getNeuralData(String fileFullPath) throws IOException {

        ArrayList<String> lines = readCSVFile(fileFullPath);
        ArrayList<Double> timeStamps = new ArrayList<Double>();

        for (int dataItemNum = 0; dataItemNum < lines.size(); dataItemNum++) {

            String[] parts = lines.get(dataItemNum).split(",");

            if (dataItemNum == 0) {
                // Skip header.
                continue;
            }

            double timeStamp = Double.parseDouble(parts[0]);
            timeStamps.add(timeStamp);
        }

        return timeStamps;
    }

    public SegmentData getSegmentData(String fileFullPath)
            throws IOException {
        ArrayList<String> lines = readCSVFile(fileFullPath);

        ArrayList<Long> sampleCounts = new ArrayList<Long>();
        ArrayList<Double> timeStamps = new ArrayList<Double>();
        ArrayList<Long> unitIDS = new ArrayList<Long>();
        ArrayList<ArrayList<Double>> vals = new ArrayList<ArrayList<Double>>();

        double samplingRate = 0;
        boolean addFlag = true;

        for (int x = 0; x < lines.size(); x++) {

            String[] parts = lines.get(x).split(",");

            if (x == 0) {
                samplingRate = Double.parseDouble(parts[1]);
                continue;
            }

            double timeStamp = 0;
            long unitID = 0;
            ArrayList<Double> values = new ArrayList<Double>();

            for (int valNDX = 0; valNDX < parts.length; valNDX++) {
                if (valNDX == 0) {
                    timeStamp = Double.parseDouble(parts[0]);
                    continue;
                }
                if (valNDX == 1) {
                    unitID = Integer.parseInt(parts[1]);
                    continue;
                }

                try {
                    Double d = Double.parseDouble(parts[valNDX]);
                    values.add(d);
                } catch (NumberFormatException ex) {

                    if (!(parts[valNDX].equals("NaN"))) {
                        throw ex;
                    }

                    // if data is NaN and values exist, then register data.
                    if (values.size() != 0) {
                        vals.add(values);
                        timeStamps.add(timeStamp);
                        sampleCounts.add(((Integer) values.size()).longValue());
                        unitIDS.add(unitID);
                    }

                    timeStamp = timeStamp + (values.size() + 1) / samplingRate;
                    values.clear();

                    if (valNDX == parts.length - 1) {
                        // last col is NaN.
                        addFlag = false;
                    }
                }
            }
            if (addFlag) {
                vals.add(values);
                timeStamps.add(timeStamp);
                sampleCounts.add(((Integer) values.size()).longValue());
                unitIDS.add(unitID);

            } else {
                addFlag = true;
            }

        }

        return new SegmentData(sampleCounts, timeStamps, unitIDS, vals);
    }

    private ArrayList<String> readCSVFile(String fileFullPath) {

        ArrayList<String> rtnList = new ArrayList<String>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileFullPath));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                rtnList.add(line);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            rtnList = null;
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            rtnList = null;
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rtnList;

    }

    public TSData getTSData(String workingFilePath) {

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }

        TSData data = new TSData();
        ArrayList<String> lines = readCSVFile(workingFilePath);

        double samplingRate = 0;

        for (int ii = 0; ii < lines.size(); ii++) {

            String[] parts = lines.get(ii).split(",");

            if (ii == 0) {
                // Skip header.
                samplingRate = Double.parseDouble(parts[1]);
                data.setSamplingRate(samplingRate);
                continue;
            }

            double timeStamp = 0;

            ArrayList<Double> values = new ArrayList<Double>();

            boolean addFlag = true;

            for (int valNDX = 0; valNDX < parts.length; valNDX++) {
                if (valNDX == 0) {
                    // Skip timeStamp.
                    timeStamp = Double.parseDouble(parts[0]);
                    continue;
                }

                try {
                    Double d = Double.parseDouble(parts[valNDX]);
                    values.add(d);
                } catch (NumberFormatException ex) {

                    if (!(parts[valNDX].equals("NaN"))) {
                        throw ex;
                    }

                    // if data is NaN and values exist, then register data.
                    if (values.size() != 0) {
                        data.addTimeStamp(timeStamp);
                        data.addValues(values);
                    }

                    timeStamp = timeStamp + (values.size() + 1) / samplingRate;
                    values.clear();

                    if (valNDX == parts.length - 1) {
                        // last col is NaN.
                        addFlag = false;
                    }
                }
            }

            if (addFlag) {
                data.addTimeStamp(timeStamp);
                data.addValues(values);
            } else {
                addFlag = true;
            }

        }

        return data;
    }

    public TOData getTOData(String workingFilePath) {

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }

        TOData data = new TOData();
        ArrayList<String> lines = readCSVFile(workingFilePath);

        for (int ii = 0; ii < lines.size(); ii++) {

            String[] parts = lines.get(ii).split(",");

            if (ii == 0) {
                // Skip header.
                continue;
            }

            boolean addFlag = true;

            try {
                Double d = Double.parseDouble(parts[0]);
                data.addTimeStamp(d);
            } catch (NumberFormatException ex) {
                if (!(parts[0].equals("NaN"))) {
                    throw ex;
                }
            }
        }
        return data;
    }

    public TIData getTIData(String workingFilePath) {

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }

        TIData data = new TIData();
        ArrayList<String> lines = readCSVFile(workingFilePath);

        double samplingRate = 0;

        for (int ii = 0; ii < lines.size(); ii++) {

            String[] parts = lines.get(ii).split(",");

            if (ii == 0) {
                // Skip header.
                samplingRate = Double.parseDouble(parts[1]);
                data.setSamplingRate(samplingRate);
                continue;
            }

            double timeStamp = 0;
            int unitID = 0;

            ArrayList<Double> values = new ArrayList<Double>();

            boolean addFlag = true;

            for (int valNDX = 0; valNDX < parts.length; valNDX++) {
                if (valNDX == 0) {
                    // Skip timeStamp.
                    timeStamp = Double.parseDouble(parts[0]);
                    continue;
                }

                if (valNDX == 1) {
                    // Skip timeStamp.
                    unitID = Integer.parseInt(parts[1]);
                    continue;
                }

                try {
                    Double d = Double.parseDouble(parts[valNDX]);
                    values.add(d);
                } catch (NumberFormatException ex) {

                    if (!(parts[valNDX].equals("NaN"))) {
                        throw ex;
                    }

                    // if data is NaN and values exist, then register data.
                    if (values.size() != 0) {
                        data.addTimeStamp(timeStamp);
                        data.addUnitID(unitID);
                        data.addValues(values);
                    }

                    timeStamp = timeStamp + (values.size() + 1) / samplingRate;
                    values.clear();

                    if (valNDX == parts.length - 1) {
                        // last col is NaN.
                        addFlag = false;
                    }
                }
            }

            if (addFlag) {
                data.addTimeStamp(timeStamp);
                data.addUnitID(unitID);
                data.addValues(values);
            } else {
                addFlag = true;
            }

        }

        return data;
    }

    public TLData getTLData(String workingFilePath) {

        // if working file didn't exists, then null.
        File file = new File(workingFilePath);
        if (!file.exists()) {
            return null;
        }

        TLData data = new TLData();
        ArrayList<String> lines = readCSVFile(workingFilePath);

        for (int ii = 0; ii < lines.size(); ii++) {

            String[] parts = lines.get(ii).split(",");

            if (ii == 0) {
                // Skip header.
                continue;
            }


            try {
                Double d = Double.parseDouble(parts[0]);
                data.addTimeStamp(d);
            } catch (NumberFormatException ex) {
                if (!(parts[0].equals("NaN"))) {
                    throw ex;
                }
            }
            
            Object d = parts[1];
            data.addValue(d);
        }

        return data;
    }
}
