package com.example.cpp.gustureunlock;

/**
 * Created by cpp on 2015/7/19.
 */
public class Point {
    public float x;
    public  float y;
    public int state;

    public static  final  int STATE_NOMAL = 0;
    public static  final  int STATE_PRESS = 1;
    public static  final  int STATE_ERROR = 2;


    public Point(float x, float y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }
}
