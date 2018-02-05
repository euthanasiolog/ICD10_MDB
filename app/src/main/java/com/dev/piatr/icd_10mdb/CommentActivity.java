package com.dev.piatr.icd_10mdb;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CommentActivity extends AppCompatActivity {
    String title;
    private final String deleteDialog = "УДАЛИТЬ ЗАМЕТКУ?";
    private final String okDialog = "УДАЛИТЬ";
    private final String cancelDialog = "ОТМЕНА";
    String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle bundle = getIntent().getExtras();
        TextView textView = (TextView) findViewById(R.id.comment_text);
        TextView header = (TextView) findViewById(R.id.header_comment);
        if (bundle!=null){
            title = bundle.getString("title");
            if (title!=null){
                header.setText(title);
            }
            comment = bundle.getString("comment");
            if (comment!=null){
                textView.setText(comment);
            }
            textView.performClick();
            }

                final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RedactCommentActivity.class);
                if (title!=null){
                    intent.putExtra("title", title);
                }
                if (comment!=null){
                    intent.putExtra("comment", comment);
                }
                startActivity(intent);
            }
        });

                final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_delete);
                fab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle(title);
                        builder.setMessage(deleteDialog);
                        builder.setPositiveButton(okDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DB db = new DB(v.getContext());
                                db.openConnection();
                                db.deleteComment(title);
                                db.closeConnection();
                                Intent intent = new Intent(v.getContext(), CommentActivity.class);
                                intent.putExtra("title", title);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton(cancelDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fab.isShown()){
                    fab.hide();
                }else fab.show();
                if (fab1.isShown()){
                    fab1.hide();
                }else fab1.show();
            }
        });
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}

