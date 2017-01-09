package com.compassecg.test720.compassecg.Home.AcitvityW.my;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.compassecg.test720.compassecg.unitl.LocalImageHelper;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class AuthenticationActivityW extends BarBaseActivity {
    ImageView img1, img2;
    TextView textbtn;
    private final int RESULT_CAMERA_IMAGE = 1; //相机
    private final int RESULT_PHOTO_IMAGE = 2; //相册
    private final int PHOTO_REQUEST_CUT = 3;// 结果
    private static Uri imageUri;
    private static Bitmap bitmap;
    private static Uri uriTempFile;
    private String imageName;
    private File imageFile;
    private Intent intent;
    private final int START = 101;
    private int indexpager = 0;
    private ByteArrayInputStream is, is2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_w);
        setTitleString("医生认证");
        img1 = getView(R.id.img1);
        img2 = getView(R.id.img2);
        textbtn = getView(R.id.textbtn);
        textbtn.setOnClickListener(this);
        img2.setOnClickListener(this);
        img1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img1:
                indexpager = 1;
                changeHeader();
                break;
            case R.id.img2:
                indexpager = 2;
                changeHeader();
                break;
            case R.id.textbtn:

                if ("".equals(is) && "".equals(is2)) {
                    ShowToast("请完善从业资格证和工作证");
                } else {
                    showConflictDialog();
                }

                break;
        }
    }

    private void showConflictDialog() {
        final android.app.AlertDialog dlgShowBack = new android.app.AlertDialog.Builder(this).create();
        dlgShowBack.setTitle("提示！");
        dlgShowBack.setMessage("是否确认保存？");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestParams params = new RequestParams();
                params.put("uid", APP.uuid);
                params.put("file", is, "tem.png", "image/png");
                params.put("file1", is2, "tem.png", "image/png");
                Post(Connector.attestation, params, START);

                dialog.dismiss();
            }
        });
        dlgShowBack.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgShowBack.setCancelable(false);
        dlgShowBack.show();
        Button btnNegative = dlgShowBack.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(getResources().getColor(R.color.lv));
    }

    private void changeHeader() {
        final AlertDialog d = new AlertDialog.Builder(this, R.style.MenuDialog).create();
        d.show();
        Window w = d.getWindow();
        w.setContentView(R.layout.activity_header_change);
        w.setGravity(Gravity.BOTTOM);

//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        d.setCanceledOnTouchOutside(true);
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = w.getAttributes();
        params.width = (int) (display.getWidth());
        w.setAttributes(params);
        d.setCanceledOnTouchOutside(true);

        w.findViewById(R.id.take).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePics();
                d.dismiss();
            }
        });
        w.findViewById(R.id.pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg();
                d.dismiss();
            }
        });
        w.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
    }

    //相机拍照
    private void takePics() {
        File path = Environment.getExternalStorageDirectory();
        //可能拍照多张
        imageName = "info" + System.currentTimeMillis() + ".png";
        String cameraPath = LocalImageHelper.getInstance().setCameraImgPath();
        imageFile = new File(cameraPath);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        startActivityForResult(intent, RESULT_CAMERA_IMAGE);
    }

    public void pickImg() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_PHOTO_IMAGE);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1.5);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("noFaceDetection", true);

        /**
         19.     * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         20.     * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         21.     */

        //uritempFile为Uri类变量，实例化uritempFile
        uriTempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriTempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
        System.out.println("系统照片编辑器");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws NullPointerException {
        switch (resultCode) {
//            case data_name:
//                tvName.setText(data.getStringExtra("name"));
//                break;
//            case data_saying:
//                sayingTv.setText(data.getStringExtra("saying"));
//                break;
//            case data_skill:
//                skillTv.setText(data.getStringExtra("skill"));
//                break;
//            case data_intro:
//                introTv.setText(data.getStringExtra("intro"));
//                break;
        }
        callbackImg(this, requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void callbackImg(Activity context, int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CAMERA_IMAGE:
//                if (resultCode == Activity.RESULT_OK) {
////                    if (isSd(context)) {
//                    if (imageUri != null) {
//                        System.out.println("相机");
//                        startPhotoZoom(imageUri);
////                        }
//                    }
//                }

                if (resultCode == Activity.RESULT_OK) {
                    String cameraPath = LocalImageHelper.getInstance().getCameraImgPath();
                    if (cameraPath == null | cameraPath.equals("")) {
                        Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    File file = new File(cameraPath);
                    Uri uri = Uri.fromFile(file);
                    startPhotoZoom(uri);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "取消拍照", Toast.LENGTH_SHORT).show();
                } else
                    break;
            case RESULT_PHOTO_IMAGE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    String uri = data.getData().toString();
                    System.out.println("相册" + uri);
                    startPhotoZoom(Uri.parse(uri));
                }
                break;
            case PHOTO_REQUEST_CUT:
                System.out.println("剪切后的" + data);
                if (resultCode == Activity.RESULT_OK && data != null) {
                    try {
                        bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uriTempFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    switch (indexpager) {
                        case 1:
                            Glide.with(getApplicationContext())
                                    .load(uriTempFile)
                                    .placeholder(R.drawable.ic_placeholder)
                                    .centerCrop()
                                    .into(img1);
                            is = compressImage(bitmap);
                            break;

                        case 2:
                            Glide.with(getApplicationContext())
                                    .load(uriTempFile)
                                    .placeholder(R.drawable.ic_placeholder)
                                    .centerCrop()
                                    .into(img2);
                            is2 = compressImage(bitmap);
                            break;

                    }

                }
                break;
        }
    }

    //
    private ByteArrayInputStream compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 60, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 80) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        Log.e("aaaaa", options + "-----" + baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        //Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return isBm;
    }


    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case START:
                if (jsonObject.getIntValue("code") == 1) {
                    ShowToast("认证成功");
                } else {
                    ShowToast("认证失败");
                }
                break;
        }
    }
}
