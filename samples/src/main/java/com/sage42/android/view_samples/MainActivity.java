package com.sage42.android.view_samples;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.sage42.android.view.CircularProgressBar;
import com.sage42.android.views_samples.R;

/**
 * Copyright (C) 2013- Sage 42 App Sdn Bhd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Corey Scott (corey.scott@sage42.com)
 *
 */
public class MainActivity extends Activity
{
    private static final int    ONE_SECOND_IN_MS = 1000;

    // view elements
    private CircularProgressBar mCountdownBar;
    private CircularProgressBar mCountUpBar;
    private CircularProgressBar mCountDownNoText;

    // some countdown timers to provide a little action
    private CountDownTimer      mTimerCountDown;
    private CountDownTimer      mTimerCountUp;
    private CountDownTimer      mTimerCountDownNoText;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // wire up the layout
        this.setContentView(R.layout.main_activity);

        // wire up the ui elements
        this.mCountdownBar = (CircularProgressBar) this.findViewById(R.id.countdown_bar);
        this.mCountUpBar = (CircularProgressBar) this.findViewById(R.id.countup_bar);
        this.mCountDownNoText = (CircularProgressBar) this.findViewById(R.id.countdown_bar_no_text);
    }

    @Override
    @SuppressWarnings("synthetic-access")
    protected void onResume()
    {
        super.onResume();

        // start some timers so that things move
        this.mTimerCountDown = new CountDownTimer(30 * ONE_SECOND_IN_MS, ONE_SECOND_IN_MS)
        {
            @Override
            public void onTick(final long millisUntilFinished)
            {
                final int secondsRemaining = (int) (millisUntilFinished / ONE_SECOND_IN_MS);
                MainActivity.this.mCountdownBar.setProgress(secondsRemaining);
            }

            @Override
            public void onFinish()
            {
                MainActivity.this.mCountdownBar.setProgress(0);
                // make it disappear (because we can)
                MainActivity.this.mCountdownBar.setVisibility(View.INVISIBLE);
            }
        }.start();

        this.mTimerCountUp = new CountDownTimer(30 * ONE_SECOND_IN_MS, ONE_SECOND_IN_MS)
        {
            @Override
            public void onTick(final long millisUntilFinished)
            {
                final int secondsElapsed = 30 - ((int) (millisUntilFinished / ONE_SECOND_IN_MS));
                MainActivity.this.mCountUpBar.setProgress(secondsElapsed);
            }

            @Override
            public void onFinish()
            {
                MainActivity.this.mCountUpBar.setProgress(30);
            }
        }.start();

        this.mTimerCountDownNoText = new CountDownTimer(30 * ONE_SECOND_IN_MS, ONE_SECOND_IN_MS)
        {
            @Override
            public void onTick(final long millisUntilFinished)
            {
                final int secondsRemaining = (int) (millisUntilFinished / ONE_SECOND_IN_MS);
                MainActivity.this.mCountDownNoText.setProgress(secondsRemaining);
            }

            @Override
            public void onFinish()
            {
                MainActivity.this.mCountDownNoText.setProgress(0);
            }
        }.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        // stop any running timers
        // there are needed to be clear and be sure that the timers dont cause exceptions when this activity is not in focus
        if (this.mTimerCountDown != null)
        {
            this.mTimerCountDown.cancel();
        }
        if (this.mTimerCountUp != null)
        {
            this.mTimerCountUp.cancel();
        }
        if (this.mTimerCountDownNoText != null)
        {
            this.mTimerCountDownNoText.cancel();
        }
    }

}