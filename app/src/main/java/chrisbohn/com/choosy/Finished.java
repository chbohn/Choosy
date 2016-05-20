package chrisbohn.com.choosy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Finished extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageURI(MainActivity.best);
        final Button shareButton = (Button) findViewById(R.id.button2);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, MainActivity.best);
                startActivity(Intent.createChooser(intent, "Share Photo"));
            }
        });
    }
}
