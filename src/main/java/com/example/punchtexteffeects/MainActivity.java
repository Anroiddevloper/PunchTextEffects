package com.example.punchtexteffeects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ZoomTextView tvhelloWord;
    SpannableString ss;
    ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvhelloWord = findViewById(R.id.tvhelloWord);

        String data = tvhelloWord.getText().toString();
        final String[] items = data.split(" ");

        final SpannableStringBuilder resultText = new SpannableStringBuilder();
        ClickableSpan[] cs = new ClickableSpan[items.length];
        final SpannableStringBuilder longDescription = new SpannableStringBuilder();
        Log.d("tagKrishna", " " + items.length);
        for (int i = 0; i < items.length; i++) {
            ss = new SpannableString(items[i]);
            final int finalI = i;
            cs[i] = new ClickableSpan() {
                private String w = items[finalI];

                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void onClick(View widget) {

                    Pattern pattern = Pattern.compile(w);
                    Matcher matcher = pattern.matcher(tvhelloWord.getText().toString());
                    while (matcher.find()) {
                        int start = matcher.start();
                        int end = matcher.end();

                        Typeface type = Typeface.createFromAsset(getAssets(), "Font/" + "bleeding_cowboys.ttf");
                        Spannable spanText = new SpannableString(w);
                        spanText.setSpan(new StyleSpan(Typeface.BOLD), 0, w.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanText.setSpan(new TypefaceSpan(type), 0, w.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanText.setSpan(new ForegroundColorSpan(Color.rgb(158, 158, 158)), 0, w.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        longDescription.replace(start, end, spanText);

                        tvhelloWord.setText(longDescription);
                    }

                }

                @Override
                public void updateDrawState(TextPaint ds) {
//                    ds.setColor(ds.linkColor);    // you can use custom color
                    ds.setUnderlineText(false);    // this remove the underline
                }

            };


            ss.setSpan(cs[i], 0, items[i].

                    length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            longDescription.append(ss);
            longDescription.append(" ");
            tvhelloWord.setMovementMethod(LinkMovementMethod.getInstance());
            tvhelloWord.setText(longDescription);

        }

//        for (String item : items)
//        {
//
//
//            endIndex = startIndex + item.length();
//            Log.d("tagKrishna","start::End" + 0 + item.length());
//
//
////            endIndex--;
////            startIndex = startIndex + 1;
//            resultText.append(ss);
//            resultText.append(" ");
//            tvhelloWord.setMovementMethod(LinkMovementMethod.getInstance());
//            tvhelloWord.setText(resultText, TextView.BufferType.SPANNABLE);
//
//        }



//        tvhelloWord.setTextColor(Color.RED);

    }



}
