package com.along.studymongo.msg;

import lombok.Data;

import java.util.List;

/**
 * Created by ace on 2017/8/23.
 */
@Data
public class Response<T> {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer FAIL_CODE = 500;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response() {
    }
    int status;
    String message;
    T data;
    PageData<T> pageData;

    public static Response<?> success() {
        return success(SUCCESS);
    }

    public static Response<?> success(String msg) {
        return success(msg, null);
    }
    public static Response<?> string(String msg) {
        return success(SUCCESS, msg);
    }
    public static <E> Response<E> success(E data) {
        return success(null, data);
    }

    public static <E> Response<E> success(String message, E data) {
        return response(SUCCESS_CODE, message, data);
    }
    public static Response<?> response(int code, String message) {
        return response(code, message, null);
    }
    public static <E> Response<E> response(int code, String message, E data) {
        Response<E> resp=new Response<E>();
        resp.setStatus(code);
        resp.setMessage(message);
        resp.setData(data);
        return resp;
    }
    public static <E> Response<E> page(long total, List<E> rows) {
        Response<E> response =new Response<E>();
        response.setStatus(SUCCESS_CODE);
        response.setMessage(SUCCESS);
        response.setPageData(new PageData<E>(total, rows));
        return response;
    }

    public static Response<?> fail() {
        return fail(FAIL);
    }

    public static Response<?> fail(String msg) {
        return fail(msg, null);
    }

    public static <E> Response<E> fail(E data) {
        return fail(null, data);
    }

    public static <E> Response<E> fail(String message, E data) {
        return response(FAIL_CODE, message, data);
    }

}
