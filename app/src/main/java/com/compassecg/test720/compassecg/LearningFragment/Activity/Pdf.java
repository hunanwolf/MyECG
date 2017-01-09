package com.compassecg.test720.compassecg.LearningFragment.Activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.compassecg.test720.compassecg.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

public class Pdf extends AppCompatActivity implements OnPageChangeListener
        , OnLoadCompleteListener, OnDrawListener {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdflayout);

        pdfView = (PDFView) findViewById(R.id.pdfView);

//        //从assets目录读取pdf
////        displayFromAssets("bao.pdf");
//        displayFromAssets(getIntent().getExtras().getString("path"));
        //从文件中读取pdf
        displayFromFile(new File(getIntent().getExtras().getString("path")));

    }

    private void displayFromAssets(String assetFileName) {
        pdfView.fromAsset(assetFileName)   //设置pdf文件地址
                .defaultPage(6)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical(false)  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load();
    }

    private void displayFromFile(File file) {
        pdfView.fromFile(file)   //设置pdf文件地址
                .defaultPage(6)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical(false)  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load();
    }

    /**
     * 翻页回调
     *
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText(getApplicationContext(), "page= " + page +
                " pageCount= " + pageCount, Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载完成回调
     *
     * @param nbPages 总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText(getApplicationContext(), "加载完成" + nbPages, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
        // Toast.makeText( MainActivity.this ,  "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage="  + displayedPage , Toast.LENGTH_SHORT).show();
    }
}