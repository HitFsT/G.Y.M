package com.example.dell.test;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by 龙 on 2017/11/12.
 *
 */

public class DialogUtil {
    public static void showDialog(final Context ctx, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

}
