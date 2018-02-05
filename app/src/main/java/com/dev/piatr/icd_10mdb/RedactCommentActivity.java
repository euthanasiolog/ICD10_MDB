package com.dev.piatr.icd_10mdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RedactCommentActivity extends AppCompatActivity {
    private String title;
    private String comment;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact_comment);
        TextView header = (TextView) findViewById(R.id.label_for_redact_comment);
        editText = (EditText) findViewById(R.id.redact_comment);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            title = bundle.getString("title");
            if (title!=null){
                header.setText(title);
                comment = bundle.getString("comment");
                if (comment!=null){
                    editText.setText(comment);
                }
            }
        }
    }

    public void backButton(View view){
        Intent intent = new Intent(view.getContext(), CommentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void saveButton(View view){
        comment = editText.getText().toString();
        if (title!=null) {
            DB db = new DB(view.getContext());
            db.openConnection();
            db.addComment(title, comment);
            db.closeConnection();
        }
        Intent intent = new Intent(view.getContext(), CommentActivity.class);
        if (title!=null){
            intent.putExtra("title", title);
        }
        intent.putExtra("comment", comment);
        startActivity(intent);
    }

}
