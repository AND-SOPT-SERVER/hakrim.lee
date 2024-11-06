package org.sopt.diary.api.response;
import org.sopt.diary.domain.Diary;

public class DiaryDetailResponse {
    private Diary diary;

    public DiaryDetailResponse(Diary diary){
        this.diary = diary;
    }

    public Diary getDiary(){
        return diary;
    }
}
