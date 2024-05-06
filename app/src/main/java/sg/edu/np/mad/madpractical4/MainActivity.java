package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        User user = new User("MAD", "MAD Developer",1,false);

        Intent intent = getIntent();
        int random = intent.getIntExtra("random", 0);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        TextView btnFollow = findViewById(R.id.btnFollow);
        TextView btnMessage = findViewById(R.id.btnMessage);

        tvName.setText(user.name + " " + random);
        tvDescription.setText(user.description);
        btnFollow.setText("Follow");

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.followed) {
                    btnFollow.setText("Unfollow");
                    user.followed = true;
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    Log.i("btnFollow", "onClick: Followed");
                } else {
                    btnFollow.setText("Follow");
                    user.followed = false;
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    Log.i("btnFollow", "onClick: Unfollowed");
                }
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(msgIntent);
            }
        });
    }
}