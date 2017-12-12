package com.example.dell.test.Staff;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddPhotoActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView photo;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        Button camera = (Button) findViewById(R.id.button_camera);
        Button album = (Button) findViewById(R.id.button_album);
        Button sure = (Button) findViewById(R.id.add_photo_sure);
        photo = (ImageView) findViewById(R.id.image_add_photo);

        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File outputImage = new File(getExternalCacheDir(),
                        "output_image.jpg");
                try

                {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (
                        IOException e)

                {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24)

                {
                    imageUri = FileProvider.getUriForFile(AddPhotoActivity.this,
                            ".Staff.AddPhotoActivity.fileprovider", outputImage);
                } else

                {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }

        });

        album.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toAlbum = new Intent();
                toAlbum.setType("image/*");
                toAlbum.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(toAlbum, "Select Picture"), CHOOSE_PHOTO);
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddPhotoActivity.this);
                dialog.setTitle("确认信息");
                dialog.setMessage("确定上传该照片吗?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.
                        OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)  {
                        finish();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.
                        OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)  {
                    }
                });
                dialog.show();
            }
        });
    }




//        album.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(AddPhotoActivity.this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
//                        PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(AddPhotoActivity.this, new
//                    String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                    Toast.makeText(AddPhotoActivity.this, "打bu开", Toast.LENGTH_SHORT).show();
//                } else{
//                    Toast.makeText(AddPhotoActivity.this, "open", Toast.LENGTH_SHORT).show();
//                    openAlbum();
//                }
//            }
//        });
//    }
//
//    private void openAlbum() {
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        startActivityForResult(intent, CHOOSE_PHOTO);
//    }
//
//    public void onRequestPermissionResult(int requestCode, String[] permissions,
//                                          int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.
//                        PERMISSION_GRANTED) {
//                    openAlbum();
//                } else{
//                    Toast.makeText(this, "请求超时",
//                            Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//        }
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK)
                {
                    try {
                        // 尝试显示照片
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().
                                openInputStream(imageUri));
                        photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    displayImage(data.getData());
                }
                break;
            default:
                break;
        }
    }

//    private void handleImageOnKitKat(Intent data) {
//        String imagePath = null;
//        Uri uri = data.getData();
//        if (DocumentsContract.isDocumentUri(this, uri)) {
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1];
//                String selection = MediaStore.Images.Media._ID + "=" +id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        selection);
//            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//                imagePath = getImagePath(uri,null);
//            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//                imagePath = uri.getPath();
//            }
//            displayImage(imagePath);
//        }
//    }

//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//        displayImage(imagePath);
//    }

//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }

    private void displayImage(Uri imagePath) {
        if (imagePath != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(imagePath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                photo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "打开图片失败", Toast.LENGTH_SHORT).show();
        }
    }



}
