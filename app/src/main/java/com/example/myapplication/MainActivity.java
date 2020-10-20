package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private EditText etTeamAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTeamAddress = findViewById(R.id.edtAdress);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView avatarImage = findViewById(R.id.avatarImage);

        if (resultCode == RESULT_CANCELED) {
            return;
        }
        Log.d("MAIN ACTIVITY", String.format("Result code : %d",resultCode));
        assert data != null;
        if (resultCode == 33) {
            String currentPhotoPath = data.getStringExtra("imageUrl");
            Bitmap photo = BitmapFactory.decodeFile(currentPhotoPath);
            photo = Bitmap.createScaledBitmap(photo, 300, 300, false);
            avatarImage.setImageBitmap(photo);
        } else {
            String drawableName;
            switch (data.getIntExtra("imageID", R.id.teamid00)) {
                case R.id.teamid00:
                    drawableName = "ic_logo_00";
                    break;
                case R.id.teamid01:
                    drawableName = "ic_logo_01";
                    break;
                case R.id.teamid02:
                    drawableName = "ic_logo_02";
                    break;
                case R.id.teamid03:
                    drawableName = "ic_logo_03";
                    break;
                case R.id.teamid04:
                    drawableName = "ic_logo_04";
                    break;
                case R.id.teamid05:
                    drawableName = "ic_logo_05";
                    break;
                default:
                    drawableName = "ic_logo_00";
                    break;
            }
            int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
            avatarImage.setImageResource(resourceId);
        }

    }

    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onOpenInGoogleMaps(View view) {
        String teamAddress = etTeamAddress.getText().toString();

        if (teamAddress.isEmpty()) {
            etTeamAddress.setError("Please provide a valid address!");
            return;
        }

        Uri gmmIntentUri = Uri.parse(String.format("http://maps.google.co.in/maps?q=%s", teamAddress));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}