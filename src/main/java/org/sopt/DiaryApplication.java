package org.sopt.diary;

import org.sopt.seminar1.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
//SpringBoot 시작점
public class DiaryApplication {
    //자바 Application 실행
    public static void main(String[] args){
        SpringApplication.run(DiaryApplication.class, args);
    }


    //TODO interface는 뭔가
    public interface UI {
        //TODO 위치변경
        static class UIException extends RuntimeException {
            public UIException(String message) {
                super(message);
            }
        }
        static class InvalidInputException extends UIException {
            public InvalidInputException(String message) {
                super(message);
            }
        }
    }
    public interface DB {
        static class DbException extends RuntimeException{
            public DbException(String message){
                super(message);
            }
        }

        static class DataNotFound extends DbException {
            public DataNotFound(String message) {
                super(message);
            }
        }
    }

}
