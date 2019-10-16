package com.example.empo.DataBase;


import android.provider.BaseColumns;

public final class EmployeeInfo {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private EmployeeInfo() {}

    /* Inner class that defines the table contents */
    public static class Employee implements BaseColumns {
        public static final String TABLE_NAME = "EmployeeInfo";
        public static final String COLUMN_1 = "empName";
        public static final String COLUMN_2 = "empTelephone";
        public static final String COLUMN_3 = "empGender";
        public static final String COLUMN_4 = "empType";
    }
}
