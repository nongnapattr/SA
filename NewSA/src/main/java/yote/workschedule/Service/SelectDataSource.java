package yote.workschedule.Service;

import yote.workschedule.Model.Select;
import yote.workschedule.Model.SelectList;
import yote.workschedule.Model.Work;
import yote.workschedule.Model.WorkList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SelectDataSource implements DataSource<SelectList> {
    private String directoryName;
    private String filename;

    public SelectDataSource() {
        this("Data", "Select.csv");
    }

    public SelectDataSource(String directoryName, String filename) { // test use
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIfNotExist();
    }

    private void initialFileIfNotExist() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = directoryName + File.separator + filename;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public SelectList readData() {
        SelectList selectList = new SelectList();
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");

                selectList.add(new Select(
                        data[1].trim(),
                        data[2].trim(), // name
                        data[3].trim(), // date
                        data[4].trim() // time
                ));

//                String name = data[1];
//                var date = data[2];
//                String time = data[3];
//                selectList.add(new Select(name,date,time));

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
            return selectList;
        }
    }

    @Override
    public void writeData(SelectList selectList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(selectList.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

