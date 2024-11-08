package org.sopt.utils.validators;

import org.sopt.DiaryApplication;
import org.sopt.diary.api.request.DiaryPostRequest;
import org.sopt.utils.enums.DiaryCategory;

public class DiaryApiValidator {

    public static void isPostRequestValid(DiaryPostRequest request){
        isTitleLengthValid(request.title());
        isContentLengthValid(request.content());
        isCategoryValid(request.category());
    }

    private static void isTitleLengthValid(String content) {
        if (content.length() > 10) {
            throw new DiaryApplication.UI.InvalidInputException("일기 내용은 30자 이하만 가능합니다.");
        } else if (content.isEmpty()){
            throw new DiaryApplication.UI.InvalidInputException("일기 내용을 1자 이상 입력해야합니다.");
        }
    }

    public static void isContentLengthValid(String content) {
        if (content.length() > 30) {
            throw new DiaryApplication.UI.InvalidInputException("일기 제목은 10자 이하만 가능합니다.");
        } else if (content.isEmpty()){
            throw new DiaryApplication.UI.InvalidInputException("일기 제목을 1자 이상 입력해야합니다.");
        }
    }

    private static void isCategoryValid(String category){
        try {
            DiaryCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            // 명세하진 않았던 부분
            throw new DiaryApplication.UI.InvalidInputException("정해진 카테고리에서 벗어납니다.");
        }
    }


}
