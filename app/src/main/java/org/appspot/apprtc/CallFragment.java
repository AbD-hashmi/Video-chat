/*
 *  Copyright 2015 The WebRTC Project Authors. All rights reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

package org.appspot.apprtc;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.webrtc.MediaStream;
import org.webrtc.RendererCommon.ScalingType;

import java.util.Random;

/**
 * Fragment for call control.
 */
public class CallFragment extends Fragment {
    private TextView contactView;
    private ImageButton cameraSwitchButton,btn_addfriend,btn_profile,btn_report,btn_gift;
    private ImageButton videoScalingButton,btn_mute,btn_unmute;
    private ImageButton toggleMuteButton,btn_find_next,btn_find_previous;
    private TextView captureFormatText;
    private SeekBar captureFormatSlider;
    private OnCallEvents callEvents;
    private ScalingType scalingType;
    private boolean videoCallEnabled = true;
    RelativeLayout relativeLayout;

    /**
     * Call control interface for container activity.
     */
    public interface OnCallEvents {
        void onCallHangUp();

        void onCameraSwitch();

        void onVideoScalingSwitch(ScalingType scalingType);

        void onCaptureFormatChange(int width, int height, int framerate);

        boolean onToggleMic();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View controlView = inflater.inflate(R.layout.fragment_call, container, false);

        // Create UI controls.
        contactView = controlView.findViewById(R.id.contact_name_call);
        btn_find_next = controlView.findViewById(R.id.button_next_room);
        btn_find_previous = controlView.findViewById(R.id.button_previous);
        cameraSwitchButton = controlView.findViewById(R.id.button_call_switch_camera);
        videoScalingButton = controlView.findViewById(R.id.button_call_scaling_mode);
        toggleMuteButton = controlView.findViewById(R.id.button_call_toggle_mic);
        captureFormatText = controlView.findViewById(R.id.capture_format_text_call);
        captureFormatSlider = controlView.findViewById(R.id.capture_format_slider_call);
        btn_mute = controlView.findViewById(R.id.button_mute);
        btn_unmute= controlView.findViewById(R.id.button_unmute);
        relativeLayout=controlView.findViewById(R.id.relativeLayout);
        btn_addfriend=controlView.findViewById(R.id.button_addfriend);
        btn_report=controlView.findViewById(R.id.button_report);
        btn_profile=controlView.findViewById(R.id.button_profile);
        btn_gift=controlView.findViewById(R.id.button_gift);
   //     MediaStream inputMediaStream=null;

        // Add buttons click events.


        btn_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Gift sent", Toast.LENGTH_SHORT).show();
            }
        });
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Profile Reported sucessfully", Toast.LENGTH_SHORT).show();
                Activity activity=((CallActivity)getActivity());
                Random random=new Random();
                int room= random.nextInt(6);
                startActivity(new Intent(activity,ConnectActivity.class).putExtra("room","2345"+room ));
                callEvents.onCallHangUp();
                activity.finish();
            }
        });
        btn_addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "User Added as a friend", Toast.LENGTH_SHORT).show();
            }
        });


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*allActivity callActivity=new CallActivity();
                callActivity.profile();*/
                Activity activity=((CallActivity)getActivity());
                startActivity(new Intent(callActivity,ProfileActivity.class));
                // Toast.makeText(getActivity(), "Gift sent", Toast.LENGTH_SHORT).show();
                callEvents.onCallHangUp();
                activity.finish();
            }
        });

        btn_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  inputMediaStream.audioTracks.getFirst().setEnabled(false);
                Toast.makeText(getActivity(), "Muted", Toast.LENGTH_SHORT).show();
                btn_unmute.setVisibility(View.VISIBLE);
                btn_mute.setVisibility(View.GONE);
            }
        });
        btn_unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  inputMediaStream.audioTracks.getFirst().setEnabled(true);
                Toast.makeText(getActivity(), "Unmuted", Toast.LENGTH_SHORT).show();
                btn_unmute.setVisibility(View.GONE);
                btn_mute.setVisibility(View.VISIBLE);
            }
        });



        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Activity activity=((CallActivity)getActivity());
                Random random=new Random();
                int room= random.nextInt(6);
                startActivity(new Intent(activity,ConnectActivity.class).putExtra("room","2345"+room ));
                callEvents.onCallHangUp();
                activity.finish();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Activity activity=((CallActivity)getActivity());
                Random random=new Random();
                int room= random.nextInt(6);
                startActivity(new Intent(activity,ConnectActivity.class).putExtra("room","2345"+room ));
                callEvents.onCallHangUp();
                activity.finish();
            }
        });


        btn_find_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Activity activity=((CallActivity)getActivity());
                Random random=new Random();
                int room= random.nextInt(6);
                startActivity(new Intent(activity,ConnectActivity.class).putExtra("room","2345"+room ));
                callEvents.onCallHangUp();
                activity.finish();*/
                Toast.makeText(getActivity(), "Disliked", Toast.LENGTH_SHORT).show();
            }
        });
        btn_find_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*Activity activity=((CallActivity)getActivity());
              Random random=new Random();
              int room= random.nextInt(6);
                startActivity(new Intent(activity,ConnectActivity.class).putExtra("room","2345"+room ));
                callEvents.onCallHangUp();
                activity.finish();*/
                Toast.makeText(getActivity(), "Liked", Toast.LENGTH_SHORT).show();
            }
        });

        cameraSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEvents.onCameraSwitch();
            }
        });

        videoScalingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (scalingType == ScalingType.SCALE_ASPECT_FILL) {
                    videoScalingButton.setBackgroundResource(R.drawable.ic_action_full_screen);
                    scalingType = ScalingType.SCALE_ASPECT_FIT;
                } else {
                    videoScalingButton.setBackgroundResource(R.drawable.ic_action_return_from_full_screen);
                    scalingType = ScalingType.SCALE_ASPECT_FILL;
                }
                callEvents.onVideoScalingSwitch(scalingType);
            }
        });
        scalingType = ScalingType.SCALE_ASPECT_FILL;

        toggleMuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean enabled = callEvents.onToggleMic();
                toggleMuteButton.setAlpha(enabled ? 1.0f : 0.3f);
            }
        });

        return controlView;
    }

Context callActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.callActivity=context;
    }

    @Override
    public void onStart() {
        super.onStart();

        boolean captureSliderEnabled = false;
        Bundle args = getArguments();
        if (args != null) {
            String contactName = args.getString(CallActivity.EXTRA_ROOMID);
            contactView.setText(contactName);
            videoCallEnabled = args.getBoolean(CallActivity.EXTRA_VIDEO_CALL, true);
            captureSliderEnabled = videoCallEnabled
                    && args.getBoolean(CallActivity.EXTRA_VIDEO_CAPTUREQUALITYSLIDER_ENABLED, false);
        }
        if (!videoCallEnabled) {
            cameraSwitchButton.setVisibility(View.INVISIBLE);
        }
        if (captureSliderEnabled) {
            captureFormatSlider.setOnSeekBarChangeListener(
                    new CaptureQualityController(captureFormatText, callEvents));
        } else {
            captureFormatText.setVisibility(View.GONE);
            captureFormatSlider.setVisibility(View.GONE);
        }
    }

    // TODO(sakal): Replace with onAttach(Context) once we only support API level 23+.
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callEvents = (OnCallEvents) activity;
    }
}
