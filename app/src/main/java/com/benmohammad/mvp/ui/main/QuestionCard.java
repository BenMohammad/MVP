package com.benmohammad.mvp.ui.main;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import com.androidnetworking.internal.ANImageLoader;
import com.androidnetworking.widget.ANImageView;
import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.db.model.Option;
import com.benmohammad.mvp.data.db.model.Question;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.card_layout)
public class QuestionCard {

    private static final String TAG = "QuestionCard";

    @View(R.id.tv_question_txt)
    private TextView questionTV;

    @View(R.id.btn_option_1)
    private Button option1Button;


    @View(R.id.btn_option_2)
    private Button option2Button;


    @View(R.id.btn_option_3)
    private Button option3Button;


    @View(R.id.iv_pic)
    private ANImageView picImageView;

    private Question question;

    public QuestionCard(Question question) {
        this.question = question;
    }

    @Resolve
    private void onResolved() {
        questionTV.setText(question.getQuestionText());

        for(int i = 0; i < 3; i++) {
            Button button = null;
            switch (i) {
                case 0:
                    button = option1Button;
                    break;
                case 1:
                    button = option2Button;
                    break;
                case 2:
                    button = option3Button;
                    break;
            }
            if(button != null)
                button.setText(question.getOptionList().get(i).getOptionText());
            if(question.getImgUrl() != null) {
                picImageView.setImageUrl(question.getImgUrl());
            }
        }
    }

    private void showCorrectOptions() {
        for(int i = 0; i < 3; i++) {
            Option option = question.getOptionList().get(i);
            Button button = null;
            switch(i) {
                case 0:
                    button = option1Button;
                    break;
                case 1:
                    button = option2Button;
                    break;
                case 2:
                    button = option3Button;
                    break;
            }
            if(button != null) {
                if(option.getIsCorrect()) {
                    button.setBackgroundColor(Color.GREEN);
                } else {
                    button.setBackgroundColor(Color.RED);
                }
            }
        }
    }

    @Click(R.id.btn_option_1)
    public void onOption1Click() {
        showCorrectOptions();
    }

    @Click(R.id.btn_option_2)
    public void onOption2Click() {
        showCorrectOptions();
    }

    @Click(R.id.btn_option_3)
    public void onOption3Click() {
        showCorrectOptions();
    }



}
