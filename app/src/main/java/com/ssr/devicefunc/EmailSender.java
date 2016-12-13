package com.ssr.devicefunc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by hasana on 10/30/2016.
 */

public class EmailSender {

    public void sendEmail(String subject,String emailAddress, String emailText, final Context ctx){

        Log.v("Me22", "Send Email");
        Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
        sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]
                {emailAddress});
        sendIntent.setType("text/html");
        sendIntent.putExtra(Intent.EXTRA_TEXT,emailText);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(sendIntent);
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
////        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        emailIntent.setType("plain/text");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"gusa1991@mail.ru"});
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//        emailIntent.putExtra(Intent.EXTRA_TEXT   , "body of email");
//        Intent chooserIntent=Intent.createChooser(emailIntent, "Send mail...");
//        chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        try {
//            ctx.startActivity(chooserIntent);
//            Log.v("Me22","Finished sending email...");
//        }
//        catch (Exception e) {
//            Log.v("Me22","print exception");
//            e.printStackTrace();
//        }
    }

}
