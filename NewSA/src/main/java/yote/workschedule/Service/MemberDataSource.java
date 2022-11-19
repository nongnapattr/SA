//package yote.workschedule.Service;
//
//import yote.workschedule.Model.MemberList;
//import yote.workschedule.Model.Member;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
//public class MemberDataSource implements DataSource<MemberList> {
//    private String directory;
//    private String filename;
//
//    public MemberDataSource(String directory, String filename) {
//        this.directory = directory;
//        this.filename = filename;
//    }
//
//    public MemberDataSource(){
//        this("Data","Member.csv");
//    }
//
//    @Override
//    public MemberList readData() {
//        MemberList memberList = new MemberList();
//        String path = directory + File.separator + filename;
//        File file = new File(path);
//        FileReader fileReader = null;
//        BufferedReader bufferedReader = null;
//        try {
//            fileReader = new FileReader(file, StandardCharsets.UTF_8);
//            bufferedReader = new BufferedReader(fileReader);
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] data = line.split(",");
//                String name = data [0];
//                int c_id = Integer.parseInt(data[1]);
//                String userName = data[2];
//                String password = data[3];
//                String phoneNumber = data[4];
//                memberList.add(new Member(name,c_id,userName,password,phoneNumber));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bufferedReader.close();
//                fileReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return memberList;
//    }
//
//    @Override
//    public void writeData(MemberList memberList) {
//        //TODO change to relative path
//        String path = directory + File.separator + filename;
//        File file = new File(path);
//        FileWriter fileWriter = null;
//        BufferedWriter bufferedWriter = null;
//
//
//        try (FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
//             BufferedWriter writer = new BufferedWriter(fw)) {
//
//            writer.write(memberList.toCsv());
//
////            for (String line : lines) {
////                writer.append(line);
////                writer.newLine();
////            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
////        try {
////            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
////            bufferedWriter = new BufferedWriter(fileWriter);
////            bufferedWriter.write(foodList.toCsv());
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                bufferedWriter.close();
////                fileWriter.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
//}
