/*
 * Copyright (c) 2015 Google Inc. All rights reserved.
 * Copyright (C) 2016 Hugo Matalonga & João Paulo Fernandes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hmatalonga.greenhub.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmatalonga.greenhub.R;
import com.hmatalonga.greenhub.ui.WelcomeActivity;
import com.hmatalonga.greenhub.util.SettingsUtils;

import static com.hmatalonga.greenhub.util.LogUtils.LOGD;
import static com.hmatalonga.greenhub.util.LogUtils.makeLogTag;

/**
 * The Terms of Service fragment in the welcome screen.
 */
public class TosFragment extends WelcomeFragment implements WelcomeActivity.WelcomeActivityContent {

    private static final String TAG = makeLogTag("TosFragment");

    @Override
    public boolean shouldDisplay(Context context) {
        return !SettingsUtils.isTosAccepted(context);
    }

    @Override
    protected View.OnClickListener getPositiveListener() {
        return new WelcomeFragmentOnClickListener(mActivity) {
            @Override
            public void onClick(View v) {
                // Ensure we don't run this fragment again
                LOGD(TAG, "Marking TOS flag.");
                SettingsUtils.markTosAccepted(mActivity, true);
                doNext();
            }
        };
    }

    @Override
    protected View.OnClickListener getNegativeListener() {
        return new WelcomeFragmentOnClickListener(mActivity) {
            @Override
            public void onClick(View v) {
                // Ensure we don't run this fragment again
                LOGD(TAG, "Need to accept Tos.");
                doFinish();
            }
        };
    }

    @Override
    protected String getPositiveText() {
        LOGD(TAG, "Getting Accept string.");
        return getResourceString(R.string.accept);
    }

    @Override
    protected String getNegativeText() {
        LOGD(TAG, "Getting Decline string.");
        return getResourceString(R.string.decline);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.welcome_tos_fragment, container, false);
    }
}
