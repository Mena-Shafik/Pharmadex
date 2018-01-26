package com.example.mena.pharmadex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Mena Shafik
 */
public class PrescriptionActivity extends AppCompatActivity {

    Context context;
    private WebView myWebView;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        setTitle("Prescription");

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        String PBN = Integer.toString(bundle.getInt("PBN"));
        final String address = bundle.getString("address");
        final String locationName = bundle.getString("location");

        //fill doctor info from bundle
        final EditText PhyN = (EditText) findViewById(R.id.PBN);
        final EditText doctorName = (EditText) findViewById(R.id.FullName);
        final EditText locName = (EditText) findViewById(R.id.WorkPlace);
        final EditText Address = (EditText) findViewById(R.id.Address);

        // patient info
        final EditText patientName = (EditText) findViewById(R.id.patientName);
        final EditText age = (EditText) findViewById(R.id.age);
        final EditText OHIP = (EditText) findViewById(R.id.OHIP);
        final EditText weight = (EditText) findViewById(R.id.weight);
        final EditText height = (EditText) findViewById(R.id.height);

        // dosage info
        final EditText drugName = (EditText) findViewById(R.id.drugName);
        final EditText dosageAmount = (EditText) findViewById(R.id.dosageAmount);
        final Spinner units = (Spinner) findViewById(R.id.dosageUnits);
        final Spinner mode = (Spinner) findViewById(R.id.modeAdmin);
        final Spinner freq = (Spinner) findViewById(R.id.Freq);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Dosage_Unit_Array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Mode_Admin_Array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.Frequency_Array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        units.setAdapter(adapter1);
        mode.setAdapter(adapter2);
        freq.setAdapter(adapter3);


        Button printBtn = (Button) findViewById(R.id.print);

        PhyN.setText(PBN);
        doctorName.setText(name);
        locName.setText(locationName);
        Address.setText(address);




        webView = new WebView(this);



        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tab = "&nbsp&nbsp&nbsp&nbsp&nbsp";
                webView.setWebViewClient(new WebViewClient() {

                    public boolean shouldOverrideUrlLoading(WebView view, String url)
                    {
                        return false;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url)
                    {
                        createWebPrintJob(view);
                        myWebView = null;
                    }
                });
                String htmlDocument =
                        "<html>" +
                                "<body style=\"height: 500px; width: 700px;\">" +
                                "<h1 align=\"center\">"+ locationName +"</h1>" +
                                "<h2 align=\"center\">"+ address +"</h2>" +
                                "<hr>"+
                                "<h2 style=\"font-size:160%;\"> "+ name +" M.D</h2>" +
                                "<hr>"+
                                "<h2> Patient Name: "+ patientName.getText() +tab+tab+tab+tab+tab+tab+tab+tab+tab+tab+ "Age: "+ age.getText()+" <h2>"+
                                "<hr>"+
                                "<br>"+
                                "<h3> Drug: "+ drugName.getText()+tab+" Amount: "+dosageAmount.getText()+tab+ "Units: "+units.getSelectedItem().toString()+"</h3>"+
                                "<h3>Mode of Administration: "+mode.getSelectedItem().toString() +tab+"Freq: "+freq.getSelectedItem().toString()+"</h3>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<br>"+
                                "<p> Signature:_________________________ </p>"+
                                "</body>" +
                        "</html>";

                webView.loadDataWithBaseURL(null, htmlDocument,
                        "text/HTML", "UTF-8", null);

                myWebView = webView;
                Toast.makeText(getApplicationContext(),"Prescription Created Successfully",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @SuppressWarnings("deprecation")
    private void createWebPrintJob(WebView webView) {

        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        String jobName = getString(R.string.app_name) + " Print Test";

        printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());
    }
}







