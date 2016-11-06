package com.jomana.zene.jomanatasksmanger.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jomana.zene.jomanatasksmanger.R;

/**
 * Created by user on 10/30/2016.
 */

    public class MyAdapterTask extends ArrayAdapter<MyTask> {
        public MyAdapterTask(Context context, int resource) {
            super(context, resource);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_my_task,parent,false);
            CheckBox cbCheck=(CheckBox) convertView.findViewById(R.id.cbCheck);
            TextView tvTitle=(TextView) convertView.findViewById(R.id.tvTitle);
            TextView tvDate=(TextView) convertView.findViewById(R.id.tvDate);
            TextView tvAddress=(TextView) convertView.findViewById(R.id.tvAddress);
            ImageButton ibPhone=(ImageButton) convertView.findViewById(R.id.ibPhone);
            RatingBar rbPriority=(RatingBar) convertView.findViewById(R.id.rbPriority);
            final MyTask myTask=getItem(position);
            tvTitle.setText(myTask.getTitle());
            //tvDate.setText(myTask.getWhen().toString());
            tvAddress.setText(myTask.getAddress());
            cbCheck.setChecked(myTask.setIsCompleted());
            ibPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"call",Toast.LENGTH_SHORT).show();
                }
            });
            rbPriority.setRating(myTask.getPrioroty());
            cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    myTask.setIsCompleted(b);
                    Toast.makeText(getContext(),"checked" +""+ b,Toast.LENGTH_SHORT).show();

                }
            });

            return convertView;
        }
    }




