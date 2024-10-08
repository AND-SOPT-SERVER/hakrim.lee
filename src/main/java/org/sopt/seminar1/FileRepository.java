package org.sopt.seminar1;

import java.io.*;
import java.util.ArrayList;

public class FileRepository {
    private final String dirDiary = "diary";
    private final String dirBackup = "backup";
    private final String fileFormat = ".txt";

    public void write(final Diary diary) {
        final String path = String.format("./%s/%s%s", dirDiary, diary.getId(), fileFormat);
        final File file = new File(path);

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(diary.getBody());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNumbering(final long numbering) {
        final String path = String.format("./num%s", fileFormat);

        try {
                FileWriter writer = new FileWriter(path);
                writer.write(Long.toString(numbering));
                writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void deleteById(long id) {
        final String path = String.format("./%s/%s%s", dirDiary, id, fileFormat);
        final File file = new File(path);
        file.delete();
    }

    void backupById(long id) {
        final String backupDir = String.format("./%s", dirBackup);
        final File dir = new File(backupDir);
        final String path = String.format("./%s/%s%s", dirDiary, id, fileFormat);
        final String backupAddress = String.format("./%s/%s%s", dirBackup, id, fileFormat);

        if(!dir.exists()){
            dir.mkdir();
        }
        final File file = new File(path);
        file.renameTo(new File(backupAddress));
    }

    Diary restoreById(long id) {
        final String backupDir = String.format("./%s", dirBackup);
        final File dir = new File(backupDir);
        final String path = String.format("./%s/%s%s", dirDiary, id, fileFormat);
        final String backupPath = String.format("./%s/%s%s", dirBackup, id, fileFormat);
        final File file = new File(backupPath);

        if(!dir.exists()){
            dir.mkdir();
        }

        Diary restored = null;
        if (file.renameTo(new File(path))) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String body = br.readLine();
                restored = new Diary(id, body);
            } catch (Exception e) {
                //TODO Exception handling
                throw new RuntimeException(e);
            }
        }
        return restored;
    }

    ArrayList<Diary> fetchDiary() {
        String directoryPath = String.format("./%s", dirDiary);
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        ArrayList<Diary> diaries = new ArrayList<>();

        if(!directory.exists()){
            directory.mkdir();
        }

        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    String fileName = file.getName();
                    long id = Long.parseLong(fileName.replaceAll(fileFormat, ""));
                    try (BufferedReader br = new BufferedReader(new FileReader(directoryPath + "/" + fileName))) {
                        String body = br.readLine();
                        diaries.add(new Diary(id, body));
                    } catch (Exception e) {
                        //TODO Exception handling
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return diaries;
    }
    long fetchNumbering(){
        final String path = String.format("./num%s", fileFormat);
        long numbering = -1;
        File file= new File(path);

        if(!file.exists()){
            try {
                FileWriter writer = new FileWriter(path);
                writer.write("0");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            numbering = Long.parseLong(line);
        } catch (Exception e) {
            //TODO Exception handling
            throw new RuntimeException(e);
        }


        return numbering;
    }
}