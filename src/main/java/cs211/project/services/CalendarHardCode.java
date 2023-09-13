package cs211.project.services;

import cs211.project.models.Calendar;
import cs211.project.models.CalendarList;

public class CalendarHardCode implements DataSource{
    @Override
    public CalendarList readData() {
        CalendarList calendarList = new CalendarList();
        Calendar cld1 = new Calendar("Test", "Samsung", 1694067658607L, 1694067688607L);
        Calendar cld2 = new Calendar("Test2", "Huawei", 1694067658607L, 1694067688607L);
        Calendar cld3 = new Calendar("Test3", "Apple", 1694067658607L, 1694067688607L);
        calendarList.addNewCalendar(cld1);
        calendarList.addNewCalendar(cld2);
        calendarList.addNewCalendar(cld3);
        return calendarList;
    }

    @Override
    public void writeData(Object o) {

    }
}
