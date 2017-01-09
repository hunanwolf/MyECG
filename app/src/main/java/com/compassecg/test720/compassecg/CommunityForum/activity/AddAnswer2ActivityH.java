package com.compassecg.test720.compassecg.CommunityForum.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.UploadPicture.ChoosePicFolderActivity;
import com.compassecg.test720.compassecg.View.KeyboardListenRelativeLayout;
import com.compassecg.test720.compassecg.unitl.Connector;
import com.compassecg.test720.compassecg.unitl.LocalImageHelper;
import com.loopj.android.http.RequestParams;
import com.test720.auxiliary.Utils.NoBarBaseActivity;
import com.test720.auxiliary.Utils.T;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddAnswer2ActivityH extends NoBarBaseActivity {

    private RelativeLayout rl_text;
    private RelativeLayout rl_song;
    private RelativeLayout rl_pic;
    private GridView lv_gridview;
    private LocalImageHelper.LocalFile fakeImage;
    int MaxNumaber = 9;//要求上传最大数值
    private static final int A = 999;
    private List<LocalImageHelper.LocalFile> showFiles = new ArrayList<>();//附件材料
    private List<String> allItems = new ArrayList<>();
    private MyAdapter adapter;
    private AlertDialog picDialog;
    private String imageName;
    private File imageFile;
    /**
     * 请求裁剪
     */
    public static final int REQUEST_CODE_GETIMAGE_BYCROP = 2;
    private LinearLayout ll_bottom;
    private KeyboardListenRelativeLayout root;

    private ImageView iv_back;
    private ImageView iv_jianpan;
    private ImageView iv_lvyin;
    private ImageView iv_pic;
    private RelativeLayout rl_choose_group;
    private TextView tv_choose;
    private int type = 0;
    private TextView textView11;
    private final int SATAR = 1;
    EditText rl_choose_group2;
    EditText et_content;
    private ByteArrayInputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer2);
        initView();
        setAdapter();

        setListenner();
        setgridview();
//        controlKeyboardLayout(root,ll_bottom);
    }

    private void setAdapter() {

    }

    private void setListenner() {
        rl_text.setOnClickListener(this);
        rl_pic.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        rl_choose_group.setOnClickListener(this);
        root.setOnKeyboardStateChangedListener(new KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener() {
            @Override
            public void onKeyboardStateChanged(int state) {
                switch (state) {
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE://软键盘隐藏
                        Log.i("WOLF", "软键盘隐藏");
                        //更改图标
                        iv_jianpan.setImageResource(R.mipmap.answer_btn_jianpan);
                        //controlKeyboardLayout(root);
                        break;
                    case KeyboardListenRelativeLayout.KEYBOARD_STATE_SHOW://软键盘显示
                        Log.i("WOLF", "软键盘显示");
                        //更改图标
                        iv_jianpan.setImageResource(R.mipmap.answer_btn_jianpan_pre);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //提交问题
    public void getDate() {
        RequestParams params = new RequestParams();
        params.put("uid", APP.uuid);
        params.put("module", tv_choose.getText().toString());
        params.put("content", rl_choose_group2.getText().toString());
        params.put("title", et_content.getText().toString());
        if (allItems.size() != 0) {
            for (int i = 0; i < allItems.size(); i++) {
                Uri uri = Uri.parse(allItems.get(i));
                String path = getRealFilePath(mContext, uri);
                is = resizeBitmap(path, 680);
                params.put("img" + i, is, "tem.png", "image/png");
            }
        }
        Post(Connector.addProblem, params, SATAR);
    }

    private void initView() {
        rl_text = getView(R.id.rl_text);
        rl_pic = getView(R.id.rl_pic);
        lv_gridview = getView(R.id.lv_gridview);
        root = getView(R.id.root);
        ll_bottom = getView(R.id.ll_bottom);
        et_content = getView(R.id.et_content);
        iv_back = getView(R.id.iv_back);
        iv_jianpan = getView(R.id.iv_jianpan);
        iv_lvyin = getView(R.id.iv_lvyin);
        iv_pic = getView(R.id.iv_pic);
        rl_choose_group = getView(R.id.rl_choose_group);
        tv_choose = getView(R.id.tv_choose);
        textView11 = getView(R.id.textView11);
        textView11.setOnClickListener(this);
        rl_choose_group2 = getView(R.id.rl_choose_group2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_text:
                popInput();
                iv_jianpan.setImageResource(R.mipmap.answer_btn_jianpan_pre);

                break;
            case R.id.rl_pic:
                closeKey();
                showPicDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_choose_group:
                showGroupDialog();
                break;

            case R.id.textView11:

                if (type == 0) {
                    ShowToast("请选择模块！");
                    return;
                }
                if ("".equals(rl_choose_group2.getText().toString())) {
                    ShowToast("请输入标题");
                    return;
                }

                if ("".equals(et_content.getText().toString())) {
                    ShowToast("请输入问题内容！");
                    return;
                }


                showConflictDialog();

                break;
        }
    }

    private void showConflictDialog() {
        final android.app.AlertDialog dlgShowBack = new android.app.AlertDialog.Builder(this).create();
        dlgShowBack.setTitle("提示");
        dlgShowBack.setMessage("是否确认发布？");
        dlgShowBack.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getDate();


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

    /**
     * 关闭键盘
     */
    private void closeKey() {
        InputMethodManager inputManager =
                (InputMethodManager) et_content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(et_content.getWindowToken(), 0);
        //更改图标
        iv_jianpan.setImageResource(R.mipmap.answer_btn_jianpan);
    }

    /**
     * 获取焦点并弹出输入法
     */
    private void popInput() {
        et_content.setFocusable(true);
        et_content.setFocusableInTouchMode(true);
        et_content.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) et_content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et_content, 0);
    }


    public void setgridview() {
        lv_gridview = getView(R.id.lv_gridview);
        Resources r = mContext.getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(R.drawable.add) + "/"
                + r.getResourceTypeName(R.drawable.add) + "/"
                + r.getResourceEntryName(R.drawable.add));
        fakeImage = LocalImageHelper.getInstance().getFake();
        fakeImage.setOriginalUri("fake");
        fakeImage.setThumbnailUri(uri.toString());
        List<LocalImageHelper.LocalFile> items = LocalImageHelper.getInstance().getCheckedItems();
        if (items.size() < MaxNumaber) {
            if (items.size() == 0) {
                //没有图片的时候不显示“加号”
            } else {
                showFiles.addAll(items);
                showFiles.add(fakeImage);
            }
        } else {
            showFiles.addAll(items);
        }
        adapter = new MyAdapter(mContext, showFiles);
        lv_gridview.setAdapter(adapter);
        setGridViewHeight(lv_gridview);
    }

    public class MyAdapter extends BaseAdapter {
        private Context m_context;
        List<LocalImageHelper.LocalFile> files;


        public MyAdapter(Context context, List<LocalImageHelper.LocalFile> files) {
            m_context = context;
            this.files = files;
        }

        @Override
        public int getCount() {
            return files.size();
        }

        @Override
        public LocalImageHelper.LocalFile getItem(int i) {
            return files.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;

            if (convertView == null || convertView.getTag() == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(m_context, R.layout.slmple_layout_grdview, null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                viewHolder.checkBox.setOnCheckedChangeListener(new Mycheck());
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (files.size() != 0) {

                if (files.get(i) == fakeImage) {
                    viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPicDialog();
                        }
                    });
                    viewHolder.checkBox.setVisibility(View.GONE);
                } else {
                    viewHolder.imageView.setOnClickListener(null);
                    viewHolder.checkBox.setVisibility(View.VISIBLE);
                }
                LocalImageHelper.LocalFile localFile = files.get(i);
                Glide.with(mContext).load(localFile.getThumbnailUri()).into(viewHolder.imageView);

                viewHolder.checkBox.setTag(localFile);
                viewHolder.checkBox.setChecked(files.contains(localFile));
            }
            return convertView;
        }

        private class ViewHolder {
            ImageView imageView;
            CheckBox checkBox;
        }

        private class Mycheck implements CompoundButton.OnCheckedChangeListener {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    if (files.contains(compoundButton.getTag())) {
                        //删除当前页面显示的图片
                        files.remove(compoundButton.getTag());
                        //  L.e("===files", files.size() + "");
                        //删除helper里选中的对应图片
                        LocalImageHelper.getInstance().getCheckedItems().remove(compoundButton.getTag());
//                        L.e("===files2", LocalImageHelper.getInstance().getCheckedItems().size() + "");
                        List<LocalImageHelper.LocalFile> items = LocalImageHelper.getInstance().getCheckedItems();
                        showFiles.clear();
                        if (items.size() < MaxNumaber) {
                            if (items.size() == 0) {

                            } else {
                                showFiles.addAll(items);
                                showFiles.add(fakeImage);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        setGridViewHeight(lv_gridview);
                    }
                }
            }
        }
    }

    //弹出对话框
    public void showPicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        picDialog = builder.create();
        picDialog.show();
        // 获取dialog的窗口
        Window window = picDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(android.R.color.white);
        View v = View.inflate(this, R.layout.dialog_choose_pic, null);
        // 获取v对象中的控件 .setOnClickListener
        picDialog.setCanceledOnTouchOutside(true);
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lParams = window.getAttributes();
        lParams.width = (int) (display.getWidth());
        window.setAttributes(lParams);
        v.findViewById(R.id.take_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChoosePicFolderActivity.class);
                intent.putExtra("MaxNumber", MaxNumaber);
                startActivityForResult(intent, REQUEST_CODE_GETIMAGE_BYCROP);
                //当界面摧毁后, 该发布界面的图片都清空, 防止下一个同类界面的选图重用
                picDialog.cancel();
            }
        });
        v.findViewById(R.id.upload_from_local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePics();
//当界面摧毁后, 该发布界面的图片都清空, 防止下一个同类界面的选图重用

                picDialog.dismiss();

            }
        });
        v.findViewById(R.id.pic_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picDialog.dismiss();
            }
        });
        window.setContentView(v);
    }

    //弹出对话框
    public void showGroupDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        picDialog = builder.create();
        picDialog.show();
        // 获取dialog的窗口
        Window window = picDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(android.R.color.white);
        View v = View.inflate(this, R.layout.dialog_choose_group, null);
        // 获取v对象中的控件 .setOnClickListener
        picDialog.setCanceledOnTouchOutside(true);
        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lParams = window.getAttributes();
        lParams.width = (int) (display.getWidth());
        window.setAttributes(lParams);

        v.findViewById(R.id.pic_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picDialog.dismiss();
            }
        });
        v.findViewById(R.id.rl_ecg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                tv_choose.setText("ECG");
                picDialog.dismiss();
            }
        });
        v.findViewById(R.id.rl_dcg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                tv_choose.setText("DCG");
                picDialog.dismiss();
            }
        });
        v.findViewById(R.id.rl_abpm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 3;
                tv_choose.setText("ABPM");
                picDialog.dismiss();
            }
        });
        v.findViewById(R.id.rl_qita).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 4;
                tv_choose.setText("其他");
                picDialog.dismiss();
            }
        });
        window.setContentView(v);
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
        startActivityForResult(intent, A);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_GETIMAGE_BYCROP:
                if (LocalImageHelper.getInstance().isResultOk()) {
                    LocalImageHelper.getInstance().setResultOk(false);
                    //获取选中的图片
                    List<LocalImageHelper.LocalFile> files = LocalImageHelper.getInstance().getCheckedItems();
                    showFiles.clear();
                    showFiles.addAll(files);
                    if (files.size() < MaxNumaber) {
                        showFiles.add(fakeImage);
                    }
                    List<LocalImageHelper.LocalFile> checks = LocalImageHelper.getInstance().getCheckedItems();

                    allItems.clear();
                    for (LocalImageHelper.LocalFile f : checks) {
                        allItems.add(f.getThumbnailUri());
                    }
                    adapter.notifyDataSetChanged();
                    setGridViewHeight(lv_gridview);
                }
                break;
            case A:
//                Log.e("===skkkkkkkkkkk", showFiles.toString());
                if (resultCode == Activity.RESULT_OK) {
                    String cameraPath = LocalImageHelper.getInstance().getCameraImgPath();
                    if (cameraPath == null | cameraPath.equals("")) {
                        Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    File file = new File(cameraPath);
                    Uri uri = Uri.fromFile(file);
                    LocalImageHelper.LocalFile b = new LocalImageHelper.LocalFile();
                    b.setOriginalUri(uri.toString());
                    b.setThumbnailUri(uri.toString());
                    b.setOrientation(getBitmapDegree(cameraPath));
                    LocalImageHelper.getInstance().getCheckedItems().add(b);
                    LocalImageHelper.getInstance().setResultOk(true);
                    List<LocalImageHelper.LocalFile> files = LocalImageHelper.getInstance().getCheckedItems();
                    showFiles.clear();

                    showFiles.addAll(files);
                    if (files.size() < MaxNumaber) {
                        showFiles.add(fakeImage);
                    }
                    //   L.e("===size", files.size() + "");
                    List<LocalImageHelper.LocalFile> checks = LocalImageHelper.getInstance().getCheckedItems();
                    allItems.clear();

                    for (LocalImageHelper.LocalFile f : checks) {
                        allItems.add(f.getThumbnailUri());
                    }
                    adapter.notifyDataSetChanged();
                    setGridViewHeight(lv_gridview);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "取消拍照", Toast.LENGTH_SHORT).show();
                } else
                    break;
        }
    }

    /**
     * 读取图片的旋转的角度，还是三星的问题，需要根据图片的旋转角度正确显示
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        if (jsonObject.getString("code").equals("1")) {
            Toast.makeText(mContext, "发布成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            T.showShort(mContext, "发布失败！");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setGridViewHeight(GridView gridView) {
        ListAdapter adapter = gridView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeigt = 0;
        double itemCount = adapter.getCount();
        double horizentalNumger = gridView.getNumColumns();
        int verticalNumber = (int) Math.ceil(itemCount / horizentalNumger);
        View item = adapter.getView(0, null, gridView);
        item.measure(0, 0);
        int itemHeight = item.getMeasuredHeight();
        int space = gridView.getVerticalSpacing();
        totalHeigt = verticalNumber * itemHeight + (verticalNumber - 1) * space;
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeigt;
        gridView.setLayoutParams(params);
    }

  /*  *//**
     * @param root
     *            最外层布局，需要调整的布局
     * @param scrollToView
     *            被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     *//*
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;
                        Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (location[1] + scrollToView
                                    .getHeight()) - rect.bottom;
                            root.scrollTo(0, srollHeight);
                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }

    */

    /**
     * 最外层布局，需要调整的布局
     * 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     *//*
    private void controlKeyboardLayout(final View root) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect r = new Rect();
                        //获取当前界面可视部分
                        getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                        //获取屏幕的高度
                        int screenHeight =  getWindow().getDecorView().getRootView().getHeight();
                        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                        int heightDifference = screenHeight - r.bottom;
                        Log.e("Keyboard Size", "Size: " + heightDifference);

                    }
                });
    }*/
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private ByteArrayInputStream compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 60, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 80) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

            options -= 5;//每次都减少10
        }
        Log.e("aaaaa", options + "-----" + baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        //Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return isBm;
    }

    /**
     * @param file     图片文件地址
     * @param newWidth 压缩尺寸
     * @return
     */
    public ByteArrayInputStream resizeBitmap(String file, int newWidth) {
        Bitmap bitmap = BitmapFactory.decodeFile(file);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float temp = ((float) height) / ((float) width);
        int newHeight = (int) ((newWidth) * temp);
        newHeight = newHeight > height ? height : newHeight;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//        bitmap.recycle();
        return compressImage(resizedBitmap);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalImageHelper.getInstance().getCheckedItems().clear();
    }
}
