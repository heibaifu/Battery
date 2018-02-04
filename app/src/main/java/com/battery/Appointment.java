package com.battery;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by szl on 2018/2/4.
 * 描述预约信息
 * 注释带星号*的表示在数据库中有对应字段
 */

public class Appointment {

    private int id; // 数据库自增id *
    private int userId; // 预约用户id *
    private int vehicleId; // 将要被换电的车辆id *
    private int stationId; // 预约电站id *
    private int newBatteryId; // 新电池id *
    private String date; // 预约时间 *
    private int complete; // 是否完成状态 *
    private Station station; // 接受预约的电站
    private Battery battery; // 为用户准备的电池

    /**
     * 根据电站id，向服务器请求该电站数据
     */
    public void loadStation() {
        RequestBody body = new FormBody.Builder()
                .add("id", Integer.toString(stationId)).build();
        HttpUtil.sendRequest(Constants.STATIONBYIDADDRESS, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                station = new Gson().fromJson(responseData, Station.class);
                Log.d("Appointment", "电站名:"+station.getName());
            }
        });
    }

    /**
     * 根据电池id，向服务器请求新电池数据
     */
    public void loadNewBattery() {
        RequestBody body = new FormBody.Builder()
                .add("id", Integer.toString(newBatteryId)).build();
        HttpUtil.sendRequest(Constants.BATTERYBYIDADDRESS, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                battery = new Gson().fromJson(responseData, Battery.class);
                Log.d("Appointment", "新电池:"+battery.getNumber());
            }
        });
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getNewBatteryId() {
        return newBatteryId;
    }

    public void setNewBatteryId(int newBatteryId) {
        this.newBatteryId = newBatteryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
