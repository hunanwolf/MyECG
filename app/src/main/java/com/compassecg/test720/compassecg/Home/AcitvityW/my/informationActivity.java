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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.LoginActivity.cahnggeActivity;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.View.CircleImageView;
import com.compassecg.test720.compassecg.unitl.BarBaseActivity;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.compassecg.test720.compassecg.unitl.LocalImageHelper;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.L;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class informationActivity extends BarBaseActivity {
    CircleImageView header_iv;
    TextView tv_name, phone_tv, gender_tv, Hospital_tv, skill_tv, intro_tv;
    private final int RESULT_CAMERA_IMAGE = 1; //相机
    private final int RESULT_PHOTO_IMAGE = 2; //相册
    private final int PHOTO_REQUEST_CUT = 3;// 结果
    private static Uri imageUri;
    private static Bitmap bitmap;
    private static Uri uriTempFile;
    private String imageName;
    private File imageFile;
    private Intent intent;
    private static final int SATATl = 1;
    TextView renz_tv;
    private static final int SATAT = 2;
    private ByteArrayInputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setTitleString("个人信息");
        getView(R.id.header).setOnClickListener(this);//头像
        getView(R.id.name).setOnClickListener(this);//名称
        getView(R.id.phone).setOnClickListener(this);//手机号
        getView(R.id.gender).setOnClickListener(this);//职称
        getView(R.id.Hospital).setOnClickListener(this);//医院
        getView(R.id.skill).setOnClickListener(this);//科室
        getView(R.id.intro).setOnClickListener(this);//个人简介
        getView(R.id.renzheng).setOnClickListener(this);//认证状态
        getView(R.id.pass_change).setOnClickListener(this);//修改密码


        header_iv = getView(R.id.header_iv);
        tv_name = getView(R.id.tv_name);
        phone_tv = getView(R.id.phone_tv);
        gender_tv = getView(R.id.gender_tv);
        Hospital_tv = getView(R.id.Hospital_tv);
        skill_tv = getView(R.id.skill_tv);
        intro_tv = getView(R.id.intro_tv);
        renz_tv = getView(R.id.renz_tv);

        if ("".equals(getIntent().getExtras().getString("index"))) {
            getDate();
        } else {
            JSONObject object = JSON.parseObject(getIntent().getExtras().getString("index"));


            if ("".equals(object.getString("nickname"))) {
                tv_name.setText("暂未完善");
            } else {
                tv_name.setText(object.getString("nickname"));
            }


            if (object.getIntValue("status") == 1) {
                renz_tv.setText("·已认证");
            } else {
                renz_tv.setText("·未认证");
            }


            if ("".equals(object.getString("tel"))) {
                phone_tv.setText("暂未完善");
            } else {
                phone_tv.setText(object.getString("tel"));
            }


            if ("".equals(object.getString("job"))) {
                gender_tv.setText("暂未完善");
            } else {
                gender_tv.setText(object.getString("job"));
            }

            if ("".equals(object.getString("hospital"))) {
                Hospital_tv.setText("暂未完善");
            } else {
                Hospital_tv.setText(object.getString("hospital"));
            }

            if ("".equals(object.getString("desk"))) {
                skill_tv.setText("暂未完善");
            } else {
                skill_tv.setText(object.getString("desk"));
            }
            Glide.with(mContext)
                    .load(Connector.lll + object.getString("pic"))
                    .placeholder(R.drawable.index_head)
                    .centerCrop()
                    .into(header_iv);
        }
    }

    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        Postl(Connector.Personindex, params, SATATl);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.header://头像

                changeHeader();
                break;

            case R.id.name://名称
                changgeedit("名称", 1);

                break;
            case R.id.phone://手机号
                if (!"".equals(phone_tv.getText().toString())) {
                    startActivity(new Intent(this, cahnggeActivity.class).putExtra("tel", phone_tv.getText().toString()));
                }

                break;
            case R.id.gender://职称
                changgeedit("职称", 3);

                break;
            case R.id.Hospital://医院
                changgeedit("医院", 4);

                break;
            case R.id.skill://科室

                changgeedit("科室", 5);
                break;
            case R.id.intro://个人简介

                startActivity(new Intent(this, profileActivityW.class));
                break;
            case R.id.renzheng://认证状态
                if (renz_tv.getText().toString().equals("·未认证")) {
                    startActivity(new Intent(this, AuthenticationActivityW.class));
                }
                break;
            case R.id.pass_change://修改密码
                startActivity(new Intent(this, PassChangeActivityW.class));
                break;
        }
    }

    private void changgeedit(final String chang, final int i) {
        // 取得自定义View
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View myLoginView = layoutInflater.inflate(R.layout.dialog_chang_info, null);
        final TextView name = (TextView) myLoginView.findViewById(R.id.name);
        final EditText edittext = (EditText) myLoginView.findViewById(R.id.edittext);
        edittext.setHint("修改" + chang);
        name.setText(chang + ":");
        final android.app.AlertDialog dlgShowBack = new android.app.AlertDialog.Builder(this).create();
        dlgShowBack.setTitle(chang);
        dlgShowBack.setView(myLoginView);
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String changage = edittext.getText().toString();
                L.e("changage", changage);

                if ("".equals(changage)) {

                    ShowToast("修改内容不能为空");
                } else {
                    RequestParams params = new RequestParams();
                    params.put("uid", APP.uuid);

                    switch (i) {
                        case 1:
                            tv_name.setText(changage);
                            params.put("nickname", changage);
                            break;
                        case 2:
                            phone_tv.setText(changage);

                            break;

                        case 3:
                            gender_tv.setText(changage);
                            params.put("job", changage);
                            break;

                        case 4:
                            Hospital_tv.setText(changage);
                            params.put("hospital", changage);
                            break;

                        case 5:
                            skill_tv.setText(changage);
                            params.put("desk", changage);
                            break;
                    }
                    Post(Connector.editInfo, params, SATAT);
                    dialog.dismiss();
                }
            }
        });
//
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
        //, R.style.MenuDialog
        final AlertDialog d = new AlertDialog.Builder(this).create();
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
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
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
//                    Glide.with(this)
//                            .load(uriTempFile)
//                            .placeholder(R.drawable.ic_placeholder)
//                            .centerCrop()
//                            .into(header_iv);

                    is = compressImage(bitmap);
                    RequestParams params = new RequestParams();
                    params.put("uid", APP.uuid);
                    params.put("file", is, "tem.png", "image/png");
                    Post(Connector.editInfo, params, SATAT);

                }
                break;
        }
    }

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
        try {
            switch (what) {
                case SATATl:
                    if (jsonObject.getIntValue("code") == 1) {
                        JSONObject object = jsonObject.getJSONObject("list");


                        if ("".equals(object.getString("nickname"))) {
                            tv_name.setText("暂未完善");
                        } else {
                            tv_name.setText(object.getString("nickname"));
                        }


                        if (object.getIntValue("status") == 1) {
                            renz_tv.setText("·已认证");
                        } else if (object.getIntValue("status") == 2) {
                            renz_tv.setText("·未认证");
                        } else {
                            renz_tv.setText("·认证中");
                        }


                            phone_tv.setText(object.getString("tel"));



                        if ("".equals(object.getString("job"))) {
                            gender_tv.setText("暂未完善");
                        } else {
                            gender_tv.setText(object.getString("job"));
                        }

                        if ("".equals(object.getString("hospital"))) {
                            Hospital_tv.setText("暂未完善");
                        } else {
                            Hospital_tv.setText(object.getString("hospital"));
                        }

                        if ("".equals(object.getString("desk"))) {
                            skill_tv.setText("暂未完善");
                        } else {
                            skill_tv.setText(object.getString("desk"));
                        }

                        Glide.with(mContext)
                                .load(Connector.lll + object.getString("pic"))
                                .placeholder(R.drawable.index_head)
                                .centerCrop()
                                .into(header_iv);
                    }

                    break;

                case SATAT:

                    if (jsonObject.getIntValue("code") == 1) {

                        ShowToast("修改成功");

                        getDate();
                    }

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
