package com.vungocminh.doanandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;
    private EditText messageEditText;
    private ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatRecyclerView = findViewById(R.id.chat_recycler_view);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_button);

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);

        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                if (!message.isEmpty()) {
                    chatMessages.add(new ChatMessage(message, ChatMessage.USER_MESSAGE));
                    chatAdapter.notifyItemInserted(chatMessages.size() - 1);
                    messageEditText.setText("");

                    // Send user message to API and get bot response
                    sendMessageToApi(message);
                }
            }
        });
    }

    private void sendMessageToApi(String message) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String apiUrl = "https://alert-saving-puma.ngrok-free.app/query";
                try {
                    JSONObject postData = new JSONObject();
                    postData.put("question", params[0]);

                    URL url = new URL(apiUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    os.write(postData.toString().getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        JSONObject responseJson = new JSONObject(response.toString());
                        return responseJson.getString("result");
                    } else {
                        return "Error: " + responseCode;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Add bot response to chat
                chatMessages.add(new ChatMessage(result, ChatMessage.BOT_MESSAGE));
                chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            }
        }.execute(message);
    }
}
