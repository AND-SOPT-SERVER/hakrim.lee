package org.sopt.seminar1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public boolean create(final String body){
        if(body.length() <= 30){
            Diary diary = new Diary(null, body);
            diaryRepository.save(diary);
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
    }
    public void update(long id, String body) {
        diaryRepository.update(id, body);
    }
    public void restore(long id) {
        diaryRepository.restore(id);
    }
    public void fetchDiary(){
        diaryRepository.fetchDiary();
    }
}
