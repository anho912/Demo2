/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.an.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 疯狂的蜗牛君_
 */
public class Results extends HashMap<String, Object> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public Results() {
    	//错误码
    	put("code", 0);
    }
    
    public static Results error() {
        return error(500, "未知异常，请联系管理员");
    }
    
    public static Results error(String msg) {
        return error(500, msg);
    }
    
    public static Results error(int code, String msg) {
        Results r = new Results();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    
    public static Results ok(String msg) {
        Results r = new Results();
        r.put("msg", msg);
        return r;
    }
    
    public static Results ok(Map<String, Object> map) {
        Results r = new Results();
        r.putAll(map);
        return r;
    }
    
    public static Results ok() {
        return new Results();
    }
    public Results put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

