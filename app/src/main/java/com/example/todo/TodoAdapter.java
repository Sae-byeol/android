package com.example.todo;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private ArrayList<Todo> mdata=null;


    public class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView textView_todo_item;
        protected ImageButton deleteBt;
        protected ImageButton editBt;
        protected EditText editText;
        protected ImageButton circleBt;
        int mode=0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_todo_item=itemView.findViewById(R.id.textview_todo_item);
            this.deleteBt=itemView.findViewById(R.id.delete_btn);
            this.editBt=itemView.findViewById(R.id.edit_btn);
            this.editText=itemView.findViewById(R.id.edit_todo_item);
            this.circleBt=itemView.findViewById(R.id.check_btn);

            circleBt.setOnClickListener(new View.OnClickListener(){
                //첫 뷰는 계속 취소선 생김-오류 해결 필요
                @Override
                public void onClick(View v) {
                    //동그라미,글씨 색 바뀌고 글씨엔 밑줄
                    int position=getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        circleBt.setImageResource(R.drawable.circle_cheked);
                        textView_todo_item.setPaintFlags(textView_todo_item.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                        Todo check_todo=new Todo(textView_todo_item.getText().toString(),1);
                        mdata.set(position,check_todo);
                    }

                }
            });

            deleteBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        if (mode==1){
                            //수정 중이라면 -> 삭제버튼이 체크버튼인 상태
                            //체크버튼 누르면 작성한 text로 setText되고 다시 원래 버튼으로
                            deleteBt.setImageResource(R.drawable.delete);
                            editBt.setVisibility(View.VISIBLE);
                            textView_todo_item.setVisibility(View.VISIBLE);
                            editText.setVisibility(View.INVISIBLE);
                            textView_todo_item.setText(editText.getText().toString());
                            Todo editTodo=new Todo(editText.getText().toString());
                            mdata.set(position,editTodo);
                            mode=0;
                        }
                        else{
                            //삭제
                            mdata.remove(position);
                            //만약 취소선 되어있던 뷰라면 해당 포지션에 대해 취소선 없애줘야함
                            circleBt.setImageResource(R.drawable.circle_uncheked);
                            textView_todo_item.setPaintFlags(0);

                        }
                        notifyDataSetChanged();
                    }
                }
            });
            editBt.setOnClickListener(new View.OnClickListener(){
                //연필이미지 사라지고 삭제이미지 대신 체크이미지(ok)
                //text부분에서 편집 가능하도록(ok)

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        deleteBt.setImageResource(R.drawable.check_icon);
                        editBt.setVisibility(View.INVISIBLE);
                        textView_todo_item.setVisibility(View.INVISIBLE);
                        editText.setVisibility(View.VISIBLE);
                        editText.setText(textView_todo_item.getText());
                        //수정버튼이 클릭된 상태
                        mode=1;
                    }
                }
            });

        }
    }

    TodoAdapter(ArrayList<Todo> list){
        mdata=list;
    }
    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.recycle_item,parent,false);
        TodoAdapter.ViewHolder vh=new TodoAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        holder.textView_todo_item.setText(mdata.get(position).getTodoName());
        if (mdata.get(position).flag==1){
            //이 경우에는 취소선이랑 동그라미 가져가야됨
            holder.circleBt.setImageResource(R.drawable.circle_cheked);
            holder.textView_todo_item.setPaintFlags(holder.textView_todo_item.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.circleBt.setImageResource(R.drawable.circle_uncheked);
            holder.textView_todo_item.setPaintFlags(0);
        }

    }



    @Override
    public int getItemCount() {
        return mdata.size();
    }
}
