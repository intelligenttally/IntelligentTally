package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2016/11/24.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库工具
 *
 * @author sh
 * @version 1.0 2016/11/24
 * @since 1.0
 */
public class ShipImageSQLiteHelper extends SQLiteOpenHelper {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "ShipImageSQLiteHelper.";

    /**
     * 引用计数器
     */
    private AtomicInteger openCounter = new AtomicInteger();

    /**
     * 单例连接，用于写访问
     */
    private static ShipImageSQLiteHelper sqLiteOpenHelper = null;

    /**
     * 获取数据库连接实例
     *
     * @param context 上下文
     *
     * @return 连接对象
     */
    public synchronized static ShipImageSQLiteHelper getSqLiteOpenHelper(Context context) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new ShipImageSQLiteHelper(context);
        }
        return sqLiteOpenHelper;
    }

    public ShipImageSQLiteHelper(Context context) {
        super(context, CommonConst.DB_NAME, null, CommonConst.DB_VERSION);
        Log.i(LOG_TAG + "TallySQLiteHelper", "TallySQLiteHelper is invoked");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            db.enableWriteAheadLogging();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // 计数加一
        openCounter.incrementAndGet();

        Log.i(LOG_TAG + "getWritableDatabase", "now open database count is " + openCounter.get());

        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // 计数加一
        openCounter.incrementAndGet();

        Log.i(LOG_TAG + "getReadableDatabase", "now open database count is " + openCounter.get());

        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        // 计数减一
        if (openCounter.decrementAndGet() <= 0) {
            super.close();
        }

        Log.i(LOG_TAG + "close", "now open database count is " + openCounter.get());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
