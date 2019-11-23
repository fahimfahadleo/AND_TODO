package com.blueitltd.andtodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListviewActivity extends AppCompatActivity {
    ImageButton addbutton;
    ListView listView;

    TextInputEditText todo,time;
    MaterialButton add;
    ArrayList<itemClass> users;
    ListveiwAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        addbutton=findViewById(R.id.addbutton);
        listView=findViewById(R.id.mylistview);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialoguebox();
            }
        });


       users =new ArrayList<>();
       adapter=new ListveiwAdapter(this,users);
       listView.setAdapter(adapter);

    }

    void showDialoguebox(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ListviewActivity.this);
        View DialogueView = getLayoutInflater().inflate(R.layout.popupwindow,null);

        todo=DialogueView.findViewById(R.id.todo);
        time=DialogueView.findViewById(R.id.time);
        add=DialogueView.findViewById(R.id.add);

        builder.setView(DialogueView);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todotext=String.valueOf(todo.getText());
                String timetext=String.valueOf(time.getText());
                View focusview=null;
                boolean isok=true;
                if(TextUtils.isEmpty(todotext)){
                    todo.setError("Field Can Not Be Empty!");
                    focusview=todo;
                    isok=false;
                }else if(TextUtils.isEmpty(timetext)){
                    time.setError("Field Can Not Be Empty!");
                    focusview=time;
                    isok=false;
                }
                if(isok){
                    users.add(new itemClass(todotext,timetext));
                    adapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }else {
                    focusview.requestFocus();
                    isok=true;

                }
            }
        });
        alertDialog.show();
    }
    class itemClass{
        String work;
        String time;
        public itemClass(String todo,String time){
            work=todo;
            this.time=time;
        }
    }

    class ListveiwAdapter extends ArrayAdapter<itemClass>{

        public ListveiwAdapter(@NonNull Context context,@NonNull ArrayList<itemClass> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                itemClass itemClass=getItem(position);
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlistviewlayout, parent, false);
                TextView todotext=convertView.findViewById(R.id.todo);
                TextView timetext=convertView.findViewById(R.id.time);
                todotext.setText(itemClass.work);
                timetext.setText(itemClass.time);
            }


            return convertView;
        }
    }
}
