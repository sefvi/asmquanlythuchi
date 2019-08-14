package vn.sefviapp.asm.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import vn.sefviapp.asm.Modle.Chi;
import vn.sefviapp.asm.Modle.LoaiChi;
import vn.sefviapp.asm.Modle.LoaiThu;
import vn.sefviapp.asm.Modle.Thu;
import vn.sefviapp.asm.Modle.User;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_TABLE_NAME = "ps09105sql.sqlite";
    private static final int DATABASE_VERSION = 1;

    // ---Table User---
    private static final String KEY_NAME_TABLE_USER = "User";

    private static final String KEY_TABLE_ID_USER = "id";
    private static final String KEY_TABLE_PASSWORD_USER = "password";
    private static final String KEY_TABLE_TEN_USER = "name";
    private static final String KEY_TABLE_EMAIL_USER = "email";

    // ---Table Khoan Thu---
    private static final String KEY_NAME_TABLE_LOAITHU = "loaithu";

    private static final String KEY_TABLE_ID_LOAITHU = "id";
    private static final String KEY_TABLE_NAME_LOAITHU = "tenLoaiThu";
    private static final String KEY_TABLE_DELETEFLAG_LOAITHU = "deleteGlag";

    // ---Table Thu---

    private static final String KEY_NAME_TABLE_THU = "thu";

    private static final String KEY_TABLE_ID_THU = "id";
    private static final String KEY_TABLE_TENMUCTHU_THU = "tenMucThu";
    private static final String KEY_TABLE_DINHMUCTHU_THU = "dinhMucThu";
    private static final String KEY_TABLE_DONVITHU_THU = "donViThu";
    private static final String KEY_TABLE_THOIDIEMAPDUNGTHU_THU = "thoiDiemApDungThu";
    private static final String KEY_TABLE_DANHGIA_THU = "danhGia";
    private static final String KEY_TABLE_DELETEFLAG_THU = "deleteFlag";
    private static final String KEY_TABLE_IDLOAITHU_THU = "idLoaiThu";

    // ---Table Loai Chi ---

    private static final String KEY_NAME_TABLE_LOAICHI = "loaiChi";

    private static final String KEY_TABLE_ID_LOAICHI = "id";
    private static final String KEY_TABLE_NAME_LOAICHI = "tenLoaiChi";
    private static final String KEY_TABLE_DELETEFLAG_LOAICHI = "deleteFlag";

    // ---Table Chi---

    private static final String KEY_NAME_TABLE_CHI = "chi";

    private static final String KEY_TABLE_ID_CHI = "id";
    private static final String KEY_TABLE_TENMUCCHI_CHI = "tenMucChi";
    private static final String KEY_TABLE_DINHMUCCHI_CHI = "dinhMucChi";
    private static final String KEY_TABLE_DONVICHI_CHI = "donViCHI";
    private static final String KEY_TABLE_THOIDIEMAPDUNGCHI_CHI = "thoiDiemApDungChi";
    private static final String KEY_TABLE_DANHGIA_CHI = "danhGia";
    private static final String KEY_TABLE_DELETEFLAG_CHI = "deleteFlag";
    private static final String KEY_TABLE_IDLOAICHI_CHI = "idLoaiChi";



    private static final  String CREATE_CLASS_TABLE_USER = "CREATE TABLE " + KEY_NAME_TABLE_USER + "(" + KEY_TABLE_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_TEN_USER + " TEXT," + KEY_TABLE_PASSWORD_USER + " TEXT,"  + KEY_TABLE_EMAIL_USER + " TEXT" + ")";

    private static final String CREATE_CLASS_TABLE_LOAITHU = "CREATE TABLE " + KEY_NAME_TABLE_LOAITHU + "(" + KEY_TABLE_ID_LOAITHU + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_NAME_LOAITHU + " TEXT," + KEY_TABLE_DELETEFLAG_LOAITHU + " INTEGER" + ")";

    private static final String CREATE_CLASS_TABLE_THU = "CREATE TABLE " + KEY_NAME_TABLE_THU + "(" + KEY_TABLE_ID_THU + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_TENMUCTHU_THU + " TEXT," + KEY_TABLE_DINHMUCTHU_THU + " DECIMAL," + KEY_TABLE_DONVITHU_THU + " TEXT," + KEY_TABLE_THOIDIEMAPDUNGTHU_THU + " DATETIME," + KEY_TABLE_DANHGIA_THU + " INTEGER," + KEY_TABLE_DELETEFLAG_THU + " INTEGER," + KEY_TABLE_IDLOAITHU_THU + " INTEGER" + ")";

    private static final String CREATE_CLASS_TABLE_LOAICHI = "CREATE TABLE " + KEY_NAME_TABLE_LOAICHI + "(" + KEY_TABLE_ID_LOAICHI + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_NAME_LOAICHI + " TEXT," + KEY_TABLE_DELETEFLAG_LOAICHI + " INTEGER" + ")";

    private static final String CREATE_CLASS_TABLE_CHI = "CREATE TABLE " + KEY_NAME_TABLE_CHI + "(" + KEY_TABLE_ID_CHI + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TABLE_TENMUCCHI_CHI + " TEXT," + KEY_TABLE_DINHMUCCHI_CHI + " DECIMAL," + KEY_TABLE_DONVICHI_CHI + " TEXT," + KEY_TABLE_THOIDIEMAPDUNGCHI_CHI + " DATETIME," + KEY_TABLE_DANHGIA_CHI + " INTEGER," + KEY_TABLE_DELETEFLAG_CHI + " INTEGER," + KEY_TABLE_IDLOAICHI_CHI + " INTEGER" + ")";

    public Database(Context context){
        super(context, DATABASE_TABLE_NAME, null, DATABASE_VERSION);
    }

    public  void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetDate(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLASS_TABLE_USER);
        db.execSQL(CREATE_CLASS_TABLE_THU);
        db.execSQL(CREATE_CLASS_TABLE_LOAITHU);
        db.execSQL(CREATE_CLASS_TABLE_LOAICHI);
        db.execSQL(CREATE_CLASS_TABLE_CHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_USER );
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_THU);
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_LOAITHU);
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_CHI);
        db.execSQL("DROP TABLE IF EXISTS " + KEY_NAME_TABLE_LOAICHI);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TABLE_TEN_USER, user.getName());
        values.put(KEY_TABLE_EMAIL_USER, user.getEmail());
        values.put(KEY_TABLE_PASSWORD_USER, user.getPassword());

        db.insert(KEY_NAME_TABLE_USER, null, values);
        db.close();
    }
    public void addLoaiThu(LoaiThu loaiThu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_NAME_LOAITHU, loaiThu.getTenLoaiThu());
        values.put(KEY_TABLE_DELETEFLAG_LOAITHU, loaiThu.getDeleteFlag());

        db.insert(KEY_NAME_TABLE_LOAITHU, null, values);
        db.close();
    }

    public void addThu(Thu thu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_TENMUCTHU_THU, thu.getTenMucThu());
        values.put(KEY_TABLE_DINHMUCTHU_THU, thu.getDinhMucThu());
        values.put(KEY_TABLE_DONVITHU_THU, thu.getDonViThu());
        values.put(KEY_TABLE_THOIDIEMAPDUNGTHU_THU, thu.getThoiDiemApDungThu());
        values.put(KEY_TABLE_DANHGIA_THU, thu.getDanhGia());
        values.put(KEY_TABLE_DELETEFLAG_THU, thu.getDeleteFlag());
        values.put(KEY_TABLE_IDLOAITHU_THU, thu.getIdLoaiThu());

        db.insert(KEY_NAME_TABLE_THU, null, values);
        db.close();
    }

    public void addLoaiChi(LoaiChi loaiChi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_NAME_LOAICHI, loaiChi.getTenLoaiChi());
        values.put(KEY_TABLE_DELETEFLAG_LOAICHI, loaiChi.getDeleteFlag());

        db.insert(KEY_NAME_TABLE_LOAICHI, null, values);
        db.close();
    }

    public void addChi(Chi chi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TABLE_TENMUCCHI_CHI, chi.getTenMucChi());
        values.put(KEY_TABLE_DINHMUCCHI_CHI, chi.getDinhMucChi());
        values.put(KEY_TABLE_DONVICHI_CHI, chi.getDonViChi());
        values.put(KEY_TABLE_THOIDIEMAPDUNGCHI_CHI, chi.getThoiDiemApDungChi());
        values.put(KEY_TABLE_DANHGIA_CHI, chi.getDanhGia());
        values.put(KEY_TABLE_DELETEFLAG_CHI, chi.getDeleteFlag());
        values.put(KEY_TABLE_IDLOAICHI_CHI, chi.getIdLoaiChi());
        db.insert(KEY_NAME_TABLE_CHI, null, values);
        db.close();
    }
}
