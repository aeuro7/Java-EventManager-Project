package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.users.User;

import java.io.*;

public class EventDataSource implements DataSource<EventList>{
    private String fileDirectoryName;
    private String fileName;
    public EventDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }
    @Override
    public EventList readData() {
        EventList eventList = new EventList();
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                Event newEvent = new Event(data[0].trim(), Long.parseLong(data[1]), Long.parseLong(data[2]), data[3].trim(),
                        Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]), data[7].trim(),
                        Double.parseDouble(data[8]), data[9].trim(), data[10]);
                eventList.addEvent(newEvent);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return eventList;
    }

    @Override
    public void writeData(EventList writeEventList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Event event: writeEventList.getAllEvent()){
                String line = event.getEventName() + ","
                        + event.getStartTime() + ","
                        + event.getDueTime() + ","
                        + event.getInfo() + ","
                        + event.getMaxSeat() + ","
                        + event.getLeftSeat() + ","
                        + event.getBookedSeat() + ","
                        + event.getLocation() + ","
                        + event.getLimitStaffPT() + ","
                        + event.getEventOwner() + ","
                        + event.getEventID();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
