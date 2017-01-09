package com.compassecg.test720.compassecg.Home.seach;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.compassecg.test720.compassecg.APP;
import com.compassecg.test720.compassecg.Home.adapter.Post_list_adapterW;
import com.compassecg.test720.compassecg.Home.adapter.forum_list_adapterW;
import com.compassecg.test720.compassecg.R;
import com.compassecg.test720.compassecg.bean.UserList;
import com.compassecg.test720.compassecg.unitl.UuidUtil;
import com.test720.auxiliary.Utils.NoBarBaseActivity;

import java.util.ArrayList;
import java.util.List;


public class search_activity extends NoBarBaseActivity {

    ListView list;


    public static final int ALNGKALG = 1024;//首页
    public static final int LKANGALGNA = 1025;//学习中心
    public static final int JMLKMLLLKL = 1026;//课件
    public static final int KAJNGANGIOAN = 1027;//杂志
    public static final int JNLAKNGANGALKM = 1028;//医生
    public static final int XIAOZU = 1029;//小组
    public static final int LUNTANG = 1030;//论坛

    GridView lg_gridview;
    PopupWindow PopupWindow;//

    List<String> list1 = new ArrayList<>();
    RelativeLayout search;
    EditText editText;
    int SATAR = 1;
    Post_list_adapterW adapter2;//帖子适配器
    forum_list_adapterW adapter3;//论坛
    //    List<listfrashi> listfrst = new ArrayList<>();
    private int MaxPage;
    SwipeRefreshLayout swip;
    int thispage = 1;
    int type = 1;
    RelativeLayout back;
    String title;
    ImageView lv_search;
    RelativeLayout layoutnull;
    private List<String> list2 = new ArrayList<>();
    RelativeLayout layout;
    TextView leibie;
    List<UserList> listsearch = new ArrayList<>();
    List<UserList> lists = new ArrayList<>();
    ListView listll;
    searchhistory_adapter adapter1;
    TextView lt_history;
    ImageView tu;
    RelativeLayout lv_Searchhistory;
    private LinearLayout seshahj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        lv_Searchhistory = getView(R.id.lv_Searchhistory);
        seshahj = getView(R.id.seshahj);
        leibie = getView(R.id.leibie);
        switch (getIntent().getExtras().getInt("type")) {
            case ALNGKALG:
                lv_Searchhistory.setVisibility(View.VISIBLE);
                leibie.setText("论坛");
                type = 1;
                break;

            case LKANGALGNA:
                type = 3;
                leibie.setText("医生");
                break;

            case KAJNGANGIOAN:
                leibie.setText("杂志");
                type = 4;
                break;

            case JNLAKNGANGALKM:
                type = 3;
                leibie.setText("医生");
                break;

            case JMLKMLLLKL:
                type = 5;
                leibie.setText("课件");
                break;
            case XIAOZU:
                type = 6;
                leibie.setText("小组");
                lv_Searchhistory.setVisibility(View.GONE);
                break;
            case LUNTANG:
                type = 7;
                leibie.setText("论坛");
                lv_Searchhistory.setVisibility(View.GONE);
                break;
        }
        initview();
        gethistoy();
    }


    public void gethistoy() {
        listsearch.addAll(UuidUtil.GetRecordList(mContext, APP.uuid));
        adapter1.notifyDataSetChanged();
    }

    public void initview() {
        layout = getView(R.id.layout);
        getView(R.id.back).setOnClickListener(this);
        list = (ListView) findViewById(R.id.lsitview);
        //seshahj = (LinearLayout) findViewById(R.id.seshahj);

        listll = getView(R.id.listll);
        adapter1 = new searchhistory_adapter(mContext, listsearch);
        listll.setAdapter(adapter1);
        getView(R.id.seshahj).setOnClickListener(this);

        swip = (SwipeRefreshLayout) findViewById(R.id.swip);
        search = getView(R.id.search);
        search.setOnClickListener(this);
        lv_search = getView(R.id.lv_search);
        editText = getView(R.id.editText);
        lt_history = getView(R.id.lt_history);
        lt_history.setText("历史记录");
        tu = getView(R.id.tu);
        listLisener();
    }

    public void listLisener() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type == 1) {
//                    list2.clear();
//                    list2.addAll(listfrst.get(position).getPicture());
//                    Intent intent = new Intent(search_activity.this, Goods_details_activity.class);
//                    intent.putExtra("id", "" + listfrst.get(position).getId());
//                    intent.putStringArrayListExtra("list", (ArrayList<String>) list2);
//
//                    startActivity(intent);
                } else {
//                    list2.clear();
//                    list2.addAll(listfrst.get(position).getPicture());
//                    Intent intent = new Intent(mContext, ProjectDetailActivity.class);
//                    intent.putExtra("id", listfrst.get(position).getId());
//                    intent.putStringArrayListExtra("list", (ArrayList<String>) list2);
////                    intent.putExtra("type", "2");
//                    startActivity(intent);
                }

            }
        });

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (view.getLastVisiblePosition() == view.getCount() - 1 && thispage < MaxPage & !progressBar.isShowing()) {
                    thispage++;
//                    Toast.makeText(getActivity(), "上拉加载更多！", Toast.LENGTH_SHORT).show();
                    fetchData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        listll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lists.clear();

                editText.setText(listsearch.get(position).getName());
                if (listsearch.get(position).getId().equals("1")) {
                    type = 1;
                    thispage = 1;
                    leibie.setText("论坛");
                } else if (listsearch.get(position).getId().equals("2")) {
                    type = 2;
                    thispage = 1;
                    leibie.setText("会诊");
                } else if (listsearch.get(position).getId().equals("3")) {
                    type = 3;
                    thispage = 1;
                    leibie.setText("医生");
                } else if (listsearch.get(position).getId().equals("4")) {
                    type = 4;
                    thispage = 1;
                    leibie.setText("杂志");
                } else if (listsearch.get(position).getId().equals("5")) {
                    type = 5;
                    thispage = 1;
                    leibie.setText("课件");
                } else if (listsearch.get(position).getId().equals("6")) {
                    type = 6;
                    thispage = 1;
                    leibie.setText("小组");
                } else if (listsearch.get(position).getId().equals("7")) {
                    type = 7;
                    thispage = 1;
                    leibie.setText("帖子");
                }
//                else if (listsearch.get(position).getId().equals("8")) {
//                    type = 8;
//                    thispage = 1;
//                    leibie.setText("论坛");
//                }
                else if (listsearch.get(position).getId().equals("9")) {
                    type = 9;
                    thispage = 1;
                    leibie.setText("ECG");
                } else if (listsearch.get(position).getId().equals("10")) {
                    type = 10;
                    thispage = 1;
                    leibie.setText("DCG");
                } else if (listsearch.get(position).getId().equals("11")) {
                    type = 11;
                    thispage = 1;
                    leibie.setText("ABPM");
                } else if (listsearch.get(position).getId().equals("12")) {
                    type = 12;
                    thispage = 1;
                    leibie.setText("其他");
                } else {

                }
                lists.add(listsearch.get(position));
                listsearch.remove(position);
                UuidUtil.AddRecordList(mContext, listsearch, APP.uuid);
                listsearch.clear();
                listsearch.addAll(lists);
                listsearch.addAll(UuidUtil.GetRecordList(mContext, APP.uuid));
                UuidUtil.AddRecordList(mContext, listsearch, APP.uuid);
                adapter1.notifyDataSetChanged();
            }
        });

    }


    public void fetchData() {

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.seshahj:
                popwind();
                break;
            case R.id.back:
                finish();
                break;

            case R.id.search:
                switch (type) {
                    case 1:
                        thispage = 1;
                        int k = -1;
                        if (!editText.getText().toString().equals("")) {
                            title = editText.getText().toString();

                            UserList userList = new UserList();
                            userList.setId("1");
                            userList.setName(editText.getText().toString());

                            for (int j = 0; j < listsearch.size(); j++) {

                                if (editText.getText().toString().equals(listsearch.get(j).getName()) && "1".equals(listsearch.get(j).getId())) {
                                    k = 1;
                                }
                            }
                            if (k == -1) {
                                listsearch.clear();
                                listsearch.add(userList);
                                listsearch.addAll(UuidUtil.GetRecordList(mContext, APP.uuid));

                                for (int i = 0; i < listsearch.size(); i++) {
                                    if (i > 4) {
                                        listsearch.remove(i);
                                    }
                                }
                                UuidUtil.AddRecordList(mContext, listsearch, APP.uuid);
                                adapter1.notifyDataSetChanged();
                            }
                        } else {
                            ShowToast("搜索内容不能为空！");
                        }
                        break;


                    case 2:

                        int m = -1;
                        if (!editText.getText().toString().equals("")) {
                            title = editText.getText().toString();

                            UserList userList = new UserList();
                            userList.setId("2");
                            userList.setName(editText.getText().toString());

                            for (int j = 0; j < listsearch.size(); j++) {

                                if (editText.getText().toString().equals(listsearch.get(j).getName()) && "1".equals(listsearch.get(j).getId())) {
                                    m = 1;
                                }
                            }

                            if (m == -1) {
                                listsearch.clear();

                                listsearch.add(userList);
                                listsearch.addAll(UuidUtil.GetRecordList(mContext, APP.uuid));
                                for (int i = 0; i < listsearch.size(); i++) {
                                    if (i > 4) {
                                        listsearch.remove(i);
                                    }
                                }
                                UuidUtil.AddRecordList(mContext, listsearch, APP.uuid);
                                adapter1.notifyDataSetChanged();
                            }
                        } else {
                            ShowToast("搜索内容不能为空！");
                        }
                        break;
                }

                break;
        }
    }

    public void popwind() {
        LayoutInflater inflater = LayoutInflater.from(this);
        // 引入窗口配置文件
        View view = inflater.inflate(R.layout.popsearch, null);
        // 创建PopupWindow对象
        PopupWindow = new
                PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        RelativeLayout btn = (RelativeLayout) view.findViewById(R.id.id1);
        RelativeLayout btn1 = (RelativeLayout) view.findViewById(R.id.id2);
        RelativeLayout btn2 = (RelativeLayout) view.findViewById(R.id.id3);
        RelativeLayout btn3 = (RelativeLayout) view.findViewById(R.id.id4);
        RelativeLayout btn4 = (RelativeLayout) view.findViewById(R.id.id5);
        RelativeLayout btn5 = (RelativeLayout) view.findViewById(R.id.id6);
        RelativeLayout btn6 = (RelativeLayout) view.findViewById(R.id.id7);

        RelativeLayout btn8 = (RelativeLayout) view.findViewById(R.id.id9);
        RelativeLayout btn9 = (RelativeLayout) view.findViewById(R.id.id10);
        RelativeLayout btn10 = (RelativeLayout) view.findViewById(R.id.id11);
        RelativeLayout btn11 = (RelativeLayout) view.findViewById(R.id.id12);

        switch (getIntent().getExtras().getInt("type")) {
            case ALNGKALG:
                btn5.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);
                btn8.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);
                break;
            case LKANGALGNA:

                btn.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);

                btn8.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);
                break;
            case JMLKMLLLKL:
                btn.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);

                btn8.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);

                break;
            case KAJNGANGIOAN:
                btn.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);

                btn8.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);
                break;
            case JNLAKNGANGALKM:
                btn.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);

                btn8.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);
                break;
            case XIAOZU:
                btn.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn8.setVisibility(View.GONE);
                btn9.setVisibility(View.GONE);
                btn10.setVisibility(View.GONE);
                btn11.setVisibility(View.GONE);

                break;
            case LUNTANG:
                btn.setVisibility(View.GONE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
                btn5.setVisibility(View.GONE);
                btn6.setVisibility(View.GONE);
                break;

        }

        // 需要设置一下此参数，点击外边可消失
        PopupWindow.setBackgroundDrawable(new
                BitmapDrawable());//这一句 要加，不然点击外边不消失。
        //设置点击窗口外边窗口消失
        PopupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
//设置popwindow的位置  tv:为微信右上角+号view，居于+号下方
        int mxl = 30;
//        int ll=mmm.getHeight();
        PopupWindow.showAsDropDown(lv_search, -120, 30);
//        PopupWindow.showAsDropDown(lv_search);
        PopupWindow.setFocusable(true);
        // 显示窗口
        PopupWindow.showAsDropDown(view);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                thispage = 1;
                leibie.setText("会诊");
                PopupWindow.dismiss();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                thispage = 1;
                leibie.setText("论坛");
                PopupWindow.dismiss();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 3;
                thispage = 1;
                leibie.setText("医生");
                PopupWindow.dismiss();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 4;
                thispage = 1;
                leibie.setText("杂志");
                PopupWindow.dismiss();

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 5;
                thispage = 1;
                leibie.setText("课件");
                PopupWindow.dismiss();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 6;
                thispage = 1;
                leibie.setText("小组");
                PopupWindow.dismiss();

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 7;
                thispage = 1;
                leibie.setText("帖子");
                PopupWindow.dismiss();

            }
        });
//        btn7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                type = 8;
//                thispage = 1;
//                leibie.setText("论坛");
//                PopupWindow.dismiss();
//
//            }
//        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 9;
                thispage = 1;
                leibie.setText("ECG");
                PopupWindow.dismiss();

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 10;
                thispage = 1;
                leibie.setText("DCG");
                PopupWindow.dismiss();

            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 11;
                thispage = 1;
                leibie.setText("ABPM");
                PopupWindow.dismiss();

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 12;
                thispage = 1;
                leibie.setText("其他");
                PopupWindow.dismiss();

            }
        });
    }


    @Override
    public void Getsuccess(JSONObject jsonObject, int what) {
        super.Getsuccess(jsonObject, what);
        switch (what) {
            case 1:
//                if (thispage == 1) {
//                    listfrst.clear();
//                }
//                JSONArray jsonArray = jsonObject.getJSONArray("list");
//                if (jsonArray.size() != 0) {
//                    List<listfrashi> newStr2 = JSONObject.parseArray(jsonArray.toJSONString(), listfrashi.class);
//                    listfrst.addAll(newStr2);
//                    MaxPage = jsonObject.getIntValue("page");
//
//                }
//                adapter.notifyDataSetChanged();
//                lt_history.setText("搜索结果");
//                if (listfrst.size() != 0) {
//                    layout.setVisibility(View.INVISIBLE);
//                    listll.setVisibility(View.INVISIBLE);
//                } else {
////                    lt_history.setText("历史记录");
//                    layout.setVisibility(View.VISIBLE);
//                    listll.setVisibility(View.INVISIBLE);
//                    tu.setVisibility(View.VISIBLE);
//                }
                break;
        }
    }
}
