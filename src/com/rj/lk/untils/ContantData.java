package com.rj.lk.untils;

import com.rj.lk.domain.Order;
import com.rj.lk.domain.User;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by luokai on 2017/4/15.
 */
public class ContantData {
    private static User user;
    private static Double addressX;
    private static Double addressY;
    private static String bicycleId;
    private static String password;
    private static String orderId;
    private static Order order;
    private static List<Map<String,Object>> bicycles;

    private static List<Map<String,Object>> list;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ContantData.user = user;
    }

    public static Double getAddressX() {
        return addressX;
    }

    public static void setAddressX(Double addressX) {
        ContantData.addressX = addressX;
    }

    public static Double getAddressY() {
        return addressY;
    }

    public static void setAddressY(Double addressY) {
        ContantData.addressY = addressY;
    }

    public static String getBicycleId() {
        return bicycleId;
    }

    public static void setBicycleId(String bicycleId) {
        ContantData.bicycleId = bicycleId;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ContantData.password = password;
    }

    public static List<Map<String,Object>> getList() {
        return list;
    }

    public static String getOrderId() {
        return orderId;
    }

    public static void setOrderId(String orderId) {
        ContantData.orderId = orderId;
    }

    public static Order getOrder() {
        return order;
    }

    public static void setOrder(Order order) {
        ContantData.order = order;
    }

    public static List<Map<String, Object>> getBicycles() {
        return bicycles;
    }

    public static void setBicycles(List<Map<String, Object>> bicycles) {
        ContantData.bicycles = bicycles;
    }

    public static void setList(List<Map<String,Object>> list) {
        for(Map map:list){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date= new Date((long)map.get("createTime"));
            map.put("createTime", sdf.format(date));
        }
        ContantData.list = list;
    }

    public static void formatJson(String json){

        try {
            JSONTokener jsonParser = new JSONTokener(json);
            JSONObject person = (JSONObject) jsonParser.nextValue();
            JSONObject data=person.getJSONObject("data");
            user=new User();
            user.setId(data.getInt("id"));
            user.setPhone(data.getString("phone"));
            user.setName(data.getString("name"));
            user.setSex(data.getString("sex"));
            user.setValid(data.getBoolean("valid"));
            user.setX("123");
            user.setY("321");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}