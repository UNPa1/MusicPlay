package com.musicplay.yuhwan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static com.musicplay.yuhwan.R.id.PersonalName;

public class multi4 extends Activity {
    private GestureDetectorCompat gestureComp;
    private List<TextField> editTexts = new ArrayList<TextField>();
    private Socket client;
    private PrintWriter printwriter;
    private BufferedReader bufferedReader;
    InputStreamReader inputStreamReader;
    //Following is the IP address of the chat server. You can change this IP address according to your configuration.
    // I have localhost IP address for Android emulator.
    private String CHAT_SERVER_IP = "192.168.0.38";
    private int Group = 0;
    int ID = 0;
    boolean synctext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi4);
        Intent intent = getIntent();
        Group = intent.getExtras().getInt("Group");
        TextView groupetext= findViewById(R.id.grouptext);
        groupetext.setText("4-1. 모둠원별 시나리오 쓰기 - 모둠원"+Group+"     (클릭 후 입력, 동시 접속 입력 가능)");
        ChatOperator chatOperator = new ChatOperator();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            chatOperator.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
        else
            chatOperator.execute((Void[])null);
        editTexts.add(new TextField((EditText) findViewById(R.id.GroupName),(EditText) findViewById(R.id.GroupStory)));
        gestureComp = new GestureDetectorCompat(this, new multi4.ControlGesutre());
        EditText GroupStory = (EditText)findViewById(R.id.GroupStory);
        GroupStory.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){ // IME_ACTION_SEARCH , IME_ACTION_GO
                    String sendingmessage = "";
                    for (int i = 0; i != editTexts.size(); i++) {
                        sendingmessage += i + "'" + editTexts.get(i).Name.getText().toString() + ":" + editTexts.get(i).Story.getText().toString() + "\r\n";
                    }
                    final multi4.Sender messageSender = new multi4.Sender(sendingmessage);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        messageSender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        messageSender.execute();
                    }
                }
                return false;
            }
        });

        EditText GroupName = (EditText)findViewById(R.id.GroupName);
        GroupName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){ // IME_ACTION_SEARCH , IME_ACTION_GO
                    String sendingmessage = "";
                    for (int i = 0; i != editTexts.size(); i++) {
                        sendingmessage += i + "'" + editTexts.get(i).Name.getText().toString() + ":" + editTexts.get(i).Story.getText().toString() + "\r\n";
                    }
                    final multi4.Sender messageSender = new multi4.Sender(sendingmessage);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        messageSender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } else {
                        messageSender.execute();
                    }
                }
                return false;
            }
        });
    }

    public void onStop () {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureComp.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    public void AddEditText(View v)
    {
        TableLayout layout = findViewById(R.id.tableLayout2);
        TableRow layout_Row = new TableRow(this);
        layout_Row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        EditText Name = new EditText(this);
        Name.setLayoutParams(new TableLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()),TableLayout.LayoutParams.MATCH_PARENT));
        EditText Story = new EditText(this);
        Story.setLayoutParams(new TableLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics()),TableLayout.LayoutParams.MATCH_PARENT));
        layout_Row.addView(Name);
        layout_Row.addView(Story);
        layout.addView(layout_Row,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        layout.requestLayout();
        editTexts.add(new TextField(Name,Story));
    }

    /**
     * This AsyncTask create the connection with the server and initialize the
     * chat senders and receivers.
     */
    private class ChatOperator extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                client = new Socket(CHAT_SERVER_IP, 69); // Creating the server socket.

                if (client != null) {
                    printwriter = new PrintWriter(client.getOutputStream(), true);
                    inputStreamReader = new InputStreamReader(client.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(multi4.this, "연결성공", Toast.LENGTH_SHORT).show();
                        }
                    }, 0);
                } else {
                    System.out.println("Server has not bean started on port 4444.");
                }
            } catch (UnknownHostException e) {
                System.out.println("Faild to connect server " + CHAT_SERVER_IP);
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Faild to connect server " + CHAT_SERVER_IP);
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Following method is executed at the end of doInBackground method.
         */
        @Override
        protected void onPostExecute(Void result) {
            final multi4.Sender messageSender = new multi4.Sender("multi4"); // Initialize chat sender AsyncTask.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                messageSender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                messageSender.execute();
            }
            Receiver receiver = new Receiver(); // Initialize chat receiver AsyncTask.
            receiver.execute();

        }

    }

    /**
     * This AsyncTask continuously reads the input buffer and show the chat
     * message if a message is availble.
     */
    private class Receiver extends AsyncTask<Void, Void, Void> {
        private String message;
        String[] MessageList = null;
        @Override
        protected Void doInBackground(Void... params) {
            Handler handler = new Handler(Looper.getMainLooper());
            while (true) {
                try {
                    if (bufferedReader.ready()) {
                        message = bufferedReader.readLine();
                        if(message.length() != 0) {
                            MessageList = message.split("\n");
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (String IndexM : MessageList) {
                                        if (editTexts.size() < Integer.parseInt(IndexM.split(":")[0])) {
                                            TableLayout layout = findViewById(R.id.GroupTable);
                                            TableRow layout_Row = new TableRow(multi4.this);
                                            layout_Row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                                            EditText Name = new EditText(multi4.this);
                                            Name.setLayoutParams(new TableLayout.LayoutParams(80, TableLayout.LayoutParams.WRAP_CONTENT));
                                            EditText Story = new EditText(multi4.this);
                                            Story.setLayoutParams(new TableLayout.LayoutParams(320, TableLayout.LayoutParams.WRAP_CONTENT));
                                            layout_Row.addView(Name);
                                            layout_Row.addView(Story);
                                            layout.addView(layout_Row, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                                            layout.removeAllViews();
                                            editTexts.add(new TextField(Name, Story));
                                        }
                                        editTexts.get(Integer.parseInt(IndexM.split(":")[0])).Story.setText(IndexM.split(":")[2]);
                                        editTexts.get(Integer.parseInt(IndexM.split(":")[0])).Name.setText(IndexM.split(":")[1]);
                                        synctext = true;
                                    }
                                }
                            }, 0);
                        }
                        publishProgress(null);
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            Log.d("Info","Server: " + message + "\n");
        }

    }

    /**
     * This AsyncTask sends the chat message through the output stream.
     */
    private class Sender extends AsyncTask<Void, Void, Void> {

        private String message;

        public Sender(String message) {
            this.message = message;
        }

        @Override
        protected Void doInBackground(Void... parms) {
            printwriter.write(message + "\n");
            printwriter.flush();

            return null;
        }
    }

    public class TextField
    {
        public EditText Name;
        public EditText Story;

        TextField(EditText Name,EditText Story)
        {
            this.Name = Name;
            this.Story = Story;
        }
    }

    class ControlGesutre extends GestureDetector.SimpleOnGestureListener {
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                Intent intent = new Intent(multi4.this, multi1.class);
                startActivity(intent);
                finish();
            } else if (event2.getX() < event1.getX()) {
                //왼쪽
                Intent intent = new Intent(multi4.this, multi4.class);
                intent.putExtra("Group",Group);
                startActivity(intent);
                finish();
            }
            return true;
        }
    }
}