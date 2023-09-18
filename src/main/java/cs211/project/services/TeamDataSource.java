package cs211.project.services;

import cs211.project.models.chats.Chat;
import cs211.project.models.chats.ChatList;
import cs211.project.models.chats.Message;
import cs211.project.models.team.Team;
import cs211.project.models.team.TeamList;
import cs211.project.models.team.TeamStaff;

import java.io.*;

public class TeamDataSource implements DataSource<TeamList> {
    private String fileDirectoryName;
    private String fileName;

    public TeamDataSource(String fileDirectoryName, String fileName) {
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
    public TeamList readData() {
        TeamList teamList = new TeamList();
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            Team currentTeam = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    currentTeam = new Team(data[1], data[0], Long.parseLong(data[2]), data[3]); //data[0] is a id data[1] is a name
                    teamList.addTeam(currentTeam);
                } else if (currentTeam != null && data[0].equals(currentTeam.getEventID())) {
                    TeamStaff staff = new TeamStaff(data[1], data[2]);
                    currentTeam.addTeamStaff(staff);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teamList;
    }

    @Override
    public void writeData(TeamList teamList) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Team team : teamList.getAllTeams()) {
                String teamInfo = team.getEventID() + ","
                        + team.getNameTeam() + ","
                        + team.getMaxStaff() + ","
                        + team.getLeaderName();
                bufferedWriter.write(teamInfo);
                bufferedWriter.newLine();
                for (TeamStaff staff : team.getAllTeamStaff()) {
                    String line = team.getEventID() + ","
                            + staff.getName() + ","
                            + staff.getRole();
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
