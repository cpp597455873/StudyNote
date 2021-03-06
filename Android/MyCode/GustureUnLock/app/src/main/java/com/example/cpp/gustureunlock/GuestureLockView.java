package com.example.cpp.gustureunlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by cpp on 2015/7/19.
 * 九宫格解锁View，使用方法，直接添加即可，注意，这个view
 * requestInput
 * reset
 * shoowErrorPath
 * shoowErrorPathWithSometime
 * 这四个方法是对外公开的
 */
public class GuestureLockView extends View {
    Point[][] points = new Point[3][3];
    Bitmap bitmapNomal;
    Bitmap bitmapPress;
    Bitmap bitmapError;
    float r;
    boolean inited = false;
    ArrayList<Integer> password = new ArrayList<>();
    float cX;
    float cY;
    Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint errorLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    boolean showError = false;
    boolean isFinish = false;
    private InputCallBack inputCallBack;

    {
        linePaint.setColor(Color.WHITE);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeCap(Paint.Cap.BUTT);
        linePaint.setStrokeWidth(15);

        errorLinePaint.setColor(Color.RED);
        errorLinePaint.setStyle(Paint.Style.FILL);
        errorLinePaint.setStrokeCap(Paint.Cap.BUTT);
        errorLinePaint.setStrokeWidth(15);

        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    public GuestureLockView(Context context) {
        super(context);
    }

    public GuestureLockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuestureLockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onDraw(Canvas canvas) {
        if (!inited) init();
        drawPoint(canvas);
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        if (password.size() == 0) return;
        if (password.size() > 1) {
            for (int i = 1; i < password.size(); i++) {
                Point point1 = points[password.get(i - 1) / 3][(password.get(i - 1) % 3)];
                Point point2 = points[password.get(i) / 3][(password.get(i) % 3)];
                canvas.drawLine(point1.x, point1.y, point2.x, point2.y, showError ? errorLinePaint : linePaint);
                canvas.drawCircle(point1.x, point1.y, r / 2, circlePaint);
                canvas.drawCircle(point2.x, point2.y, r / 2, circlePaint);
            }
        } else {
            Point point2 = points[password.get(password.size() - 1) / 3][(password.get(password.size() - 1) % 3)];
            canvas.drawCircle(point2.x, point2.y, r / 2, circlePaint);
        }
        Point point2 = points[password.get(password.size() - 1) / 3][(password.get(password.size() - 1) % 3)];
        if (!isFinish) canvas.drawLine(cX, cY, point2.x, point2.y, linePaint);
    }

    /**
     * 根据点的状态绘制点
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (points[i][j].state == Point.STATE_NOMAL) {
                    canvas.drawBitmap(bitmapNomal, points[i][j].x - r, points[i][j].y - r, paint);
                } else if (points[i][j].state == Point.STATE_PRESS) {
                    canvas.drawBitmap(bitmapPress, points[i][j].x - r, points[i][j].y - r, paint);
                } else if (points[i][j].state == Point.STATE_ERROR) {
                    canvas.drawBitmap(bitmapError, points[i][j].x - r, points[i][j].y - r, paint);
                }
            }
        }
    }

    /**
     * 根据屏幕大小,得到相应大小的位图
     *
     * @param space
     */
    private void getBitmap(float space) {
        bitmapNomal = resize(BitmapFactory.decodeResource(getResources(), R.drawable.normal), space);
        bitmapPress = resize(BitmapFactory.decodeResource(getResources(), R.drawable.press), space);
        bitmapError = resize(BitmapFactory.decodeResource(getResources(), R.drawable.error), space);
        r = bitmapNomal.getWidth() / 2;
    }

    /**
     * 缩放 这里位图的大小是间距的4/5
     * @param bitmap
     * @param space
     * @return
     */
    private static Bitmap resize(Bitmap bitmap, float space) {
        Matrix matrix = new Matrix();
        float rate = space * 4 / (5 * bitmap.getWidth());
        matrix.postScale(rate, rate); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return resizeBmp;
    }

    /**
     * 初始化
     */
    private void init() {
        float width = getWidth();  //注意一定要给view一个显示的宽度
        float height = getHeight();//注意一定要给view一个显示的高度
        float offsetY = 0, offsetX = 0;
        float space = ((width > height) ? height : width) / 4;
        if (width >= height) {
            offsetX = Math.abs(width - height) / 2;
        } else {
            offsetY = Math.abs(width - height) / 2;
        }
        initPoint(offsetY, offsetX, space);
        getBitmap(space);
        inited = true;
    }

    //初始化各个点的位置，针对横竖屏都已经做处理
    private void initPoint(float offsetY, float offsetX, float space) {
        points[0][0] = new Point(space + offsetX, offsetY + space, Point.STATE_NOMAL);
        points[0][1] = new Point(2 * space + offsetX, offsetY + space, Point.STATE_NOMAL);
        points[0][2] = new Point(3 * space + offsetX, offsetY + space, Point.STATE_NOMAL);

        points[1][0] = new Point(space + offsetX, offsetY + 2 * space, Point.STATE_NOMAL);
        points[1][1] = new Point(2 * space + offsetX, offsetY + 2 * space, Point.STATE_NOMAL);
        points[1][2] = new Point(3 * space + offsetX, offsetY + 2 * space, Point.STATE_NOMAL);

        points[2][0] = new Point(space + offsetX, offsetY + 3 * space, Point.STATE_NOMAL);
        points[2][1] = new Point(2 * space + offsetX, offsetY + 3 * space, Point.STATE_NOMAL);
        points[2][2] = new Point(3 * space + offsetX, offsetY + 3 * space, Point.STATE_NOMAL);
    }

    /**
     * 处理触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isFinish) return true;
        float x = event.getX();
        float y = event.getY();
        cX = x;
        cY = y;

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float realDis = (float) Math.sqrt(Math.abs(x - points[i][j].x) * Math.abs(x - points[i][j].x) + Math.abs(y - points[i][j].y) * Math.abs(y - points[i][j].y));
                if (r > realDis) {
                    if (!password.contains(index)) {
                        dealCross(i, j);
                        password.add(index);
                        points[i][j].state = Point.STATE_PRESS;
                        break;
                    }
                }
                index++;
            }
        }
        invalidate();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (password.size() != 0) {

                final StringBuilder passString = new StringBuilder();
                for (Integer s : password) {
                    passString.append(s + "");
                }
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (inputCallBack != null)
                            inputCallBack.onPasswordSetted(passString.toString());
                    }
                }, 100);
                isFinish = true;

            }

        }
        return true;
    }

    /**
     * 处理间隔点，就是如果需要添加的点里面有间隔，就需要处理间隔
     * @param i  当前添加点的行
     * @param j  当前添加点的列
     */
    private void dealCross(int i, int j) {
        if (password.size() > 0) {
            int lastIndex = password.get(password.size() - 1);
            int row = lastIndex / 3;
            int col = lastIndex % 3;
            if (row == i) {
                if ((j == 2 && col == 0) || (j == 0 && col == 2)) {
                    if (!password.contains(3 * i + 1)) {
                        password.add(3 * i + 1);
                        points[i][1].state = Point.STATE_PRESS;
                    }
                }
            } else if (col == j) {
                if ((i == 2 && row == 0) || (i == 0 && row == 2)) {
                    if (!password.contains(4 + j)) {
                        password.add(3 + j);
                        points[1][j].state = Point.STATE_PRESS;
                    }
                }
            }
        }
    }


    //重置view
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                points[i][j].state = Point.STATE_NOMAL;
            }
        }
        showError = false;
        isFinish = false;
        password.clear();
        invalidate();
    }

    /**
     * 显示错误情况的路径
     */
    public void showErrorPath() {
        showError = true;
        for (int i = 0; i < password.size(); i++) {
            Point point1 = points[password.get(i) / 3][(password.get(i) % 3)];
            point1.state = Point.STATE_ERROR;
        }
        invalidate();
    }

    /**
     * 延时显示错误路径，时间过去后自然消失
     * @param mill
     */
    public void showErrorPathForSometime(long mill) {
        showErrorPath();
        if (mill <= 0) mill = 1000;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, mill);
        invalidate();
    }

    /**
     * 这个就是对外公开的请求输入的接口
     * @param inputCallBack
     */
    public void requestInput(InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
        if (inited) reset();
    }

    /**
     * 输入的回调接口
     */
    public interface InputCallBack {
        void onPasswordSetted(String password);
    }

}
