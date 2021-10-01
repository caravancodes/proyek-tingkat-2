package org.d3ifcool.counterpulsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by AITEK230TELU on 2/19/2018.
 */

public class Database extends SQLiteOpenHelper{

    // DECLARATION VARIABLE
    public static final String DATABASE_NAME = "CounterPulsa.db";
    public ArrayList<Table_list> table_lists;
    public SQLiteDatabase sqLiteDatabase;

    // CREATE CLASS TABLE
    class Table_list{

        // DECLARATION VARIABLE
        private String table_name;
        private ArrayList<String> column_name;

        // CONSTRUCTOR
        public Table_list(String table_name, ArrayList column_name) {
            this.table_name = table_name;
            this.column_name = column_name;
        }

        // GETTER AND SETTER METHOD
        public String getTable_name() {
            return table_name;
        }

        public void setTable_name(String table_name) {
            this.table_name = table_name;
        }

        public ArrayList<String> getColumn_name() {
            return column_name;
        }

        public void setColumn_name(ArrayList<String> column_name) {
            this.column_name = column_name;
        }
    }

    // CONSTRUCTOR
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.table_lists = new ArrayList<>();
        this.sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TABLE USER
        String table_user = "user_table";
        ArrayList<String> table_user_column = new ArrayList<>();
        table_user_column.add("ID_USER INTEGER PRIMARY KEY AUTOINCREMENT");
        table_user_column.add("NO_KTP TEXT");
        table_user_column.add("NAMA_LENGKAP TEXT");
        table_user_column.add("NO_HP TEXT");
        table_user_column.add("EMAIL TEXT");
        table_user_column.add("USERNAME TEXT");
        table_user_column.add("PASSWORD TEXT");
        // TABLE SALDO
        String table_saldo = "saldo_table";
        ArrayList<String> table_saldo_column = new ArrayList<>();
        table_saldo_column.add("ID_SALDO INTEGER PRIMARY KEY AUTOINCREMENT");
        table_saldo_column.add("ID_USER INTEGER");
        table_saldo_column.add("NOMINAL_SALDO INTEGER");
        table_saldo_column.add("NOMINAL_SALDO_KELUAR INTEGER");
        // TABLE SALDO LIST
        String table_saldo_list = "saldo_list_table";
        ArrayList<String> table_saldo_list_column = new ArrayList<>();
        table_saldo_list_column.add("ID_LIST_SALDO INTEGER PRIMARY KEY AUTOINCREMENT");
        table_saldo_list_column.add("NAMA_PAKET TEXT");
        table_saldo_list_column.add("HARGA_PAKET INTEGER");
        table_saldo_list_column.add("STATUS_PAKET INTEGER");
        // TABLE PEMBELIAN SALDO
        String table_pembelian_saldo = "pembelian_saldo_table";
        ArrayList<String> table_pembelian_saldo_column = new ArrayList<>();
        table_pembelian_saldo_column.add("ID_PEMBELIAN_SALDO INTEGER PRIMARY KEY AUTOINCREMENT");
        table_pembelian_saldo_column.add("ID_LIST_SALDO INTEGER");
        table_pembelian_saldo_column.add("ID_USER INTEGER");
        table_pembelian_saldo_column.add("TANGGAL TEXT");
        table_pembelian_saldo_column.add("JAM TEXT");
        // TABLE SERVICE
        String table_service = "service_table";
        ArrayList<String> table_service_column = new ArrayList<>();
        table_service_column.add("ID_SERVICE INTEGER PRIMARY KEY AUTOINCREMENT");
        table_service_column.add("NAMA_SERVICE TEXT");
        table_service_column.add("HARGA_SERVICE INTEGER");
        table_service_column.add("STATUS_SERVICE INTEGER");
        // TABLE TRANSAKSI
        String table_transaksi = "transaksi_table";
        ArrayList<String> table_transaksi_column = new ArrayList<>();
        table_transaksi_column.add("ID_TRANSAKSI INTEGER PRIMARY KEY AUTOINCREMENT");
        table_transaksi_column.add("ID_SERVICE INTEGER");
        table_transaksi_column.add("ID_USER INTEGER");
        table_transaksi_column.add("NO_HP TEXT");
        table_transaksi_column.add("STATUS_TRANSAKSI INTEGER");
        // TABLE BERANDA
        String table_beranda = "beranda_table";
        ArrayList<String> table_beranda_column = new ArrayList<>();
        table_beranda_column.add("ID_BERANDA INTEGER PRIMARY KEY AUTOINCREMENT");
        table_beranda_column.add("TITLE TEXT");
        table_beranda_column.add("DESCRIPTION TEXT");
        table_beranda_column.add("IMAGE TEXT");
        table_beranda_column.add("TANGGAL TEXT");
        table_beranda_column.add("WAKTU TEXT");
        table_beranda_column.add("STATUS INTEGER");

        // CREATE TO TABLE LIST
        this.table_lists.add(new Table_list(table_user, table_user_column));
        this.table_lists.add(new Table_list(table_saldo, table_saldo_column));
        this.table_lists.add(new Table_list(table_saldo_list, table_saldo_list_column));
        this.table_lists.add(new Table_list(table_pembelian_saldo, table_pembelian_saldo_column));
        this.table_lists.add(new Table_list(table_service, table_service_column));
        this.table_lists.add(new Table_list(table_transaksi, table_transaksi_column));
        this.table_lists.add(new Table_list(table_beranda, table_beranda_column));

        // CREATE TABLE
        for (Table_list x: this.table_lists) {
            String column_list = "";
            int i = 0;
            for(i = 0; i < x.getColumn_name().size() - 1; i++){
                column_list += x.getColumn_name().get(i) + ",";
            }
            column_list += x.getColumn_name().get(i);
            System.out.println(column_list);
            sqLiteDatabase.execSQL("CREATE TABLE " + x.getTable_name() + "(" + column_list + ")");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for (Table_list x: this.table_lists) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + x.getTable_name());
        }
        onCreate(sqLiteDatabase);
    }

    // INSERT DATA TO TABLE
    public boolean insertData(String tableName, ArrayList<String> columnTable, ArrayList<String> dataTable){
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < columnTable.size(); i++) {
            contentValues.put(columnTable.get(i) , dataTable.get(i));
        }
        if(this.sqLiteDatabase.insert(tableName, null, contentValues) != -1){
            return true;
        }
        return false;
    }

    // GET DATA FROM TABLE
    public Cursor getAllData(String sql){
        Cursor response = this.sqLiteDatabase.rawQuery(sql, null);
        return response;
    }

    // UPDATE DATA TO TABLE
    public boolean updateData(String tableName, ArrayList<String> columnTable, ArrayList<String> dataTable, String sql, String[] sqlValue){
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < columnTable.size(); i++) {
            contentValues.put(columnTable.get(i) , dataTable.get(i));
        }
        if(this.sqLiteDatabase.update(tableName, contentValues, sql, sqlValue) != -1){
            return true;
        }
        return false;
    }

    // REMOVE DATA TO TABLE
    public boolean deleteData(String tableName, String sql, String[] sqlValue){
        if(this.sqLiteDatabase.delete(tableName, sql, sqlValue) != 0){
            return true;
        }
        return false;
    }
}
