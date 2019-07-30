package com.yjy.core.result;

public class ResultUtil {
	
	@SuppressWarnings("rawtypes")
	public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
	
	public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result<?> success() {
        return success(null);
    }
}