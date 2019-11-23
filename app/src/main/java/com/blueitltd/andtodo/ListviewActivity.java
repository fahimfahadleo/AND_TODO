package com.blueitltd.andtodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ListviewActivity extends AppCompatActivity {
    ImageButton addbutton;
    ListView listView;

    TextInputEditText todo,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        addbutton=findViewById(R.id.addbutton);
        listView=findViewById(R.id.mylistview);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonShowPopupWindowClick(addbutton);
            }
        });



    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        final LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popupwindow, null);

        // create the popup window

        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final boolean focusable = true; // lets taps outside the popup also dismiss it


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);


        int width = size.x;
        final PopupWindow popupWindow = new PopupWindow(popupView, width-120, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
          todo  =popupView.findViewById(R.id.todo);
          time=popupView.findViewById(R.id.time);
        MaterialButton add=popupView.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String todotext=String.valueOf(todo.getText());
               String timetext=String.valueOf(time.getText());
               View focusview=null;
               boolean isok=true;
               if(TextUtils.isEmpty(todotext)){
                   todo.setError("Field Can Not Be Empty!");
                   isok=false;
                   focusview=todo;
               }else if(TextUtils.isEmpty(timetext)){
                   time.setError("Field Can Not Be Empty!");
                   isok=false;
                   focusview=time;
               }

               if(isok){
                   popupWindow.dismiss();
               }else{
                    focusview.requestFocus();
                    isok=true;
               }

            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

    }


}
