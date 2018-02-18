package com.musicplay.yuhwan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class multi3 extends Activity {

    private List<multi3.TextField> editTexts = new ArrayList<multi3.TextField>();
    private Socket client;
    private PrintWriter printwriter;
    private BufferedReader bufferedReader;
    InputStreamReader inputStreamReader;
    //Following is the IP address of the chat server. You can change this IP address according to your configuration.
    // I have localhost IP address for Android emulator.
    private String CHAT_SERVER_IP = "192.168.0.38";
    private int Group = 0;
    private EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    boolean CloseAllThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi3);
        Intent intent = getIntent();
        Group = intent.getExtras().getInt("Group");
        ChatOperator chatOperator = new ChatOperator();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            chatOperator.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
        else
            chatOperator.execute((Void[])null);
        editTexts.add(new multi3.TextField((EditText) findViewById(R.id.PersonalName),(EditText) findViewById(R.id.PersonalStory)));

        editText1 = findViewById(R.id.Name1);
        editText1.addTextChangedListener(new textwatcher(editText1));
        editText2= findViewById(R.id.Name2);
        editText2.addTextChangedListener(new textwatcher(editText2));
        editText3= findViewById(R.id.Name3);
        editText3.addTextChangedListener(new textwatcher(editText3));
        editText4= findViewById(R.id.Name4);
        editText4.addTextChangedListener(new textwatcher(editText4));
        editText5= findViewById(R.id.Name5);
        editText5.addTextChangedListener(new textwatcher(editText5));
        editText6= findViewById(R.id.Name6);
        editText6.addTextChangedListener(new textwatcher(editText6));
    }

    public void onStop () {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    public void End1(View V)
    {

        Intent intent = new Intent(multi3.this, multi4.class);
        intent.putExtra("Group",Group);
        startActivity(intent);
        finish();
    }
    public void End2(View V)
    {
        Intent intent = new Intent(multi3.this, multi4.class);
        intent.putExtra("Group",Group);
        startActivity(intent);
        finish();
    }
    public void End3(View V)
    {
        Intent intent = new Intent(multi3.this, multi4.class);
        intent.putExtra("Group",Group);
        startActivity(intent);
        finish();
    }
    public void End4(View V)
    {
        Intent intent = new Intent(multi3.this, multi4.class);
        intent.putExtra("Group",Group);
        startActivity(intent);
        finish();
    }
    public void End5(View V)
    {
        Intent intent = new Intent(multi3.this, multi4.class);
        intent.putExtra("Group",Group);
        startActivity(intent);
        finish();
    }
    public void End6(View V)
    {
        Intent intent = new Intent(multi3.this, multi4.class);
        intent.putExtra("Group",Group);
        startActivity(intent);
        finish();
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
                            Toast.makeText(multi3.this, "연결성공", Toast.LENGTH_SHORT).show();
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
            final multi3.Sender messageSender = new multi3.Sender(Integer.toString(Group)); // Initialize chat sender AsyncTask.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                messageSender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                messageSender.execute();
            }
            multi3.Receiver receiver = new multi3.Receiver(); // Initialize chat receiver AsyncTask.
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
                        MessageList = message.split("\r\n");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                EditText ed = findViewById(Integer.parseInt(message.split(":")[0]));
                                ed.setText(message.split(":")[1].replace("\n",""));
                            }
                        }, 0);
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

    public class textwatcher implements TextWatcher
    {
        private transient EditText editText = null;
        public textwatcher(EditText editText)
        {
            super();
            this.editText = editText;
        }
        @Override
        public void afterTextChanged(Editable arg0)
        {
            // TODO Auto-generated method stub
            String sendingmessage = editText.getId() +":"+arg0.toString()+":"+Group;
            final multi3.Sender messageSender = new multi3.Sender(sendingmessage);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                messageSender.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                messageSender.execute();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
        {
            // TODO Auto-generated method stub
        }
    }
}