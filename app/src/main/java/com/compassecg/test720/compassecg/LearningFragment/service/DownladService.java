package com.compassecg.test720.compassecg.LearningFragment.service;

/**
 * Created by hp on 2016/12/27.
 */

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.compassecg.test720.compassecg.LearningFragment.Activity.CoursewaredetailsActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;


public class DownladService extends Service {
    private Handler handler;
    private final int NotificationID = 0x10000;
    private NotificationManager mNotificationManager = null;
    private NotificationCompat.Builder builder;


//    // 文件下载路径
//    private String APK_url = "";
//    // 文件保存路径(如果有SD卡就保存SD卡,如果没有SD卡就保存到手机包名下的路径)
//    private String APK_dir = "";

    private String path="";
    /**
     * Title: onBind
     *
     * @param intent
     * @return
     * @Description:
     * @see Service#onBind(Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Title: onCreate
     *
     * @Description:
     * @see Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
//        initAPKDir();// 创建保存路径
    }

    /**
     * Title: onStartCommand
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     * @Description:
     * @see Service#onStartCommand(Intent, int, int)
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        // 接收Intent传来的参数:
//        APK_url = intent.getStringExtra("url");
//        path=intent.getStringExtra("path");

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
//                dialog.cancel();
//                dialog.setProgress(0);
            }
        };


        loadDataThreah ldt = new loadDataThreah(intent.getStringExtra("path"),intent.getStringExtra("url"));
        ldt.start();
        return super.onStartCommand(intent, flags, startId);
    }

    // 进度条线程
    class loadDataThreah extends Thread {
        String path;
        String url;
        public loadDataThreah(String path,String url) {
            this.path=path;
            this.url=url;
        }

        public void run() {
            try {
                    showPDF(url,path);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }
    }
//
//    private void initAPKDir() {
//        /**
//         * 创建路径的时候一定要用[/],不能使用[\],但是创建文件夹加文件的时候可以使用[\].
//         * [/]符号是Linux系统路径分隔符,而[\]是windows系统路径分隔符 Android内核是Linux.
//         */
//        if (isHasSdcard())// 判断是否插入SD卡
//            APK_dir = getApplicationContext().getFilesDir().getAbsolutePath() + "/apk/download/";// 保存到app的包名路径下
//        else
//            APK_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apk/download/";// 保存到SD卡路径下
//        File destDir = new File(APK_dir);
//        if (!destDir.exists()) {// 判断文件夹是否存在
//            destDir.mkdirs();
//        }
//    }

    /**
     * @Description:判断是否插入SD卡
     */
    private boolean isHasSdcard() {
        String status = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    // 从服务器下载PDF并且跳转到MUPDF的ACTIVITY
    public void showPDF(String urlpath,String path1) throws Exception {
        Log.e("进来","anlnagk");
        URL u = new URL(urlpath);
        String path = createDir(path1);
        byte[] buffer = new byte[1024 * 8];
        int read;
        int ava = 0;
        long start = System.currentTimeMillis();
        BufferedInputStream bin;
        try {
            HttpURLConnection urlcon = (HttpURLConnection) u.openConnection();
            urlcon.setRequestMethod("GET");
            urlcon.setRequestProperty("Accept-Encoding", "identity");
            double fileLength = (double) urlcon.getContentLength();
            bin = new BufferedInputStream(u.openStream());
            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path));
            while ((read = bin.read(buffer)) > -1) {
                bout.write(buffer, 0, read);
                ava += read;
                Log.e("this","ava"+ava+"fileLength"+fileLength);
                int a = (int) Math.floor((ava / fileLength * 100));
                if (!CoursewaredetailsActivity.test_a.isFinishing()){
                    CoursewaredetailsActivity.test_a.  chang(a,path1,path);
                }
//                Main.test_a.  chang(a,path1);
//                dialog.setProgress(a);
                long speed = ava / (System.currentTimeMillis() - start);
                System.out.println("Download: " + ava + " byte(s)"
                        + "    avg speed: " + speed + "  (kb/s)");
            }
            bout.flush();
            bout.close();
            DownladService.this.stopSelf();
//            Uri uri = Uri.parse(path);
//            Intent intent = new Intent(getApplicationContext(),
//                    MainActivity.class);
////			intent.setAction(Intent.ACTION_VIEW);
////			intent.setData(uri);
//            intent.putExtra("path", path);
//            startActivity(intent);
           Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_LONG);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
//
//    public static String FirstFolder = "Mydonwload";//定义需要创建的根目录文件夹名字
//    public static String File_name = "HelloTest.txt";//定义需要创建的根目录文件夹名字
//    public static String SecondFolder = "MyMobileDownlod";//定义需要创建的二级目录文件夹，根据需要进行设置
//    public final static String Folder_NAME = Environment.getExternalStorageDirectory() + File.separator + FirstFolder;
//    public final static String Second_PATH = Folder_NAME + SecondFolder + File.separator;
//    public static final String FILE_NAME = Folder_NAME + File.separator + File_name;

    private String createDir(String filename) {


        File sdcardDir = Environment.getExternalStorageDirectory();
//		File sdcardDir="Mydonwload/";

        // 得到一个路径，内容是sdcard的文件夹路径和名字
        String path = getCachePath() + "/MyMobileDownlod";
        File path1 = new File(path);
        if (!path1.exists()) {
            // 若不存在，创建目录，可以在应用启动的时候创建
            path1.mkdirs();
        }
        path = path + "/" + filename;
        Log.e("sdcardDir", path + "");

        return path;

    }

    public String getCachePath() {
        File cacheDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            cacheDir = this.getExternalCacheDir();
        else
            cacheDir = this.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        return cacheDir.getAbsolutePath();
    }

    /**
     * @param x     当前值
     * @param total 总值
     * @return 当前百分比
     * @Description:返回百分之值
     */
    private String getPercent(int x, int total) {
        String result = "";// 接受百分比的值
        double x_double = x * 1.0;
        double tempresult = x_double / total;
        // 百分比格式，后面不足2位的用0补齐 ##.00%
        DecimalFormat df1 = new DecimalFormat("0.00%");
        result = df1.format(tempresult);
        return result;
    }

    /**
     * @return
     * @Description:获取当前应用的名称
     */
    private String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * Title: onDestroy
     *
     * @Description:
     * @see Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
//        DownAPKService.this.stopSelf();

    }
    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className
     *            某个界面名称
     */
    private boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }


}