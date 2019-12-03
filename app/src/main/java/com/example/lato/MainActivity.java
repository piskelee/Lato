package com.example.lato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from={"title","num0","num1","num2","num3","num4","num5"};
    private int[] to = {R.id.item_title,R.id.item_num0,R.id.item_num1,R.id.item_num2,
            R.id.item_num3,R.id.item_num4,R.id.item_num5};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        initView();
    }

    //初使化
    public void initView(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this,data,R.layout.item,from,to);
        list.setAdapter(adapter);
    }

    //按下按鈕時作的動作
    public void lottery(View view) {
        int[] lottery = createNum();
        HashMap<String,String> row = new HashMap<>();
        row.put(from[0],"第"+ (data.size()+1) +"組");
        row.put(from[1],""+lottery[0]);
        row.put(from[2],""+lottery[1]);
        row.put(from[3],""+lottery[2]);
        row.put(from[4],""+lottery[3]);
        row.put(from[5],""+lottery[4]);
        row.put(from[6],""+lottery[5]);
        data.add(row);
        adapter.notifyDataSetChanged();

        list.smoothScrollToPosition(data.size()-1);
    }

    //產生不重復號碼返回使用
   public int[] createNum(){
       HashSet<Integer> nums = new HashSet<>();
       while(nums.size()<6){
           nums.add((int)(Math.random()*49+1));
       }
       int [] ret = new int[6];
       int i=0;
       for(Integer num:nums){
           ret[i]=num;
           i++;
       }
       Arrays.sort(ret);
       return ret;
   }
}
