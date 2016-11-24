package com.port.shenh.intelligenttally.database;
/**
 * Created by sh on 2015/11/24.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.mobile.library.model.database.BaseOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * 船图数据库操作
 *
 * @author sh
 * @version 1.0 2016/11/24
 * @since 1.0
 */
public class ShipChartOperator extends BaseOperator<String> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "CargoTypeOperator.";

    public ShipChartOperator(Context context) {
        super(context);
    }

    @Override
    protected SQLiteOpenHelper onCreateDatabaseHelper(Context context) {
        return new DBSQLiteHelper(context);
    }

    @Override
    protected SQLiteOpenHelper onCreateWriteDatabaseHelper(Context context) {
        return DBSQLiteHelper.getSqLiteOpenHelper(context);
    }

    @Override
    protected String onCreateTableName() {
        return TableConst.CargoType.TABLE_NAME;
    }

    @Override
    protected ContentValues onFillData(String data) {
        // 数据库值对象
        ContentValues cv = new ContentValues();
        cv.put(CommonConst.NAME, data);

        return cv;
    }

    @Override
    protected List<String> query(String sql) {
        Log.i(LOG_TAG + "query", "sql is " + sql);
        // 查询数据
        Cursor cursor = sqLiteHelper.getReadableDatabase().rawQuery(sql, null);

        // 列索引
        int nameIndex = cursor.getColumnIndex(CommonConst.NAME);

        // 数据填充
        List<String> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            // 一条记录

            list.add(cursor.getString(nameIndex));
        }

        Log.i(LOG_TAG + "query", "row count is " + list.size());

        // 关闭数据库
        cursor.close();
        close(sqLiteHelper);

        return list;
    }

    @Override
    protected String onWhereSql(String data) {
        return String.format("%s='%s'", CommonConst.NAME, data);
    }
}
