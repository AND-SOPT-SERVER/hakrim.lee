package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();
    private final FileRepository fileRepository = new FileRepository();
    private int updateCounter = 0;

    public boolean create(final String body){
        final long id;
        if(body.length() <= 30){
            Diary diary = new Diary(null, body);
            id = diaryRepository.save(diary);
            fileRepository.write(new Diary(id, body));
            fileRepository.writeNumbering(id);
            return true;
        }else{
            return false;
        }
    }

    public List<Diary> getDiaryList(){
        return diaryRepository.findAll();
    }

    public void delete(long id){
        diaryRepository.deleteById(id);
        fileRepository.backupById(id);
        fileRepository.deleteById(id);
    }

    public void update(long id, String body) {
        updateCounter += 1;
        if(updateCounter<2){
            fileRepository.write(new Diary(id, body));
            diaryRepository.update(id, body);
        }
    }

    public void restore(long id) {
        final Diary restored;
        restored = fileRepository.restoreById(id);
        if(restored != null){
            diaryRepository.restore(restored);
        }
    }

    public void fetchDiary(){
        long numbering = fileRepository.fetchNumbering();
        //TODO problem
        ArrayList<Diary> diaries = fileRepository.fetchDiary();
        diaryRepository.fetchDiary(numbering, diaries);
    }
}
