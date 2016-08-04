package co.wordywordy.wordywordy;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements QuizListFragment.OnFragmentInteractionListener {

    private Spinner areaSpinner;
    private Spinner levelSpinner;
    private Button takeQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        areaSpinner = (Spinner) findViewById(R.id.area_spinner);
        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(this, R.array.area_choice_spinner, android.R.layout.simple_spinner_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);

        levelSpinner = (Spinner) findViewById(R.id.level_spinner);
        ArrayAdapter<CharSequence> levelAdapter = ArrayAdapter.createFromResource(this, R.array.level_choice_spinner, android.R.layout.simple_spinner_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelAdapter);

        takeQuizButton = (Button) findViewById(R.id.take_quiz_button);
        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(QuizListFragment.newInstance("test", "test2"), "TAG");
                fragmentTransaction.commit();

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
