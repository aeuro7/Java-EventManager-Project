package cs211.project.services;

import cs211.project.models.eventHub.Calendar;
import cs211.project.models.eventHub.CalendarList;

import java.io.*;

public class CalendarDataSource implements DataSource<CalendarList> {
    private String fileDirectoryName;
    private String fileName;

    public CalendarDataSource(String fileDirectoryName, String fileName) {
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
    public CalendarList readData() {
        CalendarList calendarList = new CalendarList();
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
                String name = data[0];
                String event = data[1];
                String faction = data[2];
                long startTime = Long.parseLong(data[3]);
                long dueTime = Long.parseLong(data[4]);
                boolean status = Boolean.parseBoolean(data[5]);
                String info = (data.length == 7) ? data[6] : "";

                calendarList.addNewCalendar(name, event, faction, startTime, dueTime, status, info);
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
        return calendarList;
    }

    @Override
    public void writeData(CalendarList calendarList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Calendar calendar : calendarList.getCalendars()) {
                String line = calendar.getCalendarName() + ","
                        + calendar.getEventID() + ","
                        + calendar.getFaction() + ","
                        + calendar.getStartTime() + ","
                        + calendar.getDueTime() + ","
                        + calendar.isDone() + ","
                        + calendar.getDetail();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
