package cs211.project.services;

public interface DataSource<Text> {
    Text readData();
    void writeData(Text text);
}
