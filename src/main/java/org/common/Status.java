package org.common;

import ch.qos.logback.core.status.StatusUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status<T> {
    private int status;
    private String message;
    private T data;
//    private Map map = new HashMap();

    public static <T> Status<T> ok(T data){
        Status<T> status1 = new Status<>();
        status1.data = data;
        status1.status = 0;
        return status1;
    }

    public  static <T> Status<T> error(String msg){
        Status<T> status1 = new Status<>();
        status1.message = msg;
        status1.status = 1;
        return status1;
    }
}
