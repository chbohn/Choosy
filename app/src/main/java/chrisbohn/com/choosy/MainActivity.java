package chrisbohn.com.choosy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    public static Uri best = null;
    public static final int PICK_IMAGE = 100;
    public static Queue<Uri> images = new LinkedList<>();
    public static Uri[] comparison = new Uri[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button);
        final ImageButton imgButton1 = (ImageButton) findViewById(R.id.imageButton), imgButton2 = (ImageButton) findViewById(R.id.imageButton2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK);
                pickPhotoIntent.setType("image/*");
                startActivityForResult(pickPhotoIntent, PICK_IMAGE);
                if(images.size()==2 && comparison[0] == null) {
                    comparison[0] = images.remove();
                    comparison[1] = images.remove();
                    imgButton1.setImageURI(comparison[0]);
                    imgButton2.setImageURI(comparison[1]);
                }
            }
        });
        imgButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(button.isEnabled()) button.setEnabled(false);
                if(!images.isEmpty()) {
                    comparison[1] = images.remove();
                    imgButton2.setImageURI(comparison[1]);
                }
                else {
                    best = comparison[0];
                    startActivity(new Intent(MainActivity.this, Finished.class));
                }
            }
        });
        imgButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(button.isEnabled()) button.setEnabled(false);
                if(!images.isEmpty()) {
                    comparison[0] = images.remove();
                    imgButton1.setImageURI(comparison[0]);
                }
                else {
                    best = comparison[1];
                    startActivity(new Intent(MainActivity.this, Finished.class));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            images.add(imageUri);
        }
    }
}
