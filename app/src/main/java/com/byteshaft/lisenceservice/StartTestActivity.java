package com.byteshaft.lisenceservice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class StartTestActivity extends AppCompatActivity implements View.OnClickListener {

    private String currentCategory = "";
    private final int ICAC_QUESTIONS = 2;
    private int ASKED_ICAC_QUESTIONS = 0;
    private final int GENERAL_KNOWLEDGE_QUESTIONS = 14;
    private int ASKED_QUESTIONS_GENERAL_KNOWLEDGE = 0;
    private final int ALCOHOL_DRUGS_QUESTIONS = 2;
    private int ASKED_ALCOHOL_DRUGS_QUESTIONS = 0;
    private final int FATIGUE_AND_DEFENCE_DRIVING = 1;
    private int ASKED_FATIGUE_AND_DEFENCE_DRIVING = 0;
    private final int INTERSECTION = 1;
    private int ASKED_INTERSECTION = 0;
    private final int NEGLIGENT_DRIVING = 1;
    private int ASKED_NEGLIGENT_DRIVING = 0;
    private final int PEDESTRAINS = 1;
    private int ASKED_PEDESTRAINS = 0;
    private final int SEAT_BELTS_RESTRAINS = 1;
    private int ASKED_SEAT_BELTS_RESTRAINS = 0;
    private final int SPEED_LIMITS = 2;
    private int ASKED_SPEED_LIMITS = 0;
    private final int TRAFFIC_LIGHTS = 1;
    private int ASKED_TRAFFIC_LIGHTS = 0;
    private final int TRAFFIC_LIGHTS_2 = 1;
    private int ASKED_TRAFFIC_LIGHTS_2 = 0;
    private static StartTestActivity instance;
    private static ArrayList<String[]> questionsArrayForCurrent;
    private static HashMap<String, String[]> answersForSelected;
    private Button nextButton;
    private int questionIndex = 0;
    private int currentCategoryIndex = 0;
    private  ArrayList<String> categories;

    public static StartTestActivity getInstance() {
        return instance;
    }

    public static void setValuesForCurrentCategory(ArrayList<String[]> arrayList,
                                                   HashMap<String, String[]> hashMap) {
        questionsArrayForCurrent = new ArrayList<>();
        answersForSelected = new HashMap<>();
        questionsArrayForCurrent = arrayList;
        answersForSelected = hashMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start_test);
        instance = this;
        nextButton = (Button) findViewById(R.id.button_next);
        nextButton.setOnClickListener(this);
        categories = Data.initializeCategoriesArray();
        currentCategory = categories.get(currentCategoryIndex);
        loadFragment(new QuestionsFragment());

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataForQuestion(questionIndex);
    }

    private void loadDataForQuestion(int questionNum) {
        setCurrentCategoryAskedQuestion(currentCategory, questionNum);
        Log.i("loadDataForQuestion", ""+ questionNum);
        Data.getSelectedCategoryDetails(currentCategory);
        String drawableName = "";
        String question = questionsArrayForCurrent.get(questionNum)[0];
        if (!questionsArrayForCurrent.get(0)[1].trim().isEmpty()) {
            drawableName = questionsArrayForCurrent.get(questionNum)[1];
        }
        Log.i("Question", question);
        Log.i("answer one", answersForSelected.get(question)[0]);
        QuestionsFragment.getInstance().setValuesToDisplay(question
                , answersForSelected.get(question)[0],
                answersForSelected.get(question)[1],
                answersForSelected.get(question)[2],
                Integer.parseInt(answersForSelected.get(question)[3]),
                drawableName, currentCategory);
    }

    // Method to load the fragment required Fragment as parameter
    public void loadFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, fragment);
        tx.commit();
    }

    private int  getCurrentCategoryMaxQuestion(String currentCategory) {
        switch (currentCategory) {
            case Data.sICAC:
                return ICAC_QUESTIONS;
            case Data.sGeneralKnowledge:
                return GENERAL_KNOWLEDGE_QUESTIONS;
            case Data.sAlcholDrugs:
                return ALCOHOL_DRUGS_QUESTIONS;
            case Data.sFatigueAndDefensiveDriving:
                return FATIGUE_AND_DEFENCE_DRIVING;
            case Data.sIntersections:
                return INTERSECTION;
            case Data.sNegligentDriving:
                return NEGLIGENT_DRIVING;
            case Data.sPedestrians:
                return PEDESTRAINS;
            case Data.sSeatBeltsRestraints:
                return SEAT_BELTS_RESTRAINS;
            case Data.sSpeedLimits:
                return SPEED_LIMITS;
            case Data.sTrafficLightsLanes:
                return TRAFFIC_LIGHTS;
            case Data.sTrafficLightsLanes2:
                return TRAFFIC_LIGHTS_2;
            default:
                return ICAC_QUESTIONS;
        }
    }

    private int  getCurrentCategoryAskedQuestion(String currentCategory) {
        switch (currentCategory) {
            case Data.sICAC:
                return ASKED_ICAC_QUESTIONS;
            case Data.sGeneralKnowledge:
                return ASKED_QUESTIONS_GENERAL_KNOWLEDGE;
            case Data.sAlcholDrugs:
                return ASKED_ALCOHOL_DRUGS_QUESTIONS;
            case Data.sFatigueAndDefensiveDriving:
                return ASKED_FATIGUE_AND_DEFENCE_DRIVING;
            case Data.sIntersections:
                return ASKED_INTERSECTION;
            case Data.sNegligentDriving:
                return ASKED_NEGLIGENT_DRIVING;
            case Data.sPedestrians:
                return ASKED_PEDESTRAINS;
            case Data.sSeatBeltsRestraints:
                return ASKED_SEAT_BELTS_RESTRAINS;
            case Data.sSpeedLimits:
                return ASKED_SPEED_LIMITS;
            case Data.sTrafficLightsLanes:
                return ASKED_TRAFFIC_LIGHTS;
            case Data.sTrafficLightsLanes2:
                return ASKED_TRAFFIC_LIGHTS_2;
            default:
                return ASKED_ICAC_QUESTIONS;
        }
    }

    private void  setCurrentCategoryAskedQuestion(String currentCategory, int questions) {
        switch (currentCategory) {
            case Data.sICAC:
                ASKED_ICAC_QUESTIONS = questions;
            case Data.sGeneralKnowledge:
                ASKED_QUESTIONS_GENERAL_KNOWLEDGE = questions;
            case Data.sAlcholDrugs:
                ASKED_ALCOHOL_DRUGS_QUESTIONS = questions;
            case Data.sFatigueAndDefensiveDriving:
                ASKED_FATIGUE_AND_DEFENCE_DRIVING = questions;
            case Data.sIntersections:
                ASKED_INTERSECTION = questions;
            case Data.sNegligentDriving:
                ASKED_NEGLIGENT_DRIVING = questions;
            case Data.sPedestrians:
                ASKED_PEDESTRAINS = questions;
            case Data.sSeatBeltsRestraints:
                ASKED_SEAT_BELTS_RESTRAINS = questions;
            case Data.sSpeedLimits:
                ASKED_SPEED_LIMITS = questions;
            case Data.sTrafficLightsLanes:
                ASKED_TRAFFIC_LIGHTS = questions;
            case Data.sTrafficLightsLanes2:
                ASKED_TRAFFIC_LIGHTS_2 = questions;
            default:
                ASKED_ICAC_QUESTIONS = questions;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_next:
                QuestionsFragment.getInstance().hideCurrentQuestion();
                questionIndex++;
                Log.i("ASKED", getCurrentCategoryAskedQuestion(currentCategory)+"");
                Log.i("Total Que", ""+getCurrentCategoryMaxQuestion(currentCategory));
                if (getCurrentCategoryAskedQuestion(currentCategory)
                        < getCurrentCategoryMaxQuestion(currentCategory)) {
                    loadDataForQuestion(questionIndex);
                    QuestionsFragment.getInstance().showCurrentQuestion();
                } else {
                    questionIndex = 0;
                    int nextIndex = currentCategoryIndex + 1;
                    currentCategory = categories.get(nextIndex);
                    Log.i("Currentcategory", currentCategory);
                    loadDataForQuestion(questionIndex);
                    QuestionsFragment.getInstance().showCurrentQuestion();
                }

                break;
        }
    }
}