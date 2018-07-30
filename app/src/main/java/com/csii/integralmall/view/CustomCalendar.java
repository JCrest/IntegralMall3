package com.csii.integralmall.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.csii.integralmall.R;
import com.csii.integralmall.activity.signIntegralSale.signIn.SignInActivity;
import com.csii.integralmall.utils.FontUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCalendar extends View {

    private String TAG = "CustomCalendar";

    /**
     * 各部分背景
     */
    private int mBgMonth, mBgWeek, mBgDay, mBgPre;
    /**
     * 标题的颜色、大小
     */
    private int mTextColorMonth;
    private float mTextSizeMonth;
    private int mMonthRowL, mMonthRowR;
    private float mMonthRowSpac;
    private float mMonthSpac;
    /**
     * 星期的颜色、大小
     */
    private int mTextColorWeek, mSelectWeekTextColor;
    private float mTextSizeWeek;
    /**
     * 日期文本的颜色、大小
     */
    private int mTextColorDay;
    private float mTextSizeDay;
    /**
     * 签到次数文本的颜色、大小；礼物
     */
    private int mTextColorPreFinish, mTextColorPreUnFinish, mTextColorPreNull;
    private float mTextSizePre;
    private int mGift;
    /**
     * 选中的文本的颜色
     */
    private int mSelectTextColor;
    /**
     * 选中背景
     */
    private int mSelectBg, mCurrentBg;
    private float mSelectRadius, mCurrentBgStrokeWidth;
    private float[] mCurrentBgDashPath;

    /**
     * 行间距
     */
    private float mLineSpac;
    /**
     * 字体上下间距
     */
    private float mTextSpac;

    private Paint mPaint;
    private Paint bgPaint;

    private float titleHeight, weekHeight, dayHeight, preHeight, oneHeight;
    private int columnWidth;       //每列宽度

    private Date month; //接收到的月份（XXXX年XX月：服务器传递过来）
    private Date mon_day;//接收到的日期（XXXX年XX月XX日：服务器传递过来）
    private boolean isCurrentMonth;       //展示的月份是否是当前月

    private int currentMonth;//接收到的月份
    private int currentDay, selectDay, lastSelectDay;    //当前日期 、 选中的日期 、上一次选中的日期（避免造成重复回调请求）

    private int dayOfMonth;    //月份天数
    private int firstIndex;    //当月第一天位置索引
    private int todayWeekIndex;//今天是星期几
    private int firstLineNum, lastLineNum; //第一行、最后一行能展示多少日期
    private int lineNum;      //日期行数
    private String[] WEEK_STR = new String[]{"日", "一", "二", "三", "四", "五", "六",};


    public CustomCalendar(Context context) {
        this(context, null);
    }

    public CustomCalendar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ResourceAsColor")
    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCalendar, defStyleAttr, 0);
        //顶部年月日的背景颜色
        mBgMonth = a.getColor(R.styleable.CustomCalendar_mBgMonth, Color.TRANSPARENT);

        mBgWeek = a.getColor(R.styleable.CustomCalendar_mBgWeek, Color.WHITE);
        mBgDay = a.getColor(R.styleable.CustomCalendar_mBgDay, Color.TRANSPARENT);
        mBgPre = a.getColor(R.styleable.CustomCalendar_mBgPre, Color.TRANSPARENT);

        mMonthRowL = a.getResourceId(R.styleable.CustomCalendar_mMonthRowL, R.drawable.custom_calendar_row_left);
        mMonthRowR = a.getResourceId(R.styleable.CustomCalendar_mMonthRowR, R.drawable.custom_calendar_row_right);
        mMonthRowSpac = a.getDimension(R.styleable.CustomCalendar_mMonthRowSpac, 20);
        mTextColorMonth = a.getColor(R.styleable.CustomCalendar_mTextColorMonth, Color.BLACK);
        mTextSizeMonth = a.getDimension(R.styleable.CustomCalendar_mTextSizeMonth, 100);
        mMonthSpac = a.getDimension(R.styleable.CustomCalendar_mMonthSpac, 20);
        mTextColorWeek = a.getColor(R.styleable.CustomCalendar_mTextColorWeek, Color.BLACK);
        mSelectWeekTextColor = a.getColor(R.styleable.CustomCalendar_mSelectWeekTextColor, Color.BLACK);

        mGift = a.getResourceId(R.styleable.CustomCalendar_mGift, R.drawable.bg_gift);
        mTextSizeWeek = a.getDimension(R.styleable.CustomCalendar_mTextSizeWeek, 70);
        mTextColorDay = a.getColor(R.styleable.CustomCalendar_mTextColorDay, Color.GRAY);
        mTextSizeDay = a.getDimension(R.styleable.CustomCalendar_mTextSizeDay, 70);
        mTextColorPreFinish = a.getColor(R.styleable.CustomCalendar_mTextColorPreFinish, Color.BLUE);
        mTextColorPreUnFinish = a.getColor(R.styleable.CustomCalendar_mTextColorPreUnFinish, Color.BLUE);
        mTextColorPreNull = a.getColor(R.styleable.CustomCalendar_mTextColorPreNull, Color.BLUE);
        mTextSizePre = a.getDimension(R.styleable.CustomCalendar_mTextSizePre, 40);
        mSelectTextColor = a.getColor(R.styleable.CustomCalendar_mSelectTextColor, Color.YELLOW);
        mCurrentBg = a.getColor(R.styleable.CustomCalendar_mCurrentBg, Color.GRAY);
        try {
            int dashPathId = a.getResourceId(R.styleable.CustomCalendar_mCurrentBgDashPath, R.array.customCalendar_currentDay_bg_DashPath);
            int[] array = getResources().getIntArray(dashPathId);
            mCurrentBgDashPath = new float[array.length];
            for (int i = 0; i < array.length; i++) {
                mCurrentBgDashPath[i] = array[i];
            }
        } catch (Exception e) {
            e.printStackTrace();
            mCurrentBgDashPath = new float[]{2, 3, 2, 3};
        }
        mSelectBg = a.getColor(R.styleable.CustomCalendar_mSelectBg, R.color.cal_signed_day_bg);
        mSelectRadius = a.getDimension(R.styleable.CustomCalendar_mSelectRadius, 20);
        mCurrentBgStrokeWidth = a.getDimension(R.styleable.CustomCalendar_mCurrentBgStrokeWidth, 5);
        mLineSpac = a.getDimension(R.styleable.CustomCalendar_mLineSpac, 20);
        mTextSpac = a.getDimension(R.styleable.CustomCalendar_mTextSpac, 20);
        a.recycle();  //注意回收

        /*     initCompute();*/

    }


    /**
     * 计算相关常量，构造方法中调用
     *
     * @param month
     */
    private void initCompute(String month) {
        mPaint = new Paint();
        bgPaint = new Paint();
        mPaint.setAntiAlias(true); //抗锯齿
        bgPaint.setAntiAlias(true); //抗锯齿

        map = new HashMap<>();

        //标题高度
        mPaint.setTextSize(mTextSizeMonth);
        titleHeight = FontUtil.getFontHeight(mPaint) + 2 * mMonthSpac;
        //星期高度
        mPaint.setTextSize(mTextSizeWeek);
        weekHeight = FontUtil.getFontHeight(mPaint);
        //日期高度
        mPaint.setTextSize(mTextSizeDay);
        dayHeight = FontUtil.getFontHeight(mPaint);
        //次数字体高度
        mPaint.setTextSize(mTextSizePre);
        preHeight = FontUtil.getFontHeight(mPaint);
        //每行高度 = 行间距 + 日期字体高度 + 字间距 + 次数字体高度
        oneHeight = mLineSpac + dayHeight + mTextSpac + preHeight;


        setMonth(month);
    }

    /**
     * 设置月份
     */
    private void setMonth(String Month) {
//        将服务器传递过来的字符串日期改成日期型(包含日在内的即:
//             2018年07月20日           其转化结果为:           Fri Jul 20 00:00:00 GMT+08:00 2018   )
        mon_day = strDate(Month);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mon_day);
//        获取到当前月是几月
        currentMonth = calendar.get(Calendar.MONTH) + 1;
//        获取到当前日是本月中的哪一日
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
//        获取到当前天的星期下标(星期几)
        todayWeekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//        获取当前月的总天数
        dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        isCurrentMonth = true;
        selectDay = currentDay;//当月默认选中当前日

        Log.d(TAG, "设置月份：" + mon_day + "   今天" + currentDay + "号, 是否为当前月：" + isCurrentMonth);

//        将服务器传递过来的字符串日期改成日期型(包含日在内的即:
//             2018年07月           其转化结果为:           Sun Jul 01 00:00:00 GMT+08:00 2018   )
        month = str2Date(Month);
        calendar.setTime(month);
        Log.d(TAG, "设置月份：" + month + "   今天" + currentDay + "号, 是否为当前月：" + isCurrentMonth);
        //第一行1号显示在什么位置（星期几   这里的calender 计算的是  7月1日 ）
        firstIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        lineNum = 1;
        //第一行能展示的天数
        firstLineNum = 7 - firstIndex;
        lastLineNum = 0;
        int shengyu = dayOfMonth - firstLineNum;
        while (shengyu > 7) {
            lineNum++;
            shengyu -= 7;
        }
        if (shengyu > 0) {
            lineNum++;
            lastLineNum = shengyu;
        }
        Log.i(TAG, getMonthStr(mon_day) + "一共有" + dayOfMonth + "天,第一天的索引是：" + firstIndex + "   有" + lineNum +
                "行，第一行" + firstLineNum + "个，最后一行" + lastLineNum + "个");
    }

    /**
     * 此方法将字符串形式的日期转换成日期格式
     *
     * @param month
     */
    private void setDay(String month) {
        mon_day = strDate(month);//应用到绘制月份的时候显示正常的年月日
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //宽度 = 填充父窗体
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   //获取宽的尺寸
        columnWidth = widthSize / 7;
        //高度 = 标题高度 + 星期高度 + 日期行数*每行高度
        float height = titleHeight + weekHeight + (lineNum * oneHeight) + weekHeight;
        Log.v(TAG, "标题高度：" + titleHeight + " 星期高度：" + weekHeight + " 每行高度：" + oneHeight +
                " 行数：" + lineNum + "  \n控件高度：" + height);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                (int) height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawMonth(canvas);
        drawWeek(canvas);
        drawDayAndPre(canvas);
    }


    /**
     * 绘制月份
     */
    private int rowLStart, rowRStart, rowWidth;

    private void drawMonth(Canvas canvas) {
        //背景
        bgPaint.setColor(mBgMonth);
        RectF rect = new RectF(0, 0, getWidth(), titleHeight);
        canvas.drawRect(rect, bgPaint);
        //绘制月份
        mPaint.setTextSize(mTextSizeMonth);
        mPaint.setColor(mTextColorMonth);
        float textStart = 0;
        float textLen = 0;
        if (isCurrentMonth) {
            textLen = FontUtil.getFontlength(mPaint, getMonthStr2(mon_day));
            textStart = (getWidth() - textLen) / 2;
            canvas.drawText(getMonthStr2(mon_day), textStart,
                    mMonthSpac + FontUtil.getFontLeading(mPaint), mPaint);
        } else {
            textLen = FontUtil.getFontlength(mPaint, getMonthStr2(mon_day));
            textStart = (getWidth() - textLen) / 2;
            canvas.drawText(getMonthStr2(mon_day), textStart,
                    mMonthSpac + FontUtil.getFontLeading(mPaint), mPaint);
        }

    }

    /**
     * 绘制绘制星期
     */
    private void drawWeek(Canvas canvas) {
        //背景 包含星期和下面的日期的背景
        bgPaint.setColor(Color.WHITE);
        RectF rect = new RectF(0, titleHeight, getWidth(), titleHeight + weekHeight * 1.5f + (lineNum * oneHeight));
        canvas.drawRoundRect(rect, 20, 20, bgPaint);
//        canvas.drawRect(rect, bgPaint);
/*        //背景
        bgPaint.setColor(mBgMonth);
        RectF rect = new RectF(0, titleHeight, getWidth(), titleHeight + weekHeight);
        canvas.drawRect(rect, bgPaint);*/
        //绘制星期：七天
        mPaint.setTextSize(mTextSizeWeek);

        for (int i = 0; i < WEEK_STR.length; i++) {
            if (todayWeekIndex == i) {
                mPaint.setColor(mSelectWeekTextColor);
            } else {
                mPaint.setColor(mTextColorWeek);
            }
            int len = (int) FontUtil.getFontlength(mPaint, WEEK_STR[i]);
            int x = i * columnWidth + (columnWidth - len) / 2;
            canvas.drawText(WEEK_STR[i], x, titleHeight + 0.5f * weekHeight + FontUtil.getFontLeading(mPaint), mPaint);
        }
    }

    /**
     * 绘制日期和次数
     */
    private void drawDayAndPre(Canvas canvas) {
        //某行开始绘制的Y坐标，第一行开始的坐标为标题高度+星期部分高度
        float top = titleHeight + weekHeight;
        //行
        for (int line = 0; line < lineNum; line++) {
            if (line == 0) {
                //第一行
                drawDayAndPre(canvas, top + (0.5f * weekHeight), firstLineNum, 0, firstIndex);
            } else if (line == lineNum - 1) {
                //最后一行
                top += oneHeight;
                drawDayAndPre(canvas, top, lastLineNum, firstLineNum + (line - 1) * 7, 0);
            } else {
                //满行
                top += oneHeight;
                drawDayAndPre(canvas, top, 7, firstLineNum + (line - 1) * 7, 0);
            }
        }
    }

    /**
     * 绘制某一行的日期
     *
     * @param canvas
     * @param top        顶部坐标
     * @param count      此行需要绘制的日期数量（不一定都是7天）
     * @param overDay    已经绘制过的日期，从overDay+1开始绘制
     * @param startIndex 此行第一个日期的星期索引
     */
    private void drawDayAndPre(Canvas canvas, float top,
                               int count, int overDay, int startIndex) {
//        Log.e(TAG, "总共"+dayOfMonth+"天  有"+lineNum+"行"+ "  已经画了"+overDay+"天,下面绘制："+count+"天");
        //背景
        float topPre = top + mLineSpac + dayHeight;
        bgPaint.setColor(mBgDay);
        RectF rect = new RectF(0, top, getWidth(), topPre);
//        canvas.drawRect(rect, bgPaint);


        for (int i = 0; i < count; i++) {
            int left = (startIndex + i) * columnWidth;
            int day = (overDay + i + 1);

            mPaint.setTextSize(mTextSizeDay);
            float dayTextLeading = FontUtil.getFontLeading(mPaint);
//            mPaint.setTextSize(mTextSizePre);
            float preTextLeading = FontUtil.getFontLeading(mPaint);
/***********************************  判断签到情况  ↓↓↓↓↓↓  *************************/
            SignInActivity.DayFinish finish = map.get(day);

            int len = (int) FontUtil.getFontlength(mPaint, day + "");
            int x = left + (columnWidth - len) / 2;
            mPaint.setColor(0xFF333333);
            if (isCurrentMonth) {
                if (day == currentDay) {
                    bgPaint.setColor(0xFFFE673F);
                    canvas.drawCircle(left + columnWidth / 2, top + mLineSpac + dayHeight / 2, mSelectRadius, bgPaint);
                    canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);
                } else if (finish != null) {
                    //区分完成未完成
                    if (finish.finish == 1) {
                        bgPaint.setColor(0xFFFFD9C0);
                        canvas.drawCircle(left + columnWidth / 2, top + mLineSpac + dayHeight / 2, mSelectRadius, bgPaint);
                        canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);

                    } else if (finish.finish == -1) {
                        mPaint.setTextSize(mTextSizePre);
                        bgPaint.setColor(0xFF999999);
                        canvas.drawCircle(left + columnWidth / 2, top + mLineSpac + dayHeight / 2, mSelectRadius, bgPaint);
                        mPaint.setColor(0xFFFFFFFF);
                        len = (int) FontUtil.getFontlength(mPaint, "补");
                        x = left + (columnWidth - len) / 2;
                        canvas.drawText("补", x, top + mLineSpac + dayTextLeading, mPaint);
                    } else if (finish.finish == 2) {
                        /*绘制礼物图片*/
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mGift);
                        int mGiftH = bitmap.getHeight();
                        int mGiftW = bitmap.getWidth();
                        canvas.drawBitmap(bitmap, left + (columnWidth - mGiftW) / 2, top + (oneHeight - mGiftH) / 3, new Paint());
                        mPaint.setColor(0xFFFFFFFF);
                        canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);
                    } else {
//                        mPaint.setColor(0xFF333333);
                        canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);
                    }

                } else {
                    mPaint.setColor(0xFF333333);
                    canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);

                    mPaint.setColor(mTextColorPreNull);
                }
            } else {
                if (finish != null) {
             /*       //区分完成未完成
                    if (finish.finish >= finish.all) {*/
                    mPaint.setColor(0xFF333333);
                    canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);
     /*               } else {
                        mPaint.setColor(mTextColorPreUnFinish);
                    }*/

                } else {
                    mPaint.setColor(mTextColorPreNull);
                }
            }


/***********************************  判断签到情况  ↑↑↑↑↑↑  *************************/


            //选中的日期字体白色，橙色背景
//            mPaint.setColor(mSelectTextColor);
//            bgPaint.setColor(mSelectBg);
            //绘制橙色圆背景，参数一是:中心点的x轴，参数二是:中心点的y轴，参数三是:半径，参数四是:paint对象；
//            canvas.drawCircle(left + columnWidth / 2, top + mLineSpac + dayHeight / 2, mSelectRadius, bgPaint);


            //给下面的绘制背景
//        bgPaint.setColor(Color.BLUE);
//            rect = new RectF(0, topPre, getWidth(), topPre + mTextSpac + dayHeight);
//        canvas.drawRect(rect, bgPaint);

//            mPaint.setTextSize(mTextSizeDay);
//            float dayTextLeading = FontUtil.getFontLeading(mPaint);
//            mPaint.setTextSize(mTextSizePre);
//            float preTextLeading = FontUtil.getFontLeading(mPaint);
//        Log.v(TAG, "当前日期："+currentDay+"   选择日期："+selectDay+"  是否为当前月："+isCurrentMonth);


//            mPaint.setTextSize(mTextSizeDay);

            //如果是当前月，当天日期需要做处理
            if (isCurrentMonth && currentDay == day) {
                mPaint.setColor(mTextColorDay);
                bgPaint.setColor(mCurrentBg);
                bgPaint.setStyle(Paint.Style.STROKE);  //空心
                PathEffect effect = new DashPathEffect(mCurrentBgDashPath, 1);
                bgPaint.setPathEffect(effect);   //设置画笔曲线间隔
                bgPaint.setStrokeWidth(mCurrentBgStrokeWidth);       //画笔宽度
                //绘制空心圆背景
                canvas.drawCircle(left + columnWidth / 2, top + mLineSpac + dayHeight / 2,
                        mSelectRadius - mCurrentBgStrokeWidth, bgPaint);
            }
            //绘制完后将画笔还原，避免脏笔
            bgPaint.setPathEffect(null);
            bgPaint.setStrokeWidth(0);
            bgPaint.setStyle(Paint.Style.FILL);

            //选中的日期，如果是本月，选中日期正好是当天日期，下面的背景会覆盖上面绘制的虚线背景
            if (selectDay == day) {
                //选中的日期字体白色，橙色背景
//                mPaint.setColor(mSelectTextColor);
//                bgPaint.setColor(mSelectBg);
                //绘制橙色圆背景，参数一是中心点的x轴，参数二是中心点的y轴，参数三是半径，参数四是paint对象；
//                canvas.drawCircle(left + columnWidth / 2, top + mLineSpac + dayHeight / 2, mSelectRadius, bgPaint);
            } else {
//                mPaint.setColor(mTextColorDay);
            }

/*            int len = (int) FontUtil.getFontlength(mPaint, day + "");
            int x = left + (columnWidth - len) / 2;*/
//            canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);

            //绘制次数
            mPaint.setTextSize(mTextSizePre);

            Log.e("这里是finish：", finish + "");
            String preStr = "0/0";
            if (isCurrentMonth) {
                if (finish.all == 1) {
                    /*绘制礼物图片*/
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mGift);
                    int mGiftH = bitmap.getHeight();
                    int mGiftW = bitmap.getWidth();
//                    mPaint.setColor(0xFFFFFFFF);
                    canvas.drawBitmap(bitmap, left + (columnWidth - mGiftW) / 2, topPre, new Paint());
                }
            }
//                    canvas.drawText(day + "", x, top + mLineSpac + dayTextLeading, mPaint);}








/*

                if (day > currentDay) {
                    mPaint.setColor(mTextColorPreNull);
                    preStr = finish.finish + "/" + finish.all;
                } else if (finish != null) {
                    //区分完成未完成
                    if (finish.finish >= finish.all) {
                        mPaint.setColor(mTextColorPreFinish);
                    } else {
                        mPaint.setColor(mTextColorPreUnFinish);
                    }
                    preStr = finish.finish + "/" + finish.all;

                } else {
                    mPaint.setColor(mTextColorPreNull);
                }
            } else {
                if (finish != null) {
                    //区分完成未完成
                    if (finish.finish >= finish.all) {
                        mPaint.setColor(mTextColorPreFinish);
                    } else {
                        mPaint.setColor(mTextColorPreUnFinish);
                    }
                    preStr = finish.finish + "/" + finish.all;

                } else {
                    mPaint.setColor(mTextColorPreNull);
                }
            }*/

/*            len = (int) FontUtil.getFontlength(mPaint, preStr);
            x = left + (columnWidth - len) / 2;*/
//            canvas.drawText(preStr, x, topPre + mTextSpac + preTextLeading, mPaint);
        }
    }


    /**
     * 获取年月日
     *
     * @param month
     * @return
     */
    public String getMonthStr2(Date month) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(month);
    }

    private Date strDate(String str) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
            return df.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取月份标题
     */
    private String getMonthStr(Date month) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
        return df.format(month);
    }

    private Date str2Date(String str) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月");
            return df.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /****************************事件处理↓↓↓↓↓↓↓****************************/
    //焦点坐标
    private PointF focusPoint = new PointF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                focusPoint.set(event.getX(), event.getY());
                touchFocusMove(focusPoint, false);
                break;
            case MotionEvent.ACTION_MOVE:
                focusPoint.set(event.getX(), event.getY());
                touchFocusMove(focusPoint, false);
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                focusPoint.set(event.getX(), event.getY());
                touchFocusMove(focusPoint, true);
                break;
        }
        return true;
    }

    /**
     * 焦点滑动
     */
    public void touchFocusMove(final PointF point, boolean eventEnd) {
        Log.e(TAG, "点击坐标：(" + point.x + " ，" + point.y + "),事件是否结束：" + eventEnd);
        /**标题和星期只有在事件结束后才响应*/
        if (point.y <= titleHeight) {
            //事件在标题上
            if (eventEnd && listener != null) {
                if (point.x >= rowLStart && point.x < (rowLStart + 2 * mMonthRowSpac + rowWidth)) {
                    Log.w(TAG, "点击左箭头");
                    listener.onLeftRowClick();
                } else if (point.x > rowRStart && point.x < (rowRStart + 2 * mMonthRowSpac + rowWidth)) {
                    Log.w(TAG, "点击右箭头");
                    listener.onRightRowClick();
                } else if (point.x > rowLStart && point.x < rowRStart) {
                    listener.onTitleClick(getMonthStr(month), month);
                }
            }
        } else if (point.y <= (titleHeight + weekHeight)) {
            //事件在星期部分
            if (eventEnd && listener != null) {
                //根据X坐标找到具体的焦点日期
                int xIndex = (int) point.x / columnWidth;
                Log.e(TAG, "列宽：" + columnWidth + "  x坐标余数：" + (point.x / columnWidth));
                if ((point.x / columnWidth - xIndex) > 0) {
                    xIndex += 1;
                }
                if (listener != null) {
                    listener.onWeekClick(xIndex - 1, WEEK_STR[xIndex - 1]);
                }
            }
        } else {
            /**日期部分按下和滑动时重绘，只有在事件结束后才响应*/
            touchDay(point, eventEnd);
        }
    }

    //控制事件是否响应
    private boolean responseWhenEnd = false;

    /**
     * 事件点在 日期区域 范围内
     */
    private void touchDay(final PointF point, boolean eventEnd) {
        //根据Y坐标找到焦点行
        boolean availability = false;  //事件是否有效
        //日期部分
        float top = titleHeight + weekHeight + oneHeight;
        int foucsLine = 1;
        while (foucsLine <= lineNum) {
            if (top >= point.y) {
                availability = true;
                break;
            }
            top += oneHeight;
            foucsLine++;
        }
        if (availability) {
            //根据X坐标找到具体的焦点日期
            int xIndex = (int) point.x / columnWidth;
            if ((point.x / columnWidth - xIndex) > 0) {
                xIndex += 1;
            }
//            Log.e(TAG, "列宽："+columnWidth+"  x坐标余数："+(point.x / columnWidth));
            if (xIndex <= 0)
                xIndex = 1;   //避免调到上一行最后一个日期
            if (xIndex > 7)
                xIndex = 7;   //避免调到下一行第一个日期
//            Log.e(TAG, "事件在日期部分，第"+foucsLine+"/"+lineNum+"行, "+xIndex+"列");
            if (foucsLine == 1) {
                //第一行
                if (xIndex <= firstIndex) {
                    Log.e(TAG, "点到开始空位了");
                    setSelectedDay(selectDay, true);
                } else {
                    setSelectedDay(xIndex - firstIndex, eventEnd);
                }
            } else if (foucsLine == lineNum) {
                //最后一行
                if (xIndex > lastLineNum) {
                    Log.e(TAG, "点到结尾空位了");
                    setSelectedDay(selectDay, true);
                } else {
                    setSelectedDay(firstLineNum + (foucsLine - 2) * 7 + xIndex, eventEnd);
                }
            } else {
                setSelectedDay(firstLineNum + (foucsLine - 2) * 7 + xIndex, eventEnd);
            }
        } else {
            //超出日期区域后，视为事件结束，响应最后一个选择日期的回调
            setSelectedDay(selectDay, true);
        }
    }

    /**
     * 设置选中的日期
     */
    private void setSelectedDay(int day, boolean eventEnd) {
        Log.w(TAG, "选中：" + day + "  事件是否结束" + eventEnd);
        selectDay = day;
        invalidate();
        if (listener != null && eventEnd && responseWhenEnd && lastSelectDay != selectDay) {
            lastSelectDay = selectDay;
            listener.onDayClick(selectDay, getMonthStr(month) + selectDay + "日", map.get(selectDay));
        }
        responseWhenEnd = !eventEnd;
    }

    /****************************事件处理↑↑↑↑↑↑↑****************************/


    @Override
    public void invalidate() {
        requestLayout();
        super.invalidate();
    }

    /***********************接口API↓↓↓↓↓↓↓**************************/
    private Map<Integer, SignInActivity.DayFinish> map;

    public void setRenwu(String month, List<SignInActivity.DayFinish> list) {
//        setDay(month);
        initCompute(month);
        /* setMonth(month);*/
        Log.e(TAG, "setRenwu: " + "调用setMonth()");

        if (list != null && list.size() > 0) {
            map.clear();
            for (SignInActivity.DayFinish finish : list) {
                map.put(finish.day, finish);
            }
        }
        invalidate();
    }


    public void setRenwu(List<SignInActivity.DayFinish> list) {
        if (list != null && list.size() > 0) {
            map.clear();
            for (SignInActivity.DayFinish finish : list) {
                map.put(finish.day, finish);
            }
        }
        invalidate();
    }

    /**
     * 月份增减
     */
    public void monthChange(int change) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        calendar.add(Calendar.MONTH, change);
        setMonth(getMonthStr(calendar.getTime()));
        map.clear();
        invalidate();
    }

    private onClickListener listener;

    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public interface onClickListener {

        public abstract void onLeftRowClick();

        public abstract void onRightRowClick();

        public abstract void onTitleClick(String monthStr, Date month);

        public abstract void onWeekClick(int weekIndex, String weekStr);

        public abstract void onDayClick(int day, String dayStr, SignInActivity.DayFinish finish);
    }


    /***********************接口API↑↑↑↑↑↑↑**************************/

}
