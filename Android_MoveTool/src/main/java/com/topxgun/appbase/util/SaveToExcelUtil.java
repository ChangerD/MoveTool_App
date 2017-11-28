package com.topxgun.appbase.util;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by Jwding on 2017/10/25.
 */

public class SaveToExcelUtil {
    private WritableWorkbook wwb;
    private String excelPath;
    private File excelFile;
    private Activity activity;

    public SaveToExcelUtil(Activity activity, String excelPath) {
        this.excelPath = excelPath;
        this.activity = activity;
        excelFile = new File(excelPath);
        createExcel(excelFile);

    }

    // 创建excel表.
    public void createExcel(File file) {
        WritableSheet ws = null;
        try {
            if (!file.exists()) {
                wwb = Workbook.createWorkbook(file);

                ws = wwb.createSheet("sheet1", 0);
                // 在指定单元格插入数据
                ws.addCell(new Label(0, 0, "滚转角"));
                ws.addCell(new Label(1, 0, "俯仰角"));
                ws.addCell(new Label(2, 0, "偏航角"));
                ws.addCell(new Label(3, 0, "震动指数"));
                ws.addCell(new Label(4, 0, "报警代码"));
                ws.addCell(new Label(5, 0, "电压"));
                ws.addCell(new Label(6, 0, "运行时间"));
                ws.addCell(new Label(7, 0, "转速1"));
                ws.addCell(new Label(8, 0, "转速2"));
                ws.addCell(new Label(9, 0, "转速3"));
                ws.addCell(new Label(10, 0, "转速4"));
                ws.addCell(new Label(11, 0, "转速5"));
                ws.addCell(new Label(12, 0, "转速6"));
                ws.addCell(new Label(13, 0, "转速7"));
                ws.addCell(new Label(14, 0, "转速8"));
                ws.addCell(new Label(15, 0, "油门1"));
                ws.addCell(new Label(16, 0, "油门2"));
                ws.addCell(new Label(17, 0, "油门3"));
                ws.addCell(new Label(18, 0, "油门4"));
                ws.addCell(new Label(19, 0, "油门5"));
                ws.addCell(new Label(20, 0, "油门6"));
                ws.addCell(new Label(21, 0, "油门7"));
                ws.addCell(new Label(22, 0, "油门8"));
                // 从内存中写入文件中
                wwb.write();
                wwb.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将数据存入到Excel表中
    public void writeToExcel(Object... args) {

        try {
            Workbook oldWwb = Workbook.getWorkbook(excelFile);
            wwb = Workbook.createWorkbook(excelFile, oldWwb);
            WritableSheet ws = wwb.getSheet(0);
            // 当前行数
            int row = ws.getRows();
            for(int i=0;i<args.length;i++){
                ws.addCell(new Label(i, row, args[i] + ""));
            }
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExcelDir() {
        // SD卡指定文件夹
        String sdcardPath = Environment.getExternalStorageDirectory()
                .toString();
        File dir = new File(sdcardPath + File.separator + "AgriMac/data");

        if (dir.exists()) {
            return dir.toString();

        } else {
            dir.mkdirs();
            Log.e("BAG", "保存路径不存在,");
            return dir.toString();
        }
    }
}
