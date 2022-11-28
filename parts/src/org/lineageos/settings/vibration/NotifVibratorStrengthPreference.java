/*
 * Copyright (C) 2017-2022 The LineageOS Project
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

package org.lineageos.settings.vibration;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.os.Vibrator;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceViewHolder;

import org.lineageos.settings.R;
import org.lineageos.settings.preferences.CustomSeekBarPreference;
import org.lineageos.settings.preferences.FileUtils;

public class NotifVibratorStrengthPreference extends CustomSeekBarPreference {

    private static int mMinVal = 116;
    private static int mMaxVal = 3596;
    private static int mDefVal = mMaxVal - (mMaxVal - mMinVal) / 4;
    private Vibrator mVibrator;

    private static final String FILE_LEVEL = "/sys/devices/platform/soc/200f000.qcom,spmi/spmi-0/spmi0-03/200f000.qcom,spmi:qcom,pmi8950@3:qcom,haptics@c000/leds/vibrator/vmax_mv_strong";
    private static final long[] testVibrationPattern = {0,250};

    public NotifVibratorStrengthPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInterval = 10;
        mShowSign = false;
        mUnits = "";
        mContinuousUpdates = false;
        mMinValue = mMinVal;
        mMaxValue = mMaxVal;
        mDefaultValueExists = true;
        mDefaultValue = mDefVal;
        mValue = Integer.parseInt(loadValue());

        setPersistent(false);
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static boolean isSupported() {
        return FileUtils.fileWritable(FILE_LEVEL);
    }

    public static String loadValue() {
        return FileUtils.getFileValue(FILE_LEVEL, String.valueOf(mDefVal));
    }

    private void setValue(String newValue) {
        FileUtils.writeValue(FILE_LEVEL, newValue);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        editor.putString("notif_vib_strength", newValue);
        editor.commit();
        mVibrator.vibrate(testVibrationPattern, -1);
    }

    public static void restore(Context context) {
        if (!isSupported()) {
            return;
        }

        String storedValue = PreferenceManager.getDefaultSharedPreferences(context).getString("notif_vib_strength", String.valueOf(mDefVal));
        FileUtils.writeValue(FILE_LEVEL, storedValue);
    }

    @Override
    protected void changeValue(int newValue) {
        setValue(String.valueOf(newValue));
    }
}
