package pessoto.android.myheroes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import pessoto.android.myheroes.R;

public class SliderActivity extends IntroActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_red_light)
                .fragment(R.layout.intro_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_red_light)
                .fragment(R.layout.intro_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.holo_red_light)
                .fragment(R.layout.intro_3)
                .canGoForward(false)
                .build()
        );

    }

    public void irLogin(View view) {
        Intent i = new Intent(SliderActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
