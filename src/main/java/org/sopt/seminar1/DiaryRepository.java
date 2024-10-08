package org.sopt.seminar1;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();

    public long save(final Diary diary){
        //채번과정
        final long id = numbering.addAndGet(1);
        //저장과정
        storage.put(id, diary.getBody());
        return id;
    }

    List<Diary> findAll(){
        //Diary 담을 자료구조
        List<Diary> diaryList = new ArrayList<Diary>();

        // 저장한 값을 불러오는 반복구조
        for(long index = 1; index <= numbering.longValue(); index++){
            final String body = storage.get(index);

            //불러온 값을 구성한 자료구조로 이관
            if(body != null){
                diaryList.add(new Diary(index, body));
            }
        }

        // 불러온 자료구조를 응답
        return diaryList;
    }
    void deleteById(long id){
        storage.remove(id);
    }

    void update(long id, String body){
        storage.put(id, body);
    }

    void restore(Diary diary) {
        storage.put(diary.getId(), diary.getBody());
    }

    void fetchDiary(long idx, ArrayList<Diary> diaries){
        for (Diary diary : diaries) {
            storage.put(diary.getId(), diary.getBody());
        }
        numbering.addAndGet(idx);
    }

}


