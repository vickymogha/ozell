package com.ozellcooner;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainOptionActivity extends AppCompatActivity {

    View forYourWallButton;
    int WHATSAPP_NUMMBER=1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainoption);
        forYourWallButton =  findViewById(R.id.for_your_walls_btn);
        forYourWallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  = new Intent(MainOptionActivity.this,MainActivity.class);
                startActivity(in);
                //openWhatsappContact("9643436828");
                //openWhatsApp("919643436827");

//                String number = "919643436827";
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_VIEW);
//                String url = "whatsapp://send?phone=" + number + "&text=" + "Hello";
//                sendIntent.setData(Uri.parse(url));
//                startActivity(sendIntent);

               // String mimeString = "vnd.android.cursor.item/vnd.com.whatsapp.voip.call";


//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//
//                //here you have to pass whatsApp contact  number  as  contact_number ..
//
//                String name= getContactName("9", MainOptionActivity.this);
//                int whatsappcall=getContactIdForWhatsAppVideoCall(name,MainOptionActivity.this);
//                if (whatsappcall!=0) {
//                    intent.setDataAndType(Uri.parse("content://com.android.contacts/data/" +whatsappcall),
//                            "vnd.android.cursor.item/vnd.com.whatsapp.voip.call");
//                    intent.setPackage("com.whatsapp");
//
//                    startActivityForResult(intent, WHATSAPP_NUMMBER);
//                }


            }
        });
    }

    public void onClickWhatsApp(View view) {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SENDTO);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }


    public String getContactName(final String phoneNumber, Context context)
    {
        Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName="";
        Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName=cursor.getString(0);
            }
            cursor.close();
        }

        return contactName;
    }


    Cursor cursor;
    public  int getContactIdForWhatsAppCall(String name,Context context)
    {

        cursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Data._ID},
                ContactsContract.Data.DISPLAY_NAME + "=? and "+ContactsContract.Data.MIMETYPE+ "=?",
                new String[] {name,"vnd.android.cursor.item/vnd.com.whatsapp.voip.call"},
                ContactsContract.Contacts.DISPLAY_NAME);

        if (cursor.getCount()>0)
        {
            cursor.moveToNext();
            int phoneContactID=  cursor.getInt(cursor.getColumnIndex(ContactsContract.Data._ID));
            System.out.println("9999999999999999          name  "+name+"      id    "+phoneContactID);
            return phoneContactID;
        }
        else
        {
            System.out.println("8888888888888888888          ");
            return 0;
        }
    }

    public  int getContactIdForWhatsAppVideoCall(String name,Context context)
    {
        Cursor  cursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{ContactsContract.Data._ID},
                ContactsContract.Data.DISPLAY_NAME + "=? and "+ContactsContract.Data.MIMETYPE+ "=?",
                new String[] {name,"vnd.android.cursor.item/vnd.com.whatsapp.video.call"},
                ContactsContract.Contacts.DISPLAY_NAME);

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            int phoneContactID=  cursor.getInt(cursor.getColumnIndex(ContactsContract.Data._ID));
            return phoneContactID;
        }
        else
        {
            System.out.println("8888888888888888888          ");
            return 0;
        }
    }

    void openWhatsappContact(String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");           // so that only Whatsapp reacts and not the chooser
        i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        i.putExtra(Intent.EXTRA_TEXT, "I'm the body.");
        startActivity(Intent.createChooser(i, ""));
    }
    private void openWhatsApp(String number) {
        String smsNumber = number; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n" , Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);
    }

    public static int getContactIDFromNumber(String contactNumber,Context context)
    {
        contactNumber = Uri.encode(contactNumber);
        int phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,contactNumber),new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while(contactLookupCursor.moveToNext())
        {
            phoneContactID = contactLookupCursor.getInt(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }

    public String hasWhatsapp(String contactID)
    {
        String rowContactId = null;
        boolean hasWhatsApp;

        String[] projection = new String[]{ContactsContract.RawContacts._ID};
        String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
        String[] selectionArgs = new String[]{contactID, "com.whatsapp"};
        Cursor cursor = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
        if (cursor != null) {
            hasWhatsApp = cursor.moveToNext();
            if (hasWhatsApp) {
                rowContactId = cursor.getString(0);
            }
            cursor.close();
        }
        return rowContactId;
    }
}
