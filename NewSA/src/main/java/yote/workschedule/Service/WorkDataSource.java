package yote.workschedule.Service;

import yote.workschedule.Model.Work;
import yote.workschedule.Model.WorkList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WorkDataSource implements DataSource<WorkList> {
    private String directory;
    private String filename;

    public WorkDataSource(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
    }

    public WorkDataSource(){
        this("Data","Work.csv");
    }

    @Override
    public WorkList readData() {
        WorkList workList = new WorkList();
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                var date = data[1];
                String time = data[2];
                workList.add(new Work(name,date,time));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workList;
    }

    @Override
    public void writeData(WorkList workList) {
        //TODO change to relative path
        String path = directory + File.separator + filename;
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;


        try (FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(fw)) {

            writer.write(workList.toCsv());

//            for (String line : lines) {
//                writer.append(line);
//                writer.newLine();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//        try {
//            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
//            bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(foodList.toCsv());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bufferedWriter.close();
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
}
