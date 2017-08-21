package com.teng.androidfactory;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.vdurmont.emoji.EmojiParser;

/**
 * Created by teng on 17/8/18.
 */

public class EmojiAvtivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);

        EditText editText = (EditText) findViewById(R.id.edit_text);

        TextView textView = (TextView) findViewById(R.id.text);

        String str = "An awesome string with a few emojis!";
//        Collection<Emoji>  collection = new ArrayList<Emoji>();
//        collection.add(EmojiManager.getForAlias("wink")); // This is 😉
//        editText.setText(EmojiParser.removeAllEmojis(str));
//        System.out.println(EmojiParser.removeAllEmojis(str));

        boolean change = true;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


//                editText.setText(str);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String ss = s.toString();
                String str = EmojiParser.removeAllEmojis(ss);
                if (!str.equals(ss)){
                    textView.setText(str);
                    editText.setText(str);
                    editText.setSelection(str.length());
                }
            }
        });


    }
}
