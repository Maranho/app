package info.androidhive.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

public class Chatbot extends AppCompatActivity {

    private Button btnEviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);



        final TextView conversation = (TextView) findViewById(R.id.conversation);
        final EditText userInput = (EditText) findViewById(R.id.user_input);


        final ConversationService myConversationService =
                new ConversationService(
                        "2017-05-26",
                        getString(R.string.username),
                        getString(R.string.password)
                );


        btnEviar = findViewById(R.id.btnEnviar);

        btnEviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


                final String inputText = userInput.getText().toString();
                conversation.append(
                        Html.fromHtml("<p><b>Você:</b> " + inputText + "</p>")

                );


                MessageRequest request = new MessageRequest.Builder()
                        .inputText(inputText)
                        .build();


                myConversationService
                        .message(getString(R.string.workspace), request)
                        .enqueue(new ServiceCallback<MessageResponse>() {
                            @Override
                            public void onResponse(MessageResponse response) {
                                // More code here

                                final String outputText = response.getText().get(0);


                                if (response.getIntents().get(0).getIntent()
                                        .endsWith("RequestQuote")) {
                                    // More code here


                                    String quotesURL =
                                            "https://api.forismatic.com/api/1.0/" +
                                                    "?method=getQuote&format=text&lang=en";

                                    Fuel.get(quotesURL)
                                            .responseString(new Handler<String>() {
                                                @Override
                                                public void success(Request request,
                                                                    Response response, String quote) {
                                                    conversation.append(
                                                            Html.fromHtml("<p><b>Bot:</b> " +
                                                                    quote + "</p>")
                                                    );
                                                }

                                                @Override
                                                public void failure(Request request,
                                                                    Response response,
                                                                    FuelError fuelError) {
                                                }
                                            });


                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        conversation.append(
                                                Html.fromHtml("<p><b>Robô:</b> " +
                                                        outputText + "</p>")
                                        );
                                    }
                                });

                            }


                            @Override
                            public void onFailure(Exception e) {
                            }
                        });


                userInput.setText("");


            }



        });











    }
}
