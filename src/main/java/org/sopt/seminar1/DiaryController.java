package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;

//Controller 가 클라와 서버의 연결지점 (api)
public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        //클라로부터 들어온 값의 유효성 생략
        diaryService.create(body);
    }

    final void delete(final String id) {
        long diaryId = Long.parseLong(id);
        diaryService.delete(diaryId);
    }

    final void patch(final String id, final String body) {

    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}
